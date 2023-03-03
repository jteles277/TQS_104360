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
This file is used to define our own paths for the database for example so we can test the actual data access being done

## e) 
  








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