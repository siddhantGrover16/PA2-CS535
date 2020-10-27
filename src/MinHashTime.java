import  java.lang.System.*;

public class MinHashTime {
    String folder;
    int numPerm;
  //  Pair timerPair;
    MinHashSimilarities mhs;
    /*
    This class tests whether it is faster to estimate Jaccard Similarities using MinHash matrix. This
class has a method named timer that
 Gets name of a folder, number of permutations to be used as parameters, and creates an
instance of MinHashSimilarities. Report the the time taken to construct an instance of
MinHashSimilarities.
 For every pair of les in the folder compute the exact Jaccard Similarity ; Report the time
taken (in seconds) for this task.
 Compute the MinHashMatrix and use this matrix to estimate Jaccard Similarity of every pair
of documents in the collection. Report the time taken for this task.

System.currentTimeinMillis()or by using  System.nanoTime().


     */

    public MinHashTime(String folder,int numPerm){
        this.folder=folder;
        this.numPerm =numPerm;
        timer(folder,numPerm);
    }

    public void timer(String folder,int numPerm){
       long starttime = System.currentTimeMillis();
        mhs=new MinHashSimilarities(folder,numPerm);
        long endtime = System.currentTimeMillis();
         long timetocreate=endtime-starttime;
        System.out.println("Time taken to create instance "+ timetocreate*1.0/1000 +"seconds");

        starttime = System.currentTimeMillis();
        for(int i=0; i<mhs.getDocCount()-1;i++){
            for(int j=i+1;j<mhs.getDocCount();j++){
                mhs.computeEJac(i,j);
            }
        }
        endtime = System.currentTimeMillis();
        long exactJtime= endtime-starttime;
        System.out.println("Time taken to get Exact Jaccard for all pairs "+ (exactJtime*1.0)/1000 + "seconds");


        starttime = System.currentTimeMillis();
        for(int i=0; i<mhs.getDocCount()-1;i++){
            for(int j=i+1;j<mhs.getDocCount();j++){
                mhs.computeAJac(i,j);
            }
        }

        endtime = System.currentTimeMillis();
        long approxJtime= endtime-starttime;

        System.out.println("Time taken to get Approximate for all pairs "+  (approxJtime* 1.0)/1000 + "seconds");
    }



}
