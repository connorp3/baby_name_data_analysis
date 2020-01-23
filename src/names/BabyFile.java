package names;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
* Represents an entire file for a single year of baby name data. BabyFile object consists of
* the year of the data, and a list of BabyEntry objects kept in the same order as the original file
*/

public class BabyFile {
    private int year;
    private ArrayList<BabyEntry> babyEntries = new ArrayList<>();
    static final String FILE_NAME_PREFIX = "yob";
    /**Creates the BabyFile object by scanning the file for a specified year and creating
     * BabyEntries for each line*/
    public BabyFile(String strYear, String path) throws FileNotFoundException {
        String yearFileString = FILE_NAME_PREFIX + strYear + ".txt";
        File yearFile = new File(path + "\\" + yearFileString);
        year = Integer.parseInt(strYear);

        Scanner input = new Scanner(yearFile);
        while (input.hasNextLine()) {
            BabyEntry babyEntry = new BabyEntry(input.nextLine());
            babyEntries.add(babyEntry);
        }
    }

    public int getYear() {
        return year;
    }

    public ArrayList<BabyEntry> getBabyEntries() {
        return babyEntries;
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

    public List<BabyEntry> sortBabyFileIgnoringGender() {
        List<BabyEntry> copy = new ArrayList<>(babyEntries.size());
        copy.addAll(babyEntries);
        Collections.sort(copy);
        return copy;
    }

    /**Given an name and gender input, outputs the rank of that name in BabyFile for the specified gender*/
    public int FindRankFromNameGender(String name, String gender) {

        int rank = 1;
        ArrayList<BabyEntry> babyEntriesList = getBabyEntriesForGender(gender);

        for(BabyEntry baby : babyEntriesList) {

            if (!baby.getName().equals(name)) {
                rank++;
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
    public String MostPopularNameForGender(String gender) {

        ArrayList<BabyEntry> babyEntriesList = getBabyEntriesForGender(gender);
        BabyEntry mostPopularBabyEntry = babyEntriesList.get(0);

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
