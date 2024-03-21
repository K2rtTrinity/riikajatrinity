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
                
                😎 Kui vastasid õigesti, liidetakse sinu puntkiskoorile lisapunkt juurde.
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


            System.out.print("Milliste riikide peale mängime (\"maailm\" või \"euroopa\"): ");
            String failinimi = scanner.nextLine().toLowerCase();

            //Kontrollime, et failinimi oleks õige.
            while (!failinimi.equals("maailm")){
                if (failinimi.equals("euroopa")) break;
                System.out.println("Mängu andmetes ei ole kahjuks sellist tüüpi riikide nimekirja :(");
                System.out.print("Milliste riikide peale mängime (\"maailm\" või \"euroopa\"): ");
                failinimi = scanner.nextLine().toLowerCase();
            };

            //K, kui pikk on küsimuste tsükkel.
            System.out.print("Mitmele küsimusele soovite vastata (1-30): ");
            int küsimusteArv = Integer.parseInt(scanner.nextLine());



            String riikidefail = failinimi.toLowerCase() + ".txt";
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

                //Vihje genereerimine:
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
            }
            else jätkame = false;
        }
        // Sulgeme Scanner objekti.
        scanner.close();

    }
    /**Siin võiks võtja välja selgitamise abimeetod olla, kui meil on parameetriks list Arvajatest.*/


    public static void main(String[] args) throws Exception {
        Tervitamine();

        //Talletame arvajate listi.
        ArrayList<Arvaja> mängijad = new ArrayList<>();
        ArrayList<String> nimed = new ArrayList<>();
        boolean jätkame = true;
        System.out.println();

        Mäng(mängijad, nimed, jätkame);


        //Kuvame kõigi mängijate punktiskoorid ekraanile.
        for (Arvaja arvaja : mängijad) {
            System.out.println(arvaja);
        }

        //Selgitame võitja.
        /**võtija kuulutamise abimeetod*/

        /**Lõpetussõnad*/
    }

}
