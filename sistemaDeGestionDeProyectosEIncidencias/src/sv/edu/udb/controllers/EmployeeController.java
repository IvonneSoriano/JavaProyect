/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import java.util.Optional;
import sv.edu.udb.models.Employee;
import sv.edu.udb.models.EmployeeDAO;
import sv.edu.udb.util.DAODefaults;

/**
 *
 * @author Rick
 */
public class EmployeeController {

    public static int tipoEmpleadoLoggeado = -1;

    public Employee findEmployee(String username) {
        EmployeeDAO dao = new EmployeeDAO();
        Optional<Employee> foundEmp = dao.getEmployeeByUsername(username);

        return foundEmp.orElseGet(() -> new Employee(DAODefaults.NON_EXISTING_USER.getDefaultValue()));

    }
}
