package com.eliseweir;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public final static String MENU = "menu";
    public final static String LOAN_RATE_CALCULATOR = "loanRateCalculator";
    public final static String SAVINGS_CALCULATOR = "savingsCalculator";
    public final static String MORTGAGE_CALCULATOR = "mortgageCalculator";
    public final static String EXIT = "exit";

    public final static Map<String, String> commandsToUsage = Map.of(
            MENU, "usage: menu",
            LOAN_RATE_CALCULATOR, "usage: loanRateCalculator <loanTermInYears>",
            SAVINGS_CALCULATOR, "usage: savingsCalculator <credits separated by ','> <debits separated by ','>",
            MORTGAGE_CALCULATOR, "usage: mortgageCalculator <loanAmount> <termInYears> <annualRate as %>",
            EXIT, "usage: exit"
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        String command;
        String[] commands;

        System.out.println("Type 'menu' to see available commands, 'exit' to exit");

        do {
            // prompt user for a command
            System.out.print("$ ");
            input = scanner.nextLine();
            commands = input.split(" ");

            // validate command
            command = commands[0];
            while(!commandsToUsage.containsKey(command)) {
                System.out.println(command + ": command not found");
                System.out.println("Type 'menu' to see available commands, 'exit' to exit");
                input = scanner.nextLine();
                commands = input.split(" ");
                if (commands.length < 1) command = "";
                else command = commands[0];
            }

            // validate arguments for a given command
            boolean isValidCommand = validateCommandArguments(commands);
            if (!isValidCommand) {
                System.out.println(commandsToUsage.get(command));
            } else {
                // after validation, run the program
                executeCommand(command, Arrays.copyOfRange(commands, 1, commands.length));
            }

        } while(!command.equals(EXIT));

    }

    /**
     * executeCommand calls the appropriate calculator from the command line
     * @param command - name of calculator to use
     * @param arguments - parameters to pass in to the calculator (e.g. credits, loanAmount...)
     */
    private static void executeCommand(String command, String[] arguments) {
        switch(command) {
            case MENU:
                menu();
            case EXIT:
                break;
            case LOAN_RATE_CALCULATOR:
                System.out.println("Finding best loan rates...");
                LoanRateCalculator loanRateCalculator = new LoanRateCalculator(arguments[0]);
                loanRateCalculator.calculate();
                System.out.println(loanRateCalculator.toString());
                break;
            case SAVINGS_CALCULATOR:
                System.out.println("Finding your net savings...");
                SavingsCalculator savingsCalculator = new SavingsCalculator(arguments);
                savingsCalculator.calculate();
                System.out.println(savingsCalculator.toString());
                break;
            case MORTGAGE_CALCULATOR:
                System.out.println("Finding your monthly payment...");
                MortgageCalculator mortgageCalculator = new MortgageCalculator(arguments);
                mortgageCalculator.calculate();
                System.out.println(mortgageCalculator.toString());
                break;
            default:
                System.out.println("Unexpected calculator");
                break;
        }
    }

    /**
     * validateCommandArguments checks that the requested app has the right number of arguments
     * @param args
     * @return boolean indicating whether the number of command line arguments is correct
     */
    private static boolean validateCommandArguments(String[] args) {
        switch (args[0]) {
            case MENU:
                return true;
            case EXIT:
                return args.length == 1;
            case LOAN_RATE_CALCULATOR:
                return args.length == 2;
            case SAVINGS_CALCULATOR:
                return args.length == 3;
            case MORTGAGE_CALCULATOR:
                return args.length == 4;
            default:
                return false;
        }


    }

    private static void menu() {
        System.out.println("Available commands:");
        System.out.println(MENU);
        System.out.println(LOAN_RATE_CALCULATOR);
        System.out.println(MORTGAGE_CALCULATOR);
        System.out.println(SAVINGS_CALCULATOR);
        System.out.println(EXIT);
    }
}
