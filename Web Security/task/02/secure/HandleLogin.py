import sqlite3


def credentials_given(username, password):
    con = sqlite3.connect("users.db")
    cur = con.cursor()

    query_string = "SELECT * FROM user WHERE username = ? AND password = ?"
    cur.execute(query_string, (username, password))
    result = cur.fetchall()

    if not result:
        return "<h1>Login nicht moeglich</h1>"
    else:
        return "<h1>Hallo Benutzer " + result[0][0] + "</h1>"


def show_login():
    return ("<h1>Willkommen. Bitte einloggen:</h1><br>"
            "<form action='/' method='post'>"
            "Benutzername: <input type='text' name='username' value='' />"
            "Password: <input type='password' name='password' value='' />"
            "<button type='submit'>Login</button>"
            "</form>")
