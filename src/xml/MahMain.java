package xml;

import xml.projectlist.*;
import xml.projectlist.Projectlist;
import xml.projectlist.Projectlist.ProjectOverview;
import xml.projectlist.Projectlist.ProjectOverview.Userlist;
import xml.projectlist.Projectlist.ProjectOverview.Userlist.User;

public class MahMain {
	public static void main(String[] args) {
		ObjectFactory obf = new ObjectFactory();
		Projectlist projectlist = obf.createProjectlist();
		
		obf.createProjectlistProjectOverviewUserlist();
		obf.createProjectlistProjectOverviewUserlistUser();
		
		ProjectOverview testProjectOverview = new ProjectOverview();
		projectlist.getProjectOverview().add(testProjectOverview);
		projectlist.getProjectOverview().get(0).setDescription("This is a fkn project!");
		System.out.print(projectlist.getProjectOverview().get(0).getDescription());
		
	}
}
