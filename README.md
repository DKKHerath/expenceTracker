# Expense Tracker App 

"Introducing the ultimate expense tracker application - a powerful tool to help you manage your finances and stay on top of your spending. With our user-friendly interface, you can easily track your income and expenses, set budgets, and gain insight into your spending habits. Whether you're a busy professional, a student, or a stay-at-home parent, our app is designed to meet your needs and help you achieve your financial goals. Download now and take control of your finances today!"

## Requirements
- Java 8 or later
- Maven 3.x or later (if you want to build the application from source)

## How to Run
- Go to the project directory: cd spring-boot-backend
- Build the project: ./mvnw clean install (or mvnw.cmd clean install on Windows)
- Run the application: ./mvnw spring-boot:run (or mvnw.cmd spring-boot:run on Windows)

The application should now be running on http://localhost:8080

## API documentation
You can access the API documentation by visiting http://localhost:8080/ when the application is running.

## Note
Note: Since this application doesn't require a database connection, any database related configurations and dependencies can be removed or commented out in the pom.xml file.

Note: The ./mvnw (or mvnw.cmd on Windows) is the Maven Wrapper script that allows you to run the Maven commands without having to install Maven on your machine.

In case you don't have maven wrapper script then you can download the maven and configure it in your system.
Please check the official maven documentation for further details about how to install maven on your specific operating system.
