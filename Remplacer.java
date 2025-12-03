class Remplacer extends Program{

    String remplace(String chaine){
        String nouvChaine = "";
        int idx = 0;

        int[] tabVar = new int[] {4, 2, 3};

        while (idx < length(chaine)){
            if (charAt(chaine, idx) == '%'){
                nouvChaine += tabVar[(charAt(chaine, idx +1) -1)- '0'];
                idx += 2;
            } else {
                nouvChaine += charAt(chaine, idx);
                idx ++;
            }
        }
        return nouvChaine;
    }

    void algorithm(){
        String chaine = "---------------< Gestion des employés >-----------------" + '\n'
                      + "Nombre d'employés 1: %1" + '\n'
                      + "Test 3: %3" + '\n'
                      + "" + '\n'
                      + "1 > Recruter un employé                  (-300$/semaine)" + '\n'
                      + "2 > Virer un employé                     (+300$/semaine)" + '\n'
                      + "3 > Sous payer un nouvel employé         (-100$/semaine)" + '\n'
                      + "4 > Baisser les salaires         (-100$/employé/semaine)" + '\n'
                      + "" + '\n'
                      + "5 > Retour" + '\n'
                      + "--------------------------------------------------------" + '\n';

        String aRemplacer = "%2";
        int variable = 4;

        println(remplace(chaine));
    }
}