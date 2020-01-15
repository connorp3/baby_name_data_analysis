package names;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

    public List<String> FindNameGenderFromRank(int rank, String gender) throws FileNotFoundException {
        Scanner input = new Scanner(yearFile);
        int rankCounter = 1;
        List nameAndGender = new ArrayList();

        while(input.hasNextLine()) {
            BabyEntry babyEntry = new BabyEntry(input.nextLine());

            if (!babyEntry.gender.equals(gender)) {
                continue;
            }

            if (rank == rankCounter) {
                nameAndGender.add(babyEntry.name);
                nameAndGender.add(babyEntry.gender);
                break;
            }
            rankCounter++;
        }
        return nameAndGender;
    }

    public String MostPopularNameForGender(String gender) throws FileNotFoundException {
        Scanner input = new Scanner(yearFile);
        String mostPopularName = "";

        while(input.hasNextLine()) {
            BabyEntry firstBabyEntry = new BabyEntry(input.nextLine());

            if (!firstBabyEntry.gender.equals(gender)) {
                continue;
            }
            mostPopularName = firstBabyEntry.name;
            break;
        }
        return mostPopularName;
    }

    public HashMap<Character, Integer> FirstLetterCount(String gender) throws FileNotFoundException {
        Scanner input = new Scanner(yearFile);
        HashMap<Character, Integer> LetterPopularity = new HashMap<Character, Integer>();

        while(input.hasNextLine()) {
            BabyEntry firstBabyEntry = new BabyEntry(input.nextLine());

            if (!firstBabyEntry.gender.equals(gender)) {
                continue;
            }

            if (!LetterPopularity.containsKey(firstBabyEntry.firstLetter)) {
                LetterPopularity.put(firstBabyEntry.firstLetter, 0);
            }

            int temp = LetterPopularity.get(firstBabyEntry.firstLetter);
            temp += firstBabyEntry.nameCount;
            LetterPopularity.put(firstBabyEntry.firstLetter, temp);
        }
        return LetterPopularity;
    }

    public HashSet<String> NamesOfCertainLetter(Character firstLetter, String gender) throws FileNotFoundException {
        Scanner input = new Scanner(yearFile);
        HashSet<String> nameSet = new HashSet<>();
        while(input.hasNextLine()) {
            BabyEntry babyEntry = new BabyEntry(input.nextLine());

            if(babyEntry.firstLetter == firstLetter && babyEntry.gender.equals(gender)) {
                nameSet.add(babyEntry.name);
            }
        }
        return nameSet;
    }



}
