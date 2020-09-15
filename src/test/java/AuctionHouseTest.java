import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class AuctionHouseTest {
    static HashMap<String, ItemForSale> sellItems;

    @Test
    public void doAuction() {
        sellItems = new HashMap<>();

        AuctionDay auctionDay = new AuctionDay();

        auctionDay.processLine("");
        auctionDay.processLine("10|1|SELL|toaster_1|10.00|20");
        auctionDay.processLine("12|8|BID|toaster_1|7.50");
        auctionDay.processLine("13|5|BID|toaster_1|12.50");
        auctionDay.processLine("15|8|SELL|tv_1|250.00|20");
        auctionDay.processLine("16");
        auctionDay.processLine("17|8|BID|toaster_1|20.00");
        auctionDay.processLine("18|1|BID|tv_1|150.00");
        auctionDay.processLine("19|3|BID|tv_1|200.00");
        auctionDay.processLine("20");
        auctionDay.processLine("21|3|BID|tv_1|300.00");
        auctionDay.doAuction( );

        String outputToaster_1 = auctionDay.getOutputForItem("toaster_1");
        String outputTV_1 = auctionDay.getOutputForItem("tv_1");

        Assert.assertEquals("20|toaster_1|8|SOLD|12.50|3|20.00|7.50",outputToaster_1);
        Assert.assertEquals("20|tv_1||UNSOLD|0.00|2|200.00|150.00",outputTV_1);
    }
}