package com.motoserra.APICalctaxas.taxas;

import java.util.List;
import java.lang.Math;

public class CustosConta
{
    private EstatisticaCliente est;
    private PontuacaoCliente pont;
    
    public CustosConta(EstatisticaCliente estatistica, PontuacaoCliente pontuacao)
    {
        this.est = estatistica;
        this.pont = pontuacao;
    }
    
    public double custoTotalMes(int nroConta, int mes, int ano)
    {
        return this.jurosNoMes(nroConta, mes, ano) + this.taxaNoMes(nroConta, mes, ano);
    }
    
    public double jurosNoMes(int nroConta, int mes, int ano)
    {
        double juros = 0;
        List<Double> saldosDias = est.saldosMes(nroConta, mes, ano);
        for(Double saldo: saldosDias)
        {
            if(saldo < 0)
            {
                juros += (0.003 * Math.abs(saldo));
            }
        }
        return juros;
    }
    
    public  double taxaNoMes(int nroConta, int mes, int ano)
    {
        double taxa = 100.0;
        if(pont.pontuacaoTotal(nroConta, mes, ano) > 20000)
        {
            taxa = 0;
        }
        else if(pont.pontuacaoTotal(nroConta, mes, ano) > 10000)
        {
            taxa = taxa/2;
        }
        else if(pont.pontuacaoTotal(nroConta, mes, ano) > 5000)
        {
            taxa -= (0.3 * taxa);
        }
        return taxa;
    }
}
