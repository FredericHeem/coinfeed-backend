package com.coinfeed.marketfeed.fetcher;

import java.io.IOException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coinfeed.marketfeed.model.TickerModel;
import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

public abstract class FeedFetcher {
	private static final Logger log = LoggerFactory.getLogger(FeedFetcher.class);
	protected AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
	protected FeedFetcherListener feedListener;
	protected Future<String> _future;
	protected String query;
	private IFeedDecoder decoder;
	private FeedPollerConfig config;
	
	public FeedFetcher(FeedPollerConfig config){
		if(config == null){
			this.config = new FeedPollerConfig();
		} else {
			this.config = config;
		}
	}
	
	public void fetch() {
		fetch(query);		
	}
	
	protected void fetch(String query) {
		log.debug("fetch " + query);
		try {
			_future = getAsyncHttpClient().prepareGet(query).execute(
					new AsyncCompletionHandler<String>() {
						@Override
						public String onCompleted(Response response)
								throws Exception {
							log.debug("onCompleted: "
									+ response.getResponseBody());
							onFeedReceived(response.getResponseBody());
							return null;
						}

						@Override
						public void onThrowable(Throwable throwable) {
							log.error("onThrowable: " + throwable.getMessage());
							if(throwable.getMessage() != null){
								getFeedListener().onError(throwable.getMessage());
							}
						}
					});
		} catch (IOException exception) {
			log.error(exception.getMessage());
			getFeedListener().onError(exception.getMessage());
		}
	}
	
	protected void onFeedReceived(String message) {
		log.debug("onFeedReceived:" + message);
		TickerModel tickerModel;
		try {
			tickerModel = getDecoder().decode(message);
			log.debug("onFeedReceived: market name " + getConfig().getName());
			tickerModel.setMarketName(getConfig().getName());
			getFeedListener().onFeedFetch(tickerModel);
		} catch (DecoderException e) {
			log.error("onFeedReceived: decoder error " + message);
			getFeedListener().onError(e.getMessage());
		} catch (Exception e) {
			log.error("onFeedReceived: error " + message);
			getFeedListener().onError(e.getMessage());
		}
	}

	public AsyncHttpClient getAsyncHttpClient() {
		return asyncHttpClient;
	}

	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}

	public void setDecoder(IFeedDecoder decoder) {
		this.decoder = decoder;
	}

	public IFeedDecoder getDecoder() {
		return decoder;
	}

	public void setConfig(FeedPollerConfig config) {
		this.config = config;
	}

	public FeedPollerConfig getConfig() {
		return config;
	}

	public FeedFetcherListener getFeedListener() {
		return feedListener;
	}

	public void setFeedListener(FeedFetcherListener feedListener) {
		this.feedListener = feedListener;
	}
}
