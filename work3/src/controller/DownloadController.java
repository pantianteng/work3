package controller;

import dao.DownloadDao;
import vo.Download;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@WebServlet(urlPatterns = "/Download.do")
public class DownloadController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String strId = request.getParameter("id");
        int id = Integer.parseInt(strId);

        Download download = DownloadDao.findDownloadById(id);
        String path = request.getServletContext().getRealPath(download.getPath());
        String fileName = path.substring(path.lastIndexOf("\\")+1);

       response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(fileName,"utf-8"));

        InputStream inputStream = new FileInputStream(path);
        int len;

        byte[] buffer = new byte[1024];
        ServletOutputStream outputStream = response.getOutputStream();
        while((len = inputStream.read(buffer))!=-1){
            outputStream.write(buffer,0,len);
        }
        inputStream.close();
        outputStream.close();
    }
}
