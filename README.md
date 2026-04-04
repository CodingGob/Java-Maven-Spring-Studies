# Java Maven & Spring Studies

This repository represents my transition from manual database management to professional enterprise frameworks. It covers the use of Maven for dependency management, JPA/Hibernate for Object-Relational Mapping (ORM), and the Spring Boot ecosystem for building scalable REST APIs.

## Project Structure

### 1. database-studies-jpa-maven-basics

A focused study on the transition from JDBC to JPA.

- **Maven Core:** Introduction to automating dependency management (Hibernate, MySQL Connector).
- **JPA Implementation:** Practical use of `EntityManager` and `EntityManagerFactory`.
- **Transaction Management:** Handling `persistence.xml` and manual transactions (`getTransaction().begin()`).

### 2. spring-order-project

A full-featured RESTful API built with **Spring Boot**, representing a complete order management system.

- **Domain Model:** Complex entity relationships including One-to-Many, Many-to-One, and Many-to-Many (using `@JoinTable`).
- **Composite Primary Keys:** Specialized implementation for `OrderItem` using a primary key class (PK).
- **Service Layer:** Decoupled business logic with automated dependency injection using `@Autowired`.
- **Exception Handling:** Global error handling system using `@ControllerAdvice` and custom `StandardError` objects.
- **Database Profiles:** Dual-environment configuration (H2 for testing and PostgreSQL for production).

### 3. spring-mongodb-socialmedia-project

A high-performance Social Media backend focusing on **NoSQL** document modeling and data integrity.

- **NoSQL Architecture:** Implementation of **MongoDB** with `MongoRepository` for flexible document storage.
- **DTO Pattern:** Extensive use of **Data Transfer Objects** to optimize API responses and decouple the database layer from the client.
- **Security & Cryptography:** Implementation of password hashing using **BCrypt** to ensure user data protection.
- **Advanced Validation:** Integration of **Jakarta Bean Validation** (`@NotBlank`, `@Size`, `@Valid`) with a **Fail-Fast** approach to prevent malformed data entry.
- **Custom Queries:** Implementation of complex searches using `@Query` with **Regular Expressions (Regex)** for flexible text filtering.

---

## Key Annotations & Tools Mastered

- **Spring Core:** `@Configuration`, `@Profile`, `@Autowired`, `@Service`, `@Repository`, `@Bean`.
- **JPA/Hibernate:** `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@OneToMany`, `@ManyToMany`.
- **MongoDB:** `@Document`, `@Id` (String), `@DBRef`, `MongoRepository`.
- **Web/REST:** `@RestController`, `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@RequestBody`, `@RequestParam`.
- **Validation & Safety:** `@Valid`, `@NotBlank`, `@Size`, `@NotEmpty`, Handling `UniqueViolation` and `ObjectNotFound` exceptions.
- **Security:** BCrypt Password Hashing, CORS configuration basics.

---

## Technologies

- **Java:** JDK 25 (Latest features)
- **Framework:** Spring Boot 4.0.3
- **Databases:** H2 (Testing), PostgreSQL (Relational), **MongoDB (NoSQL)**
- **Build Tool:** Maven
- **Tools:** VS Code, Git, Postman, MongoDB Compass
