from flask import Flask, request


app = Flask(__name__)

getUID = {
    "accion": "uuid",
    "franquiciaID": "1234"
}

# noinspection PyInterpreter
changeMenu = {
    "accion": "menu",
    "listado": [
        {
        "nombre": "menu1",
        "descripcion": "Descripcion del menu 1, me encanta el fulbo",
        "precio": 500.4,
        "urlImagen": "http://imagen.com/img1"
        }
    ]
}


@app.post('/api/consulta')
def consulta():
    content_type = request.headers.get('Content-Type')
    if (content_type == 'application/json'):
        json = request.get_json()
        print(json)
        if json['franquiciaID'] == '1234':
            return changeMenu
        return 'Failed to auth'

@app.post('/api/authenticate/operacionesPOST')
def getByPostUuid():
    content_type = request.headers.get('Content-Type')
    if (content_type == 'application/json'):
        json = request.get_json()
        print(json)
        return getUID

if __name__ == '__main__':
    #app.create_app()
    app.run(debug=True)

