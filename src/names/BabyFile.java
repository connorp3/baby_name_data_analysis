package names;

import java.io.File;

public class BabyFile {
    private File yearFile;
    private String yearFileString;

    public BabyFile(String year) {
        yearFileString = "yob" + year + ".txt";
        yearFile = new File("C:\\Users\\conno\\Documents\\CS307\\data_cgp19\\data\\ssa_complete\\"+yearFileString);
    }


}
