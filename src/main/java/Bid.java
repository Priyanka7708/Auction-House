import java.math.BigDecimal;

public class Bid extends Auction {
    private BigDecimal bidAmount;

    public Bid(int timestamp, int userId, String item, BigDecimal bidAmount) {
        this.setTimeStamp(timestamp);
        this.setUserId(userId);
        this.setAction("Bid");
        this.setItem(item);
        this.setBidAmount(bidAmount);
    }

    public BigDecimal getBidAmount() {
        return bidAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setBidAmount(BigDecimal bidAmount) {
        this.bidAmount = bidAmount;
    }
}
