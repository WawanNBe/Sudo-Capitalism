class Entreprise{
    Employe[] listeEmployes; // la liste des employes acuetls (max 30 pour l'instant)
    int nbEmployes; // nb d'employés
    int budget; // le budget de l'entreprise
    int charges; // charges par employés

    int stocks; // stocks
    int prixDeVente; // prix de vente

    int niveauProduction; // par défaut la production peut etre améliorée jusqu'au niveau 10
    int productionJournaliere;
    int demandeActuelle;

    int scoreEthique; // le score d'éthique du joueur à 0 au début, en négatif ou positif selon ses choix
    int nbSousPayes; // le nombre d'employés sous payés
    String gravite; // la gravité lors des contrôles d'urssaf
}