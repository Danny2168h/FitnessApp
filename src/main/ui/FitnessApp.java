package ui;

import model.*;

import java.util.List;
import java.util.Scanner;

public class FitnessApp {

    private Person person;
    private Scanner input;
    private Calculator calc;
    private Food food;
    private Exercise exercise;

    //EFFECTS: runs Fitness app application
    public FitnessApp() {
        runFitnessApp();
    }

    //MODIFIES: this
    //EFFECTS: processes user inputs
    //source: from TellerApp example
    private void runFitnessApp() {
        String cmd = null;

        init();
        System.out.println();
        System.out.println("Welcome " + person.getName() + " to your Personalized Fitness Tracker!");

        while (true) {
            displayMenu();
            cmd = input.next();

            if (cmd.equals("6")) {
                break;
            } else {
                processCommand(cmd);
            }
        }

        System.out.println("\nGoodbye!");
    }

    private void displayMenu() {
        System.out.println("\n                MAIN MENU");
        System.out.println("Goal:       Food:       Exercise:      Remaining:");
        System.out.println(calc.makeEquation(person));
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Add Food");
        System.out.println("\t2 -> View Foods");
        System.out.println("\t3 -> Add Exercise");
        System.out.println("\t4 -> View Exercises");
        System.out.println("\t5 -> Recommendation");
        System.out.println("\t6 -> Quit");
    }

    private void init() {
        input = new Scanner(System.in);
        calc = new Calculator();
        person = new Person();
        System.out.println("\nWelcome to your new healthy lifestyle! \nBefore we begin we will need some of your info");
        setPersonSex();
        //setPersonName();
        // setPersonAge();
        // setPersonHeight();
        setPersonWeight();
        setPersonGoal();
    }

    private void processCommand(String cmd) {
        if (cmd.equals("1")) {
            doAddFood();
        } else if (cmd.equals("2")) {
            doViewFoods();
        } else if (cmd.equals("3")) {
            doAddExercise();
        } else if (cmd.equals("4")) {
            doViewExercises();
        } else if (cmd.equals("5")) {
            doRecommend();
        } else {
            System.out.println("Selection is not valid...");
        }
    }

    private void doAddFood() {
        food = new Food();
        System.out.println("\nADDING FOOD");
        addFoodName();
        addFoodType();
        addFoodCal();
        addFoodMass();
        addFoodWhen();
        System.out.println(food.getName() + " has been successfully added to " + food.getTimeOfDay());
    }

    private void addFoodName() {
        System.out.println("\nENTER NAME OF FOOD:");
        String cmd = input.next();
        food.setName(cmd);
        System.out.println("Food name has been set to " + cmd);
    }


    private void addFoodCal() {
        System.out.println("\nENTER CALORIC VALUE OF FOOD:");
        int cmd = input.nextInt();
        while (0 > cmd) {
            System.out.println("Please enter a valid caloric amount");
            cmd = input.nextInt();
        }
        food.setCalories(cmd);
        System.out.println("Food calorie has been set to " + cmd + "cal");
    }

    private void addFoodWhen() {
        String cmd = "0";
        boolean run = true;
        while (run) {
            System.out.println("\nSelect meal:");
            System.out.println("\t1 -> Breakfast");
            System.out.println("\t2 -> Lunch");
            System.out.println("\t3 -> Dinner");
            System.out.println("\t4 -> Snacks");

            cmd = input.next();
            if (cmd.equals("1") || cmd.equals("2") || cmd.equals("3") || cmd.equals("4")) {
                System.out.println(processFoodType(cmd));
                run = false;
            } else {
                System.out.println("Selection invalid...");
            }
        }
    }

