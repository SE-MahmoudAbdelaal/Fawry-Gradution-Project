
# Microservices-Based E-Commerce Platform

A scalable e-commerce platform built using Spring Boot and Angular, leveraging microservices architecture for seamless and efficient service management.

## **Project Overview**

This project is an e-commerce platform developed using Spring Boot for backend services and Angular for the frontend. It is designed with a microservices architecture, where each service communicates with others using Feign Client and publishes messages through RabbitMQ. The application uses Redis for caching cart data and MySQL as the main database.

![Architecture Diagram](Microservices%20Diagram.jpg)


## **Key Features**

- **Microservices Architecture**: Independent services for user management, orders, products, coupons, and notifications.
- **Feign Client**: Used for inter-service communication to enhance modularity.
- **RabbitMQ**: Implements asynchronous messaging for reliable service communication.
- **Redis Caching**: Manages server-side caching of shopping cart data for faster access.
- **MySQL Database**: Relational database used for data persistence.

## **Technology Stack**

- **Backend**: Spring Boot, Spring Cloud, Feign Client, RabbitMQ, Redis, MySQL
- **Frontend**: Angular
- **Message Broker**: RabbitMQ
- **Cache**: Redis
- **Database**: MySQL

## **Getting Started**

### **Prerequisites**

- Java 17+
- Node.js 16+
- MySQL 8+
- Redis
- RabbitMQ

### **Installation**

1. Clone the repository:

   ```bash
   git clone https://github.com/SE-MahmoudAbdelaal/Fawry-Gradution-Project
   ```

2. Navigate to the backend directory and install dependencies:

   ```bash
   cd backend
   ./mvnw clean install
   ```

3. Set up the MySQL database by running the provided SQL scripts.

4. Start RabbitMQ and Redis servers.

5. Run each microservice using Spring Boot.

6. Navigate to the frontend directory, install dependencies, and run the Angular application:

   ```bash
   cd frontend
   npm install
   ng serve
   ```

## **Usage**

- Access the platform through the Angular frontend.
- Users can register, log in, browse products, add items to the cart, apply coupons, and checkout.
- Admins can manage products, orders, and view notifications.
