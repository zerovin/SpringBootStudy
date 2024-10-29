package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/*
 *  VO / DTO / Entity
 *  Entity - 다른 데이터 첨부불가, 테이블의 컬럼만 추가가능
 *           컬럼명과 동일 => 자동 SELECT/INSERT/UPDATE/DELET문장 생성
 *           검색 => findBy컬럼명()
 *                  ex) findByFno(int fno)
 *                      => WHERE fno= 
 *                         메소드로 SQL문장 처리
 *               => JPA (Hibernate) 자동 SQL문장 제작, 메소드 패턴
 *                  ex) findByAddressAndType(String address, String type)
 *                      =>address="" AND type=""
 *                  SQL문장 제작도 가능
 *                      
 */

/*
FNO int 
NAME text 
TYPE text 
PHONE text 
ADDRESS text 
SCORE double 
THEME text 
POSTER text 
IMAGES text 
TIME text 
PARKING text 
CONTENT text 
RDAYS text 
JJIMCOUNT int 
LIKECOUNT int 
HIT int 
REPLYCOUNT int 
 */
@Entity(name = "food_house")
@Data
public class FoodHouseEntity {
	@Id //sequence
	private int fno;
	private int jjimcount, likecount, hit, replycount;
	private String name, type, phone, address, theme, poster, images, time, parking, content, rdays;
	private double score;
}
