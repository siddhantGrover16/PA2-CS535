import java.util.List;

public class MinHashAccuracy {
    public MinHashAccuracy(){
    }

    public int accuracy(String directory, int numPerm, double errorPar){
        int inaccuratePairs = 0;
        double eJac;
        double aJac;

        MinHashSimilarities mhs = new MinHashSimilarities(directory,numPerm);
        int[][] tdMatrix= mhs.getTdMatrix();

        for (int i=0 ; i <tdMatrix[0].length-1;i++){//  first doc of pair  0.....n-1
            for(int j=i+1;j<tdMatrix[0].length;j++){// 2nd doc of poirs , will result in 01,02.....n-1,n
                eJac = mhs.computeEJac(i,j);
                aJac = mhs.computeAJac(i,j);
                if(Math.abs(eJac-aJac)>errorPar){
                    inaccuratePairs+=1;
                    //inPair.add(new Pair(i,j));// can be used to see indices of inaacurate document pairs if we need too
                }
            }
        }

        return inaccuratePairs;
    }

}
