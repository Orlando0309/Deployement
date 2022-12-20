package gestion.model;

import java.util.Date;

import gestion.gdao.genericdao.ColumnName;
import gestion.gdao.genericdao.TableName;

@TableName("v_kilometrage")
public class V_kilometrage extends V_vehicule {
	@ColumnName()
	Date daty;
	@ColumnName()
	Double debut;
	@ColumnName()
	Double fin;

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
