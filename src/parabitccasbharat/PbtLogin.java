/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parabitccasbharat;

import java.util.Random;
import javax.swing.JOptionPane;
/**
 *
 * @author rishu
 */
public class PbtLogin extends javax.swing.JFrame {
    PbtLoginData oblogin;
    ParabitDBC db1;
    String sotp, sotp2, empid, status, state, dist, city;
    int grade;
    public PbtLogin() {
        setTitle("Login");
        initComponents();
        jlabel3.setVisible(false);
        pfotp.setVisible(false);
        btnlogin.setVisible(false);
        db1 = new ParabitDBC();
    }

    void transfer()
    {
        int sgrade = grade + 1;
        
        if (status.equals("2"))
        {
           String qry = "UPDATE pbtemployeetable2 SET CRepEmpID = '"+empid+"' WHERE CRepEmpID = 'R"+empid+"'"; 
           String qry2 = "UPDATE pbtemployeetable2 SET Status = '1' WHERE CEID = '" + empid + "'";
           try
           {
               db1.stm.executeUpdate(qry);
               db1.stm.executeUpdate(qry2);
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
        }
        else if (grade != 1 && grade != 5)
        {
            String qry = "SELECT CRepEmpID FROM pbtemployeetable2 WHERE CEID = '"+empid+"'";
            String area = "", area2 = "";
            switch(sgrade)
            {
                case 3: area = "AreaState"; area2 = state; break;
                case 4: area = "AreaDist"; area2 = dist; break;
                case 5: area = "AreaCity"; area2 = city;break;
            }
            try
            {
                db1.rs2 = db1.stm.executeQuery(qry);
                db1.rs2.next();
                String bossid = db1.rs2.getString(1);
                String qry2 = "UPDATE pbtemployeetable2 SET CRepEmpID = '"+empid+"' WHERE CRepEmpID = 'T"+bossid+"' AND "+area+" = '"+area2+"'";
                System.out.println(qry2);
                db1.stm.executeUpdate(qry2);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlabel1 = new javax.swing.JLabel();
        jlabel2 = new javax.swing.JLabel();
        jlabel3 = new javax.swing.JLabel();
        tfempid = new javax.swing.JTextField();
        btnok = new javax.swing.JButton();
        btnlogin = new javax.swing.JButton();
        jlabel4 = new javax.swing.JLabel();
        pfpwd = new javax.swing.JPasswordField();
        pfotp = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlabel1.setText("Employee ID");

        jlabel2.setText("Password");

        jlabel3.setText("OTP");

        btnok.setText("OK");
        btnok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnokActionPerformed(evt);
            }
        });

        btnlogin.setText("Login");
        btnlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloginActionPerformed(evt);
            }
        });

        jlabel4.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jlabel4.setText("PARABIT CCAS - BHARAT");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(139, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jlabel4)
                        .addGap(125, 125, 125))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(pfotp, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnlogin)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnok)
                                        .addGap(11, 11, 11))))
                            .addComponent(jlabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlabel1)
                                    .addComponent(jlabel2))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfempid, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                    .addComponent(pfpwd))))
                        .addGap(122, 122, 122))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jlabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlabel1)
                    .addComponent(tfempid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jlabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(pfpwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addComponent(btnok)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabel3)
                    .addComponent(pfotp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(btnlogin)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnokActionPerformed

        empid = tfempid.getText();
        String pwd = new String(pfpwd.getPassword());
       
        String s = "Select * from  pbtemployeetable2 where CEID= '"+empid+"'and Pass = '"+pwd+"'";
        try
        {
            db1.rs1 = db1.stm.executeQuery(s);
            if(db1.rs1.next())
            {
                grade = db1.rs1.getInt("Grade");
                status = db1.rs1.getString("Status");
                state = db1.rs1.getString("AreaState");
                dist = db1.rs1.getString("AreaDist");
                city = db1.rs1.getString("AreaCity");
                
                if (status.equals("-1"))
                {
                    JOptionPane.showMessageDialog(null, "You are no longer authorized!!!", "ERROR", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    
                    jlabel3.setVisible(true);
                    pfotp.setVisible(true);
                    btnlogin.setVisible(true); 
                    int otp = new Random().nextInt(100000);
                    sotp = "" + otp;
                    JOptionPane.showMessageDialog(null, "Your OTP is " + sotp, "", JOptionPane.WARNING_MESSAGE);
                    pfotp.setText(sotp);
                }
                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Wrong Employee ID or Password", "", JOptionPane.ERROR_MESSAGE);
                tfempid.setText("");
                pfpwd.setText("");  
            }
            
        
        }catch(Exception e)
        {
            System.out.println("SQL ERROR: "+ e);
        }

        
    }//GEN-LAST:event_btnokActionPerformed

    private void btnloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloginActionPerformed
                sotp2 = new String(pfotp.getPassword());
                if (sotp.equals(sotp2))
                {
                    transfer();
                    oblogin = new PbtLoginData(empid, grade, state, dist, city);
                    PbtEmp obemp = new PbtEmp(this, oblogin);
                    this.dispose();
                    obemp.setVisible(true);
                    
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid OTP", "", JOptionPane.ERROR_MESSAGE);
                }
                
    }//GEN-LAST:event_btnloginActionPerformed

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
            java.util.logging.Logger.getLogger(PbtLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PbtLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PbtLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PbtLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PbtLogin obj = new PbtLogin();
                obj.setVisible(true);
                obj.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlogin;
    private javax.swing.JButton btnok;
    private javax.swing.JLabel jlabel1;
    private javax.swing.JLabel jlabel2;
    private javax.swing.JLabel jlabel3;
    private javax.swing.JLabel jlabel4;
    private javax.swing.JPasswordField pfotp;
    private javax.swing.JPasswordField pfpwd;
    private javax.swing.JTextField tfempid;
    // End of variables declaration//GEN-END:variables
}
