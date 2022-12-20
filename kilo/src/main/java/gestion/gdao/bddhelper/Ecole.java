/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.gdao.bddhelper;

import gestion.gdao.genericdao.ColumnName;
import gestion.gdao.genericdao.TableName;

/**
 *
 * @author Hart
 */
@TableName(value = "ecole")
public class Ecole {
    @ColumnName(value = "idEcole", pk = true)
    String idEcole;

    @Override
    public String toString() {
        return "Ecole{" + "idEcole=" + idEcole + ", nom=" + nom + '}';
    }

    @ColumnName(value = "nom")
    String nom;

    public String getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(String idEcole) {
        this.idEcole = idEcole;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
