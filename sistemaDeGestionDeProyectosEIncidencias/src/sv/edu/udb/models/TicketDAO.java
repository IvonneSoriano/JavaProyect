/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import java.sql.ResultSet;
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
public class TicketDAO implements Dao<Ticket> {

    private static Logger logger = Logger.getLogger(EmployeeDAO.class);

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

    public Ticket getOne(int id) {
        Ticket foundTicket = null;
        try {
            Connect connection = new Connect();
            connection.setRs("SELECT * FROM TICKETS WHERE TICKETID=" + id + ";");
            ResultSet ticket = (ResultSet) connection.getRs();

            while (ticket.next()) {
                foundTicket = new Ticket();
                foundTicket.setIdTicket(ticket.getInt("TICKETID"));
                foundTicket.setRequestId(ticket.getInt("REQUESTID"));
                foundTicket.setProjectID(ticket.getInt("PROJECTID"));
                foundTicket.setIdProgrammer(ticket.getInt("ID_PROGRAMADOR"));
                foundTicket.setIdTester(ticket.getInt("ID_TESTER"));
                foundTicket.setTicketStatus(ticket.getString("TICKET_STATUS"));
                foundTicket.setInternalCode(ticket.getString("INTERNALCODE"));
                foundTicket.setStartDate(ticket.getTimestamp("STARTDATE"));
                foundTicket.setEndDate(ticket.getTimestamp("ENDDATE"));

            }
        } catch (Exception e) {
            logger.error("Error processing ResultSet in getOne() method. Message: " + e.getMessage());
        }
        return foundTicket;
    }

    public boolean updateProgrammer(int idTicket, int idP) {
        try {
            Connect connection = new Connect();

            int result = connection.setQuery("UPDATE `gestion_tickets`.`TICKETS` SET "
                    + "`ID_PROGRAMADOR` = " + idP
                    + " WHERE `TICKETID` = " + idTicket + ";"
            );
            if (result <= 0) {
                logger.error("UPDATE to Tickets table has failed");
                return false;
            } else {
                logger.info("UPDATE to Tickets table has successfully completed!");
                return true;
            }
        } catch (Exception e) {
            logger.error("Error processing UPDATE query in updateProgrammer() method. Message: " + e.getMessage());
            return false;
        }
    }

    public boolean checkEmployee(int id, String p) {
        Connect connection = null;
        boolean status = false;
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getAll() method. Message: " + ex.getMessage());
        }
        try {

            int result = connection.setQuery("SELECT COUNT(*) FROM `tickets` WHERE "+ p + " = " + id + ";");
            if(result > 0){
            result = connection.setQuery("SELECT COUNT(*) FROM `tickets` WHERE " + p + " = "+ id +" AND TICKET_STATUS='DESARROLLO';");    
            if(result==0){
                status = false;
            }
            else{
                status = true;
            }
            }
            else{
                status = false;
            }

        } catch (SQLException e) {
            logger.error("Error processing ResultSet in checkEmployee() method. Message: " + e.getMessage());
        } finally {
            try {
                connection.cerrarConexion();
            } catch (SQLException ex) {
                logger.error("Error closing conecction in checkEmployee() method. Message: " + ex.getMessage());
            }

        }
        return status;
    }
    
    public boolean updateQA(int idTicket, int idP){
       try {
            Connect connection = new Connect();

            int result = connection.setQuery("UPDATE `gestion_tickets`.`TICKETS` SET "
                    + "ID_TESTER = " + idP
                    + "  WHERE TICKETID = " + idTicket+ ";"
            );
            if (result <= 0) {
                logger.error("UPDATE to Tickets table has failed");
                return false;
            } else {
                logger.info("UPDATE to Tickets table has successfully completed!");
                return true;
            }
        } catch (Exception e) {
            logger.error("Error processing UPDATE query in updateQA method. Message: " + e.getMessage());
            return false;
        }
    }

}
