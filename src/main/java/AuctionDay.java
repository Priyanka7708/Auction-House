import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class AuctionDay {
    private HashMap<String, ItemForSale> sellItems = new HashMap<>();


    public void processLine(String str) {
        // formats
        // timestamp|user_id|action|item|reserve_price|close_time
        // timestamp|user_id|action|item|bid_amount
        // timestamp (Heartbeat messages)
        int timestamp;
        int userId;
        String action;
        String item;
        BigDecimal reservePrice;
        int closeTime;
        BigDecimal bidAmount;

        Scanner sc = new Scanner(str);
        sc.useDelimiter("[|]");

        while (sc.hasNext()) {
            timestamp = sc.nextInt();
            if (!sc.hasNext()) {
                return;
            }
            userId = sc.nextInt();
            action = sc.next();
            item = sc.next();

            if (action.equals("SELL")) {
                reservePrice = sc.nextBigDecimal();
                closeTime = sc.nextInt();

                // create Sell object
                Sell sell = new Sell(timestamp, userId, item, reservePrice, closeTime);
                sellItems.put(item, new ItemForSale(sell));
            } else {
                bidAmount = sc.nextBigDecimal();

                // create Bid Object
                Bid bid = new Bid(timestamp, userId, item, bidAmount);
                if (sellItems.containsKey(item))
                    sellItems.get(item).makeBid(bid);
            }
        }
    }

    public String getOutputForItem(String itemName) {
        if (!sellItems.containsKey(itemName))
            return "";
        return sellItems.get(itemName).generateOutput().getPrintableText();
    }

    public void doAuction() {
        // could probably do something with a stream here
        List<ItemForSale> items = new ArrayList<>();
        for (ItemForSale entry : sellItems.values()) {
            printOutput( entry.generateOutput() );
            items.add(entry);
        }
        printOutputToFile(items, "output.txt");
    }

    private void printOutputToFile(List<ItemForSale> sellItems, String filename) {
        try {
            File myObj = new File(filename);
            BufferedWriter writer;
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            writer = new BufferedWriter(new FileWriter(myObj));

            for (ItemForSale item : sellItems) {
                writer.write(item.generateOutput().getPrintableText());
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void printOutput(Output output) {
        // close_time|item|user_id|status|price_paid|total_bid_count|highest_bid|lowest_bid
        System.out.println(output.getPrintableText());
        try {
            File myObj = new File("output.txt");
            BufferedWriter writer;
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            writer = new BufferedWriter(new FileWriter(myObj, true));
            writer.write(output.getPrintableText());
            writer.newLine();

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
