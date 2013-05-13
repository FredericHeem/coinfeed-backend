package com.coinfeed.marketfeed.fetcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coinfeed.marketfeed.model.TickerModel;
import com.coinfeed.marketfeed.fetcher.statemachine.FeedFetcherContext;
import com.stateforge.statemachine.listener.ObserverConsole;

public class FeedPoller  implements FeedFetcherListener {
	private static final Logger log = LoggerFactory.getLogger(FeedPoller.class);
	private FeedFetcherContext context;
	private FeedPollerListener feedPollerListener;
	private int errorCount;
	private int fetchCount;
	private TickerModel tickerModelLast;
	
	public FeedPoller(FeedFetcher feed, FeedPollerListener feedPollerListener){
		this.feedPollerListener = feedPollerListener;
		this.context = new FeedFetcherContext(feed);
		this.context.setObserver(ObserverConsole.getInstance());
		this.context.enterInitialState();
	}
	
	public void start(){
		this.context.evStart();
	}

	public void stop() {
		this.context.evStop();
	}
	
	@Override
	public void onFeedFetch(TickerModel tickerModel) {
		this.fetchCount += 1;
		log.debug("onFeedFetch:      " + tickerModel.toString() + ", #fetch " + getFetchCount());
		this.context.evFetched();
		
		if(this.tickerModelLast != null){
			log.debug("onFeedFetch: last " + tickerModelLast.toString());
		}
	
		boolean hasChanged = true;
		if(this.tickerModelLast != null && 
				this.tickerModelLast.getBid().compareTo(tickerModel.getBid()) == 0 &&
				this.tickerModelLast.getAsk().compareTo(tickerModel.getAsk()) == 0){
				log.debug("onFeedFetch same ticker");
				hasChanged = false;
		}
		
		this.feedPollerListener.onTicker(tickerModel, hasChanged);
		this.tickerModelLast = tickerModel;
	}

	@Override
	public void onError(String error) {
		this.errorCount += 1;
		log.warn("#error " + getErrorCount());
		this.context.evError();
		this.feedPollerListener.onError(error);
	}

	public int getErrorCount() {
		return errorCount;
	}

	public int getFetchCount() {
		return fetchCount;
	}


}
