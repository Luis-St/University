import pathlib

import flask
from flask import request, send_file

app = flask.Flask(__name__)

@app.route('/')
def index():
    if not request.args.get("file"):
        return generate_list()
    else:
        print("Zeige "+request.args.get("file")+" im Browser an...")
        return send_file(request.args.get("file"))


def generate_list():
    result_string = ("<html>"
                     "<body>"
                     "<h1>Vorhandene Dateien:</h1>"
                     "<ul>")

    file_iterator = pathlib.Path("files/")
    for file in file_iterator.iterdir():
        if file.is_file():
            result_string += ("<li> <a href=/?file=" +
                              str(file) + ">" + str(file.name) +
                              " öffnen</a> </li>")

    result_string += ("</ul>"
                      "</body>"
                      "</html>")
    return result_string

if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=True)