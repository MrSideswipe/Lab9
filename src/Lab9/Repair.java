package Lab9;

public class Repair extends Outgo
{
	private String description;

    public Repair(String description, float outgo)
    {
    	this.outgo = outgo;
        this.description = description;
    }
    
    public String getDescription()
    { 
    	return description;
    }
}
