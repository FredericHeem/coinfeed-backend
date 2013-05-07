package com.coinfeed.marketfeed;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.Response;

public class BitstampFeed extends FeedBase implements IFeed {
	private static final Logger log = LoggerFactory
			.getLogger(BitstampFeed.class);
	private static final String query = "https://www.bitstamp.net/api/ticker/";

	public BitstampFeed(FeedListener feedListener) {
		super(feedListener);
		log.debug("ctor");
	}

	public void fetch() {
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

	protected void onFeedReceived(String message) {
		log.debug("onFeedReceived:" + message);
		TickerModel tickerModel = BitstampFeedDecoder.decode(message);
		getFeedListener().onFeedFetch(tickerModel);
	}
}
