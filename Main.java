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
import extensions.CSVFile;
import extensions.File;

class Main extends Program {

    // -----------------------------------------------------------------< VARIABLES GLOBALES >------------------------------------------------------------------

    CSVFile config = loadCSV("./extensions/config/config.csv"); // fichier config du jeu
    CSVFile save = loadCSV("./extensions/config/save.csv"); // fichier sauvegarde du jeu
    CSVFile employesCSV = loadCSV("./extensions/config/employes.csv"); // fichier contenant la liste des employes

    // Utilisation des chemins afin de recharger les fichiers dans la fonction d'affichage
    String pathAccueil = "./extensions/tui/accueil.txt"; // accueil du jeu
    String pathButDuJeu = "./extensions/tui/butDuJeu.txt"; // règles du jeu
    String pathTabDeBord = "./extensions/tui/tabDeBord.txt"; // le tableau de bord
    String pathEmployes = "./extensions/tui/employes.txt"; // le menu des employés
    String pathProduction = "./extensions/tui/production.txt"; // le menu de gestion de production
    String pathResultats = "./extensions/tui/resultats.txt"; // l'écran d'affichage des résultats
    String pathMarche = "./extensions/tui/marche.txt"; // le menu de gestion du marché

    // Valeurs d'équilibrage
    final int SALAIRE_STANDARD = 300;
    final int SALAIRE_EXPLOITE = 100;
    final int COUT_RECRUTEMENT = 300;
    final int COUT_LICENCIEMENT = 500;
    final int COUT_CORRUPTION = 50;

    // ------------------------------------------------< IMPLEMENTATION DES FONCTIONS SUIVIES DE LEURS TESTS RESPECTIFS >---------------------------------------


    // -----------------------------------------------------------------< FONCTIONS OUTILS >--------------------------------------------------------------------

    // Fonction pour choisir un message aléatoire dans une liste 
    String messageAleatoire(String[] choix) {
        int index = (int)(random() * length(choix));
        
        return choix[index];
    }


    // Conversion d'un entier en String
    String intToString(int entier) {

        return "" + entier;
    }

    // Tests de la fonction intToString
    void test_intToString() {

        assertEquals("123", intToString(123));
        assertEquals("0", intToString(0));
    }



    // -----------------------------------------------------------------< GESTION DE LA DATE >------------------------------------------------------------------

    // Constructeur de date à partir d'un jour et d'un mois donné
    Date newDate(int jour, int mois) {
        // on utilise des chiffes pour les mois car ils sont utilisés dans une fonction d'affichage
        Date date = new Date();

        date.jour = jour; // initialise le jour avec celui donné en paramètres
        date.mois = mois; // initialise le mois avec celui donné en paramètres

        return date;
    }

    // Tests du constructeur
    void test_newDate() {
        Date premierJanvier = newDate(1,1); // test avec l'initialisation du premier janvier

        assertEquals(1, premierJanvier.jour);
        assertEquals(1, premierJanvier.mois);
    }

    // Conversion d'une date donnée en paramètres en String
    String dateToString(Date date) {
        // on utilise un tableau de mois qui utilise l'indice le numéro de mois -1 comme indice
        String[] mois = new String[] {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre"};

        return date.jour + " " + mois[date.mois -1]; // affichage de la date au format texte
    }

    // Tests de la fonction dateToString
    void test_dateToString() {
        Date premierJanvier = newDate(1,1); // on initialise une date au premier janvier
        Date deuxDecembre = newDate(2,12); // on initialise une autre date au 2 décembre

        assertEquals("1 Janvier", dateToString(premierJanvier));
        assertEquals("2 Decembre", dateToString(deuxDecembre));
    }

    // Fonction qui permet la gestion de la date en fonction du jour/mois
    void gestionDate(Date date) {
        int[] mois = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // les nombres de jours pour chaque mois

        if (date.jour == mois[date.mois -1]) { // si on atteint le dernier jour d'un mois
            date.jour = 1; // on réinitialise le jour
            date.mois += 1; // on passe au mois suivant

        } else {
            date.jour += 1; // sinon on incrémente simplement le jour
        }
    }

    // tests de la fonction gestionDate
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

    // Constructeur qui initialise un employé à partir d'un nom et prénom donnés en paramètres, les autres attributs sont initialisés par défaut
    Employe newEmploye(String prenom, String nom) {
        Employe employe = new Employe();
        
        employe.prenom = prenom; // On utilise les données entrées en paramètre
        employe.nom = nom;

        employe.salarie = false; // par défaut la personne n'est pas employée

        employe.nivSatisfaction = random(1, 10); // le niveau de satisfacion/compétence de la personne est aléatoire

        return employe;
    }

    // Tests du constructeur
    void test_newEmploye() {
        Employe employe = newEmploye("Jean", "Bonbeur");

        assertEquals(employe.nom, "Bonbeur");
        assertEquals(employe.prenom, "Jean");

        assertFalse(employe.salarie);

        assertTrue(employe.nivSatisfaction >= 1 && employe.nivSatisfaction <= 10);
    }

    // Fonction initEmployesSalaries qui initialise la liste des employés
    Employe[] initEmployes() {
        Employe[] listeEmployes = new Employe[rowCount(employesCSV) -1]; // on crée un tableau qui contient autant d'employés que de lignes (-1 pour les titres) dans le csv employes

        for (int idx = 1; idx < rowCount(employesCSV); idx++) {
            Employe employe; // on initialise chaque personne
            listeEmployes[idx -1] = newEmploye(getCell(employesCSV, idx, 0), getCell(employesCSV, idx, 1));
        }

        return listeEmployes;
    }

    // Tests de initEmployes
    void test_initEmployes() {
        Employe[] listeEmployes = initEmployes();

        assertEquals("Jean", listeEmployes[0].prenom); // on vrérifie que le premier employé correspond bien à la première ligne du csv
        assertEquals("Bonbeur", listeEmployes[0].nom);

        assertEquals("Harry", listeEmployes[29].prenom); // on vrérifie que le dernier employé correspond bien à la dernière ligne du csv
        assertEquals("Cover", listeEmployes[29].nom);
    }


    // Fonction qui permet de mettre à jour le nombre d'employés en fonction de la liste d'employés de l'entreprise
    int compteEmployes(Employe[] listeEmployes) {
        int nbEmployes = 0;

        for (int idx = 0; idx < length(listeEmployes); idx ++) {
            if (listeEmployes[idx].salarie) { // si l'employé à l'indice idx est salarié
                nbEmployes ++;
            }
        }
        return nbEmployes;
    }

    // Tests de la fonction compteEmployes
    void test_compteEmployes() {
        Employe[] listeEmployes = initEmployes();

        listeEmployes[0].salarie = true;
        listeEmployes[5].salarie = true;

        assertEquals(2, compteEmployes(listeEmployes));
    }



    // -----------------------------------------------------------------< GESTION CLASSE ENTREPRISE >------------------------------------------------------------------

    // Constructeur d'entreprise
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

        entreprise.scoreEthique = 0; // par défaut l'entreprise possède une éthique de 0, elle varie entre positif et négatif au cours du jeu

        return entreprise;
    }

