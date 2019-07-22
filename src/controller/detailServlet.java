package controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import util.DataUtil;

public class detailServlet extends HttpServlet {
	private DataUtil du = new DataUtil();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bno = request.getParameter("bno");
		String tableName = "book";
		String[] field=new String[]{"bno","bname","bauthor","bpub","bstatus","bsrc"}; 
		String condition=" bno= '"+bno+"'";
		Vector<String[]> vec = du.getData(tableName, field, condition);
		String[] data = vec.get(0);
		request.setAttribute("data", data);
		gotoPath(request, response, "./detail.jsp");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	private void gotoPath(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
