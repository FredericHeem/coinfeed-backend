
package com.coinfeed.marketfeed.fetcher.statemachine;


public class FeedFetcherFetchingState
    extends FeedFetcherOperatingState
{

    private final static FeedFetcherFetchingState instance = new FeedFetcherFetchingState();

    /**
     * Protected Constructor
     * 
     */
    protected FeedFetcherFetchingState() {
        setName("Fetching");
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
        feed.fetch();
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
     * Event id: evFetched
     * 
     */
    public void evFetched(FeedFetcherContext context) {
        com.coinfeed.marketfeed.fetcher.FeedFetcher feed = context.getFeedFetcher();
        // Transition from Fetching to Waiting triggered by evFetched
        // The next state is within the context FeedFetcherContext
        context.setTransitionName("evFetched");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, FeedFetcherWaitingState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, FeedFetcherWaitingState.getInstance());
        return ;
    }

    /**
     * Event id: evError
     * 
     */
    public void evError(FeedFetcherContext context) {
        com.coinfeed.marketfeed.fetcher.FeedFetcher feed = context.getFeedFetcher();
        // Transition from Fetching to Waiting triggered by evError
        // The next state is within the context FeedFetcherContext
        context.setTransitionName("evError");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, FeedFetcherWaitingState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, FeedFetcherWaitingState.getInstance());
        return ;
    }

}
