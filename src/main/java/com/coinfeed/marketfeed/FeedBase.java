package com.coinfeed.marketfeed;

import java.io.IOException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

public abstract class FeedBase {
	private static final Logger log = LoggerFactory.getLogger(BitstampFeed.class);
	protected AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
	protected FeedListener feedListener;
	protected Future<String> _future;
	protected String query;
	
	protected abstract void onFeedReceived(String message);
	
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
							log.error(throwable.getMessage());
							getFeedListener().onError(throwable.getMessage());
						}
					});
		} catch (IOException exception) {
			log.error(exception.getMessage());
			getFeedListener().onError(exception.getMessage());
		}
	}
	
	public FeedListener getFeedListener() {
		return feedListener;
	}

	public AsyncHttpClient getAsyncHttpClient() {
		return asyncHttpClient;
	}
	
	protected FeedBase(FeedListener feedListener){
		this.feedListener = feedListener;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
}
