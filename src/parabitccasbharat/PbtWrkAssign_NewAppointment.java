/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parabitccasbharat;

import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
/**
 *
 * @author rishu
 */
public class PbtWrkAssign_NewAppointment extends javax.swing.JDialog implements MouseListener {

    PbtLoginData oblogin;
    ParabitDBC db, db1;
    int[] rows;
    int area;
    public PbtWrkAssign_NewAppointment(PbtWorkAssignment nob, PbtLoginData ob) {
        super(nob, true);
        setTitle("New Appointment");
        initComponents();
        this.setLocationRelativeTo(nob);
        oblogin = ob;
        db = new ParabitDBC();
        db1 = new ParabitDBC();
        areatable.addMouseListener(this);
        jcbarea.addItem("Town");
        jcbarea.addItem("Village");
        jcbtown.setVisible(false);
        ltown.setVisible(false);
        String qry = "SELECT Name FROM pbtenum WHERE SubDistCode = (SELECT SubDistCode FROM pbtstates5 WHERE "
            + "SubDist = (SELECT AreaCity FROM pbtemployeetable2 WHERE CEID = '"+oblogin.ceid+"')) AND Level = 'TOWN'";
            System.out.println(qry);
            try
            {
                db.rs1 = db.stm.executeQuery(qry);
                while(db.rs1.next())
                {
                    jcbtown.addItem(db.rs1.getString(1));
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
    }
private void loadEmp(int ar)
{
    DefaultTableModel dtm2 = (DefaultTableModel)emptable.getModel();
    dtm2.setRowCount(0);
    String qry2 = "SELECT * FROM pbtemployeetable2 WHERE CRepEmpID = '"+oblogin.ceid+"' ORDER BY PreferenceArea LIKE '%"+ar+"%' DESC";
    System.out.println(qry2);
    try
    {
        int sno = 1;
        db.rs2 = db.stm.executeQuery(qry2);
        while(db.rs2.next())
        {
            int pop = 0;
            String ceid = db.rs2.getString("CEID");
            String qry = "SELECT SUM(PopulationAlloted) FROM pbtempschedule WHERE CEID = '"+ceid+"'";
            db1.rs1 = db1.stm.executeQuery(qry);
            if (db1.rs1.next())
            {
                pop = db1.rs1.getInt(1);
            }
            if (pop < 1100)
            {
                Object o[] = {0, ceid, db.rs2.getString("EmpName"), db.rs2.getString("AreaCity"), db.rs2.getString("AreaDist"), db.rs2.getString("AreaState"), db.rs2.getString("EmpDesig"), pop};
                o[0] = sno;
                dtm2.addRow(o);
                sno++;
            }
            
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
}

private void loadVill()
{
    DefaultTableModel dtm = (DefaultTableModel)areatable.getModel();
            dtm.setRowCount(0);
            String qry = "SELECT * FROM pbtenum WHERE SubDistCode = (SELECT SubDistCode FROM pbtstates5 WHERE "
            + "SubDist = (SELECT AreaCity FROM pbtemployeetable2 WHERE CEID = '"+oblogin.ceid+"')) AND Level = 'VILLAGE'";
            try
            {
                db.rs1 = db.stm.executeQuery(qry);
                while(db.rs1.next())
                {
                    Object o[] = {db.rs1.getString("Town_Vill"), db.rs1.getString("Ward"), db.rs1.getString("Name"), db.rs1.getString("Level"), db.rs1.getString("TotPopulation"), db.rs1.getString("RemainingPopulation")};
                    dtm.addRow(o);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            } 
}

private void loadTown()
{
    DefaultTableModel dtm = (DefaultTableModel)areatable.getModel();
        dtm.setRowCount(0);
        String qry = "SELECT * FROM pbtenum WHERE Town_Vill = (SELECT Town_Vill FROM pbtenum WHERE Name = '"+jcbtown.getSelectedItem()+"') AND Level = 'WARD'";
        try
        {
            db.rs2 = db.stm.executeQuery(qry);
            while(db.rs2.next())
            {
                Object o[] = {db.rs2.getString("Town_Vill"), db.rs2.getString("Ward"), db.rs2.getString("Name"), db.rs2.getString("Level"), db.rs2.getString("TotPopulation"), db.rs2.getString("RemainingPopulation")};
                dtm.addRow(o);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        areatable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        emptable = new javax.swing.JTable();
        lenum = new javax.swing.JLabel();
        startdate = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jcbarea = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfpop = new javax.swing.JTextField();
        jcbtown = new javax.swing.JComboBox<>();
        ltown = new javax.swing.JLabel();
        btnassign = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        areatable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Town_Vill", "Ward", "Name", "Level", "Total Population", "Population Remaining"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(areatable);

        emptable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SNo", "CEID", "Name", "City", "District", "State", "Designation", "Population Alloted"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(emptable);

        lenum.setText("Employees Required:");

        jLabel3.setText("Select Work Start Date:");

        jcbarea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {""}));
        jcbarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbareaActionPerformed(evt);
            }
        });

        jLabel4.setText("Select Village/Town:");

        jLabel5.setText("Population Alloting:");

        jcbtown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {""}));
        jcbtown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbtownActionPerformed(evt);
            }
        });

        ltown.setText("Select Town:");

        btnassign.setText("Assign");
        btnassign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnassignActionPerformed(evt);
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
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1139, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lenum)
                        .addGap(285, 285, 285)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfpop, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(startdate, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbarea, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131)
                .addComponent(ltown)
                .addGap(18, 18, 18)
                .addComponent(jcbtown, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnassign, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jcbtown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ltown))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lenum, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(tfpop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(startdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnassign, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbareaActionPerformed

        if (jcbarea.getSelectedItem().equals("Town"))
        {
            loadTown();
            ltown.setVisible(true);
            jcbtown.setVisible(true);
        }
        else if (jcbarea.getSelectedItem().equals("Village"))
        { 
            loadVill();
        }
          
    }//GEN-LAST:event_jcbareaActionPerformed

    private void jcbtownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbtownActionPerformed
        loadTown();
    }//GEN-LAST:event_jcbtownActionPerformed

    private void btnassignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnassignActionPerformed
        int pop = Integer.parseInt(tfpop.getText());
        if (pop > 1200)
        {
            javax.swing.JOptionPane.showMessageDialog(null, "Population Per Employee Cannot Exceed 1250!!!", "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            int r = emptable.getSelectedRow();
            String city = (String)emptable.getValueAt(r, 3);
            String dist = (String)emptable.getValueAt(r, 4);
            String state = (String)emptable.getValueAt(r, 5);
            String ceid = (String)emptable.getValueAt(r, 1);
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            String startdt = dt.format(startdate.getDate());
            for(int k = 0; k < rows.length; k++)
            {
                String villtown = (String)areatable.getValueAt(rows[k], 2);
                String ward = (String)areatable.getValueAt(rows[k], 1);
                int rempop = Integer.parseInt((String)areatable.getValueAt(rows[k], 5));
                int totres = 0;
                if (rempop > 1250 || rempop > pop)
                {
                    totres = pop;
                }
                else
                {
                    totres = rempop;
                }
                String q = "SELECT SUM(PopulationAlloted) FROM pbtempschedule WHERE CEID = '"+ceid+"'";
                String qry = "INSERT INTO pbtempschedule VALUES(null,'"+ceid+"','"+oblogin.ceid+"','"+ward+"',"
                    + "null,'"+villtown+"','"+city+"','"+dist+"','"+state+"','"+LocalDate.now()+"','"+startdt+"',50,0,"+totres+",null,null,null,'1')";
                String qry2 = "UPDATE pbtenum SET RemainingPopulation = "+(rempop - totres)+" WHERE Ward = '"+ward+"' AND Town_Vill = '"+areatable.getValueAt(rows[k], 0)+"'";
                System.out.println(qry+"\n"+qry2); //check if its inserted properly
                try
                {
                    int res;
                    db.rs4 = db.stm.executeQuery(q);
                    if (db.rs4.next())
                    {
                        res = db.rs4.getInt(1);
                        System.out.println(res);
                        if (res <= 1250)
                        {
                            db.stm.executeUpdate(qry);
                            db.stm.executeUpdate(qry2);
                            javax.swing.JOptionPane.showMessageDialog(null, "AREA ASSIGNED SUCCESSFULLY!!");
                            loadEmp(area);  
                            loadTown();
                            loadVill();
                        }
                        else
                        {
                            javax.swing.JOptionPane.showMessageDialog(null, "Population Per Employee Cannot Exceed 1250!!!", "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else
                    {
                        db.stm.executeUpdate(qry);
                        db.stm.executeUpdate(qry2);
                        javax.swing.JOptionPane.showMessageDialog(null, "AREA ASSIGNED SUCCESSFULLY!!");
                        loadEmp(area);
                        loadTown();
                        loadVill(); 
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }  
        }
    }//GEN-LAST:event_btnassignActionPerformed

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
            java.util.logging.Logger.getLogger(PbtWrkAssign_NewAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PbtWrkAssign_NewAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PbtWrkAssign_NewAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PbtWrkAssign_NewAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PbtLoginData ob = new PbtLoginData();
                PbtWrkAssign_NewAppointment dialog = new PbtWrkAssign_NewAppointment(new PbtWorkAssignment(new PbtEmpDashboard(ob), ob), ob);
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
    private javax.swing.JTable areatable;
    private javax.swing.JButton btnassign;
    private javax.swing.JTable emptable;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jcbarea;
    private javax.swing.JComboBox<String> jcbtown;
    private javax.swing.JLabel lenum;
    private javax.swing.JLabel ltown;
    private com.toedter.calendar.JDateChooser startdate;
    private javax.swing.JTextField tfpop;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        try
        {
            int r = areatable.getSelectedRow();
            area = Integer.parseInt(areatable.getValueAt(r, 0).toString());
            loadEmp(area);
        }
        catch(Exception ex)
        {
            javax.swing.JOptionPane.showMessageDialog(null, "SELECT AREA FIRST!!!", "DANGER DANGER", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        
        
        // new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
try
        {
            rows = areatable.getSelectedRows();
            int pop = 0;
            for(int k = 0; k < rows.length; k++)
            {
                pop = pop + Integer.parseInt(areatable.getValueAt(rows[k], 5).toString()); 
            }
            tfpop.setText(""+pop);
            int reqenum = pop/1200;
            if (pop <= 1200)
            {
                lenum.setText("Enumerators Required: 1");
            }
            else
            {
                if (pop%1200 == 0)
                {
                    lenum.setText("Enumerators Required: "+reqenum);
                }
                lenum.setText("Enumerators Required: "+(reqenum+1));
            }
        }
        catch(Exception ex)
        {
            System.out.println("select vill/town");
        }        
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