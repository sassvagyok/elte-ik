import json
from typing import Dict, Any
import os
from filereader import (
    get_user_by_id,
    get_basket_by_user_id,
    get_all_users,
    get_total_price_of_basket
)
from schemas.schema import User, Basket, Item

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

def load_json(path: str) -> Dict[str, Any]:
    with open(path, "r", encoding="utf-8") as f:
        data = json.load(f)

    return data

def save_json(path: str, data: Dict[str, Any]) -> None:
    with open(path, "w", encoding="utf-8") as f:
        json.dump(data, f, indent=4)

def add_user(user: Dict[str, Any]) -> None:
    data = load_json(USERS_FILE_PATH)

    if any(u["id"] == user["id"] for u in data):
        raise ValueError("Ilyen azonosítójú felhasználó már létezik!")

    data.append(user)

    save_json(USERS_FILE_PATH, data)

def add_basket(basket: Dict[str, Any]) -> None:
    data = load_json(DATA_FILE_PATH)

    if any(u["id"] == basket["id"] for u in data):
        raise ValueError("Ilyen azonosítójú kosér már létezik!")
    
    if any(u["user_id"] == basket["user_id"] for u in data):
        raise ValueError("Ennek a felhasználónak már van kosara!")
    
    data.append(basket)

    save_json(DATA_FILE_PATH, data)

def add_item_to_basket(user_id: int, item: Dict[str, Any]) -> None:
    data = load_json(DATA_FILE_PATH)

    user = get_user_by_id(user_id)
    if user is None:
        raise ValueError("A felhasználó nem található!")
    
    basket = get_basket_by_user_id(user_id)

    if basket is None:
        raise ValueError("A felhasználónak nincs kosara!")

    existing_item = next((i for i in basket["items"] if i["item_id"] == item["item_id"]), None)
    
    if existing_item:
        existing_item["quantity"] += item.get("quantity", 1)
    else:
        basket["items"].append(item)
    
    save_json(DATA_FILE_PATH, data)

def update_item(user_id: int, item_id: int, updateItem: Item) -> None:
    data = load_json(DATA_FILE_PATH)

    user = get_user_by_id(user_id)
    if user is None:
        raise ValueError("A felhasználó nem található!")
    
    basket = get_basket_by_user_id(user_id)

    if basket is None:
        raise ValueError("A felhasználónak nincs kosara!")

    item = next((i for i in basket["items"] if i["item_id"] == item_id), None)
    
    if item:
        item["name"] = updateItem.name
        item["brand"] = updateItem.brand
        item["price"] = updateItem.price
        item["quantity"] = updateItem.quantity
    else:
        raise ValueError("A termék nincs a kosárban!")
    
    save_json(DATA_FILE_PATH, data)

def delete_item(user_id: int, item_id: int) -> None:
    data = load_json(DATA_FILE_PATH)

    user = get_user_by_id(user_id)
    if user is None:
        raise ValueError("A felhasználó nem található!")
    
    basket = get_basket_by_user_id(user_id)

    if basket is None:
        raise ValueError("A felhasználónak nincs kosara!")

    item = next((i for i in basket["items"] if i["item_id"] == item_id), None)
    
    if item:
        basket["items"].remove(item)
    else:
        raise ValueError("A termék nincs a kosárban!")
    
    save_json(DATA_FILE_PATH, data)