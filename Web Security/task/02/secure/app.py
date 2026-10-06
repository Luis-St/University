import flask
from flask import request

import ShowDate
from PrettyPrint import PrettyPrint
import HandleLogin

app = flask.Flask(__name__)

@app.route('/', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        return HandleLogin.credentials_given(request.form['username'],
                                             request.form['password'])
    return HandleLogin.show_login()

@app.route('/time')
def show_date():
    return ShowDate.show_date(request.args.get('format'))

if __name__ == '__main__':
    app.run(host='0.0.0.0')
