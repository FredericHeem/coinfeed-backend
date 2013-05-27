
package com.coinfeed.marketfeed.fetcher.statemachine;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import com.stateforge.statemachine.context.AbstractContext;

public class FeedFetcherContext
    extends AbstractContext<FeedFetcherFeedFetcherState, FeedFetcherContext>
{

    private com.coinfeed.marketfeed.fetcher.FeedFetcher _feed;
    private ScheduledFuture myTimerRetryScheduledFuture;

    /**
     * Context constructor
     * 
     */
    public FeedFetcherContext(com.coinfeed.marketfeed.fetcher.FeedFetcher feed) {
        super();
        _feed = feed;
        setName("FeedFetcherContext");
        setInitialState(FeedFetcherIdleState.getInstance());
    }

    public com.coinfeed.marketfeed.fetcher.FeedFetcher getFeedFetcher() {
        return _feed;
    }

    /**
     * Enter the initial state
     * 
     */
    public void enterInitialState() {
        com.stateforge.statemachine.algorithm.StateOperation.walkTreeEntry(this, FeedFetcherFeedFetcherState.getInstance(), FeedFetcherIdleState.getInstance());
    }

    /**
     * Leave the current state
     * 
     */
    public void leaveCurrentState() {
        com.stateforge.statemachine.algorithm.StateOperation.walkTreeExit(this, this.getStateCurrent(), FeedFetcherFeedFetcherState.getInstance());
    }

    /**
     * Asynchronous event evStart
     * 
     */
    public void evStart() {
        final FeedFetcherContext me = this;
        getExecutorService().execute(new Runnable() {


            public void run() {
                try {
                    getStateCurrent().evStart(me);
                } catch (Exception exception) {
                    System.err.println(exception.getMessage());
                }
            }

        }
        );
    }

    /**
     * Asynchronous event evStop
     * 
     */
    public void evStop() {
        final FeedFetcherContext me = this;
        getExecutorService().execute(new Runnable() {


            public void run() {
                try {
                    getStateCurrent().evStop(me);
                } catch (Exception exception) {
                    System.err.println(exception.getMessage());
                }
            }

        }
        );
    }

    /**
     * Asynchronous event evError
     * 
     */
    public void evError() {
        final FeedFetcherContext me = this;
        getExecutorService().execute(new Runnable() {


            public void run() {
                try {
                    getStateCurrent().evError(me);
                } catch (Exception exception) {
                    System.err.println(exception.getMessage());
                }
            }

        }
        );
    }

    /**
     * Asynchronous event evFetched
     * 
     */
    public void evFetched() {
        final FeedFetcherContext me = this;
        getExecutorService().execute(new Runnable() {


            public void run() {
                try {
                    getStateCurrent().evFetched(me);
                } catch (Exception exception) {
                    System.err.println(exception.getMessage());
                }
            }

        }
        );
    }

    /**
     * Start the timer TimerRetry
     * 
     */
    public void timerStartTimerRetry(long duration) {
        getObserver().onTimerStart(this.getName(), "TimerRetry", duration);
        final FeedFetcherContext me = this;
        myTimerRetryScheduledFuture = getExecutorService().schedule(new Runnable() {


            public void run() {
                try {
                    getStateCurrent().evTimerRetry(me);
                } catch (Exception exception) {
                    System.err.println(exception.getMessage());
                }
            }

        }
        , duration, TimeUnit.MILLISECONDS);
    }

    /**
     * Stop the timer TimerRetry
     * 
     */
    public void timerStopTimerRetry() {
        getObserver().onTimerStop(this.getName(), "TimerRetry");
        if (myTimerRetryScheduledFuture!= null) {
            myTimerRetryScheduledFuture.cancel(false);
        }
    }

}
