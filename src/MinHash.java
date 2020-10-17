

public class MinHash {

   int numPerm;
   String folderName;
   int[][] mhMatrix;
   int[][] tdMatrix;
   int numTerms;
   String[] termMatrix;
   String[] docs;


    public MinHash(String folder, int numPermutations){




    }


    public String[] allDocs(){

        return docs;
    }

    public int[][] minHashMatrix(){

        return mhMatrix;
    }
    public int[][] termDocumenMatrix(){

        return tdMatrix;
    }
    public int numTerms(){

        return numTerms;
    }
    public int numPermutations(){

        return numPerm ;

    }


}
