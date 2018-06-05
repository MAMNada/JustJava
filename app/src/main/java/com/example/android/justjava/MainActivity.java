/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view){
        quantity +=1;
        displayQuantity(quantity);
    }

    public void decrement(View view){
        quantity -=1;
        displayQuantity(quantity);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        String priceMessage = createOrderSummary(calculatePrice(),whippedCreamCheckBox.isChecked() );
        displayMessage(priceMessage);
        calculatePrice();

    }

    /**
     *
     * @return
     */
    public int calculatePrice() {
        int price = quantity * 5;
        return price;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    public String createOrderSummary(int price, boolean hasWhipped){
        String orderSummary = "Name: Muhammad Nada\n"+
                "Add WhippedCream?"+ hasWhipped +"\n"+
                "Quantity: "+ quantity +"\n"+
                "Total: "+ price+"$\n"+
                "Thank you!";
        return  orderSummary;
    }
}