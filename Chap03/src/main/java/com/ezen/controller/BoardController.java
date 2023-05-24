package com.ezen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.domain.BoardVO;
import com.ezen.service.BoardService;

@RestController
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	

	public BoardController() {
		System.out.println("===> BoardController 생성");
	}
	
	@GetMapping("/test")
	public String test(String name) {
		//BoardService hello()호출
		return boardService.hello(name);
	}
	
	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		//BoardService getBoard()호출
		return boardService.getBoard();
	}
	
	@GetMapping("/getBoardList")
	public List<BoardVO> getBoardList() {
		//BoardService getBoardList()호출
		return boardService.getBoardList();
	}
}
