package Lab9;

import java.util.Arrays;
import java.util.HashMap;

public class Options 
{

    HashMap<String, Choice> choiceHashMap = new HashMap<>();
    private int numberInputs = 2;
    private Choice choice;
    private int cadency;
    private String deputyFirstName;
    private String deputyLastName;
    private Boolean solo = false;

    public Options(String[] args){
    	
    	this.numberInputs = args.length;
        
        switch(args[0])
        {
        	case "VII": this.cadency = 7; break;
        	case "VIII": this.cadency = 8; break;
        	default: break;
        }
        
        makeChoiceMap();
        
        if(args.length == 4)
        {
        	if (args[3].equals("suma") || args[3].equals("naprawy")){
        		this.solo = true;
        		this.deputyFirstName = args[1];
        		this.deputyLastName = args[2];
        		this.choice = this.choiceHashMap.get(args[3]);
        	}
        }
        
        else
        {
        	this.choice = this.choiceHashMap.get(args[1]);
        }
        
    }
    
    public void validate() throws IllegalArgumentException
    {

        if (numberInputs != 2 && numberInputs != 4)
            throw new IllegalArgumentException("Wrong number of arguments: should be 2 or 4");

        if (cadency != 7 && cadency != 8)
        	throw new IllegalArgumentException("Wrong arguments: cadency.");
        
        makeChoiceMap();

        if(numberInputs == 4)
        {
            if(!solo) 
                throw new IllegalArgumentException("Wrong arguments: wrong option to member.");
        }
        
        else
        {
            if(this.choice == null || this.choice == Choice.DeputyAllCosts || this.choice == Choice.DeputyRepairs) // !validateParliamentOption()
                throw new IllegalArgumentException("Wrong arguments: wrong global option");
        }
    }

    private void makeChoiceMap() 
    {
        choiceHashMap.put("suma", Choice.DeputyAllCosts);
        choiceHashMap.put("naprawy", Choice.DeputyRepairs);
        choiceHashMap.put("srednia", Choice.ParliamentAverageCosts);
        choiceHashMap.put("najwiecej", Choice.DeputyMostJourneys);
        choiceHashMap.put("najdluzej", Choice.DeputyLongestJourneys);
        choiceHashMap.put("najdrozsza", Choice.DeputyMostExpensiveJourney);
        choiceHashMap.put("Wlochy", Choice.ParliamentItalyJourneys);
    }

    public String getDeputyName() 
    {
        return deputyFirstName + " " + deputyLastName;
    }

    public Choice getChoice() 
    {
        return choice;
    }
    
    public int getCadency()
    {
        return cadency;
    }
    
    public String linkGenerator()
    {
        String link =
                "https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions%5Bposlowie.kadencja%5D=" + cadency + "&_type=objects&page=";
        return link;
    }
}
