import java.util.*;

class Trigrammes {

    private HashMap<String, List<String>> ListMotsTrigramme;
    //associer chaque trigramme possible à une liste de mots du dictionnaire qui contiennent ce trigramme.
    List<String> mots;
    // structure de données qui associe les trigrammes aux mots du dictionnaire.
    public Trigrammes(Dictionnaire dictionnaire) {
        ListMotsTrigramme = new HashMap<>();
        for (String mot : dictionnaire.getDictionnaire()) {
            List<String> trigrammes = genererTrigrammes(mot);
            for (String trigramme : trigrammes) {
                if (ListMotsTrigramme.containsKey(trigramme)) {
                   mots = ListMotsTrigramme.get(trigramme);
                } else {
                    mots = new ArrayList<>();
                    ListMotsTrigramme.put(trigramme, mots);
                }
                mots.add(mot);


            }
        }
    }
    public List<String> genererTrigrammes(String mot) {
        List<String> listTrigrammes = new ArrayList<>();
        mot = "§" + mot + "§"; // Ajouter des caractères spéciaux au début et à la fin
        for (int i = 0; i < mot.length() - 2; i++) {
            listTrigrammes.add(mot.substring(i, i + 3));
        }
        return listTrigrammes;
    }
    public void afficherTrig() {
        for (String trigramme : ListMotsTrigramme.keySet()) {
            System.out.println(trigramme + "  : " + ListMotsTrigramme.get(trigramme));
        }
    }
    public ArrayList<String> motsAvecTrigrammesCommuns(String mot) {
        List<String> trigrammes = genererTrigrammes(mot);// liste de trigramme d'un mot;
        ArrayList<String> motscontenantTrig = new ArrayList<>();//liste de mots du dico ayant un trigramme en commun avec le mot
        for (String trigramme : trigrammes) {
            if (ListMotsTrigramme.containsKey(trigramme)) {
                motscontenantTrig.addAll(ListMotsTrigramme.get(trigramme));
              //  System.out.println(trigramme + " : " + ListMotsTrigramme.get(trigramme));
            }
        }
        return motscontenantTrig;
    }

 // renvoit un mot et son nombre d'occurence
    public HashMap<String, Integer> occurrence(ArrayList<String> stringArrayList){
        HashMap<String, Integer> motEtOccurrence = new HashMap<>();
        for(String mot : stringArrayList){
           if(motEtOccurrence.get(mot)==null){   /*si le mot n'est pas encore dans la map ont met 1 sinon
                                             on incremente de 1 le  nombre d'occurence et on met à jour dans la map*/
                motEtOccurrence.put(mot,1);
            }
            else{
                Integer nombreOccurrence = motEtOccurrence.get(mot);
                nombreOccurrence =nombreOccurrence + 1;
                motEtOccurrence.put(mot,nombreOccurrence);
            }
        }
        return motEtOccurrence;
    }

  // renvoit les 100 mots de la Map ayant le plus grand nombre d'occurences
    public ArrayList<String> listeOrdonnee(HashMap<String,Integer> motEtOccurrence){
        ArrayList<String> listeOrdonnee = new ArrayList<>(100);
        for(String mot: motEtOccurrence.keySet()){
            if(listeOrdonnee.size()<100){
                listeOrdonnee.add(mot);
            }
            else{
                //je parcours la liste si je trouve un plus grand min ,je supprime le min et j'ajoute le mot
                String m = minimum(listeOrdonnee,motEtOccurrence);
                if ( motEtOccurrence.get(mot) > motEtOccurrence.get(m)){
                    listeOrdonnee.remove(m);
                    listeOrdonnee.add(mot);
                }
            }
        }
        return listeOrdonnee;
    }
//renvoit le mot qui a le plus petit nombre d'occurence
    public String minimum(List<String> liste, HashMap<String, Integer> motEtOccurrence){
        String mot = null;
        int min = 100000000;
        for (String m : liste){
            if (motEtOccurrence.get(m) < min ){
                min = motEtOccurrence.get(m);
                mot = m;
            }
        }
        return mot;
    }

    public ArrayList<String> listeDistance(List<String> list, String mot) {
        ArrayList<String> listeDesMotsProches = new ArrayList<>(5);
        HashMap< String,Integer> motEtDistance = new HashMap<>();

        if(Dictionnaire.contientMot(mot)){
        listeDesMotsProches.add(mot);
           return listeDesMotsProches;
        }
        else{
        for(String element: list){
            int d = Dictionnaire.distanceEntreDeuxMots(mot , element);
            motEtDistance.put(element,d);
        }
        for(String m : list ){
            if (listeDesMotsProches.size() < 5)
                listeDesMotsProches.add(m);
            else {
                String motMax= Distancemax(listeDesMotsProches,motEtDistance);
                if(motEtDistance.get(m) < motEtDistance.get(motMax)){
                    listeDesMotsProches.remove(motMax);
                    listeDesMotsProches.add(m);
                }
            }
        }
        }
        return listeDesMotsProches;
    }
    //mot avec la plus grande distance
    public String Distancemax(List<String> list, HashMap<String, Integer> motEtDistance){
        String mot = null;
        int max = 0;
        for (String m : list){
            if (motEtDistance.get(m) > max ){
                max = motEtDistance.get(m);
                mot = m;
            }
        }
        return mot;
    }
}

