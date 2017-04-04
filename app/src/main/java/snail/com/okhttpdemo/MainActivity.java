package snail.com.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    final OkHttpClient mOkHttpClient = new OkHttpClient();
    private TextView mShowTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getBtn = (Button)findViewById(R.id.getBtn);
        getBtn.setOnClickListener(this);
    }

    private void doGetRequest(){
        final Request request=new Request.Builder()
                .get()
                .tag(this)
                .url("http://www.wooyun.org")  
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = mOkHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.i("linjq","打印GET响应的数据：" + response.body().string());
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getBtn:
                doGetRequest();
                break;
        }
    }
}
