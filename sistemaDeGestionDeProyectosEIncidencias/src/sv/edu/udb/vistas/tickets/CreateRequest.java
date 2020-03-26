/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.vistas.tickets;

import java.awt.Dimension;
import java.sql.Timestamp;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import sv.edu.udb.controllers.CommentController;
import sv.edu.udb.controllers.ProjectsController;
import sv.edu.udb.controllers.RequestController;
import sv.edu.udb.controllers.RequestTypeController;
import sv.edu.udb.controllers.TicketsController;
import sv.edu.udb.models.Comment;
import sv.edu.udb.models.CommentDAO;
import sv.edu.udb.models.Project;
import sv.edu.udb.models.Request;
import sv.edu.udb.models.RequestType;
import sv.edu.udb.models.Session;
import sv.edu.udb.models.Ticket;
import sv.edu.udb.models.TicketDAO;
import sv.edu.udb.util.RequestStatus;
import sv.edu.udb.util.RequestTypes;
import sv.edu.udb.util.Roles;
import sv.edu.udb.vistas.Contenedor;
import static sv.edu.udb.vistas.Contenedor.desktopPane;
import sv.edu.udb.controllers.DeparmentController;
import sv.edu.udb.models.Deparment;
import sv.edu.udb.models.Session;

/**
 *
 * @author Rick
 */
public class CreateRequest extends javax.swing.JInternalFrame {

    private static Logger logger = Logger.getLogger(CreateRequest.class);

    /**
     * Creates new form CreateRequest
     */
    public CreateRequest() {
        initComponents();
        cargarTiposSolicitud();
        cargarProyectosExistentesPorDepartamento();

        //center internalframe based on main container
        Dimension desktopSize = desktopPane.getSize();
        Dimension jInternalFrameSize = this.getSize();
        this.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);

