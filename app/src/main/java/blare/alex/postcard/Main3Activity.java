package blare.alex.postcard;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;

import java.io.File;

public class Main3Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

    }

    public void send(View view) {

        EditText viewadress = (EditText)findViewById(R.id.edit_text_email_topic);
        EditText viewtext = (EditText)findViewById(R.id.edit_text_email_text);

        String strtopic = viewadress.getText().toString();
        String strEmailText = viewtext.getText().toString();

        //Путь до изображения
        String str_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) +
                "/Postcard" + "/copy_image.png";

        //>Передача изображения,текста и темы
        File fileIn = new File(str_path);
        Uri uri = Uri.fromFile(fileIn);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("image/png");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_TEXT, strEmailText);
        intent.putExtra(Intent.EXTRA_SUBJECT, strtopic);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
       //<Передача изображения,текста и темы



        if (intent.resolveActivity(getPackageManager()) != null){
            Toast.makeText(this, "Передача информации для отправки", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }



    }


}
