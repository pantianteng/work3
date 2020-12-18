package controller;

import com.google.gson.Gson;
import dao.UserDao;
import vo.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


/**
 * Servlet implementation class LoginController
 */

@WebServlet(urlPatterns = "/AjaxLoginCheck.do")
public class AjaxLoginCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//设置编码格式
		request.setCharacterEncoding("utf-8");

		//1.按照表单的各元素的name属性值获取各请求参数值
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String vcode = request.getParameter("vcode");
		String autoLogin = request.getParameter("autoLogin");
		System.out.println("checkBox的值是"+autoLogin);

		//2.获取HttpSession对象
		HttpSession session = request.getSession();
		//取出CreateVerifyImageController中存放的验证码字符串
		String saveVcode = (String) session.getAttribute("verifyCode");
		//存放返回信息的Map
		Map<String,Object> map = new HashMap<String, Object>();

		//比较输入的验证码和随机生成的验证码是否相同
		if(!vcode.equalsIgnoreCase(saveVcode)){ //验证码有误
			//在map中存放返回数据
			map.put("code",1);
			map.put("info","验证码不正确！");
		} else{ //验证码正确
			UserDao userDao = new UserDao();
			User user = userDao.findUserByUserName(userName);
			if(user == null) { //用户名不存在
				map.put("code",2);
				map.put("info","用户名不存在！");
			}else{ //用户名存在
				if(!password.equals(user.getPassword())) { //密码不正确
					map.put("code",3);
					map.put("info","密码不正确！");
				}else{ //密码正确
					session.setAttribute("currentUser",user);
					session.setAttribute("currentUserName",user.getUserName());
					Cookie cookieUserName = new Cookie("userName", userName);
					Cookie cookiePassword = new Cookie("password", password);
					if(autoLogin.equals("true")){ //勾选了“一周免登陆”
						cookieUserName.setMaxAge(60 * 60 * 24 * 7);
						cookiePassword.setMaxAge(60 * 60 * 24 * 7);
					}else{
						cookieUserName.setMaxAge(0);
						cookiePassword.setMaxAge(0);
					}
					response.addCookie(cookieUserName);
					response.addCookie(cookiePassword);
					map.put("code",0);
					map.put("info","登陆成功！");
				}
			}
		}


		//调用谷歌的Gson库将map类型转换为json字符
		String jsonStr = new Gson().toJson(map);
		//字符流输出字符串
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		}
	}


