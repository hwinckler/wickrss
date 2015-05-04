package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.wickrss.category.test.CategoryFeedTest;
import br.com.wickrss.category.test.CategoryTest;
import br.com.wickrss.channel.test.ChannelLastPubDateTest;
import br.com.wickrss.channel.test.ChannelTest;
import br.com.wickrss.feed.test.FeedMarkAsReadTest;
import br.com.wickrss.feed.test.FeedTest;
import br.com.wickrss.model.test.RSSFeedParserTest;
import br.com.wickrss.user.test.UserTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	UserTest.class,
	CategoryTest.class,
	CategoryFeedTest.class,
	ChannelTest.class,
	ChannelLastPubDateTest.class,
	FeedTest.class,
	FeedMarkAsReadTest.class,
	RSSFeedParserTest.class
})
public class AllTests {


}
