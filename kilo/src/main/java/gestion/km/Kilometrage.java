package gestion.km;

import gestion.gdao.inherit.*;

import java.util.Date;

import gestion.gdao.genericdao.*;

@TableName(value = "kilometrage")
public class Kilometrage extends DBModel {

    @ColumnName(pk = true)
    String idvehicule;
    @ColumnName()
    Date daty;
    @ColumnName()
    Double debut;
    @ColumnName()
    Double fin;

    public String getIdvehicule() {
        return idvehicule;
    }

    public void setIdvehicule(String idvehicule) {
        this.idvehicule = idvehicule;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public Double getDebut() {
        return debut;
    }

    public void setDebut(Double debut) {
        this.debut = debut;
    }

    public Double getFin() {
        return fin;
    }

    public void setFin(Double fin) {
        this.fin = fin;
    }
}
