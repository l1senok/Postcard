package blare.alex.postcard;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Main2Activity extends AppCompatActivity
implements SeekBar.OnSeekBarChangeListener
{
    TextView Name_filter, TextValue;
    ImageView ImageResult;
    SeekBar ValSeekbar;
    int valImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Name_filter = (TextView) findViewById(R.id.name_filter);
        ImageResult = (ImageView) findViewById(R.id.edit_image);
        ValSeekbar = (SeekBar) findViewById(R.id.seekBar);



        Intent intent = getIntent();
        valImage = intent.getIntExtra("valImage_2", 0);

        if (valImage == 0) {ImageResult.setImageResource(R.drawable.back);}
        if (valImage == 1) {ImageResult.setImageResource(R.drawable.image_1);}
        if (valImage == 2) {ImageResult.setImageResource(R.drawable.image_2);}
        if (valImage == 3) {ImageResult.setImageResource(R.drawable.image_3);}
        if (valImage == 4) {ImageResult.setImageResource(R.drawable.image_4);}
        if (valImage == 5) {ImageResult.setImageResource(R.drawable.image_5);}
        if (valImage == 6) {ImageResult.setImageResource(R.drawable.image_6);}

        //SeekBar
        final SeekBar seekbar = (SeekBar) findViewById(R.id.seekBar);
        seekbar.setOnSeekBarChangeListener(this);
        seekbar.setProgress(100);
        TextValue = (TextView) findViewById(R.id.val_saturation);
        TextValue.setText("100");

    }

        //>Методы для ползунка(переопределение)
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
            TextValue.setText(String.valueOf(seekBar.getProgress()));
        }
        //<Методы для ползунка


    //>Методы для изменения изображения
    public void filter_1(View view) {
    //виньетирование
        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.fil1);
        filters("Виньетирование",bm2);
    }

    public void filter_2(View view) {
        //Pony
        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.fil2);
        filters("Pony",bm2);
    }

    public void filter_3(View view) {
        //New Year
        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.fil3);
        filters("New Year",bm2);

    }

    public void filter_4(View view) {
        //Bokeh
        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.fil4);
        filters("Bokeh",bm2);
    }

    public void filter_5(View view) {
        //Blood
        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.fil5);
        filters("Blood",bm2);
    }

    private void filters(String namefil,Bitmap bm2){
        //общий код для фильтров

        Bitmap bm1 = ((BitmapDrawable)ImageResult.getDrawable()).getBitmap();
        Bitmap newBitmap = null;
        Name_filter.setText(namefil);

        int w=bm1.getWidth();
        int h=bm1.getHeight();

        Bitmap.Config config = Bitmap.Config.ARGB_8888;

        newBitmap = Bitmap.createBitmap(w, h, config);
        Canvas newCanvas = new Canvas(newBitmap);

        newCanvas.drawBitmap(bm1, 0, 0, null);

        Paint paint = new Paint();
        int val = Integer.valueOf(ValSeekbar.getProgress());
        paint.setAlpha(val+125);
        newCanvas.drawBitmap(bm2, 0, 0, paint);

        ImageResult.setImageBitmap(newBitmap);
    }
    //<Методы для изменения изображения

    //Отмена всех изменений
    public void back(View view) {

        if (valImage == 1) {ImageResult.setImageResource(R.drawable.image_1);}
        if (valImage == 2) {ImageResult.setImageResource(R.drawable.image_2);}
        if (valImage == 3) {ImageResult.setImageResource(R.drawable.image_3);}
        if (valImage == 4) {ImageResult.setImageResource(R.drawable.image_4);}
        if (valImage == 5) {ImageResult.setImageResource(R.drawable.image_5);}
        if (valImage == 6) {ImageResult.setImageResource(R.drawable.image_6);}
    }

    //Сохранение изображения в галлерею и переход для отправки по почте
    public void go_next(View view) {

        //>сохранение объекта bitmap в папке picture/Postcard на внешнем носителе
        Bitmap bitmap = ((BitmapDrawable)ImageResult.getDrawable()).getBitmap();
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Postcard", "copy_image.png");

        try {
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            } finally {
                if (fos != null) fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //<сохранение объекта bitmap в папке picture/Postcard на внешнем носителе

        Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
        // intent.putExtra("valImage_2", valImage);
        startActivity(intent);
    }

}