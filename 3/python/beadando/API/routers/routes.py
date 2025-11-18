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

@routers.post('/addshoppingbag')
def addshoppingbag(userid: int, api_token: str | None = Header(default=None)) -> str:
    verify_token(api_token)

    add_basket()

@routers.post('/additem', response_model=Basket)
def additem(userid: int, item: Item, api_token: str | None = Header(default=None)) -> Basket:
    pass

@routers.put('/updateitem')
def updateitem(userid: int, itemid: int, updateItem: Item) -> Basket:
    pass

@routers.delete('/deleteitem')
def deleteitem(userid: int, itemid: int) -> Basket:
    pass

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

@routers.get('/shoppingbag')
def shoppingbag(userid: int) -> list[Item]:
    pass

@routers.get('/getusertotal')
def getusertotal(userid: int) -> float:
    pass

def verify_token(api_token: str | None = Header(default=None)):
    if api_token is None:
        raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail="Hiányzó token!")
    
    server_token = read_token()
    if api_token != server_token:
        raise HTTPException(status_code=status.HTTP_403_FORBIDDEN, detail="Érvénytelen token!")
    return True

