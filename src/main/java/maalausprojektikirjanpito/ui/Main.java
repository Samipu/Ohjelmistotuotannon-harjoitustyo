package maalausprojektikirjanpito.ui;

import java.util.Scanner;
import maalausprojektikirjanpito.domain.ManagerService;
import maalausprojektikirjanpito.domain.PaintProject;

public class Main {
    
    /**
     * Main method of the program, for now.
     * @param args 
     */
    public static void main(String[] args) {
        System.out.println("Ohjelma kääntyy ja ajaa!");
        
        Scanner lukija = new Scanner(System.in);
        ManagerService service = new ManagerService("db/example.db");
        
        while (true) {
            System.out.println("(C)reate new, (L)ogin or (Q)uit:  ");
            String input = lukija.nextLine();

            if (input.equals("C")) {
                System.out.println("Enter username:");
                String username = lukija.nextLine();
                System.out.println("Enter password:");
                String password = lukija.nextLine();
                service.createUser(username, password);
                service.login(username, password);
                System.out.println(service.getLoggedIn());
            } else if (input.equals("L")) {
                System.out.println("Enter username:");
                String username = lukija.nextLine();
                System.out.println("Enter password:");
                String password = lukija.nextLine();
                service.login(username, password);
                System.out.println(service.getLoggedIn());
            } else if (input.equals("Q")) {
                break;
            } else {
                System.out.println("Unknown command, " + service.getLoggedIn());
            }
            
            while (true) {
                if (service.getUserProjectsByCategory().keySet().isEmpty()) {
                    System.out.println("No projects yet");
                } else {
                    for (String category : service.getUserProjectsByCategory().keySet()) {
                        System.out.println(category);
                    }
                }
                System.out.println("Select (<category>), (C)reate a project, (L)ogout or (P)rint logged user: ");
                String i = lukija.nextLine();
                if (i.equals("L")) {
                    break;
                } else if (service.getUserProjectsByCategory().keySet().contains(i)) {
                    System.out.println("PID  | Project name   | Completed | Archived | In Trash");
                    for (PaintProject project : service.getUserProjectsByCategory().get(i)) {
                        System.out.println(project.getProject_id() + " | " + project.getProject_name() + " | " + project.getProject_completed() + " | " + project.getProject_archived() + " | " + project.getProject_intrash());
                    }
                } else if (i.equals("C")) {
                    System.out.println("Project name: ");
                    String projectname = lukija.nextLine();
                    System.out.println("Project category: ");
                    String projectcategory = lukija.nextLine();
                    service.createPaintProject(projectname, projectcategory);
                } else {
                    System.out.println(service.getLoggedIn());
                }
            }
        }
        
        
    }
}
