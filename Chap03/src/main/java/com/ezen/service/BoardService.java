package com.ezen.service;

import java.util.*;

import com.ezen.domain.BoardVO;

public interface BoardService {

	String hello(String name);
	
	BoardVO getBoard();
	
	List<BoardVO> getBoardList();
}
