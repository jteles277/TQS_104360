package tqs.euromillions;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import tqs.StocksPortfolio;
import tqs.IStockmarketService;
import tqs.Stock;

@ExtendWith(MockitoExtension.class)
public class HamcrestTests {

    @BeforeEach
    public void setUp()  {
         
    }

    // 1. Prepare a mock to substitute the remote service (@Mock annotation)
    @Mock
    IStockmarketService market;

    // 2. Create an instance of the subject under test (SuT) and use the mock to set the (remote) service instance.
    @InjectMocks
    StocksPortfolio portfolio;

    @DisplayName("Tests GetTotalValue")
    @Test
    public void testGetTotalValue() {
 
        //3. Load the mock with the proper expectations (when...thenReturn)
        when(market.lookUpPrice("SUSANO")).thenReturn(4.0);
        when(market.lookUpPrice("BEEONE")).thenReturn(1.5); 

        //4. Execute the test (use the service in the SuT)
        portfolio.addStock(new Stock("SUSANO", 2));
        portfolio.addStock(new Stock("BEEONE", 4));  

        //5. Verify the result (assert) and the use of the mock (verify)
        assertThat(14.0, equalTo(portfolio.getTotalValue()));
        verify(market, times(2)).lookUpPrice(anyString());
 
    }


}
