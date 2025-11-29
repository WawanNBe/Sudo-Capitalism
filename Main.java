import extensions.CSVFile; // import des CSV
import extensions.File; // import des txt

class Main extends Program{

    CSVFile config = loadCSV("./extensions/config/config.csv"); // fichier config du jeu
    CSVFile save = loadCSV("./extensions/config/save.csv"); // fichier sauvegarde du jeu

    String pathAccueil = "./extensions/tui/accueil.txt"; // accueil du jeu
    String pathButDuJeu = "./extensions/tui/butDuJeu.txt"; // règles du jeu
    String pathTabDeBord = "./extensions/tui/tabDeBord.txt"; // les menus du jeu
    String pathEmployes = "./extensions/tui/employes.txt"; // les menus du jeu
    String pathProduction = "./extensions/tui/production.txt"; // les menus du jeu

    // conditions de fin de jeu (défaite et victoire)
    boolean finAnnee = false; // la partie s'arrête si l'année se termine (sert à limiter le jeu à 52 tours)
    boolean faillite = false; // si le joueur fait faillite, la partie s'arrête
    boolean objectifAtteint = false; // si le joueur atteint l'objectif financier, la partie est gagnée


    // implémentation de la fonction stringToInt
    int stringToInt(String string){
        int nombre = 0;
        
        for (int idx = 0; idx<length(string); idx++){
            nombre = nombre * 10 + (charAt(string, idx) - '0');
        }
        return nombre;
    }

    // tests de la fonction stringToInt
    void test_stringToInt(){
        String nbTest = "123";

        assertEquals(123, stringToInt("123"));
        assertEquals(0, stringToInt("0"));
        assertEquals(1, stringToInt("1"));
    }


    // implémentation de la fonction newDate
    Date newDate(int jour, int mois){
        Date date = new Date();

        date.jour = jour;
        date.mois = mois;

        return date;
    }

    // tests de la fonction newDate
    void test_newDate(){
        Date premierJanvier = newDate(1,1);

        assertEquals(1, premierJanvier.jour);
        assertEquals(1, premierJanvier.mois);
    }

    // implémentation de la fonction qui affiche la date
    String dateToString(Date date){
        String[] mois = new String[] {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre"};

        return date.jour + " " + mois[date.mois -1];
    }

    // implémentation des tests pour la fonction d'affichage de date
    void test_dateToString(){
        Date premierJanvier = newDate(1,1);

        assertEquals("1 Janvier", dateToString(premierJanvier));
    }


    // implémentation de la fonction newEntreprise
    Entreprise newEntreprise(int budget, int charges, int nbEmployes, int stocks, int prixDeVente, int niveauProduction){
        Entreprise entreprise = new Entreprise();

        entreprise.budget = budget;
        entreprise.charges = charges;
        entreprise.nbEmployes = nbEmployes;
        entreprise.stocks = stocks;
        entreprise.prixDeVente = prixDeVente;
        entreprise.niveauProduction = niveauProduction;
        entreprise.produitsVendusParJour = nbEmployes * (3 + niveauProduction);

        return entreprise;
    }

    // tests de la fonction newEntreprise
    void test_newEntreprise(){
        Entreprise entrepriseTest = newEntreprise(2000, 1500, 1, 10, 20, 1);

        assertEquals(2000, entrepriseTest.budget);
        assertEquals(1500, entrepriseTest.charges);
        assertEquals(1, entrepriseTest.nbEmployes);
        assertEquals(10, entrepriseTest.stocks);
        assertEquals(20, entrepriseTest.prixDeVente);
        assertEquals(1, entrepriseTest.niveauProduction);
        assertEquals(4, entrepriseTest.produitsVendusParJour);
    }


    // implémentation de la fonction qui gère la date (incrémente le jour/mois lorsqu'elle est appelée)
    void gestionDate(Date date){
        int[] mois = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // les nombres de jours pour chaque mois

        if (date.mois == 12 && date.jour == 31){
            finAnnee = true;
        } else if (date.jour == mois[date.mois -1]){
            date.jour = 1;
            date.mois += 1;
        } else {
            date.jour += 1;
        }
    }

    //tests de la fonction gestionDate
    void test_gestionDate(){
        // test du passage d'un jour normal
        Date premierJanvier = newDate(1,1);
        gestionDate(premierJanvier);

        assertEquals(2, premierJanvier.jour);
        assertEquals(1, premierJanvier.mois);

        // test du passage au mois suivant
        Date changementMois = newDate(31,1);
        gestionDate(changementMois);

        assertEquals(1, changementMois.jour);
        assertEquals(2, changementMois.mois);
    }

    // implémentation de la fonction tuiToString A CORRIGER SELON LA SUITE
    String tuiToString(Date date, Entreprise entreprise, String pathTui){
        File tui = new File(pathTui);
        String affichage = "";
        while (ready(tui)){
            affichage = affichage + readLine(tui) + '\n';
        }
        return affichage;
    }


    void algorithm(){
        // initialisation des variables
        int choix;

        // initialisation des variables du jeu via le CSV
        Date date = newDate(1,1);
        Entreprise entreprise = newEntreprise(stringToInt(getCell(config, 1, 0)),
                                              stringToInt(getCell(config, 1, 1)),
                                              stringToInt(getCell(config, 1, 2)),
                                              stringToInt(getCell(config, 1, 3)),
                                              stringToInt(getCell(config, 1, 4)),
                                              stringToInt(getCell(config, 1, 5)));

        // boucle principale du jeu
        while (!finAnnee && !faillite && !objectifAtteint){
            println(tuiToString(date, entreprise, pathAccueil)); // on affiche l'écran d'accueil
            choix = readInt();

            if (choix == 1){ // on lance la partie 

                while (choix != 3){
                    println(tuiToString(date, entreprise, pathTabDeBord));
                    choix = readInt();

                    if (choix == 1){ // on ouvre le menu employés

                        while (choix != 5){
                            println(tuiToString(date, entreprise, pathEmployes));
                            choix = readInt();

                            if (choix == 1){
                                println("< Vous avez recruté un employé ! >");

                            }else if (choix == 2){
                                println("< Vous avez viré un employé ! >");

                            }else if (choix == 3){
                                println("< Vous avez commencé à exploiter un employé ! >");
                                
                            }else if (choix == 4){
                                println("< Vous avez baissé les salaires de vos employés ! >");
                                
                            }
                
                        }choix = -1;

                    } else if (choix == 2){ // on ouvre le menu production

                        while (choix != 5){
                            println(tuiToString(date, entreprise, pathProduction));
                            choix = readInt();

                            if (choix == 1){
                                println("< Vous avez amélioré la qualité de la production ! >");

                            }else if (choix == 2){
                                println("< Vous avez acheté de nouvelles machines ! >");

                            }else if (choix == 3){
                                println("< Vous avez acheté des contrefaçons en chine ! >");
                                
                            }else if (choix == 4){
                                println("< Vous avez fait sniffer de la coke à vos employés ! >");
                                
                            }
                        }

                    }choix = -1;
                }// ajouter le calcul de la semaine ici

            } else if (choix == 2){ // on affiche les règles du jeu
            
                while (choix != 1){
                    println(tuiToString(date, entreprise, pathButDuJeu));
                    choix = readInt();
                }choix = -1;

            } else {
                objectifAtteint = !objectifAtteint;
            }
        }
    }
}