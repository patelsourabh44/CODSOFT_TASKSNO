# Student Record Management API

## Endpoints
- `POST /api/students` — create student
- `GET /api/students?search=amit&page=0&size=10&sortBy=name&direction=asc` — search/list students
- `GET /api/students/{id}` — get student
- `PUT /api/students/{id}` — update student
- `DELETE /api/students/{id}` — delete student
- `POST /api/courses`, `GET /api/courses`, `GET /api/courses/{id}`, `PUT /api/courses/{id}`, `DELETE /api/courses/{id}`
- `POST /api/enrollments` — body: `{"studentId":1,"courseId":1}`
- `GET /api/enrollments`
- `DELETE /api/enrollments/{id}`

### Student JSON
```json
{"name":"Aarav Sharma","email":"aarav@example.com","phone":"9876543210","department":"Computer Science"}
```
### Course JSON
```json
{"title":"Database Systems","code":"CS301","description":"Relational database fundamentals","credits":4}
```
