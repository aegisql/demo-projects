package com.aegisql.demo.demo_01;

import com.aegisql.conveyor.*;
import com.aegisql.conveyor.consumers.result.ResultConsumer;
import com.aegisql.conveyor.consumers.scrap.ScrapConsumer;
import com.aegisql.conveyor.loaders.PartLoader;
import org.junit.Test;

import java.time.Duration;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class Demo {

    public static void main(String[] args) {

        Conveyor<Integer, String, Integer> numbersAggregator = new AssemblingConveyor<>();

        numbersAggregator.setName("numbers_aggregator");
        numbersAggregator.setBuilderSupplier(SummaBuilder::new);
        numbersAggregator.setDefaultBuilderTimeout(Duration.ofSeconds(1));
        numbersAggregator.resultConsumer(new ResultConsumer<Integer, Integer>() {
            @Override
            public void accept(ProductBin<Integer, Integer> bin) {
                System.out.println("Summa for "+bin.key+" = "+bin.product);
            }
        }).set();
        numbersAggregator.scrapConsumer(new ScrapConsumer<Integer, Object>() {
            @Override
            public void accept(ScrapBin<Integer, Object> bin) {
                System.err.println("Error: "+bin.key+" "+bin.failureType+" "+bin.error);
            }
        }).set();
        numbersAggregator.setDefaultCartConsumer(Conveyor.getConsumerFor(numbersAggregator,SummaBuilder.class)
                .when("PART_A", SummaBuilder::partA)
                .when("PART_B", SummaBuilder::partB)
        );


        //SmartLabel<SummaBuilder> PART_A = SmartLabel.of("PART_A",SummaBuilder::partA);
        //SmartLabel<SummaBuilder> PART_B = SmartLabel.of("PART_B",SummaBuilder::partB);

        PartLoader<Integer, String> partA = numbersAggregator.part().label("PART_A");
        PartLoader<Integer, String> partB = numbersAggregator.part().label("PART_B");

        numbersAggregator.setReadinessEvaluator(Conveyor.getTesterFor(numbersAggregator).accepted("PART_A","PART_B"));


        partA.id(1000).value("1").place();
        partB.id(1000).value(1).place();

        partA.id(1001).value("1").place();
        partB.id(1001).value("1").place();

        partA.id(1002).value("1").place();
        //partB.id(1002).value(1).place();


        numbersAggregator.completeAndStop().join();

    }

    @Test
    public void testMain() {
        main(null);
    }

    @Test
    public void testSummaBuilder() {
        SummaBuilder summaBuilder = new SummaBuilder();
        summaBuilder.partA("1");
        summaBuilder.partB(1);
        Integer summa = summaBuilder.get();
        assertEquals("Expects 1+1=2",Integer.valueOf(2),summa);
    }

}
