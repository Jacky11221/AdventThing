package Day8;

import java.awt.image.PackedColorModel;
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

//        for(String ssymop: symbols){
//            System.out.println(getCoordsForPoints(ssymop,fileData2D));
//        }

        ArrayList<String> aCoords = getCoordsForPoints(symbols.get(1),fileData2D);



        System.out.println(aCoords);
        for(int i = 0; i < aCoords.size(); i++){
            String[] xy1 = splitToArray(aCoords.get(i),",");
            for(int j = i+1; j < aCoords.size(); j++){
                String[] xy2 = splitToArray(aCoords.get(j),",");


                System.out.println("xy1: " + Arrays.toString(xy1));
                System.out.println("xy2: " + Arrays.toString(xy2));

                int xDiff = Integer.parseInt(xy2[0]) - Integer.parseInt(xy1[0]);

            }
        }
//        for(int i = 0; i < aCoords.size()-1; i++){
//            String[] firstxy = splitToArray(aCoords.get(i),",");
//            String[] secondxy = splitToArray(aCoords.get(i+1),",");
//            System.out.println(
//                    (Integer.parseInt(secondxy[1])-Integer.parseInt(firstxy[1]))
//                            /(Integer.parseInt(secondxy[0])-Integer.parseInt(firstxy[0]))
//            );
//        }
    }


//    public static ArrayList<String> getSlope(ArrayList<String> coords){
//        for(int i = 0; i < coords.size()-1; i++){
//            String[] firstxy = splitToArray(coords.get(i),",");
//            String[] secondxy = splitToArray(coords.get(i+1),",");
//            System.out.println(
//                    (Integer.parseInt(secondxy[1])-Integer.parseInt(firstxy[1]))
//                            /(Integer.parseInt(secondxy[0])-Integer.parseInt(firstxy[0]))
//            );
//        }
//        return nothing;
//    }

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

    public static ArrayList<String> getCoordsForPoints(String symbol,String[][] grid){
        ArrayList<String> coords = new ArrayList<>();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (Objects.equals(grid[r][c], symbol)) coords.add(r + "," + c);
            }
        }
        return coords;
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

    public static String[] splitToArray(String str, String regex){
        return str.split(regex);
    }

}