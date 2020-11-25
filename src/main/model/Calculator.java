package model;


import model.exceptions.InvalidMassException;
import model.exceptions.InvalidPersonException;

import java.util.ArrayList;
import java.util.List;

//Calculator class processes information from the other classes, it stores the methods that calculates diet safety,
//recommendation calculations etc.
//https://www.hsph.harvard.edu/nutritionsource/healthy-eating-plate/ source for ratios of food to eat
//https://www.healthline.com/nutrition/how-many-calories-per-day for calories to maintain weight
public class Calculator {

    public static final double MINIMUM_SAFE_CALORIES = 1200;
    public static final double ONE_KG_FAT_CALORIES = 7000;
    public static final double NORMAL_CALORIE_FOR_MALE = 2500;
    public static final double NORMAL_CALORIE_FOR_FEMALE = 2000;
    public static final double RECOMMENDED_DAILY_EXERCISE_TIME = 150;
    public static final double RATIO_OF_VEGETABLES_AND_FRUITS = 0.25;
    public static final double RATIO_OF_GRAINS = 0.5;
    public static final double RATIO_OF_PROTEINS = 0.25;
    public static final double MAX_AMOUNT_OF_OTHERS = 100;


    //EFFECTS: calculates diet safety given minimum daily calorie and produces true if diet is safe false otherwise.
    //         will also produce false if person has invalid fields.
    public boolean checkDietSafety(Person p) {
        if (p.getGoalDays() <= 0 || p.getGoalWeight() <= 0 || p.getStartWeight() <= 0) {
            return false;
        }
        if (p.getGoalWeight() >= p.getStartWeight()) {
            return true;
        } else {
            double calorieDeficit = ONE_KG_FAT_CALORIES * (p.getStartWeight() - p.getGoalWeight());
            double dailyCalorie = calorieDeficit / p.getGoalDays();

            if (p.getSex()) {
                return ((NORMAL_CALORIE_FOR_MALE - dailyCalorie) >= MINIMUM_SAFE_CALORIES);
            } else {
                return ((NORMAL_CALORIE_FOR_FEMALE - dailyCalorie) >= MINIMUM_SAFE_CALORIES);
            }
        }
    }

    //EFFECTS: Calculates the users daily allotted calories to achieve goal in timeframe set by user. Throw invalid
    //         person exception if person has invalid fields.
    public Double calcDailyCal(Person p) throws InvalidPersonException {
        if (p.getGoalDays() <= 0 || p.getGoalWeight() <= 0 || p.getStartWeight() <= 0) {
            throw new InvalidPersonException();
        }
        double calorieDeficit = ONE_KG_FAT_CALORIES * (p.getStartWeight() - p.getGoalWeight());
        double dailyCalorie = calorieDeficit / p.getGoalDays();

        if (p.getSex()) {
            return NORMAL_CALORIE_FOR_MALE - dailyCalorie;
        } else {
            return NORMAL_CALORIE_FOR_FEMALE - dailyCalorie;
        }
    }

    //EFFECTS: returns the equation in string form of goal calories - food + exercise = remaining calories
    public String makeEquation(Person person) {
        String equation = "";
        String recCal = Integer.toString((int) person.getDailyRecCalories());
        equation = equation.concat(recCal);
        equation = equation.concat("    -    ");
        String foodCalorie = Integer.toString(foodCalCalc(person));
        equation = equation.concat(foodCalorie);
        equation = equation.concat("      +      ");
        String exeCalorie = Integer.toString(exerciseCalCalc(person));
        equation = equation.concat(exeCalorie);
        equation = equation.concat("      =      ");
        String remaining = Integer.toString((int) (person.getDailyRecCalories() + exerciseCalCalc(person)
                - foodCalCalc(person)));
        equation = equation.concat(remaining);
        return equation;
    }

    //EFFECTS: returns string that represents the recommendation for exercise given users exercises
    public String recommendExercise(Person p) {
        int exerciseTime = 0;
        List<Exercise> loe = p.getExercises();
        for (Exercise e : loe) {
            exerciseTime += e.getTime();
        }

        if (exerciseTime < RECOMMENDED_DAILY_EXERCISE_TIME) {
            return "\nIt is recommended that you meet the daily exercise goal of " + RECOMMENDED_DAILY_EXERCISE_TIME
                    + " minutes." + "\n You currently need " + (RECOMMENDED_DAILY_EXERCISE_TIME - exerciseTime)
                    + " more minutes of exercise, Good Luck!";
        } else {
            return "You have meet the daily " + RECOMMENDED_DAILY_EXERCISE_TIME + " minutes of exercise, Great Job!";
        }
    }

