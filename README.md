# React/Spring Demonstration

Pet adoption system with a React front end and back end in Spring (Java).
The database used is H2 in in-memory mode (only for demonstration purposes, can easily be modified to save the database in disk or use another database).
There's also a Swagger documentation on the Back directory, for the API (animalAPI.yml is the source for the documentation and you can see as a simple HTML in docAPI.html).

## How to Run!

Go (`cd`) to the Front directory, then:
`npm install`
to install the node modules, and
`npm start`
to run the front end.

Go to the Back directory, then
`.\mvnw.cmd spring-boot:run`
will run the back end (mvn spring-boot:run should work if you have Maven installed and in PATH).