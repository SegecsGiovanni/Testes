import json
import os
from typing import Literal, Optional
from uuid import uuid4
from fastapi import FastAPI, HTTPException
import random
from fastapi.encoders import jsonable_encoder
from pydantic import BaseModel, Field

app = FastAPI()

class Book(BaseModel):
    name: str
    price: float
    book_id: Optional[str] = Field(default_factory=lambda: uuid4().hex)
    genre : Literal["fiction", "non-fiction"]

BOOKS_FILE = "books.json"

BOOK_DB = []

if os.path.exists(BOOKS_FILE):
    with open(BOOKS_FILE, "r") as f:
        BOOK_DB = json.load(f)


# / -> boas vindas
@app.get("/")
async def home():
    return {"Welcome to my bookstore!"}

# / list-books -> lista de livros
@app.get("/list-books")
async def list_books():
    return {"books": BOOK_DB}

# /list-book-by-index/{index} -> listar 1 livro
@app.get("/list-book-by-index/{index}")
async def list_book_by_index(index: int):
    if index < 0 or index >= len(BOOK_DB):
        raise HTTPException(404, "Index out of range")
    else:
        return {"books": BOOK_DB[index]}

# /get-random-book -> listar 1 livro aleatório
@app.get("/get-random-book")
async def get_random_book():
    book = random.choice(BOOK_DB)
    return {"book": book}

# /add-book -> adicionar 1 livro
@app.post("/add-book")
async def add_book(book: Book):
    book.book_id = uuid4().hex
    json_book = jsonable_encoder(book)
    BOOK_DB.append(json_book)
    with open(BOOKS_FILE, "w") as f:
        json.dump(BOOK_DB, f)
    return {"Message": f'Book {book.name} was added successfully.'}