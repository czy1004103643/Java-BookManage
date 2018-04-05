package Books;

import javax.swing.*;
import java.sql.*;

public class Jdbc {
    public Connection getCt() {
        return ct;
    }
    public void setCt(Connection ct) {
        this.ct = ct;
    }
    public Statement getSt() {
        return st;
    }
    public void setSt(Statement st) {
        this.st = st;
    }

    private String forname="oracle.jdbc.driver.OracleDriver";
    private String url="jdbc:oracle:thin:@localhost:1521:orclhsp";
    private String user="scott";
    private String pass="123";
    private Connection ct;
    private Statement st;

    public void conn(){
        try{
            try {
                Class.forName(forname);
            }catch (ClassNotFoundException ce){
                JOptionPane.showMessageDialog(null,ce.getMessage());
            }
            ct=DriverManager.getConnection(url,user,pass);
            st=ct.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void close(){
        try{
            st.close();
            ct.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
