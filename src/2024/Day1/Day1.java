package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {

//        System.out.println(solveAdventDay1Part1("src/Day1/day1Input.txt"));
        System.out.println(solveAdventDay1Part2("src/Day1/day1Part2Input.txt"));
    }

    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
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

    public static int solveAdventDay1Part1(String fileName){
        ArrayList<String> fileData = getFileData(fileName);


        ArrayList<String[]> convertedFileData = new ArrayList<>();
        ArrayList<Integer> listOne = new ArrayList<>();
        ArrayList<Integer> listTwo = new ArrayList<>();

        for(String data: fileData){
            convertedFileData.add(convertTheStringToStrArrayUsingSplit(data));
        }

        for (String[] strings : convertedFileData) {
            listOne.add(Integer.valueOf(strings[0]));
            listTwo.add(Integer.valueOf(strings[1]));
        }
        Collections.sort(listOne);
        Collections.sort(listTwo);

        int sum = 0;
        for(int i = 0; i < listOne.size(); i++){
            sum += Math.abs(listOne.get(i) - listTwo.get(i));
        }
        return sum;
    }

    public static int solveAdventDay1Part2(String fileName) {
        ArrayList<String> fileData = getFileData(fileName);

        ArrayList<String[]> convertedFileData = new ArrayList<>();
        ArrayList<Integer> listOne = new ArrayList<>();
        ArrayList<Integer> listTwo = new ArrayList<>();

        for(String data: fileData){
            convertedFileData.add(convertTheStringToStrArrayUsingSplit(data));
        }

        for (String[] strings : convertedFileData) {
            listOne.add(Integer.valueOf(strings[0]));
            listTwo.add(Integer.valueOf(strings[1]));
        }

        int sum = 0;
        for (Integer valueOne : listOne) {
            int count = 0;
            for (Integer valueTwo : listTwo) {
                if (Objects.equals(valueOne, valueTwo)) count++;
            }
            sum += count * valueOne;
        }
        return sum;
    }


    public static String[] convertTheStringToStrArrayUsingSplit(String str){
        return str.split("   ");
    }

}