package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DataUtil;
import util.PageUtil;
import model.*;

public class PageServlet extends HttpServlet {
	private DataUtil du = new DataUtil();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user =(User) request.getSession().getAttribute("user");
		
		
		String tableName="book";
		String[] field=new String[]{"bno","bname","bauthor","bpub","bstatus"}; 
		String condition=null;
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		PageUtil page = du.getPage(tableName, field, condition, pageNo, 4);
		page.setPageNo(pageNo);
		request.setAttribute("page", page);
		if(user != null){
			String id = user.getId();
			if(id.equals("1")){
				gotoPath(request, response, "WEB-INF/student/index.jsp");
			}else{
				gotoPath(request, response, "WEB-INF/manager/index.jsp");
			}
		}else{//ÓÎ¿Í·ÖÒ³
			gotoPath(request, response, "./visitor.jsp");
		}
		
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
