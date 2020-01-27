package names;
import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

/**Represents an entire dataset of baby name data over a range of years. BabyData object consists of
 * a list of BabyFile objects for each year in dataSet, a string specifying the most recent year in that
 * dataset, and a string representing the path to the directory that holds the dataset*/
public class BabyData {
    private ArrayList<BabyFile> fileList;
    private String mostRecentYear;
    private String filePath;
    private boolean isDataSetADirectory;
    static final int YEAR_IN_FILE_NAME_START = 3;
    static final int YEAR_IN_FILE_NAME_END = 7;
    static final String MALE = "M";
    static final String FEMALE = "F";

    /**Creates the BabyData object by specifying the path with all files in the dataset
     * and then creating a list of BabyFile objects and determining the most recent year in the
     * dataset. The constructor uses different logic to process a URL path versus a directory path,
     * and the difference in path is passed as a parameter.*/
    public BabyData(String path, boolean isDirectory) throws IOException, URISyntaxException {
        isDataSetADirectory = isDirectory;
        fileList = new ArrayList<>();
        filePath = path;
        int maxYear = 0;
        String strMaxYear = "2020";

        if(!isDataSetADirectory) {
            URLDataSet dataSet = new URLDataSet(path);
            ArrayList<String> yearsOfData = new ArrayList<>(dataSet.getFileNames());
            for(String yearFile : yearsOfData) {
                String strYear = yearFile.substring(YEAR_IN_FILE_NAME_START, YEAR_IN_FILE_NAME_END);
                int year = Integer.parseInt(strYear);
                if (year >= maxYear) strMaxYear = strYear;

                BabyFile babyFile = new BabyFile(strYear, filePath, isDataSetADirectory);
                fileList.add(babyFile);
            }
        }
        else if(isDataSetADirectory) {
            File dataSet = new File(path);
            doesDataSetExist(dataSet);
            String[] files = dataSet.list();
            isDataSetEmpty(files);


            for (String file : files) {
                if (!file.equals("README.txt")) {
                    String strYear = file.substring(YEAR_IN_FILE_NAME_START, YEAR_IN_FILE_NAME_END);

                    int year = Integer.parseInt(strYear);
                    if (year >= maxYear) strMaxYear = strYear;

                    BabyFile babyFile = new BabyFile(strYear, filePath, isDataSetADirectory);
                    fileList.add(babyFile);
                }
            }
        }
        mostRecentYear = strMaxYear;
    }
    /**Determines if the dataset specified by a path is empty and prints error message and exits program if so*/
    private void isDataSetEmpty(String[] files) {
        if (files.length == 0) {
            System.out.println("ERROR: Data Source is empty");
            System.exit(0);
        }
    }
    /**Determines if the dataset specified by a path exists and prints error message and exits program if so*/
    private void doesDataSetExist(File dataSet) {
        if (!dataSet.exists()) {
            System.out.println("ERROR: Data Source does not exist");
            System.exit(0);
        }
    }
    /**Determines if a gender string is valid*/
    public boolean genderInputValid(String gender) {
        return gender.equals("M") || gender.equals("F");
    }

