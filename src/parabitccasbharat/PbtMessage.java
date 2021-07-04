/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parabitccasbharat;

import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author rishu
 */
public class PbtMessage extends javax.swing.JDialog {

    PbtLoginData oblogin;
    ParabitDBC db, db1;
    int mtype;
    String recid, recstate, recdist, reccity;
    public PbtMessage(PbtNotification nob, PbtLoginData ob, int msgtype) {
        super(nob, true);
        setTitle("Message");
        initComponents();
        this.setLocationRelativeTo(nob);
        oblogin = ob;
        db = new ParabitDBC();
        db1 = new ParabitDBC();
        mtype = msgtype;
    }
void generalMsg()
{
        String senderid = oblogin.ceid, receiverid = "";
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        int grd = oblogin.grade;
        Date date = new Date();
        
        Timestamp ts = new Timestamp(date.getTime());
        String timestamp = ts.toString().substring(0, 19);
        int notid = new java.util.Random().nextInt(100000); 
        String qry = "INSERT INTO pbtnotification VALUES('"+senderid+"', null, '"+timestamp+"', '"+tamsg.getText()+"', 0, '"+LocalDate.now().plusDays(7)+"', "+notid+", 3)";
        try
        {
            db.stm.executeUpdate(qry);
            javax.swing.JOptionPane.showMessageDialog(null, "Message Sent Successfully!!!");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        this.dispose();
}
void chainInfo(String rstate, String rdist, String rcity)
{
    recstate = rstate;
    recdist = rdist;
    reccity = rcity;
}
void chainMsg(String recstate, String recdist, String reccity)
{
    String senderid = oblogin.ceid, receiverid = "";
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        int grd = oblogin.grade;
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        String timestamp = ts.toString().substring(0, 19);
        int notid = new java.util.Random().nextInt(100000);
        String area = "", area2 = "", qry = "";
        int sgrade = oblogin.grade + 1;

            switch(sgrade)
            {
                case 2: area = "AreaState"; area2 = recstate; break;
                case 3: area = "AreaDist"; area2 = recdist; break;
                case 4: area = "AreaCity"; area2 = reccity; break;
            }
                    
            qry = "SELECT CEID FROM pbtemployeetable2 WHERE " + area + " = '" + area2 + "' AND Grade = " + sgrade;
            System.out.println(qry);
            try
            {
                db.rs2 = db.stm.executeQuery(qry);
                db.rs2.next();
                receiverid = db.rs2.getString("CEID");
                String qry2 = "INSERT INTO pbtnotification VALUES('"+oblogin.ceid+"', '"+receiverid+"', '"+timestamp+"', '"+tamsg.getText()+"', 0, '"+LocalDate.now().plusDays(7)+"', "+notid+", 2)";
                db1.stm.executeUpdate(qry2); 
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            javax.swing.JOptionPane.showMessageDialog(null, "Message Sent Successfully!!!");
        this.dispose();
}

void individualMsg(String recid)
{
    String senderid = oblogin.ceid, receiverid = "";
        //SimpleDateFormat sql = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        int grd = oblogin.grade;
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        String timestamp = ts.toString().substring(0, 19);
        int notid = new java.util.Random().nextInt(100000);
        String area = "", area2 = "", qry = "";
        int sgrade = oblogin.grade + 1;
        qry = "INSERT INTO pbtnotification VALUES('"+oblogin.ceid+"', '"+recid+"', '"+timestamp+"', '"+tamsg.getText()+"', 0, null, "+notid+", 1)";
            try
            {
                db.stm.executeUpdate(qry);
                javax.swing.JOptionPane.showMessageDialog(null, "Message Sent Successfully!!!");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        this.dispose();
}

void individualInfo(String mob, String name, String desig)
{
    String qry = "SELECT CEID FROM pbtemployeetable2 WHERE EmpOffMob = '" + mob + "'";
    String qry2 = "SELECT CEID FROM pbtemployeetable2 WHERE EmpName = '" + name + "' AND EmpDesig = '" + desig + "'";
    try
    {
        db.rs1 = db.stm.executeQuery(qry);
        if (db.rs1.next())
        {
            recid = db.rs1.getString(1);
        }
        else
        {
            db.rs2 = db.stm.executeQuery(qry2);
            db.rs2.next();
            recid = db.rs2.getString(1);
        }
        
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lmsg = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tamsg = new javax.swing.JTextArea();
        btnsend = new javax.swing.JButton();
        btncancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lmsg.setText("Enter Message:");

        tamsg.setColumns(20);
        tamsg.setRows(5);
        jScrollPane1.setViewportView(tamsg);

        btnsend.setText("Send");
        btnsend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsendActionPerformed(evt);
            }
        });

        btncancel.setText("Cancel");
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lmsg, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(308, 308, 308)
                        .addComponent(btnsend, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(btncancel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lmsg, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btncancel, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btnsend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsendActionPerformed
         if (mtype == 1)
         {
             individualMsg(recid);
         }
         else if (mtype == 2)
         {
             chainMsg(recstate, recdist, reccity);
         }
         else if (mtype == 3)
         {
             generalMsg();
         }
    }//GEN-LAST:event_btnsendActionPerformed

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
         this.dispose();
    }//GEN-LAST:event_btncancelActionPerformed

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
            java.util.logging.Logger.getLogger(PbtMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PbtMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PbtMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PbtMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PbtMessage dialog = new PbtMessage(new PbtNotification(new PbtEmpDashboard(new PbtLoginData()), true, new PbtLoginData()), new PbtLoginData(), 3);
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
    private javax.swing.JButton btncancel;
    private javax.swing.JButton btnsend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lmsg;
    private javax.swing.JTextArea tamsg;
    // End of variables declaration//GEN-END:variables
}
