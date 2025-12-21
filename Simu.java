class Simu extends Program {

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

    // implémentation de la fonction newMarche
    Marche newMarche(int prixDeVente, int stocks, int demande) {
        Marche marche = new Marche();

        marche.prixDeVente = prixDeVente;
        marche.stocks = stocks;
        marche.demande = demande;

        return marche;
    }

    // tests de la fonction newMarche
    void test_newMarche() {
        Marche marche = newMarche(10, 50, 20);

        assertEquals(10, marche.prixDeVente);
        assertEquals(50, marche.stocks);
        assertEquals(20, marche.demande);
    }

    void algorithm() {
        Entreprise entreprise = newEntreprise(2000, 300, 2, 50, 20, 1, 2);
        Marche marche = newMarche(15, 50, 50);
    }
}