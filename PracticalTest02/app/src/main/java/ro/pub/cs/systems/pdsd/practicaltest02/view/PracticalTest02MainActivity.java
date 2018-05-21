package ro.pub.cs.systems.pdsd.practicaltest02.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import general.Constants;
import model.TimeInformation;
import network.ClientThread;
import network.ServerThread;
import ro.pub.cs.systems.pdsd.practicaltest02.R;

public class PracticalTest02MainActivity extends AppCompatActivity {

    // Server widgets
    private EditText serverPortEditText = null;
    private Button connectButton = null;

    // Client widgets
    private EditText clientPortEditText = null;

    private EditText cityEditText = null;
    private Spinner informationTypeSpinner = null;
    private TextView time_text_view = null;

    private ServerThread serverThread = null;
    private ClientThread clientThread = null;

    private EditText key = null;
    private EditText value = null;
    private EditText key_get = null;

    private Button put = null;
    private Button get = null;

    private ConnectButtonClickListener connectButtonClickListener = new ConnectButtonClickListener();
    private class ConnectButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            String serverPort = serverPortEditText.getText().toString();
            if (serverPort == null || serverPort.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Server port should be filled!", Toast.LENGTH_SHORT).show();
                return;
            }
            serverThread = new ServerThread(Integer.parseInt(serverPort));
            if (serverThread.getServerSocket() == null) {
                Log.e(Constants.TAG, "[MAIN ACTIVITY] Could not create server thread!");
                return;
            }
            serverThread.start();
            serverThread.getData();
        }
    }

    private GetTimeButtonClickListener getTimeButtonClickListener = new GetTimeButtonClickListener();
    private class GetTimeButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            String clientAddress = "localhost";//clientAddressEditText.getText().toString();

            String clientPort = "5005"; //clientPortEditText.getText().toString();
            if (clientAddress == null || clientAddress.isEmpty()
                    || clientPort == null || clientPort.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Client connection parameters should be filled!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (serverThread == null || !serverThread.isAlive()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] There is no server to connect to!", Toast.LENGTH_SHORT).show();
                return;
            }

            time_text_view.setText(Constants.EMPTY_STRING);

            clientThread = new ClientThread(
                    clientAddress, Integer.parseInt(clientPort),  "ALL", time_text_view
            );
            clientThread.start();
        }

    }

    private SetTimeButtonClickListener setTimeButtonClickListener = new SetTimeButtonClickListener();
    private class SetTimeButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            String clientAddress = "localhost";//clientAddressEditText.getText().toString();

            String clientPort = clientPortEditText.getText().toString();
            if (clientAddress == null || clientAddress.isEmpty()
                    || clientPort == null || clientPort.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Client connection parameters should be filled!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (serverThread == null || !serverThread.isAlive()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] There is no server to connect to!", Toast.LENGTH_SHORT).show();
                return;
            }
            String city = cityEditText.getText().toString();
            String informationType = informationTypeSpinner.getSelectedItem().toString();
            if (city == null || city.isEmpty()
                    || informationType == null || informationType.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Parameters from client (city / information type) should be filled", Toast.LENGTH_SHORT).show();
                return;
            }

           // weatherForecastTextView.setText(Constants.EMPTY_STRING);
//            key_get.setText(Constants.EMPTY_STRING);
//
//            clientThread = new ClientThread(
//                    clientAddress, Integer.parseInt(clientPort), city, informationType, weatherForecastTextView
//            );
//            clientThread.start();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(Constants.TAG, "[MAIN ACTIVITY] onCreate() callback method has been invoked");
        setContentView(R.layout.activity_practical_test02_main);

        serverPortEditText = (EditText)findViewById(R.id.server_port_edit_text);
        connectButton = (Button)findViewById(R.id.connect_button);
        connectButton.setOnClickListener(connectButtonClickListener);

        key = (EditText)findViewById(R.id.key);
        value = (EditText)findViewById(R.id.value);
        key_get = (EditText)findViewById(R.id.key_get);
        time_text_view = (TextView)findViewById(R.id.time_text_view);

        put = (Button)findViewById(R.id.put);
        get = (Button)findViewById(R.id.get);

        get.setOnClickListener(getTimeButtonClickListener);
        put.setOnClickListener(setTimeButtonClickListener);

    }

    @Override
    protected void onDestroy() {
        Log.i(Constants.TAG, "[MAIN ACTIVITY] onDestroy() callback method has been invoked");
        if (serverThread != null) {
            serverThread.stopThread();
        }
        super.onDestroy();
    }

}
