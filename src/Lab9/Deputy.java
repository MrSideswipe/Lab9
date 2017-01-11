package Lab9;

import java.util.ArrayList;

import org.json.JSONException;

public class Deputy 
{

	private String name;
	private String id;
	private ArrayList<Repair> repairList = new ArrayList<>();
    private ArrayList<Travel> travelList = new ArrayList<>();
    
    public Deputy(String name, String id)
    {
    	this.name = name;
    	this.id = id;
    }
    
    public void generateRepairList() throws JSONException
    {
        ConnectionWeb connection = new ConnectionWeb("https://api-v3.mojepanstwo.pl/dane/poslowie/" + id + ".json?layers[]=wydatki");
        connection.download();
        Parser parser = new Parser(connection.getJsonResult());
        parser.generatePersonalCosts();
        repairList.addAll(parser.getRepairList());
    }
    public void generateTravelList() throws JSONException
    {
        ConnectionWeb connection = new ConnectionWeb("https://api-v3.mojepanstwo.pl/dane/poslowie/" + id + ".json?layers[]=wyjazdy");
        connection.download();
        Parser parser = new Parser(connection.getJsonResult());
        parser.generateJourneys();
        travelList.addAll(parser.getTravelList());
    }

    public float getOutgoings()
    {
        float outgoings = 0;
        for (Repair repair : repairList)
        {
            outgoings += repair.getOutgo();
        }
        for (Travel travel: travelList) 
        {
        	outgoings += travel.getOutgo();
        }
        return outgoings;
    }
    
    public float getRepairs()
    {
        float repairs = 0;
        String description = "Koszty drobnych napraw i remontów lokalu biura poselskiego";
        for (Repair repair : repairList) 
        {
        	if(repair.getDescription().equals(description))
        	{
        		repairs += repair.getOutgo();
        	}
        }
        return repairs;
    }
    
    public int getNumberTravels()
    {
        int travels = 0;
        for (Travel travel : travelList) 
        {
        	travels++;
        }
        return travels;
    }
    
    public int getTimeAbroad()
    {
        int Abroad = 0;
        for (Travel travel : travelList)
        {
        	Abroad += travel.getTime();
        }
        return Abroad;
    }
    
    public double getMostExpensiveTravel()
    {
        double max = 0;
        for (Travel travel : travelList) 
        {
        	if (max < travel.getOutgo())
        	{
        		max = travel.getOutgo();
        	}
        }
        return max;
    }
    
    public boolean getInItaly() 
    {
        if (travelList != null)
        {
        	for (Travel travel : travelList) 
        	{
        		if (travel.getCountry().equals("W³ochy"))
        			return true;
            }
        }
        return false;
    }
    
    public String toString()
    {
        return "(" + id +  ") " + name;
    }
    

}
