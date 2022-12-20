package gestion.controller;

import com.google.gson.Gson;
import gestion.km.ErrInfo;
import gestion.km.Error;
import gestion.km.Success;
import gestion.model.Vehicule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/vehicule")
@SpringBootApplication
public class TestVoiture {
	@GetMapping()
	public String getAll() {
		Gson gson = new Gson();
		try {
				ArrayList list = new Vehicule().getAll();
				return gson.toJson(new Success(list));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Error err = new Error();
			err.setInfo(new ErrInfo(500, e.getMessage()));
			return gson.toJson(err);
		}
	}
	
}
