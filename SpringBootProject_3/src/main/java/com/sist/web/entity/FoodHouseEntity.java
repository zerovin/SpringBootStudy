package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

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
@Entity(name="food_house")
@Data
public class FoodHouseEntity {
	@Id // sequence
	private int fno;
	private int jjimcount, likecount, hit, replycount;
	private String name, type, phone, address, theme, poster, images, time, parking, content, rdays;
	private double score;
}
