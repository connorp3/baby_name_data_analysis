package names;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BabyFile {
    private int year;
    private ArrayList<BabyEntry> babyEntries = new ArrayList<>();
    static final String FILE_NAME_PREFIX = "yob";

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

    public ArrayList<BabyEntry> getBabyEntriesForGender(String gender) {
        ArrayList<BabyEntry> babyEntriesForGender = new ArrayList<>();
        for (BabyEntry baby : getBabyEntries()) {
            if (baby.getGender().equals(gender)) {
                babyEntriesForGender.add(baby);
            }
        }
        return babyEntriesForGender;
    }

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

    public String MostPopularNameForGender(String gender) {

        ArrayList<BabyEntry> babyEntriesList = getBabyEntriesForGender(gender);
        BabyEntry mostPopularBabyEntry = babyEntriesList.get(0);

        return mostPopularBabyEntry.getName();
    }

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
