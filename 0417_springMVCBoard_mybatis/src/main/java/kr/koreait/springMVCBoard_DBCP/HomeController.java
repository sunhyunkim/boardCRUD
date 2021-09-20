package kr.koreait.springMVCBoard_DBCP;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.koreait.dao.MvcBoardDAO;
import kr.koreait.dao.MybatisDAO;
import kr.koreait.service.ContentViewService;
import kr.koreait.service.DeleteService;
import kr.koreait.service.IncrementService;
import kr.koreait.service.InsertService;
import kr.koreait.service.MvcBoardService;
import kr.koreait.service.ReplyService;
import kr.koreait.service.SelectService;
import kr.koreait.service.UpdateService;
import kr.koreait.vo.MvcBoardList;
import kr.koreait.vo.MvcBoardVO;

@Controller
public class HomeController {
	
//	여기부터
//	private JdbcTemplate jdbcTemplate;
//	
//	public JdbcTemplate getJdbcTemplate() {
//		return jdbcTemplate;
//	}
//	@Autowired
//	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//		Constant.template = jdbcTemplate;
//	}
//	여기까지 DBCP Template에서 사용하는 부분이므로 mybatis로 코드 변환이 완료되면 주석으로 처리한다.

//	servlet-context.xml 파일에서 생성한 mybatis bean을 사용하기 위해 SqlSession 인터페이스 객체를 선언한다.
//	servlet-context.xml 파일에서 생성한 mybatis bean을 자동으로 읽어와 SqlSession 인터페이스 객체에 넣어주도록 하기 위해 @Autowired 어노테이션으
//	붙여준다.
	@Autowired
	public SqlSession sqlSession;
	
//	프로젝트 최초 요청을 처리하는 메소드
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("프로젝트가 실행될 때 최초의 요청을 받는다.");
		return "redirect:list";
	}
	
//	글 입력폼(insert.jsp)을 호출하는 메소드
	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 insert() 메소드 실행");
		return "insert";
	}
	
//	입력 폼에 입력된 데이터를 테이블에 저장하고 브라우저에 출력할 1페이지 분량의 글을 얻어오는 컨트롤러의 메소드를 호출한다. => request로 받아서
//	Model 인터페이스 객체에 저장한다.
	@RequestMapping("/insertOK")
	public String insertOK(HttpServletRequest request, Model model, MvcBoardVO mvcBoardVO) {
		System.out.println("컨트롤러의 insertOK() 메소드 실행");
//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcBoardService service = ctx.getBean("insert", InsertService.class);
//		service.execute(model);
		
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		
//		HttpServletRequest 인터페이스 객체 request에서 insert.jsp에서 넘어온 데이터를 받는다.
//		String name = request.getParameter("name");
//		String subject = request.getParameter("subject");
//		String content = request.getParameter("content");
		
//		sql 명령을 실행한다.
//		xml 파일에서 데이터를 받을 때 param1, param2, param3, ... 형태로 받아야 한다.
//		mapper.insert(name, subject, content);
		
//		MvcBoardVO 클래스의 bean을 얻어온다.
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcBoardVO mvcBoardVO = ctx.getBean("mvcBoardVO", MvcBoardVO.class);
//		MvcBoardVO 클래스의 bean에 insert.jsp에서 request 객체로 넘어온 데이터를 저장한다.
//		mvcBoardVO.setName(name);
//		mvcBoardVO.setSubject(subject);
//		mvcBoardVO.setContent(content);
//		System.out.println(mvcBoardVO);
		
//		메인글을 저장하는 메소드를 실행한다.
		mapper.insert(mvcBoardVO);

		return "redirect:list";
	}

//	브라우저에 출력할 1페이지 분량의 글을 얻어오고 1페이지 분량의 글을 브라우저에 출력하는 페이지를 호출하는 메소드
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 list() 메소드 실행");
//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcBoardService service = ctx.getBean("select", SelectService.class);
//		service.execute(model);
		
