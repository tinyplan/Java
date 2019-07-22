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
import util.PageUtil;
/*
 * 实现add,update,delete操作;准备数据到index主界面
 */
public class managerDoServlet extends HttpServlet {

	private DataUtil du=new DataUtil();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Uri=request.getRequestURI();
		String bno=request.getParameter("id");
		if(Uri.contains("update")){
			updateData(request, response, bno);
		}else if(Uri.contains("delete")){
			deleteData(request, response, bno);
		}else{
			gotoPath(request, response, "WEB-INF/manager/add.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation=request.getRequestURI();
		request.setCharacterEncoding("utf-8");
		
		String path="WEB-INF/manager/index.jsp";
		String tableName="book";
		if(operation.contains("add")){//添加
			String bno=request.getParameter("bno");
			String bname=request.getParameter("bname");
			String bauthor=request.getParameter("bauthor");
			String bpub=request.getParameter("bpub");
			String bstatus="1";
			
			String[] field=new String[]{"bno","bname","bauthor","bpub","bstatus"}; 
			String[] value=new String[]{bno,bname,bauthor,bpub,bstatus};
			
			du.addData(tableName, field, value);
		}else if(operation.contains("update")){//更新
			String bno=request.getParameter("bno");
			
			String bname=request.getParameter("bname");
			String bauthor=request.getParameter("bauthor");
			String bpub=request.getParameter("bpub");
			
			String[] field=new String[]{"bname","bauthor","bpub"}; 
			String[] value=new String[]{bname,bauthor,bpub};
			String condition="bno="+bno;
			
			du.updateData(tableName, field, value, condition);
			
		}else{//删除
			String bname=request.getParameter("bname");
			String condition="bname='"+bname+"'";
			
			du.deleteData(tableName, condition);
		}
		
		tableName="book";
		String[] field=new String[]{"bno","bname","bauthor","bpub","bstatus"}; 
		String condition=null;
		int pageNo = 1;
		PageUtil pu = du.getPage(tableName, field, condition, pageNo, 4);
		pu.setPageNo(pageNo);
		request.setAttribute("page", pu);
		gotoPath(request, response, path);
	}
	
	
	/*
	 * 初始数据准备
	 */
	static Vector<String[]> initData(){
		DataUtil du=new DataUtil();
		String tableName="book";
		String[] field=new String[]{"bno","bname","bauthor","bpub","bstatus"}; 
		String condition=null;
		Vector<String[]> data=du.getData(tableName, field, condition);
		return data;
	}

	private void gotoPath(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	private void updateData(HttpServletRequest request, HttpServletResponse response, String bno)
			throws ServletException, IOException {
		
		String tableName="book";
		String[] field=new String[]{"bno","bname","bauthor","bpub","bstatus"};
		String condition="bno="+bno;
		
		Vector<String[]> vec=du.getData(tableName, field, condition);
		
		String[] data=vec.get(0);
		
		request.setAttribute("data",data);
		
		gotoPath(request, response, "WEB-INF/manager/update.jsp");
	}
	
	private void deleteData(HttpServletRequest request, HttpServletResponse response, String bno)
			throws ServletException, IOException{
		String tableName="book";
		String condition="bno="+bno;
		du.deleteData(tableName, condition);
		
		tableName="book";
		String[] field=new String[]{"bno","bname","bauthor","bpub","bstatus"}; 
		condition=null;
		int pageNo = 1;
		PageUtil pu = du.getPage(tableName, field, condition, pageNo, 4);
		pu.setPageNo(pageNo);
		request.setAttribute("page", pu);
		
		gotoPath(request, response, "WEB-INF/manager/index.jsp");
		
	}
	
}
