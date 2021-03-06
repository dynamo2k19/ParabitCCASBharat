/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parabitccasbharat;

import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
/**
 *
 * @author rishu
 */
public class PbtEmpDashboard extends javax.swing.JFrame implements MouseListener{
    PbtEmpManage obempmanage;
    ParabitDBC db;
    PbtLoginData oblogin;
    public PbtEmpDashboard(PbtLoginData ob) {
        setTitle("Dashboard");
        initComponents();
        oblogin = ob;
        db = new ParabitDBC();
        String qry2 = "SELECT EmpName FROM pbtemployeetable2 WHERE CEID = '" + oblogin.ceid + "'";
        try
        {
            db.rs3 = db.stm.executeQuery(qry2);
            db.rs3.next();
            String name = db.rs3.getString(1);
            lempnm.setText("Employee Name : " + name);
            //lempnm.setVisible(false);
        }
        catch(Exception e)
        {
            System.out.println("SQL ERROR: "+ e);
        }
        loadNotification();
        btnworkassign.setEnabled(false);
        if (oblogin.grade == 4)
        {
            btnworkassign.setEnabled(true);
        }
        notiftable.addMouseListener(this);
    }
private void loadNotification()
{
    Date currdate = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
    
    String area = "", area2 = "";
        switch(oblogin.grade)
        {
            case 2: area = "AreaState"; area2 = oblogin.state; break;
            case 3: area = "AreaDist"; area2 = oblogin.dist; break;
            case 4: area = "AreaCity"; area2 = oblogin.city; break;
            case 5: area = "AreaCity"; area2 = oblogin.city; break;
        }
        String qry = "";
        if (oblogin.grade == 1)
        {
            qry = "SELECT * FROM pbtnotification WHERE RecieverCeId = '" + oblogin.ceid + "' AND MsgStatus = 0 ORDER BY Time DESC";
        }
        else if (oblogin.grade == 2)
        {
            qry = "SELECT * FROM pbtnotification WHERE (RecieverCeId = '" + oblogin.ceid + "' AND MsgStatus = 0) OR (((NotType = 3 AND (SenderCeID = '93425')) OR (NotType = 2 AND RecieverCeID = '"+oblogin.ceid+"')) AND ReadTime >= '"+LocalDate.now()+"') ORDER BY Time DESC";
        System.out.println(qry);
        }
        else
        {
            qry = "SELECT * FROM pbtnotification WHERE (RecieverCeId = '" + oblogin.ceid + "' AND MsgStatus = 0) OR (NotType = 3 AND ((SenderCeId IN (SELECT CRepEmpID FROM pbtemployeetable2 WHERE "+area+" = '"+area2+"')) OR (SenderCeID = '93425')) AND SenderCeID != '"+oblogin.ceid+"' AND ReadTime >= '"+LocalDate.now()+"') OR (NotType = 2 AND RecieverCeID IN (SELECT CRepEmpID FROM pbtemployeetable2 WHERE "+area+" = '"+area2+"') AND ReadTime >= '"+LocalDate.now()+"') ORDER BY Time DESC";
        System.out.println(qry);
        }
        try         
        {
            db.rs1 = db.stm.executeQuery(qry);
            DefaultTableModel tm = (DefaultTableModel)notiftable.getModel();
            tm.setRowCount(0);
            
            while(db.rs1.next())
            {
                Object o[] = {db.rs1.getString("SenderCeid"), db.rs1.getTimestamp("Time").toString().substring(0, 19), db.rs1.getString("Message"),
                              db.rs1.getInt("NotId"), db.rs1.getInt("NotType")};
                tm.addRow(o);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
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

        lempnm = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnmanage_emp = new javax.swing.JButton();
        btnworkassign = new javax.swing.JButton();
        btn_notif = new javax.swing.JButton();
        btnempsummary = new javax.swing.JButton();
        btncensummary = new javax.swing.JButton();
        btnlogout = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        notiftable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lempnm.setText("Employee Name: user123");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel1.setText("PARABIT CCAS - BHARAT");

        btnmanage_emp.setText("Manage Employee");
        btnmanage_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmanage_empActionPerformed(evt);
            }
        });

        btnworkassign.setText("Work Assignment");
        btnworkassign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnworkassignActionPerformed(evt);
            }
        });

        btn_notif.setText("Notification");
        btn_notif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_notifActionPerformed(evt);
            }
        });

        btnempsummary.setText("Employee Summary");
        btnempsummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnempsummaryActionPerformed(evt);
            }
        });

        btncensummary.setText("Census Summary");
        btncensummary.setPreferredSize(new java.awt.Dimension(189, 81));

        btnlogout.setText("Logout");
        btnlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogoutActionPerformed(evt);
            }
        });

        notiftable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sender CEID", "Time", "Message", "NotID", "NotType"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(notiftable);

        jLabel2.setText("Notifications:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lempnm)
                        .addGap(125, 125, 125))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnmanage_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(btnworkassign, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnlogout, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(btn_notif, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(134, Short.MAX_VALUE))))
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(btnempsummary, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119)
                        .addComponent(btncensummary, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(btnlogout, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(lempnm, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnmanage_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnworkassign, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_notif, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncensummary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnempsummary, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogoutActionPerformed
         System.exit(0);
    }//GEN-LAST:event_btnlogoutActionPerformed

    private void btnmanage_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmanage_empActionPerformed
         obempmanage = new PbtEmpManage(this, true, oblogin);
         //this.setVisible(false);
         obempmanage.setVisible(true);
         
    }//GEN-LAST:event_btnmanage_empActionPerformed

    private void btnempsummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnempsummaryActionPerformed
         PbtEmpSummary obempsumm = new PbtEmpSummary(this, true, oblogin);
         obempsumm.setVisible(true);
    }//GEN-LAST:event_btnempsummaryActionPerformed

    private void btn_notifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_notifActionPerformed
         PbtNotification obnotif = new PbtNotification(this, true, oblogin);
         obnotif.setVisible(true);
    }//GEN-LAST:event_btn_notifActionPerformed

    private void btnworkassignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnworkassignActionPerformed
        PbtWorkAssignment obwrk = new PbtWorkAssignment(this, oblogin);
        obwrk.setVisible(true);
    }//GEN-LAST:event_btnworkassignActionPerformed

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
            java.util.logging.Logger.getLogger(PbtEmpDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PbtEmpDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PbtEmpDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PbtEmpDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PbtEmpDashboard obj = new PbtEmpDashboard(new PbtLoginData());
                obj.setVisible(true);
                obj.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_notif;
    private javax.swing.JButton btncensummary;
    private javax.swing.JButton btnempsummary;
    private javax.swing.JButton btnlogout;
    private javax.swing.JButton btnmanage_emp;
    private javax.swing.JButton btnworkassign;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lempnm;
    private javax.swing.JTable notiftable;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        String qry = "";
        int r = notiftable.getSelectedRow();
        int notid = (int)notiftable.getValueAt(r, 3);
        int not_type = (int)notiftable.getValueAt(r, 4);
        
        Point pos = notiftable.getMousePosition();
        if (r == notiftable.rowAtPoint(pos))
        {
            if (not_type == 1)
            {
                qry = "UPDATE pbtnotification SET MsgStatus = 1, ReadTime = '"+LocalDate.now()+"' WHERE NotId = "+notid;
                try
                {
                db.stm.executeUpdate(qry);
                loadNotification();
                }
                catch(Exception ex)
                {
                ex.printStackTrace();
                }
            }
            //System.out.println(qry);  
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
