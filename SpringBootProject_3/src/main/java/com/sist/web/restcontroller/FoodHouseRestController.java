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
	
	@GetMapping("food/list_react")
	public Map food_list(int page) {
		Map map=new HashMap();
		int rowSize=12;
		int start=(page*rowSize)-rowSize;
		List<FoodHouseVO> list=fDao.foodListData(start);
		int count=(int)fDao.count();
		int totalpage=(int)(Math.ceil(count/(double)rowSize));
		final int BLOCK=10;
		int startpage=((page-1)/BLOCK*BLOCK)+1;
		int endpage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) {
			endpage=totalpage;
		}
		
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		// res.data = map
		return map;
	}
	
	@GetMapping("food/detail_react")
	public FoodHouseEntity food_detail(int fno) {
		FoodHouseEntity vo=fDao.findByfno(fno);
		vo.setHit(vo.getHit()+1); //조회수 증가 Cookie 저장불가
		fDao.save(vo); 
		vo=fDao.findByfno(fno);
		return vo;
	}
	
	@GetMapping("food/find_react")
	public Map food_find(int page, String address) {
		Map map=new HashMap();
		int rowSize=12;
		int start=(page*rowSize)-rowSize;
		List<FoodHouseVO> list=fDao.foodFindData(start, address);
		int totalpage=fDao.foodFindTotalPage(address);
		final int BLOCK=10;
		int startpage=((page-1)/BLOCK*BLOCK)+1;
		int endpage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) {
			endpage=totalpage;
		}
		
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		map.put("address", address);
		// res.data = map
		return map;
	}
}