        // Ocultando controles de aceptar/rechazar si no es jefe de desarrollo
        if (Session.employeeType != Roles.JEFE_DE_DESARROLLO.getRolId()) {
            lblAgregarComentario.setVisible(false);
            txtAreaAgregarComentario.setVisible(false);
            btnAceptar.setVisible(false);
            btnRechazar.setVisible(false);
            jScrollPane3.setVisible(false);
            this.setSize(410, 360);
        } else {
            btnCrear.setVisible(false);
            btnLimpiar.setVisible(false);
        }
    }

    public void cargarSolicitudExistente(Request r) {
        ProjectsController pc = new ProjectsController();
        RequestTypeController rtc = new RequestTypeController();

        this.txtDescripcionRequerimiento.setText(r.getRequestDescription());
        this.txtDescripcionRequerimiento.setEnabled(false);
        this.setTitle(this.getTitle() + " - ID: " + r.getId());

        // seteando el valor previamente seleccionado al dropdown de tipo solicitud
        String requestTypeTxt = rtc.findRequestTypeById(r.getIdTypeRequest()).getRequestTypeName();

        // Get number of items
        int idFoundAt = -1;
        int num = cbTipoSolicitud.getItemCount();

        // Get items
        for (int i = 0; i < num; i++) {
            Object item = cbTipoSolicitud.getItemAt(i);
            if (item.toString().equals(requestTypeTxt)) {
                idFoundAt = i;
                break;
            }
        }
        this.cbTipoSolicitud.setSelectedIndex(idFoundAt);
        this.cbTipoSolicitud.setEnabled(false);

        // seteando el valor previamente seleccionado al dropdown de proyecto existente        
        String projectName = pc.findById(r.getProjectId()).getProjectName();

        // Get number of items
        idFoundAt = -1;
        num = cbProyectosExistentes.getItemCount();

        // Get items
        for (int i = 0; i < num; i++) {
            Object item = cbProyectosExistentes.getItemAt(i);
            if (item.toString().contains(projectName)) {
                idFoundAt = i;
                break;
            }
        }
        this.cbProyectosExistentes.setSelectedIndex(idFoundAt);
        this.cbProyectosExistentes.setEnabled(false);
    }

    private void cargarTiposSolicitud() {
        logger.info("Cargando tipos de solicitud en formulario");
        RequestTypeController controller = new RequestTypeController();
        List<RequestType> types = controller.findRequestTypes();

        cbTipoSolicitud.removeAllItems();
        for (RequestType type : types) {
            cbTipoSolicitud.addItem(type.getRequestTypeName());
        }
        cbTipoSolicitud.setSelectedIndex(-1);
    }

    private void cargarProyectosExistentesPorDepartamento() {
        logger.info("Cargando proyectos existente filtrados por departamento en formulario");
        ProjectsController controller = new ProjectsController();
        List<Project> projects = controller.projectsByDepto();

        cbProyectosExistentes.removeAllItems();
        for (Project project : projects) {
            cbProyectosExistentes.addItem(project.getProjectName() + " ("
                    + project.getProjectsId() + ")");
        }
        cbProyectosExistentes.setSelectedIndex(-1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbTipoSolicitud = new javax.swing.JComboBox<>();
        lblTipoSolicitud = new javax.swing.JLabel();
        cbProyectosExistentes = new javax.swing.JComboBox<>();
        lblProyectosExistentes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcionRequerimiento = new javax.swing.JTextArea();
        lblDescripcionReq = new javax.swing.JLabel();
        btnCrear = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaAgregarComentario = new javax.swing.JTextArea();
        btnAceptar = new javax.swing.JButton();
        btnRechazar = new javax.swing.JButton();
        lblAgregarComentario = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Nuevo Requerimiento");

        cbTipoSolicitud.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTipoSolicitud.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipoSolicitudItemStateChanged(evt);
            }
        });

        lblTipoSolicitud.setText("Tipo de solicitud: ");

        cbProyectosExistentes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblProyectosExistentes.setText("Proyecto existente: ");

        txtDescripcionRequerimiento.setColumns(20);
        txtDescripcionRequerimiento.setRows(5);
        jScrollPane1.setViewportView(txtDescripcionRequerimiento);

        lblDescripcionReq.setText("Descripcion de requerimiento:");

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        txtAreaAgregarComentario.setColumns(20);
        txtAreaAgregarComentario.setRows(5);
        jScrollPane3.setViewportView(txtAreaAgregarComentario);

        btnAceptar.setForeground(new java.awt.Color(51, 204, 0));
        btnAceptar.setText("Aceptar");
        btnAceptar.setToolTipText("");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnRechazar.setForeground(new java.awt.Color(255, 102, 102));
        btnRechazar.setText("Rechazar");
        btnRechazar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRechazarActionPerformed(evt);
            }
        });

        lblAgregarComentario.setText("Agregar Comentario:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRechazar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAgregarComentario)
                                .addGap(34, 34, 34)
                                .addComponent(btnCrear)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpiar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblProyectosExistentes, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbProyectosExistentes, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblDescripcionReq)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTipoSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbTipoSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTipoSolicitud)
                    .addComponent(cbTipoSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProyectosExistentes)
                    .addComponent(cbProyectosExistentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(lblDescripcionReq)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnCrear)
                    .addComponent(lblAgregarComentario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnRechazar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        clearElements();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void clearElements() {
        txtDescripcionRequerimiento.setText("");
        cbProyectosExistentes.setSelectedIndex(-1);
        cbTipoSolicitud.setSelectedIndex(-1);
    }
    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        // enviar notificacion a jefe de desarrollo del area
        // setear estado a: en espera de respuesta
        // mostrar dialogo con ID

        // when no option is selected in this combobox it's because there's no need
        // for a new project
        Request r = new Request();
        RequestTypeController typeController = new RequestTypeController();

        if (cbProyectosExistentes.getSelectedIndex() != -1) {
            String selectedItemText = String.valueOf(
                    cbProyectosExistentes.getSelectedItem());
            r.setProjectId(Integer.parseInt(selectedItemText.split("[\\\\(||\\\\)]")[1]));
        }

        r.setIdTypeRequest(typeController.findRequestTypeByName(String.valueOf(
                cbTipoSolicitud.getSelectedItem())).getId());
        r.setRequestDate(new Timestamp(System.currentTimeMillis()));

        r.setRequestDescription(txtDescripcionRequerimiento.getText());
        r.setRequestStatus(RequestStatus.EN_ESPERA.name());
        r.setDepartmentId(Session.deparmentId);

        if (new RequestController().insertRequest(r)) {
            clearElements();
            this.dispose();

            JOptionPane.showMessageDialog(null,
                    "Requerimiento enviado satisfactoriamente al jefe de desarrollo "
                    + "de su departamento. Por favor, espere su decisión. \n"
                    + "El codigo del requerimiento para su seguimiento es: "
                    + new RequestController().findLastRequest().getId(),
                    "Nuevo Requerimiento - Exito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Ha ocurrido un error por favor intente mas tarde. ",
                    "Nuevo Requerimiento - Error", JOptionPane.WARNING_MESSAGE);
        }

        // actualizando contenedor - how?
        desktopPane.updateUI();
    }//GEN-LAST:event_btnCrearActionPerformed

    private void cbTipoSolicitudItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipoSolicitudItemStateChanged
        String selectedItemText = String.valueOf(cbTipoSolicitud.getSelectedItem());
        if (selectedItemText.contains(RequestTypes.NUEVA_FUNCIONALIDAD.name())
                || selectedItemText.contains(RequestTypes.CORRECCION.name())) {
            cbProyectosExistentes.setSelectedIndex(0);
            cbProyectosExistentes.setEnabled(true);
        } else {
            cbProyectosExistentes.setSelectedIndex(-1);
            cbProyectosExistentes.setEnabled(false);
        }
    }//GEN-LAST:event_cbTipoSolicitudItemStateChanged

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        logger.info("Aceptando solicitud y creando ticket asociado");
        RequestController rc = new RequestController();
        String requestID = this.getTitle().split(":")[1].trim();
        Ticket nuevoTicket = new Ticket();

