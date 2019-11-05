package dkx.web.servlet;

import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/success")
public class success extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getAttribute("user");
        //设置响应头content-type:告诉浏览器本次响应响应体数据的格式及编码方式
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("登陆成功，"+user.getUsername()+"欢迎你！");
       //获取字符输出流
        PrintWriter out = resp.getWriter();
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY>");
        out.println("<form action = \"/del\" method = \"post\">");
        out.print(" <a href=\"updpassword.html\">修改密码</a>");
        out.print(" <input type = \"submit\" value =\"删除用户 \">");
        out.println("  </BODY>");
        out.println("</form>");
        out.println("</HTML>");
        //最后要记得清空缓存区，并且关闭。
        out.flush();
        out.close();
    }
}
