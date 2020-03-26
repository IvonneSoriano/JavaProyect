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
public class CommentDAO implements Dao<Comment> {

    private static final Logger logger = Logger.getLogger(CommentDAO.class);

    @Override
    public Optional<Comment> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comment> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(Comment c) {
        try {
            Connect connection = new Connect();

            String sql = "INSERT INTO `gestion_tickets`.`comments` "
                    + "(`EMPLOYEEID`, `DEPARTMENTID`, `REQUESTID`, `COMMENTTEXT`, "
                    + "`COMMENTDATE`) VALUES (" + c.getEmployeeId() + ", "
                    + c.getDepartmentId() + " , " + c.getRequestId()
                    + ", '" + c.getCommentText() + "', '" + c.getCommentDate() + "');";

            int result = connection.setQuery(sql);

            if (result <= 0) {
                logger.warn("INSERT to Comments table has failed.");
                return false;
            } else {
                logger.info("INSERT to Comments table has successfully completed!");
                return true;
            }

        } catch (Exception e) {
            logger.error("Error processing INSERT query in save method. Message: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Comment t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Comment t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
