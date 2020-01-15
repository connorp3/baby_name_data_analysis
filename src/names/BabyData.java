package names;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;


/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class BabyData {
    private ArrayList<BabyFile> fileList;
    private String name;
    private String gender;
    private String mostRecentYear;

    public BabyData(String path) {
        fileList = new ArrayList<BabyFile>();
        File data = new File(path);

        String[] files = data.list();
        int maxYear = 0;
        String strMaxYear = "";

        for (String file : files) {
            if(!file.equals("README.txt")) {
                String strYear = file.substring(3,7);

                int year = Integer.parseInt(strYear);
                if(year>= maxYear) strMaxYear = strYear;

                BabyFile babyFile = new BabyFile(strYear);
                fileList.add(babyFile);
            }
        }
        mostRecentYear = strMaxYear;

    }

    public void setFileList (String path) {
        File data = new File(path);

        String[] files = data.list();

        for (String file : files) {
            if(!file.equals("README.txt")) {
                String strYear = file.substring(3,7);
                BabyFile babyFile = new BabyFile(strYear);
                fileList.add(babyFile);
            }
        }
    }

    public void setName (String input) {
        name = input;
    }
    public void setGender (String input) {
        gender = input;
    }

    /*public int getFileYear (String fileName) {
        String strYear;
        strYear = fileName.substring(3,7);
        int year = Integer.parseInt(strYear);
        return year;
    }*/

    public HashMap<Integer, Integer> yearlyNameRank (String name, String gender) throws FileNotFoundException {

        HashMap<Integer, Integer> yearToRank = new HashMap<Integer, Integer>();
        int year;

        for (BabyFile yearData : fileList) {
            yearToRank.put(yearData.getYear(),yearData.FindRankFromNameGender(name, gender));
        }
        return yearToRank;
    }

    public List NameGenderInMostRecentYear (String name, String gender, String year) throws FileNotFoundException {
        BabyFile babyFile = new BabyFile(year);
        int rank = babyFile.FindRankFromNameGender(name, gender);
        List nameGenderPair = new ArrayList();
        BabyFile mostRecentBabyFile = new BabyFile(mostRecentYear);
        nameGenderPair = mostRecentBabyFile.FindNameGenderFromRank(rank, gender);
        nameGenderPair.add(mostRecentYear);
        return nameGenderPair;
    }

    public HashMap<String, Integer> TopRankedNamesMap (String startYear, String endYear, String gender) throws FileNotFoundException {

        HashMap<String, Integer> TopRankedNames = new HashMap<String, Integer>();

        for (BabyFile yearData : fileList) {
            if(yearData.getYear() < Integer.parseInt(startYear) ||
                    yearData.getYear() > Integer.parseInt(endYear)) {
                continue;
            }
            if(!TopRankedNames.containsKey(yearData.MostPopularNameForGender(gender))) {
                TopRankedNames.put(yearData.MostPopularNameForGender(gender), 0);
            }
            int temp = TopRankedNames.get(yearData.MostPopularNameForGender(gender));
            temp++;
            TopRankedNames.put(yearData.MostPopularNameForGender(gender), temp);

        }
        return TopRankedNames;
    }

    public List MostTopRankedName (HashMap<String, Integer> topRankedNames) {
        int max = 0;
        String mostTopRankedName = "";

        for(Map.Entry numberOneName : topRankedNames.entrySet()) {
            int topRankFreq = ((int)numberOneName.getValue());
            String strNumberOneName = (String)numberOneName.getKey();

            if((topRankFreq == max && strNumberOneName.compareTo(mostTopRankedName) < 0)
                    || topRankFreq > max) {
                max = topRankFreq;
                mostTopRankedName = strNumberOneName;
            }
        }
        List mostTopRankedNameAndFreq = new ArrayList();
        mostTopRankedNameAndFreq.add(mostTopRankedName);
        mostTopRankedNameAndFreq.add(max);
        return mostTopRankedNameAndFreq;

    }

    public HashMap<Character, Integer> RangeFirstLetterCount(String startYear, String endYear, String gender) throws FileNotFoundException {
        HashMap<Character, Integer> rangeLetterPopularity = new HashMap<Character, Integer>();

        for (BabyFile yearData : fileList) {
            if (yearData.getYear() < Integer.parseInt(startYear) ||
                    yearData.getYear() > Integer.parseInt(endYear)) {
                continue;
            }
            HashMap<Character, Integer> yearLetterPopularity = new HashMap<Character, Integer>();
            yearLetterPopularity = yearData.FirstLetterCount(gender);

            for(Map.Entry firstLetter : yearLetterPopularity.entrySet()) {
                Character charFirstLetter = (Character) firstLetter.getKey();
                if (rangeLetterPopularity.containsKey(firstLetter.getKey())) {
                    rangeLetterPopularity.put(charFirstLetter, 0);
                }
                int firstLetterFreq = ((int) firstLetter.getValue());
                int temp = rangeLetterPopularity.get(charFirstLetter);
                temp += firstLetterFreq;
                rangeLetterPopularity.put(charFirstLetter, firstLetterFreq);
            }
        }
        return rangeLetterPopularity;
    }
    
    public Character MostPopularLetter (HashMap<Character, Integer> firstLetterFreq) {
        int max = 0;
        Character mostPopularLetter = null;
        for(Map.Entry firstLetter : firstLetterFreq.entrySet()) {
            int freq = (int)firstLetter.getValue();
            Character charFirstLetter = (Character) firstLetter.getKey();
            if(freq > max) {
                max = freq;
                mostPopularLetter = charFirstLetter;
            }
        }
        return mostPopularLetter;
        
    }



}
