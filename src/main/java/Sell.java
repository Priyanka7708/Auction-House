import java.math.BigDecimal;

public class Sell extends Auction {
    private BigDecimal reservePrice;
    private int closeTime;

    public Sell(){};

    public Sell(int timestamp, int userId, String item, BigDecimal reservePrice, int closeTime) {
        this.setTimeStamp(timestamp);
        this.setUserId(userId);
        this.setAction("Sell");
        this.setItem(item);
        this.setReservePrice(reservePrice);
        this.setCloseTime(closeTime);
    }

    public BigDecimal getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(BigDecimal reservePrice) {
        this.reservePrice = reservePrice;
    }

    public int getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(int closeTime) {
        this.closeTime = closeTime;
    }
}
