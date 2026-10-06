import flask
from flask import request

import ShowDate
from PrettyPrint import PrettyPrint
import HandleLogin

app = flask.Flask(__name__)

print("\n-----------------------------\n"
      "|  Viel Spaß im Praktikum!  |"
      "\n-----------------------------\n")

@app.route('/', methods=['GET', 'POST'])
def login():
    if (request.method == 'POST'):
        return HandleLogin.credentials_given(request.form['username'],
                                             request.form['password'])

    if (request.method == 'GET'
            and 'username' in request.args
            and 'password' in request.args):
        return HandleLogin.credentials_given(request.args.get('username'),
                                             request.args.get('password'))

    else:
        return HandleLogin.show_login()

@app.route('/time')
def show_date():
    return ShowDate.show_date(request.args.get('format'))

@app.route('/info')
def info():
    first_line = "<b>Folgende Argumente wurden übergeben: </b><br>"
    params = PrettyPrint(request.args.to_dict())
    secondLine = "<b>Folgende Cookies wurden gesendet: </b> <br>"
    cookies = PrettyPrint(request.cookies.to_dict())
    thirdLine = "<b>Folgende Header wurden übertragen: </b> <br>"
    all = PrettyPrint(request.headers)

    return str(first_line) + str(params) + secondLine + str(cookies) + thirdLine + str(all)

if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=True)
