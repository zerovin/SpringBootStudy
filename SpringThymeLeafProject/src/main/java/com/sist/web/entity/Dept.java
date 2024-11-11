package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
//테이블명과 클래스명이 다른 경우에는 지정 @Entity(name="table명")
//테이블명과 클래스명이 같은 경우에는 지정X @Entity
//JPA단점 => JOIN, SubQuery가 자유롭게 사용 불가능
@Entity
@Data
public class Dept {
	@Id
	private int deptno;
	private String dname, loc;
}
