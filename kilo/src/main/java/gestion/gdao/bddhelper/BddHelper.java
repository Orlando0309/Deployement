package gestion.gdao.bddhelper;
import gestion.gdao.genericdao.GenericDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class BddHelper {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SQLException, Exception {
        System.out.println("nety");
        Etudiant eleve=new Etudiant();
        Etudiant eleveInsert=new Etudiant();
        eleve.setIdEtudiant("ET1");
        Ecole ec=new Ecole();
        ec.setIdEcole("EC1");
        eleve.setEcole(ec);
        
        eleveInsert.setEcole(ec);
        eleveInsert.setNom("testiuzgf");
        eleveInsert.setPrenom("rafzhgtest");
        
        //GenericDAO.save(eleveInsert,null);
        //GenericDAO.save(eleve,null);
        //GenericDAO.delete(eleve, GenericDAO.getConPost());
        //GenericDAO.update(eleveInsert, eleve, GenericDAO.getConPost());
        ArrayList test=GenericDAO.get(eleveInsert, GenericDAO.getConPost());
        for(int i=0;i<test.size();i++){
            System.out.println(((Etudiant)test.get(i)).getEcole());
        }
        System.out.println(test.size());
        /*Ecole ec2=(Ecole) GenericDAO.getById(ec, GenericDAO.getConPost());
        System.out.println(ec2);*/
    }
}