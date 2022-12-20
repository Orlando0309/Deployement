package gestion.km;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import gestion.gdao.genericdao.GenericDAO;
import gestion.gdao.genericdao.exception.DatabaseConfException;

public class Token {
    int idToken;
    String token;
    String idadmin;
    Timestamp dateExpiration;

    public Token() {

    }

    public Token(int idToken, String token, String idadmin, Timestamp dateExpiration) {
        this.idToken = idToken;
        this.token = token;
        this.idadmin = idadmin;
        this.dateExpiration = dateExpiration;
    }

    public Token(String token, String idadmin) {
        this.token = token;
        this.idadmin = idadmin;
    }

    public int getIdToken() {
        return idToken;
    }

    public void setIdToken(int idToken) {
        this.idToken = idToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIdadmin() {
        return idadmin;
    }

    public void setIdadmin(String idadmin) {
        this.idadmin = idadmin;
    }

    public Timestamp getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Timestamp dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public void insert(Connection con) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp dateExp = new Timestamp(now.getYear(), now.getMonth(), now.getDate(), now.getHours(),
                now.getMinutes() + 5, now.getSeconds(), now.getNanos());
        String req = "insert into token(token,idadmin,dateExpiration) values(?,?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(req);
            stmt.setString(1, getToken());
            stmt.setString(2, getIdadmin());
            stmt.setTimestamp(3, dateExp);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert() throws SQLException, DatabaseConfException {
        Connection con = null;
        con = GenericDAO.initCO(true, con);
        insert(con);
        if (con != null)
            con.close();
    }

    public static Token select(String token, Connection c) {
        token = token.replace("Bearer ", "");
        Token r = null;
        String req = "select * from token where token=?";
        try {
            PreparedStatement stmt = c.prepareStatement(req);
            stmt.setString(1, token);
            System.out.println(stmt);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                r = new Token();
                r.setIdToken(res.getInt("idToken"));
                r.setIdadmin(res.getString("idadmin"));
                r.setToken(res.getString("TOKEN"));
                r.setDateExpiration(res.getTimestamp("dateExpiration"));
                System.out.println("setiavako");
            }
            return r;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    public static Token select(String token) throws SQLException, DatabaseConfException {
        System.out.println("mandalo1");
        Token rep = null;
        Connection con = null;
        con = GenericDAO.initCO(true, con);
        rep = select(token, con);
        if (con != null)
            con.close();
        return rep;
    }

    public void delete(Connection con) throws SQLException {
        String req = "delete from token where token=?";
        try {
            PreparedStatement stmt = con.prepareStatement(req);
            stmt.setString(1, this.getToken());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() throws SQLException, ClassNotFoundException, DatabaseConfException {
        Connection c = null;
        c = GenericDAO.initCO(true, c);
        this.delete(c);
        if (c != null) {
            c.close();
        }
    }

    public static boolean verifExpired(String token, Connection c) throws DatabaseConfException {
        try {
            Token temp = Token.select(token);
            if (temp == null)
                return false;
            Timestamp now = new Timestamp(System.currentTimeMillis());

            System.out.println("IL EST MAINTENANT =" + now.toString());
            System.out.println("IL SERA EXPIRE A  =" + temp.getDateExpiration());
            if (now.before(temp.getDateExpiration())) {
                return true;
            } else {
                temp.delete();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean verifExpired(String token) throws SQLException, ClassNotFoundException, DatabaseConfException {
        Connection c = null;
        c = GenericDAO.initCO(true, c);
        if (c != null)
            c.close();
        return verifExpired(token, c);
    }
}
