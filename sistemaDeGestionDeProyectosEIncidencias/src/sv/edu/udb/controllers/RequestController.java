/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import java.util.List;

import sv.edu.udb.models.ProjectDAO;
import sv.edu.udb.models.Request;
import sv.edu.udb.models.RequestDAO;

/**
 *
 * @author Rick
 */
public class RequestController {

    public boolean insertRequest(Request r) {
        RequestDAO dao = new RequestDAO();
        boolean result = dao.save(r);
        return result;
    }
    public List<Request> getRequests(){
    
        RequestDAO dao = new RequestDAO();
        List<Request> requests = dao.getAll();

        return requests;
    }
}
