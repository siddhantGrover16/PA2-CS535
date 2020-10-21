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
        indexedDocuments = minhash.termDocumentMatrix.getDocuments();
    }

    public double exactJaccard(String file1, String file2) {
       // indexedDocuments = minhash.termDocumentMatrix.getDocuments();
        int d1 = indexedDocuments.indexOf(file1);
        int d2 = indexedDocuments.indexOf(file2);
        int intersect = 0;
        int union = 0;
        int x_a;
        int x_b;

        for (int i = 0; i < tdMatrix.length; i++) {// goes through each term  in td matrix
            x_a = tdMatrix[i][d1];// frequency that term x appaears in d1 through term doc matrix
            x_b = tdMatrix[i][d2];// frrquency that term x appears in d2 through term doc matrix
            intersect += Math.min(x_a, x_b);// summation_ min(x_a,x_b)
            union += Math.max(x_a, x_b);// summation_ max(x_a,x_b)
        }
//        System.out.println ("length is "+ tdMatrix.length);
//        System.out.println("intersect is "+ intersect);
//        System.out.println("union is "+ union);
        eJacSim = (double)intersect / union;
        return eJacSim;
    }

    public double approximateJaccard(String file1, String file2){
       // indexedDocuments = minhash.termDocumentMatrix.getDocuments();
        int d1 = indexedDocuments.indexOf(file1);
       // System.out.println(" d1 is"+ d1);
        int d2 = indexedDocuments.indexOf(file2);
        //System.out.println(" d2 is"+ d2 );

        for(int j =0 ; j< mhMatrix.length;j++){
            if (mhMatrix[j][d1]==mhMatrix[j][d2]){
               sameMH+=1;
             //  System.out.println("SAME at row "+ j );
            }
        }
       // System.out.println("num perm is "+ numPerm);
        //System.out.println(" same is " +sameMH );
        aJacSim = (double)sameMH/numPerm;

        return aJacSim;
    }

    public int[] minHashSig(String filename) {
        int d1 = indexedDocuments.indexOf(filename);
        mhSig = mhMatrix[d1];
        return mhSig;

    }
    public int getTermCount(){
        return minhash.numTerms();
    }

    public int getDocCount(){
        return minhash.allDocs().size();
    }

    public int getNumPerm(){
        return numPerm;
    }

    public int[][] getMhMatrix() {
        return mhMatrix;
    }

    public int[][] getTdMatrix() {
        return tdMatrix;
    }
}

