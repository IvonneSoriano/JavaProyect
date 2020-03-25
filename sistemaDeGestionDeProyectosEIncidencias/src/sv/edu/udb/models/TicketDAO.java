/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import java.util.List;
import java.util.Optional;
import java.sql.*;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import sv.edu.udb.util.Connect;
import sv.edu.udb.models.Ticket;
/**
 *
 * @author Rick
 */
public class TicketDAO implements Dao<Ticket>{

    @Override
    public Optional<Ticket> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ticket> getAll() {
        Connect connection = null;
        List<Ticket> ticketFound = new ArrayList<>();
        try {
            connection = new Connect();
            connection.setRs("SELECT * FROM TICKETS");
            ResultSet ticketSet = connection.getRs();
            while (ticketSet.next()) {                
                Ticket ticket = new Ticket();
                ticket.setIdTicket(ticketSet.getInt("TICKETID"));
                ticket.setRequestId(ticketSet.getInt("REQUESTID"));
                ticket.setProjectID(ticketSet.getInt("PROJECTID"));
                ticket.setIdProgrammer(ticketSet.getInt("ID_PROGRAMADOR"));
                ticket.setIdTester(ticketSet.getInt("ID_TESTER"));
                ticket.setTicketStatus(ticketSet.getString("TICKET_STATUS"));
                ticket.setInternalCode(ticketSet.getString("INTERNALCODE"));
                ticket.setStartDate(ticketSet.getTimestamp("STARTDATE"));
                ticket.setEndDate(ticketSet.getTimestamp("ENDDATE"));
                
            }
        } catch (Exception e) {
        }
        return ticketFound;
    }

    @Override
    public boolean save(Ticket t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Ticket t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Ticket t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
