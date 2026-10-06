import flask
from flask import request, send_file, abort

app = flask.Flask(__name__)

@app.route('/')
def index():
    file = request.args.get("file")
    if not file:
        return "<h1>Keine Datei angegeben</h1>"
    if not file.startswith("files/"):
        abort(403)
    return send_file(file)

if __name__ == '__main__':
    app.run(host='0.0.0.0')
