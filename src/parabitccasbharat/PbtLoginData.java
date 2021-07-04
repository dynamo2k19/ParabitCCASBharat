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
public class PbtLoginData {
    static String ceid, state, dist, city, town;
    static int grade;
    PbtLoginData(String empid, int empgrade, String areastate, String areadist, String areacity)
    {
        ceid = empid;
        grade = empgrade;
        state = areastate;
        dist = areadist;
        city = areacity;
    }
    
    PbtLoginData()
    {
        /*ceid = "93425";
        grade = 1;
        state = "India";
        dist = "India";
        city = "India";*/
        
        ceid = "524501";
        grade = 5;
        state = "Bihar";
        dist = "Rohtas";
        city = "Sasaram";
        
        /*ceid = "424300";
        grade = 5;
        state = "Bihar";
        dist = "Rohtas";
        city = "Sasaram";*/
        
        /*ceid = "324446";
        grade = 3;
        state = "Bihar";
        dist = "Rohtas";
        city = "Rohtas";*/
    }
    
}

        