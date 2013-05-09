package com.coinfeed.marketfeed;

public class BitstampFeed extends FeedBase implements IFeed {
	//private static final Logger log = LoggerFactory.getLogger(BitstampFeed.class);
	private static final String QUERY_BITSTAMP = "https://www.bitstamp.net/api/ticker/";

	public BitstampFeed(FeedFetcherConfig config) {
		super(config);
		setQuery(QUERY_BITSTAMP);
		setMarketName("Bitstamp");
		setDecoder(new BitstampFeedDecoder());
	}
}
