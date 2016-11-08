package cs544.mum.oproject.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.PersistenceException;

import cs544.mum.edu.oproject.model.Project;
import cs544.mum.edu.oproject.model.ProjectSkills;
import cs544.mum.edu.oproject.model.ProjectTask;
import cs544.mum.edu.oproject.model.User;
import cs544.mum.edu.oproject.service.ProjectService;

public class App {

	private static String userType;
	private static boolean session = false;
	static ProjectService projectService = new ProjectService();

	static ArrayList<ProjectTask> taskList = new ArrayList<ProjectTask>();
	static ArrayList<ProjectSkills> skillList = new ArrayList<ProjectSkills>();

	public static boolean isSession() {
		return session;
	}

	public static void setSession(boolean session) {
		App.session = session;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		App.userType = userType;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {

		try {
			
			User aUser = new User("Binesh", "admin", "admin");
			User nUser = new User("BKG", "normal", "normal");

			projectService.persist(aUser);
			projectService.persist(nUser);
			
			System.out.println("Provide Login Credentials:");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Name: \n");
			String userName = br.readLine();
			System.out.println("Password: \n");
			String password = br.readLine();

			User users = (User) projectService.findUser(userName);
			System.out.println("Users; "+users.toString());
			if (users == null) {
				System.out.println("Wrong user. Please provide correct user name and password !!");
				System.exit(0);
				return;
			} else {
				userType = users.getUserType();
				if (password.equals(users.getUserPassword()) && userType.equals("admin")) {
					session = true;
					System.out.println("Welcome Administrator User");
					System.out.println("You have following access to this system");

					while (session) {

						System.out.println(
								" 1. Creating project \n 2. Updating project \n 3. List Projects \n 4.List project with different Status\n 5. Enter 0 to extit");
						System.out.println("Enter Choice:");
						String choice = br.readLine();
						switch (choice) {
						case "1":
							createProject();
							break;
						case "2":
							break;
						case "3":
							listProjects();
							break;
						case "4":
							findProjectByStatu();
						case "0":
							System.exit(0);
							break;
						default:
							System.out.println("Sorry. Wrong Choice!!");

						}
					}

				}
				if (password.equals(users.getUserPassword()) && userType.equals("normal")) {
					System.out.println("Welcome Normal User ");
					System.out.println("You have following access to this system");
					
					while (session) {

						System.out.println(
								"  1.List project with different Status \n 2. Offer Different services to project. \n 3.Enter 0 to extit");
						System.out.println("Enter Choice:");
						String choice = br.readLine();
						switch (choice) {
						case "1":
							listProjects();
							break;
						case "2":
							break;
						case "0":
							System.exit(0);
							break;
						default:
							System.out.println("Sorry. Wrong Choice!!");

						}
					}
				} else {
					System.out.println("Wrong user. Please provide correct user name and password !!");
				}
			}

			

			

			

		} catch (PersistenceException e) {
			e.getMessage();
		}

	}

	private static void findProjectByStatu() throws IOException {
		System.out.println("Enter project status to find the project");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String pStatus = br.readLine();

		System.out.println("*** Find - start ***");
		Project another = projectService.findByStatus(pStatus);
		System.out.println("Project found with id " + pStatus + " is =>" + another.toString());
		System.out.println("*** Find - end ***");
	}

	private static void listProjects() {
		Collection<Project> projects = projectService.findAll();
		System.out.println("Projects Persisted are :");

		for (Project project : projects) {
			System.out.println(project.toString());
			for (ProjectSkills skills : project.getProjectSkill()) {
				System.out.println(skills.toString());
			}
			for (ProjectTask task : project.getProjectTask()) {
				System.out.println(task.toString());
			}
		}

	}

	private static void createProject() throws IOException {
		boolean addp = true;
		while (addp) {
			boolean taskStatus = false;
			boolean skillStatus = false;
			String tstatus="";
			String tskill="";
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String pName="";
			String pdesc="";
			String pStatus="";
			String pLocation="";

			taskList = new ArrayList<ProjectTask>();
			skillList = new ArrayList<ProjectSkills>();

			System.out.println("Enter Project Description:");
			System.out.println(" Project Name \n");
			pName = br.readLine();
			System.out.println(" Project Description \n");
			pdesc = br.readLine();
			System.out.println(" Project Location \n");
			pLocation = br.readLine();
			System.out.println(" Project Status \n");
			pStatus = br.readLine();
			Project project = new Project(pName, pdesc, pStatus, new Date(), new Date(), pLocation);
			System.out
					.println("Project " + pName + " is added" + "\n Do you want to add task for this project (Yes/No)");
			tstatus = br.readLine();
			if (tstatus.equals("Yes")) {
				taskStatus = true;
			} else {
				taskStatus = false;
			}
			while (taskStatus) {
				String tdes;
				String taskstatus;
				String resource;

				System.out.println("Please Enter Task :");
				System.out.println("Enter Description:");
				tdes = br.readLine();
				System.out.println("Enter Status:");
				taskstatus = br.readLine();
				System.out.println("Enter Resource Required:");
				resource = br.readLine();

				ProjectTask task = new ProjectTask(tdes, taskstatus, resource);
				taskList.add(task);
				System.out.println("Do you want to Add more Task(Yse/No)");
				String moretask = br.readLine();
				if (moretask.equals("No")) {
					taskStatus = false;
				}
			}

			System.out.println(" Do you want to add Skill for this project (Yes/No)");
			tskill = br.readLine();
			if (tskill.equals("No")) {
				skillStatus = false;
			}
			while (skillStatus) {
				String skill;

				System.out.println("Please Enter Skill :");

				skill = br.readLine();

				ProjectSkills skills = new ProjectSkills(skill);
				skillList.add(skills);
				System.out.println("Do you want to Add more Skill(Yes/No)");
				String moreskill = br.readLine();
				if (moreskill.equals("No")) {
					skillStatus = false;
				}
			}

			System.out.println("*** Persist - start ***");
			System.out.println("Project " + pName + " is saved");
			for (ProjectSkills skillL : skillList) {
				project.addskill(skillL);
			}
			for (ProjectTask t : taskList) {
				project.addProjectTask(t);
			}
			projectService.persist(project);

			System.out.println("*** Persist - end ***");
			System.out.println("Do you want to add more project(Yes/No)");
			String morep = br.readLine();
			if (morep.equals("No")) {
				addp = false;
			} else {
				taskList = new ArrayList<ProjectTask>();
				skillList = new ArrayList<ProjectSkills>();
			}

		}
	}

}
