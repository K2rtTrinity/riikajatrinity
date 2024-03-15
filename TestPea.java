import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class TestPea {
    public static void FailiLugemine(String failinimi,
                                     ArrayList<String> Riigid,
                                     ArrayList<String> Vihjed)
            throws Exception {

        File fail = new File(failinimi);
        try (Scanner sc = new Scanner(fail, "UTF-8")) {
            while (sc.hasNextLine()) {
                String rida = sc.nextLine();
                String[] andmed = rida.split(": ");
                //loeme andmed ridadest
                String riigiNimi = andmed[0];
                String vihje = andmed[1];
                //lisame riiginime ja vihje vastavasse järjendisse
                Riigid.add(riigiNimi);
                Vihjed.add(vihje);
            }
        }
    }
    public static void ListideGenereerimine(String failinimi, int küsimusteArv, Arvaja mängija) throws Exception {
        //proovin failiugemise meetodit rakendada
        ArrayList<String> Riigid = new ArrayList<>();
        ArrayList<String> Vihjed = new ArrayList<>();
        FailiLugemine(failinimi, Riigid, Vihjed);
        for (int i = 0; i < küsimusteArv; i++) {
            int juhuarv = (int) (Math.random() * 30);
            System.out.println("Vihje " + (i + 1) + ".");
            System.out.println(Vihjed.get(juhuarv));
            //lisabimeetod
            Scanner skänner = new Scanner(System.in);
            System.out.print("Teie vastus: ");
            String arvajaVastus = skänner.nextLine();

            if (arvajaVastus.equals(Riigid.get(juhuarv))){
                mängija.LisaPunktiskoor();
            }



        }
    }

    public static void main(String[] args) throws Exception {
        //Tervitamine
        System.out.println("Tere tulemast Riikide Äraarvamise mängu! \n" +
                "Enne kui asume mängu kallale, on vaja üle korrata mängureeglid.\n" +
                "Mäng on lihtne: \n" +
                "Ekraanile kuvatakse vihje, nt \"See riik on kaardi peal jalakujuline\",\n" +
                "mis järel pead sina pakkuma, mis riigiga on tegemist.\n" +
                "Kui vastasid õigesti, liidetakse sinu puntkiskoorile lisapunkt juurde.");
        System.out.println("Enne veel kas sa oleksid nii hea ja ütleksid, mis on sinu kui mängija nimi ning \n" +
                "milliste riikide peale sa oma tarkust katsetada soovid?");

        System.out.println();
        // Loome Scanner objekti, et saaksime kasutajalt sisendit lugeda
        Scanner scanner = new Scanner(System.in);
        // Küsime kasutajalt nime
        System.out.print("Teie nimi: ");
        String nimi = scanner.nextLine();
        System.out.print("Milliste riikide peale mängime (\"maailm\" või \"euroopa\"): ");
        String failinimi = scanner.nextLine();
        //küsime, kui pikk on küsimuste tsükkel
        System.out.print("Mitmele küsimusele soovite vastata (1-30): ");
        int küsimusteArv = Integer.parseInt(scanner.nextLine());
        // Sulgeme Scanner objekti


        Arvaja mängija = new Arvaja(nimi, 0, 0);


        String euroopa = failinimi.toLowerCase() + ".txt";
        ListideGenereerimine(euroopa, küsimusteArv, mängija);
        System.out.println();
        System.out.println("Lõpetasid mängu skooriga: " + mängija.getPunktiskoor());
        System.out.println(mängija);

        scanner.close();

    }

}
