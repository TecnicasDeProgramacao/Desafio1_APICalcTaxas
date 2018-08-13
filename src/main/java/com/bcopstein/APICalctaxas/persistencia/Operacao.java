package com.bcopstein.APICalctaxas.persistencia;

import java.util.Comparator;

public class Operacao implements Comparator<Operacao> {
	public final int CREDITO = 0;
	public final int DEBITO = 1;

	private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int minuto;
    private int segundo;
    private int numeroConta;
    private double valorOperacao;
    private int tipoOperacao;

	public Operacao(int dia, int mes, int ano, int hora, int minuto, int segundo, int numeroConta,
        			double valorOperacao, int tipoOperacao) {
		super();
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.hora = hora;
		this.minuto = minuto;
		this.segundo = segundo;
		this.numeroConta = numeroConta;
		this.valorOperacao = valorOperacao;
		this.tipoOperacao = tipoOperacao;
	}

	public int getDia() {
		return dia;
	}

	public int getMes() {
		return mes;
	}

	public int getAno() {
		return ano;
	}

	public int getHora() {
		return hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public int getSegundo() {
		return segundo;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public double getValorOperacao() {
		return valorOperacao;
	}

	public int getTipoOperacao() {
		return tipoOperacao;
	}
    
    public int compare(Operacao op1, Operacao op2)
    {
        if(op1.getAno() == op2.getAno())
        {
            if(op1.getMes() == op2.getMes())
            {
                if(op1.getDia() == op2.getDia())
                {
                    if(op1.getHora() == op2.getHora())
                    {
                        if(op1.getMinuto() == op2.getMinuto())
                        {
                            if(op1.getSegundo() == op2.getSegundo())
                            {
                                return 0;
                            }
                            else
                            {
                                return op1.getSegundo() - op2.getSegundo();
                            }
                        }
                        else
                        {
                            return op1.getMinuto() - op2.getMinuto();
                        }
                    }
                    else
                    {
                        return op1.getHora() - op2.getHora();
                    }
                }
                else
                {
                    return op1.getDia() - op2.getDia();
                }
            }
            else
            {
                return op1.getMes() - op2.getMes();
            }
        }
        else
        {
            return op1.getAno() - op2.getAno();
        }
    }
    
    public boolean equals(Operacao op)
    {
        return false;
    }

	@Override
	public String toString() {
		String tipo = "<C>";
		if (tipoOperacao == 1) {
			tipo = "<D>";
		}
		String line = dia+"/"+mes+"/"+ano+" "+
	                  hora+":"+minuto+":"+segundo+" "+
				      numeroConta+" "+
				      tipo+" "+
	                  valorOperacao;
		return(line);
	}
}
