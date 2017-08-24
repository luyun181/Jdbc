package com.jdbc.www.jdbc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* try
        {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序
            Connection con = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://47.94.106.128/pt2", "root", "x5qwer");//192.168.16.116是我的ip地址
            //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
            String sql = "select * from admin";
            PreparedStatement pstmt;
            try {
                pstmt = (PreparedStatement)con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                int col = rs.getMetaData().getColumnCount();
                System.out.println("============================");
                while (rs.next()) {
                    for (int i = 1; i <= col; i++) {
                        System.out.print(rs.getString(i) + "\t");
                        if ((i == 2) && (rs.getString(i).length() < 8)) {
                            System.out.print("\t");
                        }
                    }
                    System.out.println("");
                }
                System.out.println("============================");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Success connect Mysql server!");
            Toast.makeText(this,"Success connect Mysql server!",Toast.LENGTH_LONG).show();
        }catch( SQLException ee)
        {
            Toast.makeText(this,"EEE!",Toast.LENGTH_LONG).show();
            ee.printStackTrace();
        }
        catch (Exception e)
        {
            Toast.makeText(this,"E!",Toast.LENGTH_LONG).show();
            System.out.print("Error loading Mysql Driver!");
            e.printStackTrace();
        }*/
        new Thread()
        {
            public void run() {
                try {
                    //注册驱动
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://47.94.106.128/pt2";
                    Connection conn = (Connection) DriverManager.getConnection(url, "root", "x5qwer");
                    Statement stmt = conn.createStatement();
                    String sql = "select * from admin";
                    ResultSet rs = stmt.executeQuery(sql);

                    while (rs.next()) {
                        Log.v("yzy", "field1-->"+rs.getString(1)+"  field2-->"+rs.getString(2));
                    }


                    rs.close();
                    stmt.close();
                    conn.close();
                    Log.v("yzy", "success to connect!");
                }catch(ClassNotFoundException e)
                {
                    Log.v("yzy", "fail to connect!"+"  "+e.getMessage());
                } catch (SQLException e)
                {
                    Log.v("yzy", "fail to connect!"+"  "+e.getMessage());
                }
            };
        }.start();
    }
}
