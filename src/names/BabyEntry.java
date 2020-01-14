package names;

public class BabyEntry {
    String name;
    String gender;
    String nameCount;

    public BabyEntry(String line) {
        String[] data = line.split(",");
        name = data[0];
        gender = data[1];
        nameCount = data[2];
    }
}
