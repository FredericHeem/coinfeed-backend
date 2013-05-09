package com.coinfeed.marketfeed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coinfeed.marketfeed.statemachine.FeedFetcherContext;
import com.stateforge.statemachine.listener.ObserverConsole;

public class FeedFetcher  implements FeedListener {
	private static final Logger log = LoggerFactory.getLogger(FeedFetcher.class);
	private FeedFetcherContext context;
	private FeedListener feedListener;
	private int errorCount;
	private int fetchCount;
	private TickerModel tickerModelLast;
	
	public FeedFetcher(FeedBase feed, FeedListener feedListener){
		this.feedListener = feedListener;
		this.context = new FeedFetcherContext(feed);
		this.context.setObserver(ObserverConsole.getInstance());
	}
	
	public void start(){
		this.context.evStart();
	}

	@Override
	public void onFeedFetch(TickerModel tickerModel) {
		this.fetchCount += 1;
		log.debug("onFeedFetch:      " + tickerModel.toString() + ", #fetch " + getFetchCount());
		this.context.evFetched();
		
		if(this.tickerModelLast != null){
			log.debug("onFeedFetch: last " + tickerModelLast.toString());
		}
		if((this.tickerModelLast != null) && 
				(this.tickerModelLast.getBid().compareTo(tickerModel.getBid()) == 0) &&
				(this.tickerModelLast.getAsk().compareTo(tickerModel.getAsk()) == 0)){
				log.debug("onFeedFetch same ticker");
		} else {
			this.feedListener.onFeedFetch(tickerModel);
		}
		
		this.tickerModelLast = tickerModel;
	}

	@Override
	public void onError(String error) {
		this.errorCount += 1;
		log.warn("#error " + getFetchCount());
		this.context.evError();
		this.feedListener.onError(error);
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public void setFetchCount(int fetchCount) {
		this.fetchCount = fetchCount;
	}

	public int getFetchCount() {
		return fetchCount;
	}
}
