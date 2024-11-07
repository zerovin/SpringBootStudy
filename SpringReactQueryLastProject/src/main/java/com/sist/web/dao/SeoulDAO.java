package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.entity.*;
@Repository
public interface SeoulDAO extends JpaRepository<SeoulEntity, Integer>{
	/*
	@Query(value="SELECT * FROM seoul_location "
			+ "ORDER BY no ASC "
			+ "LIMIT :start, 12", nativeQuery=true)
	public List<SeoulEntity> seoulListData(@Param("start") int start);
	*/
	
	public SeoulEntity findByNo(int no);
}
