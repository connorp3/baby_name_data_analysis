package names;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BabyDataTest {
    BabyData b = new BabyData("C:\\Users\\conno\\Documents\\CS307\\data_cgp19\\data\\test_data", true);
    BabyData b1 = new BabyData("C:\\Users\\conno\\Documents\\CS307\\data_cgp19\\data\\test_data2", true);
    BabyData b2 = new BabyData("C:\\Users\\conno\\Documents\\CS307\\data_cgp19\\data\\test_data3", true);

    BabyDataTest() throws IOException, URISyntaxException {
    }

    @org.junit.jupiter.api.Test
    void testYearlyNameRank() throws IOException {
        ArrayList<String> test1 = new ArrayList<String>(
                Arrays.asList("2017: 3", "2018: 6", "2019: 6"));
        assertEquals(test1, b.yearlyNameRank("Ben","M"));

        ArrayList<String> test2 = new ArrayList<String>(
                Arrays.asList("2017: 1", "2018: 3", "2019: 1"));
        assertEquals(test2, b.yearlyNameRank("Emily", "F"));

        ArrayList<String> test3 = new ArrayList<String>(
                Arrays.asList("2017: 5", "2018: 3", "2019: 5"));
        assertEquals(test3, b.yearlyNameRank("Robert", "M"));

    }

    @org.junit.jupiter.api.Test
    void testNameGenderInMostRecentYear() throws IOException {

        ArrayList<String> test1 = new ArrayList<String>(
                Arrays.asList("Noah", "M", "2019"));
        assertEquals(test1, b.NameGenderInMostRecentYear("Adam", "M", "2017"));

        ArrayList<String> test2 = new ArrayList<String>(
                Arrays.asList("Isabella", "F", "2019"));

        assertEquals(test2, b.NameGenderInMostRecentYear("Stephanie", "F", "2018"));

        ArrayList<String> test3 = new ArrayList<String>(
                Arrays.asList("Emily", "F", "2019"));

        assertEquals(test3, b.NameGenderInMostRecentYear("Emily", "F", "2017"));

    }

    @org.junit.jupiter.api.Test
    void testMostFreqNameAtRanking() throws IOException {
        ArrayList<String> test1 = new ArrayList<String>(
                Arrays.asList("Emily", "2"));
        assertEquals(test1, b.MostFreqNameAtRanking("2017", "2019", "F", "1"));

        ArrayList<String> test2 = new ArrayList<String>(
                Arrays.asList("Emily", "1"));
        assertEquals(test2, b.MostFreqNameAtRanking("2017", "2018", "F", "1"));

        ArrayList<String> test3 = new ArrayList<String>(
                Arrays.asList("Steve", "3"));
        assertEquals(test3, b1.MostFreqNameAtRanking("2017", "2020", "M", "1"));

    }

    @Test
    void testMostFreqNameAtRankingBothGenders() throws IOException {

        ArrayList<String> test1 = new ArrayList<String>(
                Arrays.asList("Adam", "3"));
        assertEquals(test1, b1.MostFreqNameAtRankingBothGenders("2017", "2020", "2"));

        ArrayList<String> test2 = new ArrayList<String>(
                Arrays.asList("Rachel", "4"));
        assertEquals(test2, b1.MostFreqNameAtRankingBothGenders("2017", "2020", "4"));

        ArrayList<String> test3 = new ArrayList<String>(
                Arrays.asList("Steve", "3"));
        assertEquals(test3, b1.MostFreqNameAtRanking("2017", "2020", "M", "1"));

    }
    @Test
    void testMostFreqNameAtRankingBothGendersNameMeaning() throws IOException {

        ArrayList<String> test1 = new ArrayList<String>(
                Arrays.asList("Steve", "3", "M", "English Short form of N"));
        assertEquals(test1, b1.MostFreqNameAtRankingBothGendersNameMeaning("2017", "2019",  "1"));

        ArrayList<String> test2 = new ArrayList<String>(
                Arrays.asList("Emily", "1", "F", "English Medieval feminine form of Aemilius (see EMIL)."));
        assertEquals(test2, b1.MostFreqNameAtRankingBothGendersNameMeaning("2019", "2020", "1"));

        ArrayList<String> test3 = new ArrayList<String>(
                Arrays.asList("Rachel", "4", "F", "English, Jewish, French, German, Biblical Means \"ewe\" in Hebrew."));
        assertEquals(test3, b1.MostFreqNameAtRankingBothGendersNameMeaning("2017", "2020", "4"));

    }

    @org.junit.jupiter.api.Test
    void testMostPopularLetterNames() throws IOException {

        ArrayList<String> test1 = new ArrayList<String>(
                Arrays.asList("Ben", "Bob"));
        assertEquals(test1, b.MostPopularLetterNames("2017", "2019", "M"));

        ArrayList<String> test2 = new ArrayList<String>(
                Arrays.asList("Emily", "Emma"));
        assertEquals(test2, b.MostPopularLetterNames("2017", "2019", "F"));

        ArrayList<String> test3 = new ArrayList<String>(
                Arrays.asList("Emily"));
        assertEquals(test3, b1.MostPopularLetterNames("2017", "2020", "F"));

    }


    @Test
    void testFindRankFromNameForRangeOfYears() throws IOException {

        ArrayList<String> test1 = new ArrayList<String>(
                Arrays.asList("2017: 3", "2018: 6"));
        assertEquals(test1, b.FindRankFromNameForRangeOfYears("Ben","M", "2017", "2018"));

        ArrayList<String> test2 = new ArrayList<String>(
                Arrays.asList("2017: 3", "2018: 6", "2019: 6"));
        assertEquals(test2, b.FindRankFromNameForRangeOfYears("Ben","M", "2017", "2019"));

        ArrayList<String> test3 = new ArrayList<String>(
                Arrays.asList("2018: 3", "2019: 1"));
        assertEquals(test3, b.FindRankFromNameForRangeOfYears("Emily", "F", "2018", "2019"));


    }

    @Test
    void testDifferenceInRankStartAndEndYear() throws IOException {

        assertEquals(-3, b.DifferenceInRankStartAndEndYear("Ben", "M", "2018", "2017"));
        assertEquals(0, b.DifferenceInRankStartAndEndYear("Ben", "M", "2018", "2019"));
        assertEquals(0, b.DifferenceInRankStartAndEndYear("Emily", "F", "2017", "2019"));
    }

    @Test
    void testLargestChangeInRankInRangeOfYears() throws IOException {

        assertEquals("Emily, F", b1.LargestChangeInRankInRangeOfYears("2017", "2019"));
        assertEquals("Robert, M", b1.LargestChangeInRankInRangeOfYears("2017", "2020"));
        assertEquals("Adam, F", b1.LargestChangeInRankInRangeOfYears("2017", "2018"));
    }

    @Test
    void testAvgNameRankRangeOfYears() throws IOException {

        assertEquals(2.25, b1.AvgNameRankRangeOfYears("Emily", "F", "2017", "2020"));
        assertEquals(6.0, b1.AvgNameRankRangeOfYears("Jeffery", "M", "2017", "2020"));
        assertEquals(3.0, b1.AvgNameRankRangeOfYears("Adam", "M", "2019", "2020"));
    }

    @Test
    void testAvgNameRankRangeOfYearsBothGenders() throws IOException {

        double expectedMale = 10.0/3.0;
        assertEquals("Male: " + expectedMale + "\n" + "Female: 1.0", b2.AvgNameRankRangeOfYearsBothGenders("Emily", "2017", "2019"));
        double expectedFemale = 14.0/3.0;
        assertEquals("Male: 3.0" + "\n" + "Female: " + expectedFemale, b2.AvgNameRankRangeOfYearsBothGenders("Tina", "2017", "2019"));
        assertEquals("Male: 6.0\nFemale: 5.0", b2.AvgNameRankRangeOfYearsBothGenders("Georgina", "2019", "2019"));

    }
    @Test

    void testAvgNameRankRangeOfYearsBothGendersMostRecentYears() throws IOException {

        double expectedMale = 10.0/3.0;
        assertEquals("Male: " + expectedMale + "\n" + "Female: 1.0", b2.AvgNameRankRangeOfYearsBothGendersMostRecentYears("Emily", 3));
        double expectedFemale = 14.0/3.0;
        assertEquals("Male: 3.0" + "\n" + "Female: " + expectedFemale, b2.AvgNameRankRangeOfYearsBothGendersMostRecentYears("Tina", 3));
        assertEquals("Male: 6.0\nFemale: 5.0", b2.AvgNameRankRangeOfYearsBothGendersMostRecentYears("Georgina", 1));
    }

    @Test
    void testFindNameFromRankForRangeOfYears() throws IOException {

        List<String> test1 = new ArrayList<>(Arrays.asList("2018: Emily", "2019: Stephanie", "2020: Emily"));
        assertEquals(test1, b1.FindNameFromRankForRangeOfYears("1", "F", "2018", "2020"));

        List<String> test2 = new ArrayList<>(Arrays.asList("2019: Robert", "2020: Ben"));
        assertEquals(test2, b1.FindNameFromRankForRangeOfYears("5", "M", "2019", "2020"));

        List<String> test3 = new ArrayList<>(Arrays.asList("2017: Adam", "2018: Adam", "2019: Adam", "2020: Adam"));
        assertEquals(test3, b1.FindNameFromRankForRangeOfYears("3", "F", "2017", "2020"));

    }

    @Test
    void testGenderInputInvalid() {
        assertEquals(null, b.yearlyNameRank("Ben","X"));
    }

}