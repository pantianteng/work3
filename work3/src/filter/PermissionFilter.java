package filter;

import dao.ResourceDao;
import vo.Resource;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(filterName = "PermissionFilter")
public class PermissionFilter implements Filter {
    private  String notCheckPath;//不要过滤的请求地址，从web.xml文件读取

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //将ServletRequest类型参数转换为HttpServletRequest类型
        HttpServletRequest request = (HttpServletRequest) req;
        String path = request.getServletPath();//获取请求的URL-Pattern地址("xxx.do或xxx.jsp")
        System.out.println("请求地址URL-Pattern：" + path);

        if (!notCheckPath.contains(path)) { //若请求的地址不在 不需要过滤的列表 范围内，则需要判断是否已经登录
            HttpSession session = request.getSession();
            if (session.getAttribute("currentUser") == null) { //未登录
                session.setAttribute("blockInfo","您只是游客，请先登录！");
                request.getRequestDispatcher("block.jsp").forward(req,resp);
            }else { //已经登录，验证是否有权限访问请求的资源
                String currentUserName = session.getAttribute("currentUserName").toString();
                ArrayList<Resource> resourcesList = ResourceDao.findResourceListByUserName(currentUserName);
                boolean tag = false;
                for (Resource res : resourcesList) { //将 path 与 resourcesList中的Resource对象的url属性 循环比对
                    if (res.getUrl().equals(path)) {
                        tag = true;
                        break;
                    }
                }
                if (!tag){
                    session.setAttribute("blockInfo","您没有权限访问该资源！");
                    request.getRequestDispatcher("block.jsp").forward(req,resp);
                }else{
                    chain.doFilter(req,resp);
                }
            }
        }else{  //若请求的地址在 不需要过滤的列表 范围内，则直接放行
            chain.doFilter(req,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        //从web.xml配置文件的filter中读取名为notCheckPath的初始化值
        notCheckPath = config.getInitParameter("notCheckPath");
    }

}
