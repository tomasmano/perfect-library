/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.business;

import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Užívateľ
 */
@Singleton
@Startup
public class PenaltyTimerService implements PenaltyTimerServiceLocal {

    public static Logger logger = LoggerFactory.getLogger(PenaltyTimerService.class);
    @Resource
    private TimerService timerService;
    @EJB
    LibraryIssueServiceLocal issueService;

    @PostConstruct
    public void launch() {
        logger.info("{} initializing method called. Service is starting ...", this.getClass().getSimpleName());
        ScheduleExpression dailtyPeriodSpecification=new ScheduleExpression();
        dailtyPeriodSpecification.dayOfMonth("*").hour("2").minute("30");
        scheduleTask(dailtyPeriodSpecification, "Expired issues routine check");
    }

    // alternative @Schedule (hour = "2", minute = "30")
    public void scheduleTask(ScheduleExpression calendarSchedule, String taskName) {
        logger.info("Service is setting up his schedule task '{}' ...", taskName);
        // TimerConfig can be passed to the timer service, during timer creation,  
        // to specify whether the timer is persistent and also pass any additional  
        // Serializable info.   
        TimerConfig timerConfig = new TimerConfig();

        timerConfig.setPersistent(false);

        timerConfig.setInfo(taskName);

        // Now, use the injected timerservice to create a timer instance,  
        // for the passed schedule and the timer config  
        Timer timer = timerService.createCalendarTimer(calendarSchedule, timerConfig);
        logger.info("Service finished configuration of '{}' sucesfully. Remaining time to launching the task is {} minutes.", timerConfig.getInfo(), TimeUnit.MILLISECONDS.toMinutes(timer.getTimeRemaining()));
    }

    @Timeout
    private void onTimeout(Timer timer) {
        logger.info("Scheduled task '{}' of '{}' is launching now..", timer.getInfo(), this.getClass().getSimpleName());
        issueService.handleExpiredIssuesRoutine();
    }
}
