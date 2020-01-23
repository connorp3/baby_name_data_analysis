package names;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

/**Represents an entire dataset of baby name data over a range of years. BabyData object consists of
 * a list of BabyFile objects for each year in dataSet, a string specifying the most recent year in that
 * dataset, and a string representing the path to the directory that holds the dataset*/
public class BabyData {
    private ArrayList<BabyFile> fileList;
    private String mostRecentYear;
    private String filePath;
    static final int YEAR_IN_FILE_NAME_START = 3;
    static final int YEAR_IN_FILE_NAME_END = 7;

    /**Creates the BabyData object by specifying the directory with all files in the dataset
     * and then creating a list of BabyFile objects and determining the most recent year in the
     * dataset*/
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
    /**Given a range of years, outputs a list of BabyFile objects for these years*/
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

    /**Given a name and gender input, outputs a list of ranks of that name for the specified
     * gender over every year in the dataset*/
    public List<String> yearlyNameRank (String name, String gender) {

        List<String> yearlyRankList = createYearlyRankList(name, gender, fileList);
        return yearlyRankList;
    }

    public List<String> nameRankRangeOfYears (String name, String gender, String startYear, String endYear) {

        List<String> yearlyRankList = createYearlyRankList(name, gender, RangeOfYearsData(startYear, endYear));
        return yearlyRankList;
    }

    private List<String> createYearlyRankList(String name, String gender, ArrayList<BabyFile> babyFilesRangeOfYears) {
        List<String> yearToRank = new ArrayList<>();

        for (BabyFile yearData : babyFilesRangeOfYears) {
            yearToRank.add(yearData.getYear() + ": " + yearData.FindRankFromNameGender(name, gender));
        }
        return yearToRank;
    }

    /**Given a name, gender, and year input, outputs the name with that same rank for the specified gender
     * in the most recent year in the form of a list*/
    public List<String> NameGenderInMostRecentYear (String name, String gender, String year) throws FileNotFoundException {
        BabyFile babyFile = new BabyFile(year, filePath);
        int rank = babyFile.FindRankFromNameGender(name, gender);
        List<String> nameGenderPair;
        BabyFile mostRecentBabyFile = new BabyFile(mostRecentYear, filePath);
        nameGenderPair = mostRecentBabyFile.FindNameGenderFromRank(rank, gender);
        nameGenderPair.add(mostRecentYear);
        return nameGenderPair;
    }
    /**Given a range of years input and a gender, outputs a HashMap of all names ranked number one
     * for that specified gender in that range of years mapped to the number of times the name
     * held the number one ranking*/
    public HashMap<String, Integer> TopRankedNamesMap (String startYear, String endYear, String gender) {

        HashMap<String, Integer> TopRankedNames = new HashMap<>();
        ArrayList<BabyFile> rangeYearData;
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
    /**Given a range of years and gender input, outputs the name that was ranked number one the most
     * years for that specified gender*/
    public List<String> MostTopRankedName (String startYear, String endYear, String gender) {
        HashMap<String, Integer> topRankedNames = TopRankedNamesMap(startYear, endYear, gender);
        int max = 0;
        String mostTopRankedName = "";

        for(Map.Entry numberOneName : topRankedNames.entrySet()) {
            int topRankFreq = ((int)numberOneName.getValue());
            String strNumberOneName = (String)numberOneName.getKey();

            if((topRankFreq == max && mostTopRankedName.compareTo(strNumberOneName) < 0)
                    || topRankFreq > max) {
                max = topRankFreq;
                mostTopRankedName = strNumberOneName;
            }
        }
        List<String> mostTopRankedNameAndFreq = new ArrayList<>();
        mostTopRankedNameAndFreq.add(mostTopRankedName);
        mostTopRankedNameAndFreq.add(Integer.toString(max));
        return mostTopRankedNameAndFreq;

    }
    /**Given a range of years and gender input, outputs a HashMap that maps each letter of the alphabet to
     * the number of babies in BabyFile that have a name that starts with that letter over the range of years */
    public HashMap<Character, Integer> RangeFirstLetterCount(String startYear, String endYear, String gender) {
        HashMap<Character, Integer> rangeLetterPopularity = new HashMap<>();
        ArrayList<BabyFile> rangeYearData;
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
                rangeLetterPopularity.put(charFirstLetter, temp);
            }
        }
        return rangeLetterPopularity;
    }
    /**Given a HashMap outputted by RangeFirstLetterCount method as input, outputs the letter
     * that had the most instances of babies with names starting with that letter over the range
     * of years*/
    public Character MostPopularLetter (HashMap<Character, Integer> firstLetterFreq) {
        int max = 0;
        Character mostPopularLetter = 'z';
        for(Map.Entry firstLetter : firstLetterFreq.entrySet()) {
            int freq = (int)firstLetter.getValue();
            Character charFirstLetter = (Character) firstLetter.getKey();
            if(freq > max || (freq == max && charFirstLetter < mostPopularLetter)) {
                max = freq;
                mostPopularLetter = charFirstLetter;
            }
        }
        return mostPopularLetter;
    }
    /**Given a range of years and gender input, outputs a list of all the names that
     * start with the "most popular" first letter of names, as defined by the MostPopularLetter
     * method above*/
    public List MostPopularLetterNames (String startYear, String endYear, String gender) {
        HashMap<Character, Integer> firstLetterCountMap;
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
        List<String> fullNameList = new ArrayList(fullNameSet);
        Collections.sort(fullNameList);
        return fullNameList;

    }



}
