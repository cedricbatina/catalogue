package com.betobantu.dao;

import java.util.*;
import beans.Livre;

public interface LivreDAO {
 Livre trouver(String id);

 void ajouter(String id, String titre, String desc, String auteur, String prix);

 void changer(String id, String titre, String desc, String auteur, String prix);

 void supprimer(String id);

 List<Livre> lister();

 List<livre> listerParIdAsc();

 List<livre> listerParIdDesc();

 List<livre> listerParTitreAsc();

 List<Livre> listerParTitreDesc();

 List<Livre> listerParPrixAsc();

 List<Livre> listerParPrisDesc();

 List<Livre> listeParAuteurAsc();

 List<Livre> listeParAuteurDesc();

}
