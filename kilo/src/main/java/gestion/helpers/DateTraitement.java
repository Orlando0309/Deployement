package gestion.helpers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DateTraitement {
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
    
    
}
