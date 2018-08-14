package com.motoserra.APICalctaxas.taxas;

import com.bcopstein.APICalctaxas.persistencia.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collections;

public class EstatisticaCliente {
    private Persistencia per;
    private List<Operacao> lstOps;
    private Map<Integer, Conta> lstContas;
    
    public EstatisticaCliente(Persistencia persistencia){
        per = persistencia;
        lstOps = per.loadOperacoes();
        lstContas = per.loadContas();
    }
    
    public double saldoMedioMes(int nroConta,int mes,int ano){
        
        List<Double> listaSaldosDoMes = saldosMes(nroConta, mes, ano);
        double saldoDaContaNoFimDoMes = 0;
        
        for(double saldo: listaSaldosDoMes){
            saldoDaContaNoFimDoMes += saldo;
        }
        int nroDiasDoMes = listaSaldosDoMes.size();
        return saldoDaContaNoFimDoMes/nroDiasDoMes;
    }

    public double saldoMedioNegativoMes(int nroConta,int mes,int ano)
    {
        List<Double> listaSaldosDoMes = saldosMes(nroConta, mes, ano);
        double saldoDaContaNoFimDoMes = 0;
        int nroDiasComSaldoNegativo = 0;
        
        for(double saldo: listaSaldosDoMes)
        {
            if(saldo < 0)
            {
                saldoDaContaNoFimDoMes += saldo;
                nroDiasComSaldoNegativo++;
            }
        }
        return saldoDaContaNoFimDoMes/nroDiasComSaldoNegativo;
    }
    
    public double valorMedioOperacoes(int nroConta,int mes,int ano)
    {
        double valorTotalDasOperacoes = 0; 
        
        List<Operacao> lstOpsDaConta = new ArrayList<>();
        
        for(Operacao o: lstOps)
        { 
            if (o.getNumeroConta() == nroConta)
            {
                lstOpsDaConta.add(o);
            }
        }
        int nroDeOperacoes = lstOpsDaConta.size();
       
        for(Operacao o: lstOpsDaConta){
            if(o.getMes() == mes && o.getAno() == ano){
                valorTotalDasOperacoes += Math.abs(o.getValorOperacao());
            }
        }
        return valorTotalDasOperacoes/nroDeOperacoes;
        }
        

    public List<Double> saldosMes(int nroConta,int mes,int ano)
    {
        List<Operacao> lstOpsDaConta = new ArrayList<>();
        
        Conta c1 = lstContas.get(nroConta);
        double saldo = c1.getSaldo();
        
        for(Operacao o: lstOps)
        { 
            if (o.getNumeroConta() == nroConta)
            {
                lstOpsDaConta.add(o);
            }
        }
        
        Collections.sort(lstOpsDaConta, new Operacao(0,0,0,0,0,0,0,0,0));
        Collections.reverse(lstOpsDaConta);
        
        List<Operacao> lstOpsMes = new ArrayList<Operacao>();
        for(Operacao o: lstOpsDaConta)
        {
           if(o.getAno() >= ano && o.getMes() >= mes)
           {
              if(o.getTipoOperacao() == 0) //CRÉDITO
              {
                  saldo -= o.getValorOperacao();
              }
              else
              {
                  saldo += o.getValorOperacao();
              }
              if(o.getAno() == ano && o.getMes() == mes)
              {
                  lstOpsMes.add(o);
              }
           }
        }
        
        Collections.reverse(lstOpsMes);
    
        List<Double> listaComSaldosDiarios = new ArrayList<>(); 
        int dia = 1;
        int diasMes;
        if(mes == 2)
        {
        	diasMes = 28;
        }
        else if(mes < 8)
        {
        	if(mes % 2 == 0)
        	{
        		diasMes = 30;
        	}
        	else
        	{
        		diasMes = 31;
        	}
        }
        else
        {
        	if(mes % 2 == 0)
        	{
        		diasMes = 31;
        	}
        	else
        	{
        		diasMes = 31;
        	}
        }        
        if(lstOpsMes.isEmpty())
        {
        	for(int i = 0; i < diasMes; i++)
        	{
        		listaComSaldosDiarios.add(saldo);
        	}
        }
        else
        {
	        for (Operacao o: lstOpsMes)
	        {
	            while(o.getDia() != dia)
	            {
	                listaComSaldosDiarios.add(saldo);
	                dia++;
	            }
	            if (o.getTipoOperacao() == 0) //CRÉDITO
	            {
	                saldo += o.getValorOperacao();
	            }
	            else
	            {                   
	                saldo -= o.getValorOperacao();
	            }
	            //listaComSaldosDiarios.add(saldo);
	        }
	        for(int i = dia; i <= diasMes; i++)
	        {
	        	listaComSaldosDiarios.add(saldo);
	        }
        }
        return listaComSaldosDiarios;
    }
}