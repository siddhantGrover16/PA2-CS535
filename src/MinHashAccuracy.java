public class MinHashAccuracy extends MinHashSimilarities {
    String directory;
    int numPerm;
    float errorPar;
    int accuracy;
    double eJac;
    double aJac;


    public MinHashAccuracy(String folder, int numPermutations) {
        super(folder, numPermutations);
        this.directory=folder;
        this.numPerm=numPermutations;
    }

    public int accuracy(String directory, int numPerm,float errorPar){
        int accuracy =0;

        for (int i=0 ; i <tdMatrix[0].length-1;i++){
            for(int j=i+1;j<tdMatrix[0].length;j++){
                eJac = eJaci(i,j);
                aJac= aJaci(i,j);
                if(Math.abs(eJac-aJac)>errorPar){
                    accuracy+=1;
                }
            }

        }

        return accuracy;
    }

    public double eJaci(int index1,int index2){
        int intersect = 0;
        int union = 0;
        int x_a;
        int x_b;

        for (int i = 0; i < tdMatrix.length; i++) {// goes through each term  in td matrix
            x_a = tdMatrix[i][index1];// frequency that term x appaears in d1 through term doc matrix
            x_b = tdMatrix[i][index2];// frrquency that term x appears in d2 through term doc matrix
            intersect += Math.min(x_a, x_b);// summation_ min(x_a,x_b)
            union += Math.max(x_a, x_b);// summation_ max(x_a,x_b)
        }
//        System.out.println ("length is "+ tdMatrix.length);
//        System.out.println("intersect is "+ intersect);
//        System.out.println("union is "+ union);
        eJac = (double)intersect / union;

        return eJac;
    }
    public double aJaci(int index1,int index2){
        return aJac;
    }

//    public MinHashAccuracy(String folder,int numPermutations,float error){
//
//    }


}
