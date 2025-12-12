//             ██████  █    ██ ▓█████▄  ▒█████      ▄████▄   ▄▄▄       ██▓███   ██▓▄▄▄█████▓ ▄▄▄       ██▓     ██▓  ██████  ███▄ ▄███▓
//           ▒██    ▒  ██  ▓██▒▒██▀ ██▌▒██▒  ██▒   ▒██▀ ▀█  ▒████▄    ▓██░  ██▒▓██▒▓  ██▒ ▓▒▒████▄    ▓██▒    ▓██▒▒██    ▒ ▓██▒▀█▀ ██▒
//           ░ ▓██▄   ▓██  ▒██░░██   █▌▒██░  ██▒   ▒▓█    ▄ ▒██  ▀█▄  ▓██░ ██▓▒▒██▒▒ ▓██░ ▒░▒██  ▀█▄  ▒██░    ▒██▒░ ▓██▄   ▓██    ▓██░
//             ▒   ██▒▓▓█  ░██░░▓█▄   ▌▒██   ██░   ▒▓▓▄ ▄██▒░██▄▄▄▄██ ▒██▄█▓▒ ▒░██░░ ▓██▓ ░ ░██▄▄▄▄██ ▒██░    ░██░  ▒   ██▒▒██    ▒██ 
//           ▒██████▒▒▒▒█████▓ ░▒████▓ ░ ████▓▒░   ▒ ▓███▀ ░ ▓█   ▓██▒▒██▒ ░  ░░██░  ▒██▒ ░  ▓█   ▓██▒░██████▒░██░▒██████▒▒▒██▒   ░██▒
//           ▒ ▒▓▒ ▒ ░░▒▓▒ ▒ ▒  ▒▒▓  ▒ ░ ▒░▒░▒░    ░ ░▒ ▒  ░ ▒▒   ▓▒█░▒▓▒░ ░  ░░▓    ▒ ░░    ▒▒   ▓▒█░░ ▒░▓  ░░▓  ▒ ▒▓▒ ▒ ░░ ▒░   ░  ░
//           ░ ░▒  ░ ░░░▒░ ░ ░  ░ ▒  ▒   ░ ▒ ▒░      ░  ▒     ▒   ▒▒ ░░▒ ░      ▒ ░    ░      ▒   ▒▒ ░░ ░ ▒  ░ ▒ ░░ ░▒  ░ ░░  ░      ░
//           ░  ░  ░   ░░░ ░ ░  ░ ░  ░ ░ ░ ░ ▒     ░          ░   ▒   ░░        ▒ ░  ░        ░   ▒     ░ ░    ▒ ░░  ░  ░  ░      ░   
//                 ░     ░        ░        ░ ░     ░ ░            ░  ░          ░                 ░  ░    ░  ░ ░        ░         ░   
//                              ░                  ░                                                                                  


// -----------------------------------------------------------------< IMPORTS DES EXTENSIONS >------------------------------------------------------------------

import extensions.CSVFile; // import des CSV
import extensions.File; // import des txt

class Main extends Program {


    // -----------------------------------------------------------------< VARIABLES GLOBALES >------------------------------------------------------------------

    CSVFile config = loadCSV("./extensions/config/config.csv"); // fichier config du jeu
    CSVFile save = loadCSV("./extensions/config/save.csv"); // fichier sauvegarde du jeu
    CSVFile employes = loadCSV("./extensions/config/employes.csv"); // fichier contenant la liste des employés

    // utilisation des chemins afin de recharger les fichiers dans la fonction d'affichage
    String pathAccueil = "./extensions/tui/accueil.txt"; // accueil du jeu
    String pathButDuJeu = "./extensions/tui/butDuJeu.txt"; // règles du jeu
    String pathTabDeBord = "./extensions/tui/tabDeBord.txt"; // les menus du jeu
    String pathEmployes = "./extensions/tui/employes.txt"; // les menus du jeu
    String pathProduction = "./extensions/tui/production.txt"; // les menus du jeu
    String pathResultats = "./extensions/tui/resultats.txt"; // le tui des resulstats hebdomadaires



