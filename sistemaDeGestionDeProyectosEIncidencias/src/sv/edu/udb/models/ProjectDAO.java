/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import sv.edu.udb.util.Connect;
import java.sql.Timestamp;

/**
 *
 * @author Rick
 */
public class ProjectDAO implements Dao<Project> {
     private static Logger logger = Logger.getLogger(ProjectDAO.class);
     
    @Override
    public Optional<Project> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Project> getAll() {
        
        Connect connection = null;
        List<Project> projectFound = new ArrayList<>();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getAll() method. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT * FROM PROJECTS");
            ResultSet projectSet = (ResultSet) connection.getRs();

            while (projectSet.next()) {
                Project project = new Project();
                project.setProjectsId(projectSet.getInt("PROJECTID"));
                project.setDepartmentId(projectSet.getInt("DEPARMENTID"));
                project.setProjectName(projectSet.getString("PROJECTNAME"));
                project.setProjectDescription(projectSet.getString("PROJECTDESCRIPTION"));
                project.setCreationDate(projectSet.getTimestamp("CREATIONDATE"));
                projectFound.add(project);
                
                
            }

        } catch (SQLException e) {
            logger.error("Error processing ResultSet in getAll() method. Message: " + e.getMessage());
        } finally {
            try {
                connection.cerrarConexion();
            } catch (SQLException ex) {
                logger.error("Error closing conecction in getAll() method. Message: " + ex.getMessage());
            }

        }
        return projectFound;
        
    }

    @Override
    public void save(Project t) {
        
         try {
            Connect connection = new Connect();
            
            int result = connection.setQuery("INSERT INTO `gestion_tickets`.`projects` "
                    + "( `DEPARMENTID`, `PROJECTNAME`, `PROJECTDESCRIPTION`, `CREATIONDATE`) "
                    + "VALUES ( '" + t.getDepartmentId()
                    + "', '" + t.getProjectName() + "', '"
                    + t.getProjectDescription() + "', '"
                    + t.getCreationDate() + "');");

            if (result <= 0) {
                logger.error("INSERT to Projects table has failed");
            } else {
                logger.info("INSERT to Projects table has successfully completed!");
            }

        } catch (Exception e) {
            logger.error("Error processing INSERT query in save method. Message: " + e.getMessage());
        }
    }

    @Override
    public void update(Project t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Project t) {
       try {
            Connect connection = new Connect();

            int result = connection.setQuery("DELETE FROM `gestion_tickets`.`projects` WHERE `ProjectID` = "
                    + t.getProjectsId() + ";");

            if (result <= 0) {
                logger.error("DELETE to Projects table has failed");
            } else {
                logger.info("DELETO to Projects table has successfully completed!");
            }

        } catch (Exception e) {
            logger.error("Error processing DELETE query in save method. Message: " + e.getMessage());
        }
    }
    public List<Project> getProjbyDepto() {
        
        Connect connection = null;
        List<Project> projectFound = new ArrayList<>();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getAll() method. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT * FROM PROJECTS WHERE DEPARMENTID = "+Session.deparmentId+";");
            ResultSet projectSet = (ResultSet) connection.getRs();

            while (projectSet.next()) {
                Project project = new Project();
                project.setProjectsId(projectSet.getInt("PROJECTID"));
                project.setDepartmentId(projectSet.getInt("DEPARMENTID"));
                project.setProjectName(projectSet.getString("PROJECTNAME"));
                project.setProjectDescription(projectSet.getString("PROJECTDESCRIPTION"));
                project.setCreationDate(projectSet.getTimestamp("CREATIONDATE"));
                projectFound.add(project);
                
                
            }

        } catch (SQLException e) {
            logger.error("Error processing ResultSet in getAll() method. Message: " + e.getMessage());
        } finally {
            try {
                connection.cerrarConexion();
            } catch (SQLException ex) {
                logger.error("Error closing conecction in getAll() method. Message: " + ex.getMessage());
            }

        }
        return projectFound;
        
    }

}
