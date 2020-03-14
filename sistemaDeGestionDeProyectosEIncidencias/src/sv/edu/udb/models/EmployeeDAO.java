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

/**
 *
 * @author Rick
 */
public class EmployeeDAO implements Dao<Employee> {

    private static Logger logger = Logger.getLogger(EmployeeDAO.class);

    @Override
    public Optional<Employee> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employee> getAll() {

        Connect connection = null;
        List<Employee> employeesFound = new ArrayList<>();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getAll() method. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT * FROM EMPLOYEES;");
            ResultSet employees = (ResultSet) connection.getRs();

            while (employees.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(employees.getInt("EmployeeID"));
                employee.setRolId(employees.getInt("ROLID"));
                employee.setDepartmentId(employees.getInt("DEPARMENTID"));
                employee.setEmployeeName(employees.getString("EMPLOYEENAME"));
                employee.setEmployeeLastname(employees.getString("EMPLOYEELASTNAME"));
                employee.setUsername(employees.getString("USERNAME"));
                employee.setPassword(employees.getString("PASSWORD"));
                employeesFound.add(employee);
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
        return employeesFound;
    }

    @Override
    public void save(Employee t) {
        try {
            Connect connection = new Connect();

            int result = connection.setQuery("INSERT INTO `gestion_tickets`.`employees` "
                    + "(`ROLID`, `DEPARMENTID`, `EMPLOYEENAME`, `EMPLOYEELASTNAME`, `USERNAME`, `PASSWORD`) "
                    + "VALUES ('" + t.getRolId() + "', '" + t.getDepartmentId()
                    + "', '" + t.getEmployeeName() + "', '"
                    + t.getEmployeeLastname() + "', '"
                    + t.getUsername() + "', '" + t.getPassword() + "');");

            if (result <= 0) {
                logger.error("INSERT to Employees table has failed");
            } else {
                logger.info("INSERT to Employees table has successfully completed!");
            }

        } catch (Exception e) {
            logger.error("Error processing INSERT query in save method. Message: " + e.getMessage());
        }
    }

    // THIS METHOD CAN BE DONE BY SUPERVISOR  
    @Override
    public void update(Employee t, String[] params) {
        try {
            Connect connection = new Connect();

            int result = connection.setQuery("UPDATE `gestion_tickets`.`employees` SET "
                    + "`ROLID` = " + t.getRolId()
                    + ", `EMPLOYEENAME` = " + t.getEmployeeName()
                    + ", `EMPLOYEELASTNAME` = " + t.getEmployeeLastname()
                    + ", `USERNAME` = " + t.getUsername()
                    + "WHERE `EmployeeID` = " + t.getEmployeeId() + "';");

            if (result <= 0) {
                logger.error("UPDATE to Employees table has failed");
            } else {
                logger.info("UPDATE to Employees table has successfully completed!");
            }
        } catch (Exception e) {
            logger.error("Error processing UPDATE query in save method. Message: " + e.getMessage());
        }
    }

    //THIS METHOD CAN BE DONE BY THE SA
    public void updateSA(Employee t, String[] params) {
        try {
            Connect connection = new Connect();

            int result = connection.setQuery("UPDATE `gestion_tickets`.`employees` SET "
                    + "`ROLID` = " + t.getRolId()
                    + ", `DEPARMENTID` = " + t.getDepartmentId()
                    + ", `EMPLOYEENAME` = " + t.getEmployeeName()
                    + ", `EMPLOYEELASTNAME` = " + t.getEmployeeLastname()
                    + ", `USERNAME` = " + t.getUsername()
                    + ", `PASSWORD` = " + t.getPassword()
                    + "WHERE `EmployeeID` = " + t.getEmployeeId() + "';"
            );

            if (result <= 0) {
                logger.error("UPDATE to Employees table has failed");
            } else {
                logger.info("UPDATE to Employees table has successfully completed!");
            }
        } catch (Exception e) {
            logger.error("Error processing UPDATE query in save method. Message: " + e.getMessage());
        }
    }

    @Override
    public void delete(Employee t) {
        try {
            Connect connection = new Connect();

            int result = connection.setQuery("DELETE FROM `gestion_tickets`.`employees` WHERE `EmployeeID` = "
                    + t.getEmployeeId() + ";");

            if (result <= 0) {
                logger.error("DELETE to Employees table has failed");
            } else {
                logger.info("DELETO to Employees table has successfully completed!");
            }

        } catch (Exception e) {
            logger.error("Error processing DELETE query in save method. Message: " + e.getMessage());
        }
    }

    public Optional<Employee> getEmployeeByUsername(String username) {
        Employee foundEmployee = null;
        try {
            Connect connection = new Connect();
            connection.setRs("SELECT * FROM EMPLOYEES WHERE username='" + username + "';");
            ResultSet employees = (ResultSet) connection.getRs();

            while (employees.next()) {
                foundEmployee = new Employee();
                foundEmployee.setEmployeeId(employees.getInt("EmployeeID"));
                foundEmployee.setRolId(employees.getInt("ROLID"));
                foundEmployee.setDepartmentId(employees.getInt("DEPARMENTID"));
                foundEmployee.setEmployeeName(employees.getString("EMPLOYEENAME"));
                foundEmployee.setEmployeeLastname(employees.getString("EMPLOYEELASTNAME"));
                foundEmployee.setUsername(employees.getString("USERNAME"));
                foundEmployee.setPassword(employees.getString("PASSWORD"));

            }
        } catch (Exception e) {
            logger.error("Error processing ResultSet in getEmployeeByUsername() method. Message: " + e.getMessage());
        }
        return Optional.ofNullable(foundEmployee);
    }
}