    // ------------------------------------------------< IMPLEMENTATION DES FONCTIONS SUIVIES DE LEURS TESTS RESPECTIFS >------------------------------------------------------------------

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
        // on utilise un tableau de mois
        String[] mois = new String[] {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre"};

        return date.jour + " " + mois[date.mois -1]; // affichage au format texte
    }

    // tests de la fonction d'affichage de date
    void test_dateToString() {
        Date premierJanvier = newDate(1,1);
        Date deuxDecembre = newDate(2,12);

        assertEquals("1 Janvier", dateToString(premierJanvier));
        assertEquals("2 Decembre", dateToString(deuxDecembre));
    }


    // implémentation de la fonction qui gère la date (incrémente le jour/mois lorsqu'elle est appelée)
    void gestionDate(Date date) {
        int[] mois = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // les nombres de jours pour chaque mois

        if (date.jour == mois[date.mois -1]) { // si on atteint le dernier jour d'un mois
            date.jour = 1; // on réinitialise le jour
            date.mois += 1; // on change de mois

        } else {
            date.jour += 1; // sinon on incrémente simplement le jour
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



    // -----------------------------------------------------------------< GESTION CLASSE EMPLOYE >------------------------------------------------------------------

    // implémentation de la fonction newEmploye
    Employe newEmploye(String prenom, String nom) {
        Employe employe = new Employe();
        
        employe.prenom = prenom;
        employe.nom = nom;
        employe.nivSatisfaction = random(1, 10);

        return employe;
    }

    // tests de la fonction newEmploye
    void test_newEmploye() {
        Employe employe = newEmploye("Jean", "Bonbeur");

        assertEquals(employe.nom, "Bonbeur");
        assertEquals(employe.prenom, "Jean");
        assertTrue(employe.nivSatisfaction >= 1 && employe.nivSatisfaction <= 10);
    }



    // -----------------------------------------------------------------< GESTION CLASSE ENTREPRISE >------------------------------------------------------------------

    // implémentation de la fonction newEntreprise
    Entreprise newEntreprise(int budget, int charges, int nbEmployes, int stocks, int prixDeVente, int niveauProduction, int reputation) {
        Entreprise entreprise = new Entreprise();

        entreprise.budget = budget;
        entreprise.charges = charges;
        entreprise.nbEmployes = nbEmployes;
        entreprise.stocks = stocks;
        entreprise.prixDeVente = prixDeVente;
        entreprise.niveauProduction = niveauProduction;
        entreprise.productionJournaliere = entreprise.nbEmployes * (25 + (25 * entreprise.niveauProduction));
        entreprise.reputation = reputation;

        return entreprise;
    }

    // tests de la fonction newEntreprise
    void test_newEntreprise() {
        Entreprise entreprise = newEntreprise(2000, 1500, 1, 10, 20, 1, 0);

        assertEquals(2000, entreprise.budget);
        assertEquals(1500, entreprise.charges);
        assertEquals(1, entreprise.nbEmployes);
        assertEquals(10, entreprise.stocks);
        assertEquals(20, entreprise.prixDeVente);
        assertEquals(1, entreprise.niveauProduction);
        assertEquals(50, entreprise.productionJournaliere);
        assertEquals(0, entreprise.reputation);
    }



    // -----------------------------------------------------------------< GESTION DES CHOIX DU JOUEUR >------------------------------------------------------------------

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
        Entreprise entreprise = newEntreprise(2000, 1500, 1, 10, 20, 1, 0); // création d'une entreprise par défaut

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
        Entreprise entreprise1 = newEntreprise(2000, 1500, 2, 10, 20, 1, 0); // création d'une entreprise par défaut

        virerEmploye(entreprise1);

        assertEquals(1, entreprise1.nbEmployes);
        assertEquals(1200, entreprise1.charges);

        Entreprise entreprise2 = newEntreprise(2000, 1500, 1, 10, 20, 1, 0); // cas où on ne peut pas virer d'employe

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
        Entreprise entreprise = newEntreprise(2000, 1500, 1, 10, 20, 1, 0); // création d'une entreprise par défaut

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
        Entreprise entreprise = newEntreprise(2000, 1500, 1, 10, 20, 1,0); // création d'une entreprise par défaut

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
        Entreprise entreprise = newEntreprise(2000, 1500, 1, 10, 20, 1, 0); // création d'une entreprise par défaut

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
        Entreprise entreprise = newEntreprise(2000, 1500, 1, 10, 20, 1, 0); // création d'une entreprise par défaut

        acheterContrefacon(entreprise);

        assertEquals(1800, entreprise.budget);
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
        Entreprise entreprise = newEntreprise(2000, 1500, 1, 10, 20, 1, 0); // création d'une entreprise par défaut

        snifferCoke(entreprise);

        assertEquals(1550, entreprise.budget);
        assertEquals(2, entreprise.niveauProduction);
    }



    // -----------------------------------------------------------------< GESTION DU JEU >------------------------------------------------------------------

    // implémentation de la fonction finDePartie
    boolean finDePartie(Entreprise entreprise, Date date, int objectif) {
        if ((entreprise.budget <= 0) || (date.jour == 31 && date.mois == 12) || (entreprise.budget >= objectif)) {
            return true;
        } else {
            return false;
        }
    }

    // tests de la fonction finDePartie
    void test_finDePartie() {
        int objectif = 1000000; // objectif par défaut
        Entreprise entrepriseFaillite = newEntreprise(0, 1500, 1, 10, 20, 1, 0); // création d'une entreprise en faillite
        Date dateAleatoire = newDate(15, 2); // date aléatoire

        assertTrue(finDePartie(entrepriseFaillite, dateAleatoire, objectif));

        Entreprise entrepriseLambda = newEntreprise(2000, 1500, 1, 10, 20, 1, 0); // création d'une entreprise lambda
        Date dateFin = newDate(31, 12); // date fin d'année

        assertTrue(finDePartie(entrepriseLambda, dateFin, objectif));

        Entreprise entrepriseObjectif = newEntreprise(1000000, 1500, 1, 10, 20, 1, 0); // création d'une entreprise qui atteint l'objectif

        assertTrue(finDePartie(entrepriseObjectif, dateAleatoire, objectif));
    }


    // implémentation de la fonction intToString
    String intToString(int entier) {
        String string = "";
        return string += entier;
    }

    // tests de la fonction intToString
    void test_intToString() {
        assertEquals("123", intToString(123));
        assertEquals("0", intToString(0));
    }



    // -----------------------------------------------------------------< SAUVEGARDE ET CHARGEMENT >------------------------------------------------------------------

    // implémentation de la fonction saveGame
    void saveGame(Entreprise entreprise, Date date) {
        String[][] sauvegarde = {
        {"Budget", "Charges", "NbEmployes", "Stocks", "PrixDeVente", "NiveauProduction", "ProductionJournaliere", "Reputation", "Jour", "Mois"}, // titres des colonnes du tableau (sert uniquement à des fins visuelles)
        
        // récupération des données de l'entreprise et de la date dans un tableau
        {intToString(entreprise.budget),
         intToString(entreprise.charges),
         intToString(entreprise.nbEmployes),
         intToString(entreprise.stocks),
         intToString(entreprise.prixDeVente),
         intToString(entreprise.niveauProduction),
         intToString(entreprise.productionJournaliere),
         intToString(entreprise.reputation),

         intToString(date.jour),
         intToString(date.mois)}};

        saveCSV(sauvegarde, "./extensions/config/save.csv");
    }


    // implémentation de la fonction loadGame
    void loadGame(Entreprise entreprise, Date date) {
        // remplacement de la date
        date.jour = stringToInt(getCell(save, 1, 8));
        date.mois = stringToInt(getCell(save, 1, 9));

        // remplacement de l'entreprise
        entreprise.budget = stringToInt(getCell(save, 1, 0));
        entreprise.charges = stringToInt(getCell(save, 1, 1));
        entreprise.nbEmployes = stringToInt(getCell(save, 1, 2));
        entreprise.stocks = stringToInt(getCell(save, 1, 3));
        entreprise.prixDeVente = stringToInt(getCell(save, 1, 4));
        entreprise.niveauProduction = stringToInt(getCell(save, 1, 5));
        entreprise.productionJournaliere = stringToInt(getCell(save, 1, 6));
        entreprise.reputation = stringToInt(getCell(save, 1, 7));

        println(rgb(0, 200, 0, true) + "< Partie chargée ! >" + RESET); // affichage d'un message de chargement
        sleep(500);
    }



    // -----------------------------------------------------------------< MISE À JOUR DES DONNÉES DE L'ENTREPRISE >------------------------------------------------------------------

    // implémentation de la fonction updateEntreprise
    void updateEntreprise(Date date, Entreprise entreprise) {
        // calcul sur la semaine et mise à jour
        entreprise.productionJournaliere = entreprise.nbEmployes * (25 + (25 * entreprise.niveauProduction)); // on met à jour le nombre de produits vendus par jours
        
        if ((entreprise.productionJournaliere * 5) > entreprise.stocks) { // si pas assez de stocks
            entreprise.budget += (entreprise.stocks * entreprise.prixDeVente); // on ne vend que les stocks dispos 
            entreprise.stocks = 0; // on soustrait les produits vendus au stock
            
        } else { // sinon on applique la formule normale
            entreprise.budget += (entreprise.productionJournaliere * entreprise.prixDeVente) * 5; // on calcule les revenus sur 5 jours (lundi -> vendredi)
            entreprise.stocks -= entreprise.productionJournaliere * 5; // on soustrait les produits vendus de la semaine au stock
        }

        entreprise.budget -= entreprise.charges; // on soustrait les charges au chiffre d'affaire
        for (int jour = 0; jour < 7; jour++){
            gestionDate(date);
        }
    }

    // tests de la fonction updateEntreprise
    void test_updateEntreprise() {
        Entreprise entreprise = newEntreprise(2000, 1500, 1, 10, 20, 1, 0); // création d'une entreprise par défaut
        Date date = newDate(1, 1); // création d'une nouvelle date

        // simulation d'un tour de jeu
        entreprise.nbEmployes ++; // ajout employé
        entreprise.charges = entreprise.charges - (100 * entreprise.nbEmployes); // baisse salaires
        entreprise.budget -= 300; entreprise.niveauProduction ++; // amélioration de la production

        updateEntreprise(date, entreprise);

        assertEquals(600, entreprise.budget);
        assertEquals(1300, entreprise.charges);
        assertEquals(2, entreprise.nbEmployes);
        assertEquals(0, entreprise.stocks);
        assertEquals(20, entreprise.prixDeVente);
        assertEquals(2, entreprise.niveauProduction);
        assertEquals(150, entreprise.productionJournaliere);
    }



    // -----------------------------------------------------------------< GESTION DE L'AFFICHAGE  >------------------------------------------------------------------

    // implémentation de la fonction tuiToString
    String tuiToString(Date date, Entreprise entreprise, String pathTui) {
        File tui = new File(pathTui); // on récupère le chemin car le readline consomme le fichier

        String affichage = ""; // le tui sans les variables ni les couleurs
        String nouvChaine = ""; // le tui post traitement

        int idx = 0; // indice pour parcourir le TUI

        // on récupère les stats de l'entreprise dans un tableau
        int[] tabVar = new int[] {entreprise.budget,
                                  entreprise.charges,
                                  entreprise.nbEmployes,
                                  entreprise.stocks,
                                  entreprise.prixDeVente,
                                  entreprise.niveauProduction,
                                  entreprise.productionJournaliere,
                                  entreprise.reputation};

        // on récupère le tui en String
        while (ready(tui)) { // tant que le fichier texte possède des lignes
            affichage = affichage + readLine(tui) + '\n'; // on ajoute ces lignes au TUI au format String
        }

        // on traite le String et on y met les variables/couleurs
        while (idx < length(affichage)) { // tant que tous les caractères n'ont pas été lus
            if (charAt(affichage, idx) == '%' && (charAt(affichage, idx +1) >= '0' && charAt(affichage, idx +1) <= '9')) { // si on détecte un placeholder de variable
                nouvChaine += rgb(100, 100, 200, true) + tabVar[(int) (charAt(affichage, idx +1) - '0')]  + RESET; // on remplace le placeholder par la variable qui correspond
                idx += 2; // on incrémente pour passer le placeholder

            } else if (charAt(affichage, idx) == '%' && charAt(affichage, idx +1) == 'D') { // si on détecte un placeholder de date
                nouvChaine += rgb(100, 100, 200, true) + dateToString(date)  + RESET; // on remplace le placeholder par la date au format String
                idx += 2; // on incrémente pour passer le placeholder

            } else {
                nouvChaine += rgb(128, 128, 128, true) + charAt(affichage, idx) + RESET; // on ajoute les caractères au String
                idx ++;
            }
        }
        return nouvChaine;
    }


    // implémentation de la fonction pour nettoyer le terminal
    void clear(){
        print("\033[H\033[2J\033[3J"); // séquence ANSI pour reset le terminal
    }



    // -----------------------------------------------------------------< ALGORITHME PRINCIPAL >------------------------------------------------------------------

    void algorithm() {
        // initialisation des variables
        String choix = ""; // on initialise le choix à une chaine vide
        Date date = newDate(1,1); // on initialise la date au 1er janvier


        // initialisation des variables du jeu via le CSV
        Entreprise entreprise = newEntreprise(stringToInt(getCell(config, 1, 0)), // budget
                                              stringToInt(getCell(config, 1, 1)), // charges
                                              stringToInt(getCell(config, 1, 2)), // nbEmployes
                                              stringToInt(getCell(config, 1, 3)), // stocks
                                              stringToInt(getCell(config, 1, 4)), // prix de vente
                                              stringToInt(getCell(config, 1, 5)), // niveau de production
                                              stringToInt(getCell(config, 1, 7)));// réputation

        int objectif = stringToInt(getCell(config, 4, 0));  // objectif financier à atteindre


        // boucle principale du jeu
        while (!finDePartie(entreprise, date, objectif) && !equals(choix, "stop")) { // tant que les conditions de fin de partie ou le mot d'arrêt ne sont pas trigger
            clear(); // Nettoyer le terminal
            println(tuiToString(date, entreprise, pathAccueil)); // on affiche l'écran d'accueil
            choix = readString();

            if (equals(choix, "1")) { // on lance une nouvelle partie 
            
                while (!equals(choix, "4")){ // on affiche le tableau de bord
                    clear();
                    println(tuiToString(date, entreprise, pathTabDeBord));
                    choix = readString();

                    if (equals(choix, "1")) {
                        while (!equals(choix, "5")){ // on ouvre le menu employés
                            clear();
                            println(tuiToString(date, entreprise, pathEmployes));
                            choix = readString();

                            if (equals(choix, "1")) {
                                recruterEmploye(entreprise);

                            }else if (equals(choix, "2")) {
                                virerEmploye(entreprise);

                            }else if (equals(choix, "3")) {
                                exploiterEmploye(entreprise);
                                
                            }else if (equals(choix, "4")) {
                                baisserSalaires(entreprise);
                                
                            }
                        }choix = "-1";

                    } else if (equals(choix, "2")) { // on ouvre le menu production      
                        while (!equals(choix, "4")) {
                            clear();
                            println(tuiToString(date, entreprise, pathProduction));
                            choix = readString();

                            if (equals(choix, "1")) {
                                acheterMachine(entreprise);

                            } else if (equals(choix, "2")) {
                                acheterContrefacon(entreprise);
                                
                            } else if (equals(choix, "3")) {
                                snifferCoke(entreprise);

                            }
                        }choix = "-1";

                    } else if (equals(choix, "3")) {
                        updateEntreprise(date, entreprise); // on met à jour les stats de l'entreprise
                        saveGame(entreprise, date); // on sauvegarde les stats du jeu (date et entreprise)

                        while (!equals(choix, "1")){ // on affiche l'écran des résultats de la semaine
                            clear();
                            println(tuiToString(date, entreprise, pathResultats));
                            choix = readString();
                        }choix = "1";
                    }
                }

            } else if (equals(choix, "2")) { // on charge la partie auvegardée
                loadGame(entreprise, date);

            } else if (equals(choix, "3")) { // on affiche les règles du jeu        
                while (!equals(choix, "1")) {
                    clear();
                    println(tuiToString(date, entreprise, pathButDuJeu));
                    choix = readString();
                }choix = "-1";

            } else {
                choix = "stop"; // on change le choix par un mot d'arrêt
            }
        }
    }
}