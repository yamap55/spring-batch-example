package com.yamap55.example.spring

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
class BatchConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean(name = "step1")
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
                println "step 1"
                null
            }
        }).build()
    }

    @Bean(name = "step2")
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(new Tasklet() {
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
                println "step 2"
                null
            }
        }).build()
    }

    @Bean
    public Job job(Step step1, Step step2) throws Exception {
        return jobBuilderFactory.get("job1")
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .next(step2)
                .build();
    }
}
