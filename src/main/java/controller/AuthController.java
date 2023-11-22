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
import javax.servlet.http.HttpSession;

import constants.AdminConstants;
import constants.MessageConstants;
import dto.UserDto;

import service.UserService;

import constants.UrlConstants;

@WebServlet(urlPatterns = {UrlConstants.LOGIN_URL, UrlConstants.LOGOUT_URL})
public class AuthController extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	private UserService userService = null;
	
	@Override
	public void init() throws ServletException {
		userService = new UserService(); 
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		String action = req.getServletPath(); 
		
		switch (action) {
			case UrlConstants.LOGIN_URL:
				req.getRequestDispatcher("/WEB-INF/views/login/loginIndex.jsp").forward(req, resp);
				break; 
			case UrlConstants.LOGOUT_URL:
					HttpSession session = req.getSession(); 
					session.removeAttribute("USER");
					resp.sendRedirect(req.getContextPath() + UrlConstants.LOGIN_URL);
				break; 
			default:
				break; 
		}	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email"); 
		String password = req.getParameter("password"); 

		if(email.equals(AdminConstants.ADMIN_EMAIL)) {
			if(userService.findByEmail(email) == null) {
				UserDto adminDto = new UserDto(); 
				adminDto.setFullname(AdminConstants.ADMIN_FULLNAME);
				adminDto.setEmail(AdminConstants.ADMIN_EMAIL);
				adminDto.setPassword(AdminConstants.ADMIN_PASSWORD);
				adminDto.setRole_id(AdminConstants.ADMIN_ROLEID);
				userService.insert(adminDto);
			}
		}

		UserDto modelDto = userService.checkLoginUserDto(email, password); 
		
		if(modelDto != null) {
			HttpSession session = req.getSession(); 
			session.setAttribute("USER", modelDto);
			
			resp.sendRedirect(req.getContextPath() + UrlConstants.HOME_URL);
		}
		else {
			req.setAttribute("message", MessageConstants.LOGIN_MESSAGE);
			req.getRequestDispatcher("/WEB-INF/views/login/loginIndex.jsp").forward(req, resp);
		}
	}
}
