import java.io.File;

public class RoughTest {
    public static void main(String[] args) {
        MinHashSimilarities minHash = new MinHashSimilarities("src/space",400);
        int[][] tc =minHash.getTdMatrix();
        int[][] mh = minHash.getMhMatrix();
       // int[] mhsig= minHash.minHashSig("baseball298.txt");

        System.out.println("Term_Doc");
        System.out.println("Number of Terms is "+ minHash.getTermCount());
        System.out.println(" Number of docs is "+ minHash.getDocCount());

        System.out.println(" ");



//        for( int i=0; i< tc.length;i++){//
//            for(int j =0 ;j<tc[0].length;j++){
//                System.out.print(tc[i][j]+ " ");
//            }
//            System.out.println();
//        }
        System.out.println("------------------------------------------------");
        System.out.println("Min_Hash");
      //  System.out.println(" Number of perms is "+ minHash.numPerm);
        System.out.println("Number of docs is "+ minHash.getDocCount());
//        int[][] tc =minHash.getTdMatrix();
//        int[][] mh = minHash.getMhMatrix();
        //int[] mhsig= minHash.minHashSig("baseball298.txt");

//        System.out.println("Term_Doc");
//        System.out.println("Number of Terms is "+ minHash.getTermCount());
//        System.out.println(" Number of docs is "+ minHash.getDocCount());
//
//        System.out.println(" ");
//
//
//
////        for( int i=0; i< tc.length;i++){//
////            for(int j =0 ;j<tc[0].length;j++){
////                System.out.print(tc[i][j]+ " ");
////            }
////            System.out.println();
////        }
//        System.out.println("------------------------------------------------");
//        System.out.println("Min_Hash");
//        System.out.println(" Number of perms is "+ minHash.numPerm);
//        System.out.println("Number of docs is "+ minHash.getDocCount());
//
//        System.out.println(" ");
//
//        for( int i=0; i< mh.length;i++){
//            for(int j =0 ;j<mh[0].length;j++){
//                System.out.print(mh[i][j]+ " ");
//            }
//            System.out.println();
//        }
//        System.out.println("--------------------");
//
//        double eJS = minHash.exactJaccard("space-643.txt","space-643.txt");
//        double aJS = minHash.approximateJaccard("space-643.txt","space-643.txt");
//
//        System.out.println("exact JacSim = "+ eJS);
//        System.out.println("approx JacSim = "+ aJS);
//        System.out.println("--------------------------------------------");
//
//        System.out.println("minHashSig of baseball298");

        File folder = new File("src/TestFiles2");
        File[] listOfFiles = folder.listFiles();

        String[] files = new String[listOfFiles.length];
        for (int i = 0; i < listOfFiles.length; i++) {
            files[i] = listOfFiles[i].getName();
        }
//
//        TESTLSH lsh = new TESTLSH(minHash.mhMatrix, files, 8);
        //LSH lsh2 = new LSH(minHash.getMhMatrix(), files, 20);

        //System.out.println(lsh2.nearDuplicatesOf("baseball0.txt"));

        //for (int i = 0; i < mhsig.length; i++) {
        //    System.out.print ( mhsig[i] + " ");
        //}

//        MinHashAccuracy minHashAccuracy = new MinHashAccuracy("TestFiles", 600, .05);
//        minHashAccuracy.
        //MinHashTime minHashTime = new MinHashTime("C:\\Users\\Ibro\\Downloads\\space\\space", 800);
//        LSH lsh = new LSH()
        NearDuplicates nearDuplicates = new NearDuplicates("src/TestFiles2", 400, 1);
        System.out.println(nearDuplicates.nearDuplicateDetector("baseball26.txt"));
    }
}
