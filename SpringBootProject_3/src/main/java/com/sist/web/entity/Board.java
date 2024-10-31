package com.sist.web.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Id;
import lombok.Data;
@Document(indexName="reactboard")
@Data
public class Board {
	@org.springframework.data.annotation.Id
	private int no;
	private String name, subject, content, pwd, regdate;
	private int hit;
}
