package cs544.mum.edu.oproject.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "PROJECT")
public class Project {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String projectDesc;
	private String projectStatus;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	private String location;

	@OneToMany( fetch = FetchType.EAGER,mappedBy = "project",cascade=CascadeType.ALL)	
	@org.hibernate.annotations.BatchSize(size=5)
	private Collection<ProjectTask> projectTask = new ArrayList<ProjectTask>();

	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project",cascade=CascadeType.ALL)		
	@org.hibernate.annotations.BatchSize(size=5)
	private Collection<ProjectSkills> projectSkill = new ArrayList<ProjectSkills>();

	public Project() {
	}

	public Project(int id, String name, String projectDesc, String projectStatus, Date startDate, Date endDate,
			String location) {
		this.id = id;
		this.name = name;
		this.projectDesc = projectDesc;
		this.projectStatus = projectStatus;
		this.startDate = startDate;
		this.endDate = endDate;
		this.location = location;
	}

	public Project(String name, String projectDesc, String projectStatus, Date startDate, Date endDate,
			String location) {

		this.name = name;
		this.projectDesc = projectDesc;
		this.projectStatus = projectStatus;
		this.startDate = startDate;
		this.endDate = endDate;
		this.location = location;
	}

	public void addskill(ProjectSkills s) {
		s.setProject(this);
		projectSkill.add(s);
	}

	public void addProjectTask(ProjectTask task) {
		task.setProject(this);
		projectTask.add(task);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLocation() {
		return location;
	}

	
	
	
	

	

	

	public Collection<ProjectTask> getProjectTask() {
		return projectTask;
	}

	public void setProjectTask(Collection<ProjectTask> projectTask) {
		this.projectTask = projectTask;
	}

	public Collection<ProjectSkills> getProjectSkill() {
		return projectSkill;
	}

	public void setProjectSkill(Collection<ProjectSkills> projectSkill) {
		this.projectSkill = projectSkill;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((projectDesc == null) ? 0 : projectDesc.hashCode());
		result = prime * result + ((projectSkill == null) ? 0 : projectSkill.hashCode());
		result = prime * result + ((projectStatus == null) ? 0 : projectStatus.hashCode());
		result = prime * result + ((projectTask == null) ? 0 : projectTask.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id != other.id)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (projectDesc == null) {
			if (other.projectDesc != null)
				return false;
		} else if (!projectDesc.equals(other.projectDesc))
			return false;
		if (projectSkill == null) {
			if (other.projectSkill != null)
				return false;
		} else if (!projectSkill.equals(other.projectSkill))
			return false;
		if (projectStatus == null) {
			if (other.projectStatus != null)
				return false;
		} else if (!projectStatus.equals(other.projectStatus))
			return false;
		if (projectTask == null) {
			if (other.projectTask != null)
				return false;
		} else if (!projectTask.equals(other.projectTask))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Project: " + this.id + ", " + this.name + ", " + this.projectDesc + ", " + this.projectStatus + ", "
				+ this.startDate + ", " + this.endDate + ", " + this.location;
	}

}