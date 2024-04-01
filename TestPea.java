import com.sun.jdi.InconsistentDebugInfoException;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class TestPea {
    public static void Tervitamine(){
        //Sissejuhatav tervitamine:
        System.out.println("""
                
                🤩 Tere tulemast Riikide Äraarvamisemängu! 🤩

                Enne kui asume mängu kallale, on vaja üle korrata mängureeglid. 📋
                
                🎲 Mäng on lihtne:
                Ekraanile kuvatakse vihje, nt "See riik on kaardi peal jalakujuline",
                mis järel pead sina pakkuma, mis riigiga on tegemist.
                
                😎 Kui vastasid õigesti, liidetakse sinu puntkiskoorile punkt.
                """);
        System.out.println("""
                🛑✋
                Enne veel...
                Kas sa oleksid nii hea ja ütleksid, mis on sinu kui mängija nimi 
                ning
                milliste riikide peale sa oma tarkust katsetada soovid?
                
                
                """);
    }
    public static void Mäng(ArrayList<Arvaja> mängijad, ArrayList<String> nimed, boolean jätkame) throws Exception {
        // Loome Scanner objekti, et saaksime kasutajalt sisendit lugeda.
        Scanner scanner = new Scanner(System.in);
        while (jätkame){
            // Küsime kasutajalt nime.
            System.out.print("Kes mängib: ");
            String nimi = scanner.nextLine();


            System.out.print("Milliste riikide peale mängime (\"maailm\" või \"Euroopa Liit\"): ");
            String failinimi = scanner.nextLine().toLowerCase().trim().replace(" ", "");

            //Kontrollime, et failinimi oleks õige.
            while (!failinimi.equals("maailm")){
                if (failinimi.equals("euroopaliit")) break;
                System.out.println("Mängu andmetes ei ole kahjuks sellist tüüpi riikide nimekirja :(");
                System.out.print("Milliste riikide peale mängime (\"maailm\" või \"euroopaliit\"): ");
                failinimi = scanner.nextLine().toLowerCase();
            };

            //Küsime, kui pikk on küsimuste tsükkel.
            System.out.print("Mitmele küsimusele soovite vastata (1-27): ");
            int küsimusteArv = Integer.parseInt(scanner.nextLine());



            String riikidefail = failinimi + ".txt";
            //Loome Riikideklassi isendi.
            Riikideklass riigid = new Riikideklass(riikidefail, new ArrayList<>(), new ArrayList<>());

            if (nimed.contains(nimi)){      //Kontrollime, kas eelnevatest mängijatest keegi mängib.
                int indeks = nimed.indexOf(nimi);
                Arvaja mängija = mängijad.get(indeks);

                //Vihje genereerimine:
                riigid.ListideGenereerimine(küsimusteArv, mängija);

                System.out.println();
                System.out.println("Sinu punktiskoor on praegu: " + mängija.getPunktiskoor());
                System.out.println(mängija);
            } else {
                Arvaja mängija = new Arvaja(nimi, 0);

                //Vihje genereerimine ja vastamine:
                riigid.ListideGenereerimine(küsimusteArv, mängija);

                System.out.println();
                System.out.println("Sinu punktiskoor on praegu: " + mängija.getPunktiskoor());

                mängijad.add(mängija);
                nimed.add(nimi);
            }

            //Otsustame, kuna lõpetada while tsükkel.
            System.out.println("Kas soovite veel arvata riike? (jah/ei)");
            String mängujätkamine = scanner.nextLine();
            if (mängujätkamine.equals("jah")) {
                jätkame = true;
            }
            else jätkame = false;
        }
        // Sulgeme Scanner objekti.
        scanner.close();

    }

    public static Arvaja võitjaSelgitamine(ArrayList<Arvaja> mängijad){
        if (mängijad.isEmpty()) {
            return null;
        }
        Arvaja võidumees = mängijad.get(0);
        for (Arvaja arvaja : mängijad) {
            if (arvaja.getPunktiskoor() > võidumees.getPunktiskoor()) {
                võidumees = arvaja;
            }

        }
        return võidumees;
    }

    public static void main(String[] args) throws Exception {
        Tervitamine();

        //Talletame arvajate listi.
        ArrayList<Arvaja> mängijad = new ArrayList<>();
        ArrayList<String> nimed = new ArrayList<>();
        boolean jätkame = true;
        System.out.println();

        Mäng(mängijad, nimed, jätkame);

        System.out.println("""
                                
                🎲 Mäng sai läbi! 🎲

                Mängijate lõppskoorid olid järgmised:
                """);

        for (Arvaja arvaja : mängijad) {
            System.out.println(arvaja);
        }

        //Selgitame võitja.
        Arvaja võitja = võitjaSelgitamine(mängijad);
        System.out.println("Ning mängu võitis " + võitja.toString());

        System.out.println("""
                
                Loodame, et sul oli lõbus, õppisid mõningaid uusi fakte riikide kohta ning kinnistasid enda riikide teadmisi!
          
                Näeme varsti! ✋🤩
                """);


    }

}