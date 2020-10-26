import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MinHashMatrix {
    private int[][] minHashMatrix;
    private int[][] termMatrix;
    private List<Integer> maxOccurrences;
    private List<List<Integer>> uniqueIds;
    private List<Pair> permutationFunctions;
    private long prime;

    public MinHashMatrix(TermDocumentMatrix termDocumentMatrix, int numPermutations) {
        minHashMatrix = new int[numPermutations][termDocumentMatrix.getDocuments().size()];
        termMatrix = termDocumentMatrix.termDocumentMatrix();
        maxOccurrences = getMaxOccurrences(termMatrix);
        uniqueIds = getUniqueIds(termDocumentMatrix);
        int primeSize = Math.max(maxOccurrences.stream().reduce(0, Integer::sum), numPermutations);
        prime = (int) PrimeNumberUtils.getPrimeLargerThan(primeSize); //get random prime bigger than the number of terms?

        configurePermutationFunctions(numPermutations);
        createMinHashMatrix();
    }

    private void createMinHashMatrix() {
        for (int i = 0; i < minHashMatrix.length; i++) {
            for (int j = 0; j < minHashMatrix[i].length; j++) {
                minHashMatrix[i][j] = getMinHash(uniqueIds.get(j), permutationFunctions.get(i)); //This gets the min value for the i'th permutation for document j
            }
        }
    }

    private List<List<Integer>> getUniqueIds(TermDocumentMatrix termDocumentMatrix) {
        if (uniqueIds == null) {
            uniqueIds = new ArrayList<>();
            for (int i = 0; i < termDocumentMatrix.getDocuments().size(); i++) {
                uniqueIds.add(i, new ArrayList<>());
            }
            int maxSum = 0;
            int uniqueId = 0;
            for (int i = 0; i < termMatrix.length; i++) {
                for (int j = 0; j < termMatrix[i].length; j++) {
                    uniqueId = maxSum + 1;
                    int termCount = termMatrix[i][j];
                    for (int k = 0; k < termCount; k++) {
                        uniqueIds.get(j).add(uniqueId);
                        uniqueId++;
                    }
                }
                maxSum += maxOccurrences.get(i);
            }
        }
        return uniqueIds;
    }

    private List<Integer> getMaxOccurrences(int[][] termMatrix) {
        List<Integer> maxOccurrences = new ArrayList<>();
        int max;
        for (int i = 0; i < termMatrix.length; i++) {
            max = Integer.MIN_VALUE;
            for (int j = 0; j < termMatrix[i].length; j++) {
                int termCount = termMatrix[i][j];
                if (termCount > max) {
                    max = termCount;
                }
            }
            maxOccurrences.add(max);
        }
        return maxOccurrences;
    }

    private int getMinHash(List<Integer> uniqueIds, Pair permutationFunction) {
        int min = Integer.MAX_VALUE;
        for (Integer uniqueId : uniqueIds) {
            int hash = computePermutation(permutationFunction, uniqueId);
            if (hash < min) {
                min = hash;
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

    public int[][] minHashMatrix() {
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
