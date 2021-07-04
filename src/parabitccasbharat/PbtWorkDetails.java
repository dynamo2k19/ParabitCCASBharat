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
public class PbtWorkDetails extends javax.swing.JDialog {

    PbtAadharDetails aadharob;
    PbtPersonDashboard obperson;
    ButtonGroup bg1, bg2, bg3, bg4;
    int hhsno, wrkcat, sectorval, naturewrkval;
    ParabitDBC db, db1;
    String modoftravel, modevalue, wrkstatus, jobseek, itr, wrksec, naturewrk;
    public PbtWorkDetails(PbtPersonDashboard ob, PbtAadharDetails nob, int hhno, boolean in) {
        super(ob, true);
        setTitle("Work Details");
        initComponents();
        hhsno = hhno;
        aadharob = nob;
        obperson = ob;
        bg1 = new ButtonGroup();
        bg2 = new ButtonGroup();
        bg3 = new ButtonGroup();
        bg4 = new ButtonGroup();
        db = new ParabitDBC();
        db1 = new ParabitDBC(1);
        bg1.add(rbwrking);
        bg1.add(rbnotwrking);
        bg2.add(rbseekyes);
        bg2.add(rbseekno);
        bg3.add(rbeco);
        bg3.add(rbnoneco);
        bg3.add(rbboth);
        bg4.add(rbitryes);
        bg4.add(rbitrno);
        try
        {
            db1.rs1 = db1.stm.executeQuery("SELECT Total_Income FROM pbt_itr WHERE ano = '" + nob.uid + "'");
            if (db1.rs1.next())
            {
                tfincome.setText(db1.rs1.getInt(1)+"");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("aadhar scan");
        if (in == true)
        {
            incomp();
        }
    }
    
    public PbtWorkDetails(PbtPersonDashboard ob, int hhno, boolean in) {
        super(ob, true);
        initComponents();
        hhsno = hhno;
        obperson = ob;
        db = new ParabitDBC();
        bg1 = new ButtonGroup();
        bg2 = new ButtonGroup();
        bg3 = new ButtonGroup();
        bg4 = new ButtonGroup();
        bg1.add(rbwrking);
        bg1.add(rbnotwrking);
        bg2.add(rbseekyes);
        bg2.add(rbseekno);
        bg3.add(rbeco);
        bg3.add(rbnoneco);
        bg3.add(rbboth);
        bg4.add(rbitryes);
        bg4.add(rbitrno);
        System.out.println("No aadhar scan");
        if (in == true)
        {
            incomp();
        }
    }

    public void incomp()
    {
        try
        {
            if (obperson.db.rs2.getString("CWorkStat").equals("Y"))
            {
                rbwrking.setSelected(true);
            }
            else if (obperson.db.rs2.getString("CWorkStat").equals("N"))
            {
                rbnotwrking.setSelected(true);
            }
            
            if (obperson.db.rs2.getString("JobSeek").equals("Y"))
            {
                rbseekyes.setSelected(true);
            }
            else if (obperson.db.rs2.getString("JobSeek").equals("N"))
            {
                rbseekno.setSelected(true);
            }
            int w = obperson.db.rs2.getInt("WorkCategory");
            if (w == 1)
            {
                rbeco.setSelected(true);
            }
            else if (w == 2)
            {
                rbnoneco.setSelected(true);
            }
            else if (w == 3)
            {
                rbboth.setSelected(true);
            }
            tficsno.setText(obperson.db.rs2.getInt("ICSNo")+"");
            tfoccupation.setText(obperson.db.rs2.getString("Occupation"));
            tfwrkexp.setText(obperson.db.rs2.getString("WorkExp"));
            tfspecdesc.setText(obperson.db.rs2.getString("SpecDescription"));
            tfspecexp.setText(obperson.db.rs2.getString("SpecExp"));
            tfproflicno.setText(obperson.db.rs2.getString("ProfLicNo"));
            tfregno.setText(obperson.db.rs2.getString("BusiRegNo"));
            tfincome.setText(obperson.db.rs2.getInt("Income")+"");
            if (obperson.db.rs2.getString("ITR").equals("Y"))
            {
                rbitryes.setSelected(true);
            }
            else if (obperson.db.rs2.getString("ITR").equals("N"))
            {
                rbitrno.setSelected(true);
            }
            tfdistfrmwrkplace.setText(obperson.db.rs2.getFloat("DistFrmWorkPlace")+"");
            int wsec = obperson.db.rs2.getInt("WorkingSector");
            sectorval = wsec;
            String sector = "";
            while (wsec != 0)
            {
                int r = wsec % 10;
                if (r == 1)
                {
                    sector = sector + "Government Sector ";
                }
                if (r == 2)
                {
                    sector = sector + "Private Sector ";
                }
                if (r == 3)
                {
                    sector = sector + "Business ";
                }
                if (r == 4)
                {
                    sector = sector + "Freelancer ";
                }
                wsec = wsec / 10;
            }
            tfwrksec.setText(sector);
            int nat = obperson.db.rs2.getInt("WorkingSector");
            naturewrkval = nat;
            String nature = "";
            while (nat != 0)
            {
                int p = nat % 10;
                if (p == 1)
                {
                    nature = nature + "Primary ";
                }
                if (p == 2)
                {
                    nature = nature + "Manufacturing ";
                }
                if (p == 3)
                {
                    nature = nature + "Gas, Water Supply ";
                }
                if (p == 4)
                {
                    nature = nature + "Construction ";
                }
                if (p == 5)
                {
                    nature = nature + "Trade ";
                }
                if (p == 6)
                {
                    nature = nature + "Service ";
                }
                nat = nat / 10;
            }
            tfnaturewrk.setText(nature);
            
            String mod = obperson.db.rs2.getString("ModOfTravel");
            modevalue = mod;
            String mode = "";
            String[] split = mod.split(",");
            for (String s: split)
            {
                if (s.equals("1"))
                {
                    mode = mode + "On Foot\n";
                }
                if (s.equals("2"))
                {
                    mode = mode + "Bicycle\n";
                }
                if (s.equals("3"))
                {
                    mode = mode + "Moped/Scooter/Motorcycle\n";
                }
                if (s.equals("4"))
                {
                    mode = mode + "Car/Jeep/Van\n";
                }
                if (s.equals("5"))
                {
                    mode = mode + "Tempo/AutoRickshaw/Taxi\n";
                }
                if (s.equals("6"))
                {
                    mode = mode + "Bus\n";
                }
                if (s.equals("7"))
                {
                    mode = mode + "Train/Metro\n";
                }
                if (s.equals("8"))
                {
                    mode = mode + "Water Transport\n";
                }
                if (s.equals("9"))
                {
                    mode = mode + "Water Taxi\n";
                }
                if (s.equals("10"))
                {
                    mode = mode + "Air Transport\n";
                }
                if (s.equals("11"))
                {
                    mode = mode + "Air Taxi\n";
                }
                if (s.equals("12"))
                {
                    mode = mode + "Animal Transport\n";
                } 
            }
            tfmodoftravel.setText(mode);
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

        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        tfspecexp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tficsno = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfwrkexp = new javax.swing.JTextField();
        tfnaturewrk = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnsave = new javax.swing.JButton();
        tfoccupation = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfspecdesc = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tfregno = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tfproflicno = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tfincome = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        tfdistfrmwrkplace = new javax.swing.JTextField();
        rbwrking = new javax.swing.JRadioButton();
        rbnotwrking = new javax.swing.JRadioButton();
        rbseekyes = new javax.swing.JRadioButton();
        rbseekno = new javax.swing.JRadioButton();
        rbeco = new javax.swing.JRadioButton();
        rbnoneco = new javax.swing.JRadioButton();
        rbboth = new javax.swing.JRadioButton();
        rbitryes = new javax.swing.JRadioButton();
        rbitrno = new javax.swing.JRadioButton();
        btnselect = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tfmodoftravel = new javax.swing.JTextArea();
        tfwrksec = new javax.swing.JTextField();
        btnwrknature = new javax.swing.JButton();
        btnwrksec = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Current Working Status:");

        jLabel7.setText("ICSNo.:");

        jLabel2.setText("Work Category:");

        jLabel8.setText("Work Experience:");

        jLabel3.setText("Nature of Work:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setText("WORK DETAILS:");
        jLabel9.setToolTipText("");

        jLabel4.setText("Occupation:");

        btnsave.setText("Save");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        jLabel5.setText("Seeking Job:");

        jLabel6.setText("Working Sector:");

        jLabel10.setText("Specialization Experience:");

        jLabel11.setText("Specialization Description:");

        jLabel12.setText("Business Registration No.:");

        jLabel13.setText("Professional Liscence No.:");

        jLabel14.setText("ITR:");

        jLabel15.setText("Income:");

        jLabel16.setText("Mode of Travel:");

        jLabel17.setText("Distance From Work Place:");

        rbwrking.setText("Working");

        rbnotwrking.setText("Not Working");

        rbseekyes.setText("Yes");

        rbseekno.setText("No");

        rbeco.setText("Economic");

        rbnoneco.setText("Non-Economic");

        rbboth.setText("Both");

        rbitryes.setText("Yes");

        rbitrno.setText("No");

        btnselect.setText("select");
        btnselect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnselectActionPerformed(evt);
            }
        });

        tfmodoftravel.setColumns(20);
        tfmodoftravel.setRows(5);
        jScrollPane2.setViewportView(tfmodoftravel);

        btnwrknature.setText("select");
        btnwrknature.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnwrknatureActionPerformed(evt);
            }
        });

        btnwrksec.setText("select");
        btnwrksec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnwrksecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(rbeco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbnoneco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbboth))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfoccupation, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfnaturewrk, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(tfwrkexp, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnsave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(btnwrknature, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(23, 23, 23)
                                                .addComponent(tficsno, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(tfwrksec, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnwrksec, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfincome, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(41, 41, 41)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(rbitryes)
                            .addGap(18, 18, 18)
                            .addComponent(rbitrno))
                        .addComponent(jLabel9)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(18, 18, 18)
                                    .addComponent(tfproflicno, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(18, 18, 18)
                                    .addComponent(tfspecdesc, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(28, 28, 28)
                                    .addComponent(jLabel10)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfregno, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfspecexp, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addGap(18, 18, 18)
                            .addComponent(tfdistfrmwrkplace, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(btnselect, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(rbwrking)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(rbnotwrking)
                            .addGap(95, 95, 95)
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addComponent(rbseekyes)
                            .addGap(18, 18, 18)
                            .addComponent(rbseekno))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbwrking)
                        .addComponent(rbnotwrking))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rbseekyes)
                        .addComponent(rbseekno)))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbeco)
                    .addComponent(rbnoneco)
                    .addComponent(rbboth)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfwrksec, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnwrksec, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tficsno, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfnaturewrk, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnwrknature, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfoccupation, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfwrkexp, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfspecdesc, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfspecexp, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfproflicno, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfregno, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfincome, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbitryes)
                            .addComponent(rbitrno))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfdistfrmwrkplace, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnselect, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        if (rbwrking.isSelected())
        {
            wrkstatus = "Y";
        }
        else
        {
            wrkstatus = "N";
        }
        if (rbseekyes.isSelected())
        {
            jobseek = "Y";
        }
        else
        {
            jobseek = "N";
        }
        if (rbitryes.isSelected())
        {
            itr = "Y";
        }
        else
        {
            itr = "N";
        }
        if (rbeco.isSelected())
        {
            wrkcat = 1;
        }
        else if (rbnoneco.isSelected())
        {
            wrkcat = 2;
        }
        else
        {
            wrkcat = 3;
        }
        String icsno = "", inc = "", dist = "";
        
        if (!tficsno.getText().equals(""))
        {
            icsno = ",ICSNO = " + tficsno.getText();
        }
        if (!tfincome.getText().equals(""))
        {
            inc = ",Income = "+tfincome.getText();
        }
        if (!tfdistfrmwrkplace.getText().equals(""))
        {
            dist = ",DistFrmWorkPlace = '"+tfdistfrmwrkplace.getText();
        }
        
        
        String qry = "UPDATE pbtcensus_household SET CWorkStat = '"+wrkstatus+"',JobSeek = '"+jobseek+"',WorkCategory = "+wrkcat+","
                + "WorkingSector = "+sectorval+",NatureOfWork = "+naturewrkval+icsno+",Occupation = '"+tfoccupation.getText()+"',WorkExp = '"+tfwrkexp.getText()+"',"
                + "SpecDescription = '"+tfspecdesc.getText()+"',SpecExp = '"+tfspecexp.getText()+"',ProfLicNo = '"+tfproflicno.getText()+"',BusiRegNo = '"+tfregno.getText()+"'"
                + inc+",ITR = '"+itr+"'"+dist+",ModOfTravel = '"+modevalue+"' WHERE HH_SNo = "+hhsno;
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

    private void btnselectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnselectActionPerformed
        PbtModeOfTravel obj = new PbtModeOfTravel(this);
        obj.setLocationRelativeTo(this);
        obj.setVisible(true);
        tfmodoftravel.setText(modoftravel);
        System.out.println(modevalue);
    }//GEN-LAST:event_btnselectActionPerformed

    private void btnwrknatureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnwrknatureActionPerformed
        PbtNatureOfWork obj = new PbtNatureOfWork(this);
        obj.setLocationRelativeTo(this);
        obj.setVisible(true);
        tfnaturewrk.setText(naturewrk);
        System.out.println(naturewrkval);
    }//GEN-LAST:event_btnwrknatureActionPerformed

    private void btnwrksecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnwrksecActionPerformed
        PbtWrkSector obj = new PbtWrkSector(this);
        obj.setLocationRelativeTo(this);
        obj.setVisible(true);
        tfwrksec.setText(wrksec);
        System.out.println(sectorval);
    }//GEN-LAST:event_btnwrksecActionPerformed

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
            java.util.logging.Logger.getLogger(PbtWorkDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PbtWorkDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PbtWorkDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PbtWorkDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                /*PbtHomedashboard obj = new PbtHomedashboard(1, new PbtLoginData());
                PbtWorkDetails dialog = new PbtWorkDetails(new PbtPersonDashboard(obj, 1), 1, false);
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
    private javax.swing.JButton btnselect;
    private javax.swing.JButton btnwrknature;
    private javax.swing.JButton btnwrksec;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JRadioButton rbboth;
    private javax.swing.JRadioButton rbeco;
    private javax.swing.JRadioButton rbitrno;
    private javax.swing.JRadioButton rbitryes;
    private javax.swing.JRadioButton rbnoneco;
    private javax.swing.JRadioButton rbnotwrking;
    private javax.swing.JRadioButton rbseekno;
    private javax.swing.JRadioButton rbseekyes;
    private javax.swing.JRadioButton rbwrking;
    private javax.swing.JTextField tfdistfrmwrkplace;
    private javax.swing.JTextField tficsno;
    private javax.swing.JTextField tfincome;
    private javax.swing.JTextArea tfmodoftravel;
    private javax.swing.JTextField tfnaturewrk;
    private javax.swing.JTextField tfoccupation;
    private javax.swing.JTextField tfproflicno;
    private javax.swing.JTextField tfregno;
    private javax.swing.JTextField tfspecdesc;
    private javax.swing.JTextField tfspecexp;
    private javax.swing.JTextField tfwrkexp;
    private javax.swing.JTextField tfwrksec;
    // End of variables declaration//GEN-END:variables
}
