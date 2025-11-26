class Production{
    int stocks = 10; // stocks initiaux
    int prixDeVente = 20; // prix de vente initial
    int niveauProduction = 1; // par défaut la production est au niveau 1 et peut etre améliorée jusqu'au niveau 10
    int produitsVendusParJour; // pas défini par défaut mais (nbEmployes -1) * (3 + niveauProduction)
}