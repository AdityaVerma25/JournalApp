# 📔 JournalApp

JournalApp is a simple and secure journaling application that allows users to write, manage, and organize their daily thoughts and experiences. It is designed to help users build a consistent journaling habit with an easy-to-use interface and reliable backend.

---

## 🚀 Features

- ✍️ Create, edit, and delete journal entries  
- 🔐 User authentication and authorization  
- 🗂️ Organize entries by date  
- 📅 View journal history  
- 🔍 Clean and user-friendly interface  
- 🛡️ Secure data storage  

---

## 🛠️ Tech Stack

### Backend
- Java
- Spring Boot
- Spring MongoDB
- Spring Security
- REST APIs

### Database
- MongoDB (based on configuration)

### Tools
- Maven
- Git & GitHub
- IntelliJ IDEA

GeneralApp
│
├── .gitignore
├── README.md
├── pom.xml
│
└── src
    └── main
        ├── java
        │   └── com
        │       └── generalapp
        │           ├── GeneralAppApplication.java
        │           │
        │           ├── config
        │           │   ├── SecurityConfig.java
        │           │   ├── SwaggerConfig.java
        │           │   └── RedisConfig.java
        │           │
        │           ├── controller
        │           │   ├── AuthController.java
        │           │   ├── UserController.java
        │           │   └── HealthController.java
        │           │
        │           ├── service
        │           │   ├── UserService.java
        │           │   ├── JournalEntryService.java
        │           │   ├── EmailService.java
        │           │   ├── RedisService.java
        │           │   ├── WeatherService.java
        │           │   └── impl
        │           │       └── UserServiceImpl.java
        │           │
        │           ├── repository
        │           │   └── UserRepository.java
        │           │   └── JournalEntryRepository.java
        │           │   └── ApiResponseRepository.java
        │           │
        │           │
        │           ├── util
        │               ├── JwtUtil.java
        │               └── JwyAuthenticationFilter.java
        │
        └── resources
            ├── application.properties
            └── logback.xml



