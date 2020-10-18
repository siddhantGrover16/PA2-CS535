import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MinHash {
   int numPerm;
   MinHashMatrix minHashMatrix;
   TermDocumentMatrix termDocumentMatrix;

    public MinHash(String folder, int numPermutations) {
        termDocumentMatrix = new TermDocumentMatrix();
        numPerm = numPermutations;
        File dir = new File("src/" + folder);
        File[] files = dir.listFiles();

        createTermDocumentMatrix(files);
        minHashMatrix = new MinHashMatrix(termDocumentMatrix, numPermutations);
    }

    private void createTermDocumentMatrix(File[] files) {
        if (files != null) {
            for (File file : files) {
                try (Stream<String> stream = Files.lines(file.toPath())) {
                    stream.map(line -> {
                        ArrayList<String> terms = (ArrayList<String>) Arrays.asList(line.split("[,|.$':; ]"));
                        return terms.stream().map(String::toLowerCase).filter(term -> term.length() > 2 && !term.equalsIgnoreCase("the")).collect(Collectors.toList());
                    }).forEach(processedTerms -> {
                        termDocumentMatrix.indexTerms(file, processedTerms);
                    });
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
        return minHashMatrix.getIntMatrix();
    }

    public int[][] termDocumentMatrix(){
        return termDocumentMatrix.getIntMatrix();
    }

    public int numTerms(){
        return termDocumentMatrix.getTerms().size();
    }

    public int numPermutations(){
        return numPerm ;
    }

}
