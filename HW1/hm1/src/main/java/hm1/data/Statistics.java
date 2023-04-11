package hm1.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
 
public class Statistics { 
 
    private long calls;
    private long total_hits;
    private double hit_percentage;
 

   
    public Statistics(){
        this.calls = 0;
        this.total_hits = 0;
        this.hit_percentage = 0;
    }
     
    public void addcall(boolean hit){
         
        this.calls++;
        
        if(hit){total_hits++;} 

        System.out.println(this.hit_percentage);
        this.hit_percentage = ((double)total_hits/(double)calls) * 100;
    } 
    
    public long getCalls(){
        return this.calls;
    }

    public long getTotal_hits(){
        return this.total_hits;
    }
    public double getHit_percentage(){
        return this.hit_percentage;
    }
  
} 