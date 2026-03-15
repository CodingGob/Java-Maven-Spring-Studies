# Java Maven & Spring Studies

This repository represents my transition from manual database management to professional enterprise frameworks. It covers the use of Maven for dependency management, JPA/Hibernate for Object-Relational Mapping (ORM), and the Spring Boot ecosystem for building scalable REST APIs.

### Project Structure

1. **database-studies-jpa-maven-basics**
   A focused study on the transition from JDBC to JPA.
   - Introduction to **Maven** for automating dependency management (Hibernate, MySQL Connector).
   - Implementation of **JPA (Java Persistence API)** using `EntityManager` and `EntityManagerFactory`.
   - Practice with `persistence.xml` and manual transaction handling (`getTransaction().begin()`).

2. **spring-order-project**
   A full-featured RESTful API built with **Spring Boot**, representing a complete order management system.
   - **Domain Model**: Complex entity relationships including One-to-Many, Many-to-One, and Many-to-Many (using `@JoinTable`).
   - **Composite Primary Keys**: Specialized implementation for `OrderItem` using a primary key class (`PK`) to link Orders and Products.
   - **Service Layer**: Decoupled business logic with automated dependency injection using `@Autowired`.
   - **Exception Handling**: A robust global error handling system using `@ControllerAdvice` and custom exception objects (`StandardError`).
   - **Database Profiles**: Dual-environment configuration using the **H2 Database** for testing (via `@Profile("test")`) and support for **PostgreSQL** in production.
   - **Database Seeding**: Automated data population for the test environment using a `CommandLinerRunner` configuration.

### Key Annotations & Tools Mastered

- **Spring Core**: `@Configuration`, `@Profile`, `@Autowired`, `@Service`, `@Repository`.
- **JPA/Hibernate**: `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@OneToMany`, `@ManyToMany`.
- **Web/REST**: `@RestController`, `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@JsonIgnore`.
- **Validation/Safety**: Handling `Unique` constraints and custom `ResourceNotFound` exceptions.

### Technologies

- **Java**: JDK 25 (Latest features)
- **Framework**: Spring Boot 4.0.3
- **Database**: H2 (Test), PostgreSQL (Production)
- **Build Tool**: Maven
