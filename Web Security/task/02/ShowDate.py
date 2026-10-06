import os

def show_date(format_string):
    result ="<html><h1> Darstellung der Systemzeit: </h1>"
    if not format_string:
        result += os.popen("date").read()
    else:
        result += "<h2>(Aufruf von: date +"
        result += "<var>" + format_string + "</var>)</h2><br>"

        cmdlines = os.popen("date +"+format_string)
        for line in cmdlines:
            result += line+"<br>"

    result += get_format_hints()
    return result

def get_format_hints():
    return """
    <p> Mit dem GET-Parameter <b><kbd>format</kbd></b> können Sie einen 
    eigenen Formatierungsstring an den Befehl <kbd> date </kbd> senden.</p>
    <p><b>Beispiel:</b> /time?%H:%m, um Stunden ":" Minuten anzuzueigen</p>
    <p> Formatierungsoptionen finden Sie, wenn Sie <kbd>man date</kbd> in einer
    Linux-Console eingeben und nach unten scrollen. Versuchen Sie %H:%m:%S </p>
    """