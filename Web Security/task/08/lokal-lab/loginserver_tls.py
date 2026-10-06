import http.server, socketserver, ssl
HTML=b'<form method="post" action="/login"><input name="user"><input type="password" name="password"><button>Login</button></form>'
class H(http.server.BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200); self.send_header("Content-Type","text/html"); self.end_headers(); self.wfile.write(HTML)
    def do_POST(self):
        n=int(self.headers.get("Content-Length",0)); b=self.rfile.read(n)
        self.send_response(200); self.end_headers(); self.wfile.write(b"ok "+b)
    def log_message(self,*a): pass
ctx=ssl.SSLContext(ssl.PROTOCOL_TLS_SERVER); ctx.load_cert_chain("cert.pem","key.pem")
socketserver.TCPServer.allow_reuse_address=True
with socketserver.TCPServer(("0.0.0.0",443),H) as s:
    s.socket=ctx.wrap_socket(s.socket,server_side=True); s.serve_forever()
