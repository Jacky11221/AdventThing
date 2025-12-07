package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) {

        System.out.println("part 1:" + part1Ans("src/Day3/day3input.txt"));
        System.out.println("part 2:" + part2ans("src/Day3/day3input.txt"));
    }

    public static int part2ans(String path){
        ArrayList<String> allMatches = new ArrayList<String>();
        ArrayList<String> fileData = getFileData(path);
        StringBuilder searchString = new StringBuilder();

        for(String data: fileData){
            searchString.append(data);
        }

        String regex = "mul\\([0-9]{1,3},[0-9]{1,3}\\)|do\\(\\)|don't\\(\\)";
        Matcher m = Pattern.compile(regex).matcher(searchString);
        while (m.find()) {
            allMatches.add(m.group());
        }


        int sum = 0;
        boolean add = true;
        for(int i = 0; i < allMatches.size(); i++){

            if(allMatches.get(i).equals("don't()")) {
                add = false;
            }

            if(allMatches.get(i).equals("do()")) {
                add = true;
            }


            Matcher matchFirst = Pattern.compile("0").matcher("0");
            Matcher matchSecond = Pattern.compile("0").matcher("0");

            if(allMatches.get(i).substring(0,1).equals("m")) {
                String[] numbers = allMatches.get(i).split(",");
                matchFirst = Pattern.compile("[0-9]{1,3}").matcher(numbers[0]);
                matchSecond = Pattern.compile("[0-9]{1,3}").matcher(numbers[1]);
            }


            int firstNum = 0;
            int secondNum = 0;
            if(matchFirst.find()) {
                firstNum = Integer.parseInt(matchFirst.group());
            }
            if(matchSecond.find()) {
                secondNum = Integer.parseInt(matchSecond.group());
            }
            if(add) sum += firstNum * secondNum;
        }
        return sum;
    }


    public static int part1Ans(String path){
        ArrayList<String> fileData = getFileData(path);
        ArrayList<String> allMatches = new ArrayList<String>();
        StringBuilder searchString = new StringBuilder();


        for(String data: fileData){
            searchString.append(data);
        }

        String regex = "mul\\([0-9]{1,3},[0-9]{1,3}\\)";
        Matcher m = Pattern.compile(regex).matcher(searchString);
        while (m.find()) {
            allMatches.add(m.group());
        }


        int sum = 0;
        for(int i = 0; i < allMatches.size(); i++){
            String[] numbers = allMatches.get(i).split(",");
            Matcher matchFirst = Pattern.compile("[0-9]{1,3}").matcher(numbers[0]);
            Matcher matchSecond = Pattern.compile("[0-9]{1,3}").matcher(numbers[1]);


            int firstNum = 0;
            int secondNum = 0;
            if(matchFirst.find()) {
                firstNum = Integer.parseInt(matchFirst.group());
            }
            if(matchSecond.find()) {
                secondNum = Integer.parseInt(matchSecond.group());
            }
            sum += firstNum * secondNum;
        }
        return sum;
    }

    public static String[] splitToArray(String str){
        return str.split(" ");
    }

    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.isEmpty())
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}