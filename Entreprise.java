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
}