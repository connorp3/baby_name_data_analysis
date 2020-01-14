package names;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BabyFile {
    private File yearFile;
    private String yearFileString;
    private int year;

    public BabyFile(String strYear) {
        yearFileString = "yob" + strYear + ".txt";
        yearFile = new File("C:\\Users\\conno\\Documents\\CS307\\data_cgp19\\data\\ssa_complete\\"+yearFileString);
        year = Integer.parseInt(strYear);
    }

    public int getYear() {
        return year;
    }

    public int FindRankFromNameGender(String name, String gender) throws FileNotFoundException {
        //Scanner input = new Scanner(BabyData.class.getClassLoader().getResourceAsStream("ssa_complete/"+yearData));
        Scanner input = new Scanner(yearFile);
        int rank = 1;

        while(input.hasNextLine()) {
            BabyEntry babyEntry = new BabyEntry(input.nextLine());

            /*String[] dataEntry = input.nextLine().split(",");
            List<String> entryList = Arrays.asList(dataEntry);*/

            if (!babyEntry.gender.equals(gender)) {
                continue;
            }

            if (!babyEntry.name.equals(name)) {
                rank++;
                continue;
            }
            break;
        }
        return rank;
    }

    public List<String> FindNameGenderFromRank(int rank, int gender) throws FileNotFoundException {
        Scanner input = new Scanner(yearFile);

        while(input.hasNextLine()) {

            String[] dataEntry = input.nextLine().split(",");
            List<String> entryList = Arrays.asList(dataEntry);
    }


}
