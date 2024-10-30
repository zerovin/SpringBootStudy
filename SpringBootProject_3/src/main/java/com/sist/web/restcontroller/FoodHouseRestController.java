package com.sist.web.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.entity.*;
import com.sist.web.dao.*;
@RestController
@CrossOrigin(origins="*") // ip http://localhost:3000
//다른 포트번호의 접근 해제, 원래 port가 같은 경우에만 접근가능
public class FoodHouseRestController {
	@Autowired
	private FoodHouseDAO fDao;
	//자동 JSON변환 => Jackson => ObjectMapper
	
	@Autowired
	private RecipeDAO rDao;
	
	@Autowired
	private ChefDAO cDao;
	
	@GetMapping("food/main_react")
	public Map foodMainTopData(){
		Map map=new HashMap();
		List<FoodHouseVO> fList=fDao.foodHitTop9();
		List<RecipeEntity> rList=rDao.recipeMainData();
		ChefEntity vo=cDao.findByChef("핑콩이");
		
		map.put("fList", fList);
		map.put("rList", rList);
		map.put("cvo", vo);
		return map;
	}
}
