import java.io.File;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class TermDocumentMatrix {
    private HashMap<String, Map<String, Integer>> documentToTermCounts;
    private LinkedHashSet<String> allTerms;
    private List<String> indexedDocuments;

    public TermDocumentMatrix() {
        documentToTermCounts = new HashMap<>();
        allTerms = new LinkedHashSet<>();
        indexedDocuments = new ArrayList<>();
    }

    public void indexTerms(File file, List<String> processedTerms) {
        indexedDocuments.add(file.getName());
        Map<String, Integer> termCounts = new HashMap<>();
        allTerms.addAll(processedTerms);
        termCounts = processedTerms.stream().collect(groupingBy(Function.identity(), summingInt(e -> 1)));
        documentToTermCounts.put(file.getName(), termCounts);
    }

    public List<String> getDocuments() {
        return indexedDocuments;
    }

    public List<String> getTerms() {
        return new ArrayList<>(allTerms);
    }

    public int[][] getIntMatrix() {
        int[][] intMatrix = new int[indexedDocuments.size()][allTerms.size()];
        for (int i = 0; i < indexedDocuments.size(); i++) {
            int termIndex = 0;
            Map<String, Integer> termCountForDocument = documentToTermCounts.get(indexedDocuments.get(i));
            for (String term : allTerms) {
                intMatrix[i][termIndex] = termCountForDocument.getOrDefault(term, 0);
            }
        }
        return intMatrix;
    }
}
