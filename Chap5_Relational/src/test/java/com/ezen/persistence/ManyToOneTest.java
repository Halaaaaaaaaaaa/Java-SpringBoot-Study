package com.ezen.persistence;

import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ezen.domain.Board;
import com.ezen.domain.Member;

@SpringBootTest
public class ManyToOneTest {

	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private MemberRepository memberRepo;
	
	@Test //데이터 insert 테스트
	@Disabled
	public void testManyToOne() {
		//1) member 데이터 생성
		Member member1 = Member.builder()
				.id("heysh")
				.password("1234")
				.name("김세현")
				.role("USER")
				.build();
		memberRepo.save(member1);
		
		Member member2 = Member.builder()
				.id("heysh2")
				.password("5678")
				.name("홍홍홍")
				.role("ADMIN")
				.build();
		memberRepo.save(member2);
		
		//2) 게시글 데이터 저장
		for(int i=1; i<=3; i++) {
			Board board = Board.builder()
					.member(member1)
					.title("김세현이 작성한 게시글" + i)
					.content("김세현 게시글 내용" + i)
					.createDate(new Date())
					.cnt(0)
					.build();
			boardRepo.save(board);		
		}
		
		for(int i=1; i<=3; i++) {
			Board board = Board.builder()
					.member(member2)
					.title("홍홍홍이 작성한 게시글" + i)
					.content("홍홍홍 게시글 내용" + i)
					.createDate(new Date())
					.cnt(0)
					.build();
			boardRepo.save(board);		
		}
	}
	
	
	//데이터 조회 테스트
	@Test
	public void testManyToOneSelect() {
		Board board = boardRepo.findById(4L).get();
		
		System.out.println(">>>>>> 게시글 정보");
		
		System.out.println(board);
		System.out.println("작성자: " + board.getMember().getName());
	}
	
	
	
	
	
}
