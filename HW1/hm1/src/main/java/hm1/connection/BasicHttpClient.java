package hm1.connection;

// Project Imports  
import hm1.data.Quality_Info;
import hm1.data.MyRepository;
import hm1.connection.BasicHttpClient; 
import hm1.connection.ConfigUtils; 

// HTTP Imports 
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.apache.http.ParseException;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONObject; 

import org.apache.http.ParseException;
import java.io.IOException;
import java.net.URISyntaxException;

// Java Imports 
import java.io.IOException;
 
public class BasicHttpClient{
 
    public String doHttpGet(String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = client.execute(request);
        try {
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } finally {
            if (response != null)
                response.close();
        }
    }

    public String build_air_request(float lat, float lon) throws URISyntaxException, IOException, ParseException, org.json.simple.parser.ParseException {

        String apiKey = ConfigUtils.getPropertyFromConfig("air_key");

        URIBuilder uriBuilder = new URIBuilder("http://api.openweathermap.org/data/2.5/air_pollution");
        uriBuilder.addParameter("lat", lat+"");
        uriBuilder.addParameter("lon", lon+"");
        uriBuilder.addParameter("appid", apiKey);   

        return uriBuilder.build().toString();
    }

    public String build_geo_request(String region) throws URISyntaxException, IOException, ParseException, org.json.simple.parser.ParseException {

        String apiKey = ConfigUtils.getPropertyFromConfig("air_key");

        URIBuilder uriBuilder = new URIBuilder("http://api.openweathermap.org/geo/1.0/direct");
        uriBuilder.addParameter("q", region); 
        uriBuilder.addParameter("appid", apiKey);   

        return uriBuilder.build().toString();
    }
    public Quality_Info parse_geo_response(String response) throws ParseException, org.json.simple.parser.ParseException{
        
        if (response.equals("{\"cod\":\"400\",\"message\":\"Nothing to geocode\"}")){
            return null;
        }
        // get parts from response till reaching the address
        JSONArray obj = (JSONArray) new JSONArray(response); 

        if (obj.length() == 0) {
            return null;
        } 
 
        JSONObject list = (JSONObject) obj.get(0);  

        String name =  (String) list.get("name");
        String country =  (String) list.get("country");
        double lon =  (double) list.getDouble("lon");
        double lat =  (double) list.getDouble("lat"); 

        return new Quality_Info(name, country, (float)lon, (float)lat);
    }
 
    public Quality_Info parse_air_response(float lon, float lat, String response) throws ParseException, org.json.simple.parser.ParseException{
        
        // get parts from response till reaching the address
        JSONObject obj = (JSONObject)  new JSONObject(response); 

        if (obj.getJSONArray("list") == null) {
            return null;
        } 
 
        JSONObject list = (JSONObject) ((JSONArray) obj.getJSONArray("list")).get(0); 

        int aqi = (int)  ((JSONObject) list.get("main")).get("aqi"); 

         
        JSONObject comp = (JSONObject) list.get("components");
        double no2 =  (double) comp.getDouble("no2");
        double co =  (double) comp.getDouble("co");
        double no =  (double) comp.getDouble("no");
        double o3 =  (double) comp.getDouble("o3");
        double so2 =  (double) comp.getDouble("so2");
        double pm2_5 =  (double) comp.getDouble("pm2_5");
        double pm10 =  (double) comp.getDouble("pm10");
        double nh3 =  (double) comp.getDouble("nh3");   
        
        Quality_Info info =  new Quality_Info(lon, lat, aqi);

        info.updateComponents(no2, co, no, o3, so2, pm2_5, pm10, nh3);

        return info;
    }


}
