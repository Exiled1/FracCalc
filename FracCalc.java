package fracCalc;

import java.sql.Statement;
import java.util.Scanner;

public class FracCalc {

    public static String fractionOne;
    public static String operator;
    public static String fractionTwo;
    public static int firstWholeFraction; //w1
    public static int secondWhole; //w2 rename this to secondWhole
    public static int numeratorOne; //n1
    public static int numeratorTwo; //n2
    public static int denomOne; //d1
    public static int denomTwo; //d2

    //numeratorOne =(w1*denomOne) + numeratorOne

    //Initialize Variables (^^^)
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter an expression (or type \"quit\"): ");
        //Gets the users first input or if it gets the quit function it leaves.
        fractionOne = userInput.next();
        //test to see if it's an actual fraction or if someone typed quit.

        if (fractionOne.equalsIgnoreCase("quit")) {
            System.out.println("Well why did you call me in the first place? ;-;");
        }
        while (!fractionOne.equalsIgnoreCase("quit")) {
            operator = userInput.next();
            fractionTwo = userInput.next();
            fractionCalculation(fractionOne, operator, fractionTwo);
            System.out.println("Please enter another expression! :) Or you can use \"quit\" to exit): ");
            fractionOne = userInput.next();
            if (fractionOne.equalsIgnoreCase("quit")) {
                System.out.println("Bye! :D");
            }
        } // While loop finishes here and continues to calculate things until the user says to quit

        // TODO: Read the input from the user and call produceAnswer with an equation

    }// YOU SHALL NOT PASS... the main :

    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"

    public static String produceAnswer(String fractionOne) { // It would be easier for Cp 2 to just make
        Scanner scanner = new Scanner(fractionOne);
        fractionOne = scanner.next();
        operator = scanner.next();
        fractionTwo = scanner.next();
        fractionCalculation(fractionOne, operator, fractionTwo);
        return fractionOne;
    }

