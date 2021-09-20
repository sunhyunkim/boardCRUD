package kr.koreait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import kr.koreait.springMVCBoard_DBCP.Constant;
import kr.koreait.vo.MvcBoardVO;

public class MvcBoardDAO {

//	DAO 클래스에서 DBCP Template을 사용하기 위해서 JdbcTemplate 클래스 타입의 객체를 선언한다.
	private JdbcTemplate template;
	
//	기본 생성자에서 데이터베이스와 연결한다.
	public MvcBoardDAO() {
//		DAO 클래스의 객체(bean)가 생성되는 순간 servlet-context.xml 파일에서 생성되서 컨트롤러에서 받아서 Constant 클래승 JdbcTemplate 타입의 
//		멤버 변수에 저장된 bean으로 초기화 시킨다.
		template = Constant.template;
	}
	
//	InsertService 클래스에서 호출되는 테이블에 저장할 메인글 데이터가 저장된 객체를 넘겨받고 insert sql 명령을 실행하는 메소드
//	insert, delete, update sql 명령을 실행하는 메소드의 인수로 넘어온 데이터는 변경되면 안되기 때문에 DBCP Template에서는 메소드의 인수를 선언
//	할 때 반드시 final을 붙여서 인수로 넘어온 데이터를 수정할 수 없도록 선언해야 한다.
	public void insert(final MvcBoardVO mvcBoardVO) {
		System.out.println("MvcBoardDAO 클래스의 insert() 메소드 실행");
		String sql = "insert into mvcboard (idx, name, subject, content, ref, lev, seq) " +
				"values (mvcboard_idx_seq.nextval, ?, ?, ?, mvcboard_idx_seq.currval, 0, 0)";
		template.update(sql, new PreparedStatementSetter() {
//			PreparedStatementSetter 인터페이스의 익명 객체를 구현하고 자동으로 @Override 되는 setValues() 메소드로 "?"를 채운다.
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, mvcBoardVO.getName());
				ps.setString(2, mvcBoardVO.getSubject());
				ps.setString(3, mvcBoardVO.getContent());
			}
		});
	}

//	SelectService 클래스에서 호출되는 테이브에 저장된 전체 글의 개수를 얻어오는 select sql 명령을 실행하는 메소드
	public int selectCount() {
		System.out.println("MvcBoardDAO 클래스의 selectCount() 메소드 실행");
		String sql = "select count(*) from mvcboard";
//		update() : 테이블의 내용이 갱신되는 sql 명령을 실행한다. => insert, delete, update
//		query() : 테이블의 내용이 갱신되지 않는 sql 명령을 실행한다. => select => 실행 결과가 여러건일 때 사용한다.
//		queryForObject() : 테이블의 내용이 갱신되지 않는 sql 명령을 실행한다. => select => 실행 결과가 1건일 때 사용한다.
//		queryForInt() : 테이블의 내용이 갱신되지 않는 sql 명령을 실행한다. => select => 실행 결과가 1건인 정수일 때 사용한다.
		return template.queryForInt(sql);
	}

//	SelectService 클래스에서 호출되는 브라우저 화면에 표시할 1페이지 분량의 시작 인덱스, 끝 인덱스가 저장된 HashMap 객체를 넘겨받고 테이브에서
//	1페이지 분량의 글을 얻어오는 select sql 명령을 실행하는 메소드
	public ArrayList<MvcBoardVO> selectList(HashMap<String, Integer> hmap) {
		System.out.println("MvcBoardDAO 클래스의 selectList() 메소드 실행");
//		DBCP Template을 사용하는 경우 select sql 명령에만 "?"를 사용할 수 없다. => "?" 자리에 데이터가 저장된 변수를 사용해야 한다.
		String sql = "select * from ("
				   + 	"select rownum rnum, AA.* from ("
				   + 		"select * from mvcboard order by ref desc, seq asc"
				   + 	") AA where rownum <= " + hmap.get("endNo")
				   + ") where rnum >= " + hmap.get("startNo");
		
//		select sql 명령 실행 결과 BeanPropertyRowMapper 클래스 생성자의 인수로 sql 명령 실행 결과를 기억할 클래스(VO)를 넘겨서 sql 명령 실행
//		결과를 저장시켜 리턴한다. => 리턴 타입으로 형변환이 필요하다.
//		테이블의 필드 이름과 VO 클래스의 멤버 변수 이름이 반드시 같아야 정상적으로 실행된다.
		return (ArrayList<MvcBoardVO>) template.query(sql, new BeanPropertyRowMapper(MvcBoardVO.class));
	}

