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

    private int getNumBands(double similarity) {
        double rightSide = Math.log(similarity) * minHash.numPermutations();
        int bestMatch = 1;
        double bestDifference = Double.MAX_VALUE;
        for (int b = 1; b < minHash.numPermutations(); b++) {
            double leftSide = b * Math.log(1.0/b);
            double difference = Math.abs(rightSide - leftSide);
            if (difference < bestDifference) {
                bestDifference  = difference;
                bestMatch = b;
            }
        }
        return bestMatch;
    }

    public List<String> nearDuplicateDetector(String documentName) {
        return lsh.nearDuplicatesOf(documentName);
    }
}