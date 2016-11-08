package cs544.mum.oproject.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import cs544.mum.edu.oproject.model.Project;
import cs544.mum.edu.oproject.model.ProjectTask;
import cs544.mum.edu.oproject.model.User;

public  class ProjectDao implements ProjectDAOInterface<Project, String> {

	private Session currentSession;
	
	private Transaction currentTransaction;

	public ProjectDao() {
	}

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}
	
	public void closeCurrentSession() {
		currentSession.close();
	}
	public void rollBack(){
		currentTransaction.rollback();
	}
	
	
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
	
	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	public void persist(Project entity) {
		getCurrentSession().save(entity);
	}
	
	public void persist(User user) {
		getCurrentSession().save(user);
	}

	public void update(Project entity) {
		getCurrentSession().update(entity);
	}

	public Project findById(int id) {
		Project project = (Project) getCurrentSession().get(Project.class, id);
		return project; 
	}
	

	public void delete(Project entity) {
		getCurrentSession().delete(entity);
	}
	public void delete(User user) {
		getCurrentSession().delete(user);
	}

	@SuppressWarnings("unchecked")
	public Collection<Project> findAll() {
		//NEW cs544.mum.edu.oproject.model.Project(p.id,p.name,p.projectDesc,p.projectStatus,p.startDate,p.endDate,p.location)
		Collection<Project> projects =  getCurrentSession().createQuery("from Project  ").list();
		return projects;
	}

	public User findUser(String user) {		
		User users =  (User) getCurrentSession().createQuery("from User  where userName="+user).list();
		return users;
	}	

	public Project findByStatus(String pstatus) {
		Project project = (Project) getCurrentSession().get(Project.class, pstatus);
		return project;
	}

	public Project findById(String projectStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	

	


}
