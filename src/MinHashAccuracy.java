import java.util.List;
public class MinHashAccuracy {

    MinHashSimilarities mhs ;
    int inaccuratePairs =0;
    double errorPar;
    int [][] tdMatrix;
    int [][] mhMatrix;
    List<Pair> inPair;
    double eJac;
    double aJac;

    public MinHashAccuracy(String folder,int numPermutations,double error){
        this.errorPar=error;
        mhs = new MinHashSimilarities(folder,numPermutations);
        tdMatrix= mhs.getTdMatrix();
        mhMatrix=mhs.getMhMatrix();
        accuracy(folder, numPermutations, error);

    }

    public int accuracy(String directory, int numPerm,double errorPar){

        for (int i=0 ; i <tdMatrix[0].length-1;i++){//  first doc of pair  0.....n-1
            for(int j=i+1;j<tdMatrix[0].length;j++){// 2nd doc of poirs , will result in 01,02.....n-1,n
                eJac = eJaci(i,j);
                aJac= aJaci(i,j);
                if(Math.abs(eJac-aJac)>errorPar){
                    inaccuratePairs+=1;
                    inPair.add(new Pair(i,j));// can be used to see indices of inaacurate document pairs if we need too
                }
            }
        }

        return inaccuratePairs;
    }

    public double eJaci(int index1, int index2){
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
        eJac = ((double)intersect)/ union;

        return eJac;

    }

    public double aJaci(int index1, int index2){
        int same =0;
        for(int j =0 ; j< mhMatrix.length;j++){
            if (mhMatrix[j][index1]==mhMatrix[j][index2]){
                same+=1;
                //  System.out.println("SAME at row "+ j );
            }
        }
        // System.out.println("num perm is "+ numPerm);
        //System.out.println(" same is " +sameMH );
        aJac = ((double)same)/ mhs.getNumPerm();

        return aJac;
    }




}
