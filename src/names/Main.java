package names;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) throws FileNotFoundException {
        BabyData babyData = new BabyData();
        babyData.setFileList("C:\\Users\\conno\\Documents\\CS307\\data_cgp19\\data\\ssa_complete");

        System.out.println("Enter a name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.println("Enter a gender: ");
        String gender = scanner.nextLine();

        HashMap<Integer, Integer> yearlyNameRankMap = babyData.yearlyNameRank(name, gender);

        for (HashMap.Entry<Integer,Integer> entry : yearlyNameRankMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
