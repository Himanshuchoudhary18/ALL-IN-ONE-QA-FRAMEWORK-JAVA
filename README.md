# All-in-One Java QA Automation Framework

A Maven-based automation framework for web UI, API, database, and performance testing. It uses Java 11, TestNG, Selenium, REST Assured, and Gatling.

## Prerequisites

- JDK 11
- Maven 3.8 or later
- Google Chrome, for browser-based tests

Verify the tools are available:

```powershell
java -version
mvn -version
```

## Get started

Clone the repository and enter the project directory:

```powershell
git clone https://github.com/Himanshuchoudhary18/ALL-IN-ONE-QA-FRAMEWORK-JAVA.git
cd ALL-IN-ONE-QA-FRAMEWORK-JAVA
```

Review [`src/test/resources/configurations.properties`](src/test/resources/configurations.properties) before running tests. For a local run, set:

```properties
local=true
```

Select the browser and execution settings there as needed. Database and SSH fields are intentionally blank; provide only your own test-environment values. MongoDB tests require these environment variables:

```powershell
$env:MONGODB_URI = "mongodb+srv://<user>:<password>@<cluster>/"
$env:MONGODB_DATABASE = "<database-name>"
```

## Run functional tests

The TestNG suite is located at `Testng/api_.xml`.

```powershell
mvn clean install surefire:test "-Dsurefire.suiteXmlFiles=Testng/api_.xml"
```

Test results and generated reports are written to `testResults/` and are ignored by Git.

## Run performance tests

The project includes the Gatling Maven plugin. Add or restore a Gatling simulation under `src/test/java`, then run it by its fully qualified class name:

```powershell
mvn clean test-compile gatling:test "-Dgatling.simulationClass=<package>.<SimulationClass>" "-DUSERS=2" "-DRAMP_DURATION=5" "-DTEST_DURATION=10"
```

If your simulation targets the Gatling Demo Store, clone and start it in a separate terminal:

```powershell
git clone https://github.com/gatling/gatling-demostore.git
cd gatling-demostore
mvn spring-boot:run
```

## Project layout

```text
src/main/java/          Framework utilities and integrations
src/test/java/          TestNG tests, page objects, and test helpers
src/test/resources/     Configuration, TestNG support files, and test data
Testng/                 TestNG suite definitions
```

## Configuration and secrets

Do not commit passwords, API keys, database URLs containing credentials, private keys, or service-account files. Keep secrets in environment variables or a local configuration file that is excluded from Git.

## Contributing

Create a branch, make focused changes, run the relevant test suite, and open a pull request with the result.

