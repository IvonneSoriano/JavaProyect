/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.vistas.tickets;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import sv.edu.udb.models.Employee;
import sv.edu.udb.models.Ticket;
import sv.edu.udb.models.Request;
import sv.edu.udb.controllers.EmployeeController;
import sv.edu.udb.controllers.TicketsController;
import sv.edu.udb.controllers.RequestController;
import sv.edu.udb.models.Session;
import sv.edu.udb.util.Roles;
import java.util.ArrayList;
import java.util.List;
import sv.edu.udb.controllers.CommentController;
import sv.edu.udb.models.Comment;
import sv.edu.udb.util.RequestStatus;

/**
 *
 * @author kiss_
 */
public class ViewTicket extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewTicket
     */
    private TicketsController tController = new TicketsController();
    private Ticket ticket = new Ticket();
    private Request request = new Request();
    private RequestController rController = new RequestController();
    private EmployeeController eController = new EmployeeController();
    private boolean isJTI = false;
    private boolean isJAD = false;
    List<Comment> tList = new ArrayList<>();
    private RequestStatus rs;
    
    public ViewTicket() {
        initComponents();
    }
    
    public ViewTicket(int id) {
        initComponents();
        ticket = tController.showTicket(id);
        request = rController.getRequest(ticket.getRequestId());
        isJTI = (Session.employeeType == Roles.JEFE_DE_DESARROLLO.getRolId());
        isJAD = (Session.employeeType == Roles.JEFE_AREA_FUNCIONAL.getRolId());
         tList = new CommentController().getAllid(ticket.getRequestId());
        inputsState();
        fillData();
        fillComments();
        
    }
    
    public void refreshCP() {
        cmbProgr.removeAllItems();
        Employee e;
        e = eController.getEmployeeById(ticket.getIdProgrammer());
        cmbProgr.addItem(e.getFullName());
        btnAPro.setVisible(false);
    }
    
    public void refreshCQA() {
        cmbQA.removeAllItems();
        Employee e;
        e = eController.getEmployeeById(ticket.getIdTester());
        cmbQA.addItem(e.getFullName());
        btnATest.setVisible(false);
    }
    
    public void fillData() {
        Employee e;
        lvlTicketName.setText(ticket.getInternalCode());
        lblDescripcionAd.setText(request.getRequestDescription());
        if (isJTI) {
            if (ticket.getIdProgrammer() == 0) {
                fillProgrammers();
            } else {
                refreshCP();
            }
        } else {
            if (ticket.getIdProgrammer() > 0) {
                refreshCP();
//JOptionPane.showMessageDialog(rootPane, "Hola");
            }
        }
        
        if (isJAD) {
            if (ticket.getIdTester() == 0) {
                fillTesters();
            } else if (ticket.getIdTester() > 0) {
                refreshCQA();
            }
        } else {
            if (ticket.getIdTester() > 0) {
                refreshCQA();
            }
        }
        fillStatus();
        txtFechaStart.setText(ticket.getStartDDate());
//        JOptionPane.showMessageDialog(rootPane, ticket.getIdTicket());
fillAvance();
    }
    
    public void inputsState() {
//        cmbEstado.setEnabled(isJTI);

        chkbxQA.setEnabled(false);
        cmbProgr.setEnabled(false);
        cmbQA.setEnabled(false);
        btnAPro.setVisible(false);
        btnATest.setVisible(false);
        lblPorcentaje.setVisible(false);
        txtPorcentaje.setVisible(false);
        if (isJTI) {
            chkbxQA.setEnabled(true);
            cmbProgr.setEnabled(true);
            btnAPro.setVisible(true);
        }
        if (isJAD) {
            cmbQA.setEnabled(true);
        }
        if (Session.employeeType == Roles.PROGRAMADOR.getRolId()) {
            lblPorcentaje.setVisible(true);
            txtPorcentaje.setVisible(true);
        }
        if (ticket.getIdTester() == 0) {
            chkbxQA.setSelected(true);
            if (isJAD) {
                cmbQA.setEnabled(true);
                btnATest.setVisible(true);
                fillTesters();
            }
        }

//         }
    }
    
    public void fillProgrammers() {
        cmbProgr.removeAllItems();
        int cont = 0;
        List<Employee> p = eController.findEmployee(4, Session.deparmentId);
        
        for (Employee employee : p) {
            if (tController.showStatusTicket(employee.getEmployeeId(), "ID_PROGRAMADOR") == false) {
                cmbProgr.addItem(employee.getFullName());
                cont++;
            }
        }
        if (cont == 0) {
            cmbProgr.setEnabled(false);
            btnAPro.setVisible(false);
            JOptionPane.showMessageDialog(rootPane, "No hay programadores disponibles");
        }
        
    }
    
    public void fillTesters() {
        cmbQA.removeAllItems();
        int cont = 0;
        List<Employee> p = eController.findEmployee(2, Session.deparmentId);
        
        for (Employee employee : p) {
            if ((tController.showStatusTicket(employee.getEmployeeId(), "ID_TESTER")) == false) {
                cmbQA.addItem(employee.getFullName());
                cont++;
            }
        }
        if (cont == 0) {
            cmbQA.setEnabled(false);
            btnATest.setVisible(false);
            JOptionPane.showMessageDialog(rootPane, "No hay empleados administrativos disponibles");
        }
    }
    
    public void fillStatus() {
        cmbEstado.removeAllItems();
        cmbEstado.addItem(RequestStatus.ASIGNAR_PROGRAMADOR.toString());
        cmbEstado.addItem(RequestStatus.EN_DESARROLLO.toString());
        cmbEstado.addItem(RequestStatus.ESPERANDO_APROBACION_AREA_SOLICITANTE.toString());
        cmbEstado.addItem(RequestStatus.VENCIDO.toString());
        cmbEstado.addItem(RequestStatus.DEVUELTO_CON_OBSERVACIONES.toString());
        cmbEstado.setSelectedItem(ticket.getTicketStatus());
    }
    public void fillAvance(){
        float r = new TicketsController().getAvance(ticket.getIdTicket());
        String c = r + "%";
        txtAvance.setText(c);
    }

    public void fillComments(){
         

         if(!tList.isEmpty()){
             String matriz[][] = new String[tList.size()][3];
         
        for (int i = 0; i < tList.size(); i++) {
            matriz[i][0] = Integer.toString(tList.get(i).getCommentId());
            matriz[i][1] = Integer.toString(tList.get(i).getEmployeeId());
            matriz[i][2] = (tList.get(i).getCommentText());
        }
        tblComments.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                new String[]{
                    "ID", "User", "Comment"
                }
        ));
         }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlInfoTicket = new javax.swing.JPanel();
        lblAvance = new javax.swing.JLabel();
        txtAvance = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        chkbxQA = new javax.swing.JCheckBox();
        cmbQA = new javax.swing.JComboBox<>();
        lblFechaStart = new javax.swing.JLabel();
        lblFechaFinal = new javax.swing.JLabel();
        lblProgra = new javax.swing.JLabel();
        cmbProgr = new javax.swing.JComboBox<>();
        txtFechaStart = new javax.swing.JFormattedTextField();
        txtFechaFinal = new javax.swing.JFormattedTextField();
        btnAEstado = new javax.swing.JButton();
        btnAPro = new javax.swing.JButton();
        btnATest = new javax.swing.JButton();
        lvlTicketName = new javax.swing.JLabel();
        lblTDes = new javax.swing.JLabel();
        lblDescripcionAd = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtComentario = new javax.swing.JTextArea();
        lblActividad = new javax.swing.JLabel();
        btnComentario = new javax.swing.JButton();
        txtPorcentaje = new javax.swing.JFormattedTextField();
        lblPorcentaje = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblComments = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);

        pnlInfoTicket.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblAvance.setText("Avance");

        lblEstado.setText("Estado");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        chkbxQA.setText("Q/A Asignado");
        chkbxQA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkbxQAActionPerformed(evt);
            }
        });

        cmbQA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblFechaStart.setText("Fecha de inicio");

        lblFechaFinal.setText("Fecha de finalización");

        lblProgra.setText("Programador asignado");

        cmbProgr.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtFechaStart.setEditable(false);
        txtFechaStart.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd-MM-YYYY"))));

        txtFechaFinal.setEditable(false);
        txtFechaFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd-MM-YY"))));

        btnAEstado.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnAEstado.setText("Aceptar");
        btnAEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAEstadoActionPerformed(evt);
            }
        });

        btnAPro.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnAPro.setText("Aceptar");
        btnAPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAProActionPerformed(evt);
            }
        });

        btnATest.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnATest.setText("Aceptar");
        btnATest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnATestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInfoTicketLayout = new javax.swing.GroupLayout(pnlInfoTicket);
        pnlInfoTicket.setLayout(pnlInfoTicketLayout);
        pnlInfoTicketLayout.setHorizontalGroup(
            pnlInfoTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoTicketLayout.createSequentialGroup()
                .addGroup(pnlInfoTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInfoTicketLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(pnlInfoTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbProgr, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbQA, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFechaStart)
                            .addComponent(txtFechaFinal)
                            .addGroup(pnlInfoTicketLayout.createSequentialGroup()
                                .addGroup(pnlInfoTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFechaStart)
                                    .addComponent(lblFechaFinal)
                                    .addComponent(chkbxQA)
                                    .addComponent(lblEstado)
                                    .addComponent(lblAvance)
                                    .addComponent(lblProgra)
                                    .addComponent(txtAvance, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 96, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoTicketLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAEstado))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoTicketLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlInfoTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAPro, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnATest, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        pnlInfoTicketLayout.setVerticalGroup(
            pnlInfoTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoTicketLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblAvance)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAvance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblEstado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAEstado)
                .addGap(19, 19, 19)
                .addComponent(lblProgra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbProgr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAPro)
                .addGap(9, 9, 9)
                .addComponent(chkbxQA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbQA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnATest)
                .addGap(18, 18, 18)
                .addComponent(lblFechaStart)
                .addGap(11, 11, 11)
                .addComponent(txtFechaStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(lblFechaFinal)
                .addGap(18, 18, 18)
                .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(369, Short.MAX_VALUE))
        );

        lvlTicketName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lvlTicketName.setForeground(new java.awt.Color(35, 10, 89));
        lvlTicketName.setText("Nombre del Ticket");

        lblTDes.setText("Descripcion");

        lblDescripcionAd.setText("jLabel2");

        txtComentario.setColumns(20);
        txtComentario.setRows(5);
        txtComentario.setText("Comentario de jefe IT");
        jScrollPane1.setViewportView(txtComentario);

        lblActividad.setText("Actividad");

        btnComentario.setText("Insertar comentarios");
        btnComentario.setActionCommand("");
        btnComentario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComentarioActionPerformed(evt);
            }
        });

        txtPorcentaje.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##,#0%"))));

        lblPorcentaje.setText("Avance");

        tblComments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Username", "Comment", "%"
            }
        ));
        jScrollPane2.setViewportView(tblComments);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnComentario)
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblPorcentaje)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lblActividad)
                                            .addComponent(lblTDes)
                                            .addComponent(lblDescripcionAd, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(205, 205, 205))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(216, 216, 216)
                                .addComponent(lvlTicketName))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(pnlInfoTicket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(259, 259, 259))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lvlTicketName)
                .addGap(25, 25, 25)
                .addComponent(lblTDes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDescripcionAd, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPorcentaje))
                .addGap(19, 19, 19)
                .addComponent(btnComentario)
                .addGap(33, 33, 33)
                .addComponent(lblActividad)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlInfoTicket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkbxQAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkbxQAActionPerformed
        // TODO add your handling code here:
        if (tController.updateT(ticket.getIdTicket(), 0)) {
            chkbxQA.setSelected(true);
            chkbxQA.setEnabled(false);
            JOptionPane.showMessageDialog(rootPane, "Se le notificara al Jefe administrativo que debe agregar un tester");
        } else {
            JOptionPane.showMessageDialog(rootPane, "No se puede solicitar tester");
            chkbxQA.setSelected(false);
        }

    }//GEN-LAST:event_chkbxQAActionPerformed

    private void btnAEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAEstadoActionPerformed
        // TODO add your handling code here:
