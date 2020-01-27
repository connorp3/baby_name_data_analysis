package names;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**"Represents all the baby meanings for every baby name/gender pair. This class processes the baby_name_meanings.txt file and creates an object that is an arraylist of lists that contain each name in the file at index 0, the gender" +
 *"of the name at index 1, and the name meaning at index 2"*/
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
