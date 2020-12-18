package filter;

import dao.UserDao;
import vo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AutoLoginFilter")
public class AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();

        //System.out.println(request.getServletPath());

        boolean tag1 = false;
        boolean tag2 = false;
        String cookieUserName = "";
        String cookiePassword = "";

        if(cookies != null){
            for(Cookie c : cookies){
                String name = c.getName();//获取Cookie名称
                if("userName".equals(name)){
                    cookieUserName = c.getValue();//获取Cookie的值
                    tag1 = true;
                }
                if("password".equals(name)){
                    cookiePassword  = c.getValue();//获取Cookie的值
                    tag2 = true;
                }
            }

        }




        if(tag1 && tag2){ //如果找到匹配的Cookies

            System.out.println("用户名：" + cookieUserName);
            System.out.println("密码：" + cookiePassword);

            UserDao userDao = new UserDao();
            User user = userDao.findUserByUserName(cookieUserName);
            if(cookiePassword.equals(user.getPassword())){  //校验密码
                session.setAttribute("currentUserName",user.getUserName());
                request.getRequestDispatcher("main.jsp").forward(req,resp);
            }
        }else {
            chain.doFilter(req,resp);
        }
        chain.doFilter(req,resp); //无论如何最后都放行
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
