package Lab9;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionWeb 
{

	private String jsonUrl;
    private String jsonResult = "";

    public ConnectionWeb(String jsonUrl)
    {
        this.jsonUrl = jsonUrl;
    }
    
    public void download()
    {
        System.out.println(jsonUrl);
        URL url = null;
        try {
            url = new URL(jsonUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String line="";
            while((line = bufferedReader.readLine()) != null)
            {
                jsonResult += line;
            }
            
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

        } 
        
        catch (MalformedURLException e) 
        {
            e.printStackTrace();
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public String getJsonResult()
    {
        return jsonResult;
    }
}
