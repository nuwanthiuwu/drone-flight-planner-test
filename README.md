# Drone Flight Planner UI Automated Testing

This project is aimed at performing UI automated testing on a sample project of Drone Flight Planner. It utilizes Selenium WebDriver with Java, TestNG as the runner, and JUnit as the testing framework.

## Prerequisites

Before running the tests, ensure you have the following prerequisites installed:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- We use Chrome Driver version 124.0.6367.119 by default which is the latest in mac(04-05-2024). Replace the driver and version according to the browser you execute. Also, modify the BaseClass configurations accordingly.

## Installation

1. Clone this repository to your local machine:

    ```bash
    git clone https://github.com/nuwanthiuwu/drone-flight-planner-test
    ```

2. Navigate to the project directory:

    ```bash
    cd drone-flight-planner-test
    ```

3. Install project dependencies using Maven:

    ```bash
    mvn install
    ```

## Running the Tests

To execute the test suite, run the following Maven command:

```bash
mvn test
```

This command will compile the code, execute the tests, and generate test reports.

## Test Framework Details

- **Selenium WebDriver**: A powerful tool for automating web browsers across various platforms.
- **TestNG**: A testing framework inspired by JUnit and NUnit, but with more functionality.

## Test Structure

- **src/main/java/pages**: Contains actions which helps to perform tests on Drone Flight Planner app.
- **src/main/java/tests**: Contains test cases which evaluates the app.



