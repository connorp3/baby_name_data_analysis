package names;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BabyDataTest {

    @org.junit.jupiter.api.Test
    void testYearlyNameRank() throws FileNotFoundException {
        BabyData b = new BabyData("C:\\Users\\conno\\Documents\\CS307\\data_cgp19\\data\\test_data");

        ArrayList<String> test1 = new ArrayList<String>(
                Arrays.asList("2017: 3", "2018: 6", "2019: 6"));
        assertEquals(test1, b.yearlyNameRank("Ben","M"));

        ArrayList<String> test2 = new ArrayList<String>(
                Arrays.asList("2017: 1", "2018: 3", "2019: 1"));
        assertEquals(test2, b.yearlyNameRank("Emily", "F"));
    }

    @org.junit.jupiter.api.Test
    void testNameGenderInMostRecentYear() throws FileNotFoundException {
        BabyData b = new BabyData("C:\\Users\\conno\\Documents\\CS307\\data_cgp19\\data\\test_data");

        ArrayList<String> test1 = new ArrayList<String>(
                Arrays.asList("Noah", "M", "2019"));
        assertEquals(test1, b.NameGenderInMostRecentYear("Adam", "M", "2017"));

        ArrayList<String> test2 = new ArrayList<String>(
                Arrays.asList("Isabella", "F", "2019"));

        assertEquals(test2, b.NameGenderInMostRecentYear("Stephanie", "F", "2018"));


    }

    @org.junit.jupiter.api.Test
    void testMostTopRankedName() throws FileNotFoundException {
        BabyData b = new BabyData("C:\\Users\\conno\\Documents\\CS307\\data_cgp19\\data\\test_data");

        ArrayList<String> test1 = new ArrayList<String>(
                Arrays.asList("Emily", "2"));
        assertEquals(test1, b.MostTopRankedName("2017", "2019", "F"));

        ArrayList<String> test2 = new ArrayList<String>(
                Arrays.asList("Emma", "1"));
        assertEquals(test2, b.MostTopRankedName("2017", "2018", "F"));
    }

    @org.junit.jupiter.api.Test
    void testMostPopularLetterNames() throws FileNotFoundException {
        BabyData b = new BabyData("C:\\Users\\conno\\Documents\\CS307\\data_cgp19\\data\\test_data");

        ArrayList<String> test1 = new ArrayList<String>(
                Arrays.asList("Ben", "Bob"));
        assertEquals(test1, b.MostPopularLetterNames("2017", "2019", "M"));

        ArrayList<String> test2 = new ArrayList<String>(
                Arrays.asList("Emily", "Emma"));
        assertEquals(test2, b.MostPopularLetterNames("2017", "2019", "F"));

    }
}