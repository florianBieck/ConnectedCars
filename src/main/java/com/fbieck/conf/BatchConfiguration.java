package com.fbieck.conf;

import com.fbieck.batch.GeolocationProcessor;
import com.fbieck.batch.GeolocationReader;
import com.fbieck.batch.GeolocationWriter;
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

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public GeolocationProcessor geolocationProcessor() {
        return new GeolocationProcessor();
    }

    @Bean
    public GeolocationReader geolocationReader() {
        return new GeolocationReader();
    }

    @Bean
    public GeolocationWriter geolocationWriter() {
        return new GeolocationWriter();
    }

    @Bean
    public Job importUserJob(Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step1")
                .<Car, Car>chunk(10)
                .reader(geolocationReader())
                .processor(geolocationProcessor())
                .writer(geolocationWriter())
                .build();
    }
}
