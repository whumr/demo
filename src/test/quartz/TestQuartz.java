package test.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class TestQuartz {

	public static void main(String[] args) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sd = sf.getScheduler();
		
		JobDetail job = JobBuilder.newJob(TestJob.class).withIdentity("test_job", "xx").build();

		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("xx", "sss")
				.withSchedule(CronScheduleBuilder.cronSchedule("*/3 * * * * ? *"))
				.startNow().build();
		
		sd.scheduleJob(job , trigger);
		System.out.println(System.currentTimeMillis());
		sd.start();
	}

}

