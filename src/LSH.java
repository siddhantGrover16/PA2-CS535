import java.util.*;

public class LSH {
    private int numBands;
    private List<HashMap<Integer, List<String>>> lshStorage = new ArrayList<>();
    private List<String> docNames;
    private int[][] transposedMinHash;
    private int rowsPerBand;

    public LSH(int[][] minHashMatrix, String[] docNames, int bands) {
        this.numBands = bands;
        this.docNames = Arrays.asList(docNames);
        this.rowsPerBand = minHashMatrix.length / bands;
        this.transposedMinHash = transpose(minHashMatrix);

        buildLSH();
    }

    private void buildLSH() {
        for (int i = 0; i < numBands; i++) {
            lshStorage.add(new HashMap<>());
        }
        for (int docIdx = 0; docIdx < docNames.size(); docIdx++) {
            for (int bandIdx = 0; bandIdx < numBands; bandIdx++) {
                int hashCode = Arrays.hashCode(Arrays.copyOfRange(transposedMinHash[docIdx], rowsPerBand * bandIdx, rowsPerBand * bandIdx + rowsPerBand));
                lshStorage.get(bandIdx).computeIfAbsent(hashCode, s -> new ArrayList<>()).add(docNames.get(docIdx));
            }
        }
    }

    public ArrayList<String> nearDuplicatesOf(String docName){
        int docIndex = docNames.indexOf(docName);

        HashSet<String> similarDocsSet = new HashSet<>();
        for (int bandIdx = 0; bandIdx < numBands; bandIdx++) {
            HashMap<Integer, List<String>> similarBands = lshStorage.get(bandIdx);
            int hashIndex = Arrays.hashCode(Arrays.copyOfRange(transposedMinHash[docIndex], rowsPerBand * bandIdx, rowsPerBand * bandIdx + rowsPerBand));
            similarDocsSet.addAll(similarBands.getOrDefault(hashIndex, new ArrayList<>()));
        }
        similarDocsSet.remove(docName);
        return new ArrayList<>(similarDocsSet);
    }

    public static int[][] transpose(int input[][]){
        int m = input.length;
        int n = input[0].length;
        int output[][] = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                output[j][i] = input[i][j];
            }
        }

        return output;
    }
}
