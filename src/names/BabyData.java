package names;
import java.util.*;
import java.io.File;


/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class BabyData {
    private ArrayList<String> fileList;
    private String name;
    private String gender;

    public BabyData() {
        fileList = new ArrayList<>();
    }

    public void setFileList (String path) {
        File data = new File(path);

        String[] files = data.list();

        for (String file : files) {
            if(!file.equals("README.txt")) {
                fileList.add(file);
            }
        }
    }

    public void setName (String input) {
        name = input;
    }
    public void setGender (String input) {
        gender = input;
    }

    public int getFileYear (String fileName) {
        String strYear;
        strYear = fileName.substring(3,7);
        int year = Integer.parseInt(strYear);
        return year;
    }

    public HashMap<Integer, Integer> yearlyNameRank (String name, String gender) {

        HashMap<Integer, Integer> yearToRank = new HashMap<Integer, Integer>();
        int year;

        for (String yearData : fileList) {
            int rank = 1;
            year = getFileYear(yearData);
            Scanner input = new Scanner(BabyData.class.getClassLoader().getResourceAsStream("ssa_complete/"+yearData));

            while(input.hasNextLine()) {

                String[] dataEntry = input.nextLine().split(",");
                List<String> entryList = Arrays.asList(dataEntry);

                if (!entryList.contains(gender)) {
                    continue;
                }

                if (!entryList.contains(name)) {
                    rank++;
                    continue;
                }

                yearToRank.put(year, rank);
                break;
            }
        }
        return yearToRank;
    }
}
