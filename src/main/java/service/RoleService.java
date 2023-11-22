/**
 * Project: CRM
 * Auther: Vu Kim Khoi
 */
package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import repository.RoleDao;
import dto.RoleDto;
import entity.Role;

public class RoleService {
	private RoleDao roleDao = null; 
	
	public RoleService() {
		roleDao = new RoleDao(); 
	}
	
	public List<RoleDto> findAll(){
		List<RoleDto> dtos = new ArrayList<RoleDto>(); 
		List<Role> roles = roleDao.findAll(); 
		for(Role role:roles) {
			dtos.add(new RoleDto(
								role.getId(),
								role.getName(), 
								role.getDescription()
					));
		}
		
		return dtos; 
	}
	
	public RoleDto findById(String id) {
		RoleDto dto = new RoleDto(); 
		
		Role role = roleDao.findById(id); 
		dto.setId(role.getId());
		dto.setName(role.getName());
		dto.setDesc(role.getDescription());
		
		return dto; 
	}
	
	public void deleteById(String id) {
		roleDao.delete(id); 
	}
	
	public void insert(RoleDto dto) {
		// Transfer data to dto through entity
		Role role = new Role();
		role.setName(dto.getName());
		role.setDescription(dto.getDesc());
		
		roleDao.insert(role);
	}
	
	public void update(RoleDto dto) throws SQLException {
		// Transfer data to dto through entity
		Role role = new Role();
		role.setId(dto.getId());
		role.setName(dto.getName());
		role.setDescription(dto.getDesc());
		
		roleDao.update(role);
	}
}
