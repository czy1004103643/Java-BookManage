package Books;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.util.Vector;

//管理员对books表进行操作的类
public class Manage extends AbstractTableModel{

    Vector row,col;
    Jdbc jdbc=null;

    public Manage(Jdbc jdbc){
        this.jdbc=jdbc;
        this.init("");
    }

    public Manage(Jdbc jdbc,String sql){
        this.jdbc=jdbc;
        this.init(sql);
    }

    //共有多少行
    @Override
    public int getRowCount() {
        return this.row.size();
    }

    //共有多少列
    @Override
    public int getColumnCount() {
        return this.col.size();
    }

    //得到某行某列的数据
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return ((Vector)this.row.get(rowIndex)).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return (String)this.col.get(column);
    }

    public void init(String sql){
        if(sql.equals("")){
            sql="select * from books";
        }
        //初始化数据模型
        col=new Vector();
        //设置列名
        col.add("书名");
        col.add("作者");
        col.add("状态");
        col.add("编号");
        col.add("出版社");
        row=new Vector();
        try {
            ResultSet rs = jdbc.getSt().executeQuery(sql);
            while(rs.next()){
                Vector group=new Vector();
                group.add(rs.getString(1));
                group.add(rs.getString(2));
                group.add(rs.getString(3));
                group.add(rs.getString(4));
                group.add(rs.getString(5));
                row.add(group);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"连接失败");
        }
    }
}
