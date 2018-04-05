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
    JTextField name=new JTextField(2);
    JTextField author=new JTextField(4);
    ButtonGroup bgp=new ButtonGroup();
    JRadioButton R1=new JRadioButton("已借出");
    JRadioButton R2=new JRadioButton("未借出");
    JTextField id=new JTextField();
    JTextField house=new JTextField();
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
        name.setBounds(190,60,140,20);
        author.setBounds(190,100,140,20);
        R1.setBounds(200,140,80,20);
        R2.setBounds(280,140,80,20);
        id.setBounds(190,180,140,20);
        house.setBounds(190,220,140,20);
        reset.setBounds(190,260,65,20);
        addmsg.setBounds(265,260,65,20);
        panel.add(name);
        panel.add(author);
        panel.add(R1);
        panel.add(R2);
        panel.add(id);
        panel.add(house);
        panel.add(reset);
        panel.add(addmsg);
        bgp.add(R1);
        bgp.add(R2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addmsg){
            String state=null;
            String name,author,id,house;
            name=this.name.getText();
            author=this.author.getText();
            id=this.id.getText();
            house=this.house.getText();
            if(name.equals("")||author.equals("")||id.equals("")||house.equals("")){
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
                    ResultSet rs=jdbc.getSt().executeQuery("select * from books where name=\'"+name+"\'");
                    if(rs.next()) {
                        String sql = "delect from books where name=\'" + name + "\'";
                        jdbc.getSt().executeUpdate(sql);
                    }
                    int a=jdbc.getSt().executeUpdate("insert into books (NAME,AUTHOR,STATE,ID,HOUSE) values (\'"+this.name.getText()+"\',\'"+this.author.getText()+"\',\'"+state+"\',\'"+this.id.getText()+"\',\'"+this.house.getText()+"\')");
                    if(a==1){
                        JOptionPane.showMessageDialog(null,"信息成功添加");
                        setVisible(true);
                        this.name.setText("");
                        this.author.setText("");
                        this.id.setText("");
                        this.house.setText("");
                        this.name.requestFocus();
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
            name.setText("");
            author.setText("");
            id.setText("");
            house.setText("");
            name.requestFocus();
        }
    }
    public static void main(String[] args){
        Jdbc jdbc=new Jdbc();
        new Addinfo(jdbc);
        jdbc.close();
    }
}
