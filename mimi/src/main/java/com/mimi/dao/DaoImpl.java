package com.mimi.dao;



import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mimi.dao.Dao;
import com.mimi.vo.Vo;


@Repository
public class DaoImpl implements Dao {
	@Inject
	SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.mimi.dao.Dao";

	@Override
	public void insert(Vo vo) {
		sqlSession.insert(NAMESPACE + ".insert", vo);

	}

	
	


	}
