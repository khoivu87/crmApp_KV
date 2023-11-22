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

import dto.UserDto;

import service.JobService;
import service.TaskService;
import service.UserService;

import constants.UrlConstants;

@WebServlet( urlPatterns = { UrlConstants.HOME_URL } )
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TaskService taskService = null; 
	private UserService userService = null;  
	private JobService jobService = null;
	
	@Override
	public void init() throws ServletException {
		taskService = new TaskService();
		userService = new UserService(); 
		jobService = new JobService();
	}	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String action = req.getServletPath(); 
			switch (action) {
				case UrlConstants.HOME_URL:
					HttpSession session = req.getSession(); 
					UserDto userDto = (UserDto)session.getAttribute("USER"); 
					String roleName = userDto.getRoleName(); 
					
					if(roleName.equals("ROLE_MEMBER")) {
						String id = String.valueOf(userDto.getId()); 
						req.setAttribute("tasks", taskService.findByUserId(id));
						req.setAttribute("chuaThucHien", userService.loadStatisticTask(userDto, 1));
						req.setAttribute("dangThucHien", userService.loadStatisticTask(userDto, 2));
						req.setAttribute("daHoanThanh", userService.loadStatisticTask(userDto, 3));
						req.getRequestDispatcher("/WEB-INF/views/profile/profileTask.jsp").forward(req, resp);
					}
					else {
						req.setAttribute("jobs", jobService.findAll());
						req.getRequestDispatcher("/WEB-INF/views/home/homeIndex.jsp").forward(req, resp);
					}			
					break; 
				default:
					break; 
		}	
	}
}
