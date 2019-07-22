package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.lookup.ImplicitNullAnnotationVerifier;

import util.*;

public class studentServlet extends HttpServlet {

	private DataUtil du=new DataUtil();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Uri=request.getRequestURI();
		if(Uri.contains("addtolist")){//添加至列表
			
			String bno=request.getParameter("id");
			String account=request.getParameter("account");
			addToList(request, response,bno,account);
		
		}else if(Uri.contains("searchborrow")){
		
			String account=request.getParameter("account");
			getBorrowList(request, response, account);;
		
		}else if (Uri.contains("searchreturn")) {
			
			String account=request.getParameter("account");
			getReturnList(request, response, account);
			
		}else if (Uri.contains("searchlist")){//查看列表
			
			gotoPath(request, response, "WEB-INF/student/list.jsp");
		
		}else if (Uri.contains("borrow")){//确认借阅
		
			String list=request.getParameter("list");
			String account=request.getParameter("account");
			String type=request.getParameter("type");
			borrowBook(request, response, list,account,type);
		
		}else if(Uri.contains("return")){
			
			String account=request.getParameter("account");
			String list=request.getParameter("list");
			returnBook(request, response, account, list);
			
		}else if(Uri.contains("remove")){//移出列表
		
			String list=request.getParameter("list");
			String account=request.getParameter("account");
			removeItem(request, response, list, account);
		
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String searchName=request.getParameter("bname");
		String path="WEB-INF/student/index.jsp";
		
		String tableName="book";
		String[] field=new String[]{"bno","bname","bauthor","bpub","bstatus"};
		String condition="bname='"+searchName+"'";
		
		Vector<String[]> vec=du.getData(tableName, field, condition);
		request.setAttribute("data", vec);
		
		gotoPath(request, response, path);
	}
	
	public void addToList(HttpServletRequest request, HttpServletResponse response,String bno,String account) 
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		Map<String, Vector<String[]>> map=(Map<String, Vector<String[]>>)session.getAttribute("list");
		PageUtil pu = null;
		int pageNo = 1;
		
		Vector<String[]> list=map.get(account);
		
		/*添加借书列表信息 session域*/
		String tableName="book";
		String[] field=new String[]{"bno","bname","bauthor","bpub"};
		String condition = "bno='"+bno+"'";
		Vector<String[]> vec=du.getData(tableName, field, condition);
		String[] data=vec.get(0);
		list.add(data);
		map.put(account, list);
		
		/*将对应图书的状态改变*/
		field=new String[]{"bstatus"};
		String[] value=new String[]{"0"};
		du.updateData(tableName, field, value, condition);
		
		session.setAttribute("account", account);
		session.setAttribute("list",map);
		
		tableName="book";
		field=new String[]{"bno","bname","bauthor","bpub","bstatus"}; 
		condition=null;
		pu = du.getPage(tableName, field, condition, pageNo, 4);
		pu.setPageNo(pageNo);
		request.setAttribute("page", pu);
		
