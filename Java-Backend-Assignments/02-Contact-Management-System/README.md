# Contact Management System

## Endpoints
- `POST /api/contacts` — create a contact
- `GET /api/contacts` — list contacts
- `GET /api/contacts?search=arun&page=0&size=10&sortBy=name&direction=asc` — search, sort and paginate
- `GET /api/contacts/{id}` — retrieve one contact
- `PUT /api/contacts/{id}` — update contact
- `DELETE /api/contacts/{id}` — delete contact

### Example JSON
```json
{
  "name":"Arun Verma",
  "email":"arun@example.com",
  "phone":"9876543210",
  "address":"Jabalpur, Madhya Pradesh",
  "company":"Example Pvt Ltd"
}
```
Email and phone must be unique. Duplicate values return HTTP 409.
