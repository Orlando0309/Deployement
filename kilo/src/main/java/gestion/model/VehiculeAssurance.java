package gestion.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import gestion.connexion.Connexion;

public class VehiculeAssurance {
    int id;
    String idvehicule;
    String numerovoiture;
    String modele;
    String idassurance;
    Date debut;
    Date fin;
    int prix;
    public VehiculeAssurance(int id, String idvehicule, String numerovoiture, String modele, String idassurance,
            Date debut, Date fin, int prix) {
        this.id = id;
        this.idvehicule = idvehicule;
        this.numerovoiture = numerovoiture;
        this.modele = modele;
        this.idassurance = idassurance;
        this.debut = debut;
        this.fin = fin;
        this.prix = prix;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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
    public String getIdassurance() {
        return idassurance;
    }
    public void setIdassurance(String idassurance) {
        this.idassurance = idassurance;
    }
    public Date getDebut() {
        return debut;
    }
    public void setDebut(Date debut) {
        this.debut = debut;
    }
    public Date getFin() {
        return fin;
    }
    public void setFin(Date fin) {
        this.fin = fin;
    }
    public int getPrix() {
        return prix;
    }
    public void setPrix(int prix) {
        this.prix = prix;
    }
    public static int[] listFinMois(int mois) {
        Date d=new Date(System.currentTimeMillis());
        int moisNow=d.getMonth();
        int anneeNow=d.getYear();
        int[] dayLeft=new int[mois];
        Date temp=d;
        for(int i=0;i<mois;i++) {
            moisNow++;
            if(moisNow==13){
                moisNow=1;
                anneeNow=anneeNow+1;
            }
            Date moisProchain=new Date(anneeNow,moisNow,1);
            dayLeft[i]=soustractionDate(temp, moisProchain);
            System.out.println(temp);
            System.out.println(moisProchain);
            temp=moisProchain;
            System.out.println("*****************");

        }  
        return dayLeft;
    }
    public static int soustractionDate(Date avant,Date apres){
        Long diff=apres.getTime()-avant.getTime();
        double second=diff/1000;
        double hour=second/3600;
        int jour=(int)hour/24;
            return jour;
    } 
    public static int nbreJour(int mois){
        Date d=new Date(System.currentTimeMillis());
        int[] rep=listFinMois(mois);
        int total=0;
        for(int i=0;i<rep.length;i++){
            total=total+rep[i];
        }
        total=total+d.getDate();
        return total;
    }
    public static  Date dateExpiration(int mois,Connection c){
        Date rep=null;
        int daydiff=nbreJour(mois);
        Date now=new Date(System.currentTimeMillis());
        String req="select date '"+now+"' + integer '" +daydiff+"'";
        System.out.println(req);
        try{
            PreparedStatement stmt =c.prepareStatement(req);
            ResultSet s=stmt.executeQuery();
            if(s.next()){
                rep=s.getDate(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return rep;
    }
    public static VehiculeAssurance[] selectAssuranceExipre(int mois,Connection c){
        int daydiff=nbreJour(mois);
        Vector<VehiculeAssurance> v=new Vector<VehiculeAssurance>();
        String req="select * from assuranceVoiture where fin<= ?";
        try{
            PreparedStatement stmt =c.prepareStatement(req);
            stmt.setDate(1, dateExpiration(mois, c));
            ResultSet s=stmt.executeQuery();
            while (s.next()) {
                VehiculeAssurance temp=new VehiculeAssurance(s.getInt("id"), s.getString("idvehicule"), s.getString("numerovoiture"),s.getString("modele"),s.getString("idassurance"), s.getDate("debut"), s.getDate("fin"), s.getInt("prix"));  
                v.add(temp);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }    
        VehiculeAssurance[] rep =new VehiculeAssurance[v.size()];
        for(int i=0;i<rep.length;i++){
            rep[i]=v.get(i);
        }
        v.clear();
        return rep;
    }
    public static VehiculeAssurance[] selectAssuranceExipre(int mois)throws SQLException{
        Connection c=null;
        c= Connexion.getConnexion();
        VehiculeAssurance[] rep=null;
        rep=selectAssuranceExipre(mois,c);
        if(c!=null){
            c.close();
        }
        return rep;        
    }
    public static void main(String[] args) throws SQLException{
        VehiculeAssurance[] mois= selectAssuranceExipre(1);
        for(int i=0;i<mois.length;i++){
            System.out.println("Vecicule id="+mois[i].getIdvehicule());
        }
        int x=nbreJour(3);
    }    

}
