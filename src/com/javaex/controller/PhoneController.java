package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhoneController extends HttpServlet {
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//컨트롤러 테스트
		System.out.println("controller");
		
		//파라미터의 action 값을 읽어서 업무 구분
		String action = request.getParameter("action");
		System.out.println(action);
		
		RequestDispatcher rd;
		PhoneDao phoneDao = new PhoneDao();
		
		if("list".equals(action)) {
			//action = list
			System.out.println("리스트 처리");
			
			//리스트 출력 처리
			
			List<PersonVo> personList = phoneDao.getPersonList();
			
			//html을 작성해야함 -> 엄청복잡함 -> jsp가 편함
			
			//데이터 전달
			request.setAttribute("pList", personList);
			//jsp에 포워드 시킴
			rd = request.getRequestDispatcher("./WEB-INF/list.jsp");
			rd.forward(request, response);
			
		}else if("wform".equals(action)) {
			//action = wform
			System.out.println("등록 폼 처리");
		
			rd = request.getRequestDispatcher("./WEB-INF/writeForm.jsp");
			rd.forward(request, response);
			
			
		}else if("insert".equals(action)) {
			//action = insert
			System.out.println("전화번호 저장");
			//파라미터 3개 값
			//personVo로 묶음
			PersonVo personVo = new PersonVo(request.getParameter("name"), request.getParameter("hp"), request.getParameter("company")); 
			
			//Dao 저장
			phoneDao.personInsert(personVo);
			//리다이렉트
			response.sendRedirect("/phonebook2/pbc?action=list");
			
			//포워드 - 개념적으로 틀림 
			//rd = request.getRequestDispatcher("./pbc?action=list");
			//rd.forward(request, response);
			
		}else if("delete".equals(action)) {
			System.out.println("전화번호 삭제");
			//파라미터 no 값으로 삭제
			phoneDao.personDelete(Integer.parseInt(request.getParameter("id")));
			
			//리다이렉트
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		}else if("uform".equals(action)) {
			System.out.println("전화번호 수정폼");
			
			PersonVo personVo = phoneDao.getPerson(Integer.parseInt(request.getParameter("id"))); 
			request.setAttribute("pVo", personVo);
			
			//포워드 
			rd = request.getRequestDispatcher("./WEB-INF/updateForm.jsp");
			rd.forward(request, response);
			
		}else if("update".equals(action)) {
			System.out.println("전화번호 수정");
			
			//파라미터 4개 값
			//personVo로 묶음
			PersonVo personVo = new PersonVo( Integer.parseInt(request.getParameter("id")), request.getParameter("name"), request.getParameter("hp"), request.getParameter("company")); 
			 
			//Dao 수정
			phoneDao.personUpdate(personVo);
			request.setAttribute("pVo", personVo);
			
			//리다이렉트
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		}else {
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}

}
