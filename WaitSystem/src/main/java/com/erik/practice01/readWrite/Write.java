package com.erik.practice01.readWrite;

import java.sql.*;

public class Write {
    public static void add_user(String name) throws Exception {

        //加载驱动
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Class.forName("com.mysql.cj.jdbc.Driver");
        //用户信息和url
        String url = "jdbc:mysql://localhost:3306/demo?useSSL=false&useUnicode=true&characterEncoding=utf8";
        //mysql--3306
        // 协议://主机地址:端口号/数据库名?参数1&参数2...
        //oracle--1521
        //jdbc:oracle:thin:@localhost:1521:sid
        String username="root";
        String password="hdc123456";
        //链接成功 数据库对象
        Connection connection = DriverManager.getConnection(url,username,password);

        String sql = "INSERT INTO hospital(`name`) VALUES(?)";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,name);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public static void clear() throws Exception {
        //加载驱动
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Class.forName("com.mysql.cj.jdbc.Driver");
        //用户信息和url
        String url = "jdbc:mysql://localhost:3306/demo?useSSL=false&useUnicode=true&characterEncoding=utf8";
        //mysql--3306
        // 协议://主机地址:端口号/数据库名?参数1&参数2...
        //oracle--1521
        //jdbc:oracle:thin:@localhost:1521:sid
        String username="root";
        String password="hdc123456";
        //链接成功 数据库对象
        Connection connection = DriverManager.getConnection(url,username,password);

        String sql = "TRUNCATE `hospital`";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
}