    //EFFECTS: returns string that represents the recommendation for a users diet based on the foods they ate
    public String recommendDiet(Person p) {
        double grainsMass = 0;
        double proteinMass = 0;
        double vegAndFruitMass = 0;
        double othersMass = 0;
        for (Food f : addAllFoods(p)) {
            if (f.getType() == FoodTypes.VEGETABLES_AND_FRUITS) {
                vegAndFruitMass += f.getMass();
            } else if (f.getType() == FoodTypes.GRAINS) {
                grainsMass += f.getMass();
            } else if (f.getType() == FoodTypes.PROTEINS) {
                proteinMass += f.getMass();
            } else {
                othersMass += f.getMass();
            }
        }
        double total = grainsMass + proteinMass + vegAndFruitMass;
        try {
            return makeRecommendDiet(grainsMass, proteinMass, vegAndFruitMass, othersMass, total);
        } catch (InvalidMassException invalidMass) {
            return "no data for diet recommendations";
        }
    }

    //EFFECTS: returns string that is the recommendation for users diet
    //         if given mass = 0 then throw InvalidMassException
    private String makeRecommendDiet(double grainsMass, double proteinMass, double vegAndFruitMass, double othersMass,
                                     double total) throws InvalidMassException {
        if (total == 0) {
            throw new InvalidMassException();
        }
        String recommendations = advice();
        if ((RATIO_OF_GRAINS + 0.1) >= grainsMass / total && (RATIO_OF_GRAINS - 0.1) <= grainsMass / total) {
            recommendations = recommendations.concat("\n Great balance of grains in diet! Keep it up!");
        } else {
            recommendations = recommendations.concat("\n your diet needs a balance of grains");
        }
        if ((RATIO_OF_VEGETABLES_AND_FRUITS + 0.05) >= vegAndFruitMass / total && (RATIO_OF_VEGETABLES_AND_FRUITS
                - 0.05) <= vegAndFruitMass / total) {
            recommendations = recommendations.concat("\n Great balance of veggies and fruits in diet! Good job!");
        } else {
            recommendations = recommendations.concat("\n your diet needs to balance veggies and fruits diet");
        }
        if ((RATIO_OF_PROTEINS + 0.05) >= proteinMass / total && (RATIO_OF_PROTEINS - 0.05) <= proteinMass / total) {
            recommendations = recommendations.concat("\n Great balance of proteins in diet! Great work!");
        } else {
            recommendations = recommendations.concat("\n your diet needs a better balance of protein");
        }
        return finalRecommend(othersMass, recommendations);
    }


    //EFFECTS: appends final recommendation onto end of recommendations and returns it
    private String finalRecommend(double othersMass, String recommendations) {
        if (othersMass >= MAX_AMOUNT_OF_OTHERS) {
            recommendations = recommendations.concat("\n Please consider eating less foods in 'other' category");
        } else {
            recommendations = recommendations.concat("\n Great job on eating less than 150g of 'other' foods");
        }
        return recommendations;
    }

    //EFFECTS: returns a string that contains advice
    private String advice() {
        return "For a balanced diet, half of what you eat should be grains, a quarter should be proteins and another"
                + " quarter should be fruits and veggies" + "\nBelow are a list of suggestions to improve your diet:";
    }

    //EFFECTS: adds all foods from each meal into one single list and return that list
    private List<Food> addAllFoods(Person p) {

        List<Food> allFoods = new ArrayList<>();
        allFoods.addAll(p.getBreakfast());
        allFoods.addAll(p.getLunch());
        allFoods.addAll(p.getDinner());
        allFoods.addAll(p.getSnacks());

        return allFoods;
    }

    //EFFECTS: calculates the users total calories burned from all exercises combined and returns value
    public int exerciseCalCalc(Person p) {
        int exerCals = 0;
        for (Exercise pa : p.getExercises()) {
            exerCals += pa.getCalories();
        }
        return exerCals;

    }

    //EFFECTS: calculates the users total amount of calories from all foods eaten and returns value
    public int foodCalCalc(Person person) {
        int foodCals = 0;
        List<Food> allFood = addAllFoods(person);
        for (Food f : allFood) {
            foodCals += f.getCalories();
        }
        return foodCals;
    }
}
