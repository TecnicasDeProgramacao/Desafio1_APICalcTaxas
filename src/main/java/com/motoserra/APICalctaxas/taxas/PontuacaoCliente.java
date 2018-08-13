package com.motoserra.APICalctaxas.taxas;

public class PontuacaoCliente {
	
	private EstatisticaCliente estatisticaCliente;
	
    public PontuacaoCliente(EstatisticaCliente estatistica){
        //TO DO
    	estatisticaCliente = estatistica;       
    }

    public double pontuacaoSaldoMedio(int nroConta,int mes,int ano){
        //formula de pontos por saldo medio
    	double pontos = (1000*(estatisticaCliente.saldoMedioMes(nroConta, mes, ano)%1000)); 
    	return pontos;		               
    }

    public double pontuacaoValorMedioOperacoes(int nroConta,int mes,int ano){
        //Formula de pontos do valor medio de operacoes
    	if(estatisticaCliente.valorMedioOperacoes(nroConta, mes, ano) > 300)
    	{
    		double pontos = (100*(estatisticaCliente.valorMedioOperacoes(nroConta, mes, ano)% 100));
    		return pontos;
    	}
    	else
    		return 0;               
    }

    public double pontuacaoSaldoMedioNegativoMes(int nroConta,int mes,int ano){
        //Formula de pontos por saldo negativo (retorna negativo pois precisa ser subtraido da pontua��o total)
    	if(estatisticaCliente.saldoMedioNegativoMes(nroConta, mes, ano) > 500)
    	{
    		double pontos = ((100*(estatisticaCliente.saldoMedioNegativoMes(nroConta, mes, ano) % 100) /2.0));
    		return pontos;
    	} 
    	else
    		return 0;                
    }
    
    public double pontuacaoTotal(int nroConta,int mes,int ano)
    {
    	double total = pontuacaoSaldoMedio(nroConta, mes, ano) + pontuacaoValorMedioOperacoes(nroConta, mes, ano) + pontuacaoSaldoMedioNegativoMes(nroConta, mes, ano);
    	return total;
    }
}
 