    // Tests du constructeur
    void test_newEntreprise() {
        Employe[] listeEmployes = initEmployes(); // initialisation de la liste des employés
        listeEmployes[0].salarie = true; // on active le premier employé en tant que salarie
        Entreprise entreprise = newEntreprise(listeEmployes, 2000, 30, 20); // on crée l'entreprise

        assertArrayEquals(listeEmployes, entreprise.listeEmployes);
        assertEquals(1, compteEmployes(entreprise.listeEmployes));
        assertEquals(2000, entreprise.budget);
        assertEquals(300, entreprise.charges);

        assertEquals(30, entreprise.stocks);
        assertEquals(20, entreprise.prixDeVente);

        assertEquals(1, entreprise.niveauProduction);
        assertEquals(22, entreprise.productionJournaliere);
        assertEquals(150, entreprise.demandeActuelle);
    }

    // -----------------------------------------------------------------< GESTION CLASSE MARCHE >------------------------------------------------------------------

    // Constructeur d'un marché
    Marche newMarche(int prixDeVente, int stocks, int demande) {
        Marche marche = new Marche();

        marche.prixDeVente = prixDeVente; // prix de vente initial sur le marché
        marche.stocks = stocks; // stocks initiaux présents sur le marché
        marche.demande = demande; // demande initiale sur le marché

        return marche;
    }

    // Tests du constructeur
    void test_newMarche() {
        Marche marche = newMarche(10, 100, 50); // on initialise un marche

        assertEquals(10, marche.prixDeVente);
        assertEquals(100, marche.stocks);
        assertEquals(50, marche.demande);
    }


    // Fonction qui calcule l'impact de l'entreprise sur la demande
    int calculerImpactDemande(double diffPourcentage, int demandeDeBase) {
        int impact = 0; // impact du prix sur la demande

        if (diffPourcentage >= 0) { // si le prix est supérieur ou égal à celui du marché

            if (diffPourcentage > 75) { // si le prix de vente est supérieur à 1.75x celui du marché
                impact = -(int)(demandeDeBase * 0.98);

            } else if (diffPourcentage > 50) { // si le prix de vente est supérieur à 1.50x celui du marché
                impact = -(int)(demandeDeBase * 0.65);

            } else if (diffPourcentage > 25) { // si le prix de vente est supérieur à 1.25x celui du marché
                impact = -(int)(demandeDeBase * 0.4);

            } else if (diffPourcentage > 10) { // si le prix de vente est supérieur à 1.10x celui du marché
                impact = -(int)(demandeDeBase * 0.15);

            } else { // sinon le prix n'impacte pas la demande
                impact = 0;
            }

        } else { // si le prix est inférieur à celui du marché

            if (diffPourcentage < -75) { // si le prix est inférieur ou égal à 0.25x de celui du marché
                impact = -demandeDeBase;

            } else if (diffPourcentage < -50) { // si le prix est inférieur ou égal à 0.50x de celui du marché
                impact = (int)(demandeDeBase * 0.80);

            } else if (diffPourcentage < -25) { // si le prix est inférieur ou égal à 0.75x de celui du marché
                impact = (int)(demandeDeBase * 0.50);
            }
            else if (diffPourcentage < -10) { // si le prix est inférieur ou égal à 0.90x de celui du marché
                impact = (int)(demandeDeBase * 0.20);

            } else { // si le prix est légèrement inférieur à celui du marché
                impact = (int)(demandeDeBase * 0.05);
            }
        }
        return impact;
    }

    // Tests de la fonction du calcul se l'impact
    void test_calculerImpactDemande() {
        assertEquals(0, calculerImpactDemande(5.0, 100));
        assertEquals(-15, calculerImpactDemande(15.0, 100));
    }


    // Fonction qui gère la simulation du marché
    void simulerMarche(Marche marche, Entreprise entreprise) {
        double variationPrix = (random() * 10.0) - 5.0; // on simule la variation du prix du marché avec une variable aléatoire
        marche.prixDeVente += (int)variationPrix; // on caste la variation dans le prix de vente

        if (marche.prixDeVente < 1) { // on s'assure que le prix de vente sur le marché ne peut pas être inférieur à un
            marche.prixDeVente = 1;
        }

        int demandeDeBase = 140 + (int)(random() * 60); // on initialise une demande variable sur le marché
        double diffPourcentage = 100.0 * (entreprise.prixDeVente - marche.prixDeVente) / marche.prixDeVente; // on calcule le pourcentage de différence entre les prix de vente
        
        int impact = calculerImpactDemande(diffPourcentage, demandeDeBase); // on calcule l'impacte que cela a sur le marché
        
        entreprise.demandeActuelle = demandeDeBase + impact; // on actualise la demande de l'entreprise

        if (entreprise.demandeActuelle < 0) { // si la demande après calcul tombe en dessous de zéro la corrige à zéro
            entreprise.demandeActuelle = 0;
        }
    }



    // -----------------------------------------------------------------< CHOIX DU JOUEUR: MARCHE >------------------------------------------------------------------

