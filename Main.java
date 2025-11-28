import extensions.CSVFile;
class Main extends Program{

    CSVFile config = loadCSV("./extensions/config.csv");

    int objectifFinancier = 1000000; // objectif financier par défaut A EXTERNALISER

    // conditions de fin de jeu (défaite et victoire)
    boolean finAnnee = false; // la partie s'arrête si l'année se termine (sert à limiter le jeu à 52 tours)
    boolean faillite = false; // si le joueur fait faillite, la partie s'arrête
    boolean objectifAtteint = false; // si le joueur atteint l'objectif financier, la partie est gagnée


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


    // implémentation de la fonction newEntreprise
    Entreprise newEntreprise(int budget, int salaire, int nbEmployes, int stocks, int prixDeVente, int niveauProduction){
        Entreprise entreprise = new Entreprise();

        entreprise.budget = budget;
        entreprise.salaire = salaire;
        entreprise.nbEmployes = nbEmployes;
        entreprise.stocks = stocks;
        entreprise.prixDeVente = prixDeVente;
        entreprise.niveauProduction = niveauProduction;
        entreprise.produitsVendusParJour = nbEmployes * (3 + niveauProduction);

        return entreprise;
    }

    // tests de l afonction newEntreprise
    void test_newEntreprise(){
        Entreprise entrepriseTest = newEntreprise(2000, 1500, 1, 10, 20, 1);

        assertEquals(2000, entrepriseTest.budget);
        assertEquals(1500, entrepriseTest.salaire);
        assertEquals(1, entrepriseTest.nbEmployes);
        assertEquals(10, entrepriseTest.stocks);
        assertEquals(20, entrepriseTest.prixDeVente);
        assertEquals(1, entrepriseTest.niveauProduction);
        assertEquals(4, entrepriseTest.produitsVendusParJour);
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


    void initVariables(){
        // à implémenter
    }


    void afficheMenu(Date date, int chiffreAffaire){
        println(rgb(128, 128, 128, true) + "------< Date: " + dateToString(date) + " >------" + RESET);
        print(rgb(128, 128, 128, true) + "Objectif: 1M$       CA: " + RESET); println(rgb(0, 128, 0, true) + budget + '$' + RESET);
        println("");
        println(rgb(128, 128, 128, true) + "> Gestion des employés (1)" + '\n' +
                                           "> Gestion des produits (2)" + '\n' +
                                           "> Valider les choix pour cette semaine (3)" + '\n' +
                                           "------------------------------------" + RESET);
    }

    void algorithm(){
        Date date = newDate(1,1);
        // while (!finAnnee && !faillite && !objectifAtteint){
            afficheMenu(date, budget);
        // }
    }
}