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
public class Curso {
     
    private String nomeCurso;
    private String codigoCurso;
    private String cargaHoraria;

    public String getNomeCurso()
    {
        return nomeCurso;
    }

    public String getCargaHoraria()
    {
        return cargaHoraria;
    }

    public String getCodigoCurso()
    {
        return codigoCurso;
    }

    public void setNomeCurso(String nomeCurso)
    {
        Matricula.listaCurso.add(nomeCurso); 
    }

    public void setCargaHoraria(String cargaHoraria)
    {
        this.cargaHoraria = cargaHoraria;
    }

    public void setCodigoCurso(String codigoCurso)
    {
        Matricula.listaCodigoCurso.add(codigoCurso);
    }
    
}
