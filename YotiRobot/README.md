# YOTI ROBOT SIMULATION SOLUTION
By Aashutos Kakshepati

### Setting up the Application

In order to start the Application, a few pre-requisites need to be executed in order to set up the Web Application. These will be listed in the stages below.

#### Standing up the H2 DB Server

I have bundled in a script: `h2/bin/h2-start.sh`, which boots up the H2 DB from within the project workspace.

1) Please ensure the script is executable by ensuring permissions:

	`sudo chmod u+x ./h2-start.sh` 

2) Also, ensure that directory `h2/bin/db` exists and create if not present.
	
3) Execute the shell script@ `./h2-start.sh`

H2 console Web UI should be displayed confirming the Server is up. Note that the `h2/bin/db` folder is empty.

#### Use flyway to generate schema for DB

We will need to now generate the DB schema using Flyway.

1) Execute Maven command: `mvn initialize flyway:clean flyway:migrate`

2) Please confirm that `h2/bin/db` contains the file `yoti.mv.db` or similar.

3) Connecting to the DB using `jdbc:h2:tcp://localhost:9092/yoti` or similar should yield a schema `YOTI` and three database tables: `flyway_schema_history`, `HOOVER_REQUESTS`, `HOOVER_RESPONSE`

Once the Flyway modules are set up, we will need to initiate JOOQ POJO Code Generation on this DB.

#### JOOQ Java POJO Generation

Once the DB schema is up, the JOOQ code cand be generated via maven as follows:

1) Execute Maven command: `mvn initialize generate-sources`

2) Once this is complete. Within `src/main/java`, you will notice newly generated classed under `com.interview.yoti.robot.dao.jooq`

The Web Application should be ready to build now.

#### Building the Spring MVC Application

The Spring Web Application can be built using Maven once again:

1) Execute Maven command: `mvn clean compile`

2) The application can be start using: `mvn exec:java -f ./pom.xml` or similar

3) You should see the familiar Spring logo.

#### Testing with SoapUI

I have bundled in a brief set of test cases in the form of a Soap UI project in `/SoapUI/YOTI-ROBOT.xml` to run against the stood up Web Application. The Web Application does not require any authentication and is executed using HTTP POST commands.

#### JUnit 4 Unit Tests

The Unit Tests can all be run using the test suite: `com.interview.yoti.robot.TestSuite`.
