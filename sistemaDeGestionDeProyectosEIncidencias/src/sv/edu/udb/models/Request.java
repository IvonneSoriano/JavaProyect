/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;
import java.util.Date;
/**
 *
 * @author Imer
 */
public class Request {
    private int id, typeId;
    private String description, status;
    private Date requestDate;
    public Request(){
    }
    
    public void setId(int idRequest){
        this.id = idRequest;
    }
    public int getId(){
        return id;
    }
    public void setIdTypeRequest(int typeRequest){
        this.typeId = typeRequest;
    }
    public int getIdTypeRequest(){
        return typeId;
    }
    public void setRequestDescription(String requestDescription){
        this.description = requestDescription;
    }
    public String getRequestDescription(){
        return description;
    }
    public void setRequestStatus(String requestStatus){
        this.status = requestStatus;
    }
    public String getRequestStatus(){
        return status;
    }
    public void setRequestDate(Date requestDate){
        this.requestDate = requestDate;
    }
    public Date getRequestDate(){
        return requestDate;
    }
    
    
}