    private String processFoodType(String cmd) {
        String select = "";
        if (cmd.equals("1")) {
            food.setTimeOfDay("Breakfast");
            person.addBreakfast(food);
            select = "Selected Breakfast";
        } else if (cmd.equals("2")) {
            food.setTimeOfDay("Lunch");
            person.addLunch(food);
            select = "Selected Lunch";
        } else if (cmd.equals("3")) {
            food.setTimeOfDay("Dinner");
            person.addDinner(food);
            select = "Selected Dinner";
        } else if (cmd.equals("4")) {
            food.setTimeOfDay("Snacks");
            person.addSnacks(food);
            select = "Selected Snacks";
        }
        return select;
    }


    private void addFoodMass() {
        System.out.println("\nENTER MASS OF FOOD TO NEAREST GRAM:");
        int cmd = input.nextInt();
        while (0 > cmd) {
            System.out.println("Please enter a valid mass");
            cmd = input.nextInt();
        }
        food.setMass(cmd);
        System.out.println("Food mass has been set to " + cmd + "g");
    }


    private void addFoodType() {
        String cmd = "0";
        boolean run = true;
        while (run) {
            System.out.println("\nSelect one of the food categories:");
            System.out.println("\t1 -> Vegetables and Fruits");
            System.out.println("\t2 -> Protein");
            System.out.println("\t3 -> Grains");
            System.out.println("\t4 -> Others");

            cmd = input.next();
            if (cmd.equals("1") || cmd.equals("2") || cmd.equals("3") || cmd.equals("4")) {
                System.out.println(processFoodCmd(cmd));
                run = false;
            } else {
                System.out.println("Selection invalid...");
            }
        }
    }

    private String processFoodCmd(String cmd) {
        String select = "";
        if (cmd.equals("1")) {
            food.setType(FoodTypes.VEGETABLES_AND_FRUITS);
            select = "Selected Vegetables and Fruits";
        } else if (cmd.equals("2")) {
            food.setType(FoodTypes.PROTEINS);
            select = "Selected Protein";
        } else if (cmd.equals("3")) {
            food.setType(FoodTypes.GRAINS);
            select = "Selected Grains";
        } else if (cmd.equals("4")) {
            food.setType(FoodTypes.OTHERS);
            select = "Selected Others";
        }
        return select;
    }

    private void doViewFoods() {
        System.out.println("\nFOODS EATEN TODAY");
        String cmd = "0";
        boolean run = true;
        while (run) {
            System.out.println("\nSelect one of the following to view:");
            System.out.println("\t1 -> Breakfast");
            System.out.println("\t2 -> Lunch");
            System.out.println("\t3 -> Dinner");
            System.out.println("\t4 -> Snacks");

            cmd = input.next();
            if (cmd.equals("1") || cmd.equals("2") || cmd.equals("3") || cmd.equals("4")) {
                System.out.println(processViewFoods(cmd));
                System.out.println("\nEnter 1 to check other meals or any key to return to the main menu");
                cmd = input.next();
                if (!cmd.equals("1")) {
                    run = false;
                }
            } else {
                System.out.println("Selection invalid...");
            }
        }
    }


    private String processViewFoods(String cmd) {
        String select = "";
        if (cmd.equals("1")) {
            select = viewBreakfast();
        } else if (cmd.equals("2")) {
            select = viewLunch();
        } else if (cmd.equals("3")) {
            select = viewDinner();
        } else if (cmd.equals("4")) {
            select = viewSnacks();
        }
        return select;
    }

    private String viewBreakfast() {
        List<Food> breakfast = person.getBreakfast();
        if (breakfast.isEmpty()) {
            return "You have no items in breakfast";
        }
        String foods = "";
        foods = foods.concat("YOUR BREAKFAST ITEMS:");
        for (Food f : breakfast) {
            String oneFood = "Name: " + f.getName() + "   " + "Type: " + f.typeToString() + "   " + "Calories: "
                    + f.getCalories() + "   " + "Mass: " + f.getMass();
            foods = foods.concat("\n" + oneFood);
        }
        return foods;
    }

