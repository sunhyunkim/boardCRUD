package com.board.boardtest.dao;

import java.util.List;

import javax.annotation.Resource;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.boardtest.dto.BoardDto;
import com.board.boardtest.form.BoardForm;
 

@Repository
public class BoardDao {
 
    @Resource(name = "sqlSession")
    private SqlSession sqlSession;
 
    private static final String NAMESPACE = "com.board.boardtest.boardMapper";
 
    public List<BoardDto> getBoardList(BoardForm boardForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".getBoardList");
    }
}



