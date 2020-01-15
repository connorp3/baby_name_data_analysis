package names;

public class BabyEntry {
    String name;
    String gender;
    Integer nameCount;
    Character firstLetter;

    public BabyEntry(String line) {
        String[] data = line.split(",");
        name = data[0];
        gender = data[1];
        nameCount = Integer.parseInt(data[2]);
        firstLetter = name.charAt(0);
        firstLetter = Character.toLowerCase(firstLetter);
    }
}
