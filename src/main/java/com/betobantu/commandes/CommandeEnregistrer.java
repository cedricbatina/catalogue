package com.betobantu.commandes;

import java.util.*;
import javax.servlet.http.*;
import dao.LivreDAO;
import beans.Livre;

public class CommandeEnregistrer extends Commande {
 public String getNomCommande() {
  return "enregistrer";
 }

 public Action executerAction(HttpServletRequest req) {
  try {
   String edition = req.getParameter("edition");
   String id = req.getParameter("id");
   String titre = req.getParameter("titre");
   if (titre == null || titre.isEmpty())
    titre = "__";
   String desc = req.getParameter("description");
   if (desc == null || desc.isEmpty())
    desc = "__";
   String prix = req.getParameter("prix");
   if (prix == null || desc.isEmpty())
    prix = "0.00";
   String auteur = req.getParameter("auteur");
   if (auteur == null || auteur.isEmpty())
    auteur = "__";
   if (edition.equals("false")) {
    dao.ajouter(id, titre, desc, auteur, prix);

   } else {
    dao.ajouter(id, titre, desc, auteur, prix);
   }

  } catch (Exception e) {
  }
  return new Action("listerlivres", false);
 }
}
