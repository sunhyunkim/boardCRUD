package com.my.spring.mapper;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.wedding.dear.BoardVO;


public interface BoardMapper {
	

	public List<BoardVO> viewAll();
	
}
