package com.sist.web.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.entity.*;
import com.sist.web.dao.*;
@RestController
public class BoardRestController {
	@Autowired
	private BoardRepository bDao;
	
	@GetMapping("board/insert_elastic")
	public String board_insert() {
		Board b=new Board();
		b.setName("심청이");
		b.setSubject("Elastic 연습");
		b.setContent("Elastic 연습");
		b.setPwd("1234");
		b.setRegdate("2024-10-31");
		b.setHit(0);
		b.setNo((int)(bDao.count()+1));
		bDao.save(b);
		return "yes";
	}
}
