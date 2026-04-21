# Installation

## Local

```bash
docker compose up --build
```

## Production (Hetzner VPS)

```bash
cp .env.example .env
# edit .env — set CORS_ORIGIN and VITE_API_URL to your domain
docker compose up --build -d
```

Add `nginx.conf` to your nginx configuration and reload:

```bash
sudo ln -s /etc/nginx/sites-available/cookbook /etc/nginx/sites-enabled/
sudo nginx -t && sudo systemctl reload nginx
```