    // Focntion qui gère l'augmentation des prix
    String augmenterPrix(Entreprise entreprise, int montant) {
        entreprise.prixDeVente = entreprise.prixDeVente + montant; // on incrémente le prix de vente par le montant passé en paramètres
        
        // phrases retournées aléatoirement
        String[] phrases = new String[]{"< Prix en hausse. Les clients râlent mais achètent quand même ! >",
                                        "< Inflation artificielle activée ! >",
                                        "< + " + montant + "$ sur l'étiquette. C'est du 'Positionnement Premium'. >",
                                        "< Les clients n'y verront que du feu nan ? >",
                                        "< C'est l'inflation, ça choquera personne >"};

        return rgb(0, 200, 0, true) + messageAleatoire(phrases) + RESET;
    }

    // Tests de la fonction qui augmente les prix
    void test_augmenterPrix() {
        Entreprise entreprise = newEntreprise(new Employe[0], 2000, 30, 20); // on crée une première entreprise
        Entreprise entreprise2 = newEntreprise(new Employe[0], 2000, 30, 0); // on crée une seconde entreprise

        augmenterPrix(entreprise, 5);
        augmenterPrix(entreprise2, 100);

        assertEquals(25, entreprise.prixDeVente);
        assertEquals(100, entreprise2.prixDeVente);
    }


    // Fonction qui gère la baisse des prix
    String baisserPrix(Entreprise entreprise, int montant) {

        if (entreprise.prixDeVente - montant < 1) { // si le prix après baisse est inférieur à un on le normalise à 1$
            entreprise.prixDeVente = 1;

            // phrases retournées aléatoirement
            String[] phrases = new String[]{"< Prix minimum atteint (1$) ! On ne donne pas encore les produits ! >",
                                            "< Eh c'est pas la charité non plus ici ! >",
                                            "< + " + montant + "$ ?? Sérieux ? Et la marge alors ! >",
                                            "< Euh... c'est pas ça qui va payer les vacances aux Bahamas >"};

            return rgb(200, 200, 0, true) + messageAleatoire(phrases) + RESET;
        }
        
        entreprise.prixDeVente = entreprise.prixDeVente - montant; // sinon on actualise juste le prix
        
        String[] phrases = new String[]{"< Prix cassés ! On écrase la concurrence. >",
                                        "< C'est les soldes. Émeute dans le rayon 4. >",
                                        "< Dumping commercial activé. >"};

        return rgb(200, 0, 0, true) + messageAleatoire(phrases) + RESET;
    }

    void test_baisserPrix() {
        Entreprise entreprise = newEntreprise(new Employe[0], 2000, 30, 20); // on crée une première entreprise
        Entreprise entreprise2 = newEntreprise(new Employe[0], 2000, 30, 0); // on crée une seconde entreprise

        baisserPrix(entreprise, 5);
        baisserPrix(entreprise2, 1);

        assertEquals(15, entreprise.prixDeVente);
        assertEquals(1, entreprise2.prixDeVente); // on vérifie la normalisation du prix
    }



    // -----------------------------------------------------------------< CHOIX DU JOUEUR: EMPLOYES >------------------------------------------------------------------

    // Focntion qui gère le recrutemebt d'employés
    String recruterEmploye(Entreprise entreprise, int budgetDebutSemaine) {
        int chargesFutures = entreprise.charges + SALAIRE_STANDARD; // on fait une prévision des charges pour voir si le budget le permet

        if (chargesFutures * 2 > budgetDebutSemaine) { // les charges futures ne doivent pas dépasser 50% du budget au début de la semaine
            return rgb(200, 0, 0, true) + "< Trésorerie insuffisante ! Si on embauche quelqu'un de plus, on est dans le rouge la semaine prochaine ! >" + RESET;

        } else if (entreprise.budget < COUT_RECRUTEMENT) { // on vérifie que l'on dispose des fonds nécéssaires au recrutement
            return rgb(200, 200, 0, true) + "< Fonds insuffisants pour embaucher un nouvel employé ! >" + RESET;
        }

        // si le recrutement est possible
        int idx = -1;

        for (int i = 0; i < length(entreprise.listeEmployes); i++) { // on cherche le premier non salarié dans la liste
            if (!entreprise.listeEmployes[i].salarie) {
                idx = i;
                break;
            }
        }

        if (idx != -1) { // si un non salarié est trouvé
            entreprise.listeEmployes[idx].salarie = true; // on change son statut
            entreprise.listeEmployes[idx].sousPaye = false; // l'employé est payé normalement

            entreprise.nbEmployes = compteEmployes(entreprise.listeEmployes); // on actualise le nombre d'employés
            
            entreprise.budget = entreprise.budget - COUT_RECRUTEMENT; // on prend en compte le coût du recrutement
            entreprise.charges = entreprise.charges + SALAIRE_STANDARD; // on augment les charges
            
            String[] phrases = new String[]{"< " + entreprise.listeEmployes[idx].prenom + " recruté. Il a l'air motivé (le pauvre). >",
                                            "< Nouvelle recrue : " + entreprise.listeEmployes[idx].prenom + ". Espérance de vie dans la boîte : 3 mois. >",
                                            "< " + entreprise.listeEmployes[idx].prenom + " a signé. Il n'a pas lu les petites lignes. >"};

            return rgb(0, 200, 0, true) + messageAleatoire(phrases) + RESET;

        } else { // si il n'y a plus de candidats disponibles
            return rgb(200, 200, 0, true) + "< Le marché du travail est vide ! >" + RESET;
        }
    }

    // Tests de la fonction de recrutement
    void test_recruterEmploye() {
        Employe[] listeEmployes = initEmployes(); // on initialise une liste d'employés
        listeEmployes[0].salarie = true; // on active un employé
        Entreprise entreprise = newEntreprise(listeEmployes, 2000, 30, 20); // on initialise une entreprise

        recruterEmploye(entreprise, 2000);

        assertEquals(2, entreprise.nbEmployes);
        assertEquals(600, entreprise.charges);
        assertEquals(1700, entreprise.budget);
    }

