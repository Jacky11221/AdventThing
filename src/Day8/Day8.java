package Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Day8 {
    public static void main(String[] args) {
        solveDay8Part2("src/Day8/day8Input.txt");
        System.out.println("IM GONNA CRY (part 1): " + solveDay8Part1("src/Day8/day8Input.txt"));
        System.out.println("I HATE ARRAY BOUNDS (part 2): " + solveDay8Part2("src/Day8/day8Input.txt"));
    }

    public static int solveDay8Part1(String filePath){
        ArrayList<String> fileData = getFileData(filePath);
        String[][] fileData2D = getFileDataAs2DArray(fileData);
        ArrayList<String> symbols = getUniqueSymbols(fileData2D);

        for(String symbol: symbols){
            putAntiNodes(getCoordinatesForPoints(symbol, fileData2D), fileData2D);
        }
        return getNumberOfAntiNodes(fileData2D);
    }

    public static int solveDay8Part2(String filePath){
        ArrayList<String> fileData = getFileData(filePath);
        String[][] fileData2D = getFileDataAs2DArray(fileData);
        ArrayList<String> symbols = getUniqueSymbols(fileData2D);

        for(String symbol: symbols){
            putAntiNodesButForPart2(getCoordinatesForPoints(symbol, fileData2D), fileData2D);
        }

//        System.out.println(printArray(fileData2D,false));
//        System.out.println(printArray(fileData2D,true));
        return getNumberOfAntiNodes(fileData2D);
    }


    public static String printArray(String[][] fileData2D, boolean formatted){
        String stringing = "";
        if(formatted) {
            for (int r = 0; r < fileData2D.length; r++) {
                for (int c = 0; c < fileData2D[0].length; c++) {
                    if (fileData2D[r][c].charAt(0) != '.') {
                        stringing += fileData2D[r][c].substring(0, 1);
                    } else stringing += fileData2D[r][c].substring(fileData2D[r][c].length() - 1);
                }
                stringing += "\n";
            }
        }
        else{
            for (int r = 0; r < fileData2D.length; r++) {
                for (int c = 0; c < fileData2D[0].length; c++) {
                    stringing += fileData2D[r][c];
                }
                stringing += "\n";
            }
        }
        return stringing;
    }

    public static int getNumberOfAntiNodes(String[][] fileData2D){
        int count = 0;
        for (int r = 0; r < fileData2D.length; r++) {
            for (int c = 0; c < fileData2D[0].length; c++) {
                if(Objects.equals(fileData2D[r][c].substring(fileData2D[r][c].length()-1), "#")) count++;
            }
        }
        return count;
    }

    public static void putAntiNodesButForPart2(ArrayList<String> coordinates, String[][] fileData2D){

        for(int i = 0; i < coordinates.size(); i++){
            String[] xy1 = splitToArray(coordinates.get(i),",");
            for(int j = i+1; j < coordinates.size(); j++){
                String[] xy2 = splitToArray(coordinates.get(j),",");

                int xDiff = getXDiff(xy1,xy2);
                int yDiff = getYDiff(xy1,xy2);
                double x1 = Double.parseDouble(xy1[0]);
                double y1 = Double.parseDouble(xy1[1]);
                double x2 = Double.parseDouble(xy2[0]);
                double y2 = Double.parseDouble(xy2[1]);
                double slope = slope(x1,x2,y1,y2);

                int[] startPos;
                if(slope < 0) {
                    startPos = getStartPos(Integer.parseInt(xy1[0]), Integer.parseInt(xy1[1]), xDiff, yDiff, true, fileData2D);
                }
                else startPos = getStartPos(Integer.parseInt(xy1[0]),Integer.parseInt(xy1[1]),xDiff,yDiff,false,fileData2D);

                int currentX = startPos[0];
                int currentY = startPos[1];

//                System.out.println("xy1: " + Arrays.toString(xy1));
//                System.out.println("xy2: " + Arrays.toString(xy2));
//                System.out.println("slope:" + slope);
//                System.out.println("xRateOfChange:" + xDiff);
//                System.out.println("yRateOfChange:" + yDiff);
//                System.out.println("START POS:" + Arrays.toString(startPos));
//                System.out.println(" ");

                //the logic
                    while (currentX < fileData2D.length && currentY >= 0 && currentY < fileData2D[0].length) {
                        fileData2D[currentX][currentY] += "#";
                        currentX += xDiff;
                        currentY += yDiff;
                }
            }
        }
    }

    public static int[] getStartPos(int x, int y, int rateOfChangeX, int rateOfChangeY, boolean isSlopeNegative, String[][] fileData2D){
        int[] balls = new int[2];

        if(isSlopeNegative){
            while(x >= 0 && y < fileData2D[0].length){
                x -= rateOfChangeX;
                y -= rateOfChangeY;
            }
        }
        else {
            while (x >= 0 && y >= 0) {
                x -= rateOfChangeX;
                y -= rateOfChangeY;
            }
        }
        x += rateOfChangeX;
        y += rateOfChangeY;
        balls[0] = x;
        balls[1] = y;
        return balls;
    }

    public static void putAntiNodes(ArrayList<String> coordinates, String[][] fileData2D){

        for(int i = 0; i < coordinates.size(); i++){
            String[] xy1 = splitToArray(coordinates.get(i),",");
            for(int j = i+1; j < coordinates.size(); j++){
                String[] xy2 = splitToArray(coordinates.get(j),",");

                int xDiff = getXDiff(xy1,xy2);
                int yDiff = getYDiff(xy1,xy2);

                int newInterferenceX1 = (Integer.parseInt(xy1[0]) + xDiff*2);
                int newInterferenceY1 = (Integer.parseInt(xy1[1]) + yDiff*2);
                int newInterferenceX2 = (Integer.parseInt(xy2[0]) - xDiff*2);
                int newInterferenceY2 = (Integer.parseInt(xy2[1]) - yDiff*2);

                if(
                        newInterferenceX1 <= fileData2D.length - 1
                                && newInterferenceY1 <= fileData2D[0].length - 1
                                && newInterferenceX1 >= 0
                                && newInterferenceY1 >= 0) {
                    fileData2D[newInterferenceX1][newInterferenceY1] += "#";
                }
                if(
                        newInterferenceX2 <= fileData2D.length - 1
                                && newInterferenceY2 <= fileData2D[0].length - 1
                                && newInterferenceX2 >= 0
                                && newInterferenceY2 >= 0){
                    fileData2D[newInterferenceX2][newInterferenceY2] += "#";
                }
            }
        }
    }

    public static double slope(double x1,double x2,double y1, double y2){
        return (y2-y1)/(x2-x1);
    }

    public static int getXDiff(String[] xy1, String[] xy2){
        return Integer.parseInt(xy2[0]) - Integer.parseInt(xy1[0]);
    }

    public static int getYDiff(String[] xy1, String[] xy2){
        return Integer.parseInt(xy2[1]) - Integer.parseInt(xy1[1]);
    }

    public static ArrayList<String> getUniqueSymbols(String[][] fileData2D){
        ArrayList<String> symbols = new ArrayList<>();

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

    public static ArrayList<String> getCoordinatesForPoints(String symbol, String[][] grid){
        ArrayList<String> coordinates = new ArrayList<>();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (Objects.equals(grid[r][c].substring(0,1), symbol)) coordinates.add(r + "," + c);
            }
        }
        return coordinates;
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