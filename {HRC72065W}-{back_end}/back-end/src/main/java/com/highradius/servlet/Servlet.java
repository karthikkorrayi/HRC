package com.highradius.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.crud.Crud;
import com.google.gson.Gson;
import com.highradius.Pojo.WinterInternship;

/**
 * Servlet implementation class Fetch
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String search = request.getParameter("search");
		String doc_id = request.getParameter("doc_id");
		String invoice_id = request.getParameter("invoice_id");
		String buisness_year = request.getParameter("buisness_year");
		String page = request.getParameter("page");
		String rowsPerPage = request.getParameter("rowsPerPage");
		Crud fetchdata=new Crud();
		 
		HashMap<Object,Object> RES = new HashMap<Object,Object>();
		int count = fetchdata.getTotalCountOfSearch(search, doc_id, invoice_id, buisness_year);
		ArrayList<WinterInternship> data = fetchdata.getData(search, page, rowsPerPage, doc_id, invoice_id, buisness_year);
		
		  	
		Gson gson = new Gson();
		RES.put("data", data);
		RES.put("total", count);
		String respData = gson.toJson(RES);
			
		setAccessControlHeaders(response);
		response.getWriter().print(respData);
	}
	
	private void setAccessControlHeaders(HttpServletResponse response) {
			response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
			response.setHeader("Access-Control-Allow-Methods", "GET");
	  }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}


