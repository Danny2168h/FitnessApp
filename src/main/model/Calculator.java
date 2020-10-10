package model;


import java.util.ArrayList;
import java.util.List;

//does the calculations for the programs
//https://www.hsph.harvard.edu/nutritionsource/healthy-eating-plate/ source for ratios of food to eat
//https://www.healthline.com/nutrition/how-many-calories-per-day for calories to maintain weight
public class Calculator {

    public static final int MINIMUM_SAFE_CALORIES = 1200;
    private static final int ONE_KG_FAT_CALORIES = 7000;
    private static final int NORMAL_CALORIE_FOR_MALE = 2500;
    private static final int NORMAL_CALORIE_FOR_FEMALE = 2000;
    private static final double RATIO_OF_VEGETABLES = 0.5;
    private static final double RATIO_OF_GRAINS = 0.5;
    private static final double RATIO_OF_PROTEINS = 0.5;


    public Calculator() {
    }

    //REQUIRES: all integer inputs to be > 0
    //EFFECTS: produces true if
    public Boolean checkDietSafety(int goalWeight, int weight, int goalDays, boolean sex) {
        if (goalWeight >= weight) {
            return true;
        } else {
            int calorieDeficit = ONE_KG_FAT_CALORIES * (weight - goalWeight);
            int dailyCalorie = calorieDeficit / goalDays;

            if (sex) {
                return ((NORMAL_CALORIE_FOR_MALE - dailyCalorie) >= MINIMUM_SAFE_CALORIES);
            } else {
                return ((NORMAL_CALORIE_FOR_FEMALE - dailyCalorie) >= MINIMUM_SAFE_CALORIES);
            }
        }
    }

    public String recommendation(Person p) {
        int grainsMass = 0;
        int proteinMass = 0;
        int vegAndFruitMass = 0;
        int othersMass = 0;

        for (Food f : addAllFoods(p)) {
            switch (f.getType()) {
                case FRUITS:
                    vegAndFruitMass += f.getMass();
                    break;
                case PROTEINS:
                    proteinMass += f.getMass();
                    break;
                case VEGETABLES:
                    vegAndFruitMass += f.getMass();
                    break;
                case GRAINS:
                    grainsMass += f.getMass();
                    break;
                case OTHERS:
                    othersMass += f.getMass();
                    break;
            }
        }
        return "needs work";
    }

    private List<Food> addAllFoods(Person p) {

        List<Food> allFoods = new ArrayList<>();
        allFoods.addAll(p.getBreakfast());
        allFoods.addAll(p.getLunch());
        allFoods.addAll(p.getDinner());
        allFoods.addAll(p.getSnacks());

        return allFoods;
    }
}
