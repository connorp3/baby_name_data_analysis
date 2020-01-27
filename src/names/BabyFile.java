package names;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
* Represents an entire file for a single year of baby name data. BabyFile object consists of
* the year of the data, and a list of BabyEntry objects kept in the same order as the original file
*/

public class BabyFile {
    private int year;
    private ArrayList<BabyEntry> babyEntries = new ArrayList<>();
    static final String FILE_NAME_PREFIX = "yob";

    /**Creates the BabyFile object. First determines if the file Path is a URL or not, and processes the filePath for a specified year and creating
     * BabyEntries for each line. Different processes are used to do so depending on whether the file Path is a URL or a folder in data.*/
    public BabyFile(String strYear, String path, boolean isFile) throws IOException {
        String yearFileString = FILE_NAME_PREFIX + strYear + ".txt";
        if(!isFile) {
            year = Integer.parseInt(strYear);
            String strBabyFileURL = path + yearFileString;
            isYearDataURLValid(strBabyFileURL);
            java.net.URL babyFileURL = new URL(path + yearFileString);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(babyFileURL.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                BabyEntry babyEntry = new BabyEntry(inputLine);
                babyEntries.add(babyEntry);
            }
        }
        if(isFile) {
            File yearFile = new File(path + "\\" + yearFileString);
            doesYearDataExist(yearFile);
            year = Integer.parseInt(strYear);

            Scanner input = new Scanner(yearFile);
            while (input.hasNextLine()) {
                BabyEntry babyEntry = new BabyEntry(input.nextLine());
                babyEntries.add(babyEntry);
            }
        }
    }

    public int getYear() {
        return year;
    }

    public ArrayList<BabyEntry> getBabyEntries() {
        return babyEntries;
    }
/**Determines if the specified URL path to a year file is valid and exits the program if it isn't*/
    public void isYearDataURLValid(String dataSetURL) throws IOException {
        try {
            new URL(dataSetURL).toURI();
        } catch (Exception URLNotValid) {
            System.out.println("ERROR: At least one year inputted is not in the specified dataset");
            System.exit(0);
        }
    }
/**Determines if the specified file path to a year file is valid and exits the program if it isn't*/
    private void doesYearDataExist(File dataSet) {
        if (!dataSet.exists()) {
            System.out.println("ERROR: At least one year inputted is not in the specified dataset");
            System.exit(0);
        }
    }
/**Determines if a name is present in a year file. If the rank of the given name for a gender exceeds the number of BabyEntries for that
 * gender, the name does not exist.*/
    private void doesNameExist(int rank, int numBabyEntries) {
        if (rank > numBabyEntries) {
            System.out.println("ERROR: The inputted name does not exist for the specified gender in at least one of the specified year files");
            System.exit(0);
        }
    }

    /**Creates a list of only male or female BabyEntry objects from a BabyFile*/
    public ArrayList<BabyEntry> getBabyEntriesForGender(String gender) {

        ArrayList<BabyEntry> babyEntriesForGender = new ArrayList<>();
        for (BabyEntry baby : getBabyEntries()) {
            if (baby.getGender().equals(gender)) {
                babyEntriesForGender.add(baby);
            }
        }
        return babyEntriesForGender;
    }


    /**Given an name and gender input, outputs the rank of that name in BabyFile for the specified gender*/
    public int FindRankFromNameGender(String name, String gender) {

        int rank = 1;
        ArrayList<BabyEntry> babyEntriesList = getBabyEntriesForGender(gender);

        for(BabyEntry baby : babyEntriesList) {
            /**Outputs an error message and exits program if name specified is not in the given data file*/
            if (!baby.getName().equals(name)) {
                rank++;
                doesNameExist(rank, babyEntriesList.size());
                continue;
            }
            break;
        }
        return rank;
    }


    /**Given a ranking and gender input, outputs the name from BabyFile that has that ranking within the specified
    * gender*/
    public List<String> FindNameGenderFromRank(int rank, String gender) {

        int rankCounter = 1;
        List<String> nameAndGender = new ArrayList<>();
        ArrayList<BabyEntry> babyEntriesList = getBabyEntriesForGender(gender);

        for(BabyEntry baby : babyEntriesList) {

            if (rank == rankCounter) {
                nameAndGender.add(baby.getName());
                nameAndGender.add(baby.getGender());
                break;
            }
            rankCounter++;
        }
        return nameAndGender;
    }

    /**Given a gender input, outputs the name of that gender with the highest nameCount,
    * breaking ties alphabetically*/
    public String FindNameFromRank(String gender, String rank) {

        ArrayList<BabyEntry> babyEntriesList = getBabyEntriesForGender(gender);
        BabyEntry mostPopularBabyEntry = babyEntriesList.get(Integer.parseInt(rank) - 1);

        return mostPopularBabyEntry.getName();
    }
    /**Given a gender input, outputs a Hashmap that maps each letter of the alphabet to
    * the number of babies in BabyFile that have a name that starts with that letter*/
    public HashMap<Character, Integer> FirstLetterCount(String gender) {

        HashMap<Character, Integer> LetterPopularity = new HashMap<>();
        ArrayList<BabyEntry> babyEntriesList = getBabyEntriesForGender(gender);

        for(BabyEntry baby : babyEntriesList) {

            if (!LetterPopularity.containsKey(baby.getFirstLetter())) {
                LetterPopularity.put(baby.getFirstLetter(), 0);
            }

            int temp = LetterPopularity.get(baby.getFirstLetter());
            temp += baby.getNameCount();
            LetterPopularity.put(baby.getFirstLetter(), temp);
        }
        return LetterPopularity;
    }
    /**Given a letter of the alphabet and gender input, outputs a HashSet of all the names in BabyFile
    * that start with that letter and have that gender*/
    public HashSet<String> NamesOfCertainLetter(Character firstLetter, String gender) {

        HashSet<String> nameSet = new HashSet<>();
        ArrayList<BabyEntry> babyEntriesList = getBabyEntriesForGender(gender);

        for(BabyEntry baby : babyEntriesList) {

            if(baby.getFirstLetter() == firstLetter) {
                nameSet.add(baby.getName());
            }
        }
        return nameSet;
    }
}
