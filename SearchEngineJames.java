import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class SearchEngineHandler implements URLHandler{
    // An arraylist of Strings that can be manipulated by
    // various requests.
    ArrayList<String> listOfWords = new ArrayList<>();

    public String handleRequest(URI url) {

        System.out.println("Path: " + url.getPath());

        if (url.getPath().equals("/")) {
            return "Current Word List : " +  listOfWords.toString();
        }

        else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");

            if (parameters[0].equals("s")) {
                listOfWords.add(parameters[1]);
                return String.format("A new string (%s) is Added! There are %d strings in the word list", parameters[1], listOfWords.size());
            }

            return "404 Not Found!";
        }

        else if (url.getPath().contains("/search")) {

            String[] parameters = url.getQuery().split("=");

            if (parameters[0].equals("s")) {
                
                ArrayList<String> result = new ArrayList<>();

                String searchKey = parameters[1];

                for (String word : listOfWords) {
                    if (word.toLowerCase().contains(searchKey.toLowerCase())) {
                        
                        result.add(word);

                        System.out.println(word);
                    }
                }

                return "Search result : " + result.toString();
                
            }

            return "404 Not Found!";

        }

        else {
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new SearchEngineHandler());
    }
}