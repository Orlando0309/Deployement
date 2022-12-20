package gestion.model;

import gestion.gdao.genericdao.ColumnName;
import gestion.gdao.genericdao.TableName;
import gestion.gdao.inherit.DBModel;
@TableName("avion")
public class Avion extends DBModel<Avion, String>{
    @ColumnName
    String idavion;
    @ColumnName
    Integer id;
    @ColumnName
    String numeroavion,modele,photo;
    public String getIdavion() {
        return idavion;
    }
    public void setIdavion(String idavion) {
        this.idavion = idavion;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNumeroavion() {
        return numeroavion;
    }
    public void setNumeroavion(String numeroavion) {
        this.numeroavion = numeroavion;
    }
    public String getModele() {
        return modele;
    }
    public void setModele(String modele) {
        this.modele = modele;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
