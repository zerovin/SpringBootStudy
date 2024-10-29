package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sist.web.entity.*;
import java.util.*;
@Repository
public interface BoardDAO extends JpaRepository<BoardEntity, Integer>{
	public BoardEntity findByNo(int no); //상세보기
	// SELECT no, name, subject, content, regdate, hit FROM board WHERE no=?
	// insert(save()), update(save()), delete(delete()), count()
	@Query(value="SELECT no, name, subject, content, date_format(regdate, '%Y-%m-%d') as regdate, hit "
			+ "FROM board ORDER BY no DESC "
			+ "LIMIT :start, 10", nativeQuery=true) //nativeQuery=true 작성한 SQL문장 그대로 수행
	public List<BoardData> boardListData(@Param("start") int start);
	// 단점 - SQL문장이 직접적으로 보이지 않아 가독성은 낮음
	// 장점 - 적은 코딩으로 사용 가능
	// 우리나라는 원하는 SQL작성 가능한 MyBatis 선호
}
