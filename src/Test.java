public class Test {
    public static void main(String[] args) {

        //MinHashTime Test
      MinHashTime mht = new MinHashTime();
      MinHashAccuracy mha=new MinHashAccuracy();
      String folder ="src/TestFiles";

      mht.timer(folder,600);

      mha.accuracy(folder,400,0.04);
      mha.accuracy(folder,400,0.07);
      mha.accuracy(folder,400,0.09);

      mha.accuracy(folder,600,0.04);
      mha.accuracy(folder,600,0.07);
      mha.accuracy(folder,600,0.09);

      mha.accuracy(folder,800,0.04);
      mha.accuracy(folder,800,0.07);
      mha.accuracy(folder,800,0.09);

    }
}

