/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia.model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author chwu
 */
public class User implements Serializable{

    private int userid;
    private String username;
    private String password;
    private String address;
    private String zipcode;
    private String phone;
    private String email;
    private int role = 1;
    private boolean validationCheck = true;
    public ArrayList<String> outputMessage = new ArrayList<String>();
   

    public boolean initFromRequest(HttpServletRequest request) throws IndexOutOfBoundsException{
        request.getSession().setAttribute("Check", "");


        if (((request.getParameter("name")).length() == 0) || ((request.getParameter("password")).length() == 0) || ((request.getParameter("address")).length() == 0) || ((request.getParameter("zipcode")).length() == 0) || ((request.getParameter("phone")).length() == 0) || ((request.getParameter("email")).length() == 0)) {
            outputMessage.add("All the field should be filfulled!");
            validationCheck = false;
        }


        if (request.getParameter("password").compareTo(request.getParameter("confirmpassword")) == 0) {
            setUsername(request.getParameter("name"));
            setPassword(request.getParameter("password"));
            setAddress(request.getParameter("address"));
            setZipcode(request.getParameter("zipcode"));
            setPhone(request.getParameter("phone"));
            setEmail(request.getParameter("email"));

        } else {
            outputMessage.add("password is NOT match, please try it again");
                 validationCheck = false;
        }

        if ((request.getParameter("password").length() <= 6) || (request.getParameter("password").matches("^([a-zA-Z+]+[0-9+]+[&@!#+]+)$ "))) {
            outputMessage.add("password should contain at least 6 characters, at least letter character, and at least one non-digit/letter-character");
                        validationCheck = false;
        }



        if ((!isInteger(request.getParameter("zipcode"))) || (request.getParameter("zipcode").length() != 4)) {
            outputMessage.add("Zipcode should be a 4-digit number");
                        validationCheck = false;

        }

        if (isValidEmailAddress(email) == false) {
            outputMessage.add("The Email address  is invalided.");
            validationCheck = false;
        }
        
        for(String s:outputMessage){
        System.out.println(s);
    }
        request.getSession().setAttribute("Check", outputMessage);
        return validationCheck;
    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidEmailAddress(String aEmailAddress) {
        if (aEmailAddress == null) {
            return false;
        }
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(aEmailAddress);
            if (!hasNameAndDomain(aEmailAddress)) {
                result = false;
            }
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    private static boolean hasNameAndDomain(String aEmailAddress) {
        String[] tokens = aEmailAddress.split("@");
        return tokens.length == 2
                && (tokens[0] != null)
                && (tokens[1] != null);
    }

    @Override
    public String toString() {
        return "User{" + "userid=" + getUserid() + ", username=" + getUsername() + ", password=" + getPassword() + ", address=" + getAddress() + ", zipcode=" + getZipcode() + ", phone=" + getPhone() + ", email=" + getEmail() + ", role=" + getRole() + '}';
    }

    /**
     * @return the userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the role
     */
    public int getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(int role) {
        this.role = role;
    }
}
