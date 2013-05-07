package com.coinfeed.marketfeed;

import java.util.concurrent.Future;

import com.ning.http.client.AsyncHttpClient;

public class FeedBase {

	protected AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
	protected FeedListener feedListener;
	protected Future<String> _future;
	
	public FeedListener getFeedListener() {
		return feedListener;
	}

	public AsyncHttpClient getAsyncHttpClient() {
		return asyncHttpClient;
	}
	
	protected FeedBase(FeedListener feedListener){
		this.feedListener = feedListener;
	}
}
