package gestion.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gestion.km.ErrInfo;
import gestion.km.Error;
import gestion.km.OwnResponse;
import gestion.km.Success;
import gestion.model.VehiculeAssurance;

@RestController
@SpringBootApplication(scanBasePackages = { "gestion.controller.VehiculeApplication" })
public class VehiculeApplication {
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello Paradis %s!", name);
	}
	@PostMapping("/expiration")
	public OwnResponse selectExpire(@RequestParam(name="mois")int moisExpiration){
		try {
			return new Success(VehiculeAssurance.selectAssuranceExipre(moisExpiration));
		} catch (Exception e) {
			Error er=new Error();
			er.setInfo(new ErrInfo(500,e.getMessage()));
			return er;
		}
	}	
}
