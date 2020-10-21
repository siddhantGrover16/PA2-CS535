public class RoughTest {
    public static void main(String[] args) {

        MinHashSimilarities minHash = new MinHashSimilarities("TestFiles",6);
        int[][] tc =minHash.getTdMatrix();
        int[][] mh = minHash.getMhMatrix();
        int[] mhsig= minHash.minHashSig("baseball298.txt");

        System.out.println("Term_Doc");
        System.out.println("Number of Terms is "+ minHash.getTermCount());
        System.out.println(" Number of docs is "+ minHash.getDocCount());

        System.out.println(" ");



        for( int i=0; i<5;i++){//
            for(int j =0 ;j<tc[0].length;j++){
                System.out.print(tc[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println("------------------------------------------------");
        System.out.println("Min_Hash");
        System.out.println(" Number of perms is "+ minHash.numPerm);
        System.out.println("Number of docs is "+ minHash.getDocCount());

        System.out.println(" ");

        for( int i=0; i< mh.length;i++){
            for(int j =0 ;j<mh[0].length;j++){
                System.out.print(mh[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println("--------------------");

        double eJS = minHash.exactJaccard("space-0.txt","space-2.txt");
        double aJS = minHash.approximateJaccard("space-0.txt","space-2.txt");

        System.out.println("exact JacSim = "+ eJS);
        System.out.println("approx JacSim = "+ aJS);
        System.out.println("--------------------------------------------");

        System.out.println("minHashSig of baseball298");

        for (int i = 0; i < mhsig.length; i++) {
            System.out.print ( mhsig[i] + " ");
        }









    }
}
