package com.sist.web.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;

@RestController
@CrossOrigin(origins="*")
public class FoodHouseRestController {
	@Autowired //스프링에서 메모리 할당이 된 경우에만 사용 가능
	private FoodHouseDAO fDao;
	/*
	 *	클래스는 반드시 메모리 할당 후에 사용 => new 결합성이 강한 프로그램
	 *	1. <bean> : 라이브러리 클래스를 메모리 할당하는 경우 => 공통으로 사용
	 *	   @Bean
	 *	2. Annotation 이용 : 개발자가 주로 사용하는 방식
	 *     @Component : 일반 클래스 => ~Manager : OpenApi
	 *     @Repository : 데이터베이스(Oracle/MySQL,MariaDB/ElasticSearch,MongoDB(NoSQL)) 연동 => DAO
	 *                   한개의 테이블 연동
	 *                   라이브러리 : MyBatis / JPA
	 *     @Service : DAO가 여러개인 경우 통합 => BI
	 *                ex) 게시판 / 댓글, 맛집 / 예약 / 찜하기
	 *     @Controller : 웹 파일 제어
	 *                   최근 Front / Back 분리해서 처리
	 *                       Front - React / Vue => Router
	 *                       Back - 데이터를 Json으로 변경해서 전송
	 *     @RestController : 다른 언어와 연동, JSON 전송
	 *                       SpringBoot <=> Kotlin, Flutter(Dart) - 모바일
	 *     @ControllerAdvice : 공통 예외처리
	 *     @RestControllerAdvice : 공통 예외 처리
	 *     
	 *     1.web.xml - 프레임워크 사용 => Spring
	 *                 연결 파일 => application_*.xml
	 *       server.xml - 경로 확인 => <Context> SpringFrameWork
	 *                               임베디드 tomcat 자체처리 SpringBoot
	 *     2.동작
	 *               요청 (.do, /)
	 *       사용자  ===============> DispatcherServlet =====> HandlerMapping
	 *                                                              |
	 *                                                 @Controller/ @RestController
	 *                                                              |
	 *                                                   사용자가 요청한 URI를 찾는다
	 *                                 @GetMapping / @PostMapping / @RequestMapping(spring 6.0에서 제외)
	 *                                 
	 *                               RestApi - Get(SELECT) / Post(INSERT) / Put(UPDATE) / Delete(DELETE)
	 *                                         return 전송 => JSON
	 *                                                       파일명 - Forward
	 *                                                              데이터 전송
	 *                                                       redirect - Redirect
	 *                                                                  이전 화면으로 이동
	 *                                                                  데이터 전송 불가
	 *	  * ?를 사용하지 않는다
	 *      => PathVariable
	 *         ex) board/list/{page}
	 *         
	 *      에러와 동시에 데이터 전송하는 방식
	 *      => ResponseEntity (실무)
	 *         react-query {isLoading, error, data, reflush:함수명}=useQuery()
	 */
	
	//맛집 목록
	@GetMapping("/food/list/{page}")
	public ResponseEntity<Map> food_list(@PathVariable("page") int page){
		Map map=new HashMap();
		try {
			int rowSize=12;
			int start=(rowSize*page)-rowSize;
			List<FoodHouseVO> fList=fDao.foodListData(start);
			int count=(int)fDao.count();
			int totalpage=(int)(Math.ceil(count/(double)rowSize));
			
			final int BLOCK=10;
			int startpage=((page-1)/BLOCK*BLOCK)+1;
			int endpage=((page-1)/BLOCK*BLOCK)+BLOCK;
			if(endpage>totalpage) {
				endpage=totalpage;
			}
			map.put("fList", fList);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startpage", startpage);
			map.put("endpage", endpage);
			//map {}로 받아야함
			/*
			 *	return
			 *  => Map {} 여러개를 동시에 모아서 전송
			 *  => List [{],{},{}...] 
			 *  => VO {}
			 */
		}catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			// 404, 500, 415, 405, 400
		}
		return new ResponseEntity<>(map, HttpStatus.OK); //200
	}
	
	@GetMapping("food/find/{page}/{address}")
	public ResponseEntity<Map> food_find(@PathVariable("page") int page, @PathVariable("address") String address){
		Map map=new HashMap();
		try {
			int rowSize=12;
			int start=(page-1)*rowSize;
			
			List<FoodHouseVO> fList=fDao.foodFindData(start, address);
			int totalpage=fDao.foodFindTotalPage(address);
			
			final int BLOCK=10;
			int startpage=((page-1)/BLOCK*BLOCK)+1;
			int endpage=((page-1)/BLOCK*BLOCK)+BLOCK;
			if(endpage>totalpage) {
				endpage=totalpage;
			}
			
			map.put("fList", fList);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startpage", startpage);
			map.put("endpage", endpage);
		}catch(Exception ex) {
			// {isLoading, isError, error, date}
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			/*
			 * 500 : 소스에러
			 * 404 : 파일이 없는 경우
			 * 400 : Bad Request => 데이터 전송이 틀린 경우
			 * 415 : 한글변환
			 * 403 : 접근 거부 
			 */
		}
		return new ResponseEntity<>(map, HttpStatus.OK); //정상수행
	}
}
