package dao;

import domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

public class Conn {

    public User selmsg(HttpServletRequest request, HttpServletResponse response) {
        Connection connection;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/sys?&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "root";
        String name = null;
        String pw = null;
        String id = null;
        User usermsg = new User();
        ResultSet selresultSet = null;
        Statement selstatement = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String sql = "select * from user where username=" + "'"
                    + request.getParameter("username") + "'" + " and password=" + "'"
                    + request.getParameter("password") + "'";
            System.out.println(sql);
            //   String sql = "select * from user where username='zhangsan' and password='123'";

            if (!connection.isClosed()) {
                System.out.println("数据库连接成功");
            }
            try {
                selstatement = connection.createStatement();
                selresultSet = selstatement.executeQuery(sql);
                //根据用户名密码查找用户信息
                while (selresultSet.next()) {
                    name = selresultSet.getString("username");
                    pw = selresultSet.getString("password");
                    id = selresultSet.getString("userId");
                    usermsg.setUsername(name);
                    usermsg.setPassword(pw);
                    usermsg.setUserId(id);
                    //System.out.println(usermsg);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //  connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("class not found");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库链接失败");
        }

        return usermsg;
    }

    public void insmsg(HttpServletRequest request,HttpServletResponse response) {
        Connection connection;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/sys?&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "root";
        String name ;
        ResultSet insselresultSet ;
        Statement insselstatement ;
        Statement insstatment ;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String insselect = "select * from user where username=" + "'" + request.getParameter("username") + "'";
            String ins = "insert into user (username,password) values ('" + request.getParameter("username") + "','" + request.getParameter("password") + "')";
            System.out.println(insselect);
            System.out.println(ins);
            if (!connection.isClosed()) {
                System.out.println("数据库连接成功");
            }
            try {
                insselstatement = connection.createStatement();
                insselresultSet = insselstatement.executeQuery(insselect);
                insstatment = connection.createStatement();
                insstatment.execute(ins);
                while (insselresultSet.next()) {
                    name = insselresultSet.getString("username");
                    System.out.println(name);
                    request.getRequestDispatcher("/existed").forward(request, response);
                }
                request.getRequestDispatcher("/inssuccess").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updmsg(HttpServletRequest request,HttpServletResponse response ){
        Connection connection;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/sys?&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "root";
        Statement updstatement = null ;
        HttpSession session = request.getSession(true);
        String u= (String) session.getAttribute("name");
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String upd = "update user set password=" +"'"+ request.getParameter("password") + "'"+" WHERE username = "+"'"+u+"'";
            System.out.println(upd);
            if (!connection.isClosed()) {
                System.out.println("数据库连接成功");
            }
            try {
                updstatement = connection.createStatement();
                updstatement.executeUpdate(upd);
               // int statment = updstatement;
                request.getRequestDispatcher("/updsuccess").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void del(HttpServletRequest request,HttpServletResponse response){
        Connection connection;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/sys?&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "root";
        String name ;
        ResultSet delresultSet ;
        Statement delstatment ;
        HttpSession session = request.getSession(true);
        String u= (String) session.getAttribute("name");
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String del = "DELETE FROM `user` WHERE username  = '"+u+"'";
            System.out.println(del);
            if (!connection.isClosed()) {
                System.out.println("数据库连接成功");
            }
            try {
                delstatment = connection.createStatement();
                delstatment.executeUpdate(del);
                request.getRequestDispatcher("/delsuccess").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}