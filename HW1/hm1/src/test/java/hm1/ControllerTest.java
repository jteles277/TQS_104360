 
import hm1.data.Quality_Info;
import hm1.data.Statistics;
import hm1.data.MyRepository;
import hm1.service.AirService;
import hm1.AirQualityApplication;

import hm1.boundary.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc; 

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext; 
import org.springframework.boot.test.context.SpringBootTest;

//Spring Imports
import org.springframework.beans.factory.annotation.Autowired;

// Junit Imports
import org.junit.jupiter.api.Test;

//Java Imports 
import java.util.Iterator;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest(classes = AirQualityApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ControllerTest 
{   
 
    @Autowired
    private MockMvc mvc;     

    @Autowired
    private WebApplicationContext context; 
    
    @MockBean
    private AirService service; 
     
    
    @Test
    public void test_cache_endpoint() throws Exception
    {
        
        mvc.perform(
            get("/AQ/cache_statistics").contentType(MediaType.APPLICATION_JSON) )
            .andExpect(status().isFound());         
    }

    @Test
    public void test_region_Service() throws Exception
    {
        Quality_Info basic_info = new Quality_Info("Sangalhos", "PT", 27, 27);
        Statistics stats = new Statistics();

        when( service.get_info("Sangalhos", stats) ).thenReturn( basic_info);

        mvc.perform(
                get("/AQ/quality_by_city?region=Sangalhos").contentType(MediaType.APPLICATION_JSON) )
                .andExpect(status().isFound());        

    }

    @Test
    public void test_coordinates_Service() throws Exception
    {
        Quality_Info basic_info = new Quality_Info(27, 27, 1); 
        Statistics stats = new Statistics();

        when( service.get_info(27, 27, stats) ).thenReturn(basic_info);

        mvc.perform(
                get("/AQ/quality_by_coordinates?lon=27&lat=27").contentType(MediaType.APPLICATION_JSON) )
                .andExpect(status().isFound()) ;
 
    }

    @Test
	public void test_no_params() throws Exception {
		String url = "/AQ/quality_by_city";

		mvc.perform(MockMvcRequestBuilders.get(url))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
 
    
}
 