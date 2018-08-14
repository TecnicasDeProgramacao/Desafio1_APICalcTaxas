package com.motoserra.APICalctaxas.taxas;

import com.bcopstein.APICalctaxas.persistencia.*;
import java.util.List;
public class Main
{
    public static void main(String[] args)
    {
        Persistencia p = new Persistencia();
        EstatisticaCliente est = new EstatisticaCliente(p);
        List<Double> saldosDias = est.saldosMes(130, 2, 2018);
        for(Double s: saldosDias)
        {
            System.out.println(s);
        }
    }
}
