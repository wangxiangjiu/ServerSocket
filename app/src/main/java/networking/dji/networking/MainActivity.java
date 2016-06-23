package networking.dji.networking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView serverMsg;
    MyServer server;

    /* Button to start the server. */
    Button start;
    /* Button expect to remotely control a client test off button. */
    Button trigger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serverMsg = (TextView) findViewById(R.id.serverMsg);
        server = new MyServer();
        start = (Button) findViewById(R.id.start_server);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.start_server) {
                    server.startListening();
                    serverMsg.setText(""+server.len);
                }

            }
        });

        trigger = (Button) findViewById(R.id.trigger);
        trigger.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    server.sendMsgToAll("ACTION DOWN");
                }
                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    server.sendMsgToAll("ACTION UP");
                }
                return false;
            }
        });


    }


}
