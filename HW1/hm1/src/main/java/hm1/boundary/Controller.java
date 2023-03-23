package hm1.boundary;

// Spring Imports  
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;  

// Project Imports  
import hm1.data.Quality_Info;
import hm1.service.AirService;

@RestController
@RequestMapping("/AQ")
public class Controller {
 
    private final AirService airService;
 
    public Controller(AirService airService) {
        this.airService = airService;
    }

    @GetMapping(path="/quality_by_city" )
    public ResponseEntity<Quality_Info>  QualityByCity(@RequestParam String region) {

        HttpStatus status = HttpStatus.FOUND;
        Quality_Info info = airService.get_info(region);

        return new ResponseEntity<>(info, status);
    }
    @GetMapping(path="/quality_by_coordinates" )
    public ResponseEntity<Quality_Info> QualityByCoordinates(@RequestParam int lat, @RequestParam int lon){

        HttpStatus status = HttpStatus.FOUND;
        Quality_Info info = airService.get_info(lat, lon);

        return new ResponseEntity<>(info, status);
    }

}
