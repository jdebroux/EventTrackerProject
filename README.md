## Wedding Event Tracker

Week 12 homework for Skill Distillery Java bootcamp.

### Overview

Welcome to Wedding Event Tracker!

In this application, you will be able to create, edit, delete, and view all weddings within the database.  At this point this application has the bottom part of the stack created, so the controller, repositories, service classes and interfaces, all of the entities, as well as a database created and mapped out through MySQL.

The next few weekends I will be adding JavaScript and some angular as well.  Take a look at what has been created so far!

### API Routes
* [List All Weddings ](http://18.222.90.180:8080/WeddingTracker/api/weddings/) (“/weddings") GET
* [Show Wedding By ID ](http://18.222.90.180:8080/WeddingTracker/api/weddings/1) (“/weddings/{id}") GET
* [Create New Wedding ](http://18.222.90.180:8080/WeddingTracker/api/weddings/) (“/weddings") POST
* [Update A Wedding ](http://18.222.90.180:8080/WeddingTracker/api/weddings/1) (“/weddings/{id}") PUT
* [Delete A Wedding ](http://18.222.90.180:8080/WeddingTracker/api/weddings/1) (“/weddings/{id}") DELETE
* [Get Weddings Within A Range ](http://18.222.90.180:8080/WeddingTracker/api/weddings/search/totalcost/1000/2000) (“weddings/search/totalcost/{low}/{high}") GET

### Technologies Used

* Java
* STS
* Atom
* Git/Github
* Terminal
* MySQL
* Postman

### Lessons Learned

This project solidified what we learned in class this week.  We learned about JPA repositories and services, as well as new ways to create methods in controllers.  This entire project helped give more experience in this process and I feel as though I can better create full stack applications because of it.

### Attack Plan

I began working on this program by creating a database within MySQL, After the tables were all mapped, I was able to forward engineer it.  Once the database was completed, I was able to map it in STS and create all of the entities and test them with J unit.  After the entities were created I was able to create the spring web boot application.  At that point I just created the repositories and service classes and interfaces for each of my entities and a controller class for each one as well.  I then tested the spring boot application with Postman.

### Complex Parts

We were only instructed to create one table and then build a controller, a repository, and a service for that one table.  I choose to stretch this out a little bit and created 5 tables, and made sure that CRUD worked on each of these tables.

### Wishlist Additions

* I will be adding the frontend parts in the next couple weeks
* Create more information within my database
* Include more tables in the database (address, etc.)
