/**
 * Project: CRM
 * Auther: Vu Kim Khoi
 */
package service;

import java.util.ArrayList;
import java.util.List;

import constants.StatusConstants;
import repository.JobDao;
import dto.JobDto;
import entity.Job;

public class JobService {
	private JobDao jobDao = null; 
	
	public JobService() {
		jobDao = new JobDao(); 
	}
	
	public List<JobDto> findAll(){
		List<JobDto> dtos = new ArrayList<JobDto>(); 
		List<Job> jobs = jobDao.findAll(); 
		for(Job job: jobs) {
			dtos.add(new JobDto(
								job.getId(),
								job.getName(), 
								job.getStart_date(),
								job.getEnd_date(), 
								this.loadStatisticJob(job.getId(), StatusConstants.CHUA_THUC_HIEN), 
								this.loadStatisticJob(job.getId(), StatusConstants.DANG_THUC_HIEN), 
								this.loadStatisticJob(job.getId(), StatusConstants.DA_HOAN_THANH)
					));
		}
		
		return dtos; 
	}
	
	public JobDto findById(String id) {
		JobDto dto = new JobDto(); 
		
		Job job = jobDao.findById(id); 
		dto.setId(job.getId());
		dto.setName(job.getName());
		dto.setStart_date(job.getStart_date());
		dto.setEnd_date(job.getEnd_date());
		
		return dto; 
	}
	
	public void delete(String id) {
		jobDao.delete(id); 
	}
	
	public void insert(JobDto dto) {
		// Transfer data to dto from entity
		Job job = new Job();
		job.setName(dto.getName());
		job.setStart_date(dto.getStart_date());
		job.setEnd_date(dto.getEnd_date());
	
		jobDao.insert(job);
	}
	
	public void update(JobDto dto) {
		// Transfer data to dto from entity
		Job job = new Job();
		job.setId(dto.getId());
		job.setName(dto.getName());
		job.setStart_date(dto.getStart_date());
		job.setEnd_date(dto.getEnd_date());
		
		jobDao.update(job);
	}
	
	public int loadStatisticJob(int id ,int status) {
		double statistic = jobDao.loadStatisticJob(id, status); 
		statistic = (double) Math.round(statistic * 100) / 100;
		
		double result = statistic*100; 
		
		return (int)result;
	}
}
