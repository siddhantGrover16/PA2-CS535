import java.io.File;
import java.util.*;

public class TermDocumentMatrix {
    private HashMap<String, HashSet<String>> documentToTermCounts;
    private LinkedHashSet<String> allTerms;
    private List<String> indexedDocuments;

    public TermDocumentMatrix() {
        documentToTermCounts = new HashMap<>();
        allTerms = new LinkedHashSet<>();
        indexedDocuments = new ArrayList<>();
    }

    public void indexTerms(File file, List<String> processedTerms) {
        indexedDocuments.add(file.getName());
        allTerms.addAll(processedTerms);
        HashSet<String> termCounts = new HashSet<>(processedTerms);
        documentToTermCounts.put(file.getName(), termCounts);
    }

    public List<String> getDocuments() {
        return indexedDocuments;
    }

    public List<String> getTerms() {
        return new ArrayList<>(allTerms);
    }

    public int[][] getIntMatrix() {
        int[][] intMatrix = new int[allTerms.size()][indexedDocuments.size()];
        for (int i = 0; i < indexedDocuments.size(); i++) {
            int termIndex = 0;
            HashSet<String> termCountForDocument = documentToTermCounts.get(indexedDocuments.get(i));
            for (String term : allTerms) {
                intMatrix[termIndex][i] = termCountForDocument.contains(term) ? 1 : 0;
                termIndex++;
            }
        }
        return intMatrix;
    }
}
