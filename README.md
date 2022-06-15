# Set up database

```
docker run --name postgres-server -e POSTGRES_PASSWORD=123 -d -p 5433:5432 postgres

pgcli postgres://postgres:123@localhost:5433/
```
