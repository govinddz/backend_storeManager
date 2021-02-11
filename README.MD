**Requirements**
1. Java version > 1.8
2. Gradle > gradle-6.8.2-all
3. Docker 
4. Postman

**Steps for running the project**
1. Enter cmd inside root directory `-$./gradlew clean build`.
2. Run docker for pgsql > run command > docker-compose -f docker-compose-pgsql.yml up
3. Create database name > test
4. We have swagger for API documentation. Here are the link > http://localhost:8080/swagger-ui.html#!
     
**Operations**
To Check Rest Api End, Use Postmen Client and hit the end point.

 1. To Create Shop
 
   just hit  =>POST http://localhost:8080/v2/shop
   sample Request Body :
   
   `{
     "address": {
       "city": "abc",
       "country": "xyz",
       "latitude": 26.9252337,
       "longitude": 75.8010876,
       "state": "xyz"
     },
     "category": "General_Store",
     "ownerName": "Demo",
     "shopName": "demo"
   }`
   
   sample Response json:
   
      `{
           "id": 1,
           "shopName": "demo",
           "ownerName": "Demo",
           "category": "General_Store",
           "address": {
               "id": 1,
               "city": "abc",
               "state": "xyz",
               "country": "xyz",
               "latitude": 26.9252337,
               "longitude": 75.8010876
           }
       }
     `
     
 2. To Get All Shop
     
       just hit  =>POST http://localhost:8080/v2/shops?pageNumber=0&pageSize=10
       sample Request Body :
       
       `{ "location": {
            "latitude": 26.9252337,
            "longitude": 75.8010876
          },
          "shopName": "De"
        }`
       
       sample Response json:
       
          `{
               "content": [
                   {
                       "id": 1,
                       "shopName": "demo",
                       "ownerName": "Demo",
                       "category": "General_Store",
                       "address": {
                           "id": 1,
                           "city": "abc",
                           "state": "xyz",
                           "country": "xyz",
                           "latitude": 26.9252337,
                           "longitude": 75.8010876
                       }
                   }
               ],
               "pageable": {
                   "sort": {
                       "sorted": true,
                       "unsorted": false,
                       "empty": false
                   },
                   "pageNumber": 0,
                   "pageSize": 10,
                   "offset": 0,
                   "paged": true,
                   "unpaged": false
               },
               "totalElements": 1,
               "totalPages": 1,
               "last": true,
               "first": true,
               "sort": {
                   "sorted": true,
                   "unsorted": false,
                   "empty": false
               },
               "numberOfElements": 1,
               "size": 10,
               "number": 0,
               "empty": false
           }`