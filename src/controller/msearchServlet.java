package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DataUtil;
import util.Fn;

public class msearchServlet extends HttpServlet {

	private DataUtil du=new DataUtil();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String bname=request.getParameter("bname");
		
		String tableName="book";
		String[] field=new String[]{"bno","bname","bauthor","bpub","bstatus"}; 
		String condition="bname='"+bname+"'";
		Vector<String[]> data=du.getData(tableName, field, condition);
		request.setAttribute("data", data);
		gotoPath(request, response, "/WEB-INF/manager/msearch.jsp");
		
	}
	
	private void gotoPath(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
