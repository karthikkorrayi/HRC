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
// import com.HighRadius.crud.Add;
import com.google.gson.Gson;
/**
 * Servlet implementation class AddSer
 */
	  
@WebServlet("/AddVal")
public class AddVal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddVal() {
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
		String business_code = request.getParameter("business_code");
		String cust_number = request.getParameter("cust_number");
		String clear_date = request.getParameter("clear_date");
		String buisness_year = request.getParameter("buisness_year");
		String doc_id = request.getParameter("doc_id");
		String document_create_date = request.getParameter("document_create_date");
		String due_in_date = request.getParameter("due_in_date");
		String posting_date = request.getParameter("posting_date");
		String invoice_currency = request.getParameter("invoice_currency");
		String posting_id = request.getParameter("posting_id");
		String total_open_amount = request.getParameter("total_open_amount");
		String baseline_create_date = request.getParameter("baseline_create_date");
		String cust_payment_terms = request.getParameter("cust_payment_terms");
		String invoice_id = request.getParameter("invoice_id");
		String document_type = request.getParameter("document_type");
		
	    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/grey_goose","root","root");
		String sql = "INSERT INTO winter_internship (business_code, cust_number, clear_date, buisness_year, doc_id, posting_date, document_create_date, due_in_date, invoice_currency, posting_id, total_open_amount, baseline_create_date, cust_payment_terms, invoice_id,document_type) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,business_code);
		ps.setString(2,cust_number);
		ps.setString(3,clear_date);
		ps.setString(4,buisness_year);
		ps.setString(5,doc_id);
		ps.setString(6,document_create_date);
		ps.setString(7,due_in_date);
		ps.setString(8,posting_date);
		ps.setString(9,invoice_currency);
		ps.setString(10,posting_id);
		ps.setString(11,total_open_amount);
		ps.setString(12,baseline_create_date);
		ps.setString(13,cust_payment_terms);
		ps.setString(14,invoice_id);
		ps.setString(15,document_type);
		if(ps.executeUpdate() > 0){
			Response.put("inserted",true);
		}else {
			Response.put("inserted",false);
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
		response.setHeader("Access-Control-Allow-Methods", "post");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
