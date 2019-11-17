package com.flipkart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GenericServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String hiddenVal=req.getParameter("hidden");
		HttpSession hs=req.getSession();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		if(hiddenVal.equals("1"))
		{
			String name=req.getParameter("name");
			String email=req.getParameter("email");
			String mob=req.getParameter("mob");
			String addr=req.getParameter("addr");
			String gender=req.getParameter("Gender");
			String pwd=req.getParameter("pwd");
			
			
			hs.setAttribute("cname", name);
			hs.setAttribute("cemail", email);
			hs.setAttribute("cmob", mob);
			hs.setAttribute("caddr", addr);
			hs.setAttribute("sex", gender);
			hs.setAttribute("cpwd", pwd);
			
			String qry="insert into naanu.register values(?,?,?,?,?,?)";
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","bnvh");
				pstmt=con.prepareStatement(qry);
				
				pstmt.setString(1, name);
				pstmt.setString(2, email);
				//pstmt.setInt(3, Integer.parseInt(mob));
				pstmt.setLong(3, Long.parseLong(mob));
				pstmt.setString(4, addr);
				pstmt.setString(5, gender);
				pstmt.setString(6, pwd);
			
				PrintWriter out=resp.getWriter();
				
				int i=pstmt.executeUpdate();
				if(i==1)
				{
					out.print("<html><head><title>Thanks for registering</title></head><body bgcolor='orange'><h1>Registration done! now you can login</h1><h2>Click <a href='./login.html'><button>Login</button></a> here to login</h2></body></html>");
				}
				else
				{
					System.out.println("Registration failed");
				}
				
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(hiddenVal.equals("2"))
		{
			String uname=req.getParameter("uname");
			String upwd=req.getParameter("upwd");
			
			hs.setAttribute("vname", uname);
			hs.setAttribute("vpwd", upwd);
			
			
			String name=(String)hs.getAttribute("cname");
			String email=(String)hs.getAttribute("cemail");
			String addr=(String)hs.getAttribute("caddr");
			
			hs.setAttribute("daddr", addr);
			
			String qry="select * from naanu.register where name=? and pwd=?";
			
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","bnvh");
				
				pstmt=con.prepareStatement(qry);
				
				pstmt.setString(1, uname);
				pstmt.setString(2, upwd);
				
				PrintWriter out=resp.getWriter();
				
				ResultSet rs=pstmt.executeQuery();
				if(rs.next())
				{
					resp.sendRedirect("./order.html");
				}
				else
				{
					RequestDispatcher rd=req.getRequestDispatcher("./login.html");
					rd.include(req, resp);
					out.print("Invalid username or password");
				}
			}
			catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(hiddenVal.equals("3"))
		{
			int redmi=15000;
			int samsung=10000;
			int vivo=13000;
			int realme=12000;
			int price;
			
			int fastrack=5000;
			int sonata=4000;
			int fosil=3000;
			int rolex=6000;
			
			int woodland=5000;
			int bata=4000;
			int addidas=3000;
			int rebook=2000;
			
			
			String mobile=req.getParameter("mobile");
			String watch=req.getParameter("watch");
			String shoe=req.getParameter("shoe");
			System.out.println(mobile + " ===> "+watch);
			
			String qtymobile=req.getParameter("qty1");
			String qtywatch=req.getParameter("qty2");
			String qtyshoe=req.getParameter("qty3");
			System.out.println(qtymobile + "====> "+qtywatch +" ===> "+qtyshoe);
			
			Integer qtymob=Integer.parseInt(qtymobile);
			Integer qtywat=Integer.parseInt(qtywatch);
			Integer qtysho=Integer.parseInt(qtyshoe);
			
			PrintWriter out=resp.getWriter();
			String addr=(String)hs.getAttribute("daddr");
			String name=(String)hs.getAttribute("vname");
			System.out.println(addr);
			
			

			if(mobile.equals("Select Mobile"))
			{
				System.out.println("Nigga");
			}
			if(mobile.equals("Redmi"))
			{
				price=redmi*qtymob;
				out.print("<html><head><title>Order placed</title></head><body><h3>Hi "+name+" Here is your Order"+"   "+mobile+"   "+" Cost is"+price+" Your Order will be delivered to "+addr+"</h3></body></html>");
			}
			if(mobile.equals("Samsung"))
			{
				price=samsung*qtymob;
				out.print("<html><head><title>Order placed</title></head><body><h3>Hi "+name+" Here Is your order"+mobile+"Cost Is"+price+"Your Order will be delivered to "+addr+" </h3></body></html>");
			}
			if(mobile.equals("Vivo"))
			{
				price=vivo*qtymob;
				out.print("<html><head><title>Order placed</title></head><body><h3>Hi "+name+" Here is Your order"+mobile+" Cost is"+price+"Your Order will be delivered to "+addr+"</h3></body></html>");
			}
			if(mobile.equals("Realme"))
			{
				price=realme*qtymob;
				out.print("<html><head><title>Order placed</title></head><body><h3>Hi "+name+" Here is your Order "+mobile+" Cost is"+price+"Your Order will be delivered to "+addr+" </h3></body></html>");
			}
			
			
			
			if(watch.equals("Select Watches"))
			{
				
			}
			if(watch.equals("Fastrack"))
			{
				price=fastrack*qtywat;
				out.print("<html><head><title>Order placed</title></head><body><h3>Hi "+name+" Here is your Order "+watch+"Cost is"+price+"Your Order will be delivered to "+addr+" </h3></body></html>");
			}
			if(watch.equals("Sonata"))
			{
				price=sonata*qtywat;
				out.print("<html><head><title>Order placed</title></head><body><h3>Hi "+name+"Here is your Order "+watch+" Cost is "+price+"Your Order will be delivered to "+addr+"</h3></body></html>");
			}
			if(watch.equals("Fosil"))
			{
				price=fosil*qtywat;
				out.print("<html><head><title>Order placed</title></head><body><h3>Hi "+name+" Here Is your Order "+watch+" Cost is"+price+"Your Order will be delivered to "+addr+"</h3></body></html>");
			}
			if(watch.equals("Rolex"))
			{
				price=rolex*qtywat;
				out.print("<html><head><title>Order placed</title></head><body><h3>Hi "+name+" Here is your Order "+watch+" Cost is"+price+"Your Order will be delivered to "+addr+" </h3></body></html>");
			}
			
			
			if(shoe.equals("Select Shoes"))
			{
				
			}
			if(shoe.equals("Woodland"))
			{
				price=woodland*qtysho;
				out.print("<html><head><title>Order placed</title></head><body><h3>Hi "+name+" Here is your Order\t "+shoe+"Cost is"+price+" Your Order will be delivered to "+addr+"</h3></body></html>");
			}
			if(shoe.equals("Bata"))
			{
				price=bata*qtysho;
				out.print("<html><head><title>Order placed</title></head><body><h3>Hi "+name+" Here is your Order\t "+shoe+"Cost is"+price+"Your Order will be delivered to "+addr+"</h3></body></html>");
			}
			if(shoe.equals("Addidas"))
			{
				price=addidas*qtysho;
				out.print("<html><head><title>Order placed</title></head><body><h3>Hi "+name+" Here is your order\t "+shoe+" Cost is."+price+"Your Order will be delivered to "+addr+" </h3></body></html>");
			}
			if(shoe.equals("Rebook"))
			{
				price=rebook*qtysho;
				out.print("<html><head><title>Order placed</title></head><body><h3>Hi "+name+" Here is your Order \t"+shoe+"Cost is."+price+"Your Order will be delivered to "+addr+" </h3></body></html>");
			}
		}
	}
}
