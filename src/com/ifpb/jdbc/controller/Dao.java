

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
    
    /*
        Conograma de métodos:
            -Um com Statement, criando tabela
            -Um com Statemnt, adionando dados
            -Um com Stament, fazendo consulta, podendo manipular um ResultSet
            -Um com PreparedStatement, fazendo uma consulta pré-compilada
            -Um com CallableStatement, com procedimento armazenado
            -Um com ResultSetMetaData, de um consulta
            -Um com DataBaseMetaData, de uma conexão
            -JdbcRowSet
            -CachedRowSet
            -JoinRowSet
    
    */
    
    public boolean createTablePessoa() throws SQLException{
        Statement stmt = conn.createStatement();
        String sql = "create table pessoa(cpf varchar(14), nome varchar(50) not null, idade integer not null, primary key(cpf))";
        
        return stmt.executeUpdate(sql) > 0;
    }
    
    public boolean insertTablePessoa(String cpf, String nome, String idade) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "insert into pessoa values ('"+ cpf +"','"+ nome +"',"+idade+") ";
        
        return stmt.executeUpdate(sql) > 0;
    }
    
}
