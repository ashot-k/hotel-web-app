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
# Users
## GET Users paged
### Request `GET /api/users`
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
## Get User by id 
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

## Get Users by username paged
### request
`GET /api/users/search`
### params
* pageNo (default value 0)
* pageSize (default value 25)
* username
### Example request 
`GET /api/users/search?term=george&pageSize=2`
### Example response

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
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 2,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalElements": 2,
    "totalPages": 1,
    "size": 2,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
}
```