//		mapper를 얻어온다. => MybatisDAO 인터페이스를 사용한다.
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		
//		브라우저 화면에 출력할 글의 개수를 정한다.
		int pageSize = 10;
//		컨트롤러에서 HttpServletRequest 인터페이스 객체에 저장되서 넘어온 화면에 표시할 페이지 번호를 받는다.
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) { }
//		테이블에 저장된 전체 글의 개수를 얻어온다.
		int totalCount = mapper.selectCount();
//		System.out.println(totalCount);

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		1페이지 분량의 글과 페이지 작업에 사용할 8개의 변수를 기억하는 MvcBoardList 클래스의 bean을 얻어온다.
		MvcBoardList mvcBoardList = ctx.getBean("mvcBoardList", MvcBoardList.class); // 기본 생성자로 생성된 bean
//		페이지 작업에 사용할 8개의 변수를 초기화시키는 메소드를 실행한다.
		mvcBoardList.initMvcBoardList(pageSize, totalCount, currentPage);

//		MvcBoardList 크래스의 1페이지 분량의 글을 기억하는 ArrayList에 1페이지 분량의 글을 테이블에서 얻어와서 넣어준다.
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", mvcBoardList.getStartNo());
		hmap.put("endNo", mvcBoardList.getEndNo());
		mvcBoardList.setMvcBoardList(mapper.selectList(hmap));
//		System.out.println(mvcBoardList);

//		list.jsp로 넘겨줄 데이터를 Model 인터페이스 객체에 넣어준다.
		model.addAttribute("mvcBoardList", mvcBoardList);

		return "list";
	}
	
//	조회수를 증가시키는 메소드
	@RequestMapping("/increment")
	public String increment(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 increment() 메소드 실행");
//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcBoardService service = ctx.getBean("increment", IncrementService.class);
//		service.execute(model);
		
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		
//		request 객체로 넘어온 조회수를 증가시킬 글번호를 받는다.		
		int idx = Integer.parseInt(request.getParameter("idx"));
//		조회수를 증가시키는 메소드를 실행한다.
		mapper.increment(idx);
		
//		조회수를 증가시킨 후 브라우저에 표시할 그 번호와 작업 후 돌아갈 페이지 번호를 Model 인터페이스 객체에 저장한다.
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		model.addAttribute("idx", idx);
		model.addAttribute("currentPage", currentPage);
		return "redirect:contentView";
	}
	
//	조회수를 증가시킨 글 한 건을 브라우저에 출력하기 위해 테이블에서 얻어오는 메소드
	@RequestMapping("/contentView")
	public String contentView(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 contentView() 메소드 실행");
//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcBoardService service = ctx.getBean("contentView", ContentViewService.class);
//		service.execute(model);
		
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		
//		request 객체로 넘어온 조회수를 증가시킨 글번호를 받는다.
		int idx = Integer.parseInt(request.getParameter("idx"));

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		조회수를 증가시킨 글 1건을 얻어와서 MvcBoardVO 클래스 객체에 저장한다.
		MvcBoardVO mvcBoardVO = ctx.getBean("mvcBoardVO", MvcBoardVO.class);
		mvcBoardVO = mapper.selectByIdx(idx);
//		System.out.println(mvcBoardVO);
		
//		브라우저에 출력할 글, 작업 후 돌아갈 페이지 번호, 줄바꿈 구현에 사용할 "\r\n"을 Model 인터페이스 객체에 저장한다.
		model.addAttribute("vo", mvcBoardVO);
		model.addAttribute("currentPage", Integer.parseInt(request.getParameter("currentPage")));
		model.addAttribute("enter", "\r\n");
		
		return "contentView";
	}
	
//	글 1건을 삭제하는 메소드
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 delete() 메소드 실행");
//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcBoardService service = ctx.getBean("delete", DeleteService.class);
//		service.execute(model);
		
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		
//		request 객체로 넘어온 삭제할 글번호를 받는다.
		int idx = Integer.parseInt(request.getParameter("idx"));
