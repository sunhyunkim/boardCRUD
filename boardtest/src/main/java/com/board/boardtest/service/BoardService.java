package com.board.boardtest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.boardtest.dao.BoardDao;
import com.board.boardtest.dto.BoardDto;
import com.board.boardtest.form.BoardForm;
 

@Service
public class BoardService {
 
    @Autowired
    private BoardDao boardDao;
 
    public List<BoardDto> getBoardList(BoardForm boardForm) throws Exception {
 
        return boardDao.getBoardList(boardForm);
    }
}

