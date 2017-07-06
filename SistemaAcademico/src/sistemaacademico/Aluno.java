/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademico;

/**
 *
 * @author Joana
 */
public class Aluno {
     
    private String codigo;
    private String nome;
    private String cpf; 



    public String getCodigo() 
    {
        return codigo;
    }

    public String getNome() 
    {
        return nome;
    }

    public String getCpf() 
    {
        
        return cpf;
    }

    public void setCpf(String cpf) 
    {
        this.cpf = cpf;
    }

    public void setNome(String nome) 
    {
       Matricula.listaNome.add(nome);
    }

    public void setCodigo(String codigo) 
    {
        Matricula.listaCodigoAluno.add(codigo);
    }
    
    
}
