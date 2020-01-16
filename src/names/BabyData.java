package names;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;


/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class BabyData {
    private ArrayList<BabyFile> fileList;
    private String mostRecentYear;
    private String filePath;
    static final int YEAR_IN_FILE_NAME_START = 3;
    static final int YEAR_IN_FILE_NAME_END = 7;

    public BabyData(String path) throws FileNotFoundException {
        fileList = new ArrayList<>();
        File dataSet = new File(path);
        filePath = path;

        String[] files = dataSet.list();
        int maxYear = 0;
        String strMaxYear = "";

        for (String file : files) {
            if(!file.equals("README.txt")) {
                String strYear = file.substring(YEAR_IN_FILE_NAME_START, YEAR_IN_FILE_NAME_END);

                int year = Integer.parseInt(strYear);
                if(year>= maxYear) strMaxYear = strYear;

                BabyFile babyFile = new BabyFile(strYear, filePath);
                fileList.add(babyFile);
            }
        }
        mostRecentYear = strMaxYear;

    }

    public ArrayList<BabyFile> RangeOfYearsData (String startYear, String endYear) {
        ArrayList<BabyFile> babyFilesRangeOfYears = new ArrayList<>();
        for (BabyFile yearData : fileList) {
            if(yearData.getYear() >= Integer.parseInt(startYear) &&
                    yearData.getYear() <= Integer.parseInt(endYear)) {
                babyFilesRangeOfYears.add(yearData);
            }
        }
        return babyFilesRangeOfYears;
    }


    public HashMap<Integer, Integer> yearlyNameRank (String name, String gender) {

        HashMap<Integer, Integer> yearToRank = new HashMap<>();
        int year;

        for (BabyFile yearData : fileList) {
            yearToRank.put(yearData.getYear(),yearData.FindRankFromNameGender(name, gender));
        }
        return yearToRank;
    }

    public List NameGenderInMostRecentYear (String name, String gender, String year) throws FileNotFoundException {
        BabyFile babyFile = new BabyFile(year, filePath);
        int rank = babyFile.FindRankFromNameGender(name, gender);
        List nameGenderPair = new ArrayList();
        BabyFile mostRecentBabyFile = new BabyFile(mostRecentYear, filePath);
        nameGenderPair = mostRecentBabyFile.FindNameGenderFromRank(rank, gender);
        nameGenderPair.add(mostRecentYear);
        return nameGenderPair;
    }

    public HashMap<String, Integer> TopRankedNamesMap (String startYear, String endYear, String gender) throws FileNotFoundException {

        HashMap<String, Integer> TopRankedNames = new HashMap<String, Integer>();
        ArrayList<BabyFile> rangeYearData = new ArrayList<>();
        rangeYearData = RangeOfYearsData(startYear, endYear);

        for (BabyFile yearData : rangeYearData) {

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
        ArrayList<BabyFile> rangeYearData = new ArrayList<>();
        rangeYearData = RangeOfYearsData(startYear, endYear);

        for (BabyFile yearData : rangeYearData) {

            HashMap<Character, Integer> yearLetterPopularity;
            yearLetterPopularity = yearData.FirstLetterCount(gender);

            for(Map.Entry firstLetter : yearLetterPopularity.entrySet()) {
                Character charFirstLetter = (Character) firstLetter.getKey();
                if (!rangeLetterPopularity.containsKey(firstLetter.getKey())) {
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

    public List MostPopularLetterNames (String startYear, String endYear, String gender) throws FileNotFoundException {
        HashMap<Character, Integer> firstLetterCountMap = new HashMap<>();
        firstLetterCountMap = RangeFirstLetterCount(startYear, endYear, gender);
        Character topLetter = MostPopularLetter(firstLetterCountMap);
        ArrayList<BabyFile> rangeYearData;
        rangeYearData = RangeOfYearsData(startYear, endYear);
        HashSet<String> fullNameSet = new HashSet<>();

        for (BabyFile yearData : rangeYearData) {
            HashSet<String> nameSet;
            nameSet = yearData.NamesOfCertainLetter(topLetter, gender);
            fullNameSet.addAll(nameSet);
        }
        List fullNameList = new ArrayList(fullNameSet);
        Collections.sort(fullNameList);
        return fullNameList;

    }



}
