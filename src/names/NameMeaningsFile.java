package names;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NameMeaningsFile {
    private ArrayList<List<String>> nameMeanings = new ArrayList<>();
    private final String MEANINGS_FILE_PATH = "C:\\Users\\conno\\Documents\\CS307\\data_cgp19\\data\\baby_name_meanings.txt";

    public NameMeaningsFile() throws FileNotFoundException {
        File meaningsFile = new File(MEANINGS_FILE_PATH);
        Scanner input = new Scanner(meaningsFile);
        while (input.hasNextLine()) {
            String meaningsLine = input.nextLine();
            List<String> nameAndMeaningList = Arrays.asList(meaningsLine.split(" ", 3));
            nameMeanings.add(nameAndMeaningList);

        }
    }

    public ArrayList<List<String>> getNameMeanings() {
        return nameMeanings;
    }
}