    /**Given a range of years, outputs a list of BabyFile objects for these years*/
    public ArrayList<BabyFile> RangeOfYearsData (String startYear, String endYear) {
        ArrayList<BabyFile> babyFilesRangeOfYears = new ArrayList<>();
        int intStartYear = Integer.parseInt(startYear);
        int intEndYear = Integer.parseInt(endYear);
        int rangeLength = intEndYear-intStartYear + 1;
        for (BabyFile yearData : fileList) {
            if(yearData.getYear() >= Integer.parseInt(startYear) &&
                    yearData.getYear() <= Integer.parseInt(endYear)) {
                babyFilesRangeOfYears.add(yearData);
            }
        }
        /**Outputs an error message if a range of years specified is not completely in the dataset*/
        if(babyFilesRangeOfYears.size() < rangeLength) {
            System.out.println("ERROR: At least one year in range is not represented in the dataset");
            System.exit(0);
        }

        return babyFilesRangeOfYears;
    }
/**Creates a list of the yearly rankings of an inputted name/gender pair when given an ArrayList of babyFiles for a range of years*/
    private List<String> createYearlyRankList(String name, String gender, ArrayList<BabyFile> babyFilesRangeOfYears) {
        List<String> yearlyRank = new ArrayList<>();

        for (BabyFile yearData : babyFilesRangeOfYears) {
            yearlyRank.add(yearData.getYear() + ": " + yearData.FindRankFromNameGender(name, gender));
        }
        return yearlyRank;
    }
    /**Outputs a list of names ranked at an inputted ranking for a range of years*/
    public List<String> FindNameFromRankForRangeOfYears(String rank, String gender, String startYear, String endYear) {
        if(!genderInputValid(gender)) {
            return null;
        }

        List<BabyFile> babyFilesRangeOfYears = new ArrayList<>(RangeOfYearsData(startYear, endYear));
        List<String> yearlyName = new ArrayList<>();

        for (BabyFile yearData : babyFilesRangeOfYears) {
            List<String> nameAndGender;
            nameAndGender = yearData.FindNameGenderFromRank(Integer.parseInt(rank), gender);
            String name = nameAndGender.get(0);
            yearlyName.add(yearData.getYear() + ": " + name);
        }
        return yearlyName;
    }
    /**Given a name and gender input, outputs a list of ranks of that name for the specified
     * gender over every year in the dataset*/
    public List<String> yearlyNameRank (String name, String gender) {
        if(!genderInputValid(gender)) {
            return null;
        }
        List<String> yearlyRankList = createYearlyRankList(name, gender, fileList);
        return yearlyRankList;
    }
    /**Given a name and gender input, outputs a list of ranks of that name for the specified
     * gender over range of years in the dataset*/
    public List<String> FindRankFromNameForRangeOfYears (String name, String gender, String startYear, String endYear) {
        if(!genderInputValid(gender)) {
            return null;
        }
        List<String> yearlyRankList = createYearlyRankList(name, gender, RangeOfYearsData(startYear, endYear));
        return yearlyRankList;
    }
    /**Given a name and gender, outputs the average ranking of that name/gender pair for a range of years*/
    public double AvgNameRankRangeOfYears (String name, String gender, String startYear, String endYear) {
        if(!genderInputValid(gender)) {
            return 0.0;
        }
        double numYears = Integer.parseInt(endYear) - Integer.parseInt(startYear) + 1;
        double sum = 0.0;
        for(BabyFile file : RangeOfYearsData(startYear, endYear)) {
            sum += file.FindRankFromNameGender(name, gender);
        }
        return sum/numYears;
    }
/**Given a name, outputs the average male and average female ranking for that name over a range of years*/
    public String AvgNameRankRangeOfYearsBothGenders (String name, String startYear, String endYear) {
        double maleAvgRank = AvgNameRankRangeOfYears(name, MALE, startYear, endYear);
        double femaleAvgRank = AvgNameRankRangeOfYears(name, FEMALE, startYear, endYear);
        return "Male: " + maleAvgRank + "\n" + "Female: " + femaleAvgRank;
    }
/**Given a name, outputs the average male and average female ranking for that name over the most recent specified number of years*/
    public String AvgNameRankRangeOfYearsBothGendersMostRecentYears(String name, int numYears) {
        String endYear = mostRecentYear;
        int intStartYear = Integer.parseInt(mostRecentYear) - numYears + 1;
        String startYear = Integer.toString(intStartYear);
        return(AvgNameRankRangeOfYearsBothGenders(name, startYear, endYear));
    }
/**Given a name and gender, finds the difference ranking for that name/gender pair for a specified startYear and endYear*/
    public int DifferenceInRankStartAndEndYear (String name, String gender, String startYear, String endYear) throws IOException {
        if(!genderInputValid(gender)) {
            return 0;
        }

        BabyFile firstYear = new BabyFile(startYear, filePath, isDataSetADirectory);
        BabyFile lastYear = new BabyFile(endYear, filePath, isDataSetADirectory);
        int firstYearRank = firstYear.FindRankFromNameGender(name, gender);
        int lastYearRank = lastYear.FindRankFromNameGender(name, gender);

        return lastYearRank-firstYearRank;
    }
    /**Given a startYear and endYear, determines which name moved up or down in rankings the most from the startYear to the endYear regardless of gender*/
    public String LargestChangeInRankInRangeOfYears (String startYear, String endYear) throws IOException {

        BabyFile firstYear = new BabyFile(startYear, filePath, isDataSetADirectory);
        int max = 0;
        BabyEntry firstBabyEntry = firstYear.getBabyEntries().get(0);
        String name = firstBabyEntry.getName();
        String gender = firstBabyEntry.getGender();
        for(BabyEntry baby : firstYear.getBabyEntries()) {
            int babyRankChange = DifferenceInRankStartAndEndYear(baby.getName(), baby.getGender(), startYear, endYear);
            if(Math.abs(babyRankChange) < Math.abs(max)) {
                continue;
            }
            boolean currentRankChangeIsSame = Math.abs(babyRankChange) == Math.abs(max);
            if(currentRankChangeIsSame && baby.getName().compareTo(name) > 0) {
                continue;
            }
            if(currentRankChangeIsSame && baby.getName().compareTo(name) == 0 && baby.getGender().compareTo(gender) > 0) {
                continue;
            }
            gender = baby.getGender();
            name = baby.getName();
            max = babyRankChange;

        }
        return name + ", " + gender;
    }

