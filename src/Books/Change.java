package Books;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Change extends JFrame implements ActionListener{

    Jdbc jdbc=null;
    JPanel panel=new JPanel();
    JLabel label1=new JLabel("账  号",JLabel.LEFT);
    JLabel label2=new JLabel("旧密码",JLabel.LEFT);
    JLabel label3=new JLabel("新密码",JLabel.LEFT);
    JTextField ID=new JTextField();
    JTextField oldpassword=new JTextField();
    JTextField newpassword=new JTextField();
    JButton change=new JButton("重置");
    JButton reset=new JButton("清空");

    public Change(Jdbc jdbc){
        super("修改教师密码");
        this.jdbc=jdbc;
        this.setSize(500,400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.add(panel);
        panel.setLayout(null);
        change.addActionListener(this);
        reset.addActionListener(this);
        label1.setBounds(140,120,50,20);
        label2.setBounds(140,160,50,20);
        label3.setBounds(140,200,50,20);
        ID.setBounds(190,120,150,20);
        oldpassword.setBounds(190,160,150,20);
        newpassword.setBounds(190,200,150,20);
        change.setBounds(190,240,70,20);
        reset.setBounds(270,240,70,20);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(ID);
        panel.add(oldpassword);
        panel.add(newpassword);
        panel.add(change);
        panel.add(reset);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
