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



    // ------------------------------------------------< IMPLEMENTATION DES FONCTIONS SUIVIES DE LEURS TESTS RESPECTIFS >---------------------------------------

    // -----------------------------------------------------------------< GESTION DE LA DATE >------------------------------------------------------------------

    // implémentation de la fonction newDate qui permet d'initialiser une date
    Date newDate(int jour, int mois) {
        // on utilise des chiffes pour les mois car ils sont utilisés dans une fonction d'affichage
        Date date = new Date();

        date.jour = jour; // initialise le jour avec celui donné en paramètres
        date.mois = mois; // initialise le mois avec celui donné en paramètres

        return date;
    }

    // tests de la fonction newDate
    void test_newDate() {
        Date premierJanvier = newDate(1,1); // test avec l'initialisation du premier janvier

        assertEquals(1, premierJanvier.jour);
        assertEquals(1, premierJanvier.mois);
    }


    // implémentation de la fonction qui affiche la date au format jour + mois en texte
    String dateToString(Date date) {
        // on utilise un tableau de mois qui utilise l'indice le numéro de mois -1 comme indice
        String[] mois = new String[] {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre"};

        return date.jour + " " + mois[date.mois -1]; // affichage de la date au format texte
    }

    // tests de la fonction d'affichage de date
    void test_dateToString() {
        Date premierJanvier = newDate(1,1); // on initialise une date au premier janvier
        Date deuxDecembre = newDate(2,12); // on initialise une autre date au 2 décembre

        assertEquals("1 Janvier", dateToString(premierJanvier));
        assertEquals("2 Decembre", dateToString(deuxDecembre));
    }


    // implémentation de la fonction qui gère la date (incrémente le jour/mois lorsqu'elle est appelée)
    void gestionDate(Date date) {
        int[] mois = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // les nombres de jours pour chaque mois

        if (date.jour == mois[date.mois -1]) { // si on atteint le dernier jour d'un mois
            date.jour = 1; // on réinitialise le jour
            date.mois += 1; // on passe au mois suivant

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
    // NOTE: les employés ne sont pas encre employés en tant que classe pour l'instant pour le gameplay actuel

    // implémentation de la fonction newEmploye qui initialise un nouvel employé
    Employe newEmploye(String prenom, String nom) {
        Employe employe = new Employe();
        
        employe.prenom = prenom;
        employe.nom = nom;
        employe.dispo = true;

        return employe;
    }

    // tests de la fonction newEmploye
    void test_newEmploye() {
        Employe employe = newEmploye("Jean", "Bonbeur");

        assertEquals(employe.nom, "Bonbeur");
        assertEquals(employe.prenom, "Jean");
        assertTrue(employe.dispo);
    }


    // implémentation de la fonction initEmployesDispos qui initialise la liste des employés dispos au recrutement
    Employe[] initEmployesDispos() {
        Employe[] listeEmployesDispos = new Employe[rowCount(employes) -1]; // on crée un tableau qui contient autant d'employés que de lignes (-1 pour les titres) dans le csv employes

        for (int idx = 1; idx < rowCount(employes); idx++) {
            Employe nouvEmploye;
            listeEmployesDispos[idx -1] = newEmploye(getCell(employes, idx, 0), getCell(employes, idx, 1));
        }

        return listeEmployesDispos;
    }

    // tests de la fonction employesDispos
    void test_initEmployesDispos() {
        Employe[] listeEmployesDispos = initEmployesDispos();

        assertEquals("Jean", listeEmployesDispos[0].prenom); // on vrérifie que le premier employé correspond bien à la première ligne du csv
        assertEquals("Bonbeur", listeEmployesDispos[0].nom);

        assertEquals("Léo", listeEmployesDispos[9].prenom); // on vrérifie que le dernier employé correspond bien à la dernière ligne du csv
        assertEquals("Giciel", listeEmployesDispos[9].nom);
    }


    // implémentation de la fonction listeEmployesToString qui permet d'afficher la liste des employés donnée en paramètres
    String listeEmployesToString(Entreprise entreprise) {
        String affichage = "";

        for (int idx = 0; idx < entreprise.nbEmployes; idx++) {
            affichage = affichage + " ▸ " + entreprise.listeEmployes[idx].prenom + " " + entreprise.listeEmployes[idx].nom + '\n';
        }
        return affichage;
    }
    
    // tests de la fonction listeEmployesToString
    void test_listeEmployesToString() {
        Employe jean = newEmploye("Jean", "Bonbeur");
        Employe[] listeEmployes = new Employe[] {jean};
        Entreprise entreprise = newEntreprise(listeEmployes, 2000, 30, 20);

        assertEquals(" ▸ Jean Bonbeur", listeEmployesToString(entreprise));
    }

    // -----------------------------------------------------------------< GESTION CLASSE ENTREPRISE >------------------------------------------------------------------

    // implémentation de la fonction newEntreprise
    Entreprise newEntreprise(Employe[] listeEmployes, int budget, int stocks, int prixDeVente) {
        Entreprise entreprise = new Entreprise();

        entreprise.listeEmployes = listeEmployes; // la liste des employes acuetls
        entreprise.nbEmployes = 1; // 1 employé par défaut
        entreprise.budget = budget; // le budget de l'entreprise
        entreprise.charges = 300 * entreprise.nbEmployes; // 300$ par employe par défaut

        entreprise.stocks = stocks; // stocks au départ
        entreprise.prixDeVente = prixDeVente; // prix de vente initial

        entreprise.niveauProduction = 1; // par défaut l'entreprise commence au niveau 1
        entreprise.productionJournaliere = (int) ((entreprise.nbEmployes * 15) * (entreprise.niveauProduction + 0.5));
        entreprise.demandeActuelle = 150; // demande par défaut TODO: verif que c'est ok

        return entreprise;
    }

    // tests de la fonction newEntreprise
    void test_newEntreprise() {
        Employe employe = newEmploye("Jean", "Bonbeur");
        Employe[] listeEmployes = new Employe[] {employe};
        Entreprise entreprise = newEntreprise(listeEmployes, 2000, 30, 20);

        // assertArrayEquals(new Employe[] {employe}, entreprise.listeEmployes);
        assertEquals(1, entreprise.nbEmployes);
        assertEquals(2000, entreprise.budget);
        assertEquals(300, entreprise.charges);

        assertEquals(30, entreprise.stocks);
        assertEquals(20, entreprise.prixDeVente);

        assertEquals(1, entreprise.niveauProduction);
        assertEquals(22, entreprise.productionJournaliere);
        assertEquals(150, entreprise.demandeActuelle);
    }



    // -----------------------------------------------------------------< GESTION DES CHOIX DU JOUEUR >------------------------------------------------------------------

    // implémentation de la fonction recruterEmploye
    String recruterEmploye(Entreprise entreprise, Employe[] listeEmployesDispos) {
        if (entreprise.budget < 300) {
            return rgb(200, 200, 0, true) + "< Fonds insufisants ! >" + RESET;

        } else if (entreprise.nbEmployes == length(listeEmployesDispos)) {
            return rgb(200, 200, 0, true) + "< Plus personne n'a déposé de candidature pour être embauché ! >" + RESET;

        } else {
            entreprise.charges += 300;
            entreprise.listeEmployes[entreprise.nbEmployes] = listeEmployesDispos[entreprise.nbEmployes];
            entreprise.nbEmployes ++;
            return rgb(0, 200, 0, true) + "< Vous avez recruté un employé ! >" + RESET;
        }
    }

    // tests de la fonction recruterEmploye
    void test_recruterEmploye() {
        Employe employe = newEmploye("Jean", "Bonbeur");
        Employe[] listeEmployes = new Employe[] {employe};
        Entreprise entreprise = newEntreprise(listeEmployes, 2000, 30, 20);

        recruterEmploye(entreprise, listeEmployes);

        assertEquals(1, entreprise.nbEmployes);
        assertEquals(300, entreprise.charges);
    }


    // implémentation de la fonction virerEmploye
    String virerEmploye(Entreprise entreprise) {
        if (entreprise.nbEmployes <= 1){
            return rgb(200, 200, 0, true) + "< Vous ne pouvez pas virer d'employer car vous n'en avez qu'un ! >" + RESET;

        } else {
            entreprise.nbEmployes --;
            entreprise.charges -= 300;
            return rgb(200, 0, 0, true) + "< Vous avez viré un employé ! >" + RESET;
        }
    }

    // tests de la fonction virerEmploye
    void test_virerEmploye() {

        // TODO refaire ces tests

        // Employe employe = newEmploye("Jean", "Bonbeur");
        // Employe[] listeEmployes = new Employe[] {employe};

        // Entreprise entreprise = newEntreprise(listeEmployes, 2000, 30, 20);

        // virerEmploye(entreprise2);

        // assertEquals(1, entreprise2.nbEmployes);
        // assertEquals(1500, entreprise2.charges);
    }


    // implémentation de la fonction exploiterEmploye
    String exploiterEmploye(Entreprise entreprise) {
        if (entreprise.budget < 100) {
            return rgb(200, 200, 0, true) + "< Fonds insufisants ! >" + RESET;

        } else {
            entreprise.nbEmployes ++;
            entreprise.charges += 100;
            return rgb(200, 0, 0, true) + "< Vous avez commencé à exploiter un employé ! >" + RESET;
        }
    }

    // tests de la fonction exploiterEmploye
    void test_exploiterEmploye() {
        Employe employe = newEmploye("Jean", "Bonbeur");
        Employe[] listeEmployes = new Employe[] {employe};
        Entreprise entreprise = newEntreprise(listeEmployes, 2000, 30, 20);

        exploiterEmploye(entreprise);

        assertEquals(2, entreprise.nbEmployes);
        assertEquals(1600, entreprise.charges);
    }


    // implémentation de la fonction baisserSalaires
    String baisserSalaires(Entreprise entreprise) {
        if (entreprise.charges <= 0){
            entreprise.charges = 0;
            return rgb(200, 200, 0, true) + "< Vous ne payez déjà pas vos employés ! >" + RESET;

        } else {
            entreprise.charges = entreprise.charges - (100 * entreprise.nbEmployes);
            return rgb(200, 200, 0, true) + "< Vous avez baissé les salaires de vos employés ! >" + RESET;
        }
    }

    // tests de la fonction baisserSalaires
    void test_baisserSalaires() {
        Employe employe = newEmploye("Jean", "Bonbeur");
        Employe[] listeEmployes = new Employe[] {employe};
        Entreprise entreprise = newEntreprise(listeEmployes, 2000, 30, 20);

        baisserSalaires(entreprise);

        assertEquals(1400, entreprise.charges);
    }

    
    // implémentation de la fonction ameliorerProduction
    String ameliorerProduction(Entreprise entreprise) {
        if (entreprise.budget < 1000) {
            return rgb(200, 200, 0, true) + "< Fonds insufisants ! >" + RESET;

        } else {
            entreprise.budget -= 1000;
            entreprise.niveauProduction ++;
            return rgb(0, 200, 0, true) + "< Vous avez acheté une nouvelle machine ! >" + RESET;
        }
    }

    // tests de la fonction ameliorerProduction
    void test_ameliorerProduction() {
        Employe employe = newEmploye("Jean", "Bonbeur");
        Employe[] listeEmployes = new Employe[] {employe};
        Entreprise entreprise = newEntreprise(listeEmployes, 2000, 30, 20);

        ameliorerProduction(entreprise);

        assertEquals(1000, entreprise.budget);
        assertEquals(2, entreprise.niveauProduction);
    }


    // implémentation de la fonction ameliorerProduction
    String acheterContrefacon(Entreprise entreprise) {
        if (entreprise.budget < 50) {
            return rgb(200, 200, 0, true) + "< Fonds insufisants ! >" + RESET;

        } else {
            entreprise.budget -= 200;
            entreprise.stocks += 50;
            return rgb(200, 200, 0, true) + "< Vous avez acheté des contrefaçons en chine ! >" + RESET;
        }
    }

    // tests de la fonction ameliorerProduction
    void test_acheterContrefacon() {
        Employe employe = newEmploye("Jean", "Bonbeur");
        Employe[] listeEmployes = new Employe[] {employe};
        Entreprise entreprise = newEntreprise(listeEmployes, 2000, 30, 20);

        acheterContrefacon(entreprise);

        assertEquals(1800, entreprise.budget);
        assertEquals(60, entreprise.stocks);
    }


    // implémentation de la fonction ameliorerProduction
    String snifferCoke(Entreprise entreprise) {
        if (entreprise.budget < 450) {
            return rgb(200, 200, 0, true) + "< Fonds insufisants ! >" + RESET;

        } else {
            entreprise.budget -= 450;
            entreprise.niveauProduction += 1;
            return rgb(200, 0, 0, true) + "< Vous avez fait sniffer de la coke à vos employés ! >" + RESET;
        }
    }

    // tests de la fonction ameliorerProduction
    void test_snifferCoke() {
        Employe employe = newEmploye("Jean", "Bonbeur");
        Employe[] listeEmployes = new Employe[] {employe};
        Entreprise entreprise = newEntreprise(listeEmployes, 2000, 30, 20);

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
        // TODO refaire les tests
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
        {"Budget", "Charges", "NbEmployes", "Stocks", "PrixDeVente", "NiveauProduction", "ProductionJournaliere", "Jour", "Mois"}, // titres des colonnes du tableau (sert uniquement à des fins visuelles)
        
        // récupération des données de l'entreprise et de la date dans un tableau
        {intToString(entreprise.budget),
         intToString(entreprise.charges),
         intToString(entreprise.nbEmployes),
         intToString(entreprise.stocks),
         intToString(entreprise.prixDeVente),
         intToString(entreprise.niveauProduction),
         intToString(entreprise.productionJournaliere),

         intToString(date.jour),
         intToString(date.mois)}};

        saveCSV(sauvegarde, "./extensions/config/save.csv");
    }


    // implémentation de la fonction loadGame
    String loadGame(Entreprise entreprise, Date date) {
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

        String notification = rgb(0, 200, 0, true) + "< Partie chargée ! >" + RESET; // création d'un message de chargement
        return notification;
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
        Employe employe = newEmploye("Jean", "Bonbeur");
        Employe[] listeEmployes = new Employe[] {employe};
        Entreprise entreprise = newEntreprise(listeEmployes, 2000, 30, 20);

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
    String tuiToString(Date date, Entreprise entreprise, String pathTui, String notification) {
        File tui = new File(pathTui); // on récupère le chemin car le readline consomme le fichier

        String affichage = ""; // le tui sans les variables ni les couleurs
        String nouvChaine = ""; // le tui post traitement

        int idx = 0; // indice pour parcourir le TUI

        // on récupère les stats de l'entreprise dans un tableau de variables
        int[] tabVar = new int[] {entreprise.nbEmployes,            // %0
                                  entreprise.budget,                // %1
                                  entreprise.charges,               // %2
                                  entreprise.stocks,                // %3
                                  entreprise.prixDeVente,           // %4
                                  entreprise.niveauProduction,      // %5
                                  entreprise.productionJournaliere, // %6
                                  entreprise.demandeActuelle};      // %7

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
                nouvChaine += rgb(100, 100, 200, true) + dateToString(date) + RESET; // on remplace le placeholder par la date au format String
                idx += 2; // on incrémente pour passer le placeholder

            } else if (charAt(affichage, idx) == '%' && charAt(affichage, idx +1) == 'N') { // si on détecte un placeholder de notification
                nouvChaine += rgb(100, 100, 200, true) + notification + RESET; // on remplace le placeholder par la date au format String
                idx += 2; // on incrémente pour passer le placeholder

            } else if (charAt(affichage, idx) == '%' && charAt(affichage, idx +1) == 'E') { // si on détecte un placeholder de notification
                nouvChaine += rgb(100, 100, 200, true) + listeEmployesToString(entreprise) + RESET; // on remplace le placeholder par la date au format String
                idx += 2; // on incrémente pour passer le placeholder

            }else {
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
        String notification = "";


        // initialisation des variables du jeu via les CSVs

        // initialisation des employés dispos
        Employe[] listeEmployesDispos = initEmployesDispos();

        // initialisation de l'entreprise
        Employe[] listeEmployes = new Employe[length(listeEmployesDispos)]; // la taille de la liste d'meployes correspond au nombre d'employes potentiellement employables
        listeEmployes[0] = listeEmployesDispos[0]; // on y met le premier employé de l'entreprise

        Entreprise entreprise = newEntreprise(listeEmployes,
                                              stringToInt(getCell(config, 1, 0)), // budget
                                              stringToInt(getCell(config, 1, 1)), // stock
                                              stringToInt(getCell(config, 1, 2))); // prix de vente

        // initialisation de l'objectif
        int objectif = stringToInt(getCell(config, 4, 0));  // objectif financier à atteindre


        // boucle principale du jeu
        while (!finDePartie(entreprise, date, objectif) && !equals(choix, "stop")) { // tant que les conditions de fin de partie ou le mot d'arrêt ne sont pas trigger
            clear(); // Nettoyer le terminal
            println(tuiToString(date, entreprise, pathAccueil, notification)); // on affiche l'écran d'accueil
            choix = readString();

            if (equals(choix, "1")) { // on lance une nouvelle partie
            
                while (!equals(choix, "4")){ // on affiche le tableau de bord
                    clear();
                    notification = "";
                    println(tuiToString(date, entreprise, pathTabDeBord, notification));
                    choix = readString();

                    if (equals(choix, "1")) {
                        while (!equals(choix, "5")){ // on ouvre le menu employés
                            clear();
                            println(tuiToString(date, entreprise, pathEmployes, notification));
                            choix = readString();

                            if (equals(choix, "1")) {
                                notification = recruterEmploye(entreprise, listeEmployesDispos);

                            }else if (equals(choix, "2")) {
                                notification = virerEmploye(entreprise);

                            }else if (equals(choix, "3")) {
                                notification = exploiterEmploye(entreprise);
                                
                            }else if (equals(choix, "4")) {
                                notification = baisserSalaires(entreprise);
                                
                            }
                        }choix = "-1";
                        notification = "";

                    } else if (equals(choix, "2")) { // on ouvre le menu production      
                        while (!equals(choix, "4")) {
                            clear();
                            println(tuiToString(date, entreprise, pathProduction, notification));
                            choix = readString();

                            if (equals(choix, "1")) {
                                notification = ameliorerProduction(entreprise);

                            } else if (equals(choix, "2")) {
                                notification = acheterContrefacon(entreprise);
                                
                            } else if (equals(choix, "3")) {
                                notification = snifferCoke(entreprise);

                            }
                        }choix = "-1";
                        notification = "";

                    } else if (equals(choix, "3")) {
                        updateEntreprise(date, entreprise); // on met à jour les stats de l'entreprise
                        saveGame(entreprise, date); // on sauvegarde les stats du jeu (date et entreprise)

                        while (!equals(choix, "1")){ // on affiche l'écran des résultats de la semaine
                            clear();
                            println(tuiToString(date, entreprise, pathResultats, notification));
                            choix = readString();
                        }choix = "1";
                        notification = "";
                    }
                } choix = "1";

            } else if (equals(choix, "2")) { // on charge la partie auvegardée
                notification = loadGame(entreprise, date);
                choix = "1";

            } else if (equals(choix, "3")) { // on affiche les règles du jeu        
                while (!equals(choix, "1")) {
                    clear();
                    println(tuiToString(date, entreprise, pathButDuJeu, notification));
                    choix = readString();
                }choix = "-1";
                notification = "";

            } else {
                choix = "stop"; // on change le choix par un mot d'arrêt
            }
        }
    }
}