package com.sist.web.entity;
// SELECT 컬럼명, 컬럼명... => VO 인터페이스
// SELECT *
public interface FoodHouseVO {
	public int getFno();
	public String getName();
	public String getPoster();
	public int getHit();
	public int getJjimcount();
	public String getType();
	public double getScore();
	public String getContent();
	public String getTheme();
}
