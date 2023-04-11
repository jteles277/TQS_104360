# Additional tools for integration tests ((Test Containers, REST Assured))

## Context

While **unit tests** should focus on using the code with **minimal dependencies on collaborators**,
**integration tests** focus on **interactions** and, as such, usually require additional test setup. 

The **Test Containers** framework makes it easy to **deploy and run a container** for the specific objective of **running** a test and then **dispose** it. The lifecycle of the container is integrated in the runner environment. A common use case is **setting a dockerized database** to run the test on a specific database.

The integration tests class also includes **testing an API**. Spring Boot offers a good support, as seen in
Lab 3, but you can also use more generic testing libraries focused on REST API. A popular framework
is **RestAssured**.

## Rest Assure
- "Standart"   [7.1]
- SpringBoot Integration [7.2]

## Test Containers 

- Standart JPA init [7.4]
- DB migrations (flyway) [7.3]


Using Steps:
 
1. Spin the db container    -> at the **@Container** declaration
2. Init/set db state        -> 
3. Run the test             -> at the **@Test** declarations 
4. Cleanup (drop container) -> **automaticaly** at the end of the test
 