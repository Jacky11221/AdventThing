package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5 {
    public static void main(String[] args) {
        ArrayList<String> fileDataRule = getFileData("src/Day5/day5rulesinput.txt");
        ArrayList<String> fileDataProduce = getFileData("src/Day5/day5produceinput.txt");
        System.out.println(fileDataRule);
        System.out.println(fileDataProduce);

        ArrayList<String> dataAfterPassingRule = new ArrayList<>();


        for(String pages: fileDataProduce){
            for(String rule: fileDataRule){
                String firstPage = rule.substring(0,2);
                String secondPage = rule.substring(3,5);
//                System.out.println(firstPage);
//                System.out.println(secondPage);
//
//
//                System.out.println(pages);
//                System.out.println(pages);
//
//                System.out.println(pages.indexOf(firstPage));
//                System.out.println(pages.indexOf(secondPage));

                if(pages.contains(firstPage) && pages.contains(secondPage)) {
                    if (pages.indexOf(firstPage) < pages.indexOf(secondPage)) System.out.println(true);
                    else {
                        System.out.println("this BREAKS THE RULES MEANING THIS LOOP WILL BREA");
                        break;
                    }
                    dataAfterPassingRule.add(pages);
                }
            }
            System.out.println("end of page product");
        }

        System.out.println(dataAfterPassingRule);



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

    public static ArrayList<String> doRegexThings(String searchString, String regex){
        ArrayList<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile(regex).matcher(searchString);
        while (m.find()) {
            allMatches.add(m.group());
        }
        return allMatches;
    }


}




