import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MinHash {
   private int numPerm;
   private MinHashMatrix minHashMatrix;
   private TermDocumentMatrix termDocumentMatrix;

    public MinHash(String folder, int numPermutations) {
        termDocumentMatrix = new TermDocumentMatrix();

        numPerm = numPermutations;
        File dir = new File(folder);
        File[] files = dir.listFiles();

        createTermDocumentMatrix(files);
        minHashMatrix = new MinHashMatrix(termDocumentMatrix, numPermutations);
    }

    private void createTermDocumentMatrix(File[] files) {
        if (files != null) {
            for (File file : files) {
                try (Stream<String> stream = Files.lines(file.toPath())) {
                    List<String> processedTerms = stream.map(line -> {
                        List<String> terms = Arrays.asList(line.split(",.':; "));
                        return terms.stream().map(String::toLowerCase)
                                .filter(term -> term.length() > 2 && !term.equalsIgnoreCase("the"))
                                .collect(Collectors.toList());
                    }).filter(terms -> terms.size() > 0).flatMap(List::stream).collect(Collectors.toList());
                    termDocumentMatrix.indexTerms(file, processedTerms);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public List<String> allDocs(){
        return termDocumentMatrix.getDocuments();
    }

    public int[][] minHashMatrix(){
        return minHashMatrix.minHashMatrix();
    }

    public int[][] termDocumentMatrix(){
        return termDocumentMatrix.termDocumentMatrix();
    }

    public int numTerms(){
        return termDocumentMatrix.getTerms().size();
    }

    public int numPermutations(){
        return numPerm ;
    }
}
