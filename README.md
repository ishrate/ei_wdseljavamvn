# OnlineInvoice Test Automation Framework

## Overview
This is a Java Maven-based test automation framework for web applications. It primarily uses Selenium WebDriver for browser automation, TestNG for test management, and ExtentReports for reporting. The framework also supports Playwright for modern browser automation, enabling cross-browser and cross-platform testing.

## Features
- **Selenium WebDriver**: Automate web browsers for functional UI testing.
- **TestNG**: Organize, execute, and manage test cases efficiently.
- **ExtentReports**: Generate rich HTML reports for test execution.
- **Playwright Support**: Build and run Playwright-based tests for advanced browser automation.
- **Configurable**: Easily manage environment settings and credentials via `config.properties`.
- **Data-Driven Testing**: Supports test data from Excel and XML files.
- **Database Validation**: Connect to Oracle DB for backend data verification.

## Project Structure
```
src/
  main/
    java/         # Framework source code
    resources/    # Configuration files
  test/
    java/         # Test cases
    resources/    # Test data files

pom.xml           # Maven build configuration
config.properties # Main configuration file
```

## Getting Started
### Prerequisites
- Java 11 or above
- Maven 3.6+
- Chrome browser (for Selenium)

### Setup
1. Clone the repository:
   ```sh
   git clone <repo-url>
   cd ei.pwwdseljava.onlineinvoice
   ```
2. Install dependencies:
   ```sh
   mvn clean install
   ```
3. Configure `src/main/resources/config.properties` with your environment details.

### Running Tests
- **Selenium Tests**:
  ```sh
  mvn test
  ```
- **Playwright Tests**:
  (Refer to Playwright test classes and instructions in the project)
  ## Running Tests

### Running TestNG Tests
- To run all TestNG tests, execute:

  ```sh
  mvn test
  ```
- If you have a custom TestNG suite (for example, `testng.xml`), run:

  ```sh
  mvn test -DsuiteXmlFile=testng.xml
  ```

### Viewing Extent Reports
- After test execution, the Extent Report is generated in the following location:

  `test-output/ExtentReports/`

- Open the `index.html` file in your browser to view the detailed test report.
  

### Reports & Logs
- Test reports: `target/reports`
- Logs: `target/logs`
- ExtentReports: `test-output/ExtentReports/`

## Adding Playwright Tests
- Add Playwright test classes under `src/test/java/`
- Ensure Playwright dependencies are included in `pom.xml`
- Refer to sample Playwright test cases for structure.

## Contributing
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes
4. Push to the branch (`git push origin feature/your-feature`)
5. Create a Pull Request


## Contact
For questions or support, contact the maintainer at `eamon.ishrat@gmail.com`.
