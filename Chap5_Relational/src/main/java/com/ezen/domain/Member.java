package com.ezen.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
	
}
