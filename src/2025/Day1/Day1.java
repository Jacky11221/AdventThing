package Day1;

import java.util.ArrayList;

import Test.AdventFileParser;
public class Day1 {

    public static int rotationalValue(String input){
        String[] tokens = input.split("");
        String direction = tokens[0];
        String magnitude = "";
        for(int i = 1; i < tokens.length; i++){
            magnitude += tokens[i];
        }
        int amountTurned = Integer.parseInt(magnitude);

        if(direction.equals("L")) return -amountTurned;
        else return amountTurned;
    }

    public static void solvePart1(){
        ArrayList<String> hi = AdventFileParser.getFileData("Day1Part1.txt",1);
        int startingValue = 50;
        int count = 0;
        for(String input : hi){
            startingValue += rotationalValue(input);
            if(startingValue > 99){
                while(startingValue > 99) startingValue -= 100;
            }
            else if(startingValue < 0){
                while(startingValue < 0) startingValue += 100;
            }
            if(startingValue == 0) count++;
        }
        System.out.println(count);
    }

    public static void solvePart2(){
        ArrayList<String> data = AdventFileParser.getFileData("Day1Part1.txt",1);
        int startingDial = 50;
        int count = 0;

        for(String move : data){
            int rotationalValue = rotationalValue(move);
            for(int i = 0; i < Math.abs(rotationalValue); i++){
                if(rotationalValue < 0) startingDial--;
                else startingDial++;

                if(startingDial == 100){
                    startingDial = 0;
                }
                if(startingDial == -1){
                    startingDial = 99;
                }
                if(startingDial == 0){
                    count++;
                }
            }
        }

        System.out.println("actual: " + count);
        System.out.println("expected: 10");
        System.out.println("dial: " + startingDial);
    }
    public static void main(String[] args) {
        solvePart2();
    }
}
