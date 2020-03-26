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
 * @author Rick
 */
public class TicketController {

    public boolean saveTicket(Ticket t) {
        return new TicketDAO().save(t);
    }
}
