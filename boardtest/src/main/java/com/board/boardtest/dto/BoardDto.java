package com.board.boardtest.dto;

import java.util.Date;

public class BoardDto {
 
    int TB_IDX;
    String TB_TITLE;
    String TB_CONTESTS;
    int TB_CONTENTS_CNT;
    String TB_DEL_GB;
    String TB_WRITE_DTM;
    String TB_WRITE_ID;
 
    public int TB_IDX() {
        return TB_IDX;
    }
 
    public String TB_TITLE() {
        return TB_TITLE;
    }
    
    public String TB_CONTESTS() {
        return TB_CONTESTS;
    }
    
    public int TB_CONTENTS_CNT() {
        return TB_CONTENTS_CNT;
    }
 
    public String TB_DEL_GB() {
        return TB_DEL_GB;
    }
    
    public String TB_WRITE_DTM() {
        return TB_WRITE_DTM;
    }
    
    public String TB_WRITE_ID() {
        return TB_WRITE_ID;
    }
   
}
