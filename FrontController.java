package com.pattern;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Map<keyŸ��, valueŸ��>
	// keyŸ�� -> string���� why? -> ������� ��û�� ������ "/inset.do"�� ���� �ĺ����� Ȱ���ؼ� ��û�� ���� ����
	// "/insert.do"��û�� ������ InsertService��ü�� ����
	// "/update.do"��û�� ������ UpdateService��ü�� ����
	private Map<String, Command>map;
	
	@Override
	public void init() throws ServletException{
		map = new HashMap<String, Command>();
		
		//map.put(��û�ĺ���, �ĺ����� ���õ� ��ü);
		map.put("/insert.do", new InsertService());
		map.put("/update.do", new UpdateService());
		map.put("/delete.do", new DeleteService());
		map.put("/select.do", new SelectService());
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// FrontController����
		// -> ������� ��� ��õ���� �� ������ ������ �� �ְ� �ϴ� ����
		// -> URLMapping�� ��θ� "*.do"�� ����
		// -> ������������ ��� ��û�� .do�� �پ�� �ϳ��� �������� ���� ��.
		/*
		 * ����
		 * 1) �� ���� �߾�����ȭ �Ǹ鼭 ������ ������� �ϳ��� ����� ������ �����
		 *    ��� ����� ����� �� ���� �ȴٴ� �������� �߻�-> ��ɺ��� �Ϲ� Ŭ�������� �и�
		 * */
		
		// �Ϲ�Ŭ���� vs ����
		// : HttpServlet�� ��ӹ޾Ҵ����� ���� ����
		// : ���������� ������ �Ÿ𸮸� ����ϴ� �Ϳ� ���� ����
		
		// �������̽��� Ȱ���ؼ� �Ϲ�Ŭ���� ����
		// : ���Ŀ� ������� ���񽺿� ���ؼ� ������ �޼ҵ带 ������ ����!
		
		// Command ����
		// : ������� ��û�� ���� ó���� �� �ִ� �Ϲ� Ŭ������ ����� �޼ҵ�� ������ �� �ֵ��� �ϴ� ����
		// : execute(HttpServeltRequest, HttpServletResponse) �߻� �޼ҵ� ����
		// : �Ϲ� Ŭ������ implements Ű���带 �̿��ؼ� �������̽��� ����
		
		
		
		// reqURI() : /DesignPattern/insert.do
		// contextPath() : /DesignPattern
		// command : /insert.do
//		String reqURI = request.getRequestURI();
//		String contextPath = request.getContextPath();
//		String command = reqURI.substring(contextPath.length());
		
		// ���ϰ��(���� ���� ���)�� ��ȯ
		String command= request.getServletPath();
		System.out.println("��û �ĺ��� >> "+command);
		
		Command com =map.get(command);
		com.execute(request, response);
//		if(command.equals("/insert.do")) {
//			// ������ �߰�
//			Command insert =  new InsertService();
//			insert.execute(request, response);
//		}else if(command.equals("/update.do")) {
//			// ������ ����
//			Command update = new UpdateService();
//			update.execute(request, response);
//		}else if(command.equals("/delete.do")) {
//			// ������ ����
//			Command delete = new DeleteService();
//			delete.execute(request, response);
//		}else if(command.equals("/select.do")) {
//			// ������ ��ȸ
//			Command select = new SelectService();
//			select.execute(request, response);
//		}
		
	}
	

}
