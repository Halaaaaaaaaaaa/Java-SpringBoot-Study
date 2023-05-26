package com.ezen.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.ezen.domain.Board;
import com.ezen.domain.QBoard;
import com.querydsl.core.BooleanBuilder;

@SpringBootTest
public class DynamicQueryTest {
	
	@Autowired
	private DynamicBoardRepository boardRepo;
	
	@Test
	public void testDynamicQuery() {
		//String searchCondition = "TITLE";
		//String searchKeyword = "위글위글7";
		//String searchCondition = "CONTENT";
		//String searchKeyword = "...7";
		String searchCondition = "WRITER";
		String searchKeyword = "망공";
		
		//동적인 조건 생성
		//BooleanBuilder: 쿼리문의 where절 이하의 조건을 생성해주는 객체
		//순서1) BooleanBuilder 생성
		BooleanBuilder builder = new BooleanBuilder();
		
		//순서1-2) Q클래스 생성
		QBoard qboard = QBoard.board;
		
		//순서2) searchCondition의 내용에 따라 동적인 쿼리 생성
			// like는 값이 부분적 일치인 경우 검사를 하지만 나오지만 
			// eq는 찾고자 하는 값과 완벽히 일치하는 값이여야 표출된다
		if(searchCondition.equals("TITLE")) {
			builder.and(qboard.title.like("%" + searchKeyword + "%"));
		} else if(searchCondition.equals("CONTENT")) {
			builder.and(qboard.content.like("%" + searchKeyword + "%"));
		} else if(searchCondition.equals("WRITER")) {
			//builder.and(qboard.writer.like("%" + searchKeyword + "%"));
			//builder.and(qboard.writer.eq(searchKeyword));
		}
		//순서3) pageable 생성
		Pageable page = PageRequest.of(0, 6);
		Page<Board> boardList = boardRepo.findAll(builder, page);
		
		//순서4) 출력
		System.out.println(">>>>>> 검색 결과");
		for(Board board : boardList.getContent()) {
			System.out.println(board.toString());
		}	
	}

	
	

}
