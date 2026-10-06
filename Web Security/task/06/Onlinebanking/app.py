import random
import string

from flask import Flask, request, redirect, make_response, render_template, abort

app = Flask(__name__)

users = {
    "alice": {"password": "alice123", "balance": 1000},
    "bob": {"password": "bob123", "balance": 1000},
    "eve": {"password": "eve123", "balance": -10},
}

sessions = {}

def get_random_string(k=16):
    return ''.join(random.choices(string.ascii_letters + string.digits, k=k))

def set_session(user):
    session = get_random_string()
    sessions[session] = {"user": user, "csrf": get_random_string(32)}
    return session

def get_session(session_id):
    return sessions.get(session_id)

def delete_session(session_id):
    sessions.pop(session_id, None)

@app.route("/", methods=["GET", "POST"])
def login():
    error = ""
    if request.method == "POST":
        username = request.form.get("username").lower()
        password = request.form.get("password")
        if username in users and users[username]["password"] == password:
            resp = make_response(redirect("/dashboard"))
            session_id = set_session(username)
            resp.set_cookie("session_id", session_id, httponly=True, samesite="Strict")
            return resp
        else:
            error = "Benutzername oder Passwort falsch"
    return render_template("login_page.html", error=error)

@app.route("/dashboard")
def dashboard():
    s = get_session(request.cookies.get("session_id"))
    if not s:
        return redirect("/")
    return render_template("dashboard_page.html", user=s["user"],
                           balance=users[s["user"]]["balance"], csrf_token=s["csrf"])

@app.route("/transfer", methods=["POST"])
def transfer():
    s = get_session(request.cookies.get("session_id"))
    if not s:
        return redirect("/")

    if request.form.get("csrf_token") != s["csrf"]:
        abort(403)

    user = s["user"]
    recipient = request.form.get("to", "").lower()
    amount = request.form.get("amount", type=int)
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
