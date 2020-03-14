package sv.edu.udb.models;

import java.util.*;

/**
 *
 * @author Imer
 */
public class Ticket {

    private int id, request, programmer, tester, projectID;
    private String ticketStatus, internalCode;
    private Date startDate, endDate;

    public Ticket() {
    }

    public void setIdTicket(int ticketId) {
        this.id = ticketId;
    }

    public int getIdTicket() {
        return id;
    }

    public void setRequestId(int requestTicket) {
        this.request = requestTicket;
    }

    public int getRequestId() {
        return request;
    }

    public void setIdProgrammer(int programmer) {
        this.programmer = programmer;
    }

    public int getIdProgrammer() {
        return programmer;
    }

    public void setIdTester(int idTester) {
        this.tester = idTester;
    }

    public int getIdTester() {
        return tester;
    }

    public void setTicketStatus(String status) {
        this.ticketStatus = status;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
}