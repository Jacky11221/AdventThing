package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/Day4/day4input.txt");

        String[][] test = new String[fileData.size()][fileData.getFirst().length()];

//        System.out.println(Arrays.deepToString(test));
        for(int column = 0; column < fileData.size(); column++){
            for(int row = 0; row < fileData.get(column).length(); row++){
                test[row][column] = fileData.get(row).substring(column,column+1);
            }
        }
        System.out.println(Arrays.deepToString(test));

        int h= 0;

        for(int column = 0; column < test.length; column++){
            for (int row = 0; row < test[column].length; row++){
                System.out.println(surroundingHasLetter(test,column,row,"X"));
            }
        }
        System.out.println(h);
    }

        public static boolean surroundingHasLetter(String[][] arr, int column, int row, String targetLetter){

        try {
            if (arr[column - 1][row] == targetLetter) return true;
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("idc");
        }
        return false;
    }

//    public static int surroundingHasLetter(String[][] arr, int column, int row, int count, int strIndex){
//        String xmas = "XMAS";
////        System.out.println(strIndex);
//
//        if(strIndex == 4) {
//            return count + 1;
//        }
//
//        if(column != 0 && column != arr[column].length-1){
////            System.out.println(arr[column-1][row]);
//            System.out.println("current place:" + arr[column+1][row]);
//            System.out.println("current letter:" + xmas.substring(strIndex,strIndex + 1));
//
//
//            if(arr[column-1][row] == xmas.substring(strIndex,strIndex + 1)) {
//                System.out.println("true");
//                count += surroundingHasLetter(arr, column - 1, row, count, strIndex + 1);
//            }
//            if(arr[column+1][row] == xmas.substring(strIndex,strIndex + 1)) {
//                System.out.println("true");
//                count += surroundingHasLetter(arr, column + 1, row, count, strIndex + 1);
//            }
//        }
//
//        if(row != 0 && row != arr.length-1){
//            if(arr[column][row-1] == xmas.substring(strIndex,strIndex + 1)) {
//                System.out.println("true");
//                count += surroundingHasLetter(arr, column, row - 1, count, strIndex + 1);
//            }
//            if(arr[column][row+1] == xmas.substring(strIndex,strIndex + 1)) {
//                System.out.println("true");
//                count += surroundingHasLetter(arr, column, row + 1, count, strIndex + 1);
//            }
//        }
//        return 0;
//    }


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
