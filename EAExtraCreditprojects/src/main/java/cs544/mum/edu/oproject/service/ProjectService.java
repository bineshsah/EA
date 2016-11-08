package cs544.mum.edu.oproject.service;

import java.util.Collection;

import cs544.mum.edu.oproject.model.Project;
import cs544.mum.edu.oproject.model.User;
import cs544.mum.oproject.dao.ProjectDao;

public class ProjectService {

	private static ProjectDao projectDAO;

	public ProjectService() {
		projectDAO = new ProjectDao();
	}

	public void persist(Project entity) {
		projectDAO.openCurrentSessionwithTransaction();
		projectDAO.persist(entity);
		projectDAO.closeCurrentSessionwithTransaction();
	}
	
	public void persist(User user) {
		projectDAO.openCurrentSessionwithTransaction();
		projectDAO.persist(user);
		projectDAO.closeCurrentSessionwithTransaction();
	}

	public void update(Project entity) {
		projectDAO.openCurrentSessionwithTransaction();
		projectDAO.update(entity);
		projectDAO.closeCurrentSessionwithTransaction();
	}

	public Project findById(int id) {
		projectDAO.openCurrentSession();
		Project project = projectDAO.findById(id);
		projectDAO.closeCurrentSession();
		return project;
	}
	public Project findByStatus(String status) {
		projectDAO.openCurrentSession();
		Project project = projectDAO.findByStatus(status);
		projectDAO.closeCurrentSession();
		return project;
	}


	
	

	public Collection<Project> findAll() {
		projectDAO.openCurrentSession();
		Collection<Project> projects = projectDAO.findAll();
		projectDAO.closeCurrentSession();
		return projects;
	}
	public User findUser(String user) {
		projectDAO.openCurrentSession();
		User users = projectDAO.findUser(user);
		projectDAO.closeCurrentSession();
		return users;
	}

	
	public ProjectDao projectDAO() {
		return projectDAO;
	}
	public void finallyClose(){
		projectDAO.closeCurrentSession();
	}
}
