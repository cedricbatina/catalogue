package com.betobantu.beans;

public class Livre implements Cloneable {
 private String titre;
 private String description;
 private double prix;
 private String auteur;
 private String id;

 public Livre(String id, String tit, String desc, String aut, double px) {
  this.id = id;
  titre = tit;
  description = desc;
  prix = px;
  auteur = aut;
 }

 public Livre() {
 }

 /// titre
 public String getTitre() {
  return titre;
 }

 public void setTitre(String t) {
  titre = t;
 }

 /// description
 public String getDescription() {
  return description;
 }

 public void setDescription(String d) {
  description = d;

 }

 /// prix
 public double getPrix() {
  return prix;
 }

 public void setPrix(double p) {
  prix = p;
 }

 // auteur
 public String getAuteur() {
  return auteur;
 }

 public void setAuteur(String a) {
  auteur = a;
 }

 // id
 public String getId() {
  return id;
 }

 public void setId(String i) {
  id = i;
 }

 public Livre cloneMoi() {
  try {
   return (Livre) super.clone();
  } catch (CloneNotSupportedException e) {
   return null;
  }
 }

 public String toString() {
  return "Livre [titre =" + titre + " description=" + description + ", auteur=" + auteur + ", prix= " + prix + ", id="
    + id + " ]";
 }
}
