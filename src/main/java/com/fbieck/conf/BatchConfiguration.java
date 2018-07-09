package com.fbieck.conf;

import com.fbieck.batch.*;
import com.fbieck.entities.Car;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private GeolocationProcessor geolocationProcessor;

    @Autowired
    private SignalstrengthProcessor signalstrengthProcessor;

    @Autowired
    private TankfuelProcessor tankfuelProcessor;

    @Autowired
    private CarReader carReader;

    @Autowired
    private CarWriter carWriter;

    @Scheduled(fixedRate = 100)
    private void launch_signalstrength() throws Exception {
        jobLauncher.run(job_signalstrength(), new JobParametersBuilder()
                .addLong("date", System.currentTimeMillis())
                .toJobParameters());
    }

    @Scheduled(fixedRate = 100)
    private void launch_geolocation() throws Exception {

        jobLauncher.run(job_geolocation(), new JobParametersBuilder()
                .addLong("date", System.currentTimeMillis())
                .toJobParameters());
    }

    @Scheduled(fixedRate = 100)
    private void launch_tankfuel() throws Exception {
        jobLauncher.run(job_tankfuel(), new JobParametersBuilder()
                .addLong("date", System.currentTimeMillis())
                .toJobParameters());
    }

    @Bean
    public Job job_signalstrength() {
        Step step_signalstrength = stepBuilderFactory.get("signalstrength")
                .<Car, Car>chunk(10)
                .reader(carReader)
                .processor(signalstrengthProcessor)
                .writer(carWriter)
                .allowStartIfComplete(true)
                .build();
        return jobBuilderFactory.get("job_signalstrength")
                .incrementer(new RunIdIncrementer())
                .start(step_signalstrength)
                .build();
    }

    @Bean
    public Job job_geolocation() {
        Step step_geolocation = stepBuilderFactory.get("geolocation")
                .<Car, Car>chunk(10)
                .reader(carReader)
                .processor(geolocationProcessor)
                .writer(carWriter)
                .allowStartIfComplete(true)
                .build();
        return jobBuilderFactory.get("job_geolocation")
                .incrementer(new RunIdIncrementer())
                .start(step_geolocation)
                .build();
    }

    @Bean
    public Job job_tankfuel() {
        Step step_tankfuel = stepBuilderFactory.get("tankfuel")
                .<Car, Car>chunk(10)
                .reader(carReader)
                .processor(tankfuelProcessor)
                .writer(carWriter)
                .allowStartIfComplete(true)
                .build();
        return jobBuilderFactory.get("job_tankfuel")
                .incrementer(new RunIdIncrementer())
                .start(step_tankfuel)
                .build();
    }
}
