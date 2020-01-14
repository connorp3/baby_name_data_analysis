package names;
import java.util.*;
import java.io.File;


/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class BabyData {
    public ArrayList<String> fileList;

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
    public static void main (String[] args) {
        BabyData babyData = new BabyData();
        babyData.setFileList("C:\\Users\\conno\\Documents\\CS307\\data_cgp19\\data\\ssa_complete");

        System.out.println("Enter a name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.println("Enter a gender: ");
        String gender = scanner.nextLine();

        HashMap<Integer, Integer> yearlyNameRankMap = babyData.yearlyNameRank(name, gender);

        for (HashMap.Entry<Integer,Integer> entry : yearlyNameRankMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