    /**Given a name, gender, and year input, outputs the name with that same rank for the specified gender
     * in the most recent year in the form of a list*/
    public List<String> NameGenderInMostRecentYear (String name, String gender, String year) throws IOException {
        if(!genderInputValid(gender)) {
            return null;
        }
        BabyFile babyFile = new BabyFile(year, filePath, isDataSetADirectory);
        int rank = babyFile.FindRankFromNameGender(name, gender);
        List<String> nameGenderPair;
        BabyFile mostRecentBabyFile = new BabyFile(mostRecentYear, filePath, isDataSetADirectory);
        nameGenderPair = mostRecentBabyFile.FindNameGenderFromRank(rank, gender);
        nameGenderPair.add(mostRecentYear);
        return nameGenderPair;
    }
    /**Given a range of years input and a gender, outputs a HashMap of all names ranked at a specified ranking
     * for that specified gender in that range of years mapped to the number of times the name
     * held the number one ranking*/
    public HashMap<String, Integer> NameFreqAtRankingMap (String startYear, String endYear, String gender, String rank) {

        HashMap<String, Integer> namesAtRanking = new HashMap<>();
        ArrayList<BabyFile> rangeYearData;
        rangeYearData = RangeOfYearsData(startYear, endYear);

        for (BabyFile yearData : rangeYearData) {

            if(!namesAtRanking.containsKey(yearData.FindNameFromRank(gender, rank))) {
                namesAtRanking.put(yearData.FindNameFromRank(gender, rank), 0);
            }
            int temp = namesAtRanking.get(yearData.FindNameFromRank(gender, rank));
            temp++;
            namesAtRanking.put(yearData.FindNameFromRank(gender, rank), temp);

        }
        return namesAtRanking;
    }
    /**Given a range of years and gender input, outputs the name that was ranked at the specified ranking the most
     * years for that specified gender*/
    public List<String> MostFreqNameAtRanking (String startYear, String endYear, String gender, String rank) {
        if(!genderInputValid(gender)) {
            return null;
        }
        HashMap<String, Integer> nameFreqAtRanking = NameFreqAtRankingMap(startYear, endYear, gender, rank);
        int max = 0;
        String mostFreqNameAtRank = "";

        for(Map.Entry nameAtRankingKey : nameFreqAtRanking.entrySet()) {
            int rankFreq = ((int)nameAtRankingKey.getValue());
            String nameAtRanking = (String)nameAtRankingKey.getKey();

            if((rankFreq == max && nameAtRanking.compareTo(mostFreqNameAtRank) < 0)
                    || rankFreq > max) {
                max = rankFreq;
                mostFreqNameAtRank = nameAtRanking;
            }
        }
        List<String> mostFreqNameAtRanking = new ArrayList<>();
        mostFreqNameAtRanking.add(mostFreqNameAtRank);
        mostFreqNameAtRanking.add(Integer.toString(max));
        return mostFreqNameAtRanking;

    }
/**Given a range of years, returns the name and gender that is most frequently ranked at a specified ranking regardless of gender*/
    public List<String> MostFreqNameAtRankingBothGenders (String startYear, String endYear, String rank) {
        List<String> maleMostFreqNameAtRank = MostFreqNameAtRanking(startYear, endYear, MALE, rank);
        List<String> femaleMostFreqNameAtRank = MostFreqNameAtRanking(startYear, endYear, FEMALE, rank);

        Integer maleFreq = Integer.parseInt(maleMostFreqNameAtRank.get(1));
        String maleName = maleMostFreqNameAtRank.get(0);

        Integer femaleFreq = Integer.parseInt(femaleMostFreqNameAtRank.get(1));
        String femaleName = femaleMostFreqNameAtRank.get(0);

        if(maleFreq > femaleFreq || (maleFreq == femaleFreq && maleName.compareTo(femaleName) < 0)) {
            return maleMostFreqNameAtRank;
        }

        return femaleMostFreqNameAtRank;

    }

    /**Given a range of years, returns the name and gender that is most frequently ranked at a specified ranking regardless of gender. Includes the names meaning. */
    public List<String> MostFreqNameAtRankingBothGendersNameMeaning (String startYear, String endYear, String rank) throws FileNotFoundException {
        NameMeaningsFile nameMeanings = new NameMeaningsFile();
        List<String> mostTopRankedNameAndFreq = MostFreqNameAtRankingBothGenders(startYear, endYear, rank);
        String mostTopRankedName = mostTopRankedNameAndFreq.get(0);
        for(List<String> name : nameMeanings.getNameMeanings()) {
            String nameInMeaningsFile = name.get(0);
            if(nameInMeaningsFile.equals(mostTopRankedName.toUpperCase())) {
                String gender = name.get(1).toUpperCase();
                String meaning = name.get(2);
                mostTopRankedNameAndFreq.add(gender);
                mostTopRankedNameAndFreq.add(meaning);
            }
        }
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
        if(!genderInputValid(gender)) {
            return null;
        }
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
