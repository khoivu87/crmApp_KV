/**
 * Project: CRM
 * Auther: Vu Kim Khoi
 */
package service;

import java.util.List;

import repository.StatusDao;
import entity.Status;

public class StatusService {
	private StatusDao statusDao = null; 
	
	public StatusService() {
		statusDao = new StatusDao(); 
	}
	
	public List<Status> findAll(){
		return statusDao.findAll(); 
	}
}
