package com.coinfeed.marketfeed;

import java.io.IOException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

public abstract class FeedBase {
	private static final Logger log = LoggerFactory.getLogger(FeedBase.class);
	protected AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
	protected FeedListener feedListener;
	protected Future<String> _future;
	protected String query;
	private String marketName;
	private IFeedDecoder decoder;
	private FeedFetcherConfig config;
	
	public FeedBase(FeedFetcherConfig config){
		if(config == null){
			this.config = new FeedFetcherConfig();
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
			log.debug("onFeedReceived: market name " + getMarketName());
			tickerModel.setMarketName(getMarketName());
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

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setDecoder(IFeedDecoder decoder) {
		this.decoder = decoder;
	}

	public IFeedDecoder getDecoder() {
		return decoder;
	}

	public void setConfig(FeedFetcherConfig config) {
		this.config = config;
	}

	public FeedFetcherConfig getConfig() {
		return config;
	}

	public FeedListener getFeedListener() {
		return feedListener;
	}

	public void setFeedListener(FeedListener feedListener) {
		this.feedListener = feedListener;
	}
}
