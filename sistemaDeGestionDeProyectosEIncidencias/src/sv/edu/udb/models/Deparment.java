/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

/**
 *
 * @author Imer
 */
public class Departments {
    private int id;
    private String name;
    public Departments(){}
    
    public void setDepartmentId(int idDepartment){
        this.id = idDepartment;
    }
    public int getDepartmentId(){
        return id;
    }
    public void setDepartmentName(String departmentName){
        this.name = departmentName ;
    }
    public String getDepartmentName(){
        return name;
    }
}
