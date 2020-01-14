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
        return nameGenderPair;
    }


}
