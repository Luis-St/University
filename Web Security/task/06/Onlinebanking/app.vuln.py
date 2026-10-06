import random
import string

from flask import Flask, request, redirect, make_response, render_template

app = Flask(__name__)

users = {
    "alice": {"password": "alice123", "balance": 1000},
    "bob": {"password": "bob123", "balance": 1000},
    "eve": {"password": "eve123", "balance": -10},
}

sessions = {}

def get_random_string():
    """ Generiert einen zufälligen, 16-stelligen String """
    return ''.join(random.choices(string.ascii_letters + string.digits, k=16))

def set_session(user):
    """ Erstellt und gibt eine Sitzung-ID für einen user zurück """
    session = get_random_string()
    sessions[session] = user
    return session

def test_session(session_id):
    """ Gibt den Benutzernamen einer aktiven Sitzung oder None zurück """
    return sessions.get(session_id)

def delete_session(session_id):
    """ Lösche eine Sitzung aus dem Dictionary """
    del sessions[session_id]

@app.route("/", methods=["GET", "POST"])
def login():
    error = ""
    if request.method == "POST":
        username = request.form.get("username").lower()
        password = request.form.get("password")

        if username in users and users[username]["password"] == password:
            resp = make_response(redirect("/dashboard"))
            session_id = set_session(username)
            resp.set_cookie("session_id",session_id)
            return resp
        else:
            error = "Benutzername oder Passwort falsch"

    return render_template("login_page.html", error=error)

@app.route("/dashboard")
def dashboard():
    user = test_session(request.cookies.get("session_id"))
    if not user or user not in users:
        return redirect("/")

    return render_template(
        "dashboard_page.html",
        user=user,
        balance=users[user]["balance"]
    )

@app.route("/transfer")
def transfer():
    """ Die Methode, um Geld zu überweisen """
    user = test_session(request.cookies.get("session_id"))
    if not user or user not in users:
        return redirect("/")

    import sys
    for _h in ["Sec-Fetch-Site","Sec-Fetch-Mode","Sec-Fetch-Dest","Sec-Fetch-User","Referer","Cookie"]:
        print("HDR %s: %s" % (_h, request.headers.get(_h)), file=sys.stderr, flush=True)
    recipient = request.args.get("to").lower()
    amount = request.args.get("amount", type=int)

    if recipient in users and amount and amount > 0:
        if users[user]["balance"] >= amount:
            users[user]["balance"] -= amount
            users[recipient]["balance"] += amount

    return redirect("/dashboard")

@app.route("/logout")
def logout():
    delete_session(request.cookies.get("session_id"))
    resp = make_response(redirect("/"))
    resp.delete_cookie("session_id")
    return resp


if __name__ == "__main__":
    app.run(host="0.0.0.0", debug=True, port=5001)
