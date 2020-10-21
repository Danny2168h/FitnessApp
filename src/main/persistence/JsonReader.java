package persistence;

import model.Exercise;
import model.Food;
import model.FoodTypes;
import model.Person;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Represents a reader that reads person from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads person from file and returns it;
    // throws IOException if an error occurs reading data from file
    //Source: from workroom example
    public Person read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);

        return parsePerson(jsonObject);
    }

    //EFFECTS: produces true if file is empty, false otherwise
    public Boolean isEmptyFile() throws IOException {
        String jsonData = readFile(source);
        return jsonData.equals("{}");
    }

    // EFFECTS: reads source file and returns it as a string
    //Source: From workroom example
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: Parses person from JSON object and returns it
    private Person parsePerson(JSONObject jsonObject) {
        Person person = new Person();
        person.setName(jsonObject.getString("name"));
        person.setAge(jsonObject.getDouble("age"));
        person.setStartWeight(jsonObject.getDouble("startWeight"));
        person.setHeight(jsonObject.getDouble("height"));
        if (jsonObject.getBoolean("sex")) {
            person.setMale();
        } else {
            person.setFemale();
        }
        person.setGoalWeight(jsonObject.getDouble("goalWeight"));
        person.setGoalDays(jsonObject.getDouble("goalDays"));
        person.setDailyRecCalories(jsonObject.getDouble("dailyRecCalories"));

        person.setBreakfast(parseFoods("breakfast", jsonObject));
        person.setLunch(parseFoods("lunch", jsonObject));
        person.setDinner(parseFoods("dinner", jsonObject));
        person.setSnacks(parseFoods("snacks", jsonObject));

        person.setExercises(parseExercises("exercises", jsonObject));

        return person;
    }

    //EFFECTS: parses list of exercise from JSON object and returns it
    private List<Exercise> parseExercises(String exercise, JSONObject jsonObject) {
        JSONArray exercises = jsonObject.getJSONArray(exercise);
        List<Exercise> loe = new ArrayList<>();
        for (Object json : exercises) {
            loe.add(parseExercise((JSONObject) json));
        }
        return loe;
    }

    //EFFECTS: parses exercise from JSON object and returns it
    private Exercise parseExercise(JSONObject json) {
        Exercise exercise = new Exercise();
        exercise.setName(json.getString("name"));
        exercise.setCalories(json.getInt("calories"));
        exercise.setTime(json.getInt("time"));

        return exercise;
    }

    //EFFECTS: parses list of food from JSON object and returns it
    private List<Food> parseFoods(String mealTime, JSONObject jsonObject) {
        JSONArray foods = jsonObject.getJSONArray(mealTime);
        List<Food> lof = new ArrayList<>();
        for (Object json : foods) {
            lof.add(parseFood((JSONObject) json));
        }
        return lof;
    }

    //EFFECTS: parses food from JSON object and returns it
    private Food parseFood(JSONObject json) {
        Food food = new Food();
        food.setName(json.getString("name"));
        food.setCalories(json.getInt("calories"));
        FoodTypes type = FoodTypes.valueOf(json.getString("type"));
        food.setType(type);
        food.setMass(json.getInt("mass"));
        food.setTimeOfDay(json.getString("timeOfDay"));

        return food;
    }

}
