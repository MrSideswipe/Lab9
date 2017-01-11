package Lab9;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;

public class Parser 
{
	
    private String jsonToParse;
    private String deputyName;
    private Choice choice;
    private ArrayList<Deputy> deputyList = new ArrayList<>();
    private ArrayList<Travel> travelList = new ArrayList<>();
    private ArrayList<Repair> repairList = new ArrayList<>();

    public Parser(String jsonToParse)
    {
        this.jsonToParse = jsonToParse;
    }
    
    public Parser(Choice choice, String jsonToParse)
    {
        this.choice = choice;
        this.jsonToParse = jsonToParse;
    }
    
    public Parser(Choice choice, String jsonToParse, String deputyName)
    {
        this.jsonToParse = jsonToParse;
        this.deputyName =deputyName;
        this.choice = choice;
    }
    
    public void parseMainJson() throws JSONException
    {

        if(jsonToParse != null)
        {
            JSONObject jsonObject = new JSONObject(jsonToParse);
            JSONArray jsonArray = jsonObject.getJSONArray("Dataobject");

            if(choice.equals(Choice.DeputyAllCosts) || choice.equals(Choice.DeputyRepairs))
            {
                for(int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject o = jsonArray.getJSONObject(i);
                    if(generateSingleMember(o))
                    {
                        return;
                    }
                }
                throw new IllegalArgumentException("Parser: the member doesnt exists");
            }
            else
            {
                for(int i = 0; i < jsonArray.length(); i++) 
                {
                    JSONObject o = jsonArray.getJSONObject(i);
                    generateMember(o);
                }
            }

        }
    }

    private void generateMember(JSONObject o) throws JSONException
    {
        JSONObject data = (JSONObject) o.get("data");
        String name = data.getString("ludzie.nazwa");
        String id = o.getString("id");

        Deputy deputy = new Deputy(name, id);
        deputyList.add(deputy);
    }

    private boolean generateSingleMember(JSONObject o) throws JSONException
    {
        JSONObject data = (JSONObject) o.get("data");
        String name = data.getString("ludzie.nazwa");
        String id = o.getString("id");

        if(name.equals(deputyName))
        {
            Deputy deputy = new Deputy(name, id);
            deputyList.add(deputy);
            return true;
        }
        return false;
    }

    public void generatePersonalCosts() throws JSONException
    {
        if (jsonToParse != null) {
            JSONObject jsonObject = new JSONObject(jsonToParse);
            JSONObject layers = (JSONObject) jsonObject.get("layers");
            JSONObject costs = (JSONObject) layers.get("wydatki");
            JSONArray costsArray = costs.getJSONArray("punkty");
            JSONArray yearsArray = costs.getJSONArray("roczniki");

            for (int i = 0; i < yearsArray.length(); i++)
            {
                JSONObject y = yearsArray.getJSONObject(i);

                for (int j = 0; j < costsArray.length(); j++)
                {
                    JSONArray values = y.getJSONArray("pola");
                    JSONObject description = costsArray.getJSONObject(j);

                    String title = description.getString("tytul");
                    float value = Float.parseFloat(values.getString(j));

                    Repair repair = new Repair(title, value);
                    repairList.add(repair);

                }
            }
        }
    }

    public void generateJourneys() throws JSONException
    {
        if (jsonToParse != null) 
        {
            JSONObject jsonObject = new JSONObject(jsonToParse);
            JSONObject layers = (JSONObject) jsonObject.get("layers");

            if (layers.get("wyjazdy") instanceof JSONArray) 
            {
                JSONArray journeys = layers.getJSONArray("wyjazdy");
                for (int i = 0; i < journeys.length(); i++) 
                {
                    JSONObject j = journeys.getJSONObject(i);

                    String country = j.getString("kraj");
                    int time = Integer.parseInt(j.getString("liczba_dni"));
                    double cost = Double.parseDouble(j.getString("koszt_suma"));

                    if (!country.equals("Polska"))
                        this.travelList.add(new Travel(country, time, cost));

                }
            }
        }
    }

    public ArrayList getDeputys()
    {
        return deputyList;
    }
    
    public ArrayList getRepairList() 
    { 
    	return repairList; 
    
    }
    public ArrayList getTravelList() 
    {
    	return travelList; 
    }
}