    // Fonction qui gère le licenciement
    String virerEmploye(Entreprise entreprise) {

        if (entreprise.nbEmployes <= 1) { // si il ne reste qu'un seul employé
            return rgb(200, 200, 0, true) + "< Vous ne pouvez pas virer le dernier employé ! >" + RESET;

        } else if (entreprise.budget < COUT_LICENCIEMENT) { // si l'entreprise ne dispose pas d'assez de fonds pour payer le licenciement
            return rgb(200, 200, 0, true) + "< Pas assez d'argent pour payer les indemnités (" + COUT_LICENCIEMENT + "$) ! >" + RESET;
        }

        // si le licenciement est possible
        int idx = -1;

        entreprise.scoreEthique -= 1; // on baisse le score d'éthique

        for(int i = length(entreprise.listeEmployes) - 1; i >= 0; i--) { // on cherche le dernier employé embauché
            if (entreprise.listeEmployes[i].salarie) {
                idx = i;
                break;
            }
        }

        if (idx != -1) { // si un employé salarié est trouvé
            boolean etaitSousPaye = entreprise.listeEmployes[idx].sousPaye; // on récupère son statut de paie

            entreprise.listeEmployes[idx].salarie = false; // on change son statut
            entreprise.listeEmployes[idx].sousPaye = false; // on réinitialise le statut de sa paie

            entreprise.nbEmployes = compteEmployes(entreprise.listeEmployes);
            
            entreprise.budget = entreprise.budget - COUT_LICENCIEMENT; // on soustrait les charges du licenciement au budget
            
            if (etaitSousPaye) { // si l'employé était sous payé
                entreprise.charges = entreprise.charges - SALAIRE_EXPLOITE;

            } else { // si il était payé normalement
                entreprise.charges = entreprise.charges - SALAIRE_STANDARD;
            }
            
            if (entreprise.charges < 0) { // si les charges tombent en dessous de zéro on normalise à 0
                entreprise.charges = 0;
            }

            // phrases aléatoires
            String[] phrases = new String[]{"< " + entreprise.listeEmployes[idx].prenom + " viré. La sécurité l'a raccompagné. >",
                                            "< Hop, " + entreprise.listeEmployes[idx].prenom + " à la trappe. Dommage. >",
                                            "< " + entreprise.listeEmployes[idx].prenom + " nous quitte. On a gardé son stylo. >",
                                            "< " + entreprise.listeEmployes[idx].prenom + " n'a pas tenu le rythme'. >"};

            return rgb(200, 0, 0, true) + messageAleatoire(phrases) + RESET;
        }
        return "Erreur";
    }

    // Tests de la fonction de licenciement
    void test_virerEmploye() {
        Employe[] listeEmployes = initEmployes(); // on initialise une liste d'employés
        listeEmployes[0].salarie = true; // on active un premier employé
        listeEmployes[1].salarie = true; // on active un deuxième employé
        Entreprise entreprise = newEntreprise(listeEmployes, 2000, 30, 20); // on initialise une entreprise

        virerEmploye(entreprise);

        assertEquals(1, entreprise.nbEmployes);
        assertEquals(300, entreprise.charges);
        assertEquals(1500, entreprise.budget);
    }


    // Fonction qui gère l'emploi d'un employé en le sous payant
    String exploiterEmploye(Entreprise entreprise, int budgetDebutSemaine) {
        int chargesFutures = entreprise.charges + SALAIRE_EXPLOITE; // on simule si l'entreprise possède les fonds nécessaires

        if (chargesFutures * 2 > budgetDebutSemaine) { // si l'entreprise ne possède pas assez
             return rgb(200, 100, 0, true) + "< Trésorerie trop fragile pour recruter ! >" + RESET;

        } else if (entreprise.budget < COUT_CORRUPTION) { // si l'entreprise ne possède pas assez de fonds pour corrompre les RH
            return rgb(200, 200, 0, true) + "< Fonds insuffisants (" + COUT_CORRUPTION + "$) pour corrompre les RH ! >" + RESET;
        } 
        
        // si on peut sous payer un employé
        int idx = -1;

        entreprise.scoreEthique -= 1; // on baisse le score d'éthique

        for(int i = 0; i < length(entreprise.listeEmployes); i++) { // on cherche le premier employé non salarié

            if (!entreprise.listeEmployes[i].salarie) {
                idx = i;
                break;
            }
        }

        if (idx != -1) { // si on trouve un candidat
            entreprise.listeEmployes[idx].salarie = true; // il devient salarié
            entreprise.listeEmployes[idx].sousPaye = true; // il devient aussi sous payé
            
            entreprise.nbEmployes = compteEmployes(entreprise.listeEmployes); // on actualise le nombre d'employés
            
            entreprise.budget = entreprise.budget - COUT_CORRUPTION; // on met à jour le budget
            entreprise.charges = entreprise.charges + SALAIRE_EXPLOITE; // on met à jour les charges
            
            // phrases retournées
            String[] phrases = new String[]{"< " + entreprise.listeEmployes[idx].prenom + " embauché au black. Chut... >",
                                            "< Main d'oeuvre pas chère trouvée (" + entreprise.listeEmployes[idx].prenom + "). >",
                                            "< Vous avez graiddé la patte des RH.. >"};

            return rgb(200, 0, 0, true) + messageAleatoire(phrases) + RESET;

        } else {
            return "< Personne n'est disponible sur le marché du travail >";
        }
    }

    // Tests de la fonction qui gère l'exploitation d'un employé
    void test_exploiterEmploye() {
        Employe[] listeEmployes = initEmployes(); // on initialise une liste d'employés
        listeEmployes[0].salarie = true; // on active un employé
        Entreprise entreprise = newEntreprise(listeEmployes, 2000, 30, 20); // on initialise une entreprise

        exploiterEmploye(entreprise, 2000);

        assertEquals(2, entreprise.nbEmployes);
        assertEquals(400, entreprise.charges);
        assertEquals(1950, entreprise.budget);
    }

    // Fonction qui gère la baisse des salaires
    String baisserSalaires(Entreprise entreprise) {
        int reduction = 50 * entreprise.nbEmployes; // on baisse les salaires de tous les employés de 50$

        if (entreprise.charges <= 50 * entreprise.nbEmployes) { // si on a déjà atteint le salaire minimal par employé
            return rgb(200, 200, 0, true) + "< Salaire minimum déjà atteint ! >" + RESET;

        } else { // sinon on applique la baisse de salaires
            entreprise.charges = entreprise.charges - reduction; // on met à jour les charges
            entreprise.scoreEthique -= 1; // on baisse le score d'éthique

            if (entreprise.charges < 0) { // si les charges passent dans le négatif on les normalise à 0
                entreprise.charges = 0;
            }
            
            // phrases aléatoires
            String[] phrases = new String[]{"< Salaires baissés. Le moral chute, la marge augmente. >",
                                            "< Vous avez rogné sur la paye. Tant pis, ls mangeront des pâtes. >",
                                            "< Économies réalisées sur le dos des employés, les actionnaires vous félicitent ! >"};

            return rgb(200, 200, 0, true) + messageAleatoire(phrases) + RESET;
        }
    }

