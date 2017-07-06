/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Joana
 */
public class Persistencia {
    Scanner ler = new Scanner(System.in);
    Aluno aluno = new Aluno();
    private static Connection conn = null; 
    ResultSet rs = null;
    PreparedStatement ps = null;
    
public Persistencia()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/matricula", "root", "");
            System.out.println("Conexao Aberta!!...");
        }
        catch (ClassNotFoundException ex)
        {
         System.err.println("Nao foi possivel conectar ao banco de dados \n" + ex);
        }
        catch(SQLException e){
            System.out.println("Nao foi possivel encontrar o driver\n" +e);
        }
    }
    
    public static Connection conexao()
    {
        if (conn == null)
             new Persistencia();
        
        return conn;
    }
      
    private void insereDadosNoBanco(){
        try
        {
            ps = Persistencia.conexao().prepareStatement("Insert Into aluno (nome,cpf,codigo) values (?,?,?)");    
            ps.setString(1,"joana");
            ps.setString(2,"08706599605");
            ps.setString(3, "152612");
            ps.executeUpdate();
            
            ps = Persistencia.conexao().prepareStatement("Insert Into aluno (nome,cpf,codigo) values (?,?,?)");    
            ps.setString(1,"maria");
            ps.setString(2,"08706599605");
            ps.setString(3, "152614");
            ps.executeUpdate();
            
            ps = Persistencia.conexao().prepareStatement("Insert Into curso(nomeCurso,cargaHoraria,codigoCurso) values (?,?,?)");    
            ps.setString(1,"zootecnia");
            ps.setString(2,"4000");
            ps.setString(3, "dcc-2015");
            ps.executeUpdate();
            
            ps = Persistencia.conexao().prepareStatement("Insert Into matricula(dataMatricula,numeroMatricula) values (?,?)");    
            ps.setString(1,"2014-janeiro");
            ps.setString(2,"40600");
            ps.executeUpdate();
            System.out.println("\n--------------------------------------\n");
            System.out.println("\nOs dados foram inseridos no banco.....\n");
        }
        catch(SQLException e)
        {
            System.err.println("Nao foi possivel executar o comando sql " + e);
        }
    }
private void alteraDadosDoBanco()
    {
        try
        {  
            ps = Persistencia.conexao().prepareStatement("update aluno set nome=?,codigo=?,cpf=?");
            ps.setString(1,"teste");
            ps.setString(2,"null");
            ps.setString(3,"teste");
            System.out.println("\n-------------------------------------\n");
            System.out.println("\nAlterado com sucesso\n");
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println("Não foi possível executar o comando SQL");

        }
    }
    
    private void carregaDadosDoBanco_E_Altera() {
        try{
        
            ps =  Persistencia.conexao().prepareStatement("Select * from aluno" ); 
            rs = ps.executeQuery();
            
            DefaultTableModel dtm = new DefaultTableModel(new String[]{"nome","codigo","cpf"}, 0);
            System.out.println("Dados de alunos:\n");
            
            while(rs.next())
            {
                String[] dados = {rs.getString("nome"), rs.getString("codigo"),rs.getString("cpf")};          
                dtm.addRow(dados);
                System.out.println(Arrays.toString(dados));
            }
            
            System.out.println("\nDeseja realmente alterar?\n <s/n>\n");
                    String opcao = ler.next();     
                    if("s".equals(opcao)){
                         System.out.println("\nO registro sera alterado...\n");  
                    alteraDadosDoBanco();
                     System.out.println("\n-------------------------------\n");
                         System.out.println("\nalterado com sucesso\n");
                    }
        }catch(SQLException e)
        {
            System.err.println("Não foi possível executar o comando SQL");
            
        }
       
    }

 
    private void excluiDadosDoBanco()
    {
        try
        {  
            ps = Persistencia.conexao().prepareStatement("Delete from matricula where numeroMatricula = ?");
            ps.setString(1,"40600");
            System.out.println("\n-------------------------------------\n");
            System.out.println("\nexcluído com sucesso\n");
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println("Não foi possível executar o comando SQL");

        }
    }
    
    private void carregaDadosDoBanco() {
        try{
        
            ps =  Persistencia.conexao().prepareStatement("Select * from matricula" ); 
            rs = ps.executeQuery();
            
            DefaultTableModel dtm = new DefaultTableModel(new String[]{"dataMatricula","numeroMatricula"}, 0);
            System.out.println("Dados de matriculas:\n");
            
            while(rs.next())
            {
                String[] dados = {rs.getString("dataMatricula"), rs.getString("numeroMatricula")};          
                dtm.addRow(dados);
                System.out.println(Arrays.toString(dados));
            }
            
            System.out.println("\nDeseja excluir?\n <s/n>\n");
                    String opcao = ler.next();     
                    if("s".equals(opcao)){
                         System.out.println("\nO registro sera excluido...\n");  
                    excluiDadosDoBanco();
                     System.out.println("\n-------------------------------\n");
                         System.out.println("\nexcluído com sucesso\n");
                    }
        }catch(SQLException e)
        {
            System.err.println("Não foi possível executar o comando SQL");
            
        }
       
    }
public void dataBase(){
    Matricula matricula = new Matricula();
    Matricula paginaDeCadastro = new Matricula();
    try{
    int opcao = 10;
    while(opcao!=0){
         System.out.println("\nEscolha sua opção\n");
         System.out.println
            (
                  "1-Inserir dados no banco \n" + 
                  "2-Excluir Dados Do Banco\n" + 
                  "3-Carregar Dados Do Banco\n" +   
                  "4-Cadastrar alunos gravando em arquivo\n" + 
                  "5-Cadastrar curso gravando em arquivo\n" + 
                  "6-Matricular aluno em um curso gravando em arquivo\n" +
                  "7-Imprime natricula\n"+
                  "8-Altera dados no banco\n"+
                  "9-Carrega Dados do banco para alterá-los\n"+
                  "0-sair\n"
            );
            opcao = ler.nextInt();
            switch(opcao){
                case 1:insereDadosNoBanco();
                     break;
                case 2:excluiDadosDoBanco();
                     break;
                case 3:carregaDadosDoBanco();
                     break;
                case 4:paginaDeCadastro.cadastrarAlunos();
                     break;
                case 5:paginaDeCadastro.cadastrarCursos();
                     break; 
                case 6:paginaDeCadastro.matricularAlunosEmCursos();
                     break;
                case 7:matricula.imprime_Em_Tela_AlunosECursos();
                     break;
                case 8:alteraDadosDoBanco();
                     break;
                case 9:carregaDadosDoBanco_E_Altera();
                     break;
                case 0:
            }
    }
}catch(Exception erro)
        {
            System.err.println("\nFinalizado devido a erro\n" + erro);
        }
    
     
}
    
}
