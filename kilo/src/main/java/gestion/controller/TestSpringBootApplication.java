package gestion.controller;

import com.google.gson.Gson;
import gestion.km.Error;
import gestion.model.V_car_ins_del;
import gestion.km.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@RestController
@SpringBootApplication
@RequestMapping("/km")
@CrossOrigin(origins={"http://localhost:3001/","http://localhost:3000/","http://localhost:80/kilometrage/*"})

public class TestSpringBootApplication {
	@GetMapping("/expiration")
	public String selectExpire(@RequestParam(name="mois")int moisExpiration){
		try {
			return new Gson().toJson(new Success(V_car_ins_del.getExpiring(moisExpiration)));
		} catch (Exception e) {
			Error er=new Error();
			er.setInfo(new ErrInfo(500,e.getMessage()));
			return new Gson().toJson(er);
		}
	}
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/kilometrages")
	public String getAll(@RequestHeader(name = "Authorization") String token) {
		Gson gson = new Gson();
		try {
			if (Token.verifExpired(token) == false)
				throw new Exception("session expired");
			else {
				ArrayList list = new Kilometrage().getAll();
				return gson.toJson(new Success(list));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Error err = new Error();
			err.setInfo(new ErrInfo(500, e.getMessage()));
			return gson.toJson(err);
		}
	}

	@DeleteMapping("/kilometrages")
	public String delete(@RequestParam(value = "car", defaultValue = "") String name,
			@RequestHeader(name = "Authorization") String token,
			@RequestParam(value = "date", defaultValue = "") String date) {
		Gson gson=new Gson();
		try {
			if (Token.verifExpired(token) == false)
				throw new Exception("session expired");
			else {
				Kilometrage km = new Kilometrage();
				km.setIdvehicule(name);
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				km.setDaty(df.parse(date));
				km.delete();
				return gson.toJson(new Success("deleted successfully"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Error err = new Error();
			err.setInfo(new ErrInfo(500, e.getMessage()));
			return gson.toJson(err);
		}
	}

	@PostMapping("/kilometrages")
	public String newKm(@RequestBody Kilometrage km, @RequestHeader(name = "Authorization") String token) {
		Gson gson=new Gson();
		try {
			if (Token.verifExpired(token) == false)
				throw new Exception("session expired");
			else {
				km.save();
				return gson.toJson(new Success("insert successfull"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Error err = new Error();
			err.setInfo(new ErrInfo(500, e.getMessage()));
			return gson.toJson(err);
		}
	}

	@PutMapping("/kilometrages")
	public String update(@RequestBody Kilometrage[] km, @RequestHeader(name = "Authorization") String token) {
		Gson gson=new Gson();
		try {
			if (Token.verifExpired(token) == false)
				throw new Exception("session expired");
			else {
				km[0].update(km[1]);
				return gson.toJson(new Success("update successfull"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Error err = new Error();
			err.setInfo(new ErrInfo(500, e.getMessage()));
			return gson.toJson(err);
		}
	}
}
