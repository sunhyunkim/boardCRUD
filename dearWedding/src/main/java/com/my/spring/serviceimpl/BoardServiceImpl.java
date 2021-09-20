package com.my.spring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.mapper.BoardMapper;
import com.my.spring.service.BoardService;
import com.wedding.dear.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private final BoardMapper boardmapper;

	
	public BoardServiceImpl(BoardMapper boardmapper) {
		
		this.boardmapper=boardmapper;
	}


	@Override
	public List<BoardVO> MapperCheck() {
		
		return null;
	}

	
	
}
