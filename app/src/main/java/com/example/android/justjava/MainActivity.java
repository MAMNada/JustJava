/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        if(quantity < 10) {
            quantity += 1;
        }else{
            Toast.makeText(this, "No more",Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(quantity);
    }

    public void decrement(View view){
        if(quantity > 1) {
            quantity -= 1;
        }else{
            Toast.makeText(this, "No less",Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(quantity);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = findViewById(R.id.name);
        String personName = nameField.getText().toString();

        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(personName,price,hasWhippedCream,hasChocolate );
//        displayMessage(priceMessage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     *
     * @return
     */
    public int calculatePrice(boolean hasWhipped, boolean hasChocolate) {
        int price = (5 + (hasWhipped ? 1:0)*1 +(hasChocolate?1:0)*2) * quantity;
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
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }

    /**
     *
     * @param price         - The value of the price as calculated by calculatePrice()
     * @param hasWhipped    - boolean to indicate if whippedCream topping is required or not
     * @param hasChocolate  - boolean to indicate if chocolate topping is required or not
     * @return              - String to show the order summary
     */
    public String createOrderSummary(String name, int price, boolean hasWhipped, boolean hasChocolate){
        String orderSummary = name+"\n"+
                "Add WhippedCream?"+ hasWhipped +"\n"+
                "Add Chocolate?"+ hasChocolate +"\n"+
                "Quantity: "+ quantity +"\n"+
                "Total: "+ price+"$\n"+
                "Thank you!";
        return  orderSummary;
    }
}