import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class SearchEngine{
    public static void main(String[] args) throws IOException{
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
        int port = Integer.parseInt(args[0]);
        Server.start(port, new Handler());
    }
}

class Handler implements URLHandler{

    ArrayList<String> strs = new ArrayList<>();

    public String handleRequest(URI url){

        if(url.getPath().equals("/")){
            return strs.toString();
        }

        if(url.getPath().contains("/add")){
            String[] inputStrs = url.getQuery().split("=");
            if (inputStrs[0].equals("s")){
                strs.add(inputStrs[1]);
                return inputStrs[1]; 
            }
        }

        if(url.getPath().contains("/search")){
            String[] inputStrs = url.getQuery().split("=");
            ArrayList<String> result = new ArrayList<>();
            String toSearch = inputStrs[1];
            for(String str: strs){
                if(str.toLowerCase().contains(toSearch.toLowerCase())){
                    result.add(str);
                    System.out.print(str);
                }
            }
            return result.toString();

        }
        return "404 not found";


    }





}