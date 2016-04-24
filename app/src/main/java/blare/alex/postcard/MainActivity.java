package blare.alex.postcard;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    int valImage;


    //private ImageButton ValImageChange;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Создание директории для приложения
        getAlbumStorageDir("Postcard");

    }

    public File getAlbumStorageDir(String albumName) {
        // Получение каталога для публичного каталога картинок пользователя.
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs())
        {
            Log.e("MyActivity", "Директория не создана");
        }
        return file;
    }

    public void onClick_but_im(View view) {
        int id = view.getId();
        if (id == R.id.button_image_1){
            valImage = 1;
            transition();}
        if (id == R.id.button_image_2){
            valImage = 2;
            transition();}
        if (id == R.id.button_image_3){
            valImage = 3;
            transition();}
        if (id == R.id.button_image_4){
            valImage = 4;
            transition();}
        if (id == R.id.button_image_5){
            valImage = 5;
            transition();}
        if (id == R.id.button_image_6){
            valImage = 6;
            transition();}
    }

    private void transition(){
        Toast.makeText(this, "Вы выбрали изображение " + valImage, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("valImage_2", valImage);
        startActivity(intent);
    }

    public void but_info(View view) {
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }
}


