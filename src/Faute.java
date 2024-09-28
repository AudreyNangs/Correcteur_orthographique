import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Faute {
    private String mot;
    private ArrayList<String> faute = new ArrayList();

    public ArrayList<String> getFaute() {
        return faute;
    }

    public Faute(String fich) throws FileNotFoundException {
        File fichier = new File(fich);
        Scanner sc = new Scanner(fichier);
        try {
            while (sc.hasNextLine()) {
                mot = sc.nextLine();
                faute.add(mot);
            }
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void afficher() {
        for (String mot : faute) {
            System.out.println(mot);
        }
    }

}
