package org.example;

import java.sql.*;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {


    public static void main(String[] args) {
        //TIP 当文本光标位于高亮显示的文本处时按 <shortcut actionId="ShowIntentionActions"/>
        // 查看 IntelliJ IDEA 建议如何修正。
        System.out.println("Hello and welcome!");

        try {
            Class.forName("com.kingbase.Driver");  //加载驱动程序
            String url = "jdbc:kingbase://localhost:54321/DB1";
            Connection conn;
            try {
                conn = DriverManager.getConnection(url, "system", "krms");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("数据库连接成功");
            DatabaseMetaData dbmd = null;
            try {
                dbmd = conn.getMetaData();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ResultSet get_table = null;  //获取表信息
            try {
                get_table = dbmd.getTables(null, null, "%", new String[]{"TABLE"});
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // 获取每个表名并打印
            while (true) {
                try {
                    if (get_table.next()) {
                        System.out.println(get_table.getString("TABLE_NAME"));
                        System.out.println("数据");
                    }
                    else
                        break;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            conn.close();
            System.out.println("数据库断开连接");
        } catch (RuntimeException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}


            /*
            DatabaseMetaData metaData = con.getMetaData(); // 获取数据库数据
            // 获取所有表的信息
            ResultSet tableResultSet = metaData.getTables(null, null, "%", new String[]{"TABLE"});
            while (tableResultSet.next()) {
                String tableName = tableResultSet.getString("TABLE_NAME");
                System.out.println("Table Name: " + tableName);

                // 获取每个表的列信息
                ResultSet columnResultSet = metaData.getColumns(null, null, tableName, null);
                while (columnResultSet.next()) {
                    String columnName = columnResultSet.getString("COLUMN_NAME");
                    String columnType = columnResultSet.getString("TYPE_NAME");
                    int columnSize = columnResultSet.getInt("COLUMN_SIZE");
                    System.out.println("    Column Name: " + columnName + ", Type: " + columnType + ", Size: " + columnSize);
                }
                columnResultSet.close();
            }
            tableResultSet.close();
             */

//            Statement stmt = conn.createStatement();
//            int rows = stmt.executeUpdate("INSERT INTO role_table (role_name,table_status) VALUES('C++',True) ",Statement.RETURN_GENERATED_KEYS);
//            System.out.println("成功执行"+rows+"条语句");
//            ResultSet rs = stmt.getGeneratedKeys(); // 输出主键ID
//            if(rs.next()) {
//                System.out.println(rs.getString(1));
//            }