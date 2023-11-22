/**
 * Project: CRM
 * Auther: Vu Kim Khoi
 */
package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.UrlConstants;

@WebServlet(urlPatterns = {UrlConstants.ERR_403_URL})
public class ErrorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath(); 
		
		switch (action) {
			case UrlConstants.ERR_403_URL:
				req.getRequestDispatcher("/WEB-INF/views/error/error403.jsp").forward(req, resp);
				break;
			default:
				break; 
		}
	}
}
