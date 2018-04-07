package Books;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modinfo extends JDialog implements ActionListener{
    Jdbc jdbc=null;
    JLabel label1=new JLabel("书名",JLabel.CENTER);
    JLabel label2=new JLabel("作者",JLabel.CENTER);
    JLabel label3=new JLabel("状态",JLabel.CENTER);
    JLabel label4=new JLabel("编号",JLabel.CENTER);
    JLabel label5=new JLabel("出版社",JLabel.CENTER);
    JTextField name=new JTextField(2);
    JTextField author=new JTextField(4);
    ButtonGroup bgp=new ButtonGroup();
    JRadioButton R1=new JRadioButton("已借出");
    JRadioButton R2=new JRadioButton("未借出",true);
    JTextField id=new JTextField();
    JTextField house=new JTextField();
    JButton reset=new JButton("清空");
    JButton addmsg=new JButton("修改");

    public Modinfo(Jdbc jdbc,Frame owner,String title,boolean model,Manage manage,int rowNum){
        super(owner,title,model);
        System.out.println("进入构造函数");
        this.jdbc=jdbc;
        Font font=new Font("微软雅黑",Font.BOLD,20);
        this.setSize(500,400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(null);
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
        label5.setBounds(125, 220, 70, 20);
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);
        name.setBounds(190,60,140,20);
        author.setBounds(190,100,140,20);
        R1.setBounds(200,140,80,20);
        R2.setBounds(280,140,80,20);
        id.setBounds(190,180,140,20);
        house.setBounds(190,220,140,20);
        reset.setBounds(190,260,65,20);
        addmsg.setBounds(265,260,65,20);
        name.setText((String)manage.getValueAt(rowNum,0));
        author.setText((String)manage.getValueAt(rowNum,1));
        ///问题：如何获取组合框的选择？////////////////////////////////////
        if(((String)manage.getValueAt(rowNum,2)).equals("已借")){
            R1.setSelected(true);
        }
        else {
            R2.setSelected(true);
        }
        id.setText((String)manage.getValueAt(rowNum,3));
        house.setText((String)manage.getValueAt(rowNum,4));
        this.add(name);
        this.add(author);
        this.add(R1);
        this.add(R2);
        this.add(id);
        this.add(house);
        this.add(reset);
        this.add(addmsg);
        bgp.add(R1);
        bgp.add(R2);
        this.setVisible(true);
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
                    /*ResultSet rs=jdbc.getSt().executeQuery("select * from books where name=\'"+name+"\'");
                    if(rs.next()) {
                        String sql = "delect from books where name=\'" + name + "\'";
                        jdbc.getSt().executeUpdate(sql);
                    }*/
                    int a=jdbc.getSt().executeUpdate("update books set name='"+name+"',author='"
                            +author+"',state='"+state+"',house='"+house+"' where id='"+id+"'");
                    System.out.println("update books set name='"+name+"',author='"
                            +author+"',state='"+state+"',house='"+house+"' where id='"+id+"'");
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
}
