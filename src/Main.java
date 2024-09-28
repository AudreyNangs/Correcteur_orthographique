import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Dictionnaire dictionnaire=new Dictionnaire("src/dico.txt");
        Faute faute=new Faute("src/fautes.txt");
        Trigrammes trigrammes = new Trigrammes(dictionnaire);
        long startTime = System.nanoTime();
        for(String mot:faute.getFaute()) {
            System.out.println(mot);
            //String mot = "agorithmma";
            trigrammes.genererTrigrammes(mot);
            ArrayList motAyantTrigramme = trigrammes.motsAvecTrigrammesCommuns(mot);
            HashMap<String, Integer> motEtOccurrence = trigrammes.occurrence(motAyantTrigramme);
            ArrayList<String> listCentMots = trigrammes.listeOrdonnee(motEtOccurrence);
            ArrayList<String> listCinqMots = trigrammes.listeDistance(listCentMots, mot);
            System.out.println(listCinqMots);
            System.out.println();
        }
        long endTime = System.nanoTime();
        long duree = endTime - startTime;
        System.out.println(duree/1000000000);

    }
}