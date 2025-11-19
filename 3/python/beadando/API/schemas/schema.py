from pydantic import BaseModel, field_validator
import re

'''

Útmutató a fájl használatához:

Az osztályokat a schema alapján ki kell dolgozni.

A schema.py az adatok küldésére és fogadására készített osztályokat tartalmazza.
Az osztályokban az adatok legyenek validálva.
 - az int adatok nem lehetnek negatívak.
 - az email mező csak e-mail formátumot fogadhat el.
 - Hiba esetén ValuErrort kell dobni, lehetőség szerint ezt a 
   kliens oldalon is jelezni kell.

'''

ShopName='Bolt'

class User(BaseModel):
    id: int
    name: str
    email: str

    @field_validator('id')
    @classmethod
    def id_must_be_positive(cls, v: int) -> int:
        if v < 0:
            raise ValueError("Az id nem lehet negatív!")
        return v

    @field_validator('email')
    @classmethod
    def email_must_be_valid(cls, v: str) -> str:
        if not re.match(r"[^@]+@[^@]+\.[^@]+", v):
            raise ValueError("A megadott email nem jó formátumú!")
        return v

class Item(BaseModel):
    item_id: int
    name: str
    brand: str
    price: float
    quantity: int

    @field_validator('item_id')
    @classmethod
    def id_must_be_positive(cls, v: int) -> int:
        if v < 0:
            raise ValueError("Az id nem lehet negatív!")
        return v
    
    @field_validator('price')
    @classmethod
    def price_must_be_positive(cls, v: float) -> float:
        if v < 0:
            raise ValueError("Az ár nem lehet negatív!")
        return v
    
    @field_validator('quantity')
    @classmethod
    def quantity_must_be_positive(cls, v: int) -> int:
        if v < 0:
            raise ValueError("A mennyiség nem lehet negatív!")
        return v

class Basket(BaseModel):
    id: int
    user_id: int
    items: list[Item]

    @field_validator('id')
    @classmethod
    def id_must_be_positive(cls, v: int) -> int:
        if v < 0:
            raise ValueError("Az id nem lehet negatív!")
        return v
    
    @field_validator('user_id')
    @classmethod
    def user_id_must_be_positive(cls, v: int) -> int:
        if v < 0:
            raise ValueError("A user_id nem lehet negatív!")
        return v