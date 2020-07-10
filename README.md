# saving-account
An endpoint to create a saving bank account using basic authentification.

### Built With
- Java 11
- Spring Boot
- Spring Security
- Spring HATEOS
- Spring JPA
- PostgreSQL
- H2 Database - only for testing scope


### Installation
1. The primary you will need to install `PostgreSQL`;
2. Create your database;
3. Run the script inside the `./util/schema.sql` and create the tables;


### Clone Project

`git clone https://github.com/liviubiur/saving-account.git`


### Web API

| Web API                 | URL          
| ------------------------|:-------------:
| User register           | /ing-tech/users
| Create a Saving Account | /ing-tech/saving-accounts

### How it works

After you clone this repo change the `username` and `password` in the `./resources/application.yaml` file, to connect to the database. 
Now you're ready to deploy the project on your local environment.

1. Register an user:

` curl -L -X POST 'http://localhost:8085/ing-tech/users' -H 'Content-Type: application/json' -d '{"username": "test", "password": "test", "email": "test@foo.com"}'`

2. You must be logged in to create a saving account. You can use the user credentials created in the step one.

**NOTE**: The Saving Account can be created only during the working days between 09:00 - 18:00 localtime!
The user can't have more than one Saving Account! 

`curl --location --request POST 'http://localhost:8085/ing-tech/saving-accounts' \
--header 'Authorization: Basic bGl2aXU6dGVzdA==' \
--header 'Content-Type: application/json' \
--data-raw '{"balance":2}'`

### Tests

`mvn test`
