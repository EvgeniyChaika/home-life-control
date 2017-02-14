package com.control.life.home.services.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;

/**
 * Created on 06.02.17.
 */

public class StepExecutionStatsListener extends StepExecutionListenerSupport {
    private Logger logger = LoggerFactory.getLogger(StepExecutionStatsListener.class);

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info("Wrote: " + stepExecution.getWriteCount()
                + " items in step: " + stepExecution.getStepName()
                + ", updated " +stepExecution.getLastUpdated());
        return null;
    }
}