    // Tests de la fonction qui baisse les salaires
    void test_baisserSalaires() {
        Employe[] listeEmployes = initEmployes(); // on initialise une liste d'employés
        listeEmployes[0].salarie = true; // on active un employé
        Entreprise entreprise = newEntreprise(listeEmployes, 2000, 30, 20); // on initialise une entreprise

        baisserSalaires(entreprise);

        assertEquals(1, entreprise.nbEmployes);
        assertEquals(250, entreprise.charges);
    }



    // -----------------------------------------------------------------< CHOIX DU JOUEUR: PRODUCTION >------------------------------------------------------------------

    // Fonction qui gère l'amélioration de la production
    String ameliorerProduction(Entreprise entreprise) {
    
        if (entreprise.budget < 1000) { // si l'entreprise ne possède pas les fonds nécessaires
            return rgb(200, 200, 0, true) + "< Pas assez d'argent (1000$) ! >" + RESET;

        } else if (entreprise.niveauProduction >= 10) { // si l'entreprise a déjà atteint le niveau max
            return rgb(200, 200, 0, true) + "< Nous avons atteint le niveau de production maximum ! >" + RESET;
        }

        entreprise.budget = entreprise.budget - 1000; // on met à jour le budget de l'entreprise
        entreprise.niveauProduction = entreprise.niveauProduction + 1; // on incrémente le niveau de production
        
        // phrases aléatoires
        String[] phrases = new String[]{"< Nouvelle machine installée. Elle remplace 10 humains. >",
                                        "< Investissement technologique. Skynet approche. >",
                                        "< La production va s'accélérer (et le bruit aussi). >"};

        return rgb(0, 200, 0, true) + messageAleatoire(phrases) + RESET;
    }

    // Tests de la fonction qui gère l'amélioration de la production
    void test_ameliorerProduction() {
        Employe[] listeEmployes = initEmployes(); // on initialise une liste d'employés
        listeEmployes[0].salarie = true; // on active un employé
        Entreprise entreprise = newEntreprise(listeEmployes, 2000, 30, 20); // on initialise une entreprise

        ameliorerProduction(entreprise);

        assertEquals(1000, entreprise.budget);
        assertEquals(2, entreprise.niveauProduction);
    }

    // Fonction qui gère l'achat de contrefaçons
    String acheterContrefacon(Entreprise entreprise) {

        if (entreprise.budget < 200) { // si l'entreprise ne dispose pas des fonds nécessaires
            return rgb(200, 200, 0, true) + "< Vous avez besoin d'au moins 200$ ! >" + RESET;
        }

        entreprise.budget = entreprise.budget - 200; // on met à jour le budget
        entreprise.stocks = entreprise.stocks + 50; // on ajoute les contrefaçons aux stocks
        
        String[] phrases = new String[]{"< Stock de 'Adibas' reçu. Personne ne verra la différence. >",
                                        "< Qualité minimale. Marge maximale. >",
                                        "< C'est du plastique, mais on vendra ça comme du cuir. >"};

        return rgb(200, 200, 0, true) + messageAleatoire(phrases) + RESET;
    }

    // Tests de la fonction d'achat de contrefaçons
    void test_acheterContrefacon() {
        Employe[] listeEmployes = initEmployes(); // on initialise une liste d'employés
        listeEmployes[0].salarie = true; // on active un employé
        Entreprise entreprise = newEntreprise(listeEmployes, 2000, 30, 20); // on initialise une entreprise

        acheterContrefacon(entreprise);

        assertEquals(1800, entreprise.budget);
        assertEquals(80, entreprise.stocks);
    }


    // Fonction qui gère la prise de coke
    String snifferCoke(Entreprise entreprise) {

        if (entreprise.budget < 450) {
            return rgb(200, 200, 0, true) + "< Désolé, votre dealer n'accepte pas le crédit... >" + RESET;
        }

        entreprise.budget = entreprise.budget - 450; // on met à jour le budget de l'entreprise
        entreprise.niveauProduction = entreprise.niveauProduction + 1; // on augmente le niveau de production
        entreprise.scoreEthique -= 1; // on baisse le score d'éthique
        
        // phrases aléatoires
        String[] phrases = new String[]{"< Productivité boostée ! Les employés voient les sons et entendent les couleurs ! >",
                                        "< Ambiance 'Loup de Wall Street' dans l'open space ! >",
                                        "< La poudre blanche coule à flot ! >",
                                        "< Les employés battent tous les records ! >"};

        return rgb(200, 0, 0, true) + messageAleatoire(phrases) + RESET;
    }

    // Tests de la fonction de prise de coke
    void test_snifferCoke() {
        Employe[] listeEmployes = initEmployes(); // on initialise une liste d'employés
        listeEmployes[0].salarie = true; // on active un employé
        Entreprise entreprise = newEntreprise(listeEmployes, 2000, 30, 20); // on initialise une entreprise

        snifferCoke(entreprise);

        assertEquals(1550, entreprise.budget);
        assertEquals(2, entreprise.niveauProduction);
    }



    // -----------------------------------------------------------------< GESTION DU JEU >------------------------------------------------------------------

    // Fonction qui gère la fin de partie
    boolean finDePartie(Entreprise entreprise, Date date, int objectif) {

        // si le budget de l'entreprise passe dans le négatif ou que la fin de l'année est atteinte ou le budget dépasse l'objectif
        if ((entreprise.budget <= 0) || (date.jour == 31 && date.mois == 12) || (entreprise.budget >= objectif)) {
            return true;

        } else { // sinon le jeu continue
            return false;
        }
    }

