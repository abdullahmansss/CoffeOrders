package abdullah.mansour.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity
{
    TextView name_txt,total_txt;
    Button new_order;

    String name = "";
    int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name = getIntent().getStringExtra("name");
        total = getIntent().getIntExtra("total", 0);

        name_txt = findViewById(R.id.name_txt);
        total_txt = findViewById(R.id.total_txt);
        new_order = findViewById(R.id.new_order_btn);

        name_txt.setText(name);
        total_txt.setText(total + " L.E");
    }
}
