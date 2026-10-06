from flask import Flask, render_template, request, redirect, make_response
import json
from datetime import datetime

app = Flask(__name__)

DATA_FILE = "entries.json"

COOKIE_NAME = "secretcookie"
PASSWORD= "password123"
COOKIE_SECRET = "itsasecret"

def load_entries():
    try:
        with open(DATA_FILE, "r", encoding="utf-8") as f:
            return json.load(f)
    except:
        return []


def save_entries(entries):
    with open(DATA_FILE, "w", encoding="utf-8") as f:
        json.dump(entries, f, indent=4, ensure_ascii=False)

def test_cookie():
    cookie_content = request.cookies.get(COOKIE_NAME)
    if cookie_content == COOKIE_SECRET:
        return True
    else:
        return False

def process_post_entry():
    entries = load_entries()

    if request.method == "POST":
        name = request.form.get("name")
        message = request.form.get("message")

        if name and message:
            entries.insert(0, {
                "name": name,
                "message": message,
                "date": datetime.now().strftime("%d.%m.%Y %H:%M")
            })
            save_entries(entries)

        return redirect("/")
    return None

@app.route("/login", methods=["GET", "POST"])
def login():
    if request.method == "POST":
        password = request.form.get("password")
        if password == PASSWORD:
            resp = make_response(redirect("/"))
            resp.set_cookie(COOKIE_NAME, COOKIE_SECRET)
            return resp
        else:
            resp = make_response(redirect("/login"))
            resp.delete_cookie(COOKIE_NAME)
    else:
        resp = make_response(render_template("login.html"))
        resp.delete_cookie(COOKIE_NAME)
    return resp

@app.route("/", methods=["GET", "POST"])
def index():

    if not test_cookie():
        return make_response(redirect("/login"))

    process_post_entry()
    resp = make_response(render_template("index.html", entries=load_entries()))
    resp.set_cookie("lastaccess",datetime.now().strftime("%H:%M am %d. %B %Y"))

    return resp


if __name__ == "__main__":
    app.run(host="0.0.0.0", debug=True)
