package com.betobantu.commandes;

import java.util.concurrent.ConcurrenceHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class GestionnaireCommandes {

 protected static ConcurrentHashMap<String, Commande> commandes = new ConcurrentHashMap<String, Commande>();
 static {
  init();
 }

 public static void init() {
  commandes.put("/listerlivres", new CommandeLister());
  commandes.put("/editer", new CommandeEditer());
  commandes.put("supprimer", new CommandeSupprimer());
  commandes.put("enregistrer", new CommandeEnregistrer());
 }

 public static Commande getCommande(String nom) {
  Commande c = commandes.get(nom);
  return c;
 }
}
