import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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
        List<String> unionTerms = Terms.stream().distinct().collect(Collectors.toList());

        //Set<String> unionTerms = new HashSet<String>(Terms);// removes all duplicates from the arraylist of universal terms
        numTerms=unionTerms.size();

        for(int i=0;i< unionTerms.size();i++){// each row that
            for(int j=0;j< docs.length;j++){// each column
                int occurrencesOfTerm = Collections.frequency(Dterms[j], unionTerms.get(i));
                tdMatrix[i][j] = occurrencesOfTerm;
            }
        }

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

        for(int i = 0; i< tdMatrix.length; i++)
        {
            for(int j = 0; j<tdMatrix[0].length; j++)
            {
                System.out.print(tdMatrix[i][j]);
            }
            System.out.println();
        }
        return tdMatrix;
    }

    public int numTerms(){

        return numTerms;
    }
    public int numPermutations(){

        return numPerm ;

    }


}
