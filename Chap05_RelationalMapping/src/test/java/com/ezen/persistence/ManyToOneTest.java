package com.ezen.persistence;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
	@Disabled
	public void testManyToOneSelect() {
		Board board = boardRepo.findById(4L).get();
		
		System.out.println(">>>>>> 게시글 정보");
		
		System.out.println(board);
		System.out.println("작성자: " + board.getMember().getName());
	}
	
	
	//조회 테스트(다대일 양방향 테스트) 
	@Transactional
	@Test
	@Disabled
	public void testTwoWaySelect() {
		//회원 
		//Member member = memberRepo.findById("member1").get();
		//위와 같은 코드로 .get() 사용하면 데이터가 없을 경우 null이 나올 수 있기 때문에 Optional 객체로 생성
		Optional<Member> result = memberRepo.findById("heysh");
		
		List<Board> list = result.get().getBoardList();
		
		System.out.println(">>> 회원명: " + result.get().getName());
		
		//회원이 작성한 글
		for(Board board : list) {
			System.out.println(board.getTitle() + " : ");
			System.out.println(board.getTitle());
			System.out.println();
		}
		
	}
	
	
	@Test //데이터 insert 테스트//@Disabled
	public void testManyToOneInsertCascade() {
		
		//1) member 데이터 생성
		Member member1 = new Member();
			member1.setId("heysh3");
			member1.setPassword("9876");
			member1.setName("랄랄랄");
			member1.setRole("USER");
				
		
		Member member2 = new Member();
			member2.setId("heysh4");
			member2.setPassword("5432");
			member2.setName("바바바");
			member2.setRole("ADMIN");	
		
		//2) 게시글 데이터 저장
		for(int i=1; i<=3; i++) {
			Board board = new Board();
				board.setMember(member1);
				board.setTitle("랄랄랄이 등록한 게시글 " + i);
				board.setContent("랄랄랄이 등록한 게시글 내용 " + i);
				board.setCreateDate(new Date());
				board.setCnt(0);
		}
		memberRepo.save(member1);
		
		for(int i=1; i<=3; i++) {
			Board board = new Board();
				board.setMember(member2);
				board.setTitle("바바바가 등록한 게시글 " + i);
				board.setContent("바바바가 등록한 게시글 내용 " + i);
				board.setCreateDate(new Date());
				board.setCnt(0);	
		}
		memberRepo.save(member2);
	}
	
	
	//member 계정 삭제
	@Test
	@Disabled
	public void testCascadeDelete() {
		memberRepo.deleteById("heysh2");
	}
	
	
	
}
