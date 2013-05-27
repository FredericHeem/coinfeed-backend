
package com.coinfeed.marketfeed.fetcher.statemachine;


public class FeedFetcherWaitingState
    extends FeedFetcherOperatingState
{

    private final static FeedFetcherWaitingState instance = new FeedFetcherWaitingState();

    /**
     * Protected Constructor
     * 
     */
    protected FeedFetcherWaitingState() {
        setName("Waiting");
        setStateParent(FeedFetcherOperatingState.getInstance());
    }

    /**
     * Get the State Instance
     * 
     */
    public static FeedFetcherFeedFetcherState getInstance() {
        return instance;
    }

    /**
     * onEntry
     * 
     */
    @Override
    public void onEntry(FeedFetcherContext context) {
        context.getObserver().onEntry(context.getName(), this.getName());
        com.coinfeed.marketfeed.fetcher.FeedFetcher feed = context.getFeedFetcher();
        context.timerStartTimerRetry((feed.getConfig().getPollingPeriod()));
    }

    /**
     * onExit
     * 
     */
    @Override
    public void onExit(FeedFetcherContext context) {
        context.getObserver().onExit(context.getName(), this.getName());
    }

    /**
     * Event id: evTimerRetry
     * 
     */
    public void evTimerRetry(FeedFetcherContext context) {
        com.coinfeed.marketfeed.fetcher.FeedFetcher feed = context.getFeedFetcher();
        // Transition from Waiting to Fetching triggered by evTimerRetry
        // The next state is within the context FeedFetcherContext
        context.setTransitionName("evTimerRetry");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, FeedFetcherFetchingState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, FeedFetcherFetchingState.getInstance());
        return ;
    }

}
