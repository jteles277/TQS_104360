package tqs.ex_7_1;

// Junit Imports
import org.junit.jupiter.api.Test;

// Rest Assured Imports
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;   

// Hamcrest Imports 
import static org.hamcrest.Matchers.*; 
import static org.hamcrest.MatcherAssert.*;

public class API_Test 
{

    private String base_url = "https://jsonplaceholder.typicode.com"; 

    
    // the endpoint to list all ToDos is available (status code 200)
    @Test
    public void test_all_ToDos(){  

        given().when().get(base_url + "/todos")
        .then().assertThat().statusCode(200);
 
    }
    
    // when querying for ToDo #4, the API returns an object with title “et porro tempora”
    @Test
    public void test_ToDo_4(){
    
        String expected = "et porro tempora";

        given().when().get(base_url + "/todos/4")
        .then().assertThat().statusCode(200).body("title",equalTo(expected)); 
 
    }

    // When listing all “todos”, you get id #198 and #199 in the results.
    @Test
    public void test_all_ToDos_id(){
     

        given().when().get(base_url + "/todos")
        .then().assertThat().statusCode(200).body("id", hasItems(198 , 199)); 
 
    }
    
    // When listing all “todos”, you get the results in less then 2secs.
    @Test
    public void test_all_ToDos_response_time(){
        
        long wanted_time_insec = 2000;        
        long timeInSeconds = get(base_url+"/todos").time(); 

        assertThat(timeInSeconds < wanted_time_insec, is(true)); 
    }
}