    private String viewLunch() {
        List<Food> lunch = person.getLunch();
        if (lunch.isEmpty()) {
            return "You have no items in lunch";
        }
        String foods = "";
        foods = foods.concat("YOUR LUNCH ITEMS:");
        for (Food f : lunch) {
            String oneFood = "Name: " + f.getName() + "   " + "Type: " + f.typeToString() + "   " + "Calories: "
                    + f.getCalories() + "   " + "Mass(g): " + f.getMass();
            foods = foods.concat("\n" + oneFood);
        }
        return foods;
    }

    private String viewDinner() {
        List<Food> dinner = person.getDinner();
        if (dinner.isEmpty()) {
            return "You have no items in dinner";
        }
        String foods = "";
        foods = foods.concat("YOUR DINNER ITEMS:");
        for (Food f : dinner) {
            String oneFood = "Name: " + f.getName() + "   " + "Type: " + f.typeToString() + "   " + "Calories: "
                    + f.getCalories() + "   " + "Mass: " + f.getMass();
            foods = foods.concat("\n" + oneFood);
        }
        return foods;
    }

    private String viewSnacks() {
        List<Food> snacks = person.getSnacks();
        if (snacks.isEmpty()) {
            return "You have no items in snacks";
        }
        String foods = "";
        foods = foods.concat("YOUR DINNER ITEMS:");
        for (Food f : snacks) {
            String oneFood = "Name: " + f.getName() + "   " + "Type: " + f.typeToString() + "   " + "Calories: "
                    + f.getCalories() + "   " + "Mass: " + f.getMass();
            foods = foods.concat("\n" + oneFood);
        }
        return foods;
    }

    private void doRecommend() {
        System.out.println("\nPERSONALIZED RECOMMENDATIONS");
        String cmd = "";
        boolean run = true;
        while (run) {
            System.out.println("\nSelect one of the following categories:");
            System.out.println("\t1 -> Diet Recommendation");
            System.out.println("\t2 -> Exercise Recommendation");

            cmd = input.next();
            if (cmd.equals("1") || cmd.equals("2")) {
                System.out.println(processRecommendations(cmd));
                System.out.println("\nEnter 1 to get other recommendations or any key to return to main menu");
                cmd = input.next();
                if (!cmd.equals("1")) {
                    run = false;
                }
            } else {
                System.out.println("Selection invalid...");
            }
        }
    }

    private String processRecommendations(String cmd) {
        String select = "";
        if (cmd.equals("1")) {
            select = calc.recommendDiet(person);
        } else if (cmd.equals("2")) {
            select = calc.recommendExercise(person);
        }
        return select;
    }

    private void doViewExercises() {
        System.out.println("EXERCISES DONE TODAY:");
        List<Exercise> exercise = person.getExercises();
        if (exercise.isEmpty()) {
            System.out.println("You have not logged any exercises today");
        }
        String exer = "";
        for (Exercise exe : exercise) {
            String str = "Name: " + exe.getName() + "   " + "Time: " + exe.getTime() + "   " + "Calories Burned: "
                    + exe.getCalories();
            exer = exer.concat("\n" + str);
        }
        System.out.println(exer);
        System.out.println("\n Enter any key to return to main menu");
        input.next();
    }

    private void doAddExercise() {
        exercise = new Exercise();
        System.out.println("\nADDING EXERCISE");
        addExerciseName();
        addExerciseTime();
        addExerciseCal();
        person.addExercise(exercise);
        System.out.println(exercise.getName() + " has been successfully added to your exercises");
    }

    private void addExerciseCal() {
        System.out.println("\nENTER CALORIES BURNED:");
        int cmd = input.nextInt();
        while (0 > cmd) {
            System.out.println("Please enter a valid caloric amount");
            cmd = input.nextInt();
        }
        exercise.setCalories(cmd);
        System.out.println("Calories burned has been set to" + cmd + " cal");
    }

