# hw2- Manual Review

The homework will be based on this project named "Expense Tracker",where users will be able to add/remove daily transaction. 

## Compile

To compile the code from terminal, use the following command:
```
cd src
javac ExpenseTrackerApp.java
java ExpenseTrackerApp
```

To the run the tests, please use the test runner framework from VS code. This repository has the .vscode folder settings and the library needed to the tests.


You should be able to view the GUI of the project upon successful compilation. 

## Java Version
This code is compiled with ```openjdk 17.0.7 2023-04-18```. Please update your JDK accordingly if you face any incompatibility issue.


## New Features 

We have added two new features

1) Filter by Category. Now you can use the filter by category button to display only the category that you typed into the field. This only supports 1 category to be filtered at a time.

2) Filter by Max Amount. You can filter by inputting a max amount. Once you press the filter button, the program will return all the transactions whose amount is less than or equal to the max amount that you have input