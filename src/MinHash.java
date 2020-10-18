import java.io.*;
import java.util.*;

public class MinHash {

   int numPerm;
   String folderName;
   int[][] mhMatrix;
   int[][] tdMatrix;
   int numTerms;
   String[] termMatrix;
   String[] docs;



    public MinHash(String folder, int numPermutations){

        File dir = new File("src/"+folder);
        File [] files = dir.listFiles();
        String docs[] = new String[files.length];
        ArrayList<String> Terms = new ArrayList<String>();
        ArrayList<String>[] Dterms = new ArrayList[files.length];

        for(int i=0;i< docs.length;i++){
            docs[i]=files[i].getName();
        }
        for (int i =0; i< files.length;i++){
            Dterms[i] = new ArrayList<String>();
            try {
                File myObj = new File(files[i].getAbsolutePath());
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] terms = data.split("[,|.$':; ]");
                    for (int j=0; j< terms.length;j++){
                        Terms.add(terms[j]);// adding term to set of all terms
                        Dterms[i].add(terms[j]);// adding term to set of terms in particular document i
                    }

                    //System.out.println(data);
                }
                myReader.close();

            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        }



        for (int i = 0; i < Terms.size();i++) {
            if (Terms.get(i) == "the") {
                Terms.remove(i);
            }
            if (Terms.get(i).length() <= 2) {
                Terms.remove(i);
            }
        }
        

        Set<String> unionTerms = new HashSet<String>(Terms);// removes all duplicates from the arraylist of universal terms
        numTerms=unionTerms.size();

        }




    public String[] allDocs(){

        for(int i=0 ; i< docs.length;i++){
            System.out.println(docs[i]);
        }
        return docs;
    }

    public int[][] minHashMatrix(){

        return mhMatrix;
    }
    public int[][] termDocumenMatrix(){

        return tdMatrix;
    }
    public int numTerms(){

        return numTerms;
    }
    public int numPermutations(){

        return numPerm ;

    }


}
