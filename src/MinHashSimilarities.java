import java.util.LinkedHashSet;
import java.util.List;

public class MinHashSimilarities {
    int[][] tdMatrix;
    int[][] mhMatrix;
    LinkedHashSet<String> allTerms;
    private List<String> indexedDocuments;
    MinHash minhash;
    double eJacSim;
    double aJacSim;
    int [] mhSig;
    int numPerm;
    int intersect = 0;
    int union = 0;
    int sameMH;


    public MinHashSimilarities(String folder, int numPermutations) {
        this.numPerm=numPermutations;

        minhash = new MinHash(folder, numPermutations);
        tdMatrix = minhash.termDocumentMatrix.getIntMatrix();
        mhMatrix = minhash.minHashMatrix();
    }

    public double exactJaccard(String file1, String file2) {
        indexedDocuments = minhash.termDocumentMatrix.getDocuments();
        int d1 = indexedDocuments.indexOf(file1);
        int d2 = indexedDocuments.indexOf(file2);
        int intersect = 0;
        int union = 0;
        int x_a;
        int x_b;

        for (int i = 0; i < tdMatrix[0].length; i++) {
            x_a = tdMatrix[d1][i];// frequency that term x appaears in d1 through term doc matrix
            x_b = tdMatrix[d2][i];// frrquency that term x appears in d2 through term doc matrix
            intersect += Math.min(x_a, x_b);// summation_ min(x_a,x_b)
            union += Math.max(x_a, x_b);// summation_ max(x_a,x_b)
        }
        System.out.println("intersect is "+ intersect);
        System.out.println("union is "+ union);
        eJacSim = (float)intersect / union;

        return eJacSim;
    }

    public double approximateJaccard(String file1, String file2){
        indexedDocuments = minhash.termDocumentMatrix.getDocuments();
        int d1 = indexedDocuments.indexOf(file1);
        System.out.println(" d1 is"+ d1);
        int d2 = indexedDocuments.indexOf(file2);
        System.out.println(" d2 is"+ d2 );

        for(int j =0 ; j< tdMatrix.length;j++){
            if (mhMatrix[d1][j]==mhMatrix[d2][j]){
               sameMH+=1;
               System.out.println("SAME");
            }
        }
        System.out.println("num perm is "+ numPerm);
        System.out.println(" same is " +sameMH );
        aJacSim = (float)sameMH/numPerm;

        return aJacSim;
    }

    public int[] minHashSig(String filename) {
        int d1 = indexedDocuments.indexOf(filename);
        mhSig = mhMatrix[d1];
        return mhSig;

    }

    public int[][] getMhMatrix() {
        return mhMatrix;
    }

    public int[][] getTdMatrix() {
        return tdMatrix;
    }
}

