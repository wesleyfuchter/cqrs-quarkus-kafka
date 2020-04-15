## About CQRS - Command Query Responsibility Segregation

According with [Martin Folwer](https://martinfowler.com/bliki/CQRS.html) 
> At its heart is the notion that you can use a different model to update information than the model you use to read information. 
> For some situations, this separation can be valuable, but beware that for most systems CQRS adds risky complexity.

## The application

Simulates a bank account scenario where an end user adds a income or expense transaction, and it is processed in a ascyncronous event sourcing and CQRS architecture to recalculate the user's bank account balance. The user can also request the balance of it's account. Down here you can see the design:

![Design](/images/design.png)

## Deploying the external services

```
docker-compose up -d --build
```
It will deploy four docker containers on your environment with MongoDB, PostgreSQL, Kafka and Zookepper (required by Kafka)

After deploying Kafka, you'll need to [create the topic on the Kafka cluster](https://kafka.apache.org/quickstart).

## Testing the application

#### Running a CURL request to create a income transaction
```
curl -X POST -H "Content-Type: application/json" -d @income-transaction.json http://localhost:8080/transactions
```
#### Running a CURL request to create a expense transaction
```
curl -X POST -H "Content-Type: application/json" -d @expense-transaction.json http://localhost:8080/transactions
```
#### Running CURL request to fetch the balance
```
curl http://localhost:8081/balance\?accountId\=wesley | json_pp
```
#### Running [K6's](https://k6.io) simple performance test
````
k6 run --vus 10 --duration 60s performance-tests/income.js
k6 run --vus 10 --duration 60s performance-tests/expense.js
````

### Contributing
I'd love to have a frontend for it! [Please reach me out if you got interested](MailTo:wesley.fuchter@gmail.com)
