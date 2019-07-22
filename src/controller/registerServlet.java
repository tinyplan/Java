package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DataUtil;
import util.Fn;
import model.student;

public class registerServlet extends HttpServlet {
	
	private DataUtil du=new DataUtil();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String status="";
		student stu=new student();
		
		String sno=request.getParameter("sno");
		String sname=request.getParameter("sname");
		String ssex=request.getParameter("gender");
		String sage=request.getParameter("sage");
		String spwd=request.getParameter("spwd");
		
		String tableName="student";
		String[] value=new String[]{sno,sname,ssex,sage,spwd};
		String[] field=new String[]{"sno","sname","ssex","sage","spwd"};
		
		if(sno==""||sname==""||sage==""||spwd==""){
			status="0";
		}else{
			status="1";
			stu.setSno(sno);
			stu.setSname(sname);
			stu.setSsex(ssex);
			stu.setSage(sage);
			stu.setSpsd(spwd);
			
			du.addData(tableName, field, value);
		}
		
		request.setAttribute("status", status);
		request.setAttribute("stu", stu);
		gotoPath(request, response, "register.jsp");
	}
	public void gotoPath(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	

}
