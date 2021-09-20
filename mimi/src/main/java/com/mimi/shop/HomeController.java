package com.mimi.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mimi.dao.Dao;
import com.mimi.vo.Vo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	
	@Autowired
	private Dao dao;
	
	
	@RequestMapping(value = "/")
	public String home() {

		return "login";
	}
	
	@RequestMapping(value = "notyet")
	public String loginNotyet() {

		return "signup";
	}

	@RequestMapping("/signup1")
	public String shoppingHome(@RequestParam String id, @RequestParam int pw, @RequestParam int pw2) {
		Vo vo3=new Vo(id, pw);
		dao.insert(vo3);
		return "home";}
	
	
	@RequestMapping(value = "/bottom")
	public String bottomPage() {

		return "bottom";
	}

	
	@RequestMapping(value = "/a")
	public String aDetail() {

		return "a";
	}

	/*@RequestMapping(value = "/home")
	public String uploadedHome(HttpServletRequest request, @RequestParam("filename") MultipartFile mFile,Model model) {
		String fName=mFile.getOriginalFilename();
		model.addAttribute("title", fName);
		
		try {
		mFile.transferTo(new File("C:\\Users\\rlatj\\Downloads\\Spring\\sts-bundle\\sts-3.9.11.RELEASE\\workspace\\mimi\\src\\main\\webapp\\WEB-INF\\image\\"+fName));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("업로드 완료");

		return "home";*/

	//}
}
