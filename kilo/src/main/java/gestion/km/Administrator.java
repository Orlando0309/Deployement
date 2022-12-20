package gestion.km;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import gestion.gdao.genericdao.GenericDAO;
import gestion.gdao.genericdao.exception.DatabaseConfException;
import gestion.helpers.TokenManager;

public class Administrator {
    int idAdmin;
    String email;
    String mdp;

    public Administrator(int idAdmin, String email, String mdp) {
        this.idAdmin = idAdmin;
        this.email = email;
        this.mdp = mdp;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public static Token connection(String email, String mdp, String url, Connection con) throws Exception {
        String[] rep = new String[2];
        String requette = "Select id,idadmin,identifiant from administrator where identifiant=? and motdepasse=?";
        PreparedStatement statement = con.prepareStatement(requette);
        statement.setString(1, email);
        statement.setString(2, mdp);
        System.out.println(statement.toString());
        ResultSet resultat = statement.executeQuery();
        Token temp = new Token();
        if (resultat.next()) {
            int id = resultat.getInt(1);
            rep[0] = resultat.getString(2);
            rep[1] = resultat.getString(3);
            String sha1 = TokenManager.generateToken(id, url);
            temp.setToken(sha1);
            temp.setIdadmin(rep[0]);
            temp.insert();
            return Token.select(sha1);
            // return TokenManager.generateToken(id);
        } else
            throw new Exception("id not found");
    }

    public static Token connection(String email, String mdp, String url) throws SQLException, ClassNotFoundException {
        Token rep = null;
        Connection con = null;
        try {
            con = GenericDAO.initCO(true, con);
            rep = connection(email, mdp, url, con);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null)
                con.close();
        }
        return rep;
    }

    public static void deconnection(String token) throws SQLException, ClassNotFoundException, DatabaseConfException {
        Token temp = Token.select(token);
        temp.delete();
    }
}
