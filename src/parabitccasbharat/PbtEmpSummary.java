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
public class PbtEmpSummary extends javax.swing.JDialog implements MouseListener {

    ParabitDBC db, db1;
    PbtLoginData oblogin;
    String empmob, empname, empdesig;
    int f;
    boolean proceed;
    public PbtEmpSummary(PbtEmpDashboard nob, boolean modal, PbtLoginData ob) {
        super(nob, modal);
        setTitle("Employee Summary");
        initComponents();
        this.setLocationRelativeTo(nob);
        db = new ParabitDBC();
        db1 = new ParabitDBC();
        oblogin = ob;
        loadTable();
        emptable1.addMouseListener(this);
        emptable2.addMouseListener(this);
        f = 0;
        proceed = false;
        lsenior.setVisible(false);
        jcbsenior.setVisible(false);
        System.out.println("in constructor");
        if (PbtLoginData.grade == 4)
        {
            emptable2.setVisible(false);
        }
    }
    
    public PbtEmpSummary(PbtEnumDashboard nob, boolean modal, PbtLoginData ob) {
        super(nob, modal);
        initComponents();
        this.setLocationRelativeTo(nob);
        db = new ParabitDBC();
        oblogin = ob;
        //loadTable();
        emptable1.addMouseListener(this);
        emptable2.addMouseListener(this);
        f = 0;
        proceed = false;
        lsenior.setVisible(false);
        jcbsenior.setVisible(false);
        System.out.println("in constructor2");
    }

