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
public class RequestTypeDAO implements Dao<RequestType> {

    private static Logger logger = Logger.getLogger(RequestTypeDAO.class);

    @Override
    public Optional<RequestType> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RequestType> getAll() {
        Connect connection = null;
        List<RequestType> requestTypesFound = new ArrayList<RequestType>();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getAll() method. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT * FROM requesttypes;");
            ResultSet rTypes = (ResultSet) connection.getRs();

            while (rTypes.next()) {
                RequestType rType = new RequestType();
                rType.setId(rTypes.getInt("REQUESTTYPEID"));
                rType.setRequestTypeName(rTypes.getString("REQUESTTYPENAME"));
                requestTypesFound.add(rType);
            }

        } catch (SQLException e) {
            logger.error("Error processing ResultSet in getAll() method. Message: " + e.getMessage());
        } finally {
            try {
                connection.cerrarConexion();
            } catch (SQLException ex) {
                logger.error("Error closing conecction in getAll() method. Message: " + ex.getMessage());
            }

        }
        return requestTypesFound;
    }

    @Override
    public boolean save(RequestType t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(RequestType t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(RequestType t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
