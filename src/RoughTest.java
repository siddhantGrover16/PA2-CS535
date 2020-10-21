public class RoughTest {
    public static void main(String[] args) {
        MinHash minHash = new MinHash("TestFiles", 5);
        TermDocumentMatrix termDocumentMatrix = minHash.getTermDocumentMatrix();
        int[][] something = termDocumentMatrix.getIntMatrix();
        int i = 0;
    }
}
