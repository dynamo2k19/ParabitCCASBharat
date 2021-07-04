/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parabitccasbharat;

import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class PbtAppointedEmp extends javax.swing.JDialog implements MouseListener{

    ParabitDBC db;
    PbtLoginData oblogin;
    PbtEmpManage obemp;
    int f;
    String recid, recstate, recdist, reccity;
    
    public PbtAppointedEmp(PbtEmpManage nob, boolean modal, PbtLoginData ob) {
        super(nob, modal);
        this.setTitle("Appointed Employees");
        initComponents();
        this.setLocationRelativeTo(nob);
        db = new ParabitDBC();
        oblogin = ob;
        f = 0;
        obemp = nob;
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
                qry = "SELECT * FROM pbtemployeetable2 WHERE Grade = " + sgrade + " AND Status = '1'";
            }
            else
            {
                qry = "SELECT * FROM pbtemployeetable2 WHERE Grade = " + sgrade + " AND Status = '1' AND " + area + " = '" + reparea + "'";
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

        jScrollPane1 = new javax.swing.JScrollPane();
        apptemptable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        apptemptable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SNo", "CEID", "CRepEmpID", "Emp Name", "State", "District", "City", "Emp Designation", "Emp AadharNo "
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel1.setText("APPOINTED EMPLOYEE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(367, 367, 367))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
            java.util.logging.Logger.getLogger(PbtAppointedEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PbtAppointedEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PbtAppointedEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PbtAppointedEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PbtLoginData obj = new PbtLoginData();
                PbtAppointedEmp dialog = new PbtAppointedEmp(new PbtEmpManage(new PbtEmpDashboard(obj), true, obj), true, obj);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    void sendMsg()
    {
        apptemptable.addMouseListener(this);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int r = apptemptable.getSelectedRow();
        String ceid = (String)apptemptable.getValueAt(r, 1);
        String name = (String)apptemptable.getValueAt(r, 3);
        String st = (String)apptemptable.getValueAt(r, 4);
        String dist = (String)apptemptable.getValueAt(r, 5);
        String city = (String)apptemptable.getValueAt(r, 6);
        if (e.getClickCount() == 2)
        {
           if (javax.swing.JOptionPane.showConfirmDialog(null, "You Are About To Send A Chain Message Under "+name+" .Do You Want To Proceed?") == 0)
           {
               recstate = st;
               recdist = dist;
               reccity = city;
               f = 1;
               this.dispose();
           }
           else
           {
               f = 0;
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