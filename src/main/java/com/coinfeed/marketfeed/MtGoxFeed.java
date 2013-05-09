package com.coinfeed.marketfeed;

public class MtGoxFeed extends FeedBase implements IFeed {
	//private static final Logger log = LoggerFactory.getLogger(MtGoxFeed.class);
	private static final String QUERY_MTGOX = "https://data.mtgox.com/api/2/BTCUSD/money/ticker";
	public static final String DRIVER_NAME = "MtGox";
	
	public MtGoxFeed(FeedFetcherConfig config) {
		super(config);
		setQuery(QUERY_MTGOX);
		setMarketName(DRIVER_NAME);
		setDecoder(new MtGoxFeedDecoder());
	}
}
