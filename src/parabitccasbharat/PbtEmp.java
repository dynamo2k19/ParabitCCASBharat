/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parabitccasbharat;

/**
 *
 * @author rishu
 */
public class PbtEmp extends javax.swing.JDialog {

    PbtLoginData oblogin;
    PbtLogin login;
    
    public PbtEmp(PbtLogin nob, PbtLoginData ob) {
        super(nob, true);
        setTitle("");
        initComponents();
        this.setLocationRelativeTo(nob);
        oblogin = ob;
        login = nob;
        btnpmo.setEnabled(false);
        btnministry.setEnabled(false);
        btncensusdept.setEnabled(false);
        if (oblogin.grade == 100)
        {
            btnpmo.setEnabled(true);
        }
        else if (oblogin.grade == 200)
        {
            btnministry.setEnabled(true);
        }
        else
        {
            btncensusdept.setEnabled(true);
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

        jButton4 = new javax.swing.JButton();
        btnpmo = new javax.swing.JButton();
        btnministry = new javax.swing.JButton();
        btncensusdept = new javax.swing.JButton();

        jButton4.setText("jButton4");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnpmo.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        btnpmo.setText("PMO");
        btnpmo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpmoActionPerformed(evt);
            }
        });

        btnministry.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        btnministry.setText("MINISTRY");
        btnministry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnministryActionPerformed(evt);
            }
        });

        btncensusdept.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        btncensusdept.setText("CENSUS DEPARTMENT");
        btncensusdept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncensusdeptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnpmo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(btnministry, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btncensusdept, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btncensusdept, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(btnpmo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnministry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(143, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnministryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnministryActionPerformed
        
    }//GEN-LAST:event_btnministryActionPerformed

    private void btnpmoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpmoActionPerformed
       
    }//GEN-LAST:event_btnpmoActionPerformed

    private void btncensusdeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncensusdeptActionPerformed
        PbtCensusDept obcensusdept = new PbtCensusDept(login, oblogin);
        this.dispose();
        obcensusdept.setLocationRelativeTo(null);
        obcensusdept.setVisible(true);
    }//GEN-LAST:event_btncensusdeptActionPerformed

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
            java.util.logging.Logger.getLogger(PbtEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PbtEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PbtEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PbtEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new PbtEmp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncensusdept;
    private javax.swing.JButton btnministry;
    private javax.swing.JButton btnpmo;
    private javax.swing.JButton jButton4;
    // End of variables declaration//GEN-END:variables
}