//        JOptionPane.showMessageDialog(rootPane, cmbEstado.getSelectedIndex());
        String nombre = (String) (cmbEstado.getSelectedItem());
        if (tController.updateS(ticket.getIdTicket(), nombre)) {
            ticket.setTicketStatus(nombre);
            JOptionPane.showMessageDialog(rootPane, "El estado ha cambiado correctamente");
        }
    }//GEN-LAST:event_btnAEstadoActionPerformed

    private void btnAProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAProActionPerformed
        // TODO add your handling code here:
        if (cmbProgr.getSelectedIndex() != -1) {
            JOptionPane.showMessageDialog(rootPane, "Seleccione algo porfi");
        } else {
            if (ticket.getIdProgrammer() == 0) {
                String nombre = (String) (cmbProgr.getSelectedItem());
                Employee e = new Employee();
                e = eController.getEmployee(e.getName(nombre), e.getLastname(nombre));
                if (tController.updateP(ticket.getIdTicket(), e.getEmployeeId())) {
                    ticket.setIdProgrammer(e.getEmployeeId());
                    ticket.setTicketStatus("EN_DESARROLLO");
                    cmbEstado.setSelectedItem("EN_DESARROLLO");
                    tController.updateS(ticket.getIdTicket(), "EN_DESARROLLO");
                    refreshCP();
                    JOptionPane.showMessageDialog(rootPane, "El programador se ha seleccionado correctamente");
                }
            }
        }
    }//GEN-LAST:event_btnAProActionPerformed

    private void btnATestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnATestActionPerformed
        // TODO add your handling code here:
        if (cmbProgr.getSelectedIndex() != -1) {
            JOptionPane.showMessageDialog(rootPane, "Seleccione algo porfi");
        } else {
            if (ticket.getIdTester() == 0) {
                String nombre = (String) (cmbQA.getSelectedItem());
                Employee e = new Employee();
                e = eController.getEmployee(e.getName(nombre), e.getLastname(nombre));
                if (tController.updateT(ticket.getIdTicket(), e.getEmployeeId())) {
                    ticket.setIdTester(e.getEmployeeId());
                    fillAvance();
                }
            }
        }
    }//GEN-LAST:event_btnATestActionPerformed

    private void btnComentarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComentarioActionPerformed
        // TODO add your handling code here:
        Comment nuevoComentario = new Comment();
        nuevoComentario.setEmployeeId(Session.employeeId);
        nuevoComentario.setCommentDate(new Timestamp(System.currentTimeMillis()));
        nuevoComentario.setCommentText(txtComentario.getText());
        nuevoComentario.setDepartmentId(Session.deparmentId);
        nuevoComentario.setRequestId(ticket.getRequestId());
        
        if (Session.employeeType == Roles.PROGRAMADOR.getRolId()) {
            if ((!txtComentario.getText().equals("") || !txtComentario.getText().equals(" ")) && (!txtPorcentaje.getText().equals(" ") || !txtPorcentaje.getText().equals(""))) {
                new CommentController().saveComment(nuevoComentario);
                fillComments();
                if (new TicketsController().updateAvance(ticket.getIdTicket(), Float.parseFloat(txtPorcentaje.getText()))) {
                    ticket.setAvance(new TicketsController().getAvance(ticket.getIdTicket()));
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "no");
            }
        } else {
            if (!txtComentario.getText().equals("") && !txtComentario.getText().equals(" ")) {
                new CommentController().saveComment(nuevoComentario);
                fillComments();
            }            
        }
        
    }//GEN-LAST:event_btnComentarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAEstado;
    private javax.swing.JButton btnAPro;
    private javax.swing.JButton btnATest;
    private javax.swing.JButton btnComentario;
    private javax.swing.JCheckBox chkbxQA;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbProgr;
    private javax.swing.JComboBox<String> cmbQA;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblActividad;
    private javax.swing.JLabel lblAvance;
    private javax.swing.JLabel lblDescripcionAd;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaFinal;
    private javax.swing.JLabel lblFechaStart;
    private javax.swing.JLabel lblPorcentaje;
    private javax.swing.JLabel lblProgra;
    private javax.swing.JLabel lblTDes;
    private javax.swing.JLabel lvlTicketName;
    private javax.swing.JPanel pnlInfoTicket;
    private javax.swing.JTable tblComments;
    private javax.swing.JTextField txtAvance;
    private javax.swing.JTextArea txtComentario;
    private javax.swing.JFormattedTextField txtFechaFinal;
    private javax.swing.JFormattedTextField txtFechaStart;
    private javax.swing.JFormattedTextField txtPorcentaje;
    // End of variables declaration//GEN-END:variables
}
