

https://github.com/typicode/json-server

```bash
npm install -g json-server

# restore the data
cp db-bak.json db.json

json-server --watch db.json --port 3001
```

Home:
- <http://localhost:3001/>

Routes:

```bash
GET    /posts
GET    /posts/1
POST   /posts
PUT    /posts/1
PATCH  /posts/1
DELETE /posts/1

GET /posts/1/comments
```

Example;

```bash
http :3001/posts/1
```