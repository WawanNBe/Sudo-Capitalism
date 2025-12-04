import extensions.CSVFile; // import des CSV
import extensions.File; // import des txt

class Main extends Program{

    // -----------------------------------------------------------------< VARIABLES GLOBALES >------------------------------------------------------------------

    CSVFile config = loadCSV("./extensions/config/config.csv"); // fichier config du jeu
    CSVFile save = loadCSV("./extensions/config/save.csv"); // fichier sauvegarde du jeu

    String pathAccueil = "./extensions/tui/accueil.txt"; // accueil du jeu
    String pathButDuJeu = "./extensions/tui/butDuJeu.txt"; // règles du jeu
    String pathTabDeBord = "./extensions/tui/tabDeBord.txt"; // les menus du jeu
    String pathEmployes = "./extensions/tui/employes.txt"; // les menus du jeu
    String pathProduction = "./extensions/tui/production.txt"; // les menus du jeu
    String pathResultats = "./extensions/tui/resultats.txt"; // le tui des resulstats hebdomadaires

    // conditions de fin de jeu (défaite et victoire)
    boolean finAnnee = false; // la partie s'arrête si l'année se termine (sert à limiter le jeu à 52 tours)
    boolean faillite = false; // si le joueur fait faillite, la partie s'arrête
    boolean objectifAtteint = false; // si le joueur atteint l'objectif financier, la partie est gagnée


    // ------------------------------------------------< IMPLEMENTATION DES FONCTIONS SUIVIES DE LEURS TESTS RESPECTIFS >------------------------------------------------------------------

    // tests de la fonction stringToInt
    void test_stringToInt() {
        String nbTest = "123";

        assertEquals(123, stringToInt("123"));
        assertEquals(0, stringToInt("0"));
        assertEquals(1, stringToInt("1"));
    }


    // -----------------------------------------------------------------< GESTION DE LA DATE >------------------------------------------------------------------

    // implémentation de la fonction newDate
    Date newDate(int jour, int mois) {
        Date date = new Date();

        date.jour = jour;
        date.mois = mois;

        return date;
    }

    // tests de la fonction newDate
    void test_newDate() {
        Date premierJanvier = newDate(1,1);

        assertEquals(1, premierJanvier.jour);
        assertEquals(1, premierJanvier.mois);
    }

    // implémentation de la fonction qui affiche la date
    String dateToString(Date date) {
        String[] mois = new String[] {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre"};

        return date.jour + " " + mois[date.mois -1];
    }

    // tests de la fonction d'affichage de date
    void test_dateToString() {
        Date premierJanvier = newDate(1,1);

        assertEquals("1 Janvier", dateToString(premierJanvier));
    }

    // implémentation de la fonction qui gère la date (incrémente le jour/mois lorsqu'elle est appelée)
    void gestionDate(Date date) {
        int[] mois = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // les nombres de jours pour chaque mois

        if (date.mois == 12 && date.jour == 31) {
            finAnnee = true;
        } else if (date.jour == mois[date.mois -1]) {
            date.jour = 1;
            date.mois += 1;
        } else {
            date.jour += 1;
        }
    }

    //tests de la fonction gestionDate
    void test_gestionDate() {
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


    // -----------------------------------------------------------------< GESTION DE L'ENTREPRISE >------------------------------------------------------------------

    // implémentation de la fonction newEntreprise
    Entreprise newEntreprise(int budget, int charges, int nbEmployes, int stocks, int prixDeVente, int niveauProduction) {
        Entreprise entreprise = new Entreprise();

        entreprise.budget = budget;
        entreprise.charges = charges;
        entreprise.nbEmployes = nbEmployes;
        entreprise.stocks = stocks;
        entreprise.prixDeVente = prixDeVente;
        entreprise.niveauProduction = niveauProduction;
        entreprise.produitsVendusParJour = entreprise.nbEmployes * (25 + (25 * entreprise.niveauProduction));

        return entreprise;
    }

    // tests de la fonction newEntreprise
    void test_newEntreprise() {
        Entreprise entreprise = newEntreprise(2000, 1500, 1, 10, 20, 1);

        assertEquals(2000, entreprise.budget);
        assertEquals(1500, entreprise.charges);
        assertEquals(1, entreprise.nbEmployes);
        assertEquals(10, entreprise.stocks);
        assertEquals(20, entreprise.prixDeVente);
        assertEquals(1, entreprise.niveauProduction);
        assertEquals(4, entreprise.produitsVendusParJour);
    }


    // -----------------------------------------------------------------< GESTION DU JEU >------------------------------------------------------------------
    // implémentation de la fonction recruterEmploye
    void recruterEmploye(Entreprise entreprise) {
        if (entreprise.budget < 300) {
            println(rgb(200, 200, 0, true) + "< Fonds insufisants ! >" + RESET);
        } else {
            println(rgb(0, 200, 0, true) + "< Vous avez recruté un employé ! >" + RESET);
            entreprise.nbEmployes ++;
            entreprise.charges += 300;
        }
    }

    // tests de la fonction recruterEmploye
    void test_recruterEmploye() {
        Entreprise entreprise = newEntreprise(2000, 1500, 1, 10, 20, 1); // création d'une entreprise par défaut

        recruterEmploye(entreprise);

        assertEquals(2, entreprise.nbEmployes);
        assertEquals(1800, entreprise.charges);
    }

    // implémentation de la fonction virerEmploye
    void virerEmploye(Entreprise entreprise) {
        if (entreprise.nbEmployes <= 1){
            println(rgb(200, 200, 0, true) + "< Vous ne pouvez pas virer d'employer car vous n'en avez qu'un ! >" + RESET);
        } else {
            println(rgb(200, 0, 0, true) + "< Vous avez viré un employé ! >" + RESET);
            entreprise.nbEmployes --;
            entreprise.charges -= 300;
        }
    }

    // tests de la fonction virerEmploye
    void test_virerEmploye() {
        Entreprise entreprise1 = newEntreprise(2000, 1500, 2, 10, 20, 1); // création d'une entreprise par défaut

        virerEmploye(entreprise1);

        assertEquals(1, entreprise1.nbEmployes);
        assertEquals(1200, entreprise1.charges);

        Entreprise entreprise2 = newEntreprise(2000, 1500, 1, 10, 20, 1); // cas où on ne peut pas virer d'employe

        virerEmploye(entreprise2);

        assertEquals(1, entreprise2.nbEmployes);
        assertEquals(1500, entreprise2.charges);
    }

    // implémentation de la fonction exploiterEmploye
    void exploiterEmploye(Entreprise entreprise) {
        if (entreprise.budget < 100) {
            println(rgb(200, 200, 0, true) + "< Fonds insufisants ! >" + RESET);
        } else {
            println(rgb(200, 0, 0, true) + "< Vous avez commencé à exploiter un employé ! >" + RESET);
            entreprise.nbEmployes ++;
            entreprise.charges += 100;
        }
    }

    // tests de la fonction exploiterEmploye
    void test_exploiterEmploye() {
        Entreprise entreprise = newEntreprise(2000, 1500, 1, 10, 20, 1); // création d'une entreprise par défaut

        exploiterEmploye(entreprise);

        assertEquals(2, entreprise.nbEmployes);
        assertEquals(1600, entreprise.charges);
    }

    // implémentation de la fonction baisserSalaires
    void baisserSalaires(Entreprise entreprise) {
        if (entreprise.charges <= 0){
            entreprise.charges = 0;
            println(rgb(200, 200, 0, true) + "< Vous ne payez déjà pas vos employés ! >" + RESET);
        } else {
            println(rgb(200, 200, 0, true) + "< Vous avez baissé les salaires de vos employés ! >" + RESET);
            entreprise.charges = entreprise.charges - (100 * entreprise.nbEmployes);
        }
    }

    // tests de la fonction baisserSalaires
    void test_baisserSalaires() {
        Entreprise entreprise = newEntreprise(2000, 1500, 1, 10, 20, 1); // création d'une entreprise par défaut

        baisserSalaires(entreprise);

        assertEquals(1400, entreprise.charges);
    }

    
    // implémentation de la fonction ameliorerProduction
    void acheterMachine(Entreprise entreprise) {
        if (entreprise.budget < 1000) {
            println(rgb(200, 200, 0, true) + "< Fonds insufisants ! >" + RESET);

        } else {
            println(rgb(0, 200, 0, true) + "< Vous avez acheté une nouvelle machine ! >" + RESET);
            entreprise.budget -= 1000;
            entreprise.niveauProduction ++;
        }
    }

    // tests de la fonction ameliorerProduction
    void test_acheterMachine() {
        Entreprise entreprise = newEntreprise(2000, 1500, 1, 10, 20, 1); // création d'une entreprise par défaut

        acheterMachine(entreprise);

        assertEquals(1000, entreprise.budget);
        assertEquals(2, entreprise.niveauProduction);
    }

    // implémentation de la fonction ameliorerProduction
    void acheterContrefacon(Entreprise entreprise) {
        if (entreprise.budget < 50) {
            println(rgb(200, 200, 0, true) + "< Fonds insufisants ! >" + RESET);

        } else {
            println(rgb(200, 200, 0, true) + "< Vous avez acheté des contrefaçons en chine ! >" + RESET);
            entreprise.budget -= 200;
            entreprise.stocks += 50;
        }
    }

    // tests de la fonction ameliorerProduction
    void test_acheterContrefacon() {
        Entreprise entreprise = newEntreprise(2000, 1500, 1, 10, 20, 1); // création d'une entreprise par défaut

        acheterContrefacon(entreprise);

        assertEquals(1950, entreprise.budget);
        assertEquals(60, entreprise.stocks);
    }

    // implémentation de la fonction ameliorerProduction
    void snifferCoke(Entreprise entreprise) {
        if (entreprise.budget < 450) {
            println(rgb(200, 200, 0, true) + "< Fonds insufisants ! >" + RESET);

        } else {
            println(rgb(200, 0, 0, true) + "< Vous avez fait sniffer de la coke à vos employés ! >" + RESET);
            entreprise.budget -= 450;
            entreprise.niveauProduction += 1;
        }
    }

    // tests de la fonction ameliorerProduction
    void test_snifferCoke() {
        Entreprise entreprise = newEntreprise(2000, 1500, 1, 10, 20, 1); // création d'une entreprise par défaut

        snifferCoke(entreprise);

        assertEquals(1550, entreprise.budget);
        assertEquals(2, entreprise.niveauProduction);
    }


    // -----------------------------------------------------------------< MISE À JOUR DES DONNÉES DE L'ENTREPRISE >------------------------------------------------------------------

    // implémentation de la fonction updateEntreprise
    void updateEntreprise(Date date, Entreprise entreprise, boolean faillite, boolean objectifAtteint) {
        // calcul sur la semaine et mise à jour
        entreprise.produitsVendusParJour = entreprise.nbEmployes * (25 + (25 * entreprise.niveauProduction)); // on met à jour le nombre de produits vendus par jours
        
        if ((entreprise.produitsVendusParJour * 5) > entreprise.stocks) { // si pas assez de stocks
            entreprise.budget += (entreprise.stocks * entreprise.prixDeVente); // on ne vend que les stocks dispos 
            entreprise.stocks = 0; // on soustrait les produits vendus au stock
            
        } else { // sinon on applique la formule normale
            entreprise.budget += (entreprise.produitsVendusParJour * entreprise.prixDeVente) * 5; // on calcule les revenus sur 5 jours (lundi -> vendredi)
            entreprise.stocks -= entreprise.produitsVendusParJour * 5; // on soustrait les produits vendus de la semaine au stock
        }

        entreprise.budget -= entreprise.charges; // on soustrait les charges au chiffre d'affaire
        date.jour += 7;

        // on cherche si une condition (financière) d'arrêt du jeu est présente
        if (entreprise.budget <= 0) {
            faillite = true;

        } // else if (entreprise.budget >= objectif) {
        //     objectifAtteint = true;
        // }
    }

    // tests de la fonction updateEntreprise
    void test_updateEntreprise() {
        Entreprise entreprise = newEntreprise(2000, 1500, 1, 10, 20, 1); // création d'une entreprise par défaut
        Date date = newDate(1, 1); // création d'une nouvelle date

        // simulation d'un tour de jeu
        entreprise.nbEmployes ++; // ajout employé
        entreprise.charges = entreprise.charges - (100 * entreprise.nbEmployes); // baisse salaires
        entreprise.budget -= 300; entreprise.niveauProduction ++; // amélioration de la production

        updateEntreprise(date, entreprise, faillite, objectifAtteint);

        assertEquals(600, entreprise.budget);
        assertEquals(1300, entreprise.charges);
        assertEquals(2, entreprise.nbEmployes);
        assertEquals(0, entreprise.stocks);
        assertEquals(20, entreprise.prixDeVente);
        assertEquals(2, entreprise.niveauProduction);
        assertEquals(10, entreprise.produitsVendusParJour);
    }


    // -----------------------------------------------------------------< GESTION DE L'AFFICHAGE  >------------------------------------------------------------------

    // implémentation de la fonction tuiToString
    String tuiToString(Date date, Entreprise entreprise, String pathTui) {
        File tui = new File(pathTui); // on récupère le chemin car le readline consomme le fichier
        String affichage = ""; // le tui sans les variables ni les couleurs
        String nouvChaine = ""; // le tui post traitement
        int idx = 0;
        int temp = 0;

        int[] tabVar = new int[] {entreprise.budget, // on récupère les stats de l'entreprise dans un tableau
                                  entreprise.charges,
                                  entreprise.nbEmployes,
                                  entreprise.stocks,
                                  entreprise.prixDeVente,
                                  entreprise.niveauProduction,
                                  entreprise.produitsVendusParJour};

        while (ready(tui)) { // on récupère le tui en String
            affichage = affichage + readLine(tui) + '\n';
        }

        while (idx < length(affichage)) { // on traite le String et on y met les variables/couleurs
            if (charAt(affichage, idx) == '%' && (charAt(affichage, idx +1) >= '0' && charAt(affichage, idx +1) <= '9')) {
                nouvChaine += rgb(100, 100, 200, true) + tabVar[(int) (charAt(affichage, idx +1) - '0')]  + RESET;
                idx += 2;

            } else if (charAt(affichage, idx) == '%' && charAt(affichage, idx +1) == 'D') {
                nouvChaine += rgb(100, 100, 200, true) + dateToString(date)  + RESET;
                idx += 2;

            } else {
                nouvChaine += rgb(128, 128, 128, true) + charAt(affichage, idx) + RESET;
                idx ++;
            }
        }
        return nouvChaine;
    }


    // -----------------------------------------------------------------< ALGORITHME PRINCIPAL >------------------------------------------------------------------

    void algorithm() {
        // initialisation des variables
        int choix;

        // initialisation des variables du jeu via le CSV
        Date date = newDate(1,1);
        Entreprise entreprise = newEntreprise(stringToInt(getCell(config, 1, 0)), // budget
                                              stringToInt(getCell(config, 1, 1)), // charges
                                              stringToInt(getCell(config, 1, 2)), // nbEmployes
                                              stringToInt(getCell(config, 1, 3)), // stocks
                                              stringToInt(getCell(config, 1, 4)), // prix de vente
                                              stringToInt(getCell(config, 1, 5))); // niveau de production

        int objectif = stringToInt(getCell(config, 1, 0));  // objectif financier à atteindre

        // boucle principale du jeu
        while (!finAnnee && !faillite && !objectifAtteint) { // tant que l'une des conditions d'arrêt n'est pas déclenchée
            println(tuiToString(date, entreprise, pathAccueil)); // on affiche l'écran d'accueil
            choix = readInt();

            if (choix == 1) { // on lance la partie 
                while (choix != 4){
                    println(tuiToString(date, entreprise, pathTabDeBord));
                    choix = readInt();

                    if (choix == 1) { // on ouvre le menu employés
                        while (choix != 5){
                            println(tuiToString(date, entreprise, pathEmployes));
                            println(entreprise.nbEmployes);
                            choix = readInt();

                            if (choix == 1) {
                                recruterEmploye(entreprise);

                            }else if (choix == 2) {
                                virerEmploye(entreprise);

                            }else if (choix == 3) {
                                exploiterEmploye(entreprise);
                                
                            }else if (choix == 4) {
                                baisserSalaires(entreprise);
                                
                            }
                        }choix = -1;

                    } else if (choix == 2) { // on ouvre le menu production      
                        while (choix != 4) {
                            println(tuiToString(date, entreprise, pathProduction));
                            choix = readInt();

                            if (choix == 1) {
                                acheterMachine(entreprise);

                            } else if (choix == 2) {
                                acheterContrefacon(entreprise);
                                
                            } else if (choix == 3) {
                                snifferCoke(entreprise);

                            }
                        }choix = -1;

                    } else if (choix == 3) {
                        updateEntreprise(date, entreprise, faillite, objectifAtteint); // on met à jour les stats de l'entreprise

                        while (choix != 1){
                            println(tuiToString(date, entreprise, pathResultats));
                            choix = readInt();
                        }choix = 1;
                    }
                }

            } else if (choix == 2) { // on affiche les règles du jeu        
                while (choix != 1) {
                    println(tuiToString(date, entreprise, pathButDuJeu));
                    choix = readInt();
                }choix = -1;

            } else {
                objectifAtteint = !objectifAtteint; // on change l'état d'une des variables d'arrêt pour stopper le jeu si le joueur sélectionne "Quitter"
            }
        }
    }
}