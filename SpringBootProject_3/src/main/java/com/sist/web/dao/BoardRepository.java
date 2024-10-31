package com.sist.web.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.Board;
import java.util.*;
@Repository
public interface BoardRepository extends ElasticsearchRepository<Board, Integer>{
	public List<Board> findByName(String name);
}