    // Tests de la fonction de fin de partie
    void test_finDePartie() {
        int objectif = 1000000; // on initialise un objectif

        Date dateLambda = newDate(1,10); // on initialise une date lambda
        Date datePerd = newDate(31,12); // on initialise une date qui termine le jeu

        Entreprise entrepriseGagne = newEntreprise(new Employe[0], 1000000, 0, 0); // on initialise une entreprise qui a atteint l'objectif
        Entreprise entrepriseLambda = newEntreprise(new Employe[0], 1000, 0, 0); // on initialise une entreprise lambda
        Entreprise entreprisePerd = newEntreprise(new Employe[0], 0, 0, 0); // on initialise une entreprise lambda

        assertTrue(finDePartie(entrepriseGagne, dateLambda, objectif));
        assertTrue(finDePartie(entrepriseLambda, datePerd, objectif));
        assertTrue(finDePartie(entreprisePerd, dateLambda, objectif));
    }

    // Fonction qui gère la sauvegarde des donnés du jeu
    void saveGame(Entreprise entreprise, Date date, Marche marche) {

        String[][] sauvegarde = {{"nbEmployes", "budget", "charges", "stocks", "prixDeVente", "niveauProduction", "productionJournalière", "demandeActuelle", "jour", "mois"},
                                {intToString(entreprise.nbEmployes), // budget de l'entreprise
                                 intToString(entreprise.budget), // charges
                                 intToString(entreprise.charges), // nombre d'employés
                                 intToString(entreprise.stocks), // stocks
                                 intToString(entreprise.prixDeVente), // prix de vente
                                 intToString(entreprise.niveauProduction), // niveau de production
                                 intToString(entreprise.productionJournaliere), // production journalière
                                 intToString(entreprise.demandeActuelle), // demande actuelle

                                 intToString(date.jour), // jour
                                 intToString(date.mois)}}; // mois

        saveCSV(sauvegarde, "./extensions/config/save.csv"); // on sauvegarde dans le CSV
    }

    // Fonction qui gère le chargement des donnés du jeu
    String loadGame(Entreprise entreprise, Date date) {

        if (rowCount(save) < 2) { // si il n'y a pas de sauvegarde
            return rgb(200, 0, 0, true) + "< Sauvegarde vide >" + RESET;
        }

        // on charge la date
        date.jour = stringToInt(getCell(save, 1, 8)); // jour
        date.mois = stringToInt(getCell(save, 1, 9)); // mois

        // on charge les données de l'entreprise
        entreprise.nbEmployes = stringToInt(getCell(save, 1, 0)); // nombre d'employés
        entreprise.budget = stringToInt(getCell(save, 1, 1)); // budget
        entreprise.charges = stringToInt(getCell(save, 1, 2)); // charges
        entreprise.stocks = stringToInt(getCell(save, 1, 3)); // stocks
        entreprise.prixDeVente = stringToInt(getCell(save, 1, 4)); // prix de vente
        entreprise.niveauProduction = stringToInt(getCell(save, 1, 5)); // niveau de production
        entreprise.productionJournaliere = stringToInt(getCell(save, 1, 5)); // production journalière
        entreprise.demandeActuelle = stringToInt(getCell(save, 1, 5)); // demande actuelle

        entreprise.listeEmployes = initEmployes(); // on restaure la liste des employés
        for (int idx = 0; idx < entreprise.nbEmployes; idx ++) {
            entreprise.listeEmployes[idx].salarie = true;
        }
        
        return rgb(0, 200, 0, true) + "< Partie chargée ! >" + RESET;
    }


    // Fonction qui met à jour l'entreprise pour simuler la semaine
    void updateEntreprise(Date date, Entreprise entreprise, Marche marche) {
        int coutMatierePremiere = 2; // on initialise le coût des matières premières
        
        for(int i = 0; i < 5; i++) {
            entreprise.productionJournaliere = (int) ((entreprise.nbEmployes * 15) * (entreprise.niveauProduction + 0.5)); // on met à jour la production
            
            int productionPossible = entreprise.productionJournaliere; // on initialise la production possible
            int coutProductionJour = productionPossible * coutMatierePremiere; // on crée le coût de la production
            int productionReelle = 0; // on initialise la production réelle

            if (entreprise.budget >= coutProductionJour) { // si le budget permet la production
                entreprise.budget = entreprise.budget - coutProductionJour; // on soustrait le coût de production au budget
                productionReelle = productionPossible; // la production réelle est la production possible

            } else if (entreprise.budget > 0) { // si le budget est supérieur à 0 mais insuffisant
                productionReelle = entreprise.budget / coutMatierePremiere; // on ne produit que ce que l'on peut se payer
                entreprise.budget = entreprise.budget - (productionReelle * coutMatierePremiere); // on met à jour le budget de l'entreprise
            }
            entreprise.stocks = entreprise.stocks + productionReelle; // on ajoute la production aux stocks
            
            simulerMarche(marche, entreprise); // on simule le marché

            int demandeDuJour = entreprise.demandeActuelle / 5; // on met la demande de l'entreprise sur 5 jours

            if (entreprise.demandeActuelle > 0 && demandeDuJour == 0) { // si les demandes sont à zéro, on passe la demande de l'entreprise à 1
                demandeDuJour = 1;
            }
            
            int ventesDuJour = 0; // on initialise le nombre de ventes du jour

            if (entreprise.stocks < demandeDuJour) { // si on ne dispose pas d'assez de stocks
                ventesDuJour = entreprise.stocks; // on vend tout

            } else { // sinon on vend la demande
                ventesDuJour = demandeDuJour;
            }

            entreprise.stocks = entreprise.stocks - ventesDuJour; // on met à jour les stocks
            entreprise.budget = entreprise.budget + (ventesDuJour * entreprise.prixDeVente); // on met à jour le budget
        }

        entreprise.budget = entreprise.budget - entreprise.charges; // on paye les charges des employés

        // on cyle la date pour passer 7 jours et arriver à la semaine suivante
        for (int jour = 0; jour < 7; jour++) {
            gestionDate(date);
        }
    }

    // Tests de la fonction de mise à jour
    void test_updateEntreprise() {
        Entreprise entreprise = newEntreprise(new Employe[0], 2000, 30, 20);
        Marche marche = newMarche(15, 100, 50);
        Date date = newDate(1, 1);

        updateEntreprise(date, entreprise, marche);

        assertTrue(entreprise.budget != 2000);
        assertTrue(entreprise.stocks != 30);

        assertTrue(date.jour == 8);

        assertTrue(marche.prixDeVente != 15);
    }


