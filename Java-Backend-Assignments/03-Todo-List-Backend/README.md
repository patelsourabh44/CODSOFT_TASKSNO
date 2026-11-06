# To-Do List Backend

## Endpoints
- `POST /api/tasks` — create task
- `GET /api/tasks` — list tasks
- `GET /api/tasks?status=PENDING&page=0&size=10` — filter by status
- `GET /api/tasks?search=assignment&sortBy=dueDate&direction=asc` — search and sort
- `GET /api/tasks/{id}` — retrieve task
- `PUT /api/tasks/{id}` — update task
- `PATCH /api/tasks/{id}/status?status=COMPLETED` — mark completed/pending
- `DELETE /api/tasks/{id}` — delete task

### Example JSON
```json
{
  "title":"Complete Spring Boot assignment",
  "description":"Implement CRUD endpoints and test with Postman",
  "status":"PENDING",
  "priority":"HIGH",
  "dueDate":"2026-11-15"
}
```

Status values: `PENDING`, `COMPLETED`. Priority values: `LOW`, `MEDIUM`, `HIGH`.
This implementation includes the task bonus fields due date and priority. Authentication is not included;
it can be added as a separate enhancement if required by the evaluator.