//        PARA EL GENERADOR DE CODIGO
        Deparment d = new Deparment();
        DeparmentController dc = new DeparmentController();
        d = dc.showDeparment(Session.deparmentId);
//        JOptionPane.showMessageDialog(rootPane, generateInternalCode(d.getDepartmentName()));
        nuevoTicket.setRequestId(Integer.parseInt(requestID));
        if (cbProyectosExistentes.getSelectedIndex() != -1) {
            String selectedItemText = String.valueOf(
                    cbProyectosExistentes.getSelectedItem());
            nuevoTicket.setProjectID(Integer.parseInt(selectedItemText.split("[\\\\(||\\\\)]")[1]));
        }
        nuevoTicket.setIdProgrammer(0);
        nuevoTicket.setIdTester(-1);
        nuevoTicket.setTicketStatus(RequestStatus.ASIGNAR_PROGRAMADOR.name());
        nuevoTicket.setInternalCode(generateInternalCode(d.getDepartmentName()));
        nuevoTicket.setStartDate(new Timestamp(System.currentTimeMillis()));
        nuevoTicket.setEndDate(new Timestamp(System.currentTimeMillis()));
        new TicketsController().saveTicket(nuevoTicket);

        logger.info("Agregando nuevo comentario");
        Comment nuevoComentario = new Comment();
        nuevoComentario.setEmployeeId(Session.employeeId);
        nuevoComentario.setCommentDate(new Timestamp(System.currentTimeMillis()));
        nuevoComentario.setCommentText(txtAreaAgregarComentario.getText());
        nuevoComentario.setDepartmentId(Session.deparmentId);
        nuevoComentario.setRequestId(Integer.parseInt(requestID));
        new CommentController().saveComment(nuevoComentario);

        //actualizar estado de la solicitud original
        logger.info("Actualizando estado de solicitud original");
        Request r = new Request();
        r.setId(Integer.parseInt(requestID));
        r.setRequestStatus(RequestStatus.SOLICITUD_ACEPTADA.name());
        rc.updateRequestStatus(r);

        JOptionPane.showMessageDialog(null,
                "Requerimiento aceptado satisfactoriamente. \n\n "
                + "Se ha creado el ticket con el codigo interno: "
                + nuevoTicket.getInternalCode(),
                "Requerimiento Aceptado - Exito", JOptionPane.INFORMATION_MESSAGE);

        this.dispose();
       
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnRechazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRechazarActionPerformed
        logger.info("Rechazando solicitud");
        RequestController rc = new RequestController();
        String requestID = this.getTitle().split(":")[1].trim();

        logger.info("Agregando nuevo comentario");
        Comment nuevoComentario = new Comment();
        nuevoComentario.setEmployeeId(Session.employeeId);
        nuevoComentario.setCommentDate(new Timestamp(System.currentTimeMillis()));
        nuevoComentario.setCommentText(txtAreaAgregarComentario.getText());
        nuevoComentario.setDepartmentId(Session.deparmentId);
        nuevoComentario.setRequestId(Integer.parseInt(requestID));
        new CommentController().saveComment(nuevoComentario);

        //actualizar estado de la solicitud original
        logger.info("Actualizando estado de solicitud original");
        Request r = new Request();
        r.setId(Integer.parseInt(requestID));
        r.setRequestStatus(RequestStatus.SOLICITUD_RECHAZADA.name());
        rc.updateRequestStatus(r);

        JOptionPane.showMessageDialog(null,
                "Requerimiento ha sido rechazado. \n\n "
                + "No se creo ningun ticket. ",
                "Requerimiento Rechazado - Warning", JOptionPane.WARNING_MESSAGE);

        this.dispose();
        desktopPane.updateUI();
    }//GEN-LAST:event_btnRechazarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRechazar;
    private javax.swing.JComboBox<String> cbProyectosExistentes;
    private javax.swing.JComboBox<String> cbTipoSolicitud;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAgregarComentario;
    private javax.swing.JLabel lblDescripcionReq;
    private javax.swing.JLabel lblProyectosExistentes;
    private javax.swing.JLabel lblTipoSolicitud;
    private javax.swing.JTextArea txtAreaAgregarComentario;
    private javax.swing.JTextArea txtDescripcionRequerimiento;
    // End of variables declaration//GEN-END:variables

    public String generateInternalCode(String name) {
        String code = "";
        code = name.toUpperCase().substring(0, 3);
        Calendar cal = Calendar.getInstance();
        String a = Integer.toString(cal.get(1)).substring(0, 2);
        code = code + a + ramdomNum();
        return code;
    }

    public String ramdomNum() {
        TicketsController c = new TicketsController();
        String num = "";
        int n;
        for (int i = 0; i < 3; i++) {
//        n = Math.floor(Math.random()*9);
            n = (int) Math.floor(Math.random() * 6 + 1);
            num += Integer.toString(n);
        }
        while (c.checkIC(num) > 0) {
            num = ramdomNum();
        }
        return num;
    }
}
