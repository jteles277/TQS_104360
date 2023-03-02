package geocoding;

import static org.mockito.Mockito.when;

import java.util.Optional;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

import connection.ISimpleHttpClient;


@ExtendWith(MockitoExtension.class)
class AddressResolverTest {

     


    // 1. Prepare a mock to substitute the remote service (@Mock annotation)
    @Mock
    ISimpleHttpClient httpClient;

    @Test
    void whenResolveDetiGps_returnJacintoMagalhaeAddress() throws ParseException, IOException, URISyntaxException { 
        
        String json = "";

        try {

            File myObj = new File("json.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                json += myReader.nextLine(); 
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("err"); 
        }
        
        when(httpClient.doHttpGet("https://www.mapquestapi.com/geocoding/v1/reverse?key=RfVrYRVOIqYdR4IR0hTs9f0rKR9veVvg&location=30.333472%2C-81.470448")).thenReturn(json);
        AddressResolver resolver = new AddressResolver(httpClient);

        Optional<Address> result = resolver.findAddressForLocation(30.333472, -81.470448);
 
        assertEquals( result.get(), new Address( "12714 Ashley Melisse Blvd", "Jacksonville", "FL", "32225", null) );

    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddress() throws IOException, URISyntaxException, ParseException {

        
        
     
        String json = "";
        try {

            File myObj = new File("error_json.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                json += myReader.nextLine(); 
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("err"); 
        } 
        when(httpClient.doHttpGet("https://www.mapquestapi.com/geocoding/v1/reverse?key=RfVrYRVOIqYdR4IR0hTs9f0rKR9veVvg&location=-300.000000%2C-810.000000")).thenReturn(json);

        AddressResolver resolver = new AddressResolver(httpClient);

        Optional<Address> result = resolver.findAddressForLocation(-300, -810);
 
        assertEquals(false, result.isPresent());

    }
}

