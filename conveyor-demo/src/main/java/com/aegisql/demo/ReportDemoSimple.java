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
        rb1.setAddress("ый");
        rb1.setName("Илья");
        rb1.setPatronymic("Сергеевич");
        rb1.setAccount("109372955520");
        rb1.setCurrency("РУБ");
        rb1.setReportTimestamp(new Date());
        rb1.setSumm(BigDecimal.valueOf(10.34));
        System.out.println( rb1.get() );

        BalanceReportBuilderEn rb2 = new BalanceReportBuilderEn();
        rb2.setAddress("Mrs.");
        rb2.setName("Jane");
        rb2.setPatronymic("Doe");
        rb2.setAccount("83018472734724");
        rb2.setCurrency("USD");
        rb2.setReportTimestamp(new Date());
        rb2.setSumm(BigDecimal.valueOf(1340.00));
        System.out.println( rb2.get() );

        
    }
}
