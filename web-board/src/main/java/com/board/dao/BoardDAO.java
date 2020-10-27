package com.board.dao;

import java.util.List;

import com.board.domain.BoardVO;

public interface BoardDAO {
	public List<BoardVO> list() throws Exception;
	
	// 게시물 작성
	public void write(BoardVO vo) throws Exception;
	// 게시물 조회
	public BoardVO view(int bno) throws Exception;
}
