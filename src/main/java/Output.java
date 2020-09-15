import java.math.BigDecimal;

public class Output extends Sell {

    private Status status;
    private BigDecimal pricePaid;
    private int totalBidCount;
    private BigDecimal highestBid;
    private BigDecimal lowestBid;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(BigDecimal pricePaid) {
        this.pricePaid = pricePaid;
    }

    public int getTotalBidCount() {
        return totalBidCount;
    }

    public void setTotalBidCount(int totalBidCount) {
        this.totalBidCount = totalBidCount;
    }

    public BigDecimal getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(BigDecimal highestBid) {
        this.highestBid = highestBid;
    }

    public BigDecimal getLowestBid() {
        return lowestBid;
    }

    public void setLowestBid(BigDecimal lowestBid) {
        this.lowestBid = lowestBid;
    }

    public String getPrintableText()
    {
        return this.getCloseTime() + "|" + this.getItem() + "|" + (this.getStatus().equals(Status.SOLD) ? this.getUserId() : "") + "|" +
                this.getStatus() + "|" + this.getPricePaid() + "|" + this.getTotalBidCount() + "|" + this.getHighestBid() + "|" +
                this.getLowestBid();
    }

    public enum Status{
        SOLD, UNSOLD;
    }
}
