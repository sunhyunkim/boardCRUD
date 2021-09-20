package kr.koreait.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.koreait.vo.MvcBoardVO;

//	xml 파일의 sql 명령을 실행하는 인터페이스
public interface MybatisDAO {


//	mapper 인터페이스의 추상 메소드 형식은 resultType id(parameterType)과 같은 형태로 만들어 사용한다.
//	MybatisDAO 인터페이스의 추상 메소드 이름이 xml 파일의 sql 명령을 식별하는 id로 사용되고 추상 메소드의 인수로 지정된 데이터가 xml 파일의
//	sql 명령으로 전달된다.
	
	int selectCount();
	ArrayList<MvcBoardVO> selectList(HashMap<String, Integer> hmap);
//	sql 명령을 실행하는 xml 파일의 parameterType 속성에는 한 개의 자료형만 쓸 수 있는데 아래와 같이 여러개의 인수를 넘겨야 할 경우
//	인수로 넘어가는 데이터를 모두 멤버 변수로 가지고 있는 클래스 이름을 적어주면 된다.
	void insert(String name, String subject, String content);
	void increment(int idx);
	MvcBoardVO selectByIdx(int idx);
	void delete(int idx);
	void update(MvcBoardVO mvcBoardVO);
	void replyIncrement(HashMap<String, Integer> hmap);
	void replyInsert(MvcBoardVO mvcBoardVO);
	void insert(MvcBoardVO mvcBoardVO);
	
}






















