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
public class PbtHappinessDetails extends javax.swing.JDialog {

    /**
     * Creates new form PbtHappinessDetails
     */
    ParabitDBC db;
    int hhsno, hlsno;
    PbtPersonDashboard obperson;
    public PbtHappinessDetails(PbtPersonDashboard parent, int hh, boolean in) {
        super(parent, true);
        setTitle("Happiness");
        initComponents();
        obperson = parent;
        db = new ParabitDBC();
        hhsno = hh;
        slider();
        l1.setVisible(false);
        s1.setVisible(false);
        l2.setVisible(false);
        s2.setVisible(false);
        l3.setVisible(false);
        s3.setVisible(false);
        l4.setVisible(false);
        s4.setVisible(false);
        l5.setVisible(false);
        s5.setVisible(false);
        l6.setVisible(false);
        s6.setVisible(false);
        l7.setVisible(false);
        s7.setVisible(false);
        l8.setVisible(false);
        s8.setVisible(false);
        l9.setVisible(false);
        s9.setVisible(false);
        criteria();
        if (in == true)
        {
            //incomp();
        }
    }
    
    public void incomp()
    {
        try
        {
            db.rs2 = db.stm.executeQuery("SELECT QuesNo, Rating FROM happiness_answers WHERE HH_SNo = " + hhsno);
            while (db.rs2.next())
            {
                
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void criteria()
    {
        int income = 520000, age = 45, children = 0;
        String name = "", workstatus = "", occupation = "", marstatus = "";
        String sql = "SELECT Age, Name, MarStatus, CWorkStat, Income, Occupation, ChdnSurv, HL_SNo  FROM pbtcensus_household WHERE HH_SNo = " + hhsno;
        try
        {
            db.rs1 = db.stm.executeQuery(sql);
            if (db.rs1.next())
            {
                marstatus = db.rs1.getString("MarStatus");
                hlsno = db.rs1.getInt("HL_SNo");
                age = db.rs1.getInt("Age");
                name = db.rs1.getString("Name");
                children = Integer.parseInt(db.rs1.getString("ChdnSurv"));
                if (db.rs1.getString("CWorkStat").equals("Y"))
                {
                    workstatus = "Working";
                    occupation = db.rs1.getString("Occupation");
                    income = db.rs1.getInt("Income");
                }
                else
                {
                    income = 0;
                    workstatus = "Not Working";
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        lname.setText("Name: " + name);
        lage.setText("Age: " + age);
        lwrkstatus.setText("Working Status: " + workstatus);
        lincome.setText("Income: " + income);
        loccupation.setText("Occupation: " + occupation);
        lwrkstatus.setVisible(false);
        lincome.setVisible(false);
        loccupation.setVisible(false);
        
        if (age <= 40)
        {
            l1.setVisible(true);
            s1.setVisible(true);
            l2.setVisible(true);
            s2.setVisible(true);
            l4.setVisible(true);
            s4.setVisible(true);
            if (age <= 25)
            {
                l3.setVisible(true);
                s3.setVisible(true);
            }
        }
        if (age >= 25 && age <= 200)
        {
            lwrkstatus.setVisible(true);
            l5.setVisible(true);
            s5.setVisible(true);
            if (age <= 60 && workstatus.equals("Working"))
            {
                lincome.setVisible(true);
                loccupation.setVisible(true);
                l6.setVisible(true);
                s6.setVisible(true);
            }
            if (age >= 60 && (children > 0 || marstatus.equals("2")))
            {
                l9.setVisible(true);
                s9.setVisible(true);
            }
        }
        if (age >= 40 && age <= 60)
        {
            if (children > 0)
            {
                l7.setVisible(true);
                s7.setVisible(true);
            }
            l8.setVisible(true);
            s8.setVisible(true);
        } 
    }
    
    private void slider()
    {
        s1.setMinimum(1);
        s1.setMaximum(5);
        s1.setMajorTickSpacing(1);
        s1.setValue(1);
        s1.setPaintLabels(true);
        
        s2.setMinimum(1);
        s2.setMaximum(5);
        s2.setMajorTickSpacing(1);
        s2.setValue(1);
        s2.setPaintLabels(true);
        
        s3.setMinimum(1);
        s3.setMaximum(5);
        s3.setMajorTickSpacing(1);
        s3.setValue(1);
        s3.setPaintLabels(true);
        
        s4.setMinimum(1);
        s4.setMaximum(5);
        s4.setMajorTickSpacing(1);
        s4.setValue(1);
        s4.setPaintLabels(true);
        
        s5.setMinimum(1);
        s5.setMaximum(5);
        s5.setMajorTickSpacing(1);
        s5.setValue(1);
        s5.setPaintLabels(true);
        
        s6.setMinimum(1);
        s6.setMaximum(5);
        s6.setMajorTickSpacing(1);
        s6.setValue(1);
        s6.setPaintLabels(true);
        
        s7.setMinimum(1);
        s7.setMaximum(5);
        s7.setMajorTickSpacing(1);
        s7.setValue(1);
        s7.setPaintLabels(true);
        
        s8.setMinimum(1);
        s8.setMaximum(5);
        s8.setMajorTickSpacing(1);
        s8.setValue(1);
        s8.setPaintLabels(true);
        
        s9.setMinimum(1);
        s9.setMaximum(5);
        s9.setMajorTickSpacing(1);
        s9.setValue(1);
        s9.setPaintLabels(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        l2 = new javax.swing.JLabel();
        l1 = new javax.swing.JLabel();
        l4 = new javax.swing.JLabel();
        l3 = new javax.swing.JLabel();
        l6 = new javax.swing.JLabel();
        l5 = new javax.swing.JLabel();
        l9 = new javax.swing.JLabel();
        l7 = new javax.swing.JLabel();
        l8 = new javax.swing.JLabel();
        s1 = new javax.swing.JSlider();
        s2 = new javax.swing.JSlider();
        s3 = new javax.swing.JSlider();
        s4 = new javax.swing.JSlider();
        s5 = new javax.swing.JSlider();
        s6 = new javax.swing.JSlider();
        s7 = new javax.swing.JSlider();
        s8 = new javax.swing.JSlider();
        s9 = new javax.swing.JSlider();
        btnsave = new javax.swing.JButton();
        lname = new javax.swing.JLabel();
        lage = new javax.swing.JLabel();
        lwrkstatus = new javax.swing.JLabel();
        lincome = new javax.swing.JLabel();
        loccupation = new javax.swing.JLabel();
        lwrksec = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        l2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        l2.setText("How much trust you have on your family ?");

        l1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        l1.setText("How Much Trust You Have On Your Friends?");

        l4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        l4.setText("How much freedom you have in taking decision ?");

        l3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        l3.setText("Are you happy with your realtions ?");

        l6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        l6.setText("How much you are satisfied with your job environment ?");

        l5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        l5.setText("How much you are satisfied with your financial conditions ?");

        l9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        l9.setText("How much happy with your child / partner ?");

        l7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        l7.setText("Rate your relations with your children ?");

        l8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        l8.setText("Rate your relations with your parents ?");

        btnsave.setText("Save");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        lname.setText("Name: Raj");

        lage.setText("Age: 45");

        lwrkstatus.setText("Working Status: Working");

        lincome.setText("Income: 520000");

        loccupation.setText("Occupation: Engineer");

        lwrksec.setText("Working Sector: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnsave)
                .addGap(359, 359, 359))
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loccupation, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lwrksec, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lage, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(lwrkstatus)
                        .addGap(92, 92, 92)
                        .addComponent(lincome, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(l1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(l2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(l3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(l4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(l5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(l6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(l7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(l8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(l9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(s1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(34, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lname)
                    .addComponent(lage)
                    .addComponent(lwrkstatus)
                    .addComponent(lincome))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loccupation)
                    .addComponent(lwrksec))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        try
        {
            if (s1.isVisible())
            {
                String q = "INSERT INTO happiness_answers(HL_SNo, HH_SNo, QuesNo, Rating) VALUES("+hlsno+","+hhsno+",1,"+s1.getValue()+")";
                db.stm.executeUpdate(q);
            }
            if (s2.isVisible())
            {
                String q = "INSERT INTO happiness_answers(HL_SNo, HH_SNo, QuesNo, Rating) VALUES("+hlsno+","+hhsno+",2,"+s2.getValue()+")";
                db.stm.executeUpdate(q);
            }
            if (s3.isVisible())
            {
                String q = "INSERT INTO happiness_answers(HL_SNo, HH_SNo, QuesNo, Rating) VALUES("+hlsno+","+hhsno+",3,"+s3.getValue()+")";
                db.stm.executeUpdate(q);
            }
            if (s4.isVisible())
            {
                String q = "INSERT INTO happiness_answers(HL_SNo, HH_SNo, QuesNo, Rating) VALUES("+hlsno+","+hhsno+",4,"+s4.getValue()+")";
                db.stm.executeUpdate(q);
            }
            if (s5.isVisible())
            {
                String q = "INSERT INTO happiness_answers(HL_SNo, HH_SNo, QuesNo, Rating) VALUES("+hlsno+","+hhsno+",5,"+s5.getValue()+")";
                db.stm.executeUpdate(q);
            }
            if (s6.isVisible())
            {
                String q = "INSERT INTO happiness_answers(HL_SNo, HH_SNo, QuesNo, Rating) VALUES("+hlsno+","+hhsno+",6,"+s6.getValue()+")";
                db.stm.executeUpdate(q);
            }
            if (s7.isVisible())
            {
                String q = "INSERT INTO happiness_answers(HL_SNo, HH_SNo, QuesNo, Rating) VALUES("+hlsno+","+hhsno+",7,"+s7.getValue()+")";
                db.stm.executeUpdate(q);
            }
            if (s8.isVisible())
            {
                String q = "INSERT INTO happiness_answers(HL_SNo, HH_SNo, QuesNo, Rating) VALUES("+hlsno+","+hhsno+",8,"+s8.getValue()+")";
                db.stm.executeUpdate(q);
            }
            if (s9.isVisible())
            {
                String q = "INSERT INTO happiness_answers(HL_SNo, HH_SNo, QuesNo, Rating) VALUES("+hlsno+","+hhsno+",9,"+s9.getValue()+")";
                db.stm.executeUpdate(q);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        javax.swing.JOptionPane.showMessageDialog(null, "Data Saved Successfully");
        this.dispose();
    }//GEN-LAST:event_btnsaveActionPerformed

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
            java.util.logging.Logger.getLogger(PbtHappinessDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PbtHappinessDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PbtHappinessDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PbtHappinessDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                /*PbtHomedashboard obj = new PbtHomedashboard(0, new PbtLoginData());
                PbtHappinessDetails dialog = new PbtHappinessDetails(new PbtPersonDashboard(obj, 2), 2, false);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);*/
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsave;
    private javax.swing.JLabel l1;
    private javax.swing.JLabel l2;
    private javax.swing.JLabel l3;
    private javax.swing.JLabel l4;
    private javax.swing.JLabel l5;
    private javax.swing.JLabel l6;
    private javax.swing.JLabel l7;
    private javax.swing.JLabel l8;
    private javax.swing.JLabel l9;
    private javax.swing.JLabel lage;
    private javax.swing.JLabel lincome;
    private javax.swing.JLabel lname;
    private javax.swing.JLabel loccupation;
    private javax.swing.JLabel lwrksec;
    private javax.swing.JLabel lwrkstatus;
    private javax.swing.JSlider s1;
    private javax.swing.JSlider s2;
    private javax.swing.JSlider s3;
    private javax.swing.JSlider s4;
    private javax.swing.JSlider s5;
    private javax.swing.JSlider s6;
    private javax.swing.JSlider s7;
    private javax.swing.JSlider s8;
    private javax.swing.JSlider s9;
    // End of variables declaration//GEN-END:variables
}
