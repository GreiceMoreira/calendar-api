# Overview

# 📅 Calendar API

A RESTful API built in **Java** to create, store, and retrieve calendar events.  
This project is part of my learning journey in building and consuming APIs, and it demonstrates the full cycle of backend development: models, controllers, services, and repository to data.  

[Software Demo Video](http://youtube.link.goes.here)

## ✨ Features
- Add new events (name, type, date, time, description)  
- Retrieve a list of all events  
- Clear separation of **Controller**, **Service**, **Model** , and **Repository**
- Designed to be consumed by a JavaScript frontend  

---

# Development Environment

## 🛠️ Tech Stack
- **Language:** Java 21  
- **Framework:** Spring Boot 3.5.6
- **Tools:** Maven, VS Code  

### Run the API

To start the API using Spring Boot, run the following command:
./mvnw spring-boot:run
The API will be available at http://localhost:8080

---

# Useful Websites

{Make a list of websites that you found helpful in this project}

- [Java JDK 21](https://docs.oracle.com/en/java/javase/21/index.html)
- [W3Schools Java](https://www.w3schools.com/java/default.asp)
- [Spring Boot](https://spring.io/projects/spring-boot)

# Future Work

- Properly configure CORS for frontend requests
- Build the Fronted application and link it to the API https://www.figma.com/design/hBlkX2uFuuGOkoarCA6TUL/TaskCalendar?node-id=0-1&t=prCdfvpSbRpBmgda-1
- Improve validation and error handling for events



API Live - https://calendar-greice-api.netlify.app