/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.gdao.inherit;

import gestion.gdao.genericdao.GenericDAO;
import gestion.gdao.genericdao.exception.DatabaseConfException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hart
 * @param <T>
 * @param <E>
 */
public class DBModel<T extends DBModel, E extends Object> {

    public T getById(E o) throws SQLException, DatabaseConfException {
        try {
            this.setPkVal(o);
            return (T) this.get().get(0);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public T getById(E o, Connection con) throws SQLException, DatabaseConfException {
        try {
            this.setPkVal(o);
            return (T) this.get(con).get(0);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<T> getAll() throws SQLException, DatabaseConfException {
        try {
            ArrayList<T> temp = GenericDAO.get(this.getClass().newInstance(), null);
            return temp;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<T> getAll(Connection con) throws SQLException, DatabaseConfException {
        try {
            ArrayList<T> temp = GenericDAO.get(this.getClass().newInstance(), con);
            return temp;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<T> get() throws SQLException, DatabaseConfException {
        try {
            ArrayList<T> temp = GenericDAO.get(this, null);
            return temp;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public ArrayList<T> get(Connection con) throws SQLException, DatabaseConfException {
        try {
            ArrayList<T> temp = GenericDAO.get(this, con);
            return temp;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public int delete() throws DatabaseConfException {
        try {
            return GenericDAO.delete(this, null);
        } catch (NoSuchFieldException | SQLException | NoSuchMethodException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public int delete(Connection con) throws DatabaseConfException {
        try {
            return GenericDAO.delete(this, con);
        } catch (NoSuchFieldException | SQLException | NoSuchMethodException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public int save() throws DatabaseConfException {
        try {
            return GenericDAO.save(this, null);
        } catch (NoSuchFieldException | SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public int save(Connection con) throws DatabaseConfException {
        try {
            return GenericDAO.save(this, con);
        } catch (NoSuchFieldException | SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public int update(T mods) throws DatabaseConfException {
        try {
            return GenericDAO.update(mods, this, null);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public int update(T mods, Connection con) throws DatabaseConfException {
        try {
            return GenericDAO.update(mods, this, con);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public Object getPkVal() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Field fi = GenericDAO.getPK(this.getClass());
        return GenericDAO.getter(fi).invoke(this);
    }

    public void setPkVal(E o) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Field fi = GenericDAO.getPK(this.getClass());
        System.out.println(fi.getName());
        GenericDAO.setter(fi).invoke(this, o);
    }

    public Vector getPossibleFk() throws SQLException, DatabaseConfException {
        Vector v = new Vector();
        ArrayList li = this.getAll();
        li.forEach((el) -> {
            try {
                v.add((DBModel) el);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        });

        return v;
    }

}
