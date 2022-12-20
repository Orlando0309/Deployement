package gestion.controller;

import java.util.ArrayList;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import gestion.km.Administrator;
import gestion.km.ErrInfo;
import gestion.km.Error;
import gestion.km.OwnResponse;
import gestion.km.Success;
import gestion.km.Token;
import gestion.model.V_vehicule;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/car")
@SpringBootApplication
@CrossOrigin(origins={"http://localhost:3001/","http://localhost:3000/","http://localhost:80/kilometrage/*"})
public class GestionVoitureController {
	@GetMapping()
	public String getAll(@RequestHeader(name="authorization") String token)  throws Exception{
		Gson gson = new Gson();
		OwnResponse retour=null;
		if(Token.verifExpired(token)) {
			V_vehicule v=new V_vehicule();
			ArrayList liste=v.getAll();
			retour=new Success(liste);
		}else {
			Error er=new Error();
			er.setInfo(new ErrInfo(403,"You are not allowed to access"));
			retour=er;
		}
		
		
		return gson.toJson(retour);
	}
    @GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello my lovely %s!", name);
	}
	@PostMapping("/connect")
    public OwnResponse connect (@RequestParam(name = "email")String email , @RequestParam(name="mdp")String mdp ,HttpServletRequest req)
    {
        try {
            String url=req.getRequestURL().toString();
			Token data=Administrator.connection(email, mdp,url);
			if(data!=null)
			return new Success(data);
			else throw new Exception();
        } catch (Exception e) {
			Error idnotFound=new Error();
			idnotFound.setInfo(new ErrInfo(404, "ID NOT FOUND"));
           return idnotFound;
        }
    }
	@DeleteMapping("/disconnect/{token}")
	public OwnResponse disconnect(@PathVariable("token")String token){
		try{
			Administrator.deconnection(token);
			return new Success("you are successfully deconnected"); 
		}catch(Exception e){
			e.printStackTrace();
			Error idnotFound=new Error();
			idnotFound.setInfo(new ErrInfo(500, "internal server error"));
            return idnotFound;
		}
	}
}
