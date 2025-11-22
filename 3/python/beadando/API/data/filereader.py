import json
from typing import Dict, Any, List

'''
Útmutató a féjl használatához:

Felhasználó adatainak lekérdezése:

user_id = 1
user = get_user_by_id(user_id)
print(f"Felhasználó adatai: {user}")

Felhasználó kosarának tartalmának lekérdezése:

user_id = 1
basket = get_basket_by_user_id(user_id)
print(f"Felhasználó kosarának tartalma: {basket}")

Összes felhasználó lekérdezése:

users = get_all_users()
print(f"Összes felhasználó: {users}")

Felhasználó kosarában lévő termékek összárának lekérdezése:

user_id = 1
total_price = get_total_price_of_basket(user_id)
print(f"A felhasználó kosarának összára: {total_price}")

Hogyan futtasd?

Importáld a függvényeket a filehandler.py modulból:

from filereader import (
    get_user_by_id,
    get_basket_by_user_id,
    get_all_users,
    get_total_price_of_basket
)

 - Hiba esetén ValuErrort kell dobni, lehetőség szerint ezt a 
   kliens oldalon is jelezni kell.

'''

# A JSON fájl elérési útja
USERS_FILE_PATH = "data/users.json"
DATA_FILE_PATH = "data/data.json"

# változatás: path megadása, mert 2 json fájl van
def load_json(path: str) -> Dict[str, Any]:
    with open(path, "r", encoding="utf-8") as f:
        data = json.load(f)

    return data

def get_user_by_id(user_id: int) -> Dict[str, Any]:
    data = load_json(USERS_FILE_PATH)

    result = next((u for u in data if  u["id"] == user_id), None)

    return result

def get_basket_by_user_id(user_id: int) -> List[Dict[str, Any]]:
    data = load_json(DATA_FILE_PATH)

    result = next((b for b in data if  b["user_id"] == user_id), None)

    return result

def get_all_users() -> List[Dict[str, Any]]:
    data = load_json(USERS_FILE_PATH)

    return data

def get_total_price_of_basket(user_id: int) -> float:
    user = get_user_by_id(user_id)
    if user is None:
        raise ValueError("A felhasználó nem található!")
    
    basket = get_basket_by_user_id(user_id)
    if basket is None:
        raise ValueError("A felhasználónak nincs kosara!")
    
    result = sum(item["price"] for item in basket["items"])

    return result
    
def verify_token(given_token) -> bool:
    with open("data/token.txt", "r", encoding="utf-8") as f:
        token = f.read()

    return given_token == token