		gotoPath(request, response, "WEB-INF/student/index.jsp");
		
	}
	
	private void borrowBook(HttpServletRequest request,HttpServletResponse response,String list,String account,String type)
			throws ServletException,IOException{
		HttpSession session=request.getSession();
		HashMap<String, Vector<String[]>> map=(HashMap<String, Vector<String[]>>)session.getAttribute("list");
		Vector<String[]> blist=map.get(account);//获取该用户的借书列表
		if(type.equals("all")){//借阅列表中全部图书
			String tableName="borrow";
			String[] field=new String[]{"sno","bno","borrow_date"};
			
			String tableName1="book";
			String[] field1=new String[]{"bstatus"};
			String[] value1=new String[]{"0"};
			for (String[] book : blist) {
				String bno=book[0];
				int time=Fn.time();
				String[] value=new String[]{account,bno,time+"",0+""};
				du.addData(tableName, field, value);
				
				/*图书状态改变*/
				String condition="bno='"+bno+"'";
				du.updateData(tableName1, field1, value1, condition);
			}
			blist.clear();//清空借书列表
			map.put(account, blist);//与空列表重新建立映射
		}else{//借阅单本图书
			String tableName="borrow";
			String[] field=new String[]{"sno","bno","borrow_date"};
			
			String tableName1="book";
			String[] field1=new String[]{"bstatus"};
			String[] value1=new String[]{"0"};
			
			int time=Fn.time();
			String[] value=new String[]{account,list,time+""};
			du.addData(tableName, field, value);
			
			String condition="bno='"+list+"'";
			du.updateData(tableName1, field1, value1, condition);
			
			Iterator<String[]> it=blist.iterator();
			while(it.hasNext()){
				String[] strs=it.next();
				if(strs[0].equals(list)){
					it.remove();
				}
			}
			map.put(account, blist);
		}
		
		String tableName="book";
		String[] field=new String[]{"bno","bname","bauthor","bpub","bstatus"}; 
		String condition=null;
		int pageNo=1;
		PageUtil pu = du.getPage(tableName, field, condition, pageNo, 4);
		pu.setPageNo(pageNo);
		request.setAttribute("page", pu);
		gotoPath(request, response,"WEB-INF/student/index.jsp");
	}

	private void returnBook (HttpServletRequest request,HttpServletResponse response,String account,String list)
			throws ServletException,IOException{
		/*添加至return表*/
		String tableName="returnbook";
		String[] field=new String[]{"sno","bno","return_date"};
		int time=Fn.time();
		String[] value=new String[]{account,list,time+""};
		du.addData(tableName, field, value);
		
		/*图书状态改变*/
		tableName="book";
		String[] field1=new String[]{"bstatus"};
		String[] value1=new String[]{"1"};
		String condition="bno='"+ list+"'";
		du.updateData(tableName, field1, value1, condition);
		
		/*borrow中状态改变*/
		tableName="borrow";
		String[] field2=new String[]{"status"};
		String[] value2=new String[]{"1"};
		condition="bno='"+list+"' and sno='"+account+"'";
		du.updateData(tableName, field2, value2, condition);
		
		String tableName2="book";
		String[] field3=new String[]{"borrow.bno","bname","bauthor","bpub","borrow_date"};
		String joinCondition=" borrow.bno=book.bno ";
		String condition1="sno='"+account+"' and status=0";
		Vector<String[]> vec=du.getData(tableName, tableName2, field3, joinCondition, condition1);
		
		for (String[] data : vec) {
			int timestamp = Integer.parseInt(data[4]);
			data[4] = Fn.date(timestamp, "yyyy-MM-dd HH:mm:ss");
		}
		
		request.setAttribute("data", vec);
		gotoPath(request, response, "WEB-INF/student/borrow.jsp");
	}
	
	private void gotoPath(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	private void removeItem(HttpServletRequest request,HttpServletResponse response,String list,String account)
			throws ServletException, IOException{
		HttpSession session=request.getSession();
		HashMap<String, Vector<String[]>> map=(HashMap<String, Vector<String[]>>) session.getAttribute("list");
		Vector<String[]> blist=map.get(account);
		
		Iterator<String[]> it=blist.iterator();
		while(it.hasNext()){
			String[] strs=it.next();
			if(strs[0].equals(list)){
				it.remove();
			}
		}
		map.put(account, blist);
		
		String tableName1="book";
		String[] field1=new String[]{"bstatus"};
		String[] value1=new String[]{"1"};
		String condition="bno='"+list+"'";
		du.updateData(tableName1, field1, value1, condition);
		
		request.setAttribute("data", initData());
		gotoPath(request, response, "WEB-INF/student/list.jsp");
	}
	
	static Vector<String[]> initData(){
		DataUtil du=new DataUtil();
		String tableName="book";
		String[] field=new String[]{"bno","bname","bauthor","bpub","bstatus"}; 
		String condition=null;
		Vector<String[]> data=du.getData(tableName, field, condition);
		return data;
	}

	private void getBorrowList(HttpServletRequest request,HttpServletResponse response,String account)
			throws IOException,ServletException{
		String tableName1 = "borrow";
		String tableName2 = "book";
		String[] field=new String[]{"borrow.bno","bname","bauthor","bpub","borrow_date"};
		String joinCondition=" borrow.bno=book.bno ";
		String condition="sno='"+account+"' and status=0";
		Vector<String[]> vec=du.getData(tableName1,tableName2, field, joinCondition,condition);//vec 借书列表
		
		for (String[] data : vec) {
			int timestamp = Integer.parseInt(data[4]);
			data[4] = Fn.date(timestamp, "yyyy-MM-dd HH:mm:ss");
		}
		
		request.setAttribute("data", vec);
		request.setAttribute("account", account);
		gotoPath(request, response, "WEB-INF/student/borrow.jsp");
	}
	
	private void getReturnList(HttpServletRequest request,HttpServletResponse response,String account)
			throws ServletException,IOException{
		String tableName1="returnbook";
		String tableName2="book";
		String[] field=new String[]{"returnbook.bno","bname","bauthor","bpub","return_date"};
		String joinCondition=" returnbook.bno=book.bno ";
		String condition="sno='"+account+"'";
		Vector<String[]> vec=du.getData(tableName1,tableName2, field, joinCondition,condition);//vec 还书列表
		
		for (String[] data : vec) {
			int timestamp = Integer.parseInt(data[4]);
			data[4] = Fn.date(timestamp, "yyyy-MM-dd HH:mm:ss");
		}
		
		request.setAttribute("data", vec);
		request.setAttribute("account", account);
		gotoPath(request, response, "WEB-INF/student/return.jsp");
	}
}
