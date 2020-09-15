import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ItemForSale {
    private List<Bid> bidHistory = new ArrayList<>();
    private Sell sell;

    public ItemForSale(Sell sell) {
        this.sell = sell;
    }

    public void makeBid(Bid bid) {
        if (isValidBid(bid))
            bidHistory.add(bid);
    }

    private boolean isValidBid(Bid bid) {
        return bid.getTimeStamp() <= sell.getCloseTime() && bidIsHigherThanAPreviousBid(bid);
    }

    private boolean bidIsHigherThanAPreviousBid(Bid bid) {
        for (Bid previousBid : bidHistory) {
            if (previousBid.getBidAmount().compareTo(bid.getBidAmount()) < 0)
                return true;
        }
        return bidHistory.isEmpty();
    }

    public Output generateOutput() {
        Output output = new Output();
        output.setCloseTime(sell.getCloseTime());
        output.setItem(sell.getItem());

        // if two bids are received for the same amount then the earliest bid wins the item.
        bidHistory.sort(Comparator.comparing(Bid::getBidAmount).reversed().thenComparing(Bid::getTimeStamp));

        List<Bid> validBids = new ArrayList<>();
        for (Bid bid : bidHistory) {
            if (bid.getBidAmount().compareTo(sell.getReservePrice()) >= 0)
                validBids.add(bid);
        }
        if (validBids.isEmpty()) {
            output.setStatus(Output.Status.UNSOLD);
            output.setPricePaid(new BigDecimal("0.00"));
        } else {
            output.setStatus(Output.Status.SOLD);
            output.setUserId(validBids.get(0).getUserId());

            output.setPricePaid(validBids.size() > 1 ? validBids.get(1).getBidAmount() : sell.getReservePrice().setScale(2, BigDecimal.ROUND_HALF_UP));
        }

        output.setTotalBidCount(bidHistory.size());
        output.setHighestBid(bidHistory.isEmpty() ? new BigDecimal("0.00") : bidHistory.get(0).getBidAmount());
        output.setLowestBid(bidHistory.isEmpty() ? new BigDecimal("0.00") : bidHistory.get(bidHistory.size() - 1).getBidAmount());
        return output;
    }
}

