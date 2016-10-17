

package com.ifpb.jdbc.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author natan
 */
public class Dao {
    private String url;
    private String user;
    private String password;
    private Connection conn;
    
    public Dao() throws ClassNotFoundException, SQLException{
        url = "jdbc:postgresql://localhost:5432/CadPessoa";
        user = "postgres";
        password = "12345";
        conn = ConFactory.getConnection(url, user, password);
    }
    
    public boolean createTablePessoa() throws SQLException{
        Statement stmt = conn.createStatement();
        String sql = "create table pessoa(cpf varchar(14), nome varchar(50) not null, idade integer not null, primary key(cpf))";
        
        if(stmt.executeQuery(sql) == null) return false;
        
        return true;
    }
    
    
}
