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
        allTerms.addAll(processedTerms);
        Map<String, Integer> termDocumentCounts = processedTerms.stream().collect(groupingBy(Function.identity(), summingInt(e -> 1)));//store the terms that exist in this document.
        documentToTermCounts.put(file.getName(), termDocumentCounts);
    }

    public List<String> getDocuments() {
        return indexedDocuments;
    }

    public List<String> getTerms() {
        return new ArrayList<>(allTerms);
    }

    public int[][] termDocumentMatrix() {
        int[][] intMatrix = new int[allTerms.size()][indexedDocuments.size()];
        for (int i = 0; i < indexedDocuments.size(); i++) {
            int termIndex = 0;
            Map<String, Integer> termCountForDocument = documentToTermCounts.get(indexedDocuments.get(i));
            for (String term : allTerms) {
                //if this document contains the term, set term index to 1, otherwise 0.
                intMatrix[termIndex][i] = termCountForDocument.getOrDefault(term, 0);
                termIndex++;
            }
        }
        return intMatrix;
    }
}
