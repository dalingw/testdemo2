package dkx.web.servlet;

import dao.Conn;
import domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class Login extends HttpServlet {

    String name=null;
    String password = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         req.setCharacterEncoding("utf-8");
         name = req.getParameter("username");
         password = req.getParameter("password");
         System.out.println(password);
         System.out.println(name);
         Conn conn =new Conn();
         User user = conn.selmsg(req,resp);
         if(user.getUsername()==null){
            req.getRequestDispatcher("/fail").forward(req,resp);
        }
        else {
            req.setAttribute("user",user);
             HttpSession httpSession = req.getSession(true);
             httpSession.setAttribute("name",user.getUsername());
             req.getRequestDispatcher("/success").forward(req,resp);
        }

//        System.out.println("demo1被访问。。。。");
//        req.setAttribute("name","zhangsan");
//        //转发到servletDemo2
//        req.getRequestDispatcher("/demo2").forward(req,resp);
//        ServletContext  servletContext =req.getServletContext();
//        System.out.println(servletContext);
    }

}
