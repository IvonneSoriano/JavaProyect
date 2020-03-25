/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import java.util.Optional;
import java.util.List;


import sv.edu.udb.models.Request;
import sv.edu.udb.models.RequestDAO;
import sv.edu.udb.util.DAODefaults;

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
    public Request findLastRequest(){
        RequestDAO dao = new RequestDAO();
        Optional<Request> req = dao.getLastReequestId();
        return req.orElseGet(() -> new Request(DAODefaults.NO_LAST_REQUEST_FOUND.getDefaultValue()));
        
    }
    public List<Request> getRequests(){
    
        RequestDAO dao = new RequestDAO();
        List<Request> requests = dao.getAll();

        return requests;
    }
}
