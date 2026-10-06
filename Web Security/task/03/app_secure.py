import os
import flask
from flask import request, send_file, abort

app = flask.Flask(__name__)

BASE_DIR = os.path.realpath("files")

@app.route('/')
def index():
    file = request.args.get("file")
    if not file:
        return "<h1>Keine Datei angegeben</h1>"

    requested = os.path.realpath(file)

    if requested != BASE_DIR and not requested.startswith(BASE_DIR + os.sep):
        abort(403)
    return send_file(requested)

if __name__ == '__main__':
    app.run(host='0.0.0.0')
