
package com.coinfeed.marketfeed.fetcher.statemachine;

import com.stateforge.statemachine.state.AbstractState;

public class FeedFetcherFeedFetcherState
    extends AbstractState<FeedFetcherContext, FeedFetcherFeedFetcherState>
{

    private final static FeedFetcherFeedFetcherState instance = new FeedFetcherFeedFetcherState();

    /**
     * Protected Constructor
     * 
     */
    protected FeedFetcherFeedFetcherState() {
        setName("FeedFetcher");
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
     * Event id: evStart
     * 
     */
    public void evStart(FeedFetcherContext context) {
    }

    /**
     * Event id: evStop
     * 
     */
    public void evStop(FeedFetcherContext context) {
    }

    /**
     * Event id: evError
     * 
     */
    public void evError(FeedFetcherContext context) {
    }

    /**
     * Event id: evFetched
     * 
     */
    public void evFetched(FeedFetcherContext context) {
    }

    /**
     * Event id: evTimerRetry
     * 
     */
    public void evTimerRetry(FeedFetcherContext context) {
    }

}