//    public static String produceAnswer2(String fractionOne) {
//        // TODO: Implement this function to produce the solution to the input
//
////        fractionCalculation(fractionOne, operator, fractionTwo);
//        return "";
//    }
//
//    public static String produceAnswer3(String fractionOne) {
//        // TODO: Implement this function to produce the solution to the input
//
////        fractionCalculation(fractionOne, operator, fractionTwo);
//        return "";
//    }


    // TODO: Fill in the space below with any helper methods that you think you will need
    public static void fractionCalculation(String fractionOne, String operator, String fractionTwo) {
        //This is to get integer things from the stringed fractions

        /** testing fracOne for integer values*/

        if (fractionOne.contains("_")) { //If theres a mixed number (ex: 1_1/2) then...
            firstWholeFraction = Integer.parseInt(fractionOne.substring(0, fractionOne.indexOf("_")));

//            System.out.println(firstWholeFraction);
            // Mixed#1 takes the substring values that come from taking the substring of fractionOne and going until the first "_" sign
            // What happens then is that it's converted from a string into an integer
            // using Integer.parseInt.

            numeratorOne = Integer.parseInt(fractionOne.substring(fractionOne.indexOf("_") + 1, fractionOne.indexOf("/")));
//            System.out.println(numeratorOne);

            denomOne = Integer.parseInt(fractionOne.substring(fractionOne.indexOf("/") + 1));
//            System.out.println(denomOne);

            numeratorOne = (firstWholeFraction * denomOne) + numeratorOne; //Creates an improper fraction.
        } else if (fractionOne.contains("/")) { //If there wasn't a mixed number then just check for a regular fraction.
            numeratorOne = Integer.parseInt(fractionOne.substring(0, fractionOne.indexOf("/")));


            denomOne = Integer.parseInt(fractionOne.substring(fractionOne.indexOf("/") + 1));


        } else { //Then that means that theres just a whole number there.
            firstWholeFraction = Integer.parseInt(fractionOne.substring(0));
            numeratorOne = firstWholeFraction;
            denomOne = 1; //fix
//            System.out.println(numeratorOne);
        } // end of if, elif, & else.

        /**Test for the second fraction. to get values of number thingies */
        if (fractionTwo.contains("_")) { //Again checks for mixed fractions
            secondWhole = Integer.parseInt(fractionTwo.substring(0, fractionTwo.indexOf("_")));
            numeratorTwo = Integer.parseInt(fractionTwo.substring(fractionTwo.indexOf("_") + 1, fractionTwo.indexOf("/")));
            denomTwo = Integer.parseInt(fractionTwo.substring(fractionTwo.indexOf("/") + 1));
            numeratorTwo = secondWhole * denomTwo + numeratorTwo;
        } else if (fractionTwo.contains("/")) { //It has fractions :3
            numeratorTwo = Integer.parseInt(fractionTwo.substring(0, fractionTwo.indexOf("/")));
            denomTwo = Integer.parseInt(fractionTwo.substring(fractionTwo.indexOf("/") + 1));
        } else {
            secondWhole = Integer.parseInt(fractionTwo.substring(0));

            numeratorTwo = secondWhole;

            denomTwo = 1;

        } //ends the if, else if, and else statement
        calculate(numeratorOne, numeratorTwo, denomOne, denomTwo, operator);
    }


    public static void calculate(int numeratorOne, int numeratorTwo, int denomOne, int denomTwo, String operator) { //num1, den1, num2, den2, maybe rename
        if (operator.equals("+")) {
            System.out.println(add(numeratorOne, numeratorTwo, denomOne, denomTwo));
        } else if (operator.equals("-")) {
            numeratorTwo = -1 * numeratorTwo;
            System.out.println(add(numeratorOne, numeratorTwo, denomOne, denomTwo));
        } else if (operator.equals("*")) {
            System.out.println(multiply(numeratorOne, numeratorTwo, denomOne, denomTwo));
        } else {
            int x = numeratorTwo;
            int y = denomTwo;
            denomTwo = x;
            numeratorTwo = y;
            System.out.println(multiply(numeratorOne, numeratorTwo, denomOne, denomTwo));
        }
    }

    public static String add(int numeratorOne, int numeratorTwo, int denomOne, int denomTwo) {
        int newNumerator = (numeratorOne * denomTwo) + (numeratorTwo * denomOne);
        int newDenom = denomOne * denomTwo;
        int divisor = reduce(newNumerator, newDenom); //TODO Explain/comment code.

        newNumerator /= divisor;
        newDenom /= divisor;

        int intPortion = 0;  // Gets incremented for the amount of times the numerator is greater than the denominator
        // One thing it doesnt do is take off the _ if 2 whole numbers are being added to each other.

        while (newNumerator >= newDenom) {
            intPortion++;
            newNumerator -= newDenom;
        }
        String finalAnswer = "";
        if (intPortion > 0) {
            finalAnswer += intPortion + "_";
        }
        if (newNumerator != 0) {
            finalAnswer += newNumerator + "/" + newDenom;
        }

        return finalAnswer;

    }

    public static String multiply(int numeratorOne, int numeratorTwo, int denomOne, int denomTwo) {
        int newNumerator = numeratorOne * numeratorTwo;
        int newDenom = denomOne * denomTwo;

        int divisor = reduce(newNumerator, newDenom);
        newNumerator = newNumerator / divisor;
        newDenom = newDenom / divisor;

        int intPortion = 0;

        while (newNumerator >= newDenom) {
            intPortion++;
            newNumerator = newNumerator - newDenom;
        }
        String finalAnswer = "";
        if (intPortion > 0) {
            finalAnswer += intPortion + "_"; // To keep the whole number stable
        }
        if (newNumerator != 0) {
            finalAnswer += newNumerator + "/" + newDenom;
        }
        return finalAnswer;
    }

//    public static int leastCommonDenom(int numeratorOne, int denomOne, int numeratorTwo, int denomTwo) {
//        int dividend = (denomOne * numeratorTwo) + (numeratorOne * denomTwo);
//        int divisor = denomOne * denomTwo;
//        int remainder = dividend % divisor;
//        while (remainder != 0) {
//            dividend = divisor;
//            divisor = remainder;
//            remainder = dividend % divisor;
//        } //Currently not used, but will be later
//
//        return divisor;
//    }

    public static int reduce(int newNumerator, int newDenom) {
        int newNumerator_absVal = Math.abs(newNumerator); //gets abs val of numerator
        int newDenom_absVal = Math.abs(newDenom); //Gets the absolute value of the dedom

        int minNum = Math.min(newNumerator_absVal, newDenom_absVal);

        int divisor = 1; // change

        for (int i = 1; i <= minNum; i++) {
            if (newNumerator % i == 0 && newDenom % i == 0) {
                divisor = i;
            } //Okay stop
        }

        /**
         * Process: 1) Can't simplify an improper frac, ex 3/2 wont simplify(Well, you can but it wont all the way)
         * step 1: Do numerator % denom; (aka: int whole = num % denom;)
         * step 2: Do numerator = numerator - (whole * denom)
         * (what it does: say you got 6/4, it makes your whole = 1, then it changes the numerator to 6 - (1*4),
         * that way it changes 6/4 to 1 2/4)
         *
         * then: ignore the whole number & try to simplify 2/4, finally outputting 1 1/2
         * Brought to you by Timber <3
         */
        return divisor;
    } //STOP REDUCING NOW!! :3

}
