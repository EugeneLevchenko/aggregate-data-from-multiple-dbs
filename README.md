Comparus.ua Test task made by Levchenko Eugene 
=

Introduction
-
This is an API that can be used for aggregating users data from multiple databases.

Running application Locally:
-
1. Download project from GIT.
2. Run java application.
3. Open your favorite browser.
4. Follow this link: http://localhost:8080/swagger-ui/index.html
5. Choose a Development environment in "Servers" dropdown.
6. Feel free to use this API via Swagger, if you wish, you can use Postman for exploring it, but Swagger is much more suitable for this goal.

Running application Remotely:
-
1. Open your favorite browser.
2. Follow this link: http://comparus-ua-test-task.eu-west-2.elasticbeanstalk.com/swagger-ui/index.html
3. Choose a Production environment in "Servers" dropdown.
4. Feel free to use this API via Swagger, if you wish, you can use Postman for exploring it, but Swagger is much more suitable for this goal.

Description of endpoints:
-

There are 3 endpoints in application, each of which is documented and described well, but here is a brief info about them:

1. /users/find-all - It's the main endpoint of application which aggregates users a data from multiple databases
2. /users/find-by-id - It's endpoint for getting single user data by id
3. /users/find-by-filters - It's endpoint is used for filtering users data by multiple filters,
such as: [id, username, name, surname], you can use them together or separate for filtering your data.

How to use this application?
-
1. Open project
2. Find property file by this path: src/main/resources/data-source.yml
3. In property file specify your configuration for databases, max number of which is infinite
4. Run application