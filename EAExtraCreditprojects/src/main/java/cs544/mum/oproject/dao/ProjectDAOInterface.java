package cs544.mum.oproject.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cs544.mum.edu.oproject.model.Project;
import cs544.mum.edu.oproject.model.User;

public interface ProjectDAOInterface<T, Id extends Serializable> {

	public void persist(T entity);
	
	public void update(T entity);
	public  User findUser(String user);
	
	public T findById(Id Id);
	
	public T findByStatus(Id projectStatus);
	
	public void delete(T entity);
	
	public Collection<Project> findAll();
	
	
}
