public class RoughTest {
    public static void main(String[] args) {
<<<<<<< Updated upstream

=======
        MinHashSimilarities minHash = new MinHashSimilarities("TestFiles",40);
        int[][] tc =minHash.getTdMatrix();
        int[][] mh = minHash.getMhMatrix();



        System.out.println("Term_Doc");
        System.out.println("Number of Terms is "+ minHash.tdMatrix[0].length);


        for( int i=0; i< tc.length;i++){
            for(int j =0 ;j<tc[0].length;j++){
                System.out.print(tc[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println("--------------------");
        System.out.println("Min_Hash");

        for( int i=0; i< mh.length;i++){
            for(int j =0 ;j<mh[0].length;j++){
                System.out.print(mh[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println("--------------------");

        double eJS = minHash.exactJaccard("space-1.txt","space-4.txt");
        double aJS = minHash.approximateJaccard("space-1.txt","space-4.txt");

        System.out.println("exact JacSim = "+ eJS);
        System.out.println("approx JacSim = "+ aJS);










//
>>>>>>> Stashed changes
    }
}
