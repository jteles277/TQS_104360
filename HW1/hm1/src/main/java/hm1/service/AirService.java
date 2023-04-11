package hm1.service;


// Spring Imports  
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

// Project Imports  
import hm1.data.Quality_Info;
import hm1.data.Statistics;
import hm1.data.MyRepository;
import hm1.connection.BasicHttpClient; 
import hm1.connection.ConfigUtils; 

import org.apache.http.ParseException;
import java.io.IOException;
import java.net.URISyntaxException;
 
import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Optional;

@Service 
public class AirService {
    
    
    @Autowired
    private MyRepository repository; 

    BasicHttpClient httpClient;

    public AirService(){
        this.httpClient = new BasicHttpClient();
    } 

    public Quality_Info get_info(String region, Statistics stats) throws URISyntaxException, IOException, ParseException, org.json.simple.parser.ParseException {   

        // Get Coordinates
        String apiResponse = this.httpClient.doHttpGet(this.httpClient.build_geo_request(region));  
        Quality_Info new_info = this.httpClient.parse_geo_response(apiResponse);  
        if(new_info == null){
            return null;
        }
        
        // Check in-Memory Cache  
        Optional<Quality_Info> cached = repository.findById(new_info.getLon()+"" + new_info.getLat());
        
        if (!cached.isEmpty()){
            stats.addcall(true);
            return cached.get();
        }
        
        // If not in Cache then request to External API and save on Cache  
        String apiResponse2 = this.httpClient.doHttpGet(this.httpClient.build_air_request(new_info.getLat(),new_info.getLon()));  
        Quality_Info extra_info = this.httpClient.parse_air_response(new_info.getLat(), new_info.getLon(), apiResponse2);  
        
        new_info.setScore(extra_info.getScore());
        new_info.updateComponents(extra_info.getNo2(),extra_info.getCo(),extra_info.getNo(),extra_info.getO3(),extra_info.getSo2(),extra_info.getPm2_5(),extra_info.getPm10(), extra_info.getNh3());
       
        stats.addcall(false);
        repository.save(new_info);

        return new_info;
    }
    public Quality_Info get_info(float lon, float lat, Statistics stats) throws URISyntaxException, IOException, ParseException, org.json.simple.parser.ParseException {

        // Check in-Memory Cache 

        // If not in Cache then request to External API and save on Cache 

        String apiResponse = this.httpClient.doHttpGet(this.httpClient.build_air_request(lat,lon));  
        Quality_Info new_info = this.httpClient.parse_air_response(lon, lat, apiResponse);  

        stats.addcall(true);
        repository.save(new_info);

        return new_info;
    }
}

