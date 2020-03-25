/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import sv.edu.udb.util.Connect;

/**
 *
 * @author Rick
 */
public class RequestDAO implements Dao<Request> {

    private static Logger logger = Logger.getLogger(RequestDAO.class);

    @Override
    public Optional<Request> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Request> getAll() {
         Connect connection = null;
         List<Request> requestFound = new ArrayList<>();
        try {
            connection = new Connect();
            connection.setRs("SELECT * FROM REQUEST");
            ResultSet requestSet = connection.getRs();
            while (requestSet.next()) {                
                Request request = new Request();
                request.setId(requestSet.getInt("REQUESTID"));
                request.setIdTypeRequest(requestSet.getInt("REQUESTTYPEID"));
                request.setRequestDate(requestSet.getTimestamp("REQUESTDATE"));
                request.setRequestDescription(requestSet.getString("REQUESTDESCRIPTION"));
                request.setRequestStatus(requestSet.getString("REQUESTSTATUS"));
                request.setProjectId(requestSet.getInt("PROJECTID"));
                requestFound.add(request);
            }
        } catch (SQLException e) {
             logger.error("Error processing SELECT query in save method. Message: " + e.getMessage());
        }
        return requestFound;
    }

    @Override
    public boolean save(Request t) {
        try {
            Connect connection = new Connect();

            int result = connection.setQuery("INSERT INTO `gestion_tickets`.`requests`"
                    + " (`REQUESTTYPEID`, `REQUESTDATE`, `REQUESTDESCRIPTION`,"
                    + " `REQUESTSTATUS`) VALUES (" + t.getIdTypeRequest() + ", "
                    + t.getRequestDate() + " , '" + t.getRequestDescription()
                    + "',' " + t.getRequestStatus() + "');");

            if (result <= 0) {
                logger.warn("INSERT to Requests table has failed");
                return false;
            } else {
                logger.info("INSERT to Requests table has successfully completed!");
                return true;
            }

        } catch (Exception e) {
            logger.error("Error processing INSERT query in save method. Message: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Request t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Request t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Optional<Request> getNextRequestId() {
        Request lastRequest = null;
        try {
            Connect connection = new Connect();
            connection.setRs("SELECT * FROM requests ORDER BY requestid DESC LIMIT 1;;");
            ResultSet requests = (ResultSet) connection.getRs();

            while (requests.next()) {
                lastRequest = new Request();
                lastRequest.setId(requests.getInt("REQUESTID"));
                lastRequest.setIdTypeRequest(requests.getInt("REQUESTTYPEID"));
                lastRequest.setRequestDate(requests.getTimestamp("REQUESTDATE"));
                lastRequest.setRequestDescription(requests.getString("REQUESTDESCRIPTION"));
                lastRequest.setRequestStatus(requests.getString("REQUESTSTATUS"));
            }
        } catch (Exception e) {
            logger.error("Error processing ResultSet in getEmployeeByUsername() method. Message: " + e.getMessage());
        }
        return Optional.ofNullable(lastRequest);
    }
}
