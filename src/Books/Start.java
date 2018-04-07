package Books;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Start extends JFrame implements ActionListener{
    JPanel imagePanel;
    ImageIcon background;
    JButton book,people;
    Jdbc jdbc=null;

    public static void main(String[] args){
        Jdbc jdbc=new Jdbc();
        jdbc.conn();
        new Start(jdbc);
    }
    public Start(Jdbc jdbc){
        super("图书管理系统");
        this.jdbc=jdbc;
        Font font=new Font("微软雅黑",Font.BOLD,20);
        JLabel title=new JLabel();
        title.setFont(font);
        title.setForeground(Color.red);
        ImageIcon pic1=new ImageIcon("book.png");
        ImageIcon pic2=new ImageIcon("people.png");
        ImageIcon tit=new ImageIcon("title.png");
        book=new JButton();
        people=new JButton();
        book.addActionListener(this);
        people.addActionListener(this);

        /////////在此处修改两个按钮和一个标题的位置和大小/////////////////////
        book.setBounds(540,505,300,150);
        people.setBounds(10,500,240,148);
        title.setBounds(265,-80,600,350);
        ///////////////////////////////////////////////////////////////

        background=new ImageIcon("back.png");
        //将背景图片放入label中
        JLabel back=new JLabel(background);
          /*Image temp = tu.getImage().getScaledInstance(food.getWidth(),
                  food.getHeight(),tu.getImage().SCALE_DEFAULT);
          tu=new ImageIcon(temp);                  图片适应按钮的大小，但是按钮的大小要确定*/
        book.setIcon(pic1);
        people.setIcon(pic2);
        title.setIcon(tit);
        book.setHorizontalTextPosition(SwingConstants.CENTER);
        book.setContentAreaFilled(false); //按钮透明化。
        people.setContentAreaFilled(false);
        book.setBorderPainted(false);
        people.setBorderPainted(false);
        back.setBounds(0,0,background.getIconWidth(),background.getIconHeight());
        imagePanel=(JPanel)this.getContentPane();
        imagePanel.setOpaque(false);
        imagePanel.setLayout(null);
        imagePanel.add(title);
        imagePanel.add(book);
        imagePanel.add(people);
        this.setVisible(true);
        this.getLayeredPane().setLayout(null);
        this.getLayeredPane().add(back,new Integer(Integer.MIN_VALUE));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(background.getIconWidth(),background.getIconHeight());
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==book){
            new UserLogin(jdbc);
        }
        else if(e.getSource()==people){
            new Login(jdbc);
        }
    }
}