    // Fonction qui genere un message à afficher sur l'écran des résultats en fonction des performances de l'entreprise
    String genererMessageFinancier(Entreprise entreprise) {
        if (entreprise.budget < 0) {
            return rgb(200, 0, 0, true) + "< La banque vous a mis dans le rouge. Ils menacent de saisir la machine à café ! >" + RESET;

        } else if (entreprise.budget > 7500  && entreprise.budget < 15000) {
            return rgb(0, 200, 0, true) + "< Semaine largement rentable. Votre banquier vous envoie des bisous. >" + RESET;

        } else if (entreprise.budget > 15000 ) {
            return rgb(0, 200, 0, true) + "< L'argent coule à flots. Les actionnaires demandent du champagne ! >" + RESET;

        } else {
            return rgb(0, 200, 0, true) + "< Semaine terminée. Les comptes sont stables. >" + RESET;
        }
    }



    // -----------------------------------------------------------------< GESTION DES AFFICHAGES >------------------------------------------------------------------

    // Fonction qui transforme les affichages txt en String et qui y ajoute des variables
    String tuiToString(Date date, Entreprise entreprise, Marche marche, String pathTui, String notification, int objectif) {
        File tui = new File(pathTui); // on récupère le chemin du fichier
        String affichage = ""; // on crée la variable qui nous permet de convertir en String
        String resultat = ""; // on initialise le résultat renvoyé après balayage et ajout des variables
        int idx = 0; // l'indice de parcours du TUI
        
        // on récupère les données du marché et de l'entrerise dans un tableau de variables
        int[] tabVar = new int[] {entreprise.budget,                    // %0
                                  entreprise.charges,                   // %1
                                  entreprise.nbEmployes,                // %2
                                  entreprise.stocks,                    // %3
                                  entreprise.prixDeVente,               // %4
                                  entreprise.niveauProduction,          // %5
                                  entreprise.productionJournaliere,     // %6
                                  marche.prixDeVente,                   // %7
                                  entreprise.demandeActuelle};          // %8

        // on parcours une première fois pour récupérer les placeholders
        while (ready(tui)) affichage = affichage + readLine(tui) + '\n';

        // on parcours une dexième fois le String créé en remplaçant les placeholders par les variables en utilisant l'indice du tableau
        while (idx < length(affichage)) { 

            if (charAt(affichage, idx) == '%' && (charAt(affichage, idx +1) >= '0' && charAt(affichage, idx +1) <= '9')) { // si le placeholder concerne l'entreprise ou le marche

                int varIndex = (int)(charAt(affichage, idx +1) - '0'); // on initialise un indice pour le tableau

                resultat = resultat + rgb(100, 100, 200, true) + tabVar[varIndex] + RESET; // on ajoute la variable correspondante au String avec de la couleur
                idx += 2; // on incrémente de 2 pour skip le placeholder

            } else if (charAt(affichage, idx) == '%' && charAt(affichage, idx +1) == 'D') {  // si le placeholder concerne la date
                resultat = resultat + rgb(100, 100, 200, true) + dateToString(date) + RESET; // on ajoute la date au format String avec de la couleur
                idx += 2; // on incrémente de 2 pour skip le placeholder

            } else if (charAt(affichage, idx) == '%' && charAt(affichage, idx +1) == 'N') { // si le placeholder concerne une notification
                resultat = resultat + rgb(100, 100, 200, true) + notification + RESET; // on ajoute la notification avec de la couleur
                idx += 2; // on incrémente de 2 pour skip le placeholder

            } else if (charAt(affichage, idx) == '%' && charAt(affichage, idx +1) == 'E') { // si le placeholder concerne la liste des employés
                resultat = resultat + rgb(100, 100, 200, true) + listeEmployesToString(entreprise.listeEmployes) + RESET; // on ajoute la liste au format String en couleur
                idx += 2; // on incrémente de 2 pour skip le placeholder

            } else if (charAt(affichage, idx) == '%' && charAt(affichage, idx +1) == 'O') { // si le placeholder concerne la liste des employés
                resultat = resultat + rgb(100, 100, 200, true) + objectif + RESET; // on ajoute la liste au format String en couleur
                idx += 2; // on incrémente de 2 pour skip le placeholder

            } else {
                resultat = resultat + rgb(128, 128, 128, true) + charAt(affichage, idx) + RESET; // sinon on ajoute les caractères à la suite
                idx ++; // on incrémente de 1
            }
        }
        return resultat;
    }


    // Fonction qui permet de convertir la liste des employés en String
    String listeEmployesToString(Employe[] listeEmployes) {
        String affichage = ""; // on initialise un affichage en String

        // on parcours la liste des employés
        for (int idx = 0; idx < length(listeEmployes); idx++) {
            if (listeEmployes[idx].salarie) { // si lemployé est salarié
                affichage = affichage + " ▸ " + listeEmployes[idx].prenom + " " + listeEmployes[idx].nom + '\n'; // on l'ajoute à la liste avec un formatage
            }
        }
        return affichage;
    }


    // Fonction qui permet de clear le terminal et son historique
    void clear() {
        print("\033[H\033[2J\033[3J");
    }



    // -----------------------------------------------------------------< ALGORITHME PRINCIPAL >------------------------------------------------------------------

