/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.vistas;

import org.apache.log4j.Logger;
import sv.edu.udb.models.Session;
import sv.edu.udb.util.Roles;
import sv.edu.udb.vistas.employessviews.CreateUser;
import sv.edu.udb.vistas.projectviews.*;
import sv.edu.udb.vistas.employessviews.ShowUsers;

/**
 *
 * @author kiss_
 */
public class Contenedor extends javax.swing.JFrame {

    private static Logger logger = Logger.getLogger(Contenedor.class);

    /**
     * Creates new form Contenedor
     */
    public Contenedor() {
        initComponents();
        setExtendedState(Contenedor.MAXIMIZED_BOTH);
//        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        if (Session.employeeType == Roles.EMPLEADO_AREA_FUNCIONAL.getRolId()
                || Session.employeeType == Roles.PROGRAMADOR.getRolId()) {
            employeeMenu.setVisible(false);
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
        btnEditarEmp = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        projectMenu = new javax.swing.JMenu();
        insertProject = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuBorrar = new javax.swing.JMenuItem();
        jListMenu = new javax.swing.JMenuItem();
        editProject = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ticketMenu.setMnemonic('T');
        ticketMenu.setText("Ticket");

        btnNewTicket.setMnemonic('o');
        btnNewTicket.setText("Nuevo Ticket");
        ticketMenu.add(btnNewTicket);

        btnVerTicket.setMnemonic('s');
        btnVerTicket.setText("Ver Ticket");
        ticketMenu.add(btnVerTicket);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
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

        btnEditarEmp.setMnemonic('p');
        btnEditarEmp.setText("Editar Empleados");
        employeeMenu.add(btnEditarEmp);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        employeeMenu.add(deleteMenuItem);

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
        jMenu1.setText("jMenu1");
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

    public void closeForms(){
        desktopPane.removeAll();
        desktopPane.updateUI();
    }
    private void btnAgregarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEmpActionPerformed
        // TODO add your handling code here:
        closeForms();
        CreateUser cu = new CreateUser();
        desktopPane.add(cu);
        cu.show();
    }//GEN-LAST:event_btnAgregarEmpActionPerformed

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

    private void btnVerEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerEmpActionPerformed
        // TODO add your handling code here:
        closeForms();
         ShowUsers cu = new ShowUsers();
        desktopPane.add(cu);
        cu.show();
    }//GEN-LAST:event_btnVerEmpActionPerformed

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
    private javax.swing.JMenuItem btnEditarEmp;
    private javax.swing.JMenuItem btnNewTicket;
    private javax.swing.JMenuItem btnVerEmp;
    private javax.swing.JMenuItem btnVerTicket;
    private javax.swing.JMenuItem cerrarSesionMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    public static javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem editProject;
    private javax.swing.JMenu employeeMenu;
    private javax.swing.JMenuItem insertProject;
    private javax.swing.JMenuItem jListMenu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu projectMenu;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenu ticketMenu;
    // End of variables declaration//GEN-END:variables

}
