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
    int VVV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Name_filter = (TextView) findViewById(R.id.name_filter);
        ImageResult = (ImageView) findViewById(R.id.edit_image);
        ValSeekbar = (SeekBar) findViewById(R.id.seekBar);



        Intent intent = getIntent();
        int valImage = intent.getIntExtra("valImage_2",0);

        if (valImage == 0){
            ImageResult.setImageResource(R.drawable.back);
        }

        if (valImage == 1) {
            ImageResult.setImageResource(R.drawable.image_1);

        }
        if (valImage == 2) {
            ImageResult.setImageResource(R.drawable.image_2);

        }
        if (valImage == 3) {
            ImageResult.setImageResource(R.drawable.image_3);}

        //SeekBar

        final SeekBar seekbar = (SeekBar) findViewById(R.id.seekBar);
        seekbar.setOnSeekBarChangeListener(this);

        TextValue = (TextView) findViewById(R.id.val_saturation);
        TextValue.setText("0");


        VVV=valImage;

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

/*

    // Проверяет, доступно ли external storage для чтения и записи
    public boolean isExternalStorageWritable()
    {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
*/


    //>Методы для изменения изображения
    public void filter_1(View view) {
    //виньетирование
        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.fil1);
        filters("Виньетирование",bm2);
    }

    public void filter_2(View view) {
        //
        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.fil2);
        filters("filter2",bm2);
    }

    public void filter_3(View view) {
        //
        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.fil3);
        filters("filter3",bm2);

    }

    public void filter_4(View view) {
        //
        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.fil4);
        filters("filter4",bm2);
    }

    public void filter_5(View view) {
        //
        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.fil5);
        filters("filter5",bm2);
    }

    private void filters(String namefil,Bitmap bm2){
        //общий код для фильтров

        Bitmap bm1 = ((BitmapDrawable)ImageResult.getDrawable()).getBitmap();
        Bitmap newBitmap = null;
        Name_filter.setText(namefil);

        int w=bm1.getWidth();
        int h=bm2.getHeight();

        //Bitmap.Config config = bm1.getConfig();
        Bitmap.Config config = Bitmap.Config.ARGB_8888;

        newBitmap = Bitmap.createBitmap(w, h, config);
        Canvas newCanvas = new Canvas(newBitmap);

        newCanvas.drawBitmap(bm1, 0, 0, null);

        Paint paint = new Paint();
        int val = Integer.valueOf(ValSeekbar.getProgress());
        paint.setAlpha(val);
        newCanvas.drawBitmap(bm2, 0, 0, paint);

        ImageResult.setImageBitmap(newBitmap);
    }
    //<Методы для изменения изображения


    //Сохранение временных изменений
    public void apply_changes(View view) {

        if (VVV == 1) {
            ImageResult.setImageResource(R.drawable.image_1);

        }
        if (VVV == 2) {
            ImageResult.setImageResource(R.drawable.image_2);

        }
        if (VVV == 3) {
            ImageResult.setImageResource(R.drawable.image_3);}
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