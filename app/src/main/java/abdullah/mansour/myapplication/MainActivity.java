package abdullah.mansour.myapplication;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    RadioGroup radioGroup;
    RadioButton french,turkish,esspresso;
    CheckBox cream,chocolate,mocca;
    EditText name_field;
    ImageButton minus,plus;
    TextView number_of_cups_txt;
    Button order_btn;

    int cups = 1;
    int additions = 0;
    int price = 0;
    int result = 0;

    int y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        onRadioButtonClicked();
        cups();
        order();
    }

    public void initViews()
    {
        radioGroup = findViewById(R.id.radio_group);
        french = findViewById(R.id.radio_french);
        turkish = findViewById(R.id.radio_turkish);
        esspresso = findViewById(R.id.radio_esspresso);
        cream = findViewById(R.id.add_cream_ch_bx);
        chocolate = findViewById(R.id.add_chocolate_ch_bx);
        mocca = findViewById(R.id.add_mocca_ch_bx);
        name_field = findViewById(R.id.name_field);
        minus = findViewById(R.id.minus_btn);
        plus = findViewById(R.id.add_btn);
        number_of_cups_txt = findViewById(R.id.number_cups);
        order_btn = findViewById(R.id.order_now_btn);

        number_of_cups_txt.setText("" + cups);
    }

    public void cups ()
    {
        plus.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v)
            {
                if (cups == 10)
                {
                    if (y == 0)
                    {
                        Toast.makeText(MainActivity.this, "you can't order more than 10 cups at once.", Toast.LENGTH_SHORT).show();
                        y = 1;
                    } else if (y == 1)
                    {
                        Toast.makeText(MainActivity.this, "انت غبي ياض", Toast.LENGTH_SHORT).show();
                        y = 2;
                    } else if (y == 2)
                    {
                        Toast.makeText(MainActivity.this, "والله مانت قاعد فيها", Toast.LENGTH_SHORT).show();
                        y = 3;
                    } else if (y == 3)
                    {
                        finishAffinity();
                    }
                } else
                    {
                        cups = cups + 1;
                        number_of_cups_txt.setText("" + cups);
                    }
            }
        });

        minus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (cups == 1)
                {
                    Toast.makeText(MainActivity.this, "انت غبي ياض هتطلب اقل من كبايه ازاي", Toast.LENGTH_SHORT).show();
                } else
                    {
                        cups = cups - 1;
                        number_of_cups_txt.setText("" + cups);
                    }
            }
        });
    }

    public void order ()
    {
        order_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name = name_field.getText().toString();

                if (!TextUtils.isEmpty(name))
                {
                    if (cream.isChecked())
                    {
                        additions = additions + 3;
                    }

                    if (chocolate.isChecked())
                    {
                        additions = additions + 5;
                    }

                    if (mocca.isChecked())
                    {
                        additions = additions + 7;
                    }

                    result = (price + additions) * cups;

                    Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("total", result);
                    startActivity(intent);
                } else
                    {
                        Toast.makeText(MainActivity.this, "اكتب اسمك يا اهبل", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    public void onRadioButtonClicked()
    {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch(checkedId)
                {
                    case R.id.radio_french :
                        price = 5;
                        //Toast.makeText(MainActivity.this, "French : " + price, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_turkish :
                        price = 7;
                        //Toast.makeText(MainActivity.this, "Turkish : " + price, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_esspresso :
                        price = 10;
                        //Toast.makeText(MainActivity.this, "Esspresso : " + price, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
