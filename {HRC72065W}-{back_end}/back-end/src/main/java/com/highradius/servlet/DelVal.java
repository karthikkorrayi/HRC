package com.highradius.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java. util. HashMap;
import com.google.gson.Gson;
/**
 * Servlet implementation class AddSer
 */

@WebServlet("/DelVal")
public class DelVal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelVal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		HashMap<Object,Object> Response = new HashMap<Object,Object>();
		String sl_no = request.getParameter("sl_no");
	
		
		Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/grey_goose","root","root");
		String sql = "DELETE FROM winter_internship WHERE sl_no in ("+ sl_no +")";
		PreparedStatement ps = con.prepareStatement(sql);
		if(ps.executeUpdate() > 0) {
			Response.put("deleted",true);
		}else {
			Response.put("deleted",false);
		}
		Gson gson = new Gson();
		String JSONresponse = gson.toJson(Response);
		setAccessControlHeaders(response);
		response.getWriter().append(JSONresponse);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setAccessControlHeaders(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.setHeader("Access-Control-Allow-Methods", "POST");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
