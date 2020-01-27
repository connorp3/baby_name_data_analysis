package names;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidNameTest {
    InvalidNameTest() throws IOException, URISyntaxException {
    }

    BabyData b = new BabyData("C:\\Users\\conno\\Documents\\CS307\\data_cgp19\\data\\test_data", true);

    @Test
    void testFindRankFromNameForRangeOfYears() throws IOException {

        assertEquals(null, b.FindRankFromNameForRangeOfYears("Gerald", "M", "2017", "2018"));
    }
}
