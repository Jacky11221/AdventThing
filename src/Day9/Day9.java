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
        ArrayList<String> quantizedData2WithoutDots = getDataWithoutDots(quantizedData2);
        System.out.println(quantizedData2WithoutDots);

        ArrayList<ArrayList<String>> work = getPacketsOfData(quantizedData2WithoutDots);
        System.out.println(work);
        ArrayList<String> designatedQuantification = designateData(quantizedData2, work);
        System.out.println(designatedQuantification);
        System.out.println("Part 2");

    }

    private static ArrayList<String> getDataWithoutDots(ArrayList<String> data){
        ArrayList<String> newData = (ArrayList<String>) data.clone();
        for(int i = 0; i < newData.size(); i++){
            if(Objects.equals(newData.get(i), ".")){
                newData.remove(i);
                i--;
            }
        }
        return newData;
    }

    private static ArrayList<ArrayList<String>> getPacketsOfData(ArrayList<String> data){
        ArrayList<ArrayList<String>> packetsOfData = new ArrayList<>();
        ArrayList<String> dataNumbers = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            String designated = data.get(i);
            int j = i;
            while(Objects.equals(designated, data.get(j))){
                dataNumbers.add(data.get(j));
                j++;
                if(j > data.size()-1) break;
            }
            i = j-1;
            packetsOfData.add((ArrayList<String>) dataNumbers.clone());
            dataNumbers.clear();
        }
        System.out.println(packetsOfData);
        return packetsOfData;
    }

    private static ArrayList<String> designateData(ArrayList<String> data, ArrayList<ArrayList<String>> packets) {
        packets.reversed();
        for(int i = 0; i < data.size(); i++){
            if(Objects.equals(data.get(i), ".")){
                int blankDataLength = 0;
                int j = i;
                while (Objects.equals(data.get(j), ".")){
                    blankDataLength++;
                    j++;
                }
                System.out.println(blankDataLength);
                for(int packet = 0; packet < packets.size(); packet++){ //loop through packets to see if a packet fits

                    if(packets.get(packet).size() <= blankDataLength){ // checks if packet fits
                        int startOfBlank = i; // assigns start of blanks
                        for(int p = 0; p < packets.get(packet).size(); p++){ // loops through individual packet to replace blank space
                            data.add(startOfBlank, packets.get(packet).get(p)); // add
                            data.remove(startOfBlank + 1); // remove redundant .

                            for(int last = data.size() - 1; last > 0; last--){
                                if(Objects.equals(data.get(last), packets.get(packet).getFirst())){
//                                    for(int die = data.size() - 1;){
//                                        data.add(startOfBlank, packets.get(packet).get(p)); // add
//                                        data.remove(startOfBlank + 1); // remove redundant .
//                                    }
                                }
                            }

                            startOfBlank++; // iterates through all the numbers in individual packet
                        }
                        packets.removeFirst(); //removes packet as it was used
                    }
                }
                i = j; // sets index to j to skip over all dots (might be bad)
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