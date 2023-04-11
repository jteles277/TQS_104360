package hm1.boundary;

// Spring Imports  
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.http.ParseException;

// Project Imports  
import hm1.data.Quality_Info;
import hm1.data.Statistics;
import hm1.service.AirService;

@RestController
@RequestMapping("/AQ")
public class Controller {
 
    private final AirService airService;
 
    private Statistics stats; 
 
    public Controller(AirService airService) {
        this.airService = airService;
        this.stats = new Statistics();
    }

    @CrossOrigin
    @GetMapping(path="/quality_by_city" )
    public ResponseEntity<Quality_Info>  QualityByCity(@RequestParam String region) throws URISyntaxException, IOException, ParseException, org.json.simple.parser.ParseException{

        HttpStatus status = HttpStatus.FOUND;
        Quality_Info info = airService.get_info(region,stats);

        return new ResponseEntity<>(info, status);
    }

    @CrossOrigin
    @GetMapping(path="/quality_by_coordinates" )
    public ResponseEntity<Quality_Info> QualityByCoordinates(@RequestParam float lat, @RequestParam float lon) throws URISyntaxException, IOException, ParseException, org.json.simple.parser.ParseException{

        HttpStatus status = HttpStatus.FOUND;
        Quality_Info info = airService.get_info(lat, lon,stats);
        
        return new ResponseEntity<>(info, status);
    }

    @CrossOrigin
    @GetMapping(path="/cache_statistics" )
    public ResponseEntity<Statistics> CacheStatistics() throws URISyntaxException, IOException, ParseException, org.json.simple.parser.ParseException{

        HttpStatus status = HttpStatus.FOUND;   

        return new ResponseEntity<>(stats, status);
    } 
}
