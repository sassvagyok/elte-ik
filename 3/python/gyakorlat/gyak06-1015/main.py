from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, Field, field_validator

app = FastAPI()
# futtatás: fájlnév:változónév
# python -m uvicorn main:app --reload

class Book(BaseModel):
    # ... == kötelező megadni
    author : str = Field(..., min_length=2)
    title : str
    page : int
    id : int

    # @field_validator("page")
    # def page_validator(page):
    #     if page < 0:
    #         raise HTTPException(status_code=400, detail="Hibás oldalszám!")

lst = [
        Book(id=0, author="Tolkien", title="Gyuruk ura", page=600),
        Book(id=1, author="Second Tolkien", title="Gyuruk ura 2", page=700)
    ]

@app.get("/")
def welcome():
    return {"message": "hello world"}

@app.get("/items")
def get_items():
    return lst

@app.get("/items/{item_id}")
def get_item(item_id : int):
    return lst[item_id]