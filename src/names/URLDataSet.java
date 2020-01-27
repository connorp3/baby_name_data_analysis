package names;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class URLDataSet {
    private ArrayList<String> fileNames;

    public URLDataSet(String URL) throws IOException {
        isURLValid(URL);
        java.net.URL dataSetURL = new URL(URL);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(dataSetURL.openStream()));

        String inputLine;
        StringBuilder sb = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine);

        }

        in.close();

        String urlInfo = sb.toString();
        fileNames = new ArrayList<>();
        findFileNamesForURLDataSet(urlInfo);
    }

    private void findFileNamesForURLDataSet(String urlInfo) {
        int indexOfHREF = urlInfo.indexOf("href=\"yob");

        /**Exits the program if the URL does not contain data files*/
        if (indexOfHREF == -1) {
            System.out.println("ERROR: Data Source is empty");
            System.exit(0);
        }
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

    /**Returns Error Message and exits program if inputted URL is not valid*/
    public void isURLValid(String dataSetURL) throws IOException {
        try {
            new URL(dataSetURL).toURI();
        } catch (Exception URLNotValid) {
            System.out.println("ERROR: This URL is not valid");
            System.exit(0);
        }
    }

}
