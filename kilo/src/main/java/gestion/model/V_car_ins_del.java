package gestion.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import gestion.gdao.genericdao.ColumnName;
import gestion.gdao.genericdao.TableName;
import gestion.gdao.genericdao.exception.DatabaseConfException;
import gestion.gdao.inherit.DBModel;

@TableName("V_car_ins_del")
public class V_car_ins_del extends DBModel {
    @ColumnName
    Integer id;
    @ColumnName()
    String idavion;
    @ColumnName()
    String numeroavion;
    @ColumnName("nommodele")
    String modele;
    @ColumnName("nommarque")
    String marque;
    @ColumnName
    Date finassurance;
    @ColumnName
    Double diff;

    

    public String getIdavion() {
        return idavion;
    }

    public void setIdavion(String idavion) {
        this.idavion = idavion;
    }

    public String getNumeroavion() {
        return numeroavion;
    }

    public void setNumeroavion(String numeroavion) {
        this.numeroavion = numeroavion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public Date getFinassurance() {
        return finassurance;
    }

    public void setFinassurance(Date finassurance) {
        this.finassurance = finassurance;
    }

    public Double getDiff() {
        return diff;
    }

    public void setDiff(Double diff) {
        this.diff = diff;
    }

    public static V_car_ins_del[] getExpiring(int mois) throws SQLException, DatabaseConfException {
        ArrayList<V_car_ins_del> list = new V_car_ins_del().getAll();
        ArrayList<V_car_ins_del> res = new ArrayList<>();
        for (V_car_ins_del v_car_ins_del : list) {
            if (v_car_ins_del.getDiff() < mois) {
                res.add(v_car_ins_del);
            }
        }
        return res.toArray(new V_car_ins_del[res.size()]);
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }
}
