package com.ezen.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity implementation class for Entity: Board
 *
 */
@Getter
@Setter
@ToString(exclude="member") // @ToString() 실행 시 멤버 필드 제외(@ToString 주석처리 기능)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity //엔티티 클래스(데이터베이스 관련 객체)로 지정
public class Board {
	/*
	public Board() {
		super();
	}
	*/
	@GeneratedValue
	@Id //기본키 지정
	private long seq;
	private String title;
	//private String writer; //member 필드가 있으므로 필요 없음
	private String content;
	@Temporal(value=TemporalType.TIMESTAMP) //날짜 형식 지정
	private Date createDate;
	private int cnt;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID", nullable=false)
	private Member member;
	
	public void setMember(Member member) {
		//this.member는 위에 있는 전역변수, 뒤에 있는 member는 파라미터 변수
		this.member = member;
		
		member.getBoardList().add(this);
		
		//this.member는 전역변수를 나타내고, 뒤의 member는 파라미터 값을 나타낸다 
		//member.getBoardList().add(this)에서의 member는 전역변수가 가리키는 객체
	}
	  
}
