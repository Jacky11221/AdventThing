package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/Day2/Day2Input.txt");
        ArrayList<String[]> fileDataList = new ArrayList<>();
        for(String data: fileData){
            fileDataList.add(splitToArray(data));
        }
        System.out.println("part 1: " + partOne(fileDataList));
        System.out.println("part 2: " + partTwo(fileDataList));
    }

    public static int partOne(ArrayList<String[]> fileDataList){

        int count = 0;
        for(String[] list: fileDataList){
            ArrayList<String> strings = new ArrayList<>(Arrays.asList(list));
            if(isSafe(strings)) count++;
        }
        return count;
    }



    public static int partTwo(ArrayList<String[]> fileDataList){

        int count = 0;
        for(String[] list: fileDataList){
            ArrayList<String> strings = new ArrayList<>(Arrays.asList(list));
            if(isSafe(strings)) count++;
            else {
                for(int i = 0; i < strings.size(); i++){
                    int removed = Integer.parseInt(strings.remove(i));
                    if(isSafe(strings)){
                        count++;
                        break;
                    }
                    strings.add(i, String.valueOf(removed));
                }
            }
        }
        return count;
    }


    public static boolean isSafe(ArrayList<String> list){
        int countPos = 0;
        int countNeg = 0;
        for(int i = 0; i < list.size()-1; i++){
            int diff = Math.abs(Integer.parseInt(list.get(i)) - Integer.parseInt(list.get(i+1)));
            if (diff > 3 || diff < 1) return false;

            if(Integer.parseInt(list.get(i)) > Integer.parseInt(list.get(i+1))) countNeg++;
            if(Integer.parseInt(list.get(i)) < Integer.parseInt(list.get(i+1))) countPos++;
        }
        return countPos == list.size() - 1 || countNeg == list.size() - 1;
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

    public static String[] splitToArray(String str){
        return str.split(" ");
    }

}