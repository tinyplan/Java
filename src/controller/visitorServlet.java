package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.NEW;

import util.DataUtil;
import util.PageUtil;

public class visitorServlet extends HttpServlet {
	private DataUtil du = new DataUtil();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tableName;
		String condition;
		String path;
		int pageNo = 1;
		PageUtil pu = null;
		
		tableName = "book";
		String[] field=new String[]{"bno","bname","bauthor","bpub","bstatus"};
		condition = null;
		pu = du.getPage(tableName, field, condition, pageNo, 4);
		pu.setPageNo(pageNo);//进入时为页面1
		request.setAttribute("page", pu);
		gotoPath(request, response, "./visitor.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}
	private void gotoPath(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
