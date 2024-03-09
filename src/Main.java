import view.PagePrincipale;

import view.PostgresSQLConfig;

public class Main {
    public static void main(String[] args) {

        PostgresSQLConfig.initializeDatabase();




        PagePrincipale pagePrincipale = new PagePrincipale();
        pagePrincipale.afficherGUI();
    }
}
