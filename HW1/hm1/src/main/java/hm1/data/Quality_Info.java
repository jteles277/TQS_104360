package hm1.data;

import org.springframework.data.annotation.Id; 
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash(timeToLive = 60L)
public class Quality_Info {
    
    @Id 
    private String Id;

    private float lon;
    private float lat;

    private String name;
    private String country;

    private int score;

    private double no2; 
    private double co; 
    private double no; 
    private double o3; 
    private double so2;
    private double pm2_5;  
    private double pm10;   
    private double nh3;


    public Quality_Info(){
        
    }

    public Quality_Info(float lon, float lat, int score) {
        this.lon = lon;
        this.lat = lat;

        this.Id = lon+""+lat+"";

        this.score = score;
    }

    public Quality_Info(String name, String country, float lon, float lat) {
        
        this.name = name;
        this.country = country; 
        this.lon = lon;
        this.lat = lat;
        this.Id = lon+""+lat+"";

    }
    
    public void updateComponents(double no2, double co, double no, double o3, double so2, double pm2_5, double pm10, double nh3){
        this.no2 = no2;
        this.co = co;
        this.no = no;
        this.o3 = o3;
        this.so2 = so2;
        this.pm2_5 = pm2_5;
        this.pm10 = pm10;
        this.nh3 = nh3;
    }

    public int getScore(){return score;} 
    public void setScore(int score){this.score=score;}     

    public String getId(){return Id;}
    
    public String getName(){return name;} 
    public String getCountry(){return country;} 
    public float getLon(){return lon;} 
    public float getLat(){return lat;} 
    
    public double getNo2(){return no2;} 
    public double getCo(){return co;} 
    public double getNo(){return no;} 
    public double getO3(){return o3;} 
    public double getSo2(){return so2;} 
    public double getPm2_5(){return pm2_5;} 
    public double getPm10(){return pm10;} 
    public double getNh3(){return nh3;} 

} 