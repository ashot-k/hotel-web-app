# A hotel management system using a Spring Boot backend and a reactjs frontend 
Prerequisites
* Nodejs v20.11.0 
* Java jdk 17
* PostgreSQL 15
## The REST api allows users to perform crud operations based on roles 
### Roles
* ADMIN
* EMPLOYEE 
* CLIENT
### Admins can perform operations on: 
* Users
* Hotel Rooms
* Room Reservations
### Employees can perform operations on: 
* Users
### All users can:
* Register
* Login
* View available Rooms within a date range
* Book available rooms

## REST API ENDPOINTS


## Users
| Method  | URL  | Params  | Action  | Example Request |
|---|---|---|---|---|
| GET  | /api/users  | pageNo, pageSize   | Get users paged   | GET /api/users?pageNo=0&pageSize=1  |
| GET  | /api/users/:id  | -  | Get user by id   | GET /api/users/123
| GET  | /api/users/search  | term, pageNo, pageSize  | Get users by name paged  | GET /api/users/search?term=george&pageSize=2|
|  POST | /api/users   | -  | Create user | POST /api/users + json body |
|  PUT |   	/api/users/:id  | -  | Update user |PUT /api/users/123 + json body |
|  DELETE |   |   |   |
# GET Users paged
### Request `curl -X GET --location "http://localhost/api/users"`
### params
* pageNo (default value 0)
* pageSize (default value 25)
### Example response for request `GET /api/users?pageNo=0&pageSize=1` 
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
    //pagination part
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
# Get User by id 
### Request `GET /api/users/:id`
### Example response for request `GET /api/users/123`
```json
{
    "id": 123,
    "username": "kraunds3e",
    "email": "hreboul3e@unblog.fr",
    "country": "Russia",
    "postalCode": "353598",
    "street": "96 Golf View Hill",
    "street2": null,
    "phoneNumber": "365-175-4775",
    "role": "CLIENT"
}
```

# Get Users by username paged
### Request `curl -X GET --location "http://localhost/api/users/search?term=string"`
### params
* pageNo (default value 0)
* pageSize (default value 25)
* username
### Example response for request `GET /api/users/search?term=george&pageSize=2`
```json
{
    "content": [
        {
            "id": 402,
            "username": "ggeorgesonb5",
            "email": "bantonellinib5@soundcloud.com",
            "country": "Poland",
            "postalCode": "84-130",
            "street": "235 Kedzie Place",
            "street2": null,
            "phoneNumber": "388-491-4114",
            "role": "CLIENT"
        },
        {
            "id": 583,
            "username": "ogeorgeoug6",
            "email": "gthynng6@exblog.jp",
            "country": "New Zealand",
            "postalCode": "4541",
            "street": "9 Summit Circle",
            "street2": null,
            "phoneNumber": "663-443-1062",
            "role": "CLIENT"
        }
    ], + pagination part 
}
```
# POST User
### Request 
```
curl -X POST --location "http://localhost/api/users" \
-H "Content-Type: application/json" \
-d '{
          "username" : "",
          "password" : "",
          "email" : "",
          "country" : "",
          "postalCode" : "",
          "street" : "",
          "street2" : "", //optional
          "phoneNumber" : "",
          "role" : ""//defaults to client and can only be set by an admin
}
```
### Example request `POST /api/users` with body
```json
{
    "username": "sasasa",
    "password": "56469e73A",
    "email": "sasasa@gmail.com",
    "country": "greece",
    "postalCode": "54321",
    "street": "mpostari 664",
    "phoneNumber": "6909627079"
}
```
### Response
```json
{
    "id": 1202,
    "username": "sasasa",
    "email": "sasasa@gmail.com",
    "country": "greece",
    "postalCode": "54321",
    "street": "mpostari 664",
    "street2": null,
    "phoneNumber": "6909627079",
    "role": "CLIENT"
}
```

# PUT User
### Request 
```
curl -X PUT --location "http://localhost/api/users/:id" \
    -H "Content-Type: application/json" \
    -d '{
          "username" : "",
          "password" : "",
          "email" : "",
          "country" : "",
          "postalCode" : "",
          "street" : "",
          "street2" : "", //optional
          "phoneNumber" : "",
          "role" : ""//defaults to client and can only be set by an admin
        }'
```
### Example request `PUT /api/users/53` with body
```json
{
    "username": "james",
    "password": "123456789",
    "email": "james@gmail.com",
    "country": "greece",
    "postalCode": "54329",
    "street": "delfon 29",
    "phoneNumber": "6941313123",
    "role": "EMPLOYEEE"
}
```

### Response
```json
{
    "id": 53,
    "username": "james",
    "email": "james@gmail.com",
    "country": "greece",
    "postalCode": "54329",
    "street": "delfon 29",
    "street2": null,
    "phoneNumber": "6941313123",
    "role": "EMPLOYEE"
}
```









