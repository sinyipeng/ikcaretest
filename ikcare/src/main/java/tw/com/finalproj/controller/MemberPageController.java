package tw.com.finalproj.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.com.finalproj.service.MembersService;
import tw.com.finalproj.service.domain.MembersBean;

@Controller
public class MemberPageController extends HttpServlet {
	@Autowired
	MembersService membersService;

	@InitBinder
	public void init(WebDataBinder webDataBinder) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor editor1 = new CustomDateEditor(sFormat, true);
		webDataBinder.registerCustomEditor(java.util.Date.class, editor1);
	}

	@RequestMapping(path = { "/en-us/login" })
	public String handlerMethodLogin(Locale locale, Model model, String account, String password, HttpSession session) {
		// 接收資料
		// 驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);
		
		if(session.getAttribute("membersbean")!=null) {
			MembersBean memberssession = (MembersBean)session.getAttribute("membersbean");
			System.out.println(memberssession.getUseraccount());
			System.out.println("session裡面的資料" + session.getAttribute("membersbean"));
		}
		// 呼叫model
		MembersBean bean = membersService.login(account, password);
		// 根據model結果導向view
		if (bean == null) {
			errors.put("password", "帳號或密碼錯誤");
			return "/secure/ec_p3_login";
		} else {
			session.setAttribute("membersbean", bean);
			return "redirect:/";
		}
	}

	@RequestMapping(path = { "/en-us/signup" })
	public String handlerMethodSignup(Model model, MembersBean bean, BindingResult bindingResult, HttpSession session) {
		// 接收資料
		// 轉換資料
		System.out.println(bean);
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);
		if(bindingResult!=null && bindingResult.hasErrors()) {
			if(bindingResult.hasFieldErrors("")) {
				errors.put("birthday", "生日的格式必須是(YYYY-MM-DD)");
			}
		}
		if (errors != null && !errors.isEmpty()) {
			return "/secure/ec_p3_login";
		}
		bean.setUserlevel("一般會員");
		bean.setEmail(bean.getUseraccount());
		
		MembersBean check = membersService.insert(bean);
		if(check!=null&&check.getUseraccount().length()!=0) {
			session.setAttribute("membersbean", check);
			return "redirect:/";
		}else {
			return "/secure/ec_p3_login";
		}

	}
	@RequestMapping(path = {"/en-us/logout"})
	public String handlerMethodLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/secure/ec_p3_login";
	}
		
}
