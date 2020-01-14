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

    public BabyData() {
        fileList = new ArrayList<BabyFile>();
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
    
}
