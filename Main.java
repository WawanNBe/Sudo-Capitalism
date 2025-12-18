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
    CSVFile employes = loadCSV("./extensions/config/employes.csv"); // fichier contenant la liste des employes

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

        employe.salarie = false;

        employe.nivSatisfaction = random(1, 10);

        return employe;
    }

    // tests de la fonction newEmploye
    void test_newEmploye() {
        Employe employe = newEmploye("Jean", "Bonbeur");

        assertEquals(employe.nom, "Bonbeur");
        assertEquals(employe.prenom, "Jean");

        assertFalse(employe.salarie);

        assertTrue(employe.nivSatisfaction >= 1 && employe.nivSatisfaction <= 10);
    }


    // implémentation de la fonction initEmployesSalaries qui initialise la liste des employés salaries au recrutement
    Employe[] initEmployes() {
        Employe[] listeEmployes = new Employe[rowCount(employes) -1]; // on crée un tableau qui contient autant d'employés que de lignes (-1 pour les titres) dans le csv employes

        for (int idx = 1; idx < rowCount(employes); idx++) {
            Employe employe; // on initialise chaque candidat
            listeEmployes[idx -1] = newEmploye(getCell(employes, idx, 0), getCell(employes, idx, 1));
        }

        return listeEmployes;
    }

    // tests de la fonction employesDispos
    void test_initEmployes() {
        Employe[] listeEmployes = initEmployes();

        assertEquals("Jean", listeEmployes[0].prenom); // on vrérifie que le premier employé correspond bien à la première ligne du csv
        assertEquals("Bonbeur", listeEmployes[0].nom);

        assertEquals("Léo", listeEmployes[9].prenom); // on vrérifie que le dernier employé correspond bien à la dernière ligne du csv
        assertEquals("Giciel", listeEmployes[9].nom);
    }


    // implémentation de la fonction compteEmployes
    int compteEmployes(Employe[] listeEmployes) {
        int nbEmployes = 0;

        for (int idx = 0; idx < length(listeEmployes); idx ++) {
            if (listeEmployes[idx].salarie) { // si l'employé à l'indice idx est salarié
                nbEmployes ++;
            }
        }
        return nbEmployes;
    }

    // tests de la fonction compteEmployes
    void test_compteEmployes() {
        Employe[] listeEmployes = initEmployes();
        listeEmployes[0].salarie = true;
        listeEmployes[5].salarie = true;

        assertEquals(2, compteEmployes(listeEmployes));
    }

    // -----------------------------------------------------------------< GESTION CLASSE ENTREPRISE >------------------------------------------------------------------

    // implémentation de la fonction newEntreprise
    Entreprise newEntreprise(Employe[] listeEmployes, int budget, int stocks, int prixDeVente) {
        Entreprise entreprise = new Entreprise();

        entreprise.listeEmployes = listeEmployes; // la liste des employes acuetls
        entreprise.nbEmployes = compteEmployes(entreprise.listeEmployes); // 1 employé par défaut
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
    String recruterEmploye(Entreprise entreprise) {
        if (entreprise.budget < 300) {
            return rgb(200, 200, 0, true) + "< Fonds insufisants ! >" + RESET;

        } else if (entreprise.nbEmployes == length(entreprise.listeEmployes)) {
            return rgb(200, 200, 0, true) + "< Plus personne n'a déposé de candidature pour être embauché ! >" + RESET;

        } else {
            

            return rgb(0, 200, 0, true) + "< Vous avez recruté " + entreprise.listeEmployes[entreprise.nbEmployes -1].prenom + " " + entreprise.listeEmployes[entreprise.nbEmployes -1].nom + " ! >" + RESET;
        }
    }

    // tests de la fonction recruterEmploye
    void test_recruterEmploye() {

    }


    // implémentation de la fonction virerEmploye
    String virerEmploye(Entreprise entreprise) {
        if (entreprise.nbEmployes <= 1){
            return rgb(200, 200, 0, true) + "< Vous ne pouvez pas virer d'employer car vous n'en avez qu'un ! >" + RESET;

        } else {
            if (entreprise.listeEmployes[entreprise.nbEmployes].sousPaye) {
 
                return rgb(200, 0, 0, true) + "< Vous avez viré " + entreprise.listeEmployes[entreprise.nbEmployes -1].prenom + " " + entreprise.listeEmployes[entreprise.nbEmployes -1].nom + " qui était sous payé ! >" + RESET;

            } else {

                return rgb(200, 0, 0, true) + "< Vous avez viré " + entreprise.listeEmployes[entreprise.nbEmployes -1].prenom + " " + entreprise.listeEmployes[entreprise.nbEmployes -1].nom + " ! >" + RESET;
            }
        }
    }

    // tests de la fonction virerEmploye
    void test_virerEmploye() {

    }


    // implémentation de la fonction sousPayerEmploye
    String sousPayerEmploye(Entreprise entreprise) {
        if (entreprise.budget < 100) {
            return rgb(200, 200, 0, true) + "< Fonds insufisants ! >" + RESET;

        } else {

            return rgb(200, 200, 0, true) + "< Vous avez recruté " + entreprise.listeEmployes[entreprise.nbEmployes -1].prenom + " " + entreprise.listeEmployes[entreprise.nbEmployes -1].nom + " sans le déclarer ! >" + RESET;
        }
    }

    // tests de la fonction sousPayerEmploye
    void test_sousPayerEmploye() {

    }


    // implémentation de la fonction baisserSalaires
    String baisserSalaires(Entreprise entreprise) {
        if (entreprise.charges <= 0){
            entreprise.charges = 0;
            return rgb(200, 200, 0, true) + "< Vous ne payez déjà pas vos employés ! >" + RESET;

        } else {
            int baisseSalaires = entreprise.charges - (100 * entreprise.nbEmployes);

            // on vérifie si la réduction ne va pas nous faire passer en négatif
            if (entreprise.charges - baisseSalaires < 0) {
                entreprise.charges = 0; // on fixe à 0 si ça dépasse
                
            } else {
                entreprise.charges = entreprise.charges - baisseSalaires; // sinon on applique la réduction
            }

            return rgb(200, 200, 0, true) + "< Vous avez baissé les salaires de vos employés ! >" + RESET;
        }
    }

    // tests de la fonction baisserSalaires
    void test_baisserSalaires() {

    }

    
    // implémentation de la fonction ameliorerProduction
    String ameliorerProduction(Entreprise entreprise) {
        if (entreprise.budget < 1000) {
            return rgb(200, 200, 0, true) + "< Fonds insufisants ! >" + RESET;

        } else if (entreprise.niveauProduction == 10) {
            return rgb(200, 200, 0, true) + "< Votre production est déjà améliorée au niveau 10, qui est le maximum ! >" + RESET;

        } else {
            entreprise.budget -= 1000;
            entreprise.niveauProduction ++;
            return rgb(0, 200, 0, true) + "< Vous avez amélioré votre production au niveau " + entreprise.niveauProduction + " >" + RESET;
        }
    }

    // tests de la fonction ameliorerProduction
    void test_ameliorerProduction() {

    }


    // implémentation de la fonction acheterContrefacon
    String acheterContrefacon(Entreprise entreprise) {
        if (entreprise.budget < 50) {
            return rgb(200, 200, 0, true) + "< Fonds insufisants ! >" + RESET;

        } else {
            entreprise.budget -= 200;
            entreprise.stocks += 50;
            return rgb(200, 200, 0, true) + "< Vous avez acheté des contrefaçons en chine ! >" + RESET;
        }
    }

    // tests de la fonction acheterContrefacon
    void test_acheterContrefacon() {

    }


    // implémentation de la fonction snifferCoke
    String snifferCoke(Entreprise entreprise) {
        if (entreprise.budget < 450) {
            return rgb(200, 200, 0, true) + "< Fonds insufisants ! >" + RESET;

        } else {


            return rgb(200, 0, 0, true) + "< Vous avez fait sniffer de la coke à vos employés ! >" + RESET;
        }
    }

    // tests de la fonction snifferCoke
    void test_snifferCoke() {

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
    void updateEntreprise(Date date, Entreprise entreprise, Marche marche) {
        int coutMatierePremiere = 2; 
        
        // simulation sur 5 jours (Lundi -> Vendredi)
        for(int i = 0; i < 5; i++) {
            
            // 1. Production (et paiement matière première)
            int productionPossible = entreprise.nbEmployes * (25 + (25 * entreprise.niveauProduction));
            int coutProductionJour = productionPossible * coutMatierePremiere;
            int productionReelle = 0;

            if (entreprise.budget >= coutProductionJour) {
                entreprise.budget -= coutProductionJour;
                productionReelle = productionPossible;
            } else {
                // si fauché, on produit ce qu'on peut
                if (entreprise.budget > 0) {
                    productionReelle = entreprise.budget / coutMatierePremiere;
                    entreprise.budget -= (productionReelle * coutMatierePremiere);
                } else {
                    productionReelle = 0;
                }
            }
            entreprise.stocks += productionReelle;
            entreprise.productionJournaliere = productionReelle; 

            // 2. Marché
            simulerMarche(marche, entreprise);

            // 3. Vente
            // on divise la demande hebdo par 5 pour avoir la demande du jour
            int demandeDuJour = entreprise.demandeActuelle / 5;
            
            int ventesDuJour = 0;
            if (entreprise.stocks >= demandeDuJour) {
                ventesDuJour = demandeDuJour;
            } else {
                ventesDuJour = entreprise.stocks; 
            }

            entreprise.stocks -= ventesDuJour;
            entreprise.budget += (ventesDuJour * entreprise.prixDeVente);
        }

        // 4. Paiement des charges hebdo
        entreprise.budget -= entreprise.charges;
        
        // 5. Passage du temps
        for (int jour = 0; jour < 7; jour++){
            gestionDate(date);
        }
    }

    // fonction pour générer le message de fin de semaine
    String genererMessageFinancier(Entreprise entreprise) {
        if (entreprise.budget < 0) {
            return rgb(200, 0, 0, true) + "< Dû à vos coûts > à vos ventes, la banque vous a mis dans le négatif ! >" + RESET;

        } else {
            return rgb(0, 200, 0, true) + "< Semaine terminée. Les comptes sont bons. >" + RESET;
        }
    }

    // tests de la fonction updateEntreprise
    void test_updateEntreprise() {

    }



    // -----------------------------------------------------------------< GESTION DES AFFICHAGES  >------------------------------------------------------------------

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
                nouvChaine += rgb(100, 100, 200, true) + listeEmployesToString(entreprise.listeEmployes) + RESET; // on remplace le placeholder par la date au format String
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


    // implémentation de la fonction listeEmployesToString qui permet d'afficher la liste des employés donnée en paramètres
    String listeEmployesToString(Employe[] listeEmployes) {
        String affichage = "";

        for (int idx = 0; idx < length(listeEmployes); idx++) {
            if (listeEmployes[idx].salarie) { // si lemployé est salarié
                affichage = affichage + " ▸ " + listeEmployes[idx -1].prenom + " " + listeEmployes[idx -1].nom + '\n';
            }
        }
        return affichage;
    }
    
    // tests de la fonction listeEmployesToString
    void test_listeEmployesToString() {

    }



    // -----------------------------------------------------------------< ALGORITHME PRINCIPAL >------------------------------------------------------------------

    void algorithm() {
        // initialisation des variables
        String choix = ""; // on initialise le choix à une chaine vide
        Date date = newDate(1,1); // on initialise la date au 1er janvier
        String notification = "";


        // initialisation des variables du jeu via les CSVs

        // initialisation des candidatures
        Employe[] listeEmployes = initEmployes(); // on initialise la liste des employes ou non
        listeEmployes[0].salarie = true; // on active le premier employé

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
                                notification = recruterEmploye(entreprise);

                            }else if (equals(choix, "2")) {
                                notification = virerEmploye(entreprise);

                            }else if (equals(choix, "3")) {
                                notification = sousPayerEmploye(entreprise);
                                
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