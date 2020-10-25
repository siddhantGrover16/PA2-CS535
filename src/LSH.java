import java.util.*;

public class LSH {
    private int numBands;
    private List<HashMap<Integer, List<String>>> lshStorage = new ArrayList<>();
    private List<List<String>> lshArrays = new ArrayList<>();
    private List<String> docNames;
    private int[][] minHashMatrix;
    private int rowsPerBand;
    private long prime;
    private Pair pair;

    public LSH(int[][] minHashMatrix, String[] docNames, int bands) {
        this.numBands = bands;
        this.minHashMatrix = minHashMatrix;
        this.docNames = Arrays.asList(docNames);
        this.rowsPerBand = minHashMatrix.length / bands;
        this.prime = PrimeNumberUtils.getPrimeLargerThan(minHashMatrix[0].length);

        Random rand = new Random();
        this.pair = new Pair(getRandomLong(this.prime, rand), getRandomLong(this.prime, rand));
        buildLSH();
    }

    private void buildLSH() {
        for (int i = 0; i < numBands; i++) {
            lshStorage.add(new HashMap<>());
            lshArrays.add(new ArrayList<>());
        }
        int[][] transposed = transpose(minHashMatrix);
        for (int docIdx = 0; docIdx < docNames.size(); docIdx++) {
            for (int bandIdx = 0; bandIdx < numBands; bandIdx++) {
                int hashCode = Arrays.hashCode(Arrays.copyOfRange(transposed[docIdx], rowsPerBand * bandIdx, rowsPerBand * bandIdx + rowsPerBand));
                int hashIndex = getHash(hashCode);
                lshStorage.get(hashIndex).computeIfAbsent(hashIndex, s -> new ArrayList<>()).add(docNames.get(docIdx));
                lshArrays.get(hashIndex).add(docNames.get(docIdx));
            }
        }
    }

    private long getRandomLong(long upperBound, Random rand) {
        return Math.abs(rand.nextLong()) % upperBound;
    }

    private int getHash(int hashCode) {
        return Math.round(Math.abs((pair.getA() * hashCode + pair.getB()) % numBands));
    }

    public ArrayList<String> nearDuplicatesOf(String docName){
        int docIndex = docNames.indexOf(docName);
        int[][] transposed = transpose(minHashMatrix);
        HashSet<String> similarDocsSet = new HashSet<>();
        for (int bandIdx = 0; bandIdx < numBands; bandIdx++) {
            HashMap<Integer, List<String>> similarBands = lshStorage.get(docIndex);
            int hashIndex = getHash(Arrays.hashCode(Arrays.copyOfRange(transposed[docIndex], rowsPerBand * bandIdx, rowsPerBand * bandIdx + rowsPerBand)));
            List<String> similarDocs = similarBands.getOrDefault(hashIndex, new ArrayList<>());
            similarDocsSet.addAll(similarDocs);
        }
        similarDocsSet.remove(docName);
        return new ArrayList<>(similarDocsSet);
    }

    public static int[][] transpose(int arr[][]){
        int m = arr.length;
        int n = arr[0].length;
        int ret[][] = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ret[j][i] = arr[i][j];
            }
        }

        return ret;
    }
}