    private void addExerciseTime() {
        System.out.println("\nENTER TIME SPENT EXERCISING TO NEAREST MINUTE:");
        int cmd = input.nextInt();
        while (0 > cmd) {
            System.out.println("Please enter a valid amount of time");
            cmd = input.nextInt();
        }
        exercise.setTime(cmd);
        System.out.println("Exercise time has been set to " + cmd + " minutes");
    }

    private void addExerciseName() {
        System.out.println("\nENTER NAME OF EXERCISE:");
        String cmd = input.next();
        exercise.setName(cmd);
        System.out.println("Exercise name has been set to " + cmd);
    }


    private void setPersonGoal() {
        System.out.println();
        System.out.println("ENTER YOUR GOAL WEIGHT TO THE NEAREST KILOGRAM:");
        int cmd = input.nextInt();
        person.setGoalWeight(cmd);
        System.out.println("ENTER THE DAYS IN WHICH YOU WANT TO ACHIEVE GOAL (days > 0):");
        int cmd1 = input.nextInt();
        person.setGoalDays(cmd1);
        while (!calc.checkDietSafety(person)) {
            System.out.println("These goals are dangerous for your health, please enter a safer goal");
            System.out.println("RE-ENTER YOUR GOAL WEIGHT TO THE NEAREST KILOGRAM:");
            cmd = input.nextInt();
            person.setGoalWeight(cmd);
            System.out.println("RE-ENTER THE DAYS IN WHICH YOU WANT TO ACHIEVE GOAL (days > 0):");
            cmd1 = input.nextInt();
            person.setGoalDays(cmd1);
        }
        int cal = calc.calcDailyCal(person);
        person.setDailyRecCalories(cal);
        System.out.println("Your daily calorie goal has been set to: " + cal + " calories.");
    }


    private void setPersonSex() {
        System.out.println();
        System.out.println("SELECT FROM ONE OF: \n\t M for male \n\t F for female");
        String cmd = input.next();
        cmd = cmd.toLowerCase();
        while (!cmd.equals("m") && !cmd.equals("f")) {
            System.out.println("Not valid selection...");
            cmd = input.next();
            cmd = cmd.toLowerCase();
        }
        if (cmd.equals("m")) {
            person.setMale();
            System.out.println("You have set your gender to: Male");
            System.out.println();
        } else if (cmd.equals("f")) {
            person.setFemale();
            System.out.println("You have set your gender to: Female");
            System.out.println();
        }
    }

    private void setPersonHeight() {
        System.out.println();
        System.out.println("ENTER HEIGHT TO THE NEAREST CENTIMETER:");
        int cmd = input.nextInt();
        while (0 >= cmd) {
            System.out.println("Please enter a valid height");
            cmd = input.nextInt();
        }
        person.setHeight(cmd);
        System.out.println("\tYou have set your height to: " + cmd + "CM");
        System.out.println();
    }

    private void setPersonWeight() {
        System.out.println();
        System.out.println("ENTER YOUR WEIGHT TO THE NEAREST KILOGRAM:");
        int cmd = input.nextInt();
        while (0 >= cmd) {
            System.out.println("Please enter a valid weight");
            cmd = input.nextInt();
        }
        person.setStartWeight(cmd);
        System.out.println("\tYou have set your weight to: " + cmd + "KG");
        System.out.println();
    }

    private void setPersonAge() {
        System.out.println();
        System.out.println("ENTER YOUR AGE:");
        int cmd = input.nextInt();
        while (0 >= cmd) {
            System.out.println("Please enter a valid age");
            cmd = input.nextInt();
        }
        person.setAge(cmd);
        System.out.println("You have set your age to: " + cmd);
        System.out.println();
    }


    private void setPersonName() {
        System.out.println();
        System.out.println("ENTER YOUR NAME:");
        String cmd = input.next();
        person.setName(cmd);
        System.out.println("You have set your name to: " + cmd);
        System.out.println();
    }
}
