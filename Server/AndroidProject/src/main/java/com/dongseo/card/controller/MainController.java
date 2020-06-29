package com.dongseo.card.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dongseo.card.model.user.UserService;
import com.dongseo.card.model.user.UserVO;
import com.google.protobuf.TextFormat.ParseException;

@Controller
public class MainController {

	static final Logger log = Logger.getLogger(String.valueOf(CardController.class));
	
	@Autowired
	UserService userService;
	
	@GetMapping(value="/")
	public String home (Model model) {
		return "login";
	}
	@GetMapping(value="/sign")
	public String sign() {
		return "sign";
	}
	@GetMapping(value="/home")
	public String home(HttpSession session) {
		log.info((String)session.getAttribute("id"));
		return "home";
		
	}
	
	
	
// REST 	
	
	@PostMapping(value = "/login", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, String> login(@RequestBody UserVO vo, HttpSession session) {
		HashMap<String, String> obj = new HashMap<String, String>();
		log.info(vo.toString());
        String E;
		Boolean check = userService.loginCheck(vo);
		log.info("로그인");
		log.info(vo.getId());
		if(check==true) {
			E = "T";
		}
		else E="F";
		obj.put("result", E);
		return obj;
	}
	@PostMapping(value = "/sign", produces = "application/json; charset=utf-8")
	@ResponseBody
	public HashMap<String, Object> sign(@RequestBody Map<String, Object> t)throws Exception {
		HashMap<String, Object> obj = new HashMap<String, Object>();
		log.info(t.get("id")+"\n"+t.get("password")+"\n"+t.get("name")+"\n"+t.get("number")+"\n"+t.get("birth"));
		
		UserVO vo = new UserVO((String)t.get("id"),(String)t.get("password"),(String)t.get("name"),(String)t.get("number"),Integer.parseInt(String.valueOf(t.get("birth"))));
		
		if(userService.checkUserId((String)t.get("id"))) {
			obj.put("result", "아이디가 이미 존재 하고 있습니다.");
			return obj;
		}
		
		userService.insertUser(vo);
		
		obj.put("result","T");
		return obj;
	}
	
	@PostMapping(value = "/position", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, String> position(HttpServletRequest request) {
        Map<String, String> obj = new HashMap<String, String>();
        String x =  request.getParameter("x");
        String y =  request.getParameter("y");

        log.info("x:"+x );
        log.info("y:"+y );
		
        
		return obj;
	}
	
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String homes(Locale locale, Model model, @RequestParam Map<String, String> param) {
		
		
		String x = param.get("x");
		String y = param.get("y");
		
		System.out.println(x);
		System.out.println(y);
		
		model.addAttribute("param", param);
		
		return "home";
	}

	@ResponseBody
	@RequestMapping(value ="/click", method = RequestMethod.GET)
	public Map<String, String> click(@RequestParam Map<String, String> param) throws Exception {

		log.info("Click_event");
		System.out.println(param.get("title"));
		String[] t = param.get("title").split(" ");
		
		
		return param;
	}

	@ResponseBody
	@PostMapping(value = "/money")
	public void money (@RequestBody Map<String, String> param) {
		log.info("id는 " + param.get("id") + " money는 "+ param.get("money"));
		UserVO vo = new UserVO();
		vo.setId(param.get("id"));
		vo.setMoney(param.get("money")); 
		userService.updateMoney(vo);
		
	}
//	@PostMapping(value = "/location", produces = "application/json; charset=utf-8")
//	public String position(Model model, @RequestParam Map<String, String> param) throws ParseException {
//		Map<String, String> obj = new HashMap<String, String>();
//	
//
//		String x = param.get("x");
//		String y = param.get("y");
//		
//		System.out.println(x);
//		System.out.println(y);
//		
//		model.addAttribute("param", param);
//		
////		mav.addObject("line", jsonObj);
////		mav.setViewName("home.jsp");
////		System.out.println("ok");
//		return "search";
//	}
}
