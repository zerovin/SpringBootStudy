package com.sist.web.vo;
import java.util.*;

import lombok.Data;
// Spring => Spring-Boot
// 최대한 XML 파일이 없게 어노테이션 이용
// FrameWork가 Spring-Boot에 포함
// 소스 최소화 : 서버 역할만 수행 => Front-End 별도 작성 html(ThymeLeaf), Vue, React
// Tomcat 내장
@Data
public class EmpVO {
	private int empno, sal;
	private String ename, job, dbday;
	private Date hiredate;
}
