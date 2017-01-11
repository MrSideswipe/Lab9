package Lab9;

import org.json.JSONException;

public class Main 
{
	
	public static void main(String[] args) 
	{

	    try{
	    	Options options = new Options(args);
	        options.validate();
	        
	        Parliament parliament = new Parliament(options.getCadency());

	        for (int i = 1; i < 2; i++)
	        { //do 16
	            ConnectionWeb connection = new ConnectionWeb(options.linkGenerator() + i);
	            connection.download();
	            Parser parser;
	            if(options.getChoice().equals(Choice.DeputyAllCosts) || options.getChoice().equals(Choice.DeputyRepairs))
	            {
	                parser = new Parser(options.getChoice(), connection.getJsonResult(), options.getDeputyName());
	            }
	            else
	            {
	                parser = new Parser(options.getChoice(), connection.getJsonResult());
	            }

	            parser.parseMainJson();

	            parliament.pushDeputys(parser.getDeputys());
	        }

	        parliament.show();

	        switch(options.getChoice())
	        {
	        case DeputyAllCosts:
	        	parliament.getDeputy(0).generateTravelList();
	        	parliament.getDeputy(0).generateRepairList();
	        	System.out.println(parliament.getDeputy(0).getOutgoings());
	        	break;
	        case DeputyRepairs:
	        	parliament.getDeputy(0).generateRepairList();
	        	System.out.println(parliament.getDeputy(0).getRepairs());
	        	break;
	        case ParliamentAverageCosts:
	        	System.out.println(parliament.generateAverageOutgoings());
	        	break;
	        case DeputyMostJourneys:
	        	System.out.println(parliament.getDeputyMaxTravels());
	        	break;
	        case DeputyLongestJourneys:
	        	System.out.println(parliament.getDeputyMaxTimeAbroad());
	        	break;
	        case DeputyMostExpensiveJourney:
	        	System.out.println(parliament.getMostExpensiveTravel());
	        	break;
	        case ParliamentItalyJourneys:
	        	System.out.println(parliament.getDeputyInItaly());
	        	break;
	        default:
	        	break;
	        }
	    }
	    catch (IllegalArgumentException e)
	    {
	        System.out.println(e);
	        System.exit(1);
	    }
	    
	    catch (JSONException x)
	    {
	        System.out.println(x);
	        System.exit(1);
	    }
	}
}
