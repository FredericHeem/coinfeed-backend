
package com.coinfeed.marketfeed.fetcher.statemachine;


public class FeedFetcherOperatingState
    extends FeedFetcherFeedFetcherState
{

    private final static FeedFetcherOperatingState instance = new FeedFetcherOperatingState();

    /**
     * Protected Constructor
     * 
     */
    protected FeedFetcherOperatingState() {
        setName("Operating");
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
        com.coinfeed.marketfeed.fetcher.FeedFetcher feed = context.getFeedFetcher();
        feed.stop();
    }

    /**
     * Event id: evStop
     * 
     */
    public void evStop(FeedFetcherContext context) {
        com.coinfeed.marketfeed.fetcher.FeedFetcher feed = context.getFeedFetcher();
        // Transition from Operating to End triggered by evStop
        // The next state is within the context FeedFetcherContext
        context.setTransitionName("evStop");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, FeedFetcherEndState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, FeedFetcherEndState.getInstance());
        context.onEnd();
        return ;
    }

}
