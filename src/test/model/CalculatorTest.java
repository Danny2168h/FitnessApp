package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Calculator test class
public class CalculatorTest {


    private Person person;
    private Calculator calc;
    private Food food;
    private Exercise exercise;
    private String advice;

    @BeforeEach
    void beforeEach() {
        person = new Person();
        food = new Food();
        calc = new Calculator();
        exercise = new Exercise();

        advice = "For a balanced diet, half of what you eat should be grains, a quarter should be proteins "
                + "and another quarter should be fruits and veggies"
                + "\nBelow are a list of suggestions to improve your diet:";
    }

    @Test
    void testCheckDietSafetyGoalWeightEqualStartWeight() {
        //Diet that maintains weight will always be safe

        person.setStartWeight(50);
        person.setGoalWeight(50);
        person.setGoalDays(100);

        //Testing Female, maintain weight
        person.setFemale();
        assertTrue(calc.checkDietSafety(person));

        //Testing Male, maintain weight
        person.setMale();
        assertTrue(calc.checkDietSafety(person));

    }

    @Test
    void testCheckDietSafetyGoalWeightMoreThanStartWeight() {
        //Diet that gains some weight will always be safe

        person.setStartWeight(50);
        person.setGoalWeight(51);
        person.setGoalDays(100);

        //Testing Female, gain weight
        person.setFemale();
        assertTrue(calc.checkDietSafety(person));

        //Testing Male, gain weight
        person.setMale();
        assertTrue(calc.checkDietSafety(person));

    }

    @Test
    void testCheckDietSafetyGoalLessThanStartWeightButSafe() {
        //Diet is safe if daily calories is greater than minimum daily calories
        double maxCalorieDeficitMale = calc.NORMAL_CALORIE_FOR_MALE - calc.MINIMUM_SAFE_CALORIES;
        double maxCalorieDeficitFemale = calc.NORMAL_CALORIE_FOR_FEMALE - calc.MINIMUM_SAFE_CALORIES;

        //Testing Female, lose weight but safe
        person.setStartWeight(100);
        person.setGoalDays(100);
        person.setGoalWeight(90);
        double minSafeDaysFemale  = (calc.ONE_KG_FAT_CALORIES * 10) / maxCalorieDeficitFemale;
        minSafeDaysFemale += 1;
        person.setGoalDays(minSafeDaysFemale);
        person.setFemale();
        assertTrue(calc.checkDietSafety(person));

        //Testing Male, lose weight but safe
        person.setMale();
        double safeDaysMale  = (calc.ONE_KG_FAT_CALORIES * 10) / maxCalorieDeficitMale;
        safeDaysMale += 1;
        person.setGoalDays(safeDaysMale);
        assertTrue(calc.checkDietSafety(person));
    }

    @Test
    void testCheckDietSafetyGoalLessThanStartWeightUnsafe() {
        //Diet is safe if daily calories is greater than minimum daily calories
        double maxCalorieDeficitMale = calc.NORMAL_CALORIE_FOR_MALE - calc.MINIMUM_SAFE_CALORIES;
        double maxCalorieDeficitFemale = calc.NORMAL_CALORIE_FOR_FEMALE - calc.MINIMUM_SAFE_CALORIES;

        //Testing Female, lose weight but safe
        person.setStartWeight(100);
        person.setGoalDays(100);
        person.setGoalWeight(90);
        double minSafeDaysFemale  = (calc.ONE_KG_FAT_CALORIES * 10) / maxCalorieDeficitFemale;
        minSafeDaysFemale -= 1;
        person.setGoalDays(minSafeDaysFemale);
        person.setFemale();
        assertFalse(calc.checkDietSafety(person));

        //Testing Male, lose weight but safe
        person.setMale();
        double safeDaysMale  = (calc.ONE_KG_FAT_CALORIES * 10) / maxCalorieDeficitMale;
        safeDaysMale -= 1;
        person.setGoalDays(safeDaysMale);
        assertFalse(calc.checkDietSafety(person));
    }

