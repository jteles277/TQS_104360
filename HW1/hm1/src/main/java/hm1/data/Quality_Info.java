package hm1.data;

public class Quality_Info {

    private int score;
    private int PM10 = 0;
    private int CO = 0;
    private int NO2= 0;
    private int O3= 0;
    private int SO2= 0;

   
    public Quality_Info(int score) {
        this.score = score;
    }
    

    public int getScore(){return score;} 
    public void setScore(int score){this.score=score;}     

    public int getPM10(){return PM10;} 
    public int getCO(){return CO;} 
    public int getNO2(){return NO2;} 
    public int getO3(){return O3;} 
    public int getSO2(){return SO2;} 

}
