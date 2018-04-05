package Books;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Addinfo extends JFrame implements ActionListener{
    Jdbc jdbc=null;
    JPanel panel=new JPanel();
    JLabel label1=new JLabel("书名",JLabel.CENTER);
    JLabel label2=new JLabel("作者",JLabel.CENTER);
    JLabel label3=new JLabel("状态",JLabel.CENTER);
    JLabel label4=new JLabel("编号",JLabel.CENTER);
    JLabel label5=new JLabel("出版社",JLabel.CENTER);
    JTextField num=new JTextField(2);
    JTextField name=new JTextField(4);
    ButtonGroup bgp=new ButtonGroup();
    JRadioButton R1=new JRadioButton("已借出");
    JRadioButton R2=new JRadioButton("未借出");
    JTextField cla=new JTextField();
    JTextField scl=new JTextField();
    JButton reset=new JButton("清空");
    JButton addmsg=new JButton("添加");
    JButton turn=new JButton("返回");

    public Addinfo(Jdbc jdbc){
        super("添加图书信息");
        this.jdbc=jdbc;
        Font font=new Font("微软雅黑",Font.BOLD,20);
        this.setSize(500,400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(panel);
        panel.setLayout(null);
        addmsg.addActionListener(this);
        reset.addActionListener(this);
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        label5.setFont(font);
        label1.setBounds(135, 60, 70, 20);
        label2.setBounds(135, 100, 70, 20);
        label3.setBounds(135, 140, 70, 20);
        label4.setBounds(135, 180, 70, 20);
        label5.setBounds(135, 220, 70, 20);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        num.setBounds(190,60,140,20);
        name.setBounds(190,100,140,20);
        R1.setBounds(200,140,80,20);
        R2.setBounds(280,140,80,20);
        cla.setBounds(190,180,140,20);
        scl.setBounds(190,220,140,20);
        reset.setBounds(190,260,65,20);
        addmsg.setBounds(265,260,65,20);
        panel.add(num);
        panel.add(name);
        panel.add(R1);
        panel.add(R2);
        panel.add(cla);
        panel.add(scl);
        panel.add(reset);
        panel.add(addmsg);
        bgp.add(R1);
        bgp.add(R2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addmsg){
            String state=null;
            String id,nam,clas;
            id=num.getText();
            nam=name.getText();
            clas=cla.getText();
            if(id.equals("")||nam.equals("")||clas.equals("")){
                JOptionPane.showMessageDialog(null,"输入的信息不全");
            }
            else if((!R1.isSelected())&&(!R2.isSelected())){
                state="";
                JOptionPane.showMessageDialog(null,"书籍状态不可为空");
            }
            else {
                if(R1.isSelected()){
                    state="已借";
                }
                else {
                    state="未借";
                }
                try{
                    ResultSet rs=jdbc.getSt().executeQuery("select * from stu where ID=\'"+id+"\'");
                    if(rs.next()) {
                        String sql = "delect from stu where ID=\'" + id + "\'";
                        jdbc.getSt().executeUpdate(sql);
                    }
                    int a=jdbc.getSt().executeUpdate("insert into books (NAME,AUTHOR,STATE,ID,HOUSE) values (\'"+num.getText()+"\',\'"+name.getText()+"\',\'"+state+"\',\'"+cla.getText()+"\',\'"+scl.getText()+"\')");
                    if(a==1){
                        JOptionPane.showMessageDialog(null,"信息成功添加");
                        setVisible(false);
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"信息添加失败");
                        setVisible(true);
                    }
                }catch (SQLException se){
                    JOptionPane.showMessageDialog(null,se.getMessage());
                }
            }
        }
        else {
            num.setText("");
            name.setText("");
            cla.setText("");
            scl.setText("");
            num.requestFocus();
        }
    }
    public static void main(String[] args){
        Jdbc jdbc=new Jdbc();
        new Addinfo(jdbc);
        jdbc.close();
    }
}
