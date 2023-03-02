package tqs;

import java.util.List;
import java.util.ArrayList;

public class StocksPortfolio {
    List<Stock> stocks = new ArrayList<Stock>();
    IStockmarketService stockmarket;

    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket; 
    }

    public void addStock(Stock stock) {
        this.stocks.add(stock); 
    }

    public double getTotalValue() {
        
        double total = 0;
        
        for (Stock stock : stocks) {
            total += stockmarket.lookUpPrice(stock.getLabel()) * stock.getQuantity();
        }
    
        return total;
    }
}
