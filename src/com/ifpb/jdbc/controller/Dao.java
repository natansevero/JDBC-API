

package com.ifpb.jdbc.controller;

import com.ifpb.jdbc.model.Pessoa;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    
    public void consultManipulation() throws SQLException{
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String sql = "select * from pessoa";
        
        ResultSet rs = stmt.executeQuery(sql);
        
        rs.moveToInsertRow();
        rs.updateString(1, "345.232.232-34");
        rs.updateString(2, "Magrelin");
        rs.updateInt(3, 45);
        rs.insertRow();
    }
    
    public List consultPessoas(String nome) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("select * from pessoa where nome = ?");
        stmt.setString(1, nome);
        
        ResultSet rs = stmt.executeQuery();
        
        List<Pessoa> pessoas = new ArrayList<>();
        while(rs.next()){
            Pessoa pessoa = new Pessoa();
            pessoa.setCpf(rs.getString(1));
            pessoa.setNome(rs.getString(2));
            pessoa.setIdade(rs.getInt(3));
            
            pessoas.add(pessoa);
        }
        
        return pessoas;
    }
    
    public int somaIdades() throws SQLException{
        String sql = "{? = call somaIdades()}";
        CallableStatement stmt = conn.prepareCall(sql);
        
        stmt.registerOutParameter(1, java.sql.Types.INTEGER);
        stmt.execute();
        
        return stmt.getInt(1);
    }
    
    
    
    
}
