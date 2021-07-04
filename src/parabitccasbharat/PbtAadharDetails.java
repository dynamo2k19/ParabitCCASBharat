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
public class PbtAadharDetails {
    String[] data;
    String uid,name,gender,dob,dateOfBirth,careOf,building,street,landmark,vtcName,poName,districtName,subDistrictName,
            stateName,pincode;
    
    public PbtAadharDetails(String values[])
    {
        data = new String[14];
        data = values;
        uid = values[0];
        name = values[1];
        gender = values[2];
        dateOfBirth = values[3];
        careOf = values[4];
        building = values[5];
        street = values[6];
        landmark = values[7];
        vtcName = values[8];
        poName = values[9];
        districtName = values[10];
        subDistrictName = values[11];
        stateName = values[12];
        pincode = values[13];
        
    }
    
    public PbtAadharDetails(String values[], int a)
    {
        data = new String[14];
        data = values;
        uid = values[0];
        name = values[1];
        gender = values[2];
        dateOfBirth = values[3];
        careOf = values[4];
        building = values[5];
        street = values[6];
        landmark = values[7];
        vtcName = values[8];
        poName = values[9];
        districtName = values[10];
        subDistrictName = values[11];
        stateName = values[12];
        pincode = values[13];
        String db = dateOfBirth;
        dateOfBirth = db.substring(6) + "-"  + db.substring(3, 5) + "-" + db.substring(0, 2);
        
    }
    
    public PbtAadharDetails()
    {
            String db = "18-01-2001";
           String dob = db.substring(6) + "-"  + db.substring(3, 5) + "-" + db.substring(0, 2);
        System.out.println(dob);
    }
    
}
