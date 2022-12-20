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
@TableName(value = "etudiant")
public class Etudiant {
    @ColumnName(value = "idEtudiant", pk = true)
    String idEtudiant;
    @ColumnName(value = "nom")
    String nom;
    @ColumnName(value = "prenom")
    String prenom;
    @ColumnName(value = "ecole", fk = true)
    Ecole ecole;

    public String getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(String idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Ecole getEcole() {
        return ecole;
    }

    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }

}
