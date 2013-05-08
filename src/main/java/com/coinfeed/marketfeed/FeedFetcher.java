package com.coinfeed.marketfeed;

import com.stateforge.statemachine.listener.ObserverConsole;

public class FeedFetcher  implements FeedListener {
	private FeedBase feed;
	private FeedFetcherContext context;
	private FeedListener feedListener;
	
	public FeedFetcher(FeedListener feedListener){
		this.feedListener = feedListener;
		this.feed = new BitstampFeed(this);
		this.context = new FeedFetcherContext(feed);
		this.context.setObserver(ObserverConsole.getInstance());
	}
	
	public void start(){
		this.context.evStart();
	}

	@Override
	public void onFeedFetch(TickerModel tickerModel) {
		this.context.evFetched();
		this.feedListener.onFeedFetch(tickerModel);
	}

	@Override
	public void onError(String error) {
		this.context.evError();
		this.feedListener.onError(error);
	}
}
