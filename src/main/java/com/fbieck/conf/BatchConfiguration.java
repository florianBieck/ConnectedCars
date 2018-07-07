package com.fbieck.conf;

import com.fbieck.batch.CarReader;
import com.fbieck.batch.CarWriter;
import com.fbieck.batch.GeolocationProcessor;
import com.fbieck.entities.Car;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public GeolocationProcessor geolocationProcessor() {
        return new GeolocationProcessor();
    }

    @Bean
    public CarReader carReader() {
        return new CarReader();
    }

    @Bean
    public CarWriter carWriter() {
        return new CarWriter();
    }

    @Bean
    public Job importUserJob() {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .start(geolocation())
                .next(tankfuel())
                .build();
    }

    @Bean
    public Step geolocation() {
        return stepBuilderFactory.get("geolocation")
                .<Car, Car>chunk(10)
                .reader(carReader())
                .processor(geolocationProcessor())
                .writer(carWriter())
                .build();
    }

    @Bean
    public Step tankfuel() {
        return stepBuilderFactory.get("tankfuel")
                .<Car, Car>chunk(10)
                .reader(carReader())
                .processor(geolocationProcessor())
                .writer(carWriter())
                .build();
    }
}
