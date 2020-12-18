package controller;

import dao.DownloadDao;
import vo.Download;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/GetDownloadList.do")
public class GetDownloadListController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        ArrayList<Download> downloadList = DownloadDao.getDownloadList();

        request.setAttribute("downloadList",downloadList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("download.jsp");
        requestDispatcher.forward(request,response);
    }
}
