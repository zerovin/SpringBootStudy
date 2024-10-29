package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.sist.web.entity.FoodHouseData;
import com.sist.web.entity.FoodHouseEntity;
/*
 *  Mysql
 *  1. 페이징 => LIMIT 시작, 개수
 *  2. LIKE '%'||?||'%' => CONCAT('%',?,'%')
 *  3. DATE SYSDATE => DATETIME => now()
 *  4. NVL => insnull
 *  
 *  오라클      mysql(mariaDB) port 3306 driver동일
 *  NUMBER     int, double
 *  VARCHAR2   varchar
 *  CLOB       text
 *  DATE       datetime
 *  
 *  
 */
@Repository
public interface FoodHouseDAO extends JpaRepository<FoodHouseEntity, Integer>{
	// 목록
	@Query(value="SELECT fno, poster, name FROM food_house ORDER BY fno ASC "
			+ "LIMIT :start, 12", nativeQuery=true)
	public List<FoodHouseData> foodListData(@Param("start") int start);
	// 상세
	public FoodHouseEntity findByFno(int fno);
	// SELECT * FROM food_house WHERE fno=?
	// Hit 증가 = update(save())
	// 검색
	// CRUD => 게시판
	
}
