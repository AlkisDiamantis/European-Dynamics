     
      ============================================================
      ######################### MYSQL ############################
      ============================================================

- Inside the SQL folder you will find the queries to create the schema needed for this application

- run the ddl commands that you will find in ddl_comands.sql file to create the tables

- run the insert queries that you will find in dummy_data.sql file to insert some test data


      ============================================================
      ############ APPLICATION PROPERTIES ########################
      ============================================================


Now you need to set up mysql configuration in the application.properties file

spring.datasource.url ={your database url}
spring.datasource.username = {username}
spring.datasource.password = {password}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

     ============================================================
     ####################### API I/O ############################
     ============================================================

The application runs in embeded tommcat on http://localhost:8080/

and exposes the following endpoints:


- GET LIST OF USERS 
 
 http://localhost:8080/users?page={pageNumber}&limit={limitNumber}

 example: http://localhost:8080/users?page=1&limit=3


- GET USER WITH HER POSTS

 http://localhost:8080/users/{userId}

example http://localhost:8080/users/1

 http://localhost:8080/users/{userId}/posts

example:http://localhost:8080/users/1/posts


- GET A USER’S POST WITH ITS COMMENTS

 http://localhost:8080/users/{userId}/posts/{postID}

example:http://localhost:8080/users/1/posts/3

 http://localhost:8080/users/{userId}/posts/{postID}/comments

example:http://localhost:8080/users/1/posts/2/comments
























http://localhost:8080/europeandynamics/users/5

http://localhost:8080/europeandynamics/users/1/posts

http://localhost:8080/europeandynamics/users/1/posts/3

http://localhost:8080/europeandynamics/users/1/posts/2/comments
