from traceback import print_tb
from flask import Flask, request


app = Flask(__name__)

getUID = {
    "accion": "uuid",
    "grupo": "Grupo 2 - Sanchez Toledo Mariano, Barroso Oriel"
}



changeMenu = {
    "accion": "menu",
    "listado": [
        {
        "nombre": "menu1",
        "descripcion": "Descripcion del menu 1",
        "precio": 750.4,
        "urlImagen": "http://imagen.com/img1"
        },
        {
        "nombre": "menu2",
        "descripcion": "Descripcion del menu 2",
        "precio": 650.4,
        "urlImagen": "http://imagen.com/img2"
        },
        {
        "nombre": "menu3",
        "descripcion": "Descripcion del menu 3",
        "precio": 850.4,
        "urlImagen": "http://imagen.com/img3"
        }
    ]
}


@app.get('/api/authenticate/operaciones')
def getUuid():
    return getUID


@app.post('/api/authenticate/operacionesPOST')
def getByPostUuid():
    content_type = request.headers.get('Content-Type')
    if (content_type == 'application/json'):
        json = request.get_json()
        print(json)
        return getUID