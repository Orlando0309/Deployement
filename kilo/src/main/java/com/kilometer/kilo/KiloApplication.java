package com.kilometer.kilo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gestion.km.ErrInfo;
import gestion.km.Error;
import gestion.km.OwnResponse;
import gestion.km.Success;
import gestion.model.V_vehicule;

@SpringBootApplication(scanBasePackages={"gestion.controller"})
@CrossOrigin(origins={"http://localhost:3001/","http://localhost:3000/","http://localhost:80/kilometrage/*"})
public class KiloApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(KiloApplication.class, args);
	}
	@PutMapping("newstPhotos/{idAvion}")
	public OwnResponse update(@PathVariable(value="idAvion") String idAvion,@RequestParam(name="photos")String photos){
			try {
				V_vehicule.updatePhotos(idAvion,photos);
				return new Success("commande realiser avec succes");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Error er=new Error();
				er.setInfo(new ErrInfo(500, e.getMessage()));
				return  er;
			}
	}
}
