package gestion.model;

import java.sql.SQLException;
import java.util.ArrayList;

import gestion.gdao.genericdao.ColumnName;
import gestion.gdao.genericdao.TableName;
import gestion.gdao.genericdao.exception.DatabaseConfException;
import gestion.gdao.inherit.DBModel;

@TableName(value = "v_vehicule")
public class V_vehicule extends DBModel {
	@ColumnName(pk = true)
	String idavion;

	@ColumnName
	String numeroavion;
	@ColumnName
	String nommodele;
	@ColumnName
	String nommarque;
	@ColumnName
	String photo;

	ArrayList kilometrage;

	public ArrayList getKilometrage() {
		return kilometrage;
	}

	public void setKilometrage(ArrayList kilometrage) {
		this.kilometrage = kilometrage;
	}

	@Override
	public ArrayList getAll() throws SQLException, DatabaseConfException {
		// TODO Auto-generated method stub
		ArrayList liste = super.getAll();
		for (int i = 0; i < liste.size(); i++) {
			V_kilometrage kilo = new V_kilometrage();
			V_vehicule temp = (V_vehicule) liste.get(i);
			kilo.setIdavion((temp.getIdavion()));
			ArrayList lis = kilo.get();
			temp.setKilometrage(lis);
			liste.set(i, temp);
		}
		return liste;
	}
	public static void updatePhotos(String idAvion, String photos) throws SQLException, DatabaseConfException{
		V_vehicule taloha=new V_vehicule();
		taloha.setIdavion(idAvion);
		V_vehicule vaovao=new V_vehicule();
		vaovao.setPhoto(photos);
		vaovao.update(taloha);
	}

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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getNommodele() {
		return nommodele;
	}

	public void setNommodele(String nommodele) {
		this.nommodele = nommodele;
	}

	public String getNommarque() {
		return nommarque;
	}

	public void setNommarque(String nommarque) {
		this.nommarque = nommarque;
	}
}
