import java.io.File;
import java.util.List;

public class NearDuplicates {
    private MinHash minHash;
    private int numBands;
    private LSH lsh;

    public NearDuplicates(String folderName, int numPermutations, double s) {
        minHash = new MinHash(folderName, numPermutations);
        numBands = getNumBands(s);

        File folder = new File(folderName);
        File[] listOfFiles = folder.listFiles();

        String[] files = new String[listOfFiles.length];
        for (int i = 0; i < listOfFiles.length; i++) {
            files[i] = listOfFiles[i].getName();
        }
        lsh = new LSH(minHash.minHashMatrix(), files, numBands);
    }

    private int getNumBands(double s) {
        int k = minHash.numPermutations();
        //need to estimate value of B for LSH.
        //r*b = k
        // b = s^-r
        //return (int) Math.floor(Math.pow(s, -1*r));
        return 0;
    }

    public List<String> nearDuplicateDetector(String documentName) {
        return lsh.nearDuplicatesOf(documentName);
    }
}