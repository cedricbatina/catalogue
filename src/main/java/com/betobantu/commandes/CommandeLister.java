package com.betobantu.commandes;

import java.util.*;
import javax.servlet.http.*;
import dao.LivreDAO;
import beans.Livres;

public class CommandeLister extends Commande {

 public String getNomCommande() {
  return " listerlivres";
 }

 public Action executerAction(HttpServletRequest req) {
  String champ = req.getParameter("champ");
  String ordre = req.getParameter("ordre");
  List<Livre> livres = null;
  if ("asc".equals(ordre) && "titre".equals(champ)) {
   livres = dao.listerParTitreAsc();

  } else if ("desc".equals(ordre) && "titre".equals(champ)) {
   livres = dao.listerParTitreDesc();

  } else if ("asc".equals(ordre) && "prix".equals(champ)) {
   livres = dao.listerParPrixAsc();
  } else if ("desc".equals(ordre) && "prix".equals(champ)) {
   livres = dao.listerParAutheurAsc();

  } else if ("asc".equals(ordre) && "auteur".equals(champ)) {
   livres = dao.listerParAutheurAsc();

  } else if ("asc".equals(ordre) && "id".equals(champ)) {
   livres = dao.listerParIdAsc();
  } else if ("desc".equals(ordre) && "id".equals(champ)) {
   livres = dao.listerParIdDesc();
  } else {
   livres = dao.lister();
  }
  req.setAttribute("livres", livres);
  return new Action("ListeLivres.jsp", false);
 }

}
