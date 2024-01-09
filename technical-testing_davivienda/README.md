# Software Developer Engineer in Test - Test Automation Engineer

#### 1.2. INSTALLATION

The development should be focused on the use of **Linux** OS or **Unix** based systems, to make the installation of the dependencies effective and ensure the operation of the project in any **development**, **testing**, and **production** environment.

For **Windows** operating systems based on version **11**, the installation of the project is performed with these steps:

1. First install [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) as the base programming language of this repository, install [Maven 3.8.6](https://maven.apache.org/download.cgi) and create the environment variables for each of the above tools.
   To test the installation you should run the following commands in your preferred command terminal.
```sh
$ java --version
$ mvn --version
```

2. As this is an automation backend project developed with serenity bdd framework [Serenity](https://serenity-bdd.github.io/docs/tutorials/first_test), it is not necessary to install any extra tool, however, you should review the documentation to understand how it works.


### 1.3. How to run the tests?

Following these steps, we can perform the execution of the test suite or a specific feature:
```sh
$ mvn clean test verify
$ mvn clean verify -Dcucumber.filter.tags=@nombreTag
```

The test results will be recorded in the **target/site/serenity/index.html** directory.
