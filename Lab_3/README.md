# 3.1

## a)


In D_EmployeeRestControllerIT.java
``` java
 mvc.perform(get("/api/employees").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].name", is("bob")))
                .andExpect(jsonPath("$[1].name", is("alex")));
```

## b) 
 

EmployeeRepository is mocked in B_EmployeeService_UnitTest:

``` java 
@Mock( lenient = true)
private EmployeeRepository employeeRepository;
```

Stubbing:
``` java
Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
``` 

## c) 
@Mock is used to mock simple class, but in the Spring context we have @MockBean so we can mock bean objects, this difference is important because Bean Object's lifecycle is managed by spring framework instead od ourselfs 

## d)  
 Used to configure the application properties for the integration tests, in this case is used to set up the database, username and password 

## e) 
C, D and E
This three aproaches are different in the way that they dive into testing the application.

**C** would be the most basic, by mocking services and API it only tests the actual controllers

**D** dives deeper, using all the real componens of the application, and so testing all the logic, making shure that the controller reacts well with the services

**E** is again more complex testing the real REST API with explicit
HTTP client creanting realitic requests 



# 3.2


# Notes:
- Have a more complex set of services 

- Create it from the top down (instead of the normal bottom up) 
```
Boundary
|
v
Service
|
v
Data
```
- Test it all the way tru (C -> B -> A -> E(D))  