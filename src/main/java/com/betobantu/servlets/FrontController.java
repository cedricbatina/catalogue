package com.betobantu.servlets;

import java.util.*;
import java.io.*;
import java.rmi.server.ServerCloneException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.inject.Inject;
import commandes.*;
import dao.*;

@WebServlet(urlPatterns = { "/listerlivres", "/editer", "/supprimer", "/enregistrer" })

public class FrontController HttpServlet{
 @Inject @JDBC
 LivreDAO dao;

 public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
  Action action = null;
  String operation;
  Commande commande;
  operation = getOperation(req.getRequestURI());
  commande = GestionnaireCommandes.getCommande(operation);
  if (commande != null) {
   commande.setDAO(dao);
   action = commande.executerAction(req);
  }
  if (action.estRedirige()) {
   res.sendRedirect(action.getChemin());
  } else {
   ServletContext sc = getServletContext();
   RequestDispatcher rd = sc.getResquestDispatcher("/" + action.getChemin());
   rd.forward(req, res);
  }
 }

 public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
  doPost(req, res);
 }

 private String getOperation(String requestURI) {
  int pos = requestURI.lastIndexOf("/");
  return requestURI.substring(pos + 1);
 }
}
