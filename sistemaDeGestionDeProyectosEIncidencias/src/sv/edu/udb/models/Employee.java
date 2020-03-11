/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

/**
 *
 * @author Imer Palma
 */
public class Employees {
    private int Id, rolId, departmentId;
    private String name, lastname;
    
    public Employees(){}
    public void setEmployeeId(int employeeID){
        this.Id = employeeID;
    }
    public int getEmployeeId(){
        return Id;
    }
    
    public void setRolId(int rolId){
        this.rolId = rolId;
    } 
    
    public int getRolId(){
        return rolId;
    }
    
    public void setDepartmentId(int departmentId){
        this.departmentId = departmentId;
    }
    
    public int getDepartmentId(){
        return departmentId;
    }
    
    public void setEmployeeName(String employeeName){
        this.name = employeeName;
    }
    
    public String getEmployeeName(){
        return name;
    }
    
    public void  setEmployeeLastname(String employeeLastname){
        this.lastname = employeeLastname;
    }
    
    public String getEmployeeLastname(){
        return lastname;
    }
}
