class Main extends Program{

    // déclaration des variables globales en dur mais à définir dans un fichier externe ensuite

    // variables liées à l'argent
    int budget = 2000; // budget initial
    int objectifFinancier = 1000000; // objectif financier par défaut

    // variables liées au employés
    int salaire = 1500; // salaire initial des employés
    int nbEmployes = 2; // par défaut il y a deux employés dans la boite (le joueur et un employé)

    // variables liées à la production
    int stocks = 10; // stocks initiaux
    int prixDeVente = 20; // prix de vente initial
    int niveauProduction = 1; // par défaut la production est au niveau 1 et peut etre améliorée jusqu'au niveau 10
    int produitsVendusParJour = (nbEmployes -1) * 3; // nombre de produits vendus par jour (3 par employés par défaut)

    // conditions de fin de jeu (défaite et victoire)
    boolean finAnnee = false; // la partie s'arrête si l'année se termine (sert à limiter le jeu à 52 tours)
    boolean faillite = false; // si le joueur fait faillite, la partie s'arrête
    boolean objectifAtteint = false; // si le joueur atteint l'objectif financier, la partie est gagnée

    // LES VARIABLES SONT À AJUSTER PAR EXEMPLE INITIALISER CERTAINES VIA DES FONCTIONS POUR RENDRE LE JEU PLUS SIMPLE À GERER

    // implémentation de la fonction newDate
    Date newDate(int jour, int mois){
        Date date = new Date();

        date.jour = jour;
        date.mois = mois;
        return date;
    }

    // tests de la fonction newDate
    void test_newDate(){
        // test de la création d'une date
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