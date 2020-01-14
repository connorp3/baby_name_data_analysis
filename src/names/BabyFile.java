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

    public int NameGenderRank(String name, String gender) throws FileNotFoundException {
        //Scanner input = new Scanner(BabyData.class.getClassLoader().getResourceAsStream("ssa_complete/"+yearData));
        Scanner input = new Scanner(yearFile);
        int rank = 1;

        while(input.hasNextLine()) {

            String[] dataEntry = input.nextLine().split(",");
            List<String> entryList = Arrays.asList(dataEntry);

            if (!entryList.contains(gender)) {
                continue;
            }

            if (!entryList.contains(name)) {
                rank++;
                continue;
            }
            break;
        }
        return rank;
    }


}
