import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler{

    ArrayList<String> strs = new ArrayList<>();

    public String handleRequest(URI url){
        if(url.getPath().contains("/add")){
            String[] inputStrs = url.getQuery().split(":");
            if (inputStrs[0].equals("this")){
                strs.add(inputStrs[1]);
                return inputStrs[1]; 
            }
        }

        if(url.getPath().contains("/search")){
            String toSearch = new String();
            if(url.getPath().equals("/search")){
                String[] parameters = url.getQuery().split(" ");
                
            }           


        }

        return "404 not found";


    }





}