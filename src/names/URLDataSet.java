package names;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class URLDataSet {
    private ArrayList<String> fileNames;

    public URLDataSet(String URL) throws IOException {
        ArrayList<File> files = new ArrayList<>();
        java.net.URL oracle = new URL(URL);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        StringBuilder sb = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine);

        }

        in.close();

        String urlInfo = sb.toString();
        int indexOfHREF = urlInfo.indexOf("href=\"yob");
        fileNames = new ArrayList<>();

        while ((indexOfHREF) != -1) {
            int firstIndexOfFileName = indexOfHREF + 6;
            int lastIndexOfFileName = indexOfHREF + 17;
            String fileName = urlInfo.substring(firstIndexOfFileName, lastIndexOfFileName);
            fileNames.add(fileName);
            urlInfo = urlInfo.substring(lastIndexOfFileName);
            indexOfHREF = urlInfo.indexOf("href=\"yob");

        }
    }

    public ArrayList<String> getFileNames() {
        return fileNames;
    }
}
