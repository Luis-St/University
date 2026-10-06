def pretty_print_dict(d):
    """Show each value of a dict in a new line"""
    output: str = "<table><tr>"
    for key, value in d.items():
        output += "<tr><td>"+str(key) + "</td><td>" + str(value) + "</td></tr>"
    return output+"</table>"


class PrettyPrint:
    """Show each value in a new line within a browser"""
    def __init__(self,data):
        self.data = data

    def __str__(self):
        return pretty_print_dict(self.data)

