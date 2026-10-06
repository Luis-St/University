import sqlite3


def credentials_given(username, password):
    con = sqlite3.connect("users.db")
    cur = con.cursor()

    query_string = ("SELECT * FROM user WHERE "
                    "username = '" + username + "' AND password = '" + password + "'")

    cur.execute(query_string)

    result = cur.fetchall()

    if not result:
        return ("<h1>Login nicht möglich</h1> <br>"
                "<h2> Datenbankanfrage war: </h2>") + query_string
    else:
        return (
                "Datenbankanfrage war: " + query_string +
                "<h1>Hallo Benutzer " + result[0][0]+"</h1>"+
                "<h2>Folgende Daten sind über Sie gespeichert:</h2> <br>" +
                str(result))


def show_login():
    return ("<h1>Willkommen. Bitte einloggen:</h1><br>"
            "<form action='/' method='get'>"
            "Benutzername: <input type='text' name='username' value='' />"
            "Password: <input type='password' name='password' value='' />"
            "<button type='submit'>Login</button>"
            "</form>")
