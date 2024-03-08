

import modele.Investisseur;

@FunctionalInterface
public interface InvestisseurDao {

    /**
     * Insère un nouvel investisseur dans la base de données.
     *
     * @param investisseur L'objet Investisseur à insérer.
     * @return L'identifiant du nouvel investisseur inséré, ou -1 en cas d'échec.
     */
    int insert(Investisseur investisseur);
}

