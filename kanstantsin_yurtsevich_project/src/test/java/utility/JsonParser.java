package utility;

import objects.Ingredients;
import objects.Recipe;
import objects.Search;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParser {
    private final static String JSON = "/home/married/IdeaProjects/kanstantsin_yurtsevich_htp_at_project/kanstantsin_yurtsevich_project/src/test/resources/recipe.json";
    File file = new File(JSON);

    public void parseJSON() throws IOException {
        String input = new String(Files.readAllBytes(Paths.get(JSON)));
        JSONObject obj = new JSONObject(input);
        System.out.println(obj.getString("recipename"));
    }

    public void parseGSON() throws FileNotFoundException {
        Gson gson = new Gson();
        Recipe recipe = gson.fromJson(new JsonReader(new FileReader(JSON)), Recipe.class);
        System.out.println(recipe.recipename);
    }

    public void parseJacson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Recipe recipe = mapper.readValue(file, Recipe.class);
        System.out.println(recipe.recipename);
    }

    public void fromGSON() throws FileNotFoundException {
        Gson gson = new Gson();
        Recipe recipe = new Recipe("Ice cream", new Ingredients[]{}, 120);
        System.out.println(gson.toJson(recipe));
    }
    public static String fromGson(Search search){
        Gson gson = new Gson();
        return gson.toJson(search);
    }
}

