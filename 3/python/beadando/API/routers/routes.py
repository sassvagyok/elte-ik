from schemas.schema import User, Basket, Item
from fastapi.responses import JSONResponse, RedirectResponse
from fastapi import FastAPI, HTTPException, Request, Response, Cookie, status
from fastapi import APIRouter
from data.filereader import (
    get_user_by_id,
    get_basket_by_user_id,
    get_all_users,
    get_total_price_of_basket,
    verify_token
)
from data.filehandler import (
    add_user,
    add_basket,
    add_item_to_basket,
    update_item,
    delete_item
)

'''

Útmutató a fájl használatához:

- Minden route esetén adjuk meg a response_modell értékét (típus)
- Ügyeljünk a típusok megadására
- A függvények visszatérési értéke JSONResponse() legyen
- Minden függvény tartalmazzon hibakezelést, hiba esetén dobjon egy HTTPException-t
- Az adatokat a data.json fájlba kell menteni.
- A HTTP válaszok minden esetben tartalmazzák a 
  megfelelő Státus Code-ot, pl 404 - Not found, vagy 200 - OK

'''

routers = APIRouter()

# Új felhasználó létrehozása és hozzáadása
@routers.post('/adduser', response_model=User, summary="Új felhasználó hozzáadása", tags=["User"])
def adduser(user: User, api_token: str) -> User:
    """
    Új felhasználó hozzáadása, az eddigiektől eltérő azonosítóval.
    """
    if verify_token(api_token) is False:
        raise HTTPException(status_code=status.HTTP_403_FORBIDDEN, detail="Érvénytelen token!")
    
    add_user(user.model_dump())

    return JSONResponse(status_code=status.HTTP_201_CREATED, content=user.model_dump())

# Új kosár létrehozása egy felhasználónak
@routers.post('/addshoppingbag', response_model=str, summary="Kosár rendelése egy felhasználóhoz", tags=["Bag"])
def addshoppingbag(userid: int, api_token: str) -> str:
    """
    Új kosár rendelése egy létező felhasználóhoz.
    """
    if verify_token(api_token) is False:
        raise HTTPException(status_code=status.HTTP_403_FORBIDDEN, detail="Érvénytelen token!")

    user = get_user_by_id(userid)
    if user is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="A felhasználó nem található!")
    
    existing_basket = get_basket_by_user_id(userid)

    if existing_basket is not None:
        raise HTTPException(status_code=status.HTTP_400_BAD_REQUEST, detail="A felhasználónak már van kosara!")

    new_basket = {
        "id": userid + 100,
        "user_id": userid,
        "items": []
    }

    add_basket(new_basket)

    return JSONResponse(status_code=status.HTTP_201_CREATED, content=new_basket)

# Új termék hozzáadása egy kosárhoz
@routers.post('/additem', response_model=Basket, summary="Termék hozzáadása egy kosárhoz", tags=["Bag"])
def additem(userid: int, item: Item) -> Basket:
    """
    Új termék hozzáadása egy felhasználó kosarához.
    """
    user = get_user_by_id(userid)
    if user is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="A felhasználó nem található!")
    
    basket = get_basket_by_user_id(userid)
    if basket is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="A felhasználónak nincs kosara!")
    
    add_item_to_basket(userid, item.model_dump())

    basket = get_basket_by_user_id(userid)

    return JSONResponse(status_code=status.HTTP_201_CREATED, content=basket)

# Egy kosárban lévő termék módosítása
@routers.put('/updateitem', response_model=Basket, summary="Kosárban lévő termék módosítása", tags=["Bag"])
def updateitem(userid: int, itemid: int, updateItem: Item) -> Basket:
    """
    Egy felhasználó kosárban lévő termék módosítása.
    """
    user = get_user_by_id(userid)
    if user is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="A felhasználó nem található!")
    
    basket = get_basket_by_user_id(userid)
    if basket is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="A felhasználónak nincs kosara!")
    
    item = next((i for i in basket["items"] if i["item_id"] == itemid), None)

    if item is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Nincs ilyen termék a kosárban!")

    update_item(userid, itemid, updateItem)

    basket = get_basket_by_user_id(userid)

    return JSONResponse(status_code=status.HTTP_200_OK, content=basket)

# Egy kosárban lévő termék törlése
@routers.delete('/deleteitem', response_model=Basket, summary="Kosárban lévő termék törlése", tags=["Bag"])
def deleteitem(userid: int, itemid: int) -> Basket:
    """
    Egy felhasználó kosárban lévő termék törlése.
    """
    user = get_user_by_id(userid)
    if user is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="A felhasználó nem található!")
    
    basket = get_basket_by_user_id(userid)
    if basket is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="A felhasználónak nincs kosara!")
    
    item = next((i for i in basket["items"] if i["item_id"] == itemid), None)

    if item is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Nincs ilyen termék a kosárban!")

    delete_item(userid, itemid)

    basket = get_basket_by_user_id(userid)

    return JSONResponse(status_code=status.HTTP_200_OK, content=basket)

# Egy felhasználó lekérdezése
@routers.get('/user', response_model=User, summary="Felhasználó lekérdezése", tags=["User"])
def user(userid: int, api_token: str) -> User:
    """
    Egy létező felhasználó adatainak lekérdezése.
    """
    if verify_token(api_token) is False:
        raise HTTPException(status_code=status.HTTP_403_FORBIDDEN, detail="Érvénytelen token!")
    user = get_user_by_id(userid)

    if user is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Felhasználó nem található!")

    return JSONResponse(status_code=status.HTTP_200_OK, content=user)

# Az összes felhasználó lekérdezése
@routers.get('/users', response_model=list[User], summary="Az összes felhasználó lekérdezése", tags=["User"])
def users(api_token: str) -> list[User]:
    """
    Az összes felhasználó adatainak lekérdezése.
    """
    if verify_token(api_token) is False:
        raise HTTPException(status_code=status.HTTP_403_FORBIDDEN, detail="Érvénytelen token!")
    
    users = get_all_users()

    if len(users) == 0:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Nincsenek felhasználók!")

    return JSONResponse(status_code=status.HTTP_200_OK, content=users)

# Egy kosár lekérdezése
@routers.get('/shoppingbag', response_model=list[Item], summary="Felhasználó kosarának lekérdezése", tags=["Bag"])
def shoppingbag(userid: int) -> list[Item]:
    """
    Egy felhasználó kosarának lekérdezése.
    """
    user = get_user_by_id(userid)
    if user is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="A felhasználó nem található!")
    
    basket = get_basket_by_user_id(userid)
    if basket is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="A felhasználónak nincs kosara!")
    
    return JSONResponse(status_code=status.HTTP_200_OK, content=basket["items"])

# Egy kosár termékeinek összegének lekérdezése
@routers.get('/getusertotal', response_model=float, summary="Kosárban található termékek összegének lekérdezése", tags=["Bag"])
def getusertotal(userid: int) -> float:
    """
    Egy felhasználó kosarában található termékek összegének lekérdezése.
    """
    user = get_user_by_id(userid)
    if user is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="A felhasználó nem található!")
    
    basket = get_basket_by_user_id(userid)
    if basket is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="A felhasználónak nincs kosara!")
    
    sum_of_bag = get_total_price_of_basket(userid)

    return JSONResponse(status_code=status.HTTP_200_OK, content=sum_of_bag)