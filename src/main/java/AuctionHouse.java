import java.io.*;
import java.util.*;

public class AuctionHouse {
    static HashMap<String, ItemForSale> sellItems;

    public static void main(String[] args) {
        sellItems = new HashMap<>();
        String location = null;
        File file = null;

        // allow different settings based on user - set the location of the file in config.properties file
        // InputStream is = Properties.class.getResourceAsStream("/config/config.properties");
        InputStream is = AuctionHouse.class.getResourceAsStream("/config/config.properties");
        if (is != null) {
            Properties configProps = new Properties();
            try {
                configProps.load(is);
                location = configProps.getProperty("location");
                file = new File(location);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Scanner sc = null;
        AuctionDay auctionDay = new AuctionDay();
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                auctionDay.processLine(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sc.close();

        // perform auction
        auctionDay.doAuction();
    }
}


