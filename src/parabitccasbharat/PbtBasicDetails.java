/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parabitccasbharat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
/**
 *
 * @author rishu
 */
public class PbtBasicDetails extends javax.swing.JDialog {

    PbtAadharDetails aadharob;
    int hhsno;
    String gen, nri, marstatus, categ, releg;
    ParabitDBC db, db1;
    SimpleDateFormat dob;
    ButtonGroup bg1, bg2, bg3;
    PbtPersonDashboard obperson;
    public PbtBasicDetails(PbtPersonDashboard parent, PbtAadharDetails ob, int hhno, boolean in){
        super(parent, true);
        setTitle("Basic Details");
        initComponents();
        db = new ParabitDBC();
        db1 = new ParabitDBC(1);
        aadharob = ob;
        obperson = parent;
        hhsno = hhno;
        bg1 = new ButtonGroup();
        bg2 = new ButtonGroup();
        bg3 = new ButtonGroup();
        bg1.add(rbyes);
        bg1.add(rbno);
        bg2.add(rbmale);
        bg2.add(rbfemale);
        bg2.add(rbother);
        
        maritalstatus.addItem("");
        maritalstatus.addItem("Never Married");
        maritalstatus.addItem("Currently Married");
        maritalstatus.addItem("Widowed");
        maritalstatus.addItem("Separated");
        maritalstatus.addItem("Divorced");
        category.addItem("");
        religion.addItem("");
        try
        {
            String qry = "SELECT Category FROM categories";
            String qry2 = "SELECT TypeOfReligion FROM typeofreligion";
            db.rs1 = db.stm.executeQuery(qry);
            while (db.rs1.next())
            {
                category.addItem(db.rs1.getString(1));
            }
            
            db.rs2 = db.stm.executeQuery(qry2);
            while (db.rs2.next())
            {
                religion.addItem(db.rs2.getString(1));
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        dob = new SimpleDateFormat();
        if (ob.gender.startsWith("M") || ob.gender.startsWith("m"))
        {
            rbmale.setSelected(true);
            gen = "M";
        }
        else if (ob.gender.startsWith("F") || ob.gender.startsWith("f"))
        {
            rbfemale.setSelected(true);
            gen = "F";
        }
        else
        {
            rbother.setSelected(true);
            gen = "O";
        }
        
        
        Date dob;
         try {
             dob = new SimpleDateFormat("yyyy-mm-dd").parse(ob.dateOfBirth);
             tfdob.setDate(dob);
         } catch (ParseException ex) {
             Logger.getLogger(PbtBasicDetails.class.getName()).log(Level.SEVERE, null, ex);
         }

         tfage.setText(""+calculateAge(LocalDate.parse(ob.dateOfBirth), LocalDate.now()));
         tfpmthno.setText(ob.building);
         tfpmtvtc.setText(ob.vtcName);
         tfpmttehsil.setText(ob.subDistrictName);
         tfpmtdist.setText(ob.districtName);
         tfpmtstate.setText(ob.stateName);
         tfpmtpin.setText(ob.pincode);
         String qry = "SELECT * FROM pbtaadhar WHERE ANo = '" + aadharob.uid + "'";
         try
         {
             db1.rs1 = db1.stm.executeQuery(qry);
             if (db1.rs1.next())
             {
                 tfmob.setText(db1.rs1.getString("MobNo"));
                 tfemail.setText(db1.rs1.getString("Email"));
             }
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
         lforeign.setVisible(false);
         tfforeignadd.setVisible(false);
        if (in == true)
        {
            incomp();
        }
    }
    
    public PbtBasicDetails(PbtPersonDashboard parent, int hhno, boolean in)
    {
        super(parent, true);
        setTitle("Basic Details");
        initComponents();
        hhsno = hhno;
        obperson = parent;
        db = new ParabitDBC();
        bg1 = new ButtonGroup();
        bg2 = new ButtonGroup();
        bg3 = new ButtonGroup();
        bg1.add(rbyes);
        bg1.add(rbno);
        bg2.add(rbmale);
        bg2.add(rbfemale);
        bg2.add(rbother);
        category.addItem("");
        religion.addItem("");
        try
        {
            String qry = "SELECT Category FROM categories";
            String qry2 = "SELECT TypeOfReligion FROM typeofreligion";
            db.rs1 = db.stm.executeQuery(qry);
            while (db.rs1.next())
            {
                category.addItem(db.rs1.getString(1));
            }
            
            db.rs2 = db.stm.executeQuery(qry2);
            while (db.rs2.next())
            {
                religion.addItem(db.rs2.getString(1));
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        maritalstatus.addItem("");
        maritalstatus.addItem("Never Married");
        maritalstatus.addItem("Currently Married");
        maritalstatus.addItem("Widowed");
        maritalstatus.addItem("Separated");
        maritalstatus.addItem("Divorced");
        
        lforeign.setVisible(false);
        tfforeignadd.setVisible(false);
        if (in == true)
        {
            incomp();
        }
    }
    public void incomp()
    {
        try
        {  
            tfreltohead.setText(obperson.db.rs2.getString("RelToHead"));
            tfheadmob.setText(obperson.db.rs2.getString("HeadRegMobNo"));
            tfmob.setText(obperson.db.rs2.getString("MobNo"));
            tfaltphone.setText(obperson.db.rs2.getString("AltPhoneNo"));
            tfheadadhar.setText(obperson.db.rs2.getString("HeadUID"));
            tfemail.setText(obperson.db.rs2.getString("Email"));
            if (obperson.db.rs2.getString("Gender").equals("M"))
            {
                rbmale.setSelected(true);
            }
            else if (obperson.db.rs2.getString("Gender").equals("F"))
            {
                rbfemale.setSelected(true);
            }
            else
            {
                rbother.setSelected(true);
            }
            tfdob.setDate(obperson.db.rs2.getDate("DOB"));
            tfage.setText(obperson.db.rs2.getInt("Age")+"");
            tfapproxagemarriage.setText(obperson.db.rs2.getString("AproMarriageAge"));
            tfcommunity.setText(obperson.db.rs2.getString("Community"));
            tfcast.setText(obperson.db.rs2.getString("Cast"));
            if (obperson.db.rs2.getString("NRI").equals("Y"))
            {
                rbyes.setSelected(true);
            }
            else if (obperson.db.rs2.getString("NRI").equals("N"))
            {
                rbno.setSelected(true);
            }
            tfbirthplace.setText(obperson.db.rs2.getString("BirthPlaceWithStateCountry"));
            tfpmthno.setText(obperson.db.rs2.getString("PmtHNoAdd"));
            tfpmtwardno.setText(obperson.db.rs2.getString("PmtWardNo"));
            tfpmtvtc.setText(obperson.db.rs2.getString("PmtTownVillage"));
            tfpmtdist.setText(obperson.db.rs2.getString("PmtDist"));
            tfpmttehsil.setText(obperson.db.rs2.getString("PmtTehsil"));
            tfpmtstate.setText(obperson.db.rs2.getString("PmtStUt"));
            tfpmtpin.setText(obperson.db.rs2.getString("PmtPinCode"));
            maritalstatus.setSelectedIndex(Integer.parseInt(obperson.db.rs2.getString("MarStatus")));
            religion.setSelectedIndex(Integer.parseInt(obperson.db.rs2.getString("Religion")));
            category.setSelectedIndex(Integer.parseInt(obperson.db.rs2.getString("Category")));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
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

        jLabel2 = new javax.swing.JLabel();
        tfforeignadd = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfaltphone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfreltohead = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfmob = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfemail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnsave = new javax.swing.JButton();
        tfpmtpin = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tfpmtstate = new javax.swing.JTextField();
        tfpmtwardno = new javax.swing.JTextField();
        tfpmtdist = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tfpmtvtc = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tfpmttehsil = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        tfpmthno = new javax.swing.JTextField();
        tfbirthplace = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tfcast = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        tfapproxagemarriage = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        tfcommunity = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        tfage = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfdob = new com.toedter.calendar.JDateChooser();
        rbfemale = new javax.swing.JRadioButton();
        rbmale = new javax.swing.JRadioButton();
        rbother = new javax.swing.JRadioButton();
        jLabel26 = new javax.swing.JLabel();
        rbyes = new javax.swing.JRadioButton();
        rbno = new javax.swing.JRadioButton();
        tfheadmob = new javax.swing.JTextField();
        lforeign = new javax.swing.JLabel();
        maritalstatus = new javax.swing.JComboBox();
        category = new javax.swing.JComboBox();
        religion = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        tfheadadhar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("HeadRegistered Mobile Number:");

        jLabel3.setText("Alternative Phone No.:");

        jLabel4.setText("Gender:");

        jLabel5.setText("Relation to Head:");

        tfreltohead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfreltoheadActionPerformed(evt);
            }
        });

        jLabel6.setText("Mobile Number:");

        jLabel7.setText("Email:");

        jLabel8.setText("Date Of Birth:");

        jLabel9.setText("BASIC DETAILS");

        btnsave.setText("Save");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        jLabel16.setText("Permanent Pin Code:");

        jLabel10.setText("Permanent State/UT:");

        jLabel11.setText("Permanent Town/Village:");

        jLabel12.setText("Permanent Ward Number:");

        jLabel13.setText("Permanent Tehsil:");

        jLabel14.setText("Permanent District:");

        jLabel15.setText("Permanent House Number:");

        jLabel20.setText("Birth Place With State Country:");

        jLabel17.setText("Category:");

        jLabel18.setText("Approx Marriage Age:");

        jLabel19.setText("Marital Status:");

        jLabel21.setText("Religion:");

        jLabel22.setText("Community:");

        jLabel23.setText("Cast:");

        jLabel1.setText("Age:");

        rbfemale.setText("Female");

        rbmale.setText("Male");

        rbother.setText("Other");
        rbother.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbotherActionPerformed(evt);
            }
        });

        jLabel26.setText("NRI:");

        rbyes.setText("Yes");
        rbyes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbyesActionPerformed(evt);
            }
        });

        rbno.setText("No");
        rbno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnoActionPerformed(evt);
            }
        });

        lforeign.setText("Foreign Address:");

        jLabel24.setText("Head Aadhar:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(26, 26, 26)
                                .addComponent(tfreltohead, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(7, 7, 7)
                                        .addComponent(tfmob, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addGap(18, 18, 18)
                                        .addComponent(religion, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(35, 35, 35)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfaltphone, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel24)
                                    .addGap(7, 7, 7)
                                    .addComponent(tfheadadhar, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel7)
                                    .addGap(18, 18, 18)
                                    .addComponent(tfemail, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(67, 67, 67)
                                            .addComponent(rbmale)
                                            .addGap(29, 29, 29)
                                            .addComponent(rbfemale)
                                            .addGap(18, 18, 18)
                                            .addComponent(rbother))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(18, 18, 18)
                                    .addComponent(tfdob, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(29, 29, 29)
                                    .addComponent(jLabel1)
                                    .addGap(31, 31, 31)
                                    .addComponent(tfage, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(39, 39, 39)
                                    .addComponent(jLabel19)
                                    .addGap(16, 16, 16)
                                    .addComponent(maritalstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel18)
                                    .addGap(19, 19, 19)
                                    .addComponent(tfapproxagemarriage, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 24, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 463, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfheadmob, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(18, 18, 18)
                                        .addComponent(tfpmtwardno, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel11)
                                        .addGap(26, 26, 26)
                                        .addComponent(tfpmtvtc, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(tfpmtdist, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(tfpmttehsil, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(tfpmtstate, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(61, 61, 61)
                                        .addComponent(jLabel16)
                                        .addGap(18, 18, 18)
                                        .addComponent(tfpmtpin, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addGap(18, 18, 18)
                                        .addComponent(tfbirthplace, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(tfpmthno, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(174, 174, 174)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(388, 388, 388)
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(tfcommunity, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel23)
                        .addGap(51, 51, 51)
                        .addComponent(tfcast, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbyes)
                .addGap(18, 18, 18)
                .addComponent(rbno)
                .addGap(126, 126, 126)
                .addComponent(lforeign)
                .addGap(31, 31, 31)
                .addComponent(tfforeignadd, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfreltohead, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tfmob, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfaltphone, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfheadmob, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rbmale)
                                .addComponent(rbfemale)
                                .addComponent(rbother))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfheadadhar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfage, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(tfdob, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(tfapproxagemarriage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(maritalstatus, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(religion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfcommunity, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfcast, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbyes)
                            .addComponent(rbno)
                            .addComponent(tfforeignadd, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lforeign, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(tfbirthplace, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(tfpmthno, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfpmtwardno, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfpmtvtc, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(tfpmtdist, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(tfpmttehsil, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfpmtstate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfpmtpin, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfemail, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        if (rbmale.isSelected())
        {
            gen = "M";
        }
        else if (rbfemale.isSelected())
        {
            gen = "F";
        }
        else
        {
            gen = "O";
        }
        
        if (rbyes.isSelected())
        {
            nri = "Y";
            
        }
        if (rbno.isSelected())
        {
            nri = "N";
        }
        marstatus = maritalstatus.getSelectedIndex() + "";
        categ = category.getSelectedIndex() + "";
        releg = religion.getSelectedIndex() + "";
        
        obperson.mob = tfmob.getText();
        
        if (Integer.parseInt(tfage.getText()) < 15)
        {
            obperson.btnhappiness.setVisible(false);
        }
        System.out.println(tfdob.getDate().toInstant().toString().substring(0, 10));
        String qry = "UPDATE pbtcensus_household SET RelToHead = '"+tfreltohead.getText()+"',HeadRegMobNo = '"+tfheadmob.getText()+"',"
                + "MobNo = '"+tfmob.getText()+"',AltPhoneNo = '"+tfaltphone.getText()+"',Email = '"+tfemail.getText()+"',Gender = '"+gen+"',DOB = '"+tfdob.getDate().toInstant().toString().substring(0, 10)+"',"
                + "BirthPlaceWithStateCountry = '"+tfbirthplace.getText()+"',PmtHNoAdd = '"+tfpmthno.getText()+"',PmtWardNo = '"+tfpmtwardno.getText()+"',PmtTownVillage = '"+tfpmtvtc.getText()+"',"
                + "PmtTehsil = '"+tfpmttehsil.getText()+"',PmtDist = '"+tfpmtdist.getText()+"',PmtStUt = '"+tfpmtstate.getText()+"',PmtPinCode = '"+tfpmtpin.getText()+"',Age = "+tfage.getText()+", Religion = '"+releg+"'"
                + ",Community = '"+tfcommunity.getText()+"',Cast = '"+tfcast.getText()+"',Category = '"+categ+"',MarStatus = '"+marstatus+"',AproMarriageAge = '"+tfapproxagemarriage.getText()+"'"
                + ", NRI = '"+nri+"', ForeignAdd = '"+tfforeignadd.getText()+"',HeadUID = '"+tfheadadhar.getText()+"' WHERE HH_SNo = "+hhsno;
        System.out.println(qry);
        try
        {
            db.stm.executeUpdate(qry);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        javax.swing.JOptionPane.showMessageDialog(null, "Data Saved Successfully!!!");
        this.dispose();
    }//GEN-LAST:event_btnsaveActionPerformed

    private void rbotherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbotherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbotherActionPerformed

    private void rbnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnoActionPerformed
        lforeign.setVisible(false);
        tfforeignadd.setVisible(false);
    }//GEN-LAST:event_rbnoActionPerformed

    private void rbyesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbyesActionPerformed
        lforeign.setVisible(true);
        tfforeignadd.setVisible(true);
    }//GEN-LAST:event_rbyesActionPerformed

    private void tfreltoheadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfreltoheadActionPerformed
        if (tfreltohead.getText().equals("Head"))
        {
            tfheadadhar.setText(aadharob.uid);
        }
    }//GEN-LAST:event_tfreltoheadActionPerformed

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
            java.util.logging.Logger.getLogger(PbtBasicDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PbtBasicDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PbtBasicDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PbtBasicDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                //String ar[] = new String[14];
                //PbtHomedashboard obj = new PbtHomedashboard(0, new PbtLoginData());
                //PbtBasicDetails dialog = new PbtBasicDetails(new PbtPersonDashboard(obj, 0), 0/*, new PbtAadharDetails(ar)*/, true);
                /*dialog.addWindowListener(new java.awt.event.WindowAdapter() {
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
    private javax.swing.JComboBox<String> category;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lforeign;
    private javax.swing.JComboBox<String> maritalstatus;
    private javax.swing.JRadioButton rbfemale;
    private javax.swing.JRadioButton rbmale;
    private javax.swing.JRadioButton rbno;
    private javax.swing.JRadioButton rbother;
    private javax.swing.JRadioButton rbyes;
    private javax.swing.JComboBox<String> religion;
    private javax.swing.JTextField tfage;
    private javax.swing.JTextField tfaltphone;
    private javax.swing.JTextField tfapproxagemarriage;
    private javax.swing.JTextField tfbirthplace;
    private javax.swing.JTextField tfcast;
    private javax.swing.JTextField tfcommunity;
    private com.toedter.calendar.JDateChooser tfdob;
    private javax.swing.JTextField tfemail;
    private javax.swing.JTextField tfforeignadd;
    private javax.swing.JTextField tfheadadhar;
    private javax.swing.JTextField tfheadmob;
    private javax.swing.JTextField tfmob;
    private javax.swing.JTextField tfpmtdist;
    private javax.swing.JTextField tfpmthno;
    private javax.swing.JTextField tfpmtpin;
    private javax.swing.JTextField tfpmtstate;
    private javax.swing.JTextField tfpmttehsil;
    private javax.swing.JTextField tfpmtvtc;
    private javax.swing.JTextField tfpmtwardno;
    private javax.swing.JTextField tfreltohead;
    // End of variables declaration//GEN-END:variables
}