//		글 한 건을 삭제하는 메소드를 실행한다.
		mapper.delete(idx);
		
//		글 삭제 작업 후 돌아갈 페이지 번호를 Model 인터페이스 객체에 저장한다.
		model.addAttribute("currentPage", Integer.parseInt(request.getParameter("currentPage")));
		
		return "redirect:list";
	}
	
//	글 1건을 수정하는 메소드
	@RequestMapping("/update")
	public String update(HttpServletRequest request, Model model, MvcBoardVO mvcBoardVO) {
		System.out.println("컨트롤러의 update() 메소드 실행");
//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcBoardService service = ctx.getBean("update", UpdateService.class);
//		service.execute(model);
		
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		
//		커맨드 객체로 저장된 데이터로 글 한 건을 수정하는 메소드를 실행한다.
		mapper.update(mvcBoardVO);
		
//		글 수정 작업 후 돌아갈 페이지 번호를 Model 인터페이스 객체에 저장한다.
		model.addAttribute("currentPage", Integer.parseInt(request.getParameter("currentPage")));
		
		return "redirect:list";
	}
	
//	답글을 입력하기 위해서 브라우저 화면에 출력할 메인글을 얻어오고 답글을 입력하는 페이지를 호출하는 메소드
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 reply() 메소드 실행");
//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcBoardService service = ctx.getBean("contentView", ContentViewService.class);
//		service.execute(model);
		
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		
//		request 객체로 넘어온 질문글의 글번호를 받는다.
		int idx = Integer.parseInt(request.getParameter("idx"));

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		질문 글 1건을 얻어와서 MvcBoardVO 클래스 객체에 저장한다.
		MvcBoardVO mvcBoardVO = ctx.getBean("mvcBoardVO", MvcBoardVO.class);
		mvcBoardVO = mapper.selectByIdx(idx);
//		System.out.println(mvcBoardVO);
		
//		브라우저에 출력할 글, 작업 후 돌아갈 페이지 번호, 줄바꿈 구현에 사용할 "\r\n"을 Model 인터페이스 객체에 저장한다.
		model.addAttribute("vo", mvcBoardVO);
		model.addAttribute("currentPage", Integer.parseInt(request.getParameter("currentPage")));
		model.addAttribute("enter", "\r\n");
		
		return "reply";
	}
	
//	답글을 위치에 맞게 저장하는 메소드
	@RequestMapping("/replyInsert")
	public String replyInsert(HttpServletRequest request, Model model, MvcBoardVO mvcBoardVO) {
		System.out.println("컨트롤러의 replyInsert() 메소드 실행");
//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcBoardService service = ctx.getBean("reply", ReplyService.class);
//		service.execute(model);
		
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		
//		System.out.println(mvcBoardVO);
//		커맨드 객체로 받은 데이터에서 답글은 질문글 바로 아래에 위치해야 하므로 lev와 seq는 1씩 증가시켜 저장한다.
		mvcBoardVO.setLev(mvcBoardVO.getLev() + 1);
		mvcBoardVO.setSeq(mvcBoardVO.getSeq() + 1);
//		System.out.println(mvcBoardVO);

//		답글이 삽입될 위치를 정하기 위해 조건에 만족하는 seq를 1씩 증가시키는 메소드를 실행한다.
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("ref", mvcBoardVO.getRef());
		hmap.put("seq", mvcBoardVO.getSeq());
		mapper.replyIncrement(hmap);
		
//		답글을 저장하는 메소드를 실행한다.
		mapper.replyInsert(mvcBoardVO);
		
//		답글 저장 후 돌아갈 페이지 번호를 Model 인터페이스 객체에 저장한다.
		model.addAttribute("currentPage", Integer.parseInt(request.getParameter("currentPage")));

		return "redirect:list";
	}
	
}






















