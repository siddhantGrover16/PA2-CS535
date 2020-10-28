import java.util.List;

public class MinHashAccuracy {

    public MinHashAccuracy() {
    }

    public int accuracy(String folder, int numPerm, double errorPar){
        int inaccuratePairs = 0;
        double eJac;
        double aJac;

        MinHashSimilarities mhs = new MinHashSimilarities(folder,numPerm);
        int numdocs = mhs.getDocCount();
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

        System.out.println(" The number of inaacurate pairs for " + numPerm + " permutions  with error parameter "+ errorPar + " is "+ inaccuratePairs + ". The total number of distinct pair are " + (numdocs * (numdocs-1))/2);
        System.out.println( "-x-x-x-x-x-xx-x-x-x--x--x-x-x-x-x-x-x-x-x-x-x--x-x-x-x-x--x-x-x-x-x-x-x");

        return inaccuratePairs;
    }

}
