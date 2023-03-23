package hm1.service;

// Spring Imports  
import org.springframework.stereotype.Service;

// Project Imports  
import hm1.data.Quality_Info;

@Service
public class AirService {
    
    public Quality_Info get_info(String region){

        // Check in-Memory Cache 

        // If not in Cache then request to External API and save on Cache
        
        // If errror return null
        return new Quality_Info(2);
    }
    public Quality_Info get_info(int lon, int lat){

        // Check in-Memory Cache 

        // If not in Cache then request to External API and save on Cache
        
        // If errror return null
        return new Quality_Info(1);
    }
}

