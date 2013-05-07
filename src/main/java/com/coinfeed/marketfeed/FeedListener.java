package com.coinfeed.marketfeed;

public interface FeedListener {
	public void onFeedFetch();
	public void onError(String error);
}