    @Test
    void testCalcDailyCalLoseWeight() {

        //Testing Female, calc calorie lose weight
        person.setStartWeight(100);
        person.setGoalDays(100);
        person.setGoalWeight(90);
        double calorieDeficit = calc.ONE_KG_FAT_CALORIES * (person.getStartWeight() - person.getGoalWeight());
        double dailyCalorie = calorieDeficit / person.getGoalDays();
        person.setFemale();
        assertEquals(calc.NORMAL_CALORIE_FOR_FEMALE - dailyCalorie, calc.calcDailyCal(person));

        //Testing Male, lose weight but safe
        person.setMale();
        assertEquals(calc.NORMAL_CALORIE_FOR_MALE - dailyCalorie, calc.calcDailyCal(person));
    }

    @Test
    void testCalcDailyCalGainWeight() {

        //Testing Female, calc calorie lose weight
        person.setStartWeight(100);
        person.setGoalDays(100);
        person.setGoalWeight(110);
        double calorieDeficit = calc.ONE_KG_FAT_CALORIES * (person.getStartWeight() - person.getGoalWeight());
        double dailyCalorie = calorieDeficit / person.getGoalDays();
        person.setFemale();
        assertEquals(calc.NORMAL_CALORIE_FOR_FEMALE - dailyCalorie, calc.calcDailyCal(person));

        //Testing Male, lose weight but safe
        person.setMale();
        assertEquals(calc.NORMAL_CALORIE_FOR_MALE - dailyCalorie, calc.calcDailyCal(person));
    }

    @Test
    void testMakeEquation() {
        person.setDailyRecCalories(1500);
        food.setCalories(200);
        food.setName("Cake");
        food.setMass(90);
        food.setType(FoodTypes.PROTEINS);
        food.setTimeOfDay("Breakfast");
        exercise.setCalories(200);
        exercise.setName("Running");
        exercise.setTime(60);
        person.addBreakfast(food);
        person.addExercise(exercise);

        String equation = 1500 + "    -    " + 200 + "      +      " + 200 + "      =      " + 1500;

        assertEquals(equation, calc.makeEquation(person));
    }

    @Test
    void testRecommendExerciseNotMeeting() {
        Exercise exercise1 = new Exercise();
        int lessThanHalf = (int) ((calc.RECOMMENDED_DAILY_EXERCISE_TIME / 2) - 1);
        exercise.setTime(lessThanHalf);
        exercise1.setTime(lessThanHalf);
        person.addExercise(exercise);
        person.addExercise(exercise1);

        String rec = "\nIt is recommended that you meet the daily exercise goal of "
                + calc.RECOMMENDED_DAILY_EXERCISE_TIME + " minutes." + "\n You currently need "
                + (calc.RECOMMENDED_DAILY_EXERCISE_TIME - 2 * lessThanHalf)
                + " more minutes of exercise, Good Luck!";

        assertEquals(rec, calc.recommendExercise(person));
    }

    @Test
    void testRecommendExerciseMeeting() {
        Exercise exercise1 = new Exercise();
        int half = (int) (calc.RECOMMENDED_DAILY_EXERCISE_TIME / 2);
        exercise.setTime(half);
        exercise1.setTime(half);
        person.addExercise(exercise);
        person.addExercise(exercise1);

        String rec = "You have meet the daily " + calc.RECOMMENDED_DAILY_EXERCISE_TIME
                + " minutes of exercise, Great Job!";

        assertEquals(rec, calc.recommendExercise(person));
    }

    @Test
    void testRecommendDietNoData() {
        assertEquals( "no data for diet recommendations", calc.recommendDiet(person));
    }

