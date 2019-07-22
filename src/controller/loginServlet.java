package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import util.*;
import model.*;

public class loginServlet extends HttpServlet {

	private DataUtil du=new DataUtil();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tableName;
		String condition;
		String path;
		int pageNo = 1;
		PageUtil pu = null;
		String saveCode = (String)request.getSession().getAttribute("checkCode");
		
		
		String account=request.getParameter("account");
		String pwd=request.getParameter("pwd");
		String id=request.getParameter("identity");
		String checkCode = request.getParameter("checkCode");
		if (checkCode.equals(saveCode)) {
//			Vector<String[]> vec=initData();//获得初始化数据
//			request.setAttribute("data", vec);
			
			Vector<String[]> list=new Vector<String[]>();
			Map<String, Vector<String[]>> map=new HashMap<String, Vector<String[]>>();
			map.put(account, list);//防止studentServlet中提取list时出现空指针
			/*用户身份类别验证*/
			if(id.equals("1")){//学生
				
				tableName="student";
				path="WEB-INF/student/index.jsp";
				condition="sno='"+account+"' and spwd="+pwd;//学生账户名为sno
				
			}else{
				tableName="manager";
				path="WEB-INF/manager/index.jsp";
				condition="mname='"+account+"' and mpwd="+pwd;//管理员账户名为mname
			}
			
			if(du.CheckLogin(tableName, condition)){
				User user=new User();
				//封装登录信息
				user.setAccount(account);
				user.setPwd(pwd);
				user.setId(id);
				
				tableName="book";
				String[] field=new String[]{"bno","bname","bauthor","bpub","bstatus"}; 
				condition=null;
				pu = du.getPage(tableName, field, condition, pageNo, 4);
				pu.setPageNo(pageNo);
				request.setAttribute("page", pu);
				
				HttpSession session=request.getSession();
				session.setAttribute("list", map);//session中事先存入图书列表
				session.setAttribute("user", user);//session中存入用户对象
				
				/*String autoLogin=request.getParameter("autoLogin");
				
				if(autoLogin!=null){
					String loginMsg=account+"-"+pwd+"-"+id;
					Cookie cookie=new Cookie("autoLogin", loginMsg);
					response.addCookie(cookie);
				}
				*/
				gotoPath(request, response, path);
			}else{
				request.setAttribute("status", "1");
				gotoPath(request, response, "./failToLogin.jsp");
			}
		}else{
			request.setAttribute("status", "0");
			gotoPath(request, response, "./failToLogin.jsp");
		}
	}
	
	private void gotoPath(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	static Vector<String[]> initData(){
		DataUtil du=new DataUtil();
		String tableName="book";
		String[] field=new String[]{"bno","bname","bauthor","bpub","bstatus"}; 
		String condition=null;
		Vector<String[]> data=du.getData(tableName, field, condition);
		return data;
	}
}
