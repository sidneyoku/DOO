package br.ifpr.paranavai.crudbasicoatividadeii.validacao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sidney
 */
public class Validacao {
    
    public static List<String> ValidaCamposCidade(String nome, String estado) {
        List<String> msg = new ArrayList<>();
        
        if(nome.isEmpty()) {
            msg.add("Preencher a cidade.");
        } 
        
        if(estado.isEmpty()) {
            msg.add("Escolha o estado.");
        }
        
        return msg;
    }
    
    public static List<String> ValidaCamposEstado(String nome, String sigla) {
        List<String> msg = new ArrayList<>();
        
        if(nome.isEmpty()) {
            msg.add("Preencher o estado.");
        } 
        
        if(sigla.isEmpty()) {
            msg.add("Preencha a sigla.");
        }
        
        return msg;
    }
    
    public static List<String> ValidaCamposPessoa(String nome, int idEstado, int idCidade) {
        List<String> msg = new ArrayList<>();
        
        if(nome.isEmpty()) {
            msg.add("Preencher o nome.");
        } 
        
        if(idEstado < 0) {
            msg.add("Escolha o estado.");
        }
        
        if(idCidade < 0) {
            msg.add("Escolha a cidade.");
        }
        
        return msg;
    }
    
}