    private void loadTable()
{{
    int sgrade = oblogin.grade + 1;
    String area = "AreaCity", sarea = "";
    String qry = "";
    switch(sgrade)
    {
        case 2: area = "AreaState"; break;
        case 3: area = "AreaDist"; sarea = "AreaState"; break;
        case 4: area = "AreaCity"; sarea = "AreaDist"; break;
        case 5: sarea = "AreaCity";
    }
    
    if (sgrade == 2)
    {
        qry = "SELECT * FROM pbtemployeetable2 WHERE Grade = " + sgrade + " AND Status = '1'";
    }
    else
    {
        qry = "SELECT * FROM pbtemployeetable2 WHERE Grade = "+sgrade+" AND Status = '1' AND "+sarea+" = (SELECT "+sarea+" FROM pbtemployeetable2 WHERE CEID = '"+oblogin.ceid+"')";
    }
    
        try
        {
            System.out.println(qry);
            DefaultTableModel tm = (DefaultTableModel)emptable1.getModel();
            db.rs1 = db.stm.executeQuery(qry);
            tm.setRowCount(0);
            int sno = 1;
            while(db.rs1.next())
            {
                db1.rs1 = db1.stm.executeQuery("SELECT SUM(PopulationAlloted), SUM(WorkDone) FROM pbtempschedule WHERE CEID = '"+db.rs1.getString("CEID")+"'");
                int tp = 0, wd = 0, pp = 0;
                if (db1.rs1.next())
                {
                    tp = db1.rs1.getInt(1);
                    wd = db1.rs1.getInt(2);
                    pp = tp - wd;
                }
                Object o[] = {sno, db.rs1.getString("EmpName"), db.rs1.getString(area), db.rs1.getString("EmpOffMob"), tp, wd, pp, 0};
                tm.addRow(o);
                sno++;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
    
    private void loadTable2()
{
    int r = emptable1.getSelectedRow();
    String val = (String) emptable1.getValueAt(r, 2);
    String area = "AreaState";
    
    int grade2 = oblogin.grade + 2;
    String area2 = "AreaCity";
    switch(grade2)
    {
        case 3: area2 = "AreaDist"; break;
        case 4: area2 = "AreaCity"; area = "AreaDist"; break;
        case 5: area2 = "AreaCity"; area = "AreaCity";
    }
    String qry = "SELECT * FROM pbtemployeetable2 WHERE Grade = " + grade2 + " AND Status = '1' AND " + area + " = '" + val + "'";
    System.out.println(qry);
    try
    {
        DefaultTableModel tm2 = (DefaultTableModel)emptable2.getModel();
        db.rs2 = db.stm.executeQuery(qry);
        tm2.setRowCount(0);
        int sno = 1;
        while(db.rs2.next())
        {
            db1.rs2 = db1.stm.executeQuery("SELECT SUM(PopulationAlloted), SUM(WorkDone) FROM pbtempschedule WHERE CEID = '"+db.rs2.getString("CEID")+"'");
                int tp = 0, wd = 0, pp = 0;
                if (db1.rs2.next())
                {
                    tp = db1.rs2.getInt(1);
                    wd = db1.rs2.getInt(2);
                    pp = tp - wd;
                }
            Object o[] = {db.rs2.getString("SNo"), db.rs2.getString("EmpName"), db.rs2.getString(area2), db.rs2.getString("EmpOffMob"), tp, wd, pp, 0};
            o[0] = sno;
            tm2.addRow(o);
            sno++;
        }
    }
    catch(Exception e)
    {
        System.out.println(e);
        e.printStackTrace();
    }
        
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        emptable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        emptable2 = new javax.swing.JTable();
        lsenior = new javax.swing.JLabel();
        jcbsenior = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        emptable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SNo", "Employee Name", "Area Alloted", "Mobile No.", "Work Alloted", "Work Completed", "Work Pending", "Not Responding"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(emptable1);

        emptable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SNo", "Employee Name", "Area Alloted", "Mobile No.", "Work Alloted", "Work Completed", "Work Pending", "Not Responding"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(emptable2);

        lsenior.setText("Senior Employee:");

        jcbsenior.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {""}));
        jcbsenior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbseniorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 956, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lsenior, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jcbsenior, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lsenior, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbsenior, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbseniorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbseniorActionPerformed
            String senior = jcbsenior.getSelectedItem().toString();
            int index = senior.indexOf('-');
            empdesig = senior.substring(index+1);
            empname = senior.substring(0, index);
            System.out.println(empname+"*"+empdesig+"/");
            if (javax.swing.JOptionPane.showConfirmDialog(null, "Do You Want To Send Message To "+empname+" ?") == 0)
            {
                proceed = true;
                this.dispose();
            }
    }//GEN-LAST:event_jcbseniorActionPerformed

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
            java.util.logging.Logger.getLogger(PbtEmpSummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PbtEmpSummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PbtEmpSummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PbtEmpSummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PbtEmpSummary dialog = new PbtEmpSummary(new PbtEmpDashboard(new PbtLoginData()), true, new PbtLoginData());
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
void loadJCB()
{
    String name, desig, currceid = oblogin.ceid;
    int grd = oblogin.grade;
    try
    {
        while(grd > 1)
        {
            String qry = "SELECT EmpName, EmpDesig, CEID FROM pbtemployeetable2 WHERE CEID = (SELECT CRepEmpID FROM pbtemployeetable2 WHERE CEID = '"+currceid+"')";
            //System.out.println(qry);
            db.rs3 = db.stm.executeQuery(qry);
            db.rs3.next();
            currceid = db.rs3.getString("CEID");
            name = db.rs3.getString("EmpName");
            desig = db.rs3.getString("EmpDesig");
            jcbsenior.addItem(name+"-"+desig);
            grd--;
        }
        
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JTable emptable1;
    javax.swing.JTable emptable2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jcbsenior;
    private javax.swing.JLabel lsenior;
    // End of variables declaration//GEN-END:variables
void sndMsg()
{
            int r2 = emptable2.getSelectedRow();
            int r = emptable1.getSelectedRow();
            if (emptable2.isRowSelected(r2))
            {
                if (javax.swing.JOptionPane.showConfirmDialog(null, "Do You Want To Send Message To "+emptable2.getValueAt(r2, 1)+" ?") == 0)
                {
                   empmob = (String)emptable2.getValueAt(r2, 3);
                   proceed = true;
                   this.dispose();
                }   
            }
            else if (emptable1.isRowSelected(r))
            {
                if (javax.swing.JOptionPane.showConfirmDialog(null, "Do You Want To Send Message To "+emptable1.getValueAt(r, 1)+" ?") == 0)
                {
                    empmob = (String)emptable1.getValueAt(r, 3);
                    proceed = true;
                    this.dispose();  
                }
            }
            
            
}

void sendMsg()
{
    f = 1;
    if (oblogin.grade > 1)
    {
        loadJCB();
        lsenior.setVisible(true);
        jcbsenior.setVisible(true); 
    } 
}
    @Override
    public void mouseClicked(MouseEvent e) {

        //loadTable2();
      
        if (e.getClickCount() == 1)
        {
            System.out.println("GRADE: "+PbtLoginData.grade);
            if (PbtLoginData.grade < 4)
            {
                loadTable2();
            }
        }
        else if (f == 1)
        {
            sndMsg();
        }
        
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
