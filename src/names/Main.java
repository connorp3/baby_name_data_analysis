package names;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main (String[] args) throws FileNotFoundException {
        BabyData babyData = new BabyData("C:\\Users\\conno\\Documents\\CS307\\data_cgp19\\data\\ssa_complete");
        Scanner scanner = new Scanner(System.in);

        /*System.out.println("Enter a name: ");
        String name = scanner.nextLine();

        System.out.println("Enter a gender: ");
        String gender = scanner.nextLine();

        HashMap<Integer, Integer> yearlyNameRankMap = babyData.yearlyNameRank(name, gender);

        for (HashMap.Entry<Integer,Integer> entry : yearlyNameRankMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }*/

        System.out.println("Enter a name: ");
        String name = scanner.nextLine();

        System.out.println("Enter a gender: ");
        String gender = scanner.nextLine();

        System.out.println("Enter a year: ");
        String year = scanner.nextLine();

        List nameGenderMostRecentYear = new ArrayList<>();
        nameGenderMostRecentYear = babyData.NameGenderInMostRecentYear(name, gender, year);
        System.out.println(nameGenderMostRecentYear.get(2) + ": " + nameGenderMostRecentYear.get(0)
                            + ", " + nameGenderMostRecentYear.get(1));

        System.out.println("Enter a start year: ");
        String startYear = scanner.nextLine();

        System.out.println("Enter an end year: ");
        String endYear = scanner.nextLine();

        System.out.println("Enter a gender: ");
        String gender = scanner.nextLine();

        List
    }
}
