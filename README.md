# Secure RBAC Management System

## Overview

The **SecureRBAC Management System** is a backend application designed to manage user authentication, role management, and user profiles with a robust implementation of Role-Based Access Control (RBAC). It ensures secure and scalable access control for enterprise applications, adhering to modern software development practices.

## Features
- **User Authentication**: Secure login and registration with HMAC SHA-256 encryption.
- **Role Management**: Fine-grained role-based access control with CRUD operations for roles.
- **User Profile Management**: Efficient user profile handling with Lombok and ModelMapper integration.
- **OAuth 2.0 Integration**: Secure API access using industry-standard authorization protocols.
- **RESTful APIs**: Clean and standardized endpoints for communication between microservices.
- **Microservices Architecture**: Modular services for authentication, role management, and profile handling.
- **Logging**: SLF4J for efficient logging.
- **Security**: Implements best practices for secure data transmission.

---

## Technologies Used
- **Java 17**
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA**
- **HMAC SHA-256 Encryption**
- **OAuth 2.0**
- **MySQL**
- **RESTful APIs**
- **ModelMapper**
- **Lombok**
- **SLF4J**
- **Docker**

---

## System Architecture
This project is built using a microservices architecture, with three primary services:
1. **User Authentication Service**: Manages user login, registration, and token generation.
2. **Role Management Service**: Handles role-based access control and role CRUD operations.
3. **User Profile Management Service**: Manages user profile information and updates.

Each service communicates using RESTful APIs to ensure modular and efficient integration.

---

## Packaging Structure

**com.example.project** <br/> 
**|-- authentication-service** <br/> 
**|-- role-management-service** <br/> 
**|-- user-profile-management-service** 

Each service is modularized and follows the microservices architecture for scalability and maintainability.

## How to Run the Project

## Prerequisites

Java 17 installed.

MySQL server running.

Maven installed.

IDE such as IntelliJ IDEA or Eclipse.

## Steps

1. **Clone the repository from GitHub.** <br/>
   ```git clone [<repository-url>](https://github.com/SHAIKAMEERr/Secure-RBAC-Management-System.git)```
2. **Navigate to Individual Services:** For each service <br/>
   ```cd user-authentication-service```
3. **Install Dependencies:** Ensure you have Maven installed, then run: <br/>
   ```mvn clean install```
4. **Run the Service:** Start the service using: <br/>
   ```mvn spring-boot:run``` <br/>
5. **Setup Databases:**
  - Create a MySQL database named ```rbac_management```.
  -  Update the ```application.properties``` file in each service with your database credentials.

7. **Access APIs:** <br/>
Use tools like Postman or Curl to interact with the APIs.

## Contact Information
For any questions or contributions, feel free to reach out!

**LinkedIn:** [Shaik Ameer](https://www.linkedin.com/in/ameer-shaikk/)

**Email:** shaikameerjann@gmail.com
