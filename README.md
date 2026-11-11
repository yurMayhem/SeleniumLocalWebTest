# Selenium Java TestNG Project (Experiment 6)

Automated browser testing project using Selenium WebDriver 4.25.0, TestNG 7.10.2, and Java 17 for a student login form web application.

## Project Structure
```text
SeleniumLocalWebTest/
├── .github/
│   └── workflows/
│       └── selenium-tests.yml
├── src/
│   └── test/
│       └── java/
│           └── LocalLoginTest.java
├── webpage/
│   └── login.html
├── .dockerignore
├── .gitignore
├── Dockerfile
├── pom.xml
├── README.md
└── testng.xml
```

## Features & Test Cases
1. **validLoginTest**: Verifies successful student login with username `student` and password `12345`. Confirms display of `"Login successful!"`.
2. **invalidLoginTest**: Verifies that invalid credentials display `"Invalid username or password!"`.
3. **checkBackgroundColor**: Validates that the webpage background color is lightblue (`rgba(173, 216, 230, 1)`).

## Local Execution
Ensure Java 17+ and Maven are installed. Run:
```bash
mvn clean test
```

## Docker Execution
Build and run the containerized test suite:
```bash
docker build -t yurmayhem/selenium-local-web-test:latest .
docker run --rm yurmayhem/selenium-local-web-test:latest
```
