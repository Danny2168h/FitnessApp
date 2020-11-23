# Fitness Tracker

## Task 1 

***What will the application do?***

The fitness application will assist users in achieving their health related goals. It will keep track of
the foods consumed, and the physical activities performed during the day of the user. Based off of that information the
application will calculate the amount of calories the person has expended/consumed. A summary of how the app will run
is given below.
 
How the app will run:
- On Start, the app will ask users for their name, height, age, weight, gender and their goals.
- The app will then calculate a recommended daily calorie intake required to reach their goals.
- Users will then be able to add foods eaten to breakfast,lunch or snack categories.
- Users will also be able to add the physical activities they performed.

***Who will use it?***

People who want to make a healthy change in their lifestyle and want an application which can keep track
of their progress and make sure they can stick to their goals. 

***Why is this project of interest to you?***

Ever since COVID-19 I found that it has been increasingly difficult to achieve the same amount of physical activity in
any given day compared to pre COVID times. This is in part due to the fact that classes have been moved online which 
meant that I often found myself sitting at my desk for hours on end. By making a health app, I hope I can promote
people to strive towards a healthier lifestyle even during times like this.

## Task 2 
***User Stories Phase 1***

What the app should be able to do:
- The user will be able to create an account by inputting their information.
- The user will be able to add foods to breakfast, lunch, dinner or snack categories. (GRADE THIS)
- The user will be able to add physical activities performed. (GRADE THIS)
- The user will be able to view the foods they have eaten. (GRADE THIS)
- The user will be able to view the physical activities they have done. 
- The user will be able to view the caloric goal in the day, this should update as activities or foods are added. (GRADE THIS)
- The user will be able to get a recommendation for their diet based on the foods they have eaten.

- The user will be able to view their own personal information such as height weight etc.


***User Stories Phase 2***
- The user will be able to save their account information and all the foods along with exercises they have done to their
account.
- The user upon starting the program, will be allowed to load 1 out of 3 different account slots that contain 
information.

## PHASE 4

For task 2 of phase 4 I choose to include a type hierarchy in my code. The classes: AddExerciseMenu, AddFoodMenu, 
CreateAccountMenu and SetGoalMenu implement the Java ActionListener interface and each override the ActionPerformed abstract
method from the interface and provide different/unique implementations across the classes.

## Phase 4: Task 3
Reflection on overall project design
***Changes that I would make to my project***
- In my console based UI there are multiple methods that do the same thing and have the overall same implementation,
but only differ in a very small area. I could have abstracted away the behaviour into a helper method, to improve readability.

- As I had constructed a class to represent each menu in my GUI, some menus had much the same functionality, it would
have been more optimal to create a type hierarchy with a more abstract menu and each class could extend the abstract menu
to reduce code duplication and semantic coupling (for example like fonts and font sizes across different menus).

- The calculator class is not very cohesive, for example it is responsible for checking diet safety, calculating daily
calories and is also responsible for making recommendations. To improve cohesion each of these functions could be refactored
into their individual classes.

- I could have made use of more static constants more within my GUI code, for reasons of single point of control. As an example,
in my GUI I would have to modify each button's x and y coordinates to move the buttons to a different area on the panel. 
If I had made more use of static constants within my code, this would improve the ease of modification to my code in the 
future.

- Both food and exercise share some similar behaviour, such as having fields like name and calories. Introducing an
abstract superclass that had protected fields like name and calories with their respective setters and getters would 
have been optimal to improve overall readability and reduce redundancy in code.
