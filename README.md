# 📚 Virtual Book Store

A ***Spring Boot-based web application*** for managing a virtual bookstore.
It supports features like user authentication, book management, shopping cart, orders, and reviews.

---

## 🚀 Features

- **User Authentication** (Login, Registration, Password Update)
- **Book Management** (Add, update, delete, view books)
- **Shopping Cart** functionality
- **Order Placement & Tracking**
- **User Reviews & Ratings**
- **Role-Based Access Control** (Admin/User)
- **RESTful API** design following Spring best practices

---

## 🧩 Tech Stack

- **Backend**:	Java 17, Spring Boot
- **Security**:	Spring Security, JWT
- **Database**:	MySQL / H2 (configurable)
- **Build Tool**:	Maven
- **API Design**:	REST
- **Version Control**:	Git

---

## 📁 Project Structure

VirtualBookStore/
 ├── src/main/java/com/virtualbookstore/bookstoreapp/
 │   ├── controllers/ 
 │   ├── entities/
 │   ├── dto/
 │   ├── config/
 │   ├── common/
 │   └── VirtualBookstoreApplication.java
 ├── src/main/resources/
 │   ├── application.properties
 ├── pom.xml                 
 └── HELP.md / README.md

 ---

 ## ⚙️ Setup and Run

 ### 1️⃣ Clone the repository

 ```bash
git clone https://github.com/yourusername/VirtualBookStore.git
cd VirtualBookStore
```

### 2️⃣ Build the project

```bash
mvn clean install
```

### 3️⃣ Run the application

```bash
mvn spring-boot:run
```

Application will start at http://localhost:8082

---

## 🔐 Authentication

JWT-based authentication with Spring Security:
- POST /auth/register – Register new users
- POST /auth/login – Authenticate and receive token
- Authorization: Bearer <token> for secured endpoints

---

## 📘 API Overview

| Module     | Endpoint                  | Method | Description                   |
| ---------- | ------------------------- | ------ | ----------------------------- |
| **Auth**   | `/auth/register`          | POST   | Register a new user           |
| **Auth**   | `/auth/login`             | POST   | Login and get JWT token       |
| **Books**  | `/books/browse`           | GET    | View all books                |
| **Books**  | `/books/{id}`             | GET    | View single book              |
| **Books**  | `/books/add`              | POST   | Add a book                    |
| **Books**  | `/books/edit/{id}`        | PUT    | Edit a book                   |
| **Books**  | `/books/updatestock/{id}` | PATCH  | Update stock of book          |
| **Books**  | `/books/delete/{id}`      | DELETE | delete a book                 |
| **Cart**   | `/cart`                   | GET    | View user cart                |
| **Cart**   | `/cart/add`               | POST   | Add book to cart              |
| **Cart**   | `/cart/{id}`              | PUT    | Change quantity book to cart  |
| **Cart**   | `/cart/{id}`              | DELETE | Delete book to cart           |
| **Cart**   | `/cart/clearcart`         | DELETE | Delete all items in cart      |
| **Orders** | `/orders/viewall`         | GET    | Get user orders               |
| **Orders** | `/orders/fromcart`        | POST   | Order all items from cart     |
| **Orders** | `/orders`                 | POST   | Order an item                 |
| **Orders** | `/orders/view/{id}`       | GET    | Get user order                |
| **Orders** | `/orders/cancel/{id}`     | PATCH  | Cancel an order               |

- Only important Endpoints are mentioned
---

## 🧠 Future Enhancements

- Add payment gateway integration
- Implement admin dashboard for analytics
- Integrate with cloud database (AWS RDS / Firebase)

---
