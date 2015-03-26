package webapp.controller;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webapp.model.Address;
import webapp.model.User;

@Controller
public class UserController {
	
/*	Spring이 파라미터를 자동 Mapping
	 http://localhost:8080/sexam11/m/user/registertest
	 
	 	?name=sun&job=student&age=27&birthday=20150326
		?name=sun&job=student&age=27&birthday=2015/03/26&addrs[0].country=korea&addrs[0].city=seoul&addrs[0].gu=mapo
		?name=sun&job=student&job=programmer&addrs[0].country=korea&addrs[0].city=seoul&addrs[0].gu=mapo
		?name=sun&job=student&job=programmer&addrs[0].country=korea&addrs[0].city=seoul&addrs[0].gu=mapo&addrs[1].country=japan&addrs[1].city=tokyo&addrs[1].gu=sibuya
		?name=sun&job=student&job=programmer&job=writer&addrs[0].country=korea&addrs[0].city=seoul&addrs[0].gu=mapo&addrs[1].country=japan&addrs[1].city=tokyo&addrs[1].gu=sibuya&addrs[2].country=usa&addrs[2].city=la&addrs[2].gu=la
*/
	
	@RequestMapping("/user/registertest")
	public void register(User user, PrintWriter out) {
		
		out.println("user.name = " + user.getName() + "<br>");
		out.println("user.age = " + user.getAge() + "<br>");
		out.println("user.birthday = " + user.getBirthday() + "<br>");
		
		String[] job = user.getJob();
		if(job != null) {
			for(String j : job)
				out.println("user.job = " + j + "<br>");
		}
		
		List<Address> addrs = user.getAddrs();
		if(addrs != null) {
			for(Address a : addrs) {
				out.println("user.addrs.country = " + a.getCountry() + "<br>");
				out.println("user.addrs.city = " + a.getCity() + "<br>");
				out.println("user.addrs.gu = " + a.getGu() + "<br>");
			}
		}
	}
	
	@RequestMapping(value="/user/register", method=RequestMethod.GET)
	public String register() {
		
		return "user/register";
	}

	@RequestMapping(value="/user/register", method=RequestMethod.POST)
	public String register(User user22, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("error", "바인딩 에러");
			return "user/register";
		}
		
		return "user/registersuccess";
	}

}
