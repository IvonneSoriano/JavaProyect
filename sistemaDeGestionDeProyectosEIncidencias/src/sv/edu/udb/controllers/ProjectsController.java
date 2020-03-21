/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;
import java.util.List;
import sv.edu.udb.models.Project;
import sv.edu.udb.models.ProjectDAO;
import sv.edu.udb.util.DAODefaults;
/**
 *
 * @author Imer Palma
 */
public class ProjectsController {
     public List<Project> projectsByDepto(){
         ProjectDAO dao = new ProjectDAO();
         List<Project> projectsByDepto = dao.getProjbyDepto();
         
         return projectsByDepto;
         
     }
     public List<Project> getProjects(){
         ProjectDAO dao = new ProjectDAO();
         List<Project> projects = dao.getAll();
         
         return projects;
     }
     public void insertProject(Project p) {
        ProjectDAO dao = new ProjectDAO();
        dao.save(p);
    }
     public void deleteProject(Project p){
         ProjectDAO dao = new ProjectDAO();
         dao.delete(p);
     }
     
}
