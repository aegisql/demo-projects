package com.aegisql.demo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class ReportDemoSimple 
{
    public static void main( String[] args )
    {

        BalanceReportBuilderRu rb1 = new BalanceReportBuilderRu();
        System.out.println( rb1.get() );

        BalanceReportBuilderEn rb2 = new BalanceReportBuilderEn();
        System.out.println( rb2.get() );
        
    }
}
