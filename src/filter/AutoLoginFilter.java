package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import model.User;
import util.DataUtil;


public class AutoLoginFilter implements Filter {

	private DataUtil du=new DataUtil();
	public AutoLoginFilter() {
        // TODO Auto-generated constructor stub
    }
    
    public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest re, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request=(HttpServletRequest)re;
		Cookie[] cookies=request.getCookies();
		String autoLogin=null;
		
		for(int i=0; cookies!=null && i < cookies.length;i++){
			if("autoLogin".equals(cookies[i].getName())){
				autoLogin=cookies[i].getValue();
			}
		}
		
		if(autoLogin!=null){
			String[] msg=autoLogin.split("-");
			String account=msg[0];
			String pwd=msg[1];
			String id=msg[2];
			
			String tableName;
			String condition;
			
			if(id=="1"){//Ñ§Éú
				tableName="student";
				condition="sno='"+account+"' and spsd="+pwd;
			}else{
				tableName="manager";
				condition="mname='"+account+"' and mpwd="+pwd;
			}
			
			if(du.CheckLogin(tableName, condition)){
				User user=new User();
				user.setAccount(account);
				user.setId(id);
				user.setPwd(pwd);
				request.getSession().setAttribute("user", user);
			}
		}
		
		chain.doFilter(request, response);
	}

	
	
}
