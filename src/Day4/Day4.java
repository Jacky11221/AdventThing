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

//        for(int column = 0; column < test.length; column++){
//            for (int row = 0; row < test[column].length; row++){
//                if(test[column][row] == "M"){
//
//                }
//            }
//        }
    }

//    public static boolean surroundingHasLetter(String[][] arr, int column, int row, String targetLetter){
//        if(column == 0 || row == 0 || column == arr[column].length || row == arr[row].length) return false;
//
//        if(     arr[column-1][row] == targetLetter ||
//                arr[column+1][row] == targetLetter ||
//                arr[column][row-1] == targetLetter ||
//                arr[column][row+1] == targetLetter ||
//                arr[column+1][row+1] == targetLetter ||
//                arr[column+1][row-1] == targetLetter ||
//                arr[column-1][row+1] == targetLetter ||
//                arr[column-1][row-1] == targetLetter){ return true;
//        }
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