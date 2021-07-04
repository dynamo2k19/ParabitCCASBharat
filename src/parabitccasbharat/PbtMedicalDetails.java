/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parabitccasbharat;

import javax.swing.ButtonGroup;

/**
 *
 * @author rishu
 */
public class PbtMedicalDetails extends javax.swing.JDialog {

    int hhsno;
    ButtonGroup bg1, bg2, bg3, bg4, bg5, bg6;
    PbtAadharDetails aadharob;
    ParabitDBC db;
    String healthchkup, veg, addict, pwd, bgrp;
    PbtPersonDashboard obperson;
    public PbtMedicalDetails(PbtPersonDashboard parent, PbtAadharDetails ob, int hhno, boolean in) {
        super(parent, true);
        setTitle("Medical Details");
        initComponents();
        aadharob = ob;
        hhsno = hhno;
        obperson = parent;
        db = new ParabitDBC();
        bg1 = new ButtonGroup();
        bg2 = new ButtonGroup();
        bg3 = new ButtonGroup();
        bg4 = new ButtonGroup();
        bg5 = new ButtonGroup();
        bg6 = new ButtonGroup();
        db = new ParabitDBC();
        /*bg1.add(rbhomeyes);
        bg1.add(rbhomeno);
        bg2.add(rboutyes);
        bg2.add(rboutno);*/
        bg3.add(rbpwdyes);
        bg3.add(rbpwdno);
        bg4.add(rbaddicyes);
        bg4.add(rbaddicno);
        bg5.add(rbvegyes);
        bg5.add(rbvegno);
        bg6.add(rbhealthyes);
        bg6.add(rbhealthno);
        bgroup.addItem("O+");
        bgroup.addItem("A+");
        bgroup.addItem("B+");
        bgroup.addItem("AB+");
        bgroup.addItem("O-");
        bgroup.addItem("A-");
        bgroup.addItem("B-");
        bgroup.addItem("AB-");
        slider();
        if (in == true)
        {
            incomp();
        }
    }
    
    public PbtMedicalDetails(PbtPersonDashboard parent, int hhno, boolean in) {
        super(parent, true);
        setTitle("Medical Details");
        initComponents();
        hhsno = hhno;
        obperson = parent;
        db = new ParabitDBC();
        bg1 = new ButtonGroup();
        bg2 = new ButtonGroup();
        bg3 = new ButtonGroup();
        bg4 = new ButtonGroup();
        bg5 = new ButtonGroup();
        bg6 = new ButtonGroup();
        db = new ParabitDBC();
        /*bg1.add(rbhomeyes);
        bg1.add(rbhomeno);
        bg2.add(rboutyes);
        bg2.add(rboutno);*/
        bg3.add(rbpwdyes);
        bg3.add(rbpwdno);
        bg4.add(rbaddicyes);
        bg4.add(rbaddicno);
        bg5.add(rbvegyes);
        bg5.add(rbvegno);
        bg6.add(rbhealthyes);
        bg6.add(rbhealthno);
        bgroup.addItem("O+");
        bgroup.addItem("A+");
        bgroup.addItem("B+");
        bgroup.addItem("AB+");
        bgroup.addItem("O-");
        bgroup.addItem("A-");
        bgroup.addItem("B-");
        bgroup.addItem("AB-");
        slider();
        if (in == true)
        {
            incomp();
        }
    }
    
    public void incomp()
    {
        try
        {
            tfwt.setText(obperson.db.rs2.getInt("Wt")+"");
            tfht.setText(obperson.db.rs2.getFloat("Ht")+"");
            tfbmi.setText(obperson.db.rs2.getFloat("BMI")+"");
            tfstem.setText(obperson.db.rs2.getString("Stem_Cell_Id"));
            bgroup.setSelectedIndex(Integer.parseInt(obperson.db.rs2.getString("BGroup")));
            homefood.setValue(Integer.parseInt(obperson.db.rs2.getString("HomeFood")));
            outfood.setValue(Integer.parseInt(obperson.db.rs2.getString("OutsideFood")));
            tfsport.setText(obperson.db.rs2.getString("Sport"));
            sleep.setValue(Integer.parseInt(obperson.db.rs2.getString("SleepHrs")));
            spiritual.setValue(Integer.parseInt(obperson.db.rs2.getString("Spiritual")));
            yoga.setValue(Integer.parseInt(obperson.db.rs2.getString("Yoga")));
            meditation.setValue(Integer.parseInt(obperson.db.rs2.getString("Meditation")));
            health.setValue(Integer.parseInt(obperson.db.rs2.getString("RatYourHealth")));
            tfchronicdisease.setText(obperson.db.rs2.getString("ChronicDisease"));
            
            if (obperson.db.rs2.getString("Health_Checkup").equals("Y"))
            {
                rbhealthyes.setSelected(true);
            }
            else if (obperson.db.rs2.getString("Health_Checkup").equals("N"))
            {
                rbhealthno.setSelected(true);
            }
            
            if (obperson.db.rs2.getString("Addiction").equals("Y"))
            {
                rbaddicyes.setSelected(true);
            }
            else if (obperson.db.rs2.getString("Addiction").equals("N"))
            {
                rbaddicno.setSelected(true);
            }
            
            if (obperson.db.rs2.getString("Veg").equals("Y"))
            {
                rbvegyes.setSelected(true);
            }
            else if (obperson.db.rs2.getString("Veg").equals("N"))
            {
                rbvegno.setSelected(true);
            }
            
            if (obperson.db.rs2.getString("PWD").equals("Y"))
            {
                rbpwdyes.setSelected(true);
            }
            else if (obperson.db.rs2.getString("PWD").equals("N"))
            {
                rbpwdno.setSelected(true);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void slider()
    {
        yoga.setMinimum(1);
        yoga.setMaximum(5);
        yoga.setMajorTickSpacing(1);
        yoga.setValue(1);
        yoga.setPaintLabels(true);
        meditation.setMinimum(1);
        meditation.setMaximum(5);
        meditation.setMajorTickSpacing(1);
        meditation.setValue(1);
        meditation.setPaintLabels(true);
        health.setMinimum(1);
        health.setMaximum(5);
        health.setMajorTickSpacing(1);
        health.setValue(1);
        health.setPaintLabels(true);
        spiritual.setMinimum(1);
        spiritual.setMaximum(5);
        spiritual.setMajorTickSpacing(1);
        spiritual.setValue(1);
        spiritual.setPaintLabels(true);
        sleep.setMinimum(1);
        sleep.setMaximum(5);
        sleep.setMajorTickSpacing(1);
        sleep.setValue(1);
        sleep.setPaintLabels(true);
        homefood.setMinimum(1);
        homefood.setMaximum(5);
        homefood.setMajorTickSpacing(1);
        homefood.setValue(1);
        homefood.setPaintLabels(true);
        outfood.setMinimum(1);
        outfood.setMaximum(5);
        outfood.setMajorTickSpacing(1);
        outfood.setValue(1);
        outfood.setPaintLabels(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider2 = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        tfstem = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnsave = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        tfwt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfsport = new javax.swing.JTextField();
        tfht = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tfbmi = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        tfchronicdisease = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        rbhealthyes = new javax.swing.JRadioButton();
        rbhealthno = new javax.swing.JRadioButton();
        rbaddicyes = new javax.swing.JRadioButton();
        rbaddicno = new javax.swing.JRadioButton();
        health = new javax.swing.JSlider();
        meditation = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        rbpwdyes = new javax.swing.JRadioButton();
        rbpwdno = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        rbvegyes = new javax.swing.JRadioButton();
        rbvegno = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        yoga = new javax.swing.JSlider();
        spiritual = new javax.swing.JSlider();
        sleep = new javax.swing.JSlider();
        homefood = new javax.swing.JSlider();
        outfood = new javax.swing.JSlider();
        bgroup = new javax.swing.JComboBox<>();
        btncal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Stem Cell ID:");

        jLabel9.setText("MEDICAL DETAILS");

        btnsave.setText("Save");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        jLabel5.setText("Weight:");

        jLabel6.setText("Blood Group:");

        jLabel10.setText("Height:");

        jLabel11.setText("BMI:");

        jLabel12.setText("Home Food:");

        jLabel13.setText("Outside Food:");

        jLabel14.setText("Satisfied With Sleep Hrs:");

        jLabel15.setText("Health Checkup:");

        jLabel16.setText("Addiction:");

        jLabel17.setText("Chronic Disease:");

        jLabel18.setText("Spiritual:");

        jLabel19.setText("Yoga:");

        jLabel20.setText("Meditation:");

        jLabel21.setText("Rate Your Health:");

        rbhealthyes.setText("Yes");

        rbhealthno.setText("No");

        rbaddicyes.setText("Yes");

        rbaddicno.setText("No");

        jLabel3.setText("Person with Disability:");

        rbpwdyes.setText("Yes");

        rbpwdno.setText("No");

        jLabel4.setText("Vegetarian:");

        rbvegyes.setText("Yes");

        rbvegno.setText("No");

        jLabel1.setText("Sport:");

        bgroup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ""}));

        btncal.setText("Calculate");
        btncal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel2)
                        .addGap(35, 35, 35)
                        .addComponent(tfstem, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jLabel6)
                        .addGap(48, 48, 48)
                        .addComponent(bgroup, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(homefood, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(outfood, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel1)
                        .addGap(20, 20, 20)
                        .addComponent(tfsport, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(sleep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel15)
                        .addGap(20, 20, 20)
                        .addComponent(rbhealthyes)
                        .addGap(18, 18, 18)
                        .addComponent(rbhealthno))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(rbpwdyes)
                        .addGap(0, 0, 0)
                        .addComponent(rbpwdno))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(spiritual, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(yoga, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(meditation, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel21)
                        .addGap(27, 27, 27)
                        .addComponent(health, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(778, 778, 778)
                        .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(tfwt, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(tfht, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(tfbmi, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btncal))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(rbaddicyes)
                                .addGap(18, 18, 18)
                                .addComponent(rbaddicno)
                                .addGap(165, 165, 165)
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(tfchronicdisease, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(rbvegyes)))
                        .addGap(18, 18, 18)
                        .addComponent(rbvegno)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfwt, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfht, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btncal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfbmi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel2))
                    .addComponent(tfstem, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bgroup, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(outfood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1))
                    .addComponent(tfsport, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(homefood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel14))
                    .addComponent(sleep, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(rbhealthyes))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(rbhealthno)))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel16))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(rbaddicyes))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(rbaddicno))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfchronicdisease, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(rbvegyes))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(rbvegno)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel3))
                    .addComponent(rbpwdyes)
                    .addComponent(rbpwdno))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spiritual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yoga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(meditation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(health, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        
        if (rbaddicyes.isSelected())
        {
            addict = "Y";
        }
        else
        {
            addict = "N";
        }
        if (rbvegyes.isSelected())
        {
            veg = "Y";
        }
        else
        {
            veg = "N";
        }
        if (rbhealthyes.isSelected())
        {
            healthchkup = "Y";
        }
        else
        {
            healthchkup = "N";
        }
        if (rbpwdyes.isSelected())
        {
            pwd = "Y";
        }
        else
        {
            pwd = "N";
        }
        bgrp = bgroup.getSelectedIndex() + "";
        
        String w = "", h = "", bmi = "";
        if (!tfwt.getText().equals(""))
        {
            w = ",Wt = "+tfwt.getText();
        }
        if (!tfht.getText().equals(""))
        {
            h = ",Ht = "+tfht.getText();
        }
        if (!tfbmi.getText().equals(""))
        {
            bmi = ",BMI = "+tfbmi.getText();
        }
        
        String qry = "UPDATE pbtcensus_household SET Stem_Cell_Id = '"+tfstem.getText()+"'"+w+h+""
                + bmi+",BGroup = '"+bgrp+"',HomeFood = '"+homefood.getValue()+"',OutsideFood = '"+outfood.getValue()+"',"
                + "SleepHrs = '"+sleep.getValue()+"',Health_Checkup = '"+healthchkup+"',Addiction = '"+addict+"',ChronicDisease = '"+tfchronicdisease.getText()+"',"
                +"Sport = '"+tfsport.getText()+"',Veg = '"+veg+"', PWD = '"+pwd+"',Yoga = "+yoga.getValue()+",Meditation = "+meditation.getValue()+",Spiritual = "+spiritual.getValue()+",RatYourHealth = "+health.getValue()+" WHERE HH_SNo = "+hhsno;
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

    private void btncalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncalActionPerformed
        if (tfwt.getText().isEmpty() && tfht.getText().isEmpty())
        {
            javax.swing.JOptionPane.showMessageDialog(null, "Enter Weight and Height!!!");
        }
        else
        {
            int w = Integer.parseInt(tfwt.getText());   //kg  
            float h = Float.parseFloat(tfht.getText()) * 0.3048f;   //foot to m 
            float bmi = w / (h * h);
            tfbmi.setText(bmi+"");
        }
    }//GEN-LAST:event_btncalActionPerformed

    
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
            java.util.logging.Logger.getLogger(PbtMedicalDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PbtMedicalDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PbtMedicalDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PbtMedicalDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                /*PbtHomedashboard obj = new PbtHomedashboard(0, new PbtLoginData());
                PbtMedicalDetails dialog = new PbtMedicalDetails(new PbtPersonDashboard(obj, 0), 0, false);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);*/
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> bgroup;
    private javax.swing.JButton btncal;
    private javax.swing.JButton btnsave;
    private javax.swing.JSlider health;
    private javax.swing.JSlider homefood;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JSlider meditation;
    private javax.swing.JSlider outfood;
    private javax.swing.JRadioButton rbaddicno;
    private javax.swing.JRadioButton rbaddicyes;
    private javax.swing.JRadioButton rbhealthno;
    private javax.swing.JRadioButton rbhealthyes;
    private javax.swing.JRadioButton rbpwdno;
    private javax.swing.JRadioButton rbpwdyes;
    private javax.swing.JRadioButton rbvegno;
    private javax.swing.JRadioButton rbvegyes;
    private javax.swing.JSlider sleep;
    private javax.swing.JSlider spiritual;
    private javax.swing.JTextField tfbmi;
    private javax.swing.JTextField tfchronicdisease;
    private javax.swing.JTextField tfht;
    private javax.swing.JTextField tfsport;
    private javax.swing.JTextField tfstem;
    private javax.swing.JTextField tfwt;
    private javax.swing.JSlider yoga;
    // End of variables declaration//GEN-END:variables
}
