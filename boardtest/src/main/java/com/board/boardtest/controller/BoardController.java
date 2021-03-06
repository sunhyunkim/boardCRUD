package com.board.boardtest.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.boardtest.dto.BoardDto;
import com.board.boardtest.form.BoardForm;
import com.board.boardtest.service.BoardService;

@Controller
@RequestMapping("/boardtest")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("/boardList")
	public String getBoardList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "boardtest/boardList";
	}

	@RequestMapping("/getBoardList")
	@ResponseBody
	public List<BoardDto> getBoardList(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm)
			throws Exception {

		List<BoardDto> boardList = boardService.getBoardList(boardForm);

		return boardList;
	}
}
