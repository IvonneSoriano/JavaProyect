/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.vistas;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import sv.edu.udb.models.Session;
import sv.edu.udb.util.Roles;
import sv.edu.udb.vistas.employessviews.CreateUser;
import sv.edu.udb.vistas.projectviews.*;
import sv.edu.udb.vistas.employessviews.ShowUsers;
import sv.edu.udb.vistas.tickets.CreateRequest;
import sv.edu.udb.vistas.employessviews.*;
import sv.edu.udb.vistas.requestview.*;
import sv.edu.udb.vistas.tickets.*;
import sv.edu.udb.controllers.TicketsController;
import java.util.List;
import sv.edu.udb.models.Ticket;

/**
 *
 * @author kiss_
 */
public class Contenedor extends javax.swing.JFrame {
    
    private static Logger logger = Logger.getLogger(Contenedor.class);
    private List<Ticket> cods= null;
    private TicketsController tController = new TicketsController();
    /**
     * Creates new form Contenedor
     */
    public Contenedor() {
            initComponents();
            setExtendedState(Contenedor.MAXIMIZED_BOTH);
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            if (Session.employeeType == Roles.EMPLEADO_AREA_FUNCIONAL.getRolId()
                    || Session.employeeType == Roles.PROGRAMADOR.getRolId()) {
                employeeMenu.setVisible(false);
            }
            if (Session.employeeType == Roles.JEFE_AREA_FUNCIONAL.getRolId()){
                cods= tController.checkTickets(Session.deparmentId);
                if(!cods.isEmpty()){
                     for (Ticket cod : cods) {
                        JOptionPane.showMessageDialog(projectMenu, "El ticket "+ cod.getInternalCode() + " necesita un tester");
                    }
                }    
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

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        ticketMenu = new javax.swing.JMenu();
        btnNewTicket = new javax.swing.JMenuItem();
        btnVerTicket = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        cerrarSesionMenuItem = new javax.swing.JMenuItem();
        employeeMenu = new javax.swing.JMenu();
        btnAgregarEmp = new javax.swing.JMenuItem();
        btnVerEmp = new javax.swing.JMenuItem();
        projectMenu = new javax.swing.JMenu();
        insertProject = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuBorrar = new javax.swing.JMenuItem();
        jListMenu = new javax.swing.JMenuItem();
        editProject = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ticketMenu.setMnemonic('T');
        ticketMenu.setText("Ticket");

        btnNewTicket.setMnemonic('o');
        btnNewTicket.setText("Nuevo Ticket");
        btnNewTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewTicketActionPerformed(evt);
            }
        });
        ticketMenu.add(btnNewTicket);

        btnVerTicket.setMnemonic('s');
        btnVerTicket.setText("Ver Ticket");
        btnVerTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerTicketActionPerformed(evt);
            }
        });
        ticketMenu.add(btnVerTicket);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        ticketMenu.add(saveAsMenuItem);

        cerrarSesionMenuItem.setMnemonic('x');
        cerrarSesionMenuItem.setText("Cerrar Sesion");
        cerrarSesionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSesionMenuItemActionPerformed(evt);
            }
        });
        ticketMenu.add(cerrarSesionMenuItem);

        menuBar.add(ticketMenu);

        employeeMenu.setMnemonic('e');
        employeeMenu.setText("Empleados");

        btnAgregarEmp.setMnemonic('t');
        btnAgregarEmp.setText("Agregar empleado");
        btnAgregarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEmpActionPerformed(evt);
            }
        });
        employeeMenu.add(btnAgregarEmp);

        btnVerEmp.setMnemonic('y');
        btnVerEmp.setText("Ver Empleados");
        btnVerEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerEmpActionPerformed(evt);
            }
        });
        employeeMenu.add(btnVerEmp);

        menuBar.add(employeeMenu);

        projectMenu.setMnemonic('P');
        projectMenu.setText("Proyectos");

        insertProject.setMnemonic('c');
        insertProject.setText("Nuevo Proyecto");
        insertProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertProjectActionPerformed(evt);
            }
        });
        projectMenu.add(insertProject);

        jMenuBorrar.setText("Eliminar proyecto");
        projectMenu.add(jMenuBorrar);

        jListMenu.setText("Ver Proyectos");
        jListMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jListMenuActionPerformed(evt);
            }
        });
        projectMenu.add(jListMenu);

        editProject.setMnemonic('a');
        editProject.setText("Editar Proyecto");
        projectMenu.add(editProject);

        menuBar.add(projectMenu);

        jMenu1.setForeground(new java.awt.Color(35, 10, 89));
        jMenu1.setText("Peticiones");

        jMenuItem1.setText("Ver peticiones");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        menuBar.add(jMenu1);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cerrarSesionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSesionMenuItemActionPerformed
        Session.logOut();
        new Login().setVisible(true);
        this.dispose();
        return;

    }//GEN-LAST:event_cerrarSesionMenuItemActionPerformed
    
    public void closeForms() {
        desktopPane.removeAll();
        desktopPane.updateUI();
    }
    private void insertProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertProjectActionPerformed
        // TODO add your handling code here:
        closeForms();
        ProjectInsert insert = new ProjectInsert();
        desktopPane.add(insert);
        insert.show();
    }//GEN-LAST:event_insertProjectActionPerformed

    private void jListMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jListMenuActionPerformed
        // TODO add your handling code here:
        closeForms();
        try {
            ProjectList list = new ProjectList();
            desktopPane.add(list);
            list.show();
        } catch (Exception e) {
        }

    }//GEN-LAST:event_jListMenuActionPerformed

    private void btnNewTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewTicketActionPerformed
        closeForms();
        CreateRequest r = new CreateRequest();
        desktopPane.add(r);
        r.show();
    }//GEN-LAST:event_btnNewTicketActionPerformed

    private void btnVerEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerEmpActionPerformed
        // TODO add your handling code here:
        closeForms();
        ShowUsers cu = new ShowUsers();
        desktopPane.add(cu);
        cu.show();
    }//GEN-LAST:event_btnVerEmpActionPerformed

    private void btnAgregarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEmpActionPerformed
        // TODO add your handling code here:
        closeForms();
        CreateUser cu = new CreateUser();
        desktopPane.add(cu);
        cu.show();
    }//GEN-LAST:event_btnAgregarEmpActionPerformed

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
        // TODO add your handling code here:
         closeForms();
         ViewTicket cu = new ViewTicket(1);
        desktopPane.add(cu);
        cu.show();
    }//GEN-LAST:event_saveAsMenuItemActionPerformed
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        closeForms();
        RequestList rl = new RequestList();
        desktopPane.add(rl);
        rl.show();
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnVerTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerTicketActionPerformed
        // TODO add your handling code here:
        closeForms();
        TicketsList tl = new TicketsList();
        desktopPane.add(tl);
        tl.show();
    }//GEN-LAST:event_btnVerTicketActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Contenedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Contenedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Contenedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Contenedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Contenedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnAgregarEmp;
    private javax.swing.JMenuItem btnNewTicket;
    private javax.swing.JMenuItem btnVerEmp;
    private javax.swing.JMenuItem btnVerTicket;
    private javax.swing.JMenuItem cerrarSesionMenuItem;
    public static javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem editProject;
    private javax.swing.JMenu employeeMenu;
    private javax.swing.JMenuItem insertProject;
    private javax.swing.JMenuItem jListMenu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu projectMenu;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenu ticketMenu;
    // End of variables declaration//GEN-END:variables

}
