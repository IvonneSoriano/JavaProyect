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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Employee t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Employee t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
