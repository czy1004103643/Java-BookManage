package Books;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class User extends JFrame implements ActionListener{
    Jdbc jdbc=null;
    JTable jt1=null;
    JTable jt2=null;
    JScrollPane jsp1=null;
    JScrollPane jsp2=null;
    ImageIcon background;
    JPanel panel;
    UsManage usManage1;
    UsManage usManage2;

    public User(Jdbc jdbc,String name){
        super("欢迎来到图书馆");
        System.out.println("这是构造函数");
        this.jdbc=jdbc;
        usManage1=new UsManage(jdbc);
        usManage2=new UsManage(jdbc,"select name,author,house from books where person='"
                +name+"'");
        background=new ImageIcon("Userback.png");
        JLabel backlabel=new JLabel(background);
        backlabel.setBounds(0,0,background.getIconWidth(),background.getIconHeight());
        this.getLayeredPane().add(backlabel,new Integer(Integer.MIN_VALUE));
        panel=(JPanel)this.getContentPane();
        panel.setLayout(null);
        panel.setOpaque(false);

        jt1=new JTable(usManage1);
        jt1.getTableHeader().setFont(new Font("楷体",Font.BOLD,20));
        DefaultTableCellRenderer r1=new DefaultTableCellRenderer();
        r1.setHorizontalAlignment(JLabel.CENTER);
        jt1.setDefaultRenderer(Object.class,r1);
        jt1.setFont(new Font("微软雅黑",Font.BOLD,20));
        jt1.setRowHeight(30);
        jt1.setBounds(0,0,500,500);

        jt1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==1){
                    int row =((JTable)e.getSource()).rowAtPoint(e.getPoint());
                    int col=((JTable)e.getSource()).columnAtPoint(e.getPoint());
                    System.out.println((String)usManage1.getValueAt(row,col));
                }
            }
        });

        jt2=new JTable(usManage2);
        jt2.getTableHeader().setFont(new Font("楷体",Font.BOLD,20));
        DefaultTableCellRenderer r2=new DefaultTableCellRenderer();
        r2.setHorizontalAlignment(JLabel.CENTER);
        jt2.setDefaultRenderer(Object.class,r2);
        jt2.setFont(new Font("微软雅黑",Font.BOLD,20));
        jt2.setRowHeight(30);
        jt2.setBounds(500,0,500,250);

        jsp1=new JScrollPane(jt1);
        jsp1.setBounds(0,0,500,500);
        jsp2=new JScrollPane(jt2);
        jsp2.setBounds(500,0,500,250);
        panel.add(jsp1);
        panel.add(jsp2);
        panel.setBounds(0,0,1000,500);

        this.setSize(background.getIconWidth(),background.getIconHeight());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args){
        Jdbc jdbc=new Jdbc();
        jdbc.conn();
        new User(jdbc,"select * from users where username='aa'");
    }
}
