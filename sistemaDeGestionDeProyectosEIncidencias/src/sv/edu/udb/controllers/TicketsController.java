/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;
import sv.edu.udb.models.Ticket;
import sv.edu.udb.models.TicketDAO;
        
/**
 *
 * @author kiss_
 */
public class TicketsController {
    
    public Ticket showTicket(int id){
        TicketDAO dao = new TicketDAO();
        return dao.getOne(id);
    }
    
    public boolean showStatusTicket (int p, String par){
        TicketDAO dao = new TicketDAO();
        return dao.checkEmployee(p,par);
    }
    
    public boolean updateP(int t, int p){
        TicketDAO dao = new TicketDAO();
        return dao.updateProgrammer(t, p);
    }
       public boolean updateT(int t, int p){
        TicketDAO dao = new TicketDAO();
        return dao.updateQA(t, p);
    }
    
}
