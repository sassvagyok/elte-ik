import json
from typing import Dict, Any
import os

'''
Útmutató a fájl függvényeinek a használatához

Új felhasználó hozzáadása:

new_user = {
    "id": 4,  # Egyedi felhasználó azonosító
    "name": "Szilvás Szabolcs",
    "email": "szabolcs@plumworld.com"
}

Felhasználó hozzáadása a JSON fájlhoz:

add_user(new_user)

Hozzáadunk egy új kosarat egy meglévő felhasználóhoz:

new_basket = {
    "id": 104,  # Egyedi kosár azonosító
    "user_id": 2,  # Az a felhasználó, akihez a kosár tartozik
    "items": []  # Kezdetben üres kosár
}

add_basket(new_basket)

Új termék hozzáadása egy felhasználó kosarához:

user_id = 2
new_item = {
    "item_id": 205,
    "name": "Szilva",
    "brand": "Stanley",
    "price": 7.99,
    "quantity": 3
}

Termék hozzáadása a kosárhoz:

add_item_to_basket(user_id, new_item)

Hogyan használd a fájlt?

Importáld a függvényeket a filehandler.py modulból:

from filehandler import (
    add_user,
    add_basket,
    add_item_to_basket,
)

 - Hiba esetén ValuErrort kell dobni, lehetőség szerint ezt a 
   kliens oldalon is jelezni kell.

'''

# A JSON fájl elérési útja
USERS_FILE_PATH = os.path.join(os.path.dirname(__file__), "users.json")
DATA_FILE_PATH = os.path.join(os.path.dirname(__file__), "data.json")

def load_users_json() -> Dict[str, Any]:
    with open(USERS_FILE_PATH, "r", encoding="utf-8") as f:
        data = json.load(f)

    return data

def save_users_json(data: Dict[str, Any]) -> None:
    with open(USERS_FILE_PATH, "w", encoding="utf-8") as f:
        json.dump(data, f, indent=4)

def load_data_json() -> Dict[str, Any]:
    with open(DATA_FILE_PATH, "r", encoding="utf-8") as f:
        data = json.load(f)

    return data

def save_data_json(data: Dict[str, Any]) -> None:
    with open(DATA_FILE_PATH, "w", encoding="utf-8") as f:
        json.dump(data, f, indent=4)

def add_user(user: Dict[str, Any]) -> None:
    data = load_users_json()

    if any(u["id"] == user["id"] for u in data):
        raise ValueError("Ilyen azonosítójú felhasználó már létezik!")

    data.append(user)

    save_users_json(data)

def add_basket(basket: Dict[str, Any]) -> None:
    data = load_data_json()

    if any(u["id"] == basket["id"] for u in data):
        raise ValueError("Ilyen azonosítójú kosér már létezik!")
    
    if any(u["user_id"] == basket["user_id"] for u in data):
        raise ValueError("Ennek a felhasználónak már van kosara!")
    
    data.append(basket)

    save_data_json(data)

def add_item_to_basket(user_id: int, item: Dict[str, Any]) -> None:
    pass
