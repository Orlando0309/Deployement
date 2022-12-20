package gestion.gdao.bddhelper;

import gestion.gdao.genericdao.ColumnName;
import gestion.gdao.genericdao.TableName;

import java.lang.annotation.*;

@TableName(value = "test")
public class Eleve {
    @ColumnName(value = "classesss")
    private int classe;

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ColumnName
    private String name;

    int tes;

    public int getTes() {
        return tes;
    }

    public void setTes(int tes) {
        this.tes = tes;
    }

}