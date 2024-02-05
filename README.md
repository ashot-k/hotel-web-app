# A hotel management system using a Spring Boot backend and a reactjs frontend 
Prerequisites
* Nodejs v20.11.0 
* Java jdk 17
* PostgreSQL 15
## The REST api allows crud operations based on roles
### Admins can perform operations on: 
* Users
* Hotel Rooms
* Room Reservations
### All users can:
* Register
* Login
* View available Rooms within a date range
* Book available rooms
  
## REST API ENDPOINTS
## Users
### Get Users paged
### request
`GET /api/users`
### params
* pageNo (default value 0)
* pageSize (default value 25)

### Example request 

`GET /api/users?pageNo=0&pageSize=1`

### Example response

```json
{
    "content": [
        {
            "id": 1,
            "username": "lmacmurray0",
            "email": "bbenediktovich0@google.it",
            "country": "Indonesia",
            "postalCode": null,
            "street": "0974 Dayton Place",
            "street2": null,
            "phoneNumber": "406-295-8030",
            "role": "CLIENT"
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 1,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "unpaged": false,
        "paged": true
    },
    "last": false,
    "totalPages": 1002,
    "totalElements": 1002,
    "size": 1,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
}
```



