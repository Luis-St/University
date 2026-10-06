import subprocess

def show_date(format_string):
    result = "<html><h1> Darstellung der Systemzeit: </h1>"
    if not format_string:
        result += subprocess.run(["date"], capture_output=True, text=True).stdout
    else:
        proc = subprocess.run(["date", "+" + format_string],
                              capture_output=True, text=True)
        result += "<br>" + proc.stdout + "<br>"
    return result
