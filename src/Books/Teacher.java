package Books;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.*;

public class Teacher extends JFrame implements ActionListener,WindowListener {
    Jdbc jdbc=null;
    /*JMenuBar bar=new JMenuBar();
    JMenu menu1=new JMenu("信息");
    JMenu menu2=new JMenu("查看");
    JMenu menu3=new JMenu("修改密码");
    JMenuItem item1=new JMenuItem("录入图书信息");
    JMenuItem item2=new JMenuItem("查看图书信息");
    JMenuItem item3=new JMenuItem("修改登录密码");*/
    JPanel panel=new JPanel();
    ImageIcon background;
    JButton bt1=null;
    JButton bt2=null;
    JButton bt3=null;

    public Teacher(Jdbc jdbc){
        super("欢迎图书管理员");
        this.jdbc=jdbc;
        background=new ImageIcon("admin_back.png");
        JLabel label=new JLabel(background);
        label.setBounds(0,0,background.getIconWidth(),background.getIconHeight());
        ImageIcon pic1=new ImageIcon("admin1.png");
        ImageIcon pic2=new ImageIcon("admin2.png");
        ImageIcon pic3=new ImageIcon("admin3.png");
        bt1=new JButton();
        bt2=new JButton();
        bt3=new JButton();
        //////////修改图片的位置/////////////////////////
        bt1.setBounds(200,200,200,200);
        bt2.setBounds(400,200,200,200);
        bt3.setBounds(600,200,200,200);
        //////////修改图片的位置/////////////////////////
        bt1.setIcon(pic1);
        bt2.setIcon(pic2);
        bt3.setIcon(pic3);
        bt1.setContentAreaFilled(false);
        bt2.setContentAreaFilled(false);
        bt3.setContentAreaFilled(false);
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt3.addActionListener(this);
        bt1.setBorderPainted(false);
        bt2.setBorderPainted(false);
        bt3.setBorderPainted(false);
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        panel=(JPanel)this.getContentPane();
        panel.setLayout(null);
        panel.setOpaque(false);
        panel.add(bt1);
        panel.add(bt2);
        panel.add(bt3);
        this.setSize(background.getIconWidth(),background.getIconHeight());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        /*this.setJMenuBar(bar);
        bar.add(menu1);
        bar.add(menu2);
        bar.add(menu3);
        menu1.add(item1);
        menu2.add(item2);
        menu3.add(item3);
        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);*/
    }
    public static void main(String[] args){
        Jdbc jdbc=new Jdbc();
        jdbc.conn();
        new Teacher(jdbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bt1){
            Addinfo addinfo=new Addinfo(jdbc);
        }
        if(e.getSource()==bt2){
            Table table=new Table(jdbc);
        }
        if(e.getSource()==bt3){
            Change change=new Change(jdbc);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("jdbc关闭了");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("jdbc关闭了");
        jdbc.close();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
