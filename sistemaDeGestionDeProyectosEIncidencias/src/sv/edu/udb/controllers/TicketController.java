/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;
import java.util.List;
import sv.edu.udb.models.Ticket;
import sv.edu.udb.models.TicketDAO;
import sv.edu.udb.util.DAODefaults;
/**
 *
 * @author Imer Palma
 */
public class TicketController {
    public List<Ticket> getAllTickets(){
        TicketDAO dao = new TicketDAO();
        List<Ticket> tickets = dao.getAll();
        
        return tickets;
    }
}