//	IncrementService 클래스에서 조회수를 증가시킬 글번호를 넘겨받고 조회수를 증가시키는 update sql 명령을 실행하는 메소드
	public void increment(final int idx) {
		System.out.println("MvcBoardDAO 클래스의 increment() 메소드 실행");
		String sql = "update mvcboard set hit = hit + 1 where idx = ?";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, idx);
			}
		});
		
	}

//	ContentViewService 클래스에서 조회수를 증가시킨 글번호를 넘겨받고 조회수를 증가시킨 글 1건을 얻어오는 select sql 명령을 실행하는 메소드
	public MvcBoardVO selectByIdx(int idx) {
		System.out.println("MvcBoardDAO 클래스의 increment() 메소드 실행");
		String sql = "select * from mvcboard where idx = " + idx;
		return template.queryForObject(sql, new BeanPropertyRowMapper(MvcBoardVO.class));
	}

//	DeleteService 클래스에서 삭제할 글번호를 넘겨받고 글 1건을 삭제하는 delete sql 명령을 실행하는 메소드
	public void delete(final int idx) {
		System.out.println("MvcBoardDAO 클래스의 delete() 메소드 실행");
		String sql = "delete from mvcboard where idx = ?";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, idx);
			}
		});
	}

//	UpdateService 클래스에서 수정할 글번호와 데이터를 넘겨받고 글 1건을 수정하는 update sql 명령을 실행하는 메소드
	public void update(final int idx, final String subject, final String content) {
		System.out.println("MvcBoardDAO 클래스의 update() 메소드 실행");
		String sql = "update mvcboard set subject = ?, content = ? where idx = ?";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, subject);
				ps.setString(2, content);
				ps.setInt(3, idx);
			}
		});
	}

//	ReplyService 클래스에서 호출되는 글그룹과 글이 출력되는 순서가 저장된 HashMap 객체를 넘겨받고 조건에 만족하는 seq를 1씩 증가시키는
//	update sql 명령을 실행하는 메소드
	public void replyIncrement(final HashMap<String, Integer> hmap) {
		System.out.println("MvcBoardDAO 클래스의 replyIncrement() 메소드 실행");
		String sql = "update mvcboard set seq = seq + 1 where ref = ? and seq >= ?";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, hmap.get("ref"));
				ps.setInt(2, hmap.get("seq"));
			}
		});
	}

//	ReplyService 클래스에서 호출되는 답글이 저장된 객체를 넘겨받고 답글을 저장하는 insert sql 명령을 실행하는 메소드
	public void replyInsert(final MvcBoardVO mvcBoardVO) {
		System.out.println("MvcBoardDAO 클래스의 replyInsert() 메소드 실행");
		String sql = "insert into mvcboard (idx, name, subject, content, ref, lev, seq) values (mvcboard_idx_seq.nextval, ?, ?, ?, ?, ?, ?)";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, mvcBoardVO.getName());
				ps.setString(2, mvcBoardVO.getSubject());
				ps.setString(3, mvcBoardVO.getContent());
				ps.setInt(4, mvcBoardVO.getRef());
				ps.setInt(5, mvcBoardVO.getLev());
				ps.setInt(6, mvcBoardVO.getSeq());
			}
		});
	}
	
}
















