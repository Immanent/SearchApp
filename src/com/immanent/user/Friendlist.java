package com.immanent.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.immanent.models.MySQLAccess;
import com.immanent.models.TokenModel;
import com.immanent.services.ServiceController;

/**
 * Servlet implementation class Friendlist
 */
public class Friendlist extends ServiceController {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Friendlist() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		dispatch("/User.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String diaspora_id = request.getParameter("diaspora_id"); // dilma@localhost:3000
		String[] splits = diaspora_id.split("@");
		String redirect_url = "http://" + splits[1] + "/authorize/verify";
		String token=null;
		MySQLAccess dao = new MySQLAccess();
		TokenModel tokenModel=new TokenModel();
		String signed_manifest = dao.read();
		try {
			  response.setContentType("text/html");
			  token=tokenModel.get(diaspora_id);
			  if(token.isEmpty()){
				  token = new String(sendPost(redirect_url, signed_manifest));
			      tokenModel.setAuth_token(token);
			      tokenModel.setDiaspora_id(diaspora_id);
			      tokenModel.save();
			  }
		      
		      /*
		       * Temporaly removed
		       * response.setStatus(response.SC_MOVED_TEMPORARILY);
		         response.setHeader("Location", site); 
		       * */	
			  response.sendRedirect("http://localhost:3000/dauth/authorize/authorization_token?auth_token="+token);
		      //RequestDispatcher rd = request.getRequestDispatcher("http://localhost:3000/dauth/authorize/authorization_token");
			  //rd.forward(request, response);
		      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String sendPost(String url, String parameters) throws Exception {
		final String USER_AGENT = "Mozilla/5.0";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		// add header
		post.setHeader("User-Agent", USER_AGENT);
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("signed_manifest", parameters));
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		String params[]=result.toString().split("%");
		return params[0];
		
	}

}
