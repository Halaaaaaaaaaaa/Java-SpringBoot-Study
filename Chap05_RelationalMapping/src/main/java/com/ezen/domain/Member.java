package com.ezen.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="boardList")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member {
	@Id //기본키 등록
	@Column(name="MEMBER_ID") //필드를 매핑할 열의 이름을 지정
	private String id;
	private String password;
	private String name;
	private String role;
	
	//여기서의 member는 Board.java에 선언된 @JoinColumn(name="MEMBER_ID") private Member member;
	//member 참조하겠다
	@OneToMany(mappedBy="member", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Board> boardList = new ArrayList<Board>();

	
	//@OneToMany(mappedBy="member", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	//@OneToMany(mappedBy="member", fetch=FetchType.LAZY)
}
