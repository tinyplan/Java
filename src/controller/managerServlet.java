package controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DataUtil;
/*
 * 选择请求跳转功能 Servlet，实现指定操作于managerDoServlet
 */
public class managerServlet extends HttpServlet {

	private DataUtil du=new DataUtil();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}		
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String option=request.getParameter("option");
		String path;
		if(option.equals("search")){
			String tableName="book";
			String[] field=new String[]{"bno","bname","bauthor","bpub","bstatus"}; 
			String condition=null;
			Vector<String[]> data=du.getData(tableName, field, condition);
			request.setAttribute("data", data);
			path="/WEB-INF/manager/msearch.jsp";
		}else{
			path="WEB-INF/manager/"+option+".jsp";
		}
		gotoPath(request, response, path);
		
	}
	
	private void gotoPath(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
