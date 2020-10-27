public class Test {
    public static void main(String[] args) {

        //MinHashTime Test
      MinHashTime mht = new MinHashTime();
      MinHashAccuracy mha=new MinHashAccuracy();

      mht.timer("src/TestFiles",600);

      mha.accuracy("src/TestFiles",400,0.04);
      mha.accuracy("src/TestFiles",400,0.07);
      mha.accuracy("src/TestFiles",400,0.09);

      mha.accuracy("src/TestFiles",600,0.04);
      mha.accuracy("src/TestFiles",600,0.07);
      mha.accuracy("src/TestFiles",600,0.09);

      mha.accuracy("src/TestFiles",800,0.04);
      mha.accuracy("src/TestFiles",800,0.07);
      mha.accuracy("src/TestFiles",800,0.09);

    }
}

