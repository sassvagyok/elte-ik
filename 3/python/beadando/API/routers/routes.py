from schemas.schema import User, Basket, Item
from fastapi.responses import JSONResponse, RedirectResponse
from fastapi import FastAPI, HTTPException, Request, Response, Cookie, Header, status
from fastapi import APIRouter
from data.filereader import (
    get_user_by_id,
    get_basket_by_user_id,
    get_all_users,
    get_total_price_of_basket,
    read_token
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

@routers.post('/adduser', response_model=User)
def adduser(user: User, api_token: str | None = Header(default=None)) -> User:
    verify_token(api_token)
    
    add_user(user.model_dump())

    return JSONResponse(status_code=status.HTTP_201_CREATED, content=user.model_dump())

@routers.post('/addshoppingbag', response_model=str)
def addshoppingbag(userid: int, api_token: str | None = Header(default=None)) -> str:
    verify_token(api_token)

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

@routers.post('/additem', response_model=Basket)
def additem(userid: int, item: Item) -> Basket:
    user = get_user_by_id(userid)
    if user is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="A felhasználó nem található!")
    
    basket = get_basket_by_user_id(userid)
    if basket is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="A felhasználónak nincs kosara!")
    
    add_item_to_basket(userid, item.model_dump())

    basket = get_basket_by_user_id(userid)

    return JSONResponse(status_code=status.HTTP_201_CREATED, content=basket)


@routers.put('/updateitem', response_model=Basket)
def updateitem(userid: int, itemid: int, updateItem: Item) -> Basket:
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

@routers.delete('/deleteitem', response_model=Basket)
def deleteitem(userid: int, itemid: int) -> Basket:
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

@routers.get('/user', response_model=User)
def user(userid: int, api_token: str | None = Header(default=None)) -> User:
    verify_token(api_token)
    user = get_user_by_id(userid)

    if user is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Felhasználó nem található!")

    return JSONResponse(status_code=status.HTTP_200_OK, content=user)

@routers.get('/users', response_model=list[User])
def users(api_token: str | None = Header(default=None)) -> list[User]:
    verify_token(api_token)
    users = get_all_users()

    if len(users) == 0:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Nincsenek felhasználók!")

    return JSONResponse(status_code=status.HTTP_200_OK, content=users)

@routers.get('/shoppingbag', response_model=list[Item])
def shoppingbag(userid: int) -> list[Item]:
    user = get_user_by_id(userid)
    if user is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="A felhasználó nem található!")
    
    basket = get_basket_by_user_id(userid)
    if basket is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="A felhasználónak nincs kosara!")
    
    return JSONResponse(status_code=status.HTTP_200_OK, content=basket["items"])

@routers.get('/getusertotal', response_model=float)
def getusertotal(userid: int) -> float:
    user = get_user_by_id(userid)
    if user is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="A felhasználó nem található!")
    
    basket = get_basket_by_user_id(userid)
    if basket is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="A felhasználónak nincs kosara!")
    
    sum_of_bag = sum(basket["items"])

    return JSONResponse(status_code=status.HTTP_200_OK, content=sum_of_bag)    

def verify_token(api_token: str | None = Header(default=None)):
    if api_token is None:
        raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail="Hiányzó token!")
    
    server_token = read_token()
    if api_token != server_token:
        raise HTTPException(status_code=status.HTTP_403_FORBIDDEN, detail="Érvénytelen token!")
    return True

