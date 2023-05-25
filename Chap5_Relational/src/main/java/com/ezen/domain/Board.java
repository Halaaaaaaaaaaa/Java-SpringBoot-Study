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
@ToString
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
	
	/*@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", createDate=" + createDate + ", cnt=" + cnt + "]";
	}*/
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	private Member member;
	  
}
