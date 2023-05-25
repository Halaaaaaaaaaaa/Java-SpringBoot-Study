package com.ezen.persistence;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ezen.domain.Board;

@SpringBootTest
public class QueryMethodTest {

	@Autowired
	private BoardRepository boardRepo;
	
	//각 테스트 케이스 실행 전 반드시 수행
	//@BeforeEach
	public void dataPrepare() {
		System.out.println("===> dataPrepare() list data");
		
		for(int i=1; i<=200; i++) {
			Board board = new Board();
			
			board.setTitle("위글위글" + i);
			//board.setWriter("망공");
			board.setContent("온도니 씰룩" + i);
			board.setCreateDate(new Date());
			board.setCnt(0);
			
			boardRepo.save(board);
		}
	}
	
	@Test //제목을 조건으로 게시글 목록 조회
	@Disabled
	public void testFindByTitle() {
		System.out.println("===> testFindByTitle() list data");
		
		List<Board> boardList = boardRepo.findByTitle("네코펀치");
		
		System.out.println(">>> 조회 결과");
		for(Board board : boardList) {
			System.out.println(board.toString());
		}
	}
	
	@Test //내용을 조건으로 게시글 목록 조회
	@Disabled
	public void testByContentContaining() {
		System.out.println("===> testByContentContaining() list data");
		
		List<Board> boardList = boardRepo.findByContentContaining("게시글 내용");
		
		System.out.println(">>> 조회 결과");
		for(Board board : boardList) {
			System.out.println(board.toString());
		}
	}
	
	@Test //제목과 내용을 조건으로 게시글 목록 조회
	@Disabled
	public void testByTitleContainingOrContentContaining() {
		System.out.println("===> testByTitleContainingOrContentContaining() list data");
		
		List<Board> boardList = boardRepo.findByTitleContainingOrContentContaining("4", "13");
		
		System.out.println(">>> 조회 결과");
		for(Board board : boardList) {
			System.out.println(board.toString());
		}
	}
	
	/*
	@Test //페이징 처리만 하는 메소드
	public void testByTitleContaining() {
		System.out.println("===> findByTitleContaining() paging");
		
		//1)페이징 처리만 사용할 경우,
		//Pageable paging = PageRequest.of(0, 6);
		
		//2)페이징 처리 + 정렬 처리 추가
		Pageable paging = PageRequest.of(0, 6, Sort.Direction.DESC, "seq");
		
		List<Board> boardList = boardRepo.findByTitleContaining("위글위글9", paging);
		
		System.out.println(">>> 조회 결과");
		for(Board board : boardList) {
			System.out.println(board.toString());
		}
	}
	*/
	
	@Test // 페이징 정보를 포함하는 페이징 처리 메소드
	public void testByTitleContaining() {
		System.out.println("===> findByTitleContaining() paging");
		
		// 페이징 처리 + 정렬 처리 추가
		Pageable paging = PageRequest.of(0, 6, Sort.Direction.DESC, "seq");
		
		Page<Board> pageInfo = boardRepo.findByTitleContaining("위글위글9", paging);
		
		System.out.println(">>Page당 항목 수 : " + pageInfo.getSize());
		System.out.println(">>전체 Page 수   : " + pageInfo.getTotalPages());
		System.out.println(">>조회된 전체 항목 수 : " + pageInfo.getTotalElements());
		System.out.println(">>다음 페이지 여부    : " + pageInfo.hasNext());
		
		// 조회된 데이터 가져오기
		List<Board> boardList = pageInfo.getContent();
		
		System.out.println(">>> 조회 결과");
		for(Board board : boardList) {
			System.out.println(board.toString());
		}
	}
	
	
}
