/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import sv.edu.udb.util.Connect;

/**
 *
 * @author Rick
 */
public class TicketDAO implements Dao<Ticket> {

    private static Logger logger = Logger.getLogger(TicketDAO.class);

    @Override
    public Optional<Ticket> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ticket> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
