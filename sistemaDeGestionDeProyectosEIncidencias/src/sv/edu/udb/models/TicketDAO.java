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
public class TicketDAO implements Dao<Ticket>{
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
        try {
            Connect connection = new Connect();

            String sql = "INSERT INTO `gestion_tickets`.`tickets` (`REQUESTID`, "
                    + "`PROJECTID`, `ID_PROGRAMADOR`, `ID_TESTER`, `TICKET_STATUS`,"
                    + "`INTERNALCODE`, `STARTDATE`, `ENDDATE`) VALUES("
                    + t.getRequestId() + ", " + t.getProjectID() + ", "
                    + t.getIdProgrammer() + ", " + t.getIdTester() + ", '"
                    + t.getTicketStatus() + "', '" + t.getInternalCode() + "', '"
                    + t.getStartDate() + "', '" + t.getEndDate() + "');";
            int result = connection.setQuery(sql);

            if (result <= 0) {
                logger.warn("INSERT to tickets table has failed");
                return false;
            } else {
                logger.info("INSERT to tickets table has successfully completed!");
                return true;
            }

        } catch (Exception e) {
            logger.error("Error processing INSERT query in save method. Message: " + e.getMessage());
            return false;
        }
    }


    @Override
    public boolean update(Ticket t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Ticket t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Ticket getOne(int id){
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
//                foundTicket.setIdProgrammer(ticket.getInt("ID_PROGRAMADOR"));
//                foundTicket.setIdTester(ticket.getInt("	ID_TESTER"));
//                foundTicket.setTicketStatus(ticket.getString("TICKET_STATUS"));
                foundTicket.setInternalCode(ticket.getString("INTERNALCODE"));
                foundTicket.setStartDate(ticket.getTimestamp("STARTDATE"));
                foundTicket.setEndDate(ticket.getTimestamp("ENDDATE"));
                

            }
        } catch (Exception e) {
            logger.error("Error processing ResultSet in getEmployeeByUsername() method. Message: " + e.getMessage());
        }
        return foundTicket;
    }
    
    public boolean updateProgrammer(int idTicket, int idP){
        try {
            Connect connection = new Connect();

            int result = connection.setQuery("UPDATE `gestion_tickets`.`TICKETS` SET "
                    + "`ID_PROGRAMADOR` = " + idP
                    + " WHERE `TICKETID` = " + idTicket+ ";"
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
    
}
