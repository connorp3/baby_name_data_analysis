package names;

public class BabyEntry {
    private String name;
    private String gender;
    private Integer nameCount;
    private Character firstLetter;

    public BabyEntry(String line) {
        String[] data = line.split(",");
        name = data[0];
        gender = data[1];
        nameCount = Integer.parseInt(data[2]);
        firstLetter = name.charAt(0);
        firstLetter = Character.toLowerCase(firstLetter);
    }

    public Character getFirstLetter() {
        return firstLetter;
    }

    public Integer getNameCount() {
        return nameCount;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }
}
