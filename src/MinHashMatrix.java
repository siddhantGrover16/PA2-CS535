import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MinHashMatrix {
    int[][] minHashMatrix;
    List<Pair> permutationFunctions;
    int prime;

    public MinHashMatrix(TermDocumentMatrix termDocumentMatrix, int numPermutations) {
        minHashMatrix = new int[numPermutations][termDocumentMatrix.getDocuments().size()];
        prime = (int) PrimeNumberUtils.getRandPrimeLargerThan(termDocumentMatrix.getTerms().size(), 10); //get random prime bigger than the number of terms?
        configurePermutationFunctions(numPermutations);
        createMinHashMatrix(termDocumentMatrix, numPermutations);
    }

    private void createMinHashMatrix(TermDocumentMatrix termDocumentMatrix, int numPermutations) {
        int[][] termMatrix = termDocumentMatrix.termDocumentMatrix();
        List<String> terms = termDocumentMatrix.getTerms();

        for (int i = 0; i < minHashMatrix.length; i++) {
            for (int j = 0; j < minHashMatrix[i].length; j++) {
                minHashMatrix[i][j] = getMinHash(termMatrix, permutationFunctions.get(i), terms, j); //This gets the min value for the i'th permutation for document j
            }
        }
    }

    private int getMinHash(int[][] termMatrix, Pair permutationFunction, List<String> terms, int documentIndex) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < termMatrix.length; i++) {
            if (termMatrix[i][documentIndex] > 0) { //for all term existences in this particular document, 1 if term exists
                int value = terms.get(i).hashCode();
                int hash = computePermutation(permutationFunction, value);
                if (hash < min) {
                    min = hash;
                }
            }
        }
        return min;
    }

    private void configurePermutationFunctions(int numPermutations) {
        Random rand = new Random();
        permutationFunctions = new ArrayList<>();
        for (int i = 0; i < numPermutations; i++) {
            permutationFunctions.add(new Pair(getRandomLong(prime, rand), getRandomLong(prime, rand)));
        }
    }

    public int[][] getIntMatrix() {
        return minHashMatrix;
    }

    /**
     * Gets a random long.
     * @param upperBound
     *  The upperbound.
     * @param rand
     *  An instance of Random.
     * @return
     *  A random long bounded by upperbound.
     */
    private long getRandomLong(long upperBound, Random rand) {
        return Math.abs(rand.nextLong()) % upperBound;
    }

    private int computePermutation(Pair permutationPair, int documentValue) {
        return (int) ((permutationPair.getA() * documentValue + permutationPair.getB()) % prime);
    }
}
