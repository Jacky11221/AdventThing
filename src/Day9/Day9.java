package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day9 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/Day9/day9Input.txt");
        System.out.println(fileData);
        System.out.println(quantizedData(fileData));

    }

    public static String replaceBlanks(ArrayList<String> data){
        for(int i = 0; i < data.size(); i++){
            if(data.get(i).equals(".")){
                //do stuff
            }
        }
        return "stuff";
    }

    public static String quantizedData(ArrayList<String> fileData){
        String data = fileData.getFirst();
        String quantized = "";
        for(int i = 0; i < data.length(); i++){
            int currentNumber = Integer.parseInt(data.substring(i,i+1));
            if(i%2 == 0){
                quantized += makeString(currentNumber, String.valueOf(i/2));
            }
            else{
                quantized += (makeString(currentNumber, "."));
            }
        }
        return quantized;
    }

    public static String makeString(int length, String str){
        String string = "";
        for(int i = 0; i < length; i++){
            string += str;
        }
        return string;
    }



    public static String[][] getFileDataAs2DArray(ArrayList<String> fileData){
        int rows = fileData.size();
        int columns = fileData.getFirst().length();
        String[][] grid = new String[rows][columns];

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                grid[r][c] = fileData.get(r).substring(c, c+1);
            }
        }
        return grid;
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

    public static String[] splitToArray(String str, String regex){
        return str.split(regex);
    }
}
