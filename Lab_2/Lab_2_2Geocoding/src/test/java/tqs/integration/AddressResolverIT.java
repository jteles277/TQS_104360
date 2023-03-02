package integration;

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

import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolver;

public class AddressResolverIT {

    TqsBasicHttpClient httpClient;
    @BeforeEach
    public void init(){
        httpClient = new TqsBasicHttpClient();
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {

        
        
        AddressResolver resolver = new AddressResolver(httpClient);

        Optional<Address> result = resolver.findAddressForLocation(30.333472, -81.470448);
 
        assertEquals( result.get(), new Address( "802 Arkenstone Dr", "Jacksonville", "FL", "32225", null) );

    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {

   
        AddressResolver resolver = new AddressResolver(httpClient);

        Optional<Address> result = resolver.findAddressForLocation(-300, -810);
 
        assertEquals(false, result.isPresent());
        
    }

}
