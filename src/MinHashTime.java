import  java.lang.System.*;

public class MinHashTime {
    String folder;
    int numPerm;
  //  Pair timerPair;
    MinHashSimilarities mhs;

    public MinHashTime(){

    }

    public void timer(String folder,int numPerm){
        this.folder=folder;
        this.numPerm =numPerm;
       long starttime = System.currentTimeMillis();
        mhs=new MinHashSimilarities(folder,numPerm);
        long endtime = System.currentTimeMillis();
        double[][] ejac = new double [mhs.getDocCount()][mhs.getDocCount()];
        double[][] ajac = new double [mhs.getDocCount()][mhs.getDocCount()];
         long timetocreate=endtime-starttime;
        System.out.println("Time taken to create instance "+ timetocreate*1.0/1000 +"seconds");

        starttime = System.currentTimeMillis();
        for(int i=0; i<mhs.getDocCount()-1;i++){
            for(int j=i+1;j<mhs.getDocCount();j++){
                ejac[i][j] = mhs.computeEJac(i,j);
            }
        }
        endtime = System.currentTimeMillis();
        long exactJtime= endtime-starttime;
        System.out.println("Time taken to get Exact Jaccard for all pairs "+ (exactJtime*1.0)/1000 + "seconds");


        starttime = System.currentTimeMillis();
        for(int i=0; i<mhs.getDocCount()-1;i++){
            for(int j=i+1;j<mhs.getDocCount();j++){
                ajac[i][j] = mhs.computeAJac(i,j);
            }
        }

        endtime = System.currentTimeMillis();
        long approxJtime= endtime-starttime;

        System.out.println("Time taken to get Approximate Jaccard for all pairs "+  (approxJtime* 1.0)/1000 + "seconds");
        System.out.println();
    }



}
