package webapp.controller;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;

import webapp.beans.Calculator;
import webapp.model.Dept;
import webapp.model.Emp;

@Controller
@RequestMapping("/dept")
public class DeptController {
	
	static Log log = LogFactory.getLog(DeptController.class);
	
	@Autowired
	private Calendar calendar;
	
	@Autowired
	private Calculator calculator;
	
	@RequestMapping("/calendar")
	public void getCalendar() {
		log.info("##################");
		log.info("getCalendar()...  ");
		log.info("##################");
		log.info(calendar.getTime().toLocaleString());
		log.info(calculator.sum(100, 50));
	} // http://localhost:8080/sexam11/m/dept/calendar
	

	@Autowired
	LocaleResolver lr; // spring 다국어 처리
	
	@RequestMapping(value="/locale", method=RequestMethod.GET)
	public void changeLocale(String language, PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
		
		log.info("language = " + language);
		
		Locale l = new Locale(language);
		lr.setLocale(request, response, l);
		
		out.println("<h1> language = " + language + "<h1>");
	} // http://localhost:8080/sexam11/m/dept/locale?language=ko
	

	
	@RequestMapping(value="/message", method=RequestMethod.GET)
	public String getMessage() {
		
		return "/dept/message"; // message.jsp 호출
	} // http://localhost:8080/sexam11/m/dept/message

	
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String registerTest(Integer deptno, String dname, String loc) {
		
		log.info("deptno = " + deptno);
		log.info("dname = " + dname);
		log.info("loc = " + loc);
		
		return "/dept/registerform"; // registerform.jsp 호출
	} // http://localhost:8080/sexam11/m/dept/test?deptno=10&dname=sun&loc=seoul
	
	@RequestMapping(value="/test2", method=RequestMethod.GET)
	public String registerTest2(HttpServletRequest request, HttpServletResponse response) {
		
		String deptno = request.getParameter("deptno");
		String dname = request.getParameter("dname");
		String loc = request.getParameter("loc");
		
		log.info("deptno = " + deptno);
		log.info("dname = " + dname);
		log.info("loc = " + loc);
		
		return "/dept/registerform"; // registerform.jsp 호출
	} // http://localhost:8080/sexam11/m/dept/test2?deptno=10&dname=sun&loc=seoul
	
	@RequestMapping(value="/test3", method=RequestMethod.GET)
	public String registerTest3(Dept dept) {
		
		log.info("dept.deptno = " + dept.getDeptno());
		log.info("dept.dname = " + dept.getDname());
		log.info("dept.loc = " + dept.getLoc());
		
		List<Emp> emps = dept.getEmps();
		for (int i = 0; i < emps.size(); i++) {
			log.info("dept.emps[" + (i) +"].empno = " + emps.get(i).getEmpno());
			log.info("dept.emps[" + (i) + "].ename = " + emps.get(i).getEname());
		}
		
		return "/dept/registerform";
	} // http://localhost:8080/sexam11/m/dept/test3?deptno=10&dname=sun&loc=seoul&emps[0].empno=1010&emps[3].ename=홍길동
	
	
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String getRegister(Integer deptno, String dname, String loc) {

		return "/dept/registerform";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register() {
		
		
		return "/dept/register";
	}
	
}
