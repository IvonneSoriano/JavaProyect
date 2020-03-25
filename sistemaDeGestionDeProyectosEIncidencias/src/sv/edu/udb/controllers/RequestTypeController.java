/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import java.util.List;
import sv.edu.udb.models.RequestType;
import sv.edu.udb.models.RequestTypeDAO;

/**
 *
 * @author Rick
 */
public class RequestTypeController {
    public List<RequestType> findRequestTypes(){
        RequestTypeDAO dao = new RequestTypeDAO();
        return dao.getAll();
    }
}
