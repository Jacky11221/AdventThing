package Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Day8 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/Day8/day8Input.txt");
        String[][] fileData2D = getFileDataAs2DArray(fileData);
        ArrayList<String> symbols = getUniqueSymbols(fileData2D);
        System.out.println(Arrays.deepToString(fileData2D));
        System.out.println(symbols);



    }

    public static ArrayList<String> getUniqueSymbols(String[][] fileData2D){;
        ArrayList<String> symbols = new ArrayList<String>();

        for (int r = 0; r < fileData2D.length; r++) {
            for (int c = 0; c < fileData2D[0].length; c++) {
                boolean containsSymbol = false;
                boolean isDot = Objects.equals(fileData2D[r][c], ".");

                for(String symbol: symbols){
                    if (Objects.equals(fileData2D[r][c], symbol)) {
                        containsSymbol = true;
                        break;
                    }
                }

                if(symbols.isEmpty() && !isDot || !containsSymbol && !isDot){
                    symbols.add(fileData2D[r][c]);
                }
            }
        }
        return symbols;
    }

    public static String[][] getFileDataAs2DArray(ArrayList<String> fileData){
        int rows = fileData.size();
        int columns = fileData.get(0).length();
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
}

