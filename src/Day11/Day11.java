package Day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day11 {
    public static void main(String[] args) {
        String[] fileData = splitToArray(getFileData("src/Day11/day11Input.txt").getFirst()," ");
        ArrayList<String> fileData1 = new ArrayList<>(Arrays.asList(fileData));
        System.out.println(fileData1);


        for(int i = 0; i < 75; i++){
            fileData1 = testing((ArrayList<String>) fileData1.clone());
//            System.out.println(fileData1);
        }
        System.out.println(fileData1.size());

    }

    public static ArrayList<String> testing(ArrayList<String> fileData){
        ArrayList<String> overflow = new ArrayList<>();

        for(String data: fileData){
            if(Long.parseLong(data) == 0) overflow.add("1");
            else if(data.length() % 2 == 0){
                overflow.add(data.substring(0,data.length()/2));

                int otherHalf = Integer.parseInt(data.substring(data.length()/2));
                overflow.add(String.valueOf(otherHalf));
            }
            else{
                overflow.add(String.valueOf(Long.parseLong(data)*2024));
            }
        }
        return overflow;
    }

    public static String[] splitToArray(String str, String regex){
        return str.split(regex);
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

