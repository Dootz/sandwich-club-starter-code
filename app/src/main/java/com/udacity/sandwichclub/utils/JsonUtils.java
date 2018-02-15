package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class JsonUtils {

    public static final String TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwichJSON = new JSONObject(json);
            JSONObject name = sandwichJSON.getJSONObject("name");
            String mainName = name.getString("mainName");
            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList<String>();
            for (int i=0; i<alsoKnownAsArray.length(); i++) {
                alsoKnownAs.add( alsoKnownAsArray.getString(i) );
            }
            String placeOfOrigin = sandwichJSON.getString("placeOfOrigin");

            String description = sandwichJSON.getString("description");
            String image = sandwichJSON.getString("image");
            JSONArray ingredientsArray = sandwichJSON.getJSONArray("ingredients");

            List<String> ingredients = new ArrayList<String>();
            for (int i=0; i<ingredientsArray.length(); i++) {
                ingredients.add( ingredientsArray.getString(i) );
            }
            Sandwich sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

            return sandwich;
        }
        catch(JSONException ex){
            Log.e(TAG, "Parsing error: " + ex.getMessage());
        }
        return null;
    }
}
