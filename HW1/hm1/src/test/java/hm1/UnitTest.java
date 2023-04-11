package hm1;

import hm1.data.Quality_Info;
import hm1.data.MyRepository;
import hm1.data.Statistics;
import hm1.service.AirService;
import hm1.connection.BasicHttpClient;

//Spring Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// Junit Imports
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

//Java Imports 
import java.util.Iterator;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
 
public class UnitTest 
{   
 
    AirService service;
    Statistics stats; 
    BasicHttpClient httpClient;
    
  

    @Test
    public void test_geo_apiRequest () throws Exception
    { 
        this.httpClient = new BasicHttpClient();

        String apiResponse = this.httpClient.doHttpGet(this.httpClient.build_geo_request("Sangalhos"));  
        Quality_Info new_info = this.httpClient.parse_geo_response(apiResponse);  

        Assertions.assertEquals(new_info.getName(), "Sangalhos"); 
        Assertions.assertEquals(new_info.getCountry(), "PT"); 

    }

    @Test
    public void test_empty_geo_apiRequest () throws Exception
    { 
        this.httpClient = new BasicHttpClient();

        String apiResponse = this.httpClient.doHttpGet(this.httpClient.build_geo_request(""));  
        Quality_Info new_info = this.httpClient.parse_geo_response(apiResponse);  

        Assertions.assertEquals(new_info, null);  

    }

    @Test
    public void test_bad_geo_apiRequest () throws Exception
    { 
        this.httpClient = new BasicHttpClient();

        String apiResponse = this.httpClient.doHttpGet(this.httpClient.build_geo_request("dsafsdfdagdsaffaafea"));  
        Quality_Info new_info = this.httpClient.parse_geo_response(apiResponse);  

        Assertions.assertEquals(new_info, null);  

    }

    @Test
    public void test_air_apiRequest () throws Exception
    { 
        this.httpClient = new BasicHttpClient();

       // If not in Cache then request to External API and save on Cache  
       String apiResponse2 = this.httpClient.doHttpGet(this.httpClient.build_air_request(50,50));  
       Quality_Info extra_info = this.httpClient.parse_air_response(50,50, apiResponse2);  

       Assertions.assertEquals(extra_info.getScore(), 2); 

    }

   
} 
