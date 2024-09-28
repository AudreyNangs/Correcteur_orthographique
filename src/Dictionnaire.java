import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dictionnaire {
    private static HashSet<String> dictionnaire = new HashSet<>();
    public HashSet<String> getDictionnaire() {
        return dictionnaire;
    }
    private String mot;
    public Dictionnaire( String fich) throws FileNotFoundException {
        //lecture du fchier;
        File fichier = new File(fich);
        Scanner sc = new Scanner(fichier);
        try {
            while (sc.hasNextLine()) {
                mot = sc.nextLine();
                dictionnaire.add(mot);
            }
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void afficheDictionnaire( ){
        for (String mot : dictionnaire) {
            System.out.println(mot);
        }

    }
        // Calcul de la distance d'édition
        public static int distanceEntreDeuxMots(String mot1, String mot2) {
            int longueur1 = mot1.length();
            int longueur2 = mot2.length();

            // Créez un tableau pour stocker les sous-problèmes résolus
            int[][] matrice = new int[longueur1 + 1][longueur2 + 1];

            // Initialisation du tableau
            for (int i = 0; i <= longueur1; i++) {
                matrice[i][0] = i;
            }
            for (int j = 0; j <= longueur2; j++) {
                matrice[0][j] = j;
            }

            // Remplissage du tableau
            for (int i = 1; i <= longueur1; i++) {
                for (int j = 1; j <= longueur2; j++) {
                    if (mot1.charAt(i - 1) == mot2.charAt(j - 1)) {
                        matrice[i][j] = matrice[i - 1][j - 1];
                    } else {
                        matrice[i][j] = 1 + Math.min(Math.min(matrice[i - 1][j], matrice[i][j - 1]), matrice[i - 1][j - 1]);
                    }
                }
            }

            return matrice[longueur1][longueur2];// la dernière case du tableau.
        }
    public static boolean contientMot(String mot) {
        return dictionnaire.contains(mot);
    }

}

