package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Day9 {
    public static void main(String[] args) {
        ArrayList<String> fileData1 = getFileData("src/Day9/day9Input.txt");
        ArrayList<String> fileData2 = getFileData("src/Day9/day9Input.txt");

//        ArrayList<String> quantizedData1 = quantizeData(fileData1);
//        System.out.println("quantized1:" + quantizedData1);
//        ArrayList<String> astralQuantification = replaceBlanks(quantizedData1);
//        System.out.println("Part 1 ans:" + checkSum(astralQuantification));


        ArrayList<String> quantizedData2 = quantizeData(fileData2);
        System.out.println("quantized2:" + quantizedData2);
        ArrayList<String> designatedQuantification = designateData(quantizedData2);
        System.out.println("Part 2");

    }

    private static ArrayList<String> designateData(ArrayList<String> data) {
        for(int i = 0; i < data.size(); i++){
            if(Objects.equals(data.get(i), ".")){
                int blankDataLength = 0;
                int j = i;
                while (Objects.equals(data.get(j), ".")){
                    blankDataLength++;
                    j++;
                }
                i = j;
                System.out.println(blankDataLength);
                //add things here maybe

            }
        }
        return data;
    }

    public static long checkSum(ArrayList<String> data){
        long sum = 0;
        for(int i = 0; i < data.size(); i++){
            sum += (long) Integer.parseInt(data.get(i)) *i;
        }
        return sum;
    }

    public static ArrayList<String> replaceBlanks(ArrayList<String> data){
        for(int i = 0; i < data.size(); i++){
            if(data.get(i).equals(".")){
                if(Objects.equals(data.getLast(), ".")) {
                    data.removeLast();
                    i--;
                }
                else {
                    data.add(i, data.getLast());
                    data.remove(i + 1);
                    data.removeLast();
                }
            }
        }
        return data;
    }

    public static ArrayList<String> quantizeData(ArrayList<String> fileData){
        String data = fileData.getFirst();
        ArrayList<String> quantized = new ArrayList<String>();
        for(int i = 0; i < data.length(); i++){
            int currentNumber = Integer.parseInt(data.substring(i,i+1));
            if(i%2 == 0){
                for(int j = 0; j < currentNumber; j++){
                    quantized.add(String.valueOf(i/2));
                }
            }
            else{
                for(int j = 0; j < currentNumber; j++){
                    quantized.add(".");
                }
            }
        }
        return quantized;
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