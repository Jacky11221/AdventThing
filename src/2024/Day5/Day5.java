package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
        ArrayList<String> brokenPages = new ArrayList<>();


        for(String pages: fileDataProduce){
            boolean works = true;
            for(String rule: fileDataRule){
                String firstPage = rule.substring(0,2);
                String secondPage = rule.substring(3,5);

                if(pages.contains(firstPage) && pages.contains(secondPage)) {
                    if (pages.indexOf(firstPage) < pages.indexOf(secondPage)) System.out.println(true);
                    else {
                        works = false;
                        System.out.println("this BREAKS THE RULES MEANING THIS LOOP WILL BREA");
                        break;
                    }
                }
            }
            if(works) dataAfterPassingRule.add(pages);
            else {
                brokenPages.add(pages);
            }
            System.out.println("end of page product");
        }

        int sum = 0;

        System.out.println("Works:" + dataAfterPassingRule);
        System.out.println("Broken" + brokenPages);

//
//        for(String data: dataAfterPassingRule) {
//            String[] arr = splitToArray(data, ",");
//            for(int i = 0; i < arr.length; i++){
//                if(arr.length/2 == i){
//                    sum += Integer.parseInt(arr[i]);
//                }
//            }
//        }
//
//        System.out.println(sum);
//        System.out.println(Arrays.toString(arr));
        solveDay5Part2();
    }


    public static int solveDay5Part2(){
        ArrayList<String> fileDataRule = getFileData("src/Day5/day5rulesinput.txt");
        ArrayList<String> fileDataProduce = getFileData("src/Day5/day5produceinput.txt");
        System.out.println(fileDataRule);
        System.out.println(fileDataProduce);

        ArrayList<String> dataAfterPassingRule = new ArrayList<>();
        ArrayList<String> brokenPages = new ArrayList<>();
        ArrayList<String[]> listOfBrokenPages = new ArrayList<>();


        for(String pages: fileDataProduce){
            boolean works = true;
            for(String rule: fileDataRule){
                String firstPage = rule.substring(0,2);
                String secondPage = rule.substring(3,5);

                if(pages.contains(firstPage) && pages.contains(secondPage)) {
                    if (!(pages.indexOf(firstPage) < pages.indexOf(secondPage))) {
                        works = false;
                        System.out.println("this BREAKS THE RULES MEANING THIS LOOP WILL BREA");
                        break;
                    }
                }
            }
            if(!works){
                String[] PLEASE = splitToArray(pages,",");
                listOfBrokenPages.add(PLEASE);
//                System.out.println(Arrays.toString(PLEASE));
            }
//            System.out.println("end of page product");
        }

        int sum = 0;

//        System.out.println("Works:" + dataAfterPassingRule);
        System.out.println("Broken" + listOfBrokenPages);
        ArrayList<ArrayList<String>> rulesForBrokenPages = new ArrayList<>();
        ArrayList<String> ruleForSingularBrokenPage = new ArrayList<>();


        int countI = 0;

        for (String[] brokenPage : listOfBrokenPages) {
            System.out.println(Arrays.toString(brokenPage));
            for (String rule : fileDataRule) {
                boolean firstPageExists = false;
                boolean secondPageExists = false;

                String firstPage = rule.substring(0, 2);
                String secondPage = rule.substring(3, 5);

                for (String s : brokenPage) {
                    if (s == firstPage) firstPageExists = true;
                    if (s == secondPage) secondPageExists = true;
                }
                if(firstPageExists && secondPageExists) ruleForSingularBrokenPage.add(rule);
            }
            ArrayList<String> stopBeingMutable = (ArrayList<String>) ruleForSingularBrokenPage.clone();
            ruleForSingularBrokenPage.clear();
            rulesForBrokenPages.add(stopBeingMutable);
            countI++;
        }
        System.out.println("rules " + rulesForBrokenPages);


        for(int i = 0; i < listOfBrokenPages.size(); i++){
            int middleIndex = listOfBrokenPages.get(i).length/2;

            for(int j = 0; j < rulesForBrokenPages.get(i).size(); j++){
                String[] thing = splitToArray(rulesForBrokenPages.get(i).get(j),"\\|");

            }
        }
        return 0;
    }

//    public static ArrayList<String> getRulesForIncorrectPages(String pages){
//
//    }




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



