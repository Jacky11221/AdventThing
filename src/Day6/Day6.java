package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Day6 {
    public static void main(String[] args) {
        String[][] fileData2D = getFileDataAs2DArray(getFileData("src/Day6/day6Input.txt"));
        System.out.println(Arrays.deepToString(fileData2D));


        traverseArray(fileData2D);


    }

    public static void traverseArray(String[][] fileData2D) {
        try {
            int direction = 1;
            while (true) {
                int[] guardCoordinates = {0, 0};
                for (int r = 0; r < fileData2D.length; r++) {
                    for (int c = 0; c < fileData2D[0].length; c++) {
                        if (Objects.equals(fileData2D[r][c], "^")
                                || Objects.equals(fileData2D[r][c], ">")
                                || Objects.equals(fileData2D[r][c], "<")
                                || Objects.equals(fileData2D[r][c], "v")) {
                            guardCoordinates[0] = r;
                            guardCoordinates[1] = c;

                            System.out.println(printArray(fileData2D,false));

                            if (direction == 1) {
                                if (Objects.equals(fileData2D[r - 1][c], "#")) {
                                    direction++;
                                } else {
                                    fileData2D[r - 1][c] = fileData2D[r][c];
                                    fileData2D[r][c] = "X";
                                }
                            }


                            if (getGuardDirection(fileData2D, guardCoordinates) == 2) {
                                if (Objects.equals(fileData2D[r][c + 1], "#")) {
                                    direction++;

                                } else {
                                    fileData2D[r][c + 1] = fileData2D[r][c];
                                    fileData2D[r][c] = "X";
                                }
                            }

                            if (getGuardDirection(fileData2D, guardCoordinates) == 3) {
                                if (Objects.equals(fileData2D[r][c - 1], "#")) {
                                    direction++;

                                } else {
                                    fileData2D[r][c - 1] = fileData2D[r][c];
                                    fileData2D[r][c] = "X";
                                }
                            }

                            if (getGuardDirection(fileData2D, guardCoordinates) == 4) {
                                if (Objects.equals(fileData2D[r + 1][c], "#")) {
                                    direction = 1;

                                } else {
                                    fileData2D[r + 1][c] = fileData2D[r][c];
                                    fileData2D[r][c] = "X";
                                }
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            System.out.println("kys");
        }
    }

    public static int getGuardDirection(String[][] fileData2D, int[] guardCoordinates) {
        return switch (fileData2D[guardCoordinates[0]][guardCoordinates[1]]) {
            case "^" -> 1;
            case ">" -> 2;
            case "<" -> 3;
            default -> 4;
        };
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