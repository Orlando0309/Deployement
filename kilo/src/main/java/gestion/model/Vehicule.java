package gestion.model;

import gestion.gdao.genericdao.ColumnName;
import gestion.gdao.genericdao.TableName;
import gestion.gdao.inherit.DBModel;

@TableName(value = "vehicule")
public class Vehicule extends DBModel {

	@ColumnName(pk = true)
	String idvehicule;

	@ColumnName()
	String numerovoiture;

	@ColumnName()
	String modele;

	public String getIdvehicule() {
		return idvehicule;
	}

	public void setIdvehicule(String idvehicule) {
		this.idvehicule = idvehicule;
	}

	public String getNumerovoiture() {
		return numerovoiture;
	}

	public void setNumerovoiture(String numerovoiture) {
		this.numerovoiture = numerovoiture;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}
}
