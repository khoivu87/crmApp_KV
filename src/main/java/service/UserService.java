/**
 * Project: CRM
 * Auther: Vu Kim Khoi
 */
package service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.mindrot.jbcrypt.BCrypt;

import repository.UserDao;
import dto.UserDto;
import entity.User;

public class UserService {
	
	private UserDao userDao = null; 
	
	public UserService() {
		userDao = new UserDao(); 
	}
	
	public List<UserDto> findAll(){
		List<UserDto> dtos = userDao.findAll(); 
		
		return dtos; 
	}
	
	public List<UserDto> findAllName(){
		List<UserDto> dtos = userDao.findAllName(); 
		
		return dtos; 
	}
	
	public UserDto findById(String id) {
		UserDto dto = userDao.findById(id); 		
		
		return dto; 
	}
	
	public void delete(String id) {
		userDao.delete(id);
	}
	
	public void insert(UserDto dto) {
		User user = new User(); 
		
		// Encrypt password
		String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(12)); // hash 12 lan
		
		user.setEmail(dto.getEmail());
		user.setPassword(hashed);
		user.setFullname(dto.getFullname());
		user.setPhone(dto.getPhone());
		user.setAddress(dto.getAddress());	
		user.setAvatar(dto.getAvatar());
		user.setRole_id(dto.getRole_id());
		
		userDao.insert(user);
	}
	
	public void update(UserDto dto) {
		User user = new User(); 
		
		user.setId(dto.getId());
		user.setEmail(dto.getEmail());
		user.setFullname(dto.getFullname());
		user.setPhone(dto.getPhone());
		user.setAddress(dto.getAddress());
		user.setAvatar(dto.getAvatar());
		user.setRole_id(dto.getRole_id());
		
		userDao.update(user);
	}
	
	// Login validation
	public UserDto checkLoginUserDto(String email, String password) {
		UserDto user = userDao.findByEmail(email); 
		if(user != null && BCrypt.checkpw(password, user.getPassword())) {
			return user; 
		}
		return null; 
	}
	
	public int loadStatisticTask(UserDto model ,int status_id) {
		double statistic = userDao.loadStatisticTask(model, status_id); 
 
		statistic = (double) Math.round(statistic * 100) / 100;
		
		double result = statistic*100; // Change from factor to whole number
		
		return (int)result;
	}
	
	// Check password by id
	public int checkPassword(String id, String password) {
		UserDto user = userDao.findById(id); 
		if(user != null && BCrypt.checkpw(password, user.getPassword())) {
				return 1; 
			}
		return 0; 
	}
	
	public int changePassword(String id, String password) {
		String hashed = BCrypt.hashpw( password, BCrypt.gensalt(12)); // hash 12 lan
		return userDao.changePassword(id, hashed); 
	}
	
	// Upload file
	public boolean uploadFile(HttpServletRequest request) {
		try {
			for (Part part : request.getParts()) {
				String contentDisp = part.getHeader("content-disposition");
				String[] items = contentDisp.split(";");
				
				String fileName = "";
				for (String s : items) {	
					if (s.trim().startsWith("filename")) {
						fileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
					}
				}
				
				String url = ""; 
				if(!fileName.isEmpty()) {
					File folderUpload = new File(System.getProperty("catalina.base"));  
					String absolutePath = folderUpload.getAbsolutePath();
				    String filePath = absolutePath.substring(0,absolutePath.lastIndexOf(".metadata"));// LẤY ĐƯỜNG DẪN CỦA PROJECT TRONG MÁY LOCAL
				    String folderPath = request.getContextPath().substring(1) + File.separator + "WebContent" + 
				    		File.separator + "static" + File.separator + "plugins" + File.separator + 
				    		"images" + File.separator + "users" + File.separator;     
				    // Path to save upload file at:  /plugins/images/users/
				      
				    url = filePath + folderPath + fileName; 
				    part.write(url); 
				}		
			}
			return true; 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false; 
	}
	
	// Create URL to save avatar image at: /plugins/images/users/
	public String getAvatarUrl(HttpServletRequest request) {
		String url = ""; 
		try {
			for (Part part : request.getParts()) {
				String contentDisp = part.getHeader("content-disposition");
				String[] items = contentDisp.split(";");
				
				String fileName = "";
				for (String s : items) {	
					if (s.trim().startsWith("filename")) {
						fileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
					}
				}
				
				if(!fileName.isEmpty()) {
				    url = request.getContextPath() + "/static/plugins/images/users/" + fileName; 
				}		
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return url; 
	}
	
	public UserDto findByEmail(String email) {
		return userDao.findByEmail(email); 
	}
}
