/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import java.util.List;
import java.util.Optional;
import sv.edu.udb.models.Employee;
import sv.edu.udb.models.EmployeeDAO;
import sv.edu.udb.util.DAODefaults;

/**
 *
 * @author Rick
 */
public class EmployeeController {

    public boolean insertEmployee(Employee e) {
        EmployeeDAO dao = new EmployeeDAO();
        return dao.save(e);
    }
    

    public Employee findEmployee(String username) {
        EmployeeDAO dao = new EmployeeDAO();
        Optional<Employee> foundEmp = dao.getEmployeeByUsername(username);

        return foundEmp.orElseGet(() -> new Employee(DAODefaults.NON_EXISTING_USER.getDefaultValue()));
    }
    public List<Employee> findEmployee(int rol) {
        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> foundEmp = dao.getAllByRol(rol);

        return foundEmp;
    }
    
     public List<Employee> findEmployee(int rol, int dep) {
        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> foundEmp = dao.getAllByRolAndDepto(rol, dep);

        return foundEmp;
    }
}
