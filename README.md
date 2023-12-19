# Rewards Service

### To Run

Make sure you have java and gradle installed

#### Build the project
`gradle build`

#### Run the project
`java -jar build/libs/customer-points-0.0.1-SNAPSHOT.jar`

#### Interact with the application
Using either a command line tool like curl or UI testing tool such as Postman, send a POST call to either
endpoint in the RewardsController class with a List of Transactions.

##### Total Rewards Endpoint
`curl -X POST -H "Content-Type: application/json" -d '[{
"account": 123,
"date": "APRIL",
"amount": 125.00
}, {"account": 456,
"date": "JUNE",
"amount": 75.00
}, {"account": 456,
"date": "JUNE",
"amount": 45.00
}]' http://localhost:8080/api/rewards
`

##### Rewards Monthly Endpoint
`curl -X POST -H "Content-Type: application/json" -d '[{
"account": 123,
"date": "APRIL",
"amount": 125.00
}, {"account": 456,
"date": "JUNE",
"amount": 75.00
}, {"account": 456,
"date": "JUNE",
"amount": 45.00
}]' http://localhost:8080/api/rewards/monthly`