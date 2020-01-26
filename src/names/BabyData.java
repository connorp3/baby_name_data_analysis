package names;
import java.io.*;
import java.util.*;

/**Represents an entire dataset of baby name data over a range of years. BabyData object consists of
 * a list of BabyFile objects for each year in dataSet, a string specifying the most recent year in that
 * dataset, and a string representing the path to the directory that holds the dataset*/
public class BabyData {
    private ArrayList<BabyFile> fileList;
    private String mostRecentYear;
    private String filePath;
    static final int YEAR_IN_FILE_NAME_START = 3;
    static final int YEAR_IN_FILE_NAME_END = 7;
    static final String MALE = "M";
    static final String FEMALE = "F";
    static final String URL_DATASET = "https://www2.cs.duke.edu/courses/spring20/compsci307d/assign/01_data/data/ssa_complete/";

    /**Creates the BabyData object by specifying the directory with all files in the dataset
     * and then creating a list of BabyFile objects and determining the most recent year in the
     * dataset*/
    public BabyData(String path) throws IOException {
        fileList = new ArrayList<>();
        filePath = path;
        int maxYear = 0;
        String strMaxYear = "2020";

        if(path.equals(URL_DATASET)) {
            URLDataSet dataSet = new URLDataSet(URL_DATASET);
            ArrayList<String> yearsOfData = new ArrayList<>(dataSet.getFileNames());
            for(String yearFile : yearsOfData) {
                String strYear = yearFile.substring(YEAR_IN_FILE_NAME_START, YEAR_IN_FILE_NAME_END);
                int year = Integer.parseInt(strYear);
                if (year >= maxYear) strMaxYear = strYear;

                BabyFile babyFile = new BabyFile(strYear, URL_DATASET);
                fileList.add(babyFile);
            }
        }
        else {
            File dataSet = new File(path);
            String[] files = dataSet.list();


            for (String file : files) {
                if (!file.equals("README.txt")) {
                    String strYear = file.substring(YEAR_IN_FILE_NAME_START, YEAR_IN_FILE_NAME_END);

                    int year = Integer.parseInt(strYear);
                    if (year >= maxYear) strMaxYear = strYear;

                    BabyFile babyFile = new BabyFile(strYear, filePath);
                    fileList.add(babyFile);
                }
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

    private List<String> createYearlyRankList(String name, String gender, ArrayList<BabyFile> babyFilesRangeOfYears) {
        List<String> yearlyRank = new ArrayList<>();

        for (BabyFile yearData : babyFilesRangeOfYears) {
            yearlyRank.add(yearData.getYear() + ": " + yearData.FindRankFromNameGender(name, gender));
        }
        return yearlyRank;
    }

    public List<String> FindNameFromRankForRangeOfYears(String rank, String gender, String startYear, String endYear) {
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

        List<String> yearlyRankList = createYearlyRankList(name, gender, fileList);
        return yearlyRankList;
    }

    public List<String> FindRankFromNameForRangeOfYears (String name, String gender, String startYear, String endYear) {

        List<String> yearlyRankList = createYearlyRankList(name, gender, RangeOfYearsData(startYear, endYear));
        return yearlyRankList;
    }

    public double AvgNameRankRangeOfYears (String name, String gender, String startYear, String endYear) {
        double numYears = Integer.parseInt(endYear) - Integer.parseInt(startYear) + 1;
        double sum = 0.0;
        for(BabyFile file : RangeOfYearsData(startYear, endYear)) {
            sum += file.FindRankFromNameGender(name, gender);
        }
        return sum/numYears;
    }

    public String AvgNameRankRangeOfYearsBothGenders (String name, String startYear, String endYear) {
        double maleAvgRank = AvgNameRankRangeOfYears(name, MALE, startYear, endYear);
        double femaleAvgRank = AvgNameRankRangeOfYears(name, FEMALE, startYear, endYear);
        return "Male: " + maleAvgRank + "\n" + "Female: " + femaleAvgRank;
    }

    public int DifferenceInRankStartAndEndYear (String name, String gender, String startYear, String endYear) throws IOException {

        BabyFile firstYear = new BabyFile(startYear, filePath);
        BabyFile lastYear = new BabyFile(endYear, filePath);
        int firstYearRank = firstYear.FindRankFromNameGender(name, gender);
        int lastYearRank = lastYear.FindRankFromNameGender(name, gender);

        return lastYearRank-firstYearRank;
    }

    public String LargestChangeInRankInRangeOfYears (String startYear, String endYear) throws IOException {
        BabyFile firstYear = new BabyFile(startYear, filePath);
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
    /**Given a range of years and gender input, outputs the name that was ranked number one the most
     * years for that specified gender*/
    public List<String> MostFreqNameAtRanking (String startYear, String endYear, String gender, String rank) {
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
