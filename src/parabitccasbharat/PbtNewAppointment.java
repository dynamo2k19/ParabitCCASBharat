/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parabitccasbharat;

import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PbtNewAppointment extends javax.swing.JDialog implements MouseListener{

    ParabitDBC db, db1;
    PbtLoginData oblogin;
    String state, dist, city;
    DefaultTableModel dtm;
    
    public PbtNewAppointment(PbtEmpManage nob, boolean modal, PbtLoginData ob) {
        super(nob, modal);
        setTitle("New Appointment");
        initComponents();
        this.setLocationRelativeTo(nob);
        db = new ParabitDBC();
        db1 = new ParabitDBC();
        oblogin = ob;
        
        empTypeJCB(oblogin.grade);
        loadTable();
        String qry = "SELECT * FROM pbtemployeetable2 WHERE CEID = '" + oblogin.ceid + "'";
        try
        {
            db.rs2 = db.stm.executeQuery(qry);
            db.rs2.next();
            state = db.rs2.getString("AreaState");
            dist = db.rs2.getString("AreaDist");
            city = db.rs2.getString("AreaCity");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        ldist.setVisible(false);
        lpop.setVisible(false);
        ltehsil.setVisible(false);
        newappointtable.addMouseListener(this);
    }

    private void empTypeJCB(int empgrade)
    {
       String qry = "SELECT DISTINCT States FROM pbtstates5 ORDER BY States";
       String qry3 = "SELECT DISTINCT District FROM pbtstates5 WHERE States = (SELECT AreaState FROM pbtemployeetable2 WHERE CEID = '" + oblogin.ceid + "') ORDER BY District";
       String qry4 = "SELECT DISTINCT SubDist FROM pbtstates5 WHERE District = (SELECT AreaDist FROM pbtemployeetable2 WHERE CEID = '" + oblogin.ceid + "') ORDER BY SubDist";
       try
       {
           db.rs1 = db.stm.executeQuery(qry);
           while(db.rs1.next())
           {
               jcbstate.addItem(db.rs1.getString(1));
           }
           
           db1.rs1 = db1.stm.executeQuery(qry3);
           while(db1.rs1.next())
           {
               
               jcbdist.addItem(db1.rs1.getString(1));
           }
           
           db1.rs2 = db1.stm.executeQuery(qry4);
           while(db1.rs2.next())
           {
               jcbcity.addItem(db1.rs2.getString(1));
           }
           jcbstate.setEnabled(false);
           jcbdist.setEnabled(false);
           jcbcity.setEnabled(false);
           if (empgrade == 1)
           {
               jcbstate.setEnabled(true);
           }
           else if (empgrade == 2)
           {
               jcbdist.setEnabled(true);
           }
           else if (empgrade == 3)
           {
               jcbcity.setEnabled(true);
           }
       }
       catch(Exception e)
       {
            System.out.println(e);
       }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox3 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        newappointtable = new javax.swing.JTable();
        jcbstate = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        ldist = new javax.swing.JLabel();
        ltehsil = new javax.swing.JLabel();
        lpop = new javax.swing.JLabel();
        jcbdist = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jcbcity = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        newappointtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "SNo", "Govt Emp ID", "Emp Name", "WorkExp", "Emp Designation"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(newappointtable);
        if (newappointtable.getColumnModel().getColumnCount() > 0) {
            newappointtable.getColumnModel().getColumn(2).setPreferredWidth(150);
        }

        jcbstate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {""}));
        jcbstate.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbstateItemStateChanged(evt);
            }
        });
        jcbstate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbstateActionPerformed(evt);
            }
        });

        jLabel1.setText("STATE");

        ldist.setText("Total Districts:");

        ltehsil.setText("Total Tehsil:");

        lpop.setText("Total Population:");

        jcbdist.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {""}));
        jcbdist.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbdistItemStateChanged(evt);
            }
        });
        jcbdist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbdistActionPerformed(evt);
            }
        });

        jLabel2.setText("DISTRICT");

        jcbcity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {""}));
        jcbcity.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbcityItemStateChanged(evt);
            }
        });
        jcbcity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbcityActionPerformed(evt);
            }
        });

        jLabel3.setText("CITY");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(ldist, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115)
                .addComponent(ltehsil, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(lpop, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbstate, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbdist, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbcity, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbstate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbdist, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbcity, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ldist, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ltehsil, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lpop, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbstateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbstateItemStateChanged

    }//GEN-LAST:event_jcbstateItemStateChanged

    private void jcbstateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbstateActionPerformed
        String st = (String)jcbstate.getSelectedItem();
        String qry = "SELECT COUNT(DISTINCT District), COUNT(DISTINCT SubDist), SUM(TPopulation) FROM pbtstates5 WHERE States = '" + st + "'";
        try
        {
            db.rs4 = db.stm.executeQuery(qry);
            db.rs4.next();
            ldist.setText("Total Districts: "+db.rs4.getInt(1));
            ltehsil.setText("Total Tehsils: "+db.rs4.getInt(2));
            lpop.setText("Total Population: "+db.rs4.getLong(3)); 
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        ldist.setVisible(true);
        lpop.setVisible(true);
        ltehsil.setVisible(true);
    }//GEN-LAST:event_jcbstateActionPerformed

    private void jcbdistItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbdistItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbdistItemStateChanged

    private void jcbdistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbdistActionPerformed
        String dist = (String)jcbdist.getSelectedItem();
        String qry = "SELECT COUNT(DISTINCT SubDist), SUM(TPopulation) FROM pbtstates5 WHERE District = '" + dist + "'";
        try
        {
            db.rs5 = db.stm.executeQuery(qry);
            db.rs5.next();
            ltehsil.setText("Total Tehsils: "+db.rs5.getInt(1));
            lpop.setText("Total Population: "+db.rs5.getLong(2)); 
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        //ldist.setVisible(true);
        lpop.setVisible(true);
        ltehsil.setVisible(true);
    }//GEN-LAST:event_jcbdistActionPerformed

    private void jcbcityItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbcityItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbcityItemStateChanged

    private void jcbcityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbcityActionPerformed
        String city = (String)jcbcity.getSelectedItem();
        String qry = "SELECT SUM(TPopulation) FROM pbtstates5 WHERE SubDist = '" + city + "'";
        try
        {
            db.rs5 = db.stm.executeQuery(qry);
            db.rs5.next();
            lpop.setText("Total Population: "+db.rs5.getInt(1)); 
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        //ldist.setVisible(true);
        lpop.setVisible(true);
        //ltehsil.setVisible(true);
    }//GEN-LAST:event_jcbcityActionPerformed

 private void empAppoint(MouseEvent evt)
 {
     String setid = "";
     int gr = oblogin.grade + 1;
     String sgrade = "" + gr;
    newappointtable = (JTable)evt.getSource();
    int r = newappointtable.getSelectedRow();
    int c = newappointtable.getSelectedColumn();
    String qry = "";
    String qry2 = "SELECT GEID FROM pbtemployeetable2 WHERE GEID = '" + newappointtable.getValueAt(r, 1) + "'";
    try
    {
        db1.rs3 = db1.stm.executeQuery(qry2);
        db1.rs3.next();
        setid = sgrade + db1.rs3.getString(1);
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
                
    if (newappointtable.getColumnName(c).equals("Emp Name"))
    {
                    
        switch (oblogin.grade + 1)
        {
            case 2: qry = "UPDATE pbtemployeetable2 SET CEID = '" + setid + "', CRepEmpID = '"+ oblogin.ceid + "', Status = 1, AreaState = '" + jcbstate.getSelectedItem() + "', AreaDist = '" + jcbstate.getSelectedItem() + "', AreaCity = '" + jcbstate.getSelectedItem() + "' WHERE GEID = '"+newappointtable.getValueAt(r, 1) + "'";
                        break;
            case 3: qry = "UPDATE pbtemployeetable2 SET CEID = '" + setid + "', CRepEmpID = '"+ oblogin.ceid + "', Status = 1, AreaState = '" + state + "', AreaDist = '" + jcbdist.getSelectedItem() + "', AreaCity = '" + jcbdist.getSelectedItem() + "' WHERE GEID = '"+newappointtable.getValueAt(r, 1) + "'";
                        break;
            case 4: qry = "UPDATE pbtemployeetable2 SET CEID = '" + setid + "', CRepEmpID = '"+ oblogin.ceid + "', Status = 1, AreaState = '" + state + "', AreaDist = '" + dist + "', AreaCity = '" + jcbcity.getSelectedItem() + "' WHERE GEID = '"+newappointtable.getValueAt(r, 1) + "'";
                        break;
            default: qry = "UPDATE pbtemployeetable2 SET CEID = '" + setid + "', CRepEmpID = '"+ oblogin.ceid + "', Status = 1, AreaState = '" + state + "', AreaDist = '" + dist + "', AreaCity = '" + city + "' WHERE GEID = '"+newappointtable.getValueAt(r, 1) + "'";
        }
                    
        try
        {
            if (javax.swing.JOptionPane.showConfirmDialog(null, "Are you sure you want to appoint " + newappointtable.getValueAt(r, c)) == 0)
            {
                PbtCal ob = new PbtCal(this, true);
                ob.setVisible(true);
                System.out.println(ob.date);
                db.stm.executeUpdate(qry);
                mail((String)newappointtable.getValueAt(r, c), ob.date);
                javax.swing.JOptionPane.showMessageDialog(null, newappointtable.getValueAt(r, c)+" successfully appointed!");
            }
        }
        catch(Exception e)
        {
            System.out.println("SQL exception: "+e);
        }
    } 
 }
 
 void mail(String name, String date)
 {
     Sender sender = new Sender();
     Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.user", sender.uname);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        //props.put("mail.smtp.socketFactory.port", "465");
        //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //props.put("mail.smtp.socketFactory.fallback", "false"); 
        try
        {
            
            Session session = Session.getInstance(props, null);
            session.setDebug(true);
            MimeMessage message = new MimeMessage(session);
            message.setText("Congrats " + name + " You Are Selected For Census 2021!!"
                    + " Kindly Report Before " + date);
            message.setSubject("Parabit CCAS-Bharat");
            message.setFrom(new InternetAddress(sender.uname));
            message.addRecipient(RecipientType.TO, new InternetAddress("kairostimeyt1@gmail.com"));
            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", sender.uname, sender.upwd);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
         javax.swing.JOptionPane.showMessageDialog(null, "EMAIL SENT SUCCESSFULLY");
 }
 
 private void loadTable()
{
    int sgrade = oblogin.grade + 1;
    String qry3 = "SELECT * FROM pbtemployeetable2 WHERE Grade = '" + sgrade + "' AND Status = '0'";
    try
    {
        db.rs3 = db.stm.executeQuery(qry3);
        dtm = (DefaultTableModel)newappointtable.getModel();
        dtm.setRowCount(0);
        int sno = 1;
        while(db.rs3.next())
        {
            Object o[] = {db.rs3.getString("SNo"), db.rs3.getString("GEID"), db.rs3.getString("EmpName"), db.rs3.getString("WorkExp"), db.rs3.getString("EmpDesig")};
            o[0] = sno;
            dtm.addRow(o);
            sno++;
        }
    }
    catch(Exception e)
    {
        System.out.println(e);
    }
            
}

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
            java.util.logging.Logger.getLogger(PbtNewAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PbtNewAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PbtNewAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PbtNewAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PbtLoginData obj = new PbtLoginData();
               PbtNewAppointment dialog = new PbtNewAppointment(new PbtEmpManage(new PbtEmpDashboard(obj), true, obj), true, obj);
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
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbcity;
    private javax.swing.JComboBox<String> jcbdist;
    private javax.swing.JComboBox<String> jcbstate;
    private javax.swing.JLabel ldist;
    private javax.swing.JLabel lpop;
    private javax.swing.JLabel ltehsil;
    private javax.swing.JTable newappointtable;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        int sgrade = oblogin.grade + 1;
        int f = 0;
        String area = "", st = "";
        switch(sgrade)
        {
            case 2: area = "AreaState"; st = (String)jcbstate.getSelectedItem();  
                    break;
            case 3: area = "AreaDist"; st = (String)jcbdist.getSelectedItem();
                    break;
            case 4: area = "AreaCity"; st = (String)jcbcity.getSelectedItem();
                    break;
            default: area = "AreaCity";
        }
       
        String qry = "SELECT * FROM pbtemployeetable2 WHERE Grade = " + sgrade + " AND Status = '1'";
        
        try
        {
            db.rs6 = db.stm.executeQuery(qry);
            while (db.rs6.next())
            {
                if (st.equals(db.rs6.getString(area)))
                {
                    f = 1;
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        
        if ((!st.equals("")) || (sgrade == 5))
        {
            if (e.getClickCount() == 2)
            {
                if (f == 0)
                {
                    empAppoint(e);
                    loadTable();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Employee Already Appointed for " + st + "!!!", "WARNING", JOptionPane.WARNING_MESSAGE);
                }
            }
            
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
