/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parabitccasbharat;

import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
/**
 *
 * @author rishu
 */
public class PbtTransfer extends javax.swing.JDialog implements MouseListener{

    PbtLoginData oblogin;
    ParabitDBC db, db1;
    String name, ceid, trans_state, trans_dist, trans_city, trans_ceid;
    int s;
    public PbtTransfer(PbtEmpManage nob, boolean modal, PbtLoginData ob) {
        super(nob, modal);
        setTitle("Transfer");
        initComponents();
        this.setLocationRelativeTo(nob);
        oblogin = ob;
        
        db = new ParabitDBC(); 
        db1 = new ParabitDBC();
        loadTable(0);
        apptemptable.addMouseListener(this);
        lreplace.setVisible(false);
    }

    void loadTable(int f)
    {
        DefaultTableModel tm = (DefaultTableModel)apptemptable.getModel();
        tm.setRowCount(0);
        String state = "", dist = "", city = "";
        String qry = "";
        int sgrade = oblogin.grade + 1;
        try
        {
            String qry2 = "SELECT * FROM pbtemployeetable2 WHERE CEID = '" + oblogin.ceid + "'";
            db.rs2 = db.stm.executeQuery(qry2);
            db.rs2.next();
            state = db.rs2.getString("AreaState");
            dist = db.rs2.getString("AreaDist");
            city = db.rs2.getString("AreaCity");
            String area = "", reparea = "";
            switch(sgrade)
            {
                case 3: area = "AreaState"; reparea = state; break;
                case 4: area = "AreaDist"; reparea = dist; break;
                case 5: area = "AreaCity"; reparea = city; break;
            }
            if (sgrade == 2)
            {
                if (f == 0)
                {
                    qry = "SELECT * FROM pbtemployeetable2 WHERE Grade = " + sgrade + " AND Status = '1'";
                    s = 0;
                    lreplace.setVisible(false);
                }
                else
                {
                    qry = "SELECT * FROM pbtemployeetable2 WHERE Grade = " + sgrade + " AND Status = '1' AND CEID != '"+ceid+"'";
                    s = 1;
                    lreplace.setVisible(true);
                }
            }
            else
            {
                if (f == 0)
                {
                    qry = "SELECT * FROM pbtemployeetable2 WHERE Grade = " + sgrade + " AND Status = '1' AND " + area + " = '" + reparea + "'";
                    s = 0;
                    lreplace.setVisible(false);
                }
                else
                {
                    qry = "SELECT * FROM pbtemployeetable2 WHERE Grade = " + sgrade + " AND Status = '1' AND " + area + " = '" + reparea + "' AND CEID != '"+ceid+"'";
                    s = 1;
                    lreplace.setVisible(true);
                }
                
            }
            
            db.rs1 = db.stm.executeQuery(qry);
            int sno = 1;
            while (db.rs1.next())
            {
                Object o[] = {db.rs1.getString("SNo"), db.rs1.getString("CEID"), db.rs1.getString("CRepEmpID"), db.rs1.getString("EmpName"), 
                                db.rs1.getString("AreaState"), db.rs1.getString("AreaDist"), db.rs1.getString("AreaCity"), db.rs1.getString("EmpDesig"), db.rs1.getString("EmpANo")};
                o[0] = sno;
                tm.addRow(o);
                sno++;
            }
        }
        catch(Exception e)
        {
            System.out.println("SQL Exception..." + e);
        }
    }
    
    void transfer()
    {
        
        loadTable(1);
        
        trans_ceid = ceid;
        String qry = "SELECT * FROM pbtemployeetable2 WHERE CEID = '" + ceid + "'";
        try
        {
            db.rs3 = db.stm.executeQuery(qry);
            db.rs3.next();
            trans_state = db.rs3.getString("AreaState");
            trans_dist = db.rs3.getString("AreaDist");
            trans_city = db.rs3.getString("AreaCity");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    void transfer2()
    {
        String trans_state2, trans_dist2, trans_city2;
        String qry = "SELECT * FROM pbtemployeetable2 WHERE CEID = '" + ceid + "'";
        try
        {
            db.rs4 = db.stm.executeQuery(qry);
            db.rs4.next();
            trans_state2 = db.rs4.getString("AreaState");
            trans_dist2 = db.rs4.getString("AreaDist");
            trans_city2 = db.rs4.getString("AreaCity");
            String qry2 = "UPDATE pbtemployeetable2 SET AreaState = '"+trans_state2+"', AreaDist = '"+trans_dist2+"', AreaCity = '"+trans_city2+"', Status = 2 WHERE CEID = '"+trans_ceid+"'";
            String qry3 = "UPDATE pbtemployeetable2 SET AreaState = '"+trans_state+"', AreaDist = '"+trans_dist+"', AreaCity = '"+trans_city+"', Status = 2 WHERE CEID = '"+ceid+"'";
            String qry4 = "UPDATE pbtemployeetable2 SET CRepEmpID = 'R"+trans_ceid+"' WHERE CRepEmpID = '"+ceid+"'";
            String qry5 = "UPDATE pbtemployeetable2 SET CRepEmpID = 'R"+ceid+"' WHERE CRepEmpID = '"+trans_ceid+"'";
            db.stm.executeUpdate(qry2);
            db.stm.executeUpdate(qry3);
            db.stm.executeUpdate(qry4);
            db.stm.executeUpdate(qry5);
            javax.swing.JOptionPane.showMessageDialog(null, "Successfully Transferred!");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        loadTable(0);
    }
    
    void dismiss()
    {
        String qry2 = "UPDATE pbtemployeetable2 SET CRepEmpID = 'T" + oblogin.ceid + "' WHERE CRepEmpID = '"+ceid+"'";
        String qry = "UPDATE pbtemployeetable2 SET CRepEmpID = null, AreaCity = null, AreaDist = null, AreaState = null, Status = '-1' WHERE CEID = '" + ceid + "'";
        if (javax.swing.JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Dismiss " + name + " ??") == 0)
        {
            try
            {
                db.stm.executeUpdate(qry2);
                db.stm.executeUpdate(qry);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
            if (javax.swing.JOptionPane.showConfirmDialog(null, "Do You Want To Appoint New Employee For This Area?") == 0)
            {
                
                PbtNewAppointment ob = new PbtNewAppointment(new PbtEmpManage(new PbtEmpDashboard(oblogin), true, oblogin), true, oblogin);
                ob.setVisible(true);
            }
            loadTable(0);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        apptemptable = new javax.swing.JTable();
        lreplace = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        apptemptable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SNo", "CEID", "CReporting ID", "Employee Name", "State", "District", "City", "Designation", "Aadhar No"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(apptemptable);

        lreplace.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lreplace.setText("Select An Employee To Replace With:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lreplace, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(254, 254, 254))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lreplace, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(PbtTransfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PbtTransfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PbtTransfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PbtTransfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PbtLoginData obj = new PbtLoginData();
                PbtTransfer dialog = new PbtTransfer(new PbtEmpManage(new PbtEmpDashboard(obj), true, obj), true, obj);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable apptemptable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lreplace;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        int r = apptemptable.getSelectedRow();
        ceid = (String)apptemptable.getValueAt(r, 1);
        name = (String)apptemptable.getValueAt(r, 3);
        if (e.getClickCount() == 2 && s == 0)
        {
            PbtTransferDismiss ob = new PbtTransferDismiss(this, true);
            ob.setVisible(true);
        }
        
        if (e.getClickCount() == 1 && s == 1)
        {
            transfer2();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
