package Lab9;

public class Travel extends Outgo 
{
	private int time;
    private String country;

    public Travel(String country, int time, double outgo)
    {
    	this.outgo = outgo;
        this.country = country;
        this.time = time;  
    }

    public String getCountry()
    {
    	return country;
    }
    
    public int getTime()
    {
    	return time;
    }
}
