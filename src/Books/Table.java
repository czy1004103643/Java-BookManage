package Books;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;

public class Table extends JFrame implements ActionListener{
    Jdbc jdbc=null;
    JTable jt=null;
    JScrollPane jsp=null;
    JPanel panel1=null;
    JButton bt1=null;
    JButton bt2=null;
    JButton bt3=null;
    JButton bt4=null;
    JButton bt5=null;
    JTextField tf=null;
    Manage manage;

    Table(Jdbc jdbc){
        super("查看图书信息");
        this.jdbc=jdbc;
        manage=new Manage(jdbc);
        jt=new JTable(manage);
        jt.getTableHeader().setFont(new Font("楷体",Font.BOLD,20));
        DefaultTableCellRenderer r=new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        jt.setDefaultRenderer(Object.class,r);
        jt.setFont(new Font("微软雅黑",Font.BOLD,20));
        jt.setRowHeight(30);
        jt.setBounds(0,0,1000,500);
        jsp=new JScrollPane(jt);
        jsp.setBounds(0,0,1000,500);
        panel1=new JPanel();
        panel1.setLayout(null);
        panel1.add(jsp);
        panel1.setBounds(0,0,1000,500);
        bt1=new JButton("修改");
        bt2=new JButton("上一页");
        bt3=new JButton("下一页");
        bt4=new JButton("查询");
        bt5=new JButton("删除");
        tf=new JTextField(10);
        bt1.setBounds(100,550,100,50);
        bt2.setBounds(300,550,100,50);
        bt3.setBounds(500,550,100,50);
        bt4.setBounds(700,550,100,50);
        bt5.setBounds(800,550,100,50);
        tf.setFont(new Font("微软雅黑",Font.PLAIN,20));
        tf.setBounds(500,500,200,30);
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt3.addActionListener(this);
        bt4.addActionListener(this);
        bt5.addActionListener(this);
        this.add(bt1);
        this.add(bt2);
        this.add(bt3);
        this.add(bt4);
        this.add(bt5);
        this.add(tf);
        this.add(panel1);
        this.setLayout(null);
        this.setSize(1000,700);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args){
        Jdbc jdbc=new Jdbc();
        jdbc.conn();
        new Table(jdbc);
        jdbc.close();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bt4){
            String name=this.tf.getText().trim();
            String sql="select * from books where name='"+name+"'";
            manage=new Manage(jdbc,sql);
            jt.setModel(manage);
        }
        if(e.getSource()==bt5){
            //一行都没选返回-1
            int rowNum=this.jt.getSelectedRow();
            if(rowNum==-1){
                JOptionPane.showMessageDialog(this,"请选择一行");
                return;
            }
            String id=(String) manage.getValueAt(rowNum,3);
            System.out.println("id="+id);
            try {
                jdbc.getSt().executeUpdate("delete from books where id='"+id+"'");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            manage=new Manage(jdbc);
            jt.setModel(manage);
        }
        if(e.getSource()==bt1){
            int rowNum=this.jt.getSelectedRow();
            if(rowNum==-1){
                JOptionPane.showMessageDialog(this,"请选择一行");
                return;
            }
            Modinfo modinfo=new Modinfo(jdbc,"修改图书信息",manage,rowNum);

        }
    }
}
