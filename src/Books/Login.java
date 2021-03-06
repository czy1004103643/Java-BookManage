package Books;


import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.painter.border.StandardBorderPainter;
import org.pushingpixels.substance.api.shaper.StandardButtonShaper;
import org.pushingpixels.substance.api.skin.CremeSkin;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener{
    Jdbc jdbc=null;
    JPanel panel=new JPanel();
    JLabel label1=new JLabel("账号:");
    JTextField username=new JTextField();
    JLabel label2=new JLabel("密码:");
    JPasswordField password=new JPasswordField();
    JButton enter=new JButton("登录");
    JButton exit=new JButton("退出");
    ImageIcon background;

    public Login(Jdbc jdbc){
        super("图书管理系统");
        this.jdbc=jdbc;
        background=new ImageIcon("login_back.png");
        JLabel back=new JLabel(background);
        this.setResizable(false);
        this.setSize(background.getIconWidth(),background.getIconHeight());
        enter.setBounds(110,180,70,20);
        exit.setBounds(240,180,70,20);
        enter.addActionListener(this);
        exit.addActionListener(this);
        this.getLayeredPane().add(back,new Integer(Integer.MIN_VALUE));
        back.setBounds(0,0,background.getIconWidth(),background.getIconHeight());
        Font font=new Font("微软雅黑",Font.BOLD,15);
        label1.setBounds(115,90,50,25);
        label1.setFont(font);
        username.setBounds(150,90,150,25);
        label2.setBounds(115,125,50,25);
        label2.setFont(font);
        password.setBounds(150,125,150,25);
        //this.setBounds(110,60,540,320);
        //this.add(panel);
        panel=(JPanel)this.getContentPane();
        panel.setLayout(null);
        panel.setOpaque(false);
        panel.add(enter);
        panel.add(exit);
        panel.add(label1);
        panel.add(username);
        panel.add(label2);
        panel.add(password);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}

    @Override
    public void actionPerformed(ActionEvent e) {
        String id,pass;
        id=username.getText();
        pass=String.valueOf(password.getPassword());
        System.out.println(pass);
        if(e.getSource()==exit){
            System.exit(0);
        }
        if(e.getSource()==enter){
            if(id.equals("")||pass.equals("")){
                JOptionPane.showMessageDialog(null,"你输入的账号密码为空");
            }
            else {
                try {
                    System.out.println("select * from admin where username=\'"+id+"\'");
                    ResultSet rs = jdbc.getSt().executeQuery("select * from admin where username=\'"+id+"\'");
                    if (rs.next()) {
                        if (rs.getString("PASSWORD").equals(pass)) {
                            this.setVisible(false);
                            new Teacher(jdbc);
                        } else {
                            JOptionPane.showMessageDialog(null, "你输入的密码有误");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "用户名不存在");
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        }
    }
}
