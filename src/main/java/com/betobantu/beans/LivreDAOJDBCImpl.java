package com.betobantu.beans;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;

import com.betobantu.dao.JDBC;
import com.betobantu.dao.LivreDAO;

import beans.Livre;

@ApplicationScoped
@JDBC

public class LivreDAOJDBCImpl implements LivreDAO {
 @Resource(lookup = "jdbc/MySQLLibrairies")
 private DataSource dataSource;

 public Livre trouver(final String id) {
  return avecBD(new RunJDBC<Livre>() {
   public Livre run(Connection con) throws Exception {
    PreparedStatement requ = con.prepareStatement("SELECT * FROM livre WHERE id = ?");
    requ.setString(1, id);
    ResultSet rs = requ.executeQuery();
    if (rs.next()) {
     Livre livre = new Livre();
     livre.setId("" + rs.getString("id"));
     livre.setPrix(rs.getBigDecimal("prix").doubleValue());
     livre.setAuteur((rs.getString("auteur")));
     livre.setTitre(rs.getString("titre"));
     livre.setDescription(rs.getDescription());
     return livre;
    } else {
     return null;
    }
   }
  });
 }

 public void ajouter(final String id, final String titre, final String desc, final String auteur, final String prix) {
  avecBD(new RunJDBC<Livre>() {
   public Livre run(Connection con) throws Exception {
    PreparedStatement requ = con
      .prepareStatement("INSERT INTO livre (id, titre, description,  " + " auteur, prix) VALUES (?,?,?,?,?)");
    requ.setString(1, id);
    requ.setString(2, titre);
    requ.setString(3, desc);
    requ.setString(4, auteur);
    try {
     requ.setBigDecimal(5, new BigDecimal(prix));

    } catch (NumberFormatException e) {
     requ.setBigDecimal(5, new BigDecimal("0.0"));
    }
    int nbLignes = requ.executeUpdate();
    if (nbLignes != 1) {
     throw new LibrairieException("Insertion impossible");
    }
    return null;
   }
  });
 }

 public void changer(final String id, final String titre, final String desc, final String auteur, final String prix) {
  avecBD(new RunJDBC<Livre>() {
   public Livre run(Connection con) throws Exception {
    PreparedStatement requ = con
      .prepareStatement("UPDATE livre SET titre = ?, description=?, " + "auteur=?,  prix=? WHERE id = ?");
    requ.setString(1, titre);
    requ.setString(2, desc);
    requ.setString(3, auteur);
    try {
     requ.setBigDecimal(4, new BigDecimal("prix"));
    } catch (NumberFormatException e) {
     requ.setBigDecimal(4, new BigDecimal("0.0"));
    }
    requ.setString(5, id);
    int nbLignes = requ.executeUpdate();
    if (nbLignes != 1) {
     throw new LibrairieException("Majour impossible");
    }
    return null;
   }
  });
 }

 public void supprimer(final String id) {
  avecBD(new RunJDBC<Livre>() {
   public Livre run(Connection con) throws Exception {
    PreparedStatement requ = con.prepareStatement("DELETE FORM livre WHERE id = ?");
    requ.setString(1, id);
    int nbLignes = requ.executeUpdate();
    if (nbLignes != 1) {
     throw new LibrairieException("Suppression impossible");
    }
    return null;
   }
  });
 }

 public List<Livre> lister() {
  return lister(null);
 }

 public List<Livre> listerParIdAsc() {
  return lister("id ASC");

 }

 public List<Livre> listerParIdDesc() {
  return lister("id DESC");
 }

 public List<Livre> listeParPrixAsc() {
  return lister("prix ASC");

 }

 public List<Livre> listerParTitreAsc() {
  return lister("titre ASC");

 }

 public List<Livre> listeParAuteurAsc() {
  return lister("auteur ASC");
 }

 public List<Livre> listerParAuteurDesc() {
  return lister("auteur DESC");
 }

 static interface RunJDBC<T> {
  T run(Connection con) throws Exception;
 }

 private List<Livre> lister(final String tri) {
  return avecBD(new RunJDBC<List<Livre>>() {
   public List<Livre> run(Connection con) throws Exception {
    List<Livre> liste = new ArrayList<Liste>();
    Statement stat = con.createStatement();
    final String requ = "SELECT * FROM livre" + (tri != null ? " ORDER BY " + tri + ";" : ";");
    ResultSet rs = stat.executeQuery(requ);
    while (rs.next()) {
     Livre livre = new Livre();
     livre.setId(rs.getString("id"));
     livre.setPrix(rs.getBigDecimal("prix").doubleValue());
     livre.setAuteur(rs.getString("auteur"));
     livre.setTitre(rs.getString("titre"));
     livre.setDescription(rs.getString("description"));
     liste.add(livre);
    }
    return liste;
   }
  });
 }

 private <T> T avecBD(RunJDBC<T> runner) {
  Connection con = null;
  T result = null;
  try {
   con = dataSource.getConnection();
   boolean auto = con.getAutoCommit();
   con.setAutoCommit(false);
   result = runner.run(con);
   con.commit();
   con.setAutoCommit(auto);
  } catch (Exception ex) {
   System.out.println(ex);
   try {
    con.rollback();
   } catch (SQLException e) {
   }
  } finally {
   if (con != null) {
    try {
     con.close();
    } catch (Exception ex) {
    }
   }
   return result;
  }
 }

 private class LibrairieException extends Exception {
  public LibraireException (String mess) {
   super (mess);
  }
 }
}
