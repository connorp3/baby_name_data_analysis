package names;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**Represents all files in a dataset with a URL path. This class processes a URL dataset path and creates an object that is an ArrayList of all the filenames in that dataset*/

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

    /**given the urlInputStream, finds all the data file names in the URL dataset*/
    private void findFileNamesForURLDataSet(String urlInputStream) {
        int indexOfHREF = urlInputStream.indexOf("href=\"yob");

        /**Exits the program if the URL does not contain data files*/
        if (indexOfHREF == -1) {
            System.out.println("ERROR: Data Source is empty");
            System.exit(0);
        }
        while ((indexOfHREF) != -1) {
            int firstIndexOfFileName = indexOfHREF + 6;
            int lastIndexOfFileName = indexOfHREF + 17;
            String fileName = urlInputStream.substring(firstIndexOfFileName, lastIndexOfFileName);
            fileNames.add(fileName);
            urlInputStream = urlInputStream.substring(lastIndexOfFileName);
            indexOfHREF = urlInputStream.indexOf("href=\"yob");

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
