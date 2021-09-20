package com.wedding.dear;

public class BoardVO {
	/*
	 * CREATE TABLE TB_BOARD( TB_IDX number primary key, -- 게시글에 대한 고유값 TB_TITLE
	 * varchar2(30), -- 게시글 제목 TB_CONTENTS varchar2(100), -- 게시글 내용 TB_CONTENT_CNT
	 * number(1), -- 게시글 조회 수 TB_DEL_GB char(1), -- 삭제여부 TB_WRITE_DTM Date, -- 게시글
	 * 작성일 TB_WRITE_ID varchar2(10) -- 게시글 작성자 );
	 */

	public int TB_IDX;
	public String TB_TITLE;
	public String TB_CONTENTS;
	public int TB_CONTENT_CNT;
	public String TB_DEL_GB;
	public String TB_WRITE_DTM;
	public String TB_WRITE_ID;

	public int getTB_IDX() {
		return TB_IDX;
	}

	public void setTB_IDX(int tB_IDX) {
		TB_IDX = tB_IDX;
	}

	public String getTB_TITLE() {
		return TB_TITLE;
	}

	public void setTB_TITLE(String tB_TITLE) {
		TB_TITLE = tB_TITLE;
	}

	public String getTB_CONTENTS() {
		return TB_CONTENTS;
	}

	public void setTB_CONTENTS(String tB_CONTENTS) {
		TB_CONTENTS = tB_CONTENTS;
	}

	public int getTB_CONTENT_CNT() {
		return TB_CONTENT_CNT;
	}

	public void setTB_CONTENT_CNT(int tB_CONTENT_CNT) {
		TB_CONTENT_CNT = tB_CONTENT_CNT;
	}

	public String getTB_DEL_GB() {
		return TB_DEL_GB;
	}

	public void setTB_DEL_GB(String tB_DEL_GB) {
		TB_DEL_GB = tB_DEL_GB;
	}

	public String getTB_WRITE_DTM() {
		return TB_WRITE_DTM;
	}

	public void setTB_WRITE_DTM(String tB_WRITE_DTM) {
		TB_WRITE_DTM = tB_WRITE_DTM;
	}

	public String getTB_WRITE_ID() {
		return TB_WRITE_ID;
	}

	public void setTB_WRITE_ID(String tB_WRITE_ID) {
		TB_WRITE_ID = tB_WRITE_ID;
	}

}
