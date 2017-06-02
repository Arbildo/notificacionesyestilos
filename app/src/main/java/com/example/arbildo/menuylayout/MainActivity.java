package com.example.arbildo.menuylayout;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.DropBoxManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.security.KeyStore;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    EditText caja1, caja2;
    TextView tvResultado;
    double n1,n2;
    Double resultado;
    int notificacionID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        caja1=(EditText)findViewById(R.id.et1);
        caja2=(EditText)findViewById(R.id.et2);
        tvResultado=(TextView)findViewById(R.id.tv1);
       }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.restar:
                n1=Double.parseDouble(caja1.getText().toString());
                n2=Double.parseDouble(caja2.getText().toString());
                resultado=n1-n2;
                tvResultado.setText(""+resultado);

                desplegarNotificacion();
                return true;
            case R.id.sumar:
                n1=Double.parseDouble(caja1.getText().toString());
                n2=Double.parseDouble(caja2.getText().toString());
                resultado=n1+n2;
                tvResultado.setText(""+resultado);

                desplegarNotificacion();
                return true;

            case R.id.notificacion:
                Intent i = new Intent (this, NotificationActivity.class);
                i.putExtra("Vacio", caja1.getText().toString());
                startActivity(i);
            return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickk (View v)
    {
        Intent i = new Intent (this, NotificationActivity.class);
        i.putExtra("Vacio", caja1.getText().toString());
        startActivity(i);
    }

    protected void desplegarNotificacion(){
        Intent i = new Intent(this, NotificationView.class);
        i.putExtra("notificationID", notificacionID);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);
        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        CharSequence ticker ="Visitanos!!";
        CharSequence contentTitle = "Se ha efectuado una operación";
        CharSequence contentText = "El resultado es"+resultado;
        Notification noti = new NotificationCompat.Builder(this)
                .setContentIntent(pendingIntent)
                .setTicker(ticker)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSmallIcon(R.drawable.ic_launcher)
                .addAction(R.drawable.ic_launcher, ticker, pendingIntent)
                .setVibrate(new long[] {100, 250, 100, 500})
                .build();
        nm.notify(notificacionID, noti);
    }

}
