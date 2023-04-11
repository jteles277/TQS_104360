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

@SpringBootTest(classes = AirQualityApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest 
{   
  
    BasicHttpClient httpClient; 

    @Test
    public void test_apiRequest () throws Exception
    { 
        this.httpClient = new BasicHttpClient();
        // Get Coordinates
        String apiResponse = this.httpClient.doHttpGet(this.httpClient.build_geo_request("sangalhos"));  
        Quality_Info new_info = this.httpClient.parse_geo_response(apiResponse);   

        // If not in Cache then request to External API and save on Cache  
        String apiResponse2 = this.httpClient.doHttpGet(this.httpClient.build_air_request(new_info.getLat(),new_info.getLon()));  
        Quality_Info extra_info = this.httpClient.parse_air_response(new_info.getLat(), new_info.getLon(), apiResponse2);   
        new_info.setScore(extra_info.getScore());
        new_info.updateComponents(extra_info.getNo2(),extra_info.getCo(),extra_info.getNo(),extra_info.getO3(),extra_info.getSo2(),extra_info.getPm2_5(),extra_info.getPm10(), extra_info.getNh3());

        Assertions.assertEquals(new_info.getName(), "Sangalhos"); 
        Assertions.assertEquals(new_info.getCountry(), "PT");   
        Assertions.assertEquals(extra_info.getScore(), 2); 

    } 
    
}
