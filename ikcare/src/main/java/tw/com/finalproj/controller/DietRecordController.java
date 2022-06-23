package tw.com.finalproj.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.finalproj.service.FoodDataRepositorService;
import tw.com.finalproj.service.MembersService;
import tw.com.finalproj.service.domain.FoodDataBean;

@Controller
public class DietRecordController {

	@Autowired
	MembersService membersService;
	@Autowired
	FoodDataRepositorService foodDataRepositorService;

	public void init(WebDataBinder webDataBinder) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor editor1 = new CustomDateEditor(sFormat, true);
		webDataBinder.registerCustomEditor(java.util.Date.class, editor1);
	}
//
//	@RequestMapping(path = { "/pages/dietfindfood" })
//	public String SearchFoodData(Model model, String foodname, HttpSession httpSession) {
//		// 接收資料
//		// 驗證資料
//
//		if (foodname == null || foodname.length() == 0) {
//			return "/pages/dietRecord";
//		} else {
//			List<FoodDataBean> foodDatas = foodDataRepositorService.select(foodname);
//			System.err.println(foodDatas);
//			if (foodDatas != null && foodDatas.size() != 0) {
//
//				model.addAttribute("foodDatas", foodDatas);
//			}
//		}
//
//		return "/pages/dietRecord";
//
//	}
//
//	@RequestMapping(path = { "/pages/createdetail" })
//	public String createdetail(int exchangenumber, String meal, int sampleid, String samplename, HttpSession session) {
//		System.out.println(exchangenumber + "克" + meal + "餐點名稱:" + samplename + "他的ID" + sampleid);
//		// 接收資料
//		// 驗證資料
//		List<FoodDataBean> breakfast = new ArrayList<FoodDataBean>();
//		if(session.getAttribute("breakfast")!=null) {
//			breakfast = (List<FoodDataBean>) session.getAttribute("breakfast");
//			if (breakfast != null) {
//				for (FoodDataBean x : breakfast) {
//					System.out.println(x.getSamplename());
//				}
//				session.removeAttribute("breakfast");
//			}
//		}
//
////
//		if (exchangenumber == 0) {
//			return "/pages/dietRecord";
//		}
//		FoodDataBean bean = foodDataRepositorService.select(sampleid);
//		switch (meal) {
//		case "早餐": {
////			bean.setServing(null);
//
//			return "/pages/dietRecord";
//		}
//		case "午餐": {
//			break;
//		}
//		case "晚餐": {
//			break;
//		}
//		case "點心": {
//			break;
//		}
//
//		}
//		return "/pages/dietRecord";
//	}
	@GetMapping("/pages/dietfindfood/{searchname}")
	public @ResponseBody List<FoodDataBean> SearchFoodData(@PathVariable(name = "searchname") String searchname){
		System.out.println(searchname);
		List<FoodDataBean> beans = foodDataRepositorService.select(searchname);
		System.err.println(beans);
		return beans;
	}
	@GetMapping("/pages/insertfood/{insertid}")
	public @ResponseBody FoodDataBean insertFood(@PathVariable(name = "insertid")int insertid) {
		System.out.println(insertid);
		return foodDataRepositorService.select(insertid);
	}
}

