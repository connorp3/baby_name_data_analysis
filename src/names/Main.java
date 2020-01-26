package names;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {

    public static void main (String[] args) throws IOException {

        String URL = "https://www2.cs.duke.edu/courses/spring20/compsci307d/assign/01_data/data/ssa_complete/";

        BabyData b = new BabyData(URL);
        System.out.println(b.yearlyNameRank("Rachel", "F"));



        /*BabyData babyData = new BabyData("C:\\Users\\conno\\Documents\\CS307\\data_cgp19\\data\\ssa_complete");
        Scanner scanner = new Scanner(System.in);

        BabyFile babyFile = new BabyFile("2018","C:\\Users\\conno\\Documents\\CS307\\data_cgp19\\data\\ssa_complete");
        babyFile.sortBabyFileIgnoringGender();
*/
        /*BabyData b2 = new BabyData("C:\\Users\\conno\\Documents\\CS307\\data_cgp19\\data\\test_data2");

        ArrayList<String> test1 = new ArrayList<String>(
                Arrays.asList("Adam", "3"));
        System.out.println(b2.MostFreqNameAtRankingBothGenders("2017", "2020", "2"));*/

        /*System.out.println("Enter a name: ");
        String name = scanner.nextLine();

        System.out.println("Enter a gender: ");
        String gender = scanner.nextLine();

        HashMap<Integer, Integer> yearlyNameRankMap = babyData.yearlyNameRank(name, gender);

        for (HashMap.Entry<Integer,Integer> entry : yearlyNameRankMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }*/

        /*System.out.println("Enter a name: ");
        String name = scanner.nextLine();

        System.out.println("Enter a gender: ");
        String gender = scanner.nextLine();

        System.out.println("Enter a year: ");
        String year = scanner.nextLine();

        List nameGenderMostRecentYear = new ArrayList<>();
        nameGenderMostRecentYear = babyData.NameGenderInMostRecentYear(name, gender, year);
        System.out.println(nameGenderMostRecentYear.get(2) + ": " + nameGenderMostRecentYear.get(0)
                            + ", " + nameGenderMostRecentYear.get(1));*/

        /*System.out.println("Enter a start year: ");
        String startYear = scanner.nextLine();

        System.out.println("Enter an end year: ");
        String endYear = scanner.nextLine();

        System.out.println("Enter a gender: ");
        String gender = scanner.nextLine();*/

        /*HashMap<String, Integer> numberOneNames = new HashMap<String, Integer>();
        numberOneNames = babyData.TopRankedNamesMap(startYear, endYear, gender);

        List mostCommonNumberOneName = new ArrayList();
        mostCommonNumberOneName = babyData.MostTopRankedName(numberOneNames);

        System.out.println(mostCommonNumberOneName.get(0));
        System.out.println(mostCommonNumberOneName.get(1));*/

        /*List topNamesForLetter;
        topNamesForLetter = babyData.MostPopularLetterNames(startYear, endYear, gender);
        System.out.println(topNamesForLetter);*/
    }
}
