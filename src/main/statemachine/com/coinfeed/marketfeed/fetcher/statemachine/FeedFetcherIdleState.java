
package com.coinfeed.marketfeed.fetcher.statemachine;


public class FeedFetcherIdleState
    extends FeedFetcherFeedFetcherState
{

    private final static FeedFetcherIdleState instance = new FeedFetcherIdleState();

    /**
     * Protected Constructor
     * 
     */
    protected FeedFetcherIdleState() {
        setName("Idle");
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
        com.coinfeed.marketfeed.fetcher.FeedFetcher feed = context.getFeedFetcher();
        // Transition from Idle to Operating triggered by evStart
        // The next state is within the context FeedFetcherContext
        context.setTransitionName("evStart");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, FeedFetcherFetchingState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, FeedFetcherFetchingState.getInstance());
        return ;
    }

}
