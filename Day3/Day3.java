package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) {

        ArrayList<String> allMatches = new ArrayList<String>();
        String searchString = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
        String regex = "mul\\([1-9]{1,3},[1-9]{1,3}\\)";
        Matcher m = Pattern.compile(regex).matcher(searchString);
        while (m.find()) {
            allMatches.add(m.group());
        }


        for(int i = 0; i < allMatches.size(); i++){
            Matcher matchFirst = Pattern.compile("[1-9]{1,3}").matcher(allMatches.get(i));
            Matcher matchSecond = Pattern.compile("[1-9]{1,3}").matcher(allMatches.get(i));


            if(matchFirst.find()) System.out.println(matchFirst.group());
            if(matchSecond.find()) System.out.println(matchSecond.group());
        }
        System.out.println(allMatches);
    }


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