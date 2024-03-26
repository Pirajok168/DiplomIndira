from flask import *
yourApi = "" ## Поставь сюда свой API
app = Flask(__name__)

@app.route("/helloWorld")
def hello_world():
    return "<p>Hello, World!</p>"

@app.route("/uploadImage", methods=['POST'])
def upload_image():
    request.files ## В этом массиве изображения, которые отпрвляешь с мобилки
    return "<p>Hello, World!</p>"

if __name__ == "__main__":
    app.run(debug=True, port=8080, host=yourApi)