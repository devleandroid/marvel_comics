package marvel.br.com.lebronx.marvelcomics.Interface;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

    Scheduler mainThread();

    Scheduler backgroundThread();
}
