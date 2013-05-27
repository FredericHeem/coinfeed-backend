
package com.coinfeed.marketfeed.fetcher.statemachine;


public class FeedFetcherEndState
    extends FeedFetcherFeedFetcherState
{

    private final static FeedFetcherEndState instance = new FeedFetcherEndState();

    /**
     * Protected Constructor
     * 
     */
    protected FeedFetcherEndState() {
        setName("End");
        setStateParent(FeedFetcherFeedFetcherState.getInstance());
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
