package com.sist.web.dao;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;
@Repository
public interface FoodHouseDAO extends JpaRepository<FoodHouseEntity, Integer>{
	// 9개의 데이터 => Main에 출력
	@Query(value="SELECT fno, name, poster, score, hit, jjimcount, type, content, theme "
			+ "FROM food_house ORDER BY hit DESC "
			+ "LIMIT 0, 9", nativeQuery=true)
	public List<FoodHouseVO> foodHitTop9();
	
	//SELECT * FROM food_house WHERE fno=? => 자동 SQL문장
	public FoodHouseEntity findByfno(int fno);
	
	@Query(value="SELECT fno, name, poster, score, hit, jjimcount, type, content, theme "
			+ "FROM food_house ORDER BY fno ASC "
			+ "LIMIT :start, 12", nativeQuery=true)
	// nativeQuery=true => 작성된 자체 SQL문장 수행 JPQL => Entity중심
	public List<FoodHouseVO> foodListData(@Param("start") int start);
	// count() => 총페이지 추출 함수
	// 이미 존재하는 메소드 이용 => 복잡한 쿼리(SELECT)는 작성
	// 메소드 패턴 이용 => SELECT에서 검색
	
	//검색 => LIKE '%'||#{}||'%' => CONCAT('%',:,'%')
	//JPA => JOIN
	@Query(value="SELECT fno, name, poster, score, hit, jjimcount, type, content, theme "
			+ "FROM food_house WHERE address LIKE CONCAT('%',:address,'%') ORDER BY fno ASC "
			+ "LIMIT :start, 12", nativeQuery=true)
	public List<FoodHouseVO> foodFindData(@Param("start") int start, @Param("address") String address);
	
	@Query(value="SELECT CEIL(COUNT(*)/12.0) "
			+ "FROM food_house "
			+ "WHERE address LIKE CONCAT('%',:address,'%')")
	public int foodFindTotalPage(@Param("address") String address);
}