    @Test
    void testRecommendDietAllBalancedDiet() {
        Food food1 = new Food();
        Food food2 = new Food();
        Food food3 = new Food();

        int totalMass = 1000;
        food.setMass(totalMass * calc.RATIO_OF_GRAINS);
        food.setType(FoodTypes.GRAINS);

        food1.setMass(totalMass * calc.RATIO_OF_PROTEINS);
        food1.setType(FoodTypes.PROTEINS);

        food2.setMass(totalMass * calc.RATIO_OF_VEGETABLES_AND_FRUITS);
        food2.setType(FoodTypes.VEGETABLES_AND_FRUITS);

        double maxMinusOne = calc.MAX_AMOUNT_OF_OTHERS - 1;

        food3.setMass(maxMinusOne);
        food3.setType(FoodTypes.OTHERS);

        person.addBreakfast(food);
        person.addLunch(food1);
        person.addDinner(food2);
        person.addSnacks(food3);

        String recommendation = advice + "\n Great balance of grains in diet! Keep it up!"
                + "\n Great balance of veggies and fruits in diet! Good job!"
                + "\n Great balance of proteins in diet! Great work!"
                + "\n Great job on eating less than 150g of 'other' foods";

        assertEquals( recommendation, calc.recommendDiet(person));
    }

    @Test
    void testRecommendDietNonBalanceDietManyGrains() {
        Food food1 = new Food();
        Food food2 = new Food();
        Food food3 = new Food();

        int totalMass = 1000;
        food.setMass(totalMass * (calc.RATIO_OF_GRAINS + 0.25));
        food.setType(FoodTypes.GRAINS);

        food1.setMass(totalMass * (calc.RATIO_OF_PROTEINS - 0.125));
        food1.setType(FoodTypes.PROTEINS);

        food2.setMass(totalMass * (calc.RATIO_OF_VEGETABLES_AND_FRUITS - 0.125));
        food2.setType(FoodTypes.VEGETABLES_AND_FRUITS);

        food3.setMass(calc.MAX_AMOUNT_OF_OTHERS);
        food3.setType(FoodTypes.OTHERS);

        person.addBreakfast(food);
        person.addLunch(food1);
        person.addDinner(food2);
        person.addSnacks(food3);

        String recommendation = advice + "\n your diet needs a balance of grains"
                + "\n your diet needs to balance veggies and fruits diet"
                + "\n your diet needs a better balance of protein"
                + "\n Please consider eating less foods in 'other' category";

        assertEquals(recommendation, calc.recommendDiet(person));
    }

    @Test
    void testRecommendDietNonBalanceDietManyProtein() {
        Food food1 = new Food();
        Food food2 = new Food();

        int totalMass = 1000;
        food.setMass(totalMass * (calc.RATIO_OF_GRAINS - 0.25));
        food.setType(FoodTypes.GRAINS);

        food1.setMass(totalMass * (calc.RATIO_OF_PROTEINS + 0.5));
        food1.setType(FoodTypes.PROTEINS);

        food2.setMass(totalMass * (calc.RATIO_OF_VEGETABLES_AND_FRUITS - 0.25));
        food2.setType(FoodTypes.VEGETABLES_AND_FRUITS);

        person.addBreakfast(food);
        person.addLunch(food1);
        person.addDinner(food2);

        String recommendation = advice + "\n your diet needs a balance of grains"
                + "\n your diet needs to balance veggies and fruits diet"
                + "\n your diet needs a better balance of protein"
                + "\n Great job on eating less than 150g of 'other' foods";

        assertEquals(recommendation, calc.recommendDiet(person));
    }

    @Test
    void testRecommendDietNonBalanceDietManyVeggies() {
        Food food1 = new Food();
        Food food2 = new Food();

        int totalMass = 1000;
        food.setMass(totalMass * (calc.RATIO_OF_GRAINS - 0.25));
        food.setType(FoodTypes.GRAINS);

        food1.setMass(totalMass * (calc.RATIO_OF_PROTEINS - 0.25));
        food1.setType(FoodTypes.PROTEINS);

        food2.setMass(totalMass * (calc.RATIO_OF_VEGETABLES_AND_FRUITS + 0.5));
        food2.setType(FoodTypes.VEGETABLES_AND_FRUITS);

        person.addBreakfast(food);
        person.addLunch(food1);
        person.addDinner(food2);

        String recommendation = advice + "\n your diet needs a balance of grains"
                + "\n your diet needs to balance veggies and fruits diet"
                + "\n your diet needs a better balance of protein"
                + "\n Great job on eating less than 150g of 'other' foods";

        assertEquals(recommendation, calc.recommendDiet(person));
    }

}




