import os
import sqlite3

if not os.path.isfile("users.db"):
    print("Erstelle Benutzerdatenbank")

    con = sqlite3.connect("users.db")
    cur = con.cursor()
    cur.execute("CREATE TABLE user(username, password, email, address)")
    cur.execute("""
        INSERT INTO user (username, password, email, address) VALUES 
        ('admin', 'password123', 'admin@thecompany.org', '123 Admin St, Admin Town'),
        ('alice', 'IamAlice', 'alice@wonder.lands', '1 Rabbit Hole, Fantasy Town'),
        ('bob', 'qwerty', 'bob@sideshow.org', '42 Nowhere Blvd, Springfield'),
        ('eve', 'evil', 'info@evilcorp.com', '666 Evil Avenue, Bad Land'),
        ('ttp', 'ITS4ever', 'trust@ttp.me', '1 Trust Blvd, Trust Town');
        """)
    con.commit()
    print("Benutzerdatenbank mit folgenden Einträgen erstellt:")
    res = cur.execute("SELECT * FROM user")
    result = res.fetchall()
    print(result)

    con.close()
else:
    print ("Existierende SQLite Benutzerdatenbank verwenden")

