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
    CSVFile marcheCSV = loadCSV("./extensions/config/marche.csv"); // fichier de config du marche

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

        return entreprise;
    }

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

    // -----------------------------------------------------------------< GESTION DU MARCHE >------------------------------------------------------------------

    Marche newMarche(int prixDeVente, int stocks, int demande) {
        Marche m = new Marche();
        m.prixDeVente = prixDeVente;
        m.stocks = stocks;
        m.demande = demande;
        return m;
    }

    void test_newMarche() {
        Marche m = newMarche(10, 100, 50);
        assertEquals(10, m.prixDeVente);
    }

    int calculerImpactDemande(double diffPourcentage, int demandeDeBase) {
        int impact = 0;
        if (diffPourcentage >= 0) { 
            if (diffPourcentage > 75) impact = -(int)(demandeDeBase * 0.98);
            else if (diffPourcentage > 50) impact = -(int)(demandeDeBase * 0.65);
            else if (diffPourcentage > 25) impact = -(int)(demandeDeBase * 0.4);
            else if (diffPourcentage > 10) impact = -(int)(demandeDeBase * 0.15);
            else impact = 0;
        } else { 
            if (diffPourcentage < -75) impact = -demandeDeBase; 
            else if (diffPourcentage < -50) impact = (int)(demandeDeBase * 0.80);
            else if (diffPourcentage < -25) impact = (int)(demandeDeBase * 0.50);
            else if (diffPourcentage < -10) impact = (int)(demandeDeBase * 0.20);
            else impact = (int)(demandeDeBase * 0.05);
        }
        return impact;
    }

    void test_calculerImpactDemande() {
        assertEquals(0, calculerImpactDemande(5.0, 100));
        assertEquals(-15, calculerImpactDemande(15.0, 100));
    }

    void simulerMarche(Marche m, Entreprise e) {
        double variationPrix = (random() * 10.0) - 5.0; 
        m.prixDeVente += (int)variationPrix;   
        if (m.prixDeVente < 1) m.prixDeVente = 1;

        int demandeDeBase = 140 + (int)(random() * 60); 
        double diffPourcentage = 100.0 * (e.prixDeVente - m.prixDeVente) / m.prixDeVente;
        
        int impact = calculerImpactDemande(diffPourcentage, demandeDeBase);
        
        e.demandeActuelle = demandeDeBase + impact;
        if (e.demandeActuelle < 0) e.demandeActuelle = 0;
    }

    String augmenterPrix(Entreprise e, int montant) {
        e.prixDeVente = e.prixDeVente + montant;
        
        String[] phrases = new String[]{
            "< Prix en hausse. Les clients râlent mais achètent. >",
            "< Inflation artificielle activée. >",
            "< + " + montant + "$ sur l'étiquette. C'est du 'Positionnement Premium'. >"
        };
        return rgb(0, 200, 0, true) + messageAleatoire(phrases) + RESET;
    }

    void test_augmenterPrix() {
        Entreprise entreprise = newEntreprise(new Employe[0], 2000, 30, 20);

        augmenterPrix(entreprise, 5);

        assertEquals(15, entreprise.prixDeVente);
    }

    String baisserPrix(Entreprise entreprise, int montant) {
        if (entreprise.prixDeVente - montant < 1) {
            entreprise.prixDeVente = 1;

            return rgb(200, 200, 0, true) + "< Prix minimum atteint (1$) ! On ne donne pas encore les produits ! >" + RESET;
        }
        entreprise.prixDeVente = entreprise.prixDeVente - montant;
        
        String[] phrases = new String[]{
            "< Prix cassés ! On écrase la concurrence. >",
            "< C'est les soldes. Émeute dans le rayon 4. >",
            "< Dumping commercial activé. >"
        };
        return rgb(200, 0, 0, true) + messageAleatoire(phrases) + RESET;
    }

    void test_baisserPrix() {
        Entreprise entreprise = newEntreprise(new Employe[0], 2000, 30, 20);

        baisserPrix(entreprise, 5);

        assertEquals(5, entreprise.prixDeVente);
    }

    // -----------------------------------------------------------------< GESTION DES CHOIX DU JOUEUR >------------------------------------------------------------------

    String recruterEmploye(Entreprise entreprise, int budgetDebutSemaine) {
        int chargesFutures = entreprise.charges + SALAIRE_STANDARD;

        // SECURITÉ : Les charges futures ne doivent pas dépasser 50% du budget du début de la semaine
        if (chargesFutures * 2 > budgetDebutSemaine) {
            return rgb(200, 100, 0, true) + "< Trésorerie insuffisante (Charges > 50% du budget initial) ! >" + RESET;
        } 
        else if (entreprise.budget < COUT_RECRUTEMENT) {
            return rgb(200, 200, 0, true) + "< Fonds insuffisants pour l'annonce ! >" + RESET;
        } 
        else {
            int idx = -1;
            for(int i = 0; i < length(entreprise.listeEmployes); i++) {
                if (!entreprise.listeEmployes[i].salarie) {
                    idx = i;
                    break;
                }
            }

            if (idx != -1) {
                entreprise.listeEmployes[idx].salarie = true;
                entreprise.listeEmployes[idx].sousPaye = false;
                entreprise.nbEmployes = entreprise.nbEmployes + 1;
                
                entreprise.budget = entreprise.budget - COUT_RECRUTEMENT;
                entreprise.charges = entreprise.charges + SALAIRE_STANDARD;
                
                String[] phrases = new String[]{
                    "< " + entreprise.listeEmployes[idx].prenom + " recruté. Il a l'air motivé (le pauvre). >",
                    "< Nouvelle recrue : " + entreprise.listeEmployes[idx].prenom + ". Espérance de vie dans la boîte : 3 mois. >",
                    "< " + entreprise.listeEmployes[idx].prenom + " a signé. Il n'a pas lu les petites lignes. >"
                };
                return rgb(0, 200, 0, true) + messageAleatoire(phrases) + RESET;

            } else {
                return rgb(200, 200, 0, true) + "< Le marché du travail est vide ! >" + RESET;
            }
        }
    }

    void test_recruterEmploye() {
        Employe[] tab = new Employe[2];
        tab[0] = newEmploye("A", "A");
        Entreprise entreprise = newEntreprise(tab, 2000, 30, 20);

        recruterEmploye(entreprise, 2000);

        assertEquals(1, entreprise.nbEmployes);
        assertEquals(300, entreprise.charges);
        assertEquals(1700, entreprise.budget);
    }

    String virerEmploye(Entreprise entreprise) {
        if (entreprise.nbEmployes <= 1) {
            return rgb(200, 200, 0, true) + "< Vous ne pouvez pas virer le dernier employé ! >" + RESET;

        } else if (entreprise.budget < COUT_LICENCIEMENT) {
            return rgb(200, 200, 0, true) + "< Pas assez d'argent pour payer les indemnités (" + COUT_LICENCIEMENT + "$) ! >" + RESET;
        }

        int idx = -1;
        for(int i = length(entreprise.listeEmployes) - 1; i >= 0; i--) {
            if (entreprise.listeEmployes[i].salarie) {
                idx = i;
                break;
            }
        }

        if (idx != -1) {
            boolean etaitSousPaye = entreprise.listeEmployes[idx].sousPaye;
            entreprise.listeEmployes[idx].salarie = false;
            entreprise.listeEmployes[idx].sousPaye = false;
            entreprise.nbEmployes = entreprise.nbEmployes - 1;
            
            entreprise.budget = entreprise.budget - COUT_LICENCIEMENT;
            
            if (etaitSousPaye) {
                entreprise.charges = entreprise.charges - SALAIRE_EXPLOITE;
            } else {
                entreprise.charges = entreprise.charges - SALAIRE_STANDARD;
            }
            
            if (entreprise.charges < 0) {
                entreprise.charges = 0;
            }

            String[] phrases = new String[]{
                "< " + entreprise.listeEmployes[idx].prenom + " viré. La sécurité l'a raccompagné. >",
                "< Hop, " + entreprise.listeEmployes[idx].prenom + " à la trappe. C'est la 'flexisécurité'. >",
                "< " + entreprise.listeEmployes[idx].prenom + " nous quitte. On a gardé son stylo. >"
            };

            return rgb(200, 0, 0, true) + messageAleatoire(phrases) + RESET;
        }
        return "< Erreur interne >";
    }

    void test_virerEmploye() {
        Employe[] tab = new Employe[2];
        tab[0] = newEmploye("A", "A"); tab[0].salarie = true;
        tab[1] = newEmploye("B", "B"); tab[1].salarie = true;

        Entreprise entreprise = newEntreprise(tab, 2000, 30, 20);

        virerEmploye(entreprise);

        assertEquals(1, entreprise.nbEmployes);
        assertEquals(1500, entreprise.budget); 
    }

    String exploiterEmploye(Entreprise entreprise, int budgetDebutSemaine) {
        int chargesFutures = entreprise.charges + SALAIRE_EXPLOITE;

        if (chargesFutures * 2 > budgetDebutSemaine) {
             return rgb(200, 100, 0, true) + "< Trésorerie trop fragile pour recruter ! >" + RESET;
        }
        else if (entreprise.budget < COUT_CORRUPTION) {
            return rgb(200, 200, 0, true) + "< Fonds insuffisants (" + COUT_CORRUPTION + "$) ! >" + RESET;
        } 
        
        int idx = -1;
        for(int i = 0; i < length(entreprise.listeEmployes); i++) {
            if (!entreprise.listeEmployes[i].salarie) {
                idx = i;
                break;
            }
        }

        if (idx != -1) {
            entreprise.listeEmployes[idx].salarie = true;
            entreprise.listeEmployes[idx].sousPaye = true;
            entreprise.nbEmployes = entreprise.nbEmployes + 1;
            
            entreprise.budget = entreprise.budget - COUT_CORRUPTION;
            entreprise.charges = entreprise.charges + SALAIRE_EXPLOITE;
            
            String[] phrases = new String[]{
                "< " + entreprise.listeEmployes[idx].prenom + " embauché au black. Chut. >",
                "< Main d'oeuvre pas chère trouvée (" + entreprise.listeEmployes[idx].prenom + "). >"
            };

            return rgb(200, 0, 0, true) + messageAleatoire(phrases) + RESET;

        } else {
            return "< Personne à exploiter >";
        }
    }

    void test_exploiterEmploye() {
        Employe[] tab = new Employe[1];
        tab[0] = newEmploye("A", "A");

        Entreprise entreprise = newEntreprise(tab, 1000, 30, 20);

        exploiterEmploye(entreprise, 1000);

        assertEquals(100, entreprise.charges);
        assertEquals(950, entreprise.budget);
    }

    String baisserSalaires(Entreprise entreprise) {
        int reduction = 50 * entreprise.nbEmployes;
        if (entreprise.charges <= 50 * entreprise.nbEmployes) {
            return rgb(200, 200, 0, true) + "< Salaire minimum déjà atteint ! >" + RESET;
        } else {
            entreprise.charges = entreprise.charges - reduction;
            if (entreprise.charges < 0) entreprise.charges = 0;
            
            String[] phrases = new String[]{
                "< Salaires baissés. Le moral chute, la marge augmente. >",
                "< Vous avez rogné sur la paye. Ils mangeront des pâtes. >",
                "< Économies réalisées sur le dos des employés. Bravo. >"
            };
            return rgb(200, 200, 0, true) + messageAleatoire(phrases) + RESET;
        }
    }

    void test_baisserSalaires() {
        Employe[] tab = new Employe[2]; 
        tab[0] = newEmploye("A","A"); tab[0].salarie=true;
        Entreprise entreprise = newEntreprise(tab, 1000, 30, 20);
        baisserSalaires(entreprise);
        assertEquals(250, entreprise.charges); 
    }


    String acheterMachine(Entreprise entreprise) {
        if (entreprise.budget < 1000) return rgb(200, 200, 0, true) + "< Pas assez d'argent (1000$) ! >" + RESET;
        entreprise.budget = entreprise.budget - 1000;
        entreprise.niveauProduction = entreprise.niveauProduction + 1;
        
        String[] phrases = new String[]{
            "< Nouvelle machine installée. Elle remplace 10 humains. >",
            "< Investissement technologique. Skynet approche. >",
            "< La production va s'accélérer (et le bruit aussi). >"
        };
        return rgb(0, 200, 0, true) + messageAleatoire(phrases) + RESET;
    }

    void test_acheterMachine() {
        Entreprise entreprise = newEntreprise(new Employe[0], 2000, 30, 20);
        acheterMachine(entreprise);
        assertEquals(1000, entreprise.budget);
        assertEquals(2, entreprise.niveauProduction);
    }

    String acheterContrefacon(Entreprise entreprise) {
        if (entreprise.budget < 200) return rgb(200, 200, 0, true) + "< Pas assez d'argent (200$) ! >" + RESET;
        entreprise.budget = entreprise.budget - 200;
        entreprise.stocks = entreprise.stocks + 50;
        
        String[] phrases = new String[]{
            "< Stock de 'Adibas' reçu. Personne ne verra la différence. >",
            "< Made in Nulle-Part ajouté au stock. Marge maximale. >",
            "< C'est du plastique, mais on vendra ça comme du cuir. >"
        };
        return rgb(200, 200, 0, true) + messageAleatoire(phrases) + RESET;
    }

    void test_acheterContrefacon() {
        Entreprise entreprise = newEntreprise(new Employe[0], 2000, 0, 20);
        acheterContrefacon(entreprise);
        assertEquals(1800, entreprise.budget);
        assertEquals(50, entreprise.stocks);
    }

    String snifferCoke(Entreprise entreprise) {
        if (entreprise.budget < 450) return rgb(200, 200, 0, true) + "< Votre dealer n'accepte pas le crédit (450$). >" + RESET;
        entreprise.budget = entreprise.budget - 450;
        entreprise.niveauProduction = entreprise.niveauProduction + 1;
        
        String[] phrases = new String[]{
            "< Productivité boostée ! Les employés voient des couleurs bizarres. >",
            "< Ambiance 'Loup de Wall Street' dans l'open space ! >"
        };
        return rgb(200, 0, 0, true) + messageAleatoire(phrases) + RESET;
    }

    void test_snifferCoke() {
        Entreprise entreprise = newEntreprise(new Employe[0], 2000, 30, 20);
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

    void test_finDePartie() {
        Entreprise entreprise = newEntreprise(new Employe[0], -100, 0, 0);
        assertTrue(finDePartie(entreprise, newDate(1,1), 1000));
        entreprise.budget = 2000;
        assertTrue(finDePartie(entreprise, newDate(31,12), 1000));
    }

    void saveGame(Entreprise entreprise, Date date) {
        String[][] sauvegarde = {
            {"Budget", "Charges", "NbEmployes", "Stocks", "PrixDeVente", "NiveauProduction", "ProductionJournaliere", "Jour", "Mois"},
            {intToString(entreprise.budget), intToString(entreprise.charges), intToString(entreprise.nbEmployes), intToString(entreprise.stocks),
             intToString(entreprise.prixDeVente), intToString(entreprise.niveauProduction), intToString(entreprise.productionJournaliere),
             intToString(date.jour), intToString(date.mois)}
        };
        saveCSV(sauvegarde, "./extensions/config/save.csv");
    }

    String loadGame(Entreprise entreprise, Date date) {
        if (rowCount(save) < 2) return rgb(200, 0, 0, true) + "< Sauvegarde vide >" + RESET;

        date.jour = stringToInt(getCell(save, 1, 7));
        date.mois = stringToInt(getCell(save, 1, 8));

        entreprise.budget = stringToInt(getCell(save, 1, 0));
        entreprise.charges = stringToInt(getCell(save, 1, 1));
        
        int nbSaved = stringToInt(getCell(save, 1, 2));
        for(int i=0; i<length(entreprise.listeEmployes); i++) {
            entreprise.listeEmployes[i].salarie = false;
            entreprise.listeEmployes[i].sousPaye = false;
        }
        for(int i=0; i<nbSaved && i<length(entreprise.listeEmployes); i++) {
            entreprise.listeEmployes[i].salarie = true;
        }
        entreprise.nbEmployes = nbSaved;

        entreprise.stocks = stringToInt(getCell(save, 1, 3));
        entreprise.prixDeVente = stringToInt(getCell(save, 1, 4));
        entreprise.niveauProduction = stringToInt(getCell(save, 1, 5));
        entreprise.productionJournaliere = stringToInt(getCell(save, 1, 6));
        
        return rgb(0, 200, 0, true) + "< Partie chargée ! >" + RESET;
    }

    void updateEntreprise(Date date, Entreprise entreprise, Marche m) {
        int coutMatierePremiere = 2; 
        
        for(int i = 0; i < 5; i++) {
            entreprise.productionJournaliere = (int) ((entreprise.nbEmployes * 15) * (entreprise.niveauProduction + 0.5));
            
            int productionPossible = entreprise.productionJournaliere;
            int coutProductionJour = productionPossible * coutMatierePremiere;
            int productionReelle = 0;

            if (entreprise.budget >= coutProductionJour) {
                entreprise.budget = entreprise.budget - coutProductionJour;
                productionReelle = productionPossible;

            } else if (entreprise.budget > 0) {
                productionReelle = entreprise.budget / coutMatierePremiere;
                entreprise.budget = entreprise.budget - (productionReelle * coutMatierePremiere);
            }
            entreprise.stocks = entreprise.stocks + productionReelle;
            
            simulerMarche(m, entreprise);

            int demandeDuJour = entreprise.demandeActuelle / 5;
            if(entreprise.demandeActuelle > 0 && demandeDuJour == 0) demandeDuJour = 1;
            
            int ventesDuJour = 0;
            if (entreprise.stocks < demandeDuJour) {
                ventesDuJour = entreprise.stocks;
            } else {
                ventesDuJour = demandeDuJour;
            }

            entreprise.stocks = entreprise.stocks - ventesDuJour;
            entreprise.budget = entreprise.budget + (ventesDuJour * entreprise.prixDeVente);
        }

        entreprise.budget = entreprise.budget - entreprise.charges;
        for (int j = 0; j < 7; j++) {
            gestionDate(date);
        }
    }

    void test_updateEntreprise() {
        Entreprise entreprise = newEntreprise(new Employe[0], 1000, 0, 100);
        Marche m = newMarche(10, 100, 50);
        Date d = newDate(1, 1);

        updateEntreprise(d, entreprise, m);

        assertTrue(entreprise.budget != 1000); 
    }

    // CORRECTION ICI : Ajout de l'accolade manquante
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

    // ============================================================================================
    //                                     INTERFACE (TUI)
    // ============================================================================================

    String tuiToString(Date date, Entreprise entreprise, Marche m, String pathTui, String notification) {
        File tui = new File(pathTui);
        String affichage = "";
        String res = "";
        int idx = 0;
        
        int[] tabVar = new int[] {
            entreprise.budget, 
            entreprise.charges, 
            entreprise.nbEmployes, 
            entreprise.stocks, 
            entreprise.prixDeVente, 
            entreprise.niveauProduction, 
            entreprise.productionJournaliere * 5, 
            m.prixDeVente,
            entreprise.demandeActuelle 
        };

        while (ready(tui)) affichage = affichage + readLine(tui) + '\n';

        while (idx < length(affichage)) { 
            if (charAt(affichage, idx) == '%' && (charAt(affichage, idx +1) >= '0' && charAt(affichage, idx +1) <= '9')) { 
                int varIndex = (int)(charAt(affichage, idx +1) - '0');
                if (varIndex < length(tabVar)) {
                    res = res + rgb(100, 100, 200, true) + tabVar[varIndex] + RESET;
                } else {
                    res = res + "ERR";
                }
                idx = idx + 2;

            } else if (charAt(affichage, idx) == '%' && charAt(affichage, idx +1) == 'D') { 
                res = res + rgb(100, 100, 200, true) + dateToString(date) + RESET;
                idx = idx + 2;

            } else if (charAt(affichage, idx) == '%' && charAt(affichage, idx +1) == 'N') { 
                res = res + rgb(100, 100, 200, true) + notification + RESET;
                idx = idx + 2;

            } else if (charAt(affichage, idx) == '%' && charAt(affichage, idx +1) == 'E') { 
                res = res + rgb(150, 150, 150, true) + listeEmployesToString(entreprise.listeEmployes) + RESET;
                idx = idx + 2;

            } else {
                res = res + rgb(128, 128, 128, true) + charAt(affichage, idx) + RESET;
                idx = idx + 1;
            }
        }
        return res;
    }


    // Fonction qui permet de convertir la liste des employés en String
    String listeEmployesToString(Employe[] listeEmployes) {
        String affichage = "";

        for (int idx = 0; idx < length(listeEmployes); idx++) {
            if (listeEmployes[idx].salarie) { // si lemployé est salarié
                affichage = affichage + " ▸ " + listeEmployes[idx -1].prenom + " " + listeEmployes[idx -1].nom + '\n';
            }
        }
        return affichage;
    }

    void clear() {
        print("\033[H\033[2J\033[3J");
    }

    // -----------------------------------------------------------------< ALGORITHME PRINCIPAL >------------------------------------------------------------------

    void algorithm() {
        String choix = "";
        Date date = newDate(1,1);
        String notification = "";
        
        Employe[] listeEmployes = initEmployes();
        if (length(listeEmployes) > 0) listeEmployes[0].salarie = true;

        int budget = 5000;
        int charges = 300;
        int stocks = 0;
        int prix = 10;
        int prod = 1;

        if (rowCount(config) >= 2) {
             budget = stringToInt(getCell(config, 1, 0));
             stocks = stringToInt(getCell(config, 1, 1)); 
             prix = stringToInt(getCell(config, 1, 2));   
        }

        Entreprise entreprise = newEntreprise(listeEmployes, budget, stocks, prix);
        
        int prixMarche = 15;
        int stockMarche = 50000;
        int demandeMarche = 25;
        if (rowCount(marcheCSV) >= 2) {
            prixMarche = stringToInt(getCell(marcheCSV, 1, 0));
            stockMarche = stringToInt(getCell(marcheCSV, 1, 1));
            demandeMarche = stringToInt(getCell(marcheCSV, 1, 2));
        }
        Marche marche = newMarche(prixMarche, stockMarche, demandeMarche);

        int objectif = 1000000;
        if (rowCount(config) >= 5) objectif = stringToInt(getCell(config, 4, 0));

        int budgetDebutSemaine = entreprise.budget;

        while (!finDePartie(entreprise, date, objectif) && !equals(choix, "stop")) { 
            clear();
            println(tuiToString(date, entreprise, marche, pathAccueil, notification));
            choix = readString();
            
            if (equals(choix, "1")) { 
                
                budgetDebutSemaine = entreprise.budget;

                while (!equals(choix, "4")){ 
                    clear();
                    println(tuiToString(date, entreprise, marche, pathTabDeBord, notification));
                    choix = readString();

                    if (equals(choix, "1")) { 
                        while (!equals(choix, "5")){ 
                            clear();
                            println(tuiToString(date, entreprise, marche, pathEmployes, notification));
                            choix = readString();

                            if (equals(choix, "1")) notification = recruterEmploye(entreprise, budgetDebutSemaine);
                            else if (equals(choix, "2")) notification = virerEmploye(entreprise);
                            else if (equals(choix, "3")) notification = exploiterEmploye(entreprise, budgetDebutSemaine);
                            else if (equals(choix, "4")) notification = baisserSalaires(entreprise);
                        }
                        choix = "-1"; notification = "";

                    } else if (equals(choix, "2")) { 
                        while (!equals(choix, "4")) {
                            clear();
                            println(tuiToString(date, entreprise, marche, pathProduction, notification));
                            choix = readString();

                            if (equals(choix, "1")) notification = acheterMachine(entreprise);
                            else if (equals(choix, "2")) notification = acheterContrefacon(entreprise);
                            else if (equals(choix, "3")) notification = snifferCoke(entreprise);
                        }
                        choix = "-1"; notification = "";

                    } else if (equals(choix, "3")) { 
                        while (!equals(choix, "5")) {
                            clear();
                            println(tuiToString(date, entreprise, marche, pathMarche, notification));
                            choix = readString();
                            
                            if (equals(choix, "1")) notification = augmenterPrix(entreprise, 1);
                            else if (equals(choix, "2")) notification = baisserPrix(entreprise, 1);
                            else if (equals(choix, "3")) notification = augmenterPrix(entreprise, 5);
                            else if (equals(choix, "4")) notification = baisserPrix(entreprise, 5);
                         }
                         choix = "-1"; notification = "";

                    } else if (equals(choix, "4")) { 
                        updateEntreprise(date, entreprise, marche);
                        budgetDebutSemaine = entreprise.budget;
                        notification = genererMessageFinancier(entreprise);
                        saveGame(entreprise, date);

                        while (!equals(choix, "1")){ 
                            clear();
                            println(tuiToString(date, entreprise, marche, pathResultats, notification));
                            choix = readString();
                        }
                        choix = "1"; notification = "";
                    }
                } choix = "1";
                
            } else if (equals(choix, "2")) { 
                notification = loadGame(entreprise, date);
                budgetDebutSemaine = entreprise.budget;
                choix = "1";

            } else if (equals(choix, "3")) { 
                while (!equals(choix, "1")) {
                    clear();
                    println(tuiToString(date, entreprise, marche, pathButDuJeu, notification));
                    choix = readString();
                }
                choix = "-1"; notification = "";
            } else {
                choix = "stop";
            }
        }
    }
}