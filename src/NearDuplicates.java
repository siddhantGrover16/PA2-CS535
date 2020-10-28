import java.io.File;
import java.util.ArrayList;
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
        if (similarity == 1.0) {
            return 1;
        } else if (similarity <= 0.0) {
            return this.minHash.numPermutations();
        }
        int bestMatch = 1;
        int r = 0;
        int[] factorsOfNumPermutations = getFactors(this.minHash.numPermutations());
        //k =r*b
        double bestDifference = Double.MAX_VALUE;
        for (int i = 0; i < factorsOfNumPermutations.length; i++) {
            int rows = factorsOfNumPermutations[i];
            for (int b = 1; b < minHash.numPermutations(); b++) {
                double leftSide = Math.pow((1.0 / b), (1.0 / rows));
                double difference = Math.abs(leftSide - similarity);
                if (difference < bestDifference) {
                    bestDifference = difference;
                    bestMatch = b;
                    r = rows;
                }
            }
        }
        return bestMatch;
    }

    private int[] getFactors(int numPermutations) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 1 ; i <= numPermutations ; ++i) {
            if ( numPermutations % i == 0)
                factors.add(i);
        }
        return factors.stream().mapToInt(i->i).toArray();
    }

    public List<String> nearDuplicateDetector(String documentName) {
        return lsh.nearDuplicatesOf(documentName);
    }
}