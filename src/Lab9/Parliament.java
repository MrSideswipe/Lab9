package Lab9;

import java.util.ArrayList;

import org.json.JSONException;

public class Parliament
{
    private int cadency;
    private ArrayList<Deputy> deputyList = new ArrayList<>();

    public Parliament(int cadency)
    {
        this.cadency = cadency;
    }
    
    public void pushDeputys(ArrayList list)
    {
        deputyList.addAll(list);

    }
    
    public float generateAverageOutgoings() throws JSONException
    {
        float sum = 0;
        for (Deputy deputy : deputyList) 
        {
            deputy.generateRepairList();
            deputy.generateTravelList();
            sum += deputy.getOutgoings();
        }
        float average = sum/deputyList.size();
        return average;
    }
    
    public Deputy getDeputyMaxTravels() throws JSONException
    {
        Deputy max = deputyList.get(0);
        for (Deputy deputy: deputyList) 
        {
            deputy.generateTravelList();
            if(deputy.getNumberTravels() > max.getNumberTravels())
            {
                max = deputy;
            }
        }
        return max;
    }
    
    public Deputy getDeputyMaxTimeAbroad() throws JSONException
    {
        Deputy max = deputyList.get(0);
        for (Deputy deputy : deputyList) 
        {
            deputy.generateTravelList();
            if (deputy.getTimeAbroad() > max.getTimeAbroad())
            {
                max = deputy;
            }
        }
        return max;
    }
    
    public Deputy getMostExpensiveTravel() throws JSONException
    {
        Deputy max = deputyList.get(0);
        for (Deputy deputy : deputyList) 
        {
            deputy.generateTravelList();
            if (deputy.getMostExpensiveTravel() > max.getMostExpensiveTravel())
            {
                max = deputy;
            }
        }
        return max;
    }
    
    public ArrayList getDeputyInItaly() throws JSONException
    {
        ArrayList<Deputy> italy = new ArrayList<>();
        for (Deputy deputy : deputyList) 
        {
            deputy.generateTravelList();
            if (deputy.getInItaly())
                italy.add(deputy);
        }
        return italy;
    }
    
    
    public void show()
    {
        for (Deputy deputy: deputyList)
        {
            System.out.println(deputy);
        }
    }
    
    public Deputy getDeputy(int n)
    {
    	return deputyList.get(0);
    }
}