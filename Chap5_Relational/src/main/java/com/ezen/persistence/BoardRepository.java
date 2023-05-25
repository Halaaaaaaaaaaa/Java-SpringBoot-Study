package com.ezen.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ezen.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	// 제목을 조건으로 게시글 목록 조회 - keyword 등록 
	List<Board> findByTitle(String keyword);
	
	// 내용을 조건으로 게시글 목록 조회 - keyword를 like 조건으로 조회 
	List<Board> findByContentContaining(String keyword);
		
	// 제목 || 내용 조건으로 게시글 목록 조회 - keyword를 like 조건으로 조회
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	
	// 페이징 처리만 하는 메소드 추가
	//List<Board> findByTitleContaining(String keyword, Pageable paging);
	
	// 페이징 정보를 포함하는 페이징 처리 메소드
	Page<Board> findByTitleContaining(String keyword, Pageable paging);
	
	// 위치 기반 파라미터 사용
	//find로 된 메소드가 아닌 관계로 자동으로 컬럼이 생성되지 않기 때문에 @Query 사용
	@Query("SELECT b FROM Board b "
			+ "WHERE b.title LIKE %?1% "
			+ "ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest1(String keyword);
	
	// 이름 기반 파라미터 사용
	@Query("SELECT b FROM Board b "
			+ "WHERE b.title LIKE %:searchKeyword% "
			+ "ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest2(@Param("searchKeyword") String keyword);
	
	// 특정 컬럼 조회
	// 결과값은 Object[]로 출력
	@Query("SELECT b.seq, b.title, b.content "
			+ "FROM Board b "
			+ "WHERE b.title LIKE %:searchKeyword% "
			+ "ORDER BY b.seq DESC")
	List<Object[]> queryAnnotationTest3(@Param("searchKeyword") String keyword);
	
	// Native Query 사용
	@Query(value="SELECT seq, title, content FROM board "
			+ "WHERE title LIKE '%'||?1||'%' "
			+ "ORDER BY seq DESC", nativeQuery=true)
	List<Object[]> queryAnnotationTest4(String keyword);
	
	// Native Query 페이징 처리
	@Query(value="SELECT seq, title, content FROM board "
			+ "ORDER BY seq DESC", nativeQuery=true)
	List<Object[]> queryAnnotationTest5(Pageable paging);
	
	// Native Query 페이징 처리
	@Query(value="SELECT * FROM board "
			+ "WHERE title LIKE '%'||?1||'%'", nativeQuery=true)
	List<Object[]> queryAnnotationTest6(String keyword, Pageable paging);
	
}