    void algorithm() {
        String choix = ""; // on initialise la variable qui va contenir les choix du joueur en String pour gérer le contrôle de saisie
        String notification = ""; // on initialise la variable qui contiendra les notifications à afficher sur les écrans de jeu
        
        // initialisation des 3 classes
        Date date = newDate(1,1); // on initialise la date au premier janvier

        Employe[] listeEmployes = initEmployes(); // on initialise la liste des employés
        listeEmployes[0].salarie = true; // on ajoute le premier employé

        // on initialise l'entreprise depuis le csv de config
        Entreprise entreprise = newEntreprise(listeEmployes,
                                              stringToInt(getCell(config, 1, 0)),
                                              stringToInt(getCell(config, 1, 1)),
                                              stringToInt(getCell(config, 1, 2)));
        
        // on initialise le marche depuis le csv de config
        Marche marche = newMarche(stringToInt(getCell(config, 4, 0)),
                                  stringToInt(getCell(config, 4, 1)),
                                  stringToInt(getCell(config, 4, 2)));

        // on initialise l'objectif financier à atteindre depuis le csv de config
        int objectif = stringToInt(getCell(config, 7, 0));

        int budgetDebutSemaine = entreprise.budget; // on initialise le budget de début de semaine sur le budget de l'entreprise pour la première semaine



        // -----------< Boucle de jeu >-----------

        while (!finDePartie(entreprise, date, objectif) && !equals(choix, "stop")) { // tant que la fon de partie n'est pas activée ou que l'on ne saisit pas le mot stop (sert uniquement pour la possibilité de quitter le jeu)
            clear(); // on nettoie le terminal
            println(tuiToString(date, entreprise, marche, pathAccueil, notification, objectif)); // on affiche l'accueil du jeu
            choix = readString(); // on propose au joueur de choisir entre jouer, importer une sauvegarde, lire les règles du jeu ou quitter
            
            if (equals(choix, "1")) { // si le joueur choisit de lancer la partie 

                while (!equals(choix, "5")){ // tant que le joueur ne choisit pas de quitter
                    clear();
                    println(tuiToString(date, entreprise, marche, pathTabDeBord, notification, objectif)); // on affiche le tableau de bord
                    choix = readString(); // on propose au joueur de choisir entre gérer les employés, gérer la production, gérer le marché et valider la semaine

                    if (equals(choix, "1")) { // si le joueur choisit de gérer les employés

                        while (!equals(choix, "5")){ // tant que le joueur ne choisit pas de revenir au menu précédent
                            clear();
                            println(tuiToString(date, entreprise, marche, pathEmployes, notification, objectif)); // on affiche l'écran de gestion des employés
                            choix = readString(); // on propose au joueur de choisir entre recruter, virer, sous payer, baisser les salaires et revenir au menu précédent

                            if (equals(choix, "1")) {
                                notification = recruterEmploye(entreprise, budgetDebutSemaine); // on recrute un employé

                            } else if (equals(choix, "2")) {
                                notification = virerEmploye(entreprise); // on vire un employé

                            } else if (equals(choix, "3")) {
                                notification = exploiterEmploye(entreprise, budgetDebutSemaine); // on sous paye un nouvel employé

                            } else if (equals(choix, "4")) {
                                notification = baisserSalaires(entreprise); // on baisse les salaires
                            }
                        } choix = "-1"; notification = ""; // on revient en arrière
                          notification = ""; // on clear les notifications


                    } else if (equals(choix, "2")) { // si le joueur choisit de gérer la production

                        while (!equals(choix, "4")) { // tant que le joueur ne choisit pas de revenir au menu précédent
                            clear();
                            println(tuiToString(date, entreprise, marche, pathProduction, notification, objectif)); // on affiche l'écran de production
                            choix = readString(); // on propose au joueur de choisir entre améliorer la prod, acheter des contrefaçons, passer les employés sous coke ou revenir en arrière

                            if (equals(choix, "1")) {
                                notification = ameliorerProduction(entreprise); // on améliore la prod

                            } else if (equals(choix, "2")) {
                                notification = acheterContrefacon(entreprise); // on achete des contrefaçons

                            } else if (equals(choix, "3")) {
                                notification = snifferCoke(entreprise); // on fait sniffer de la coke aux employés
                            }
                        } choix = "-1"; // on revient en arrière
                          notification = ""; // on clear les notifications


                    } else if (equals(choix, "3")) {  // si le joueur choisit de gérer le marche

                        while (!equals(choix, "5")) { // tant que le joueur ne choisit pas de revenir au menu précédent
                            clear();
                            println(tuiToString(date, entreprise, marche, pathMarche, notification, objectif)); // on affiche l'écran de gestion du marché
                            choix = readString(); // on propose au joueur de monter les prix de 1$, 5$, baisser de 1$, 5$
                            
                            if (equals(choix, "1")) {
                                notification = augmenterPrix(entreprise, 1); // on augmente les prix de 1$

                            }else if (equals(choix, "2")) {
                                notification = augmenterPrix(entreprise, 5); // on augmente les prix de 5$

                            }else if (equals(choix, "3")) {
                                notification = baisserPrix(entreprise, 1); // on augmente les prix de 1$

                            }else if (equals(choix, "4")) {
                                notification = baisserPrix(entreprise, 5); // on augmente les prix de 5$

                            }
                         } choix = "-1"; // on revient en arrière
                           notification = ""; // on clear les notifications


                    } else if (equals(choix, "4")) { // si le joueur choisit de valider la semaine

                        updateEntreprise(date, entreprise, marche); // on met à jour les stats de l'entreprise
                        budgetDebutSemaine = entreprise.budget; // on met à jour le budget de la semaine

                        notification = genererMessageFinancier(entreprise); // on affiche un message sur les performances de l'entreprise

                        saveGame(entreprise, date, marche); // on sauvegarde la partie

                        finDePartie(entreprise, date, objectif); // on vérifie si le jeu est gagné/perdu

                        while (!equals(choix, "1")){  // tant que le joueur ne choisit pas de passer à la semaine suivante
                            clear();
                            println(tuiToString(date, entreprise, marche, pathResultats, notification, objectif)); // on affiche l'écran des résultats
                            choix = readString();

                        } choix = "1";
                          notification = "";
                    }
                } choix = "1";
                
            } else if (equals(choix, "2")) { // si le joueur a choisi de charger sa partie

                notification = loadGame(entreprise, date); // on affiche un message et on charge la partie
                budgetDebutSemaine = entreprise.budget; // on met à jour le budget hebdomadaire de l'entreprise
                choix = "1";

            } else if (equals(choix, "3")) { // si le joueur affiche les règles du jeu

                while (!equals(choix, "1")) { // tant que le joueur ne revient pas en arrière
                    clear();
                    println(tuiToString(date, entreprise, marche, pathButDuJeu, notification, objectif)); // on affiche les règles du jeu
                    choix = readString();

                } choix = "-1";
                  notification = "";

            } else { // si le joueur choisit de quitter le jeu
                choix = "stop"; // on met le choix à stop pour arrêter la boucle
            }
        }
    }
}