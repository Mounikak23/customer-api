# Customer Rest Api Execution Steps
* download the given code base or clone to your local machine
* go to root directory of the app
* build the project using cmd "mvn clean install"
* java -jar target/customer-api-1.0.0.jar

# Links
 
* [Swagger](http://localhost:8080/api/swagger-ui.html) - Swagger Documentation for customer rest api

## Examples
I loaded sample data for customers 123 & 456.

http://localhost:8080/api/rewards/ -> returns all customers monthwise and total rewards 

http://localhost:8080/api/rewards/123 -> returns 123 customer monthwise and total rewards.

# Rest API Urls (Please refer to Swagger documentation for more information)

## Rewards API

### GET - http://localhost:8080/api/rewards/
* Returns list of monthwise and total rewards of all customers with in given date range. If date range is not given, it will return all monthwise and total rewards of all customers.
* fromDate & toDate are optional query parameters. If given, it should be yyyy-MM-dd format and both are required.

### GET - http://localhost:8080/api/rewards/{customerId}
* Returns list of monthwise and total rewards of given customer with in given date range. If date range is not given, it will return all monthwise and total rewards of given customer.
* customerId is path variable & its required query parameter.
* fromDate & toDate are optional query parameters. If given, it should be yyyy-MM-dd format and both are required.

## Transaction API

### GET - http://localhost:8080/api/transactions/
* Returns list of transactions of all customers with in given date range. If date range is not given, it will return all transactions of all customers.
* fromDate & toDate are optional query parameters. If given, it should be yyyy-MM-dd format and both are required.

### GET - http://localhost:8080/api/transactions/{customerId}
* Returns list of transactions of given customer with in given date range. If date range is not given, it will return all transactions of given customer.
* customerId is path variable & its required query parameter.
* fromDate & toDate are optional query parameters. If given, it should be yyyy-MM-dd format and both are required.

### POST - http://localhost:8080/api/transactions/
* it accepts BaseTransaction model body with customerId, transactionTotal and transactionDate
* transactionDate should be yyyy-MM-dd format.

## Assumptions/Notes
* Reward points as decimal with precision 2.
* Knowingly didn't write unit test cases, let me know if needed.
* Ideally, we should get all these data using ORM framework, just used setup data in memory. But still you can add transaction using transaction post end point. Once you shutdown spring container, you will loose all your added data. 

