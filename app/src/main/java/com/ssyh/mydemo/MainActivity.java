package com.ssyh.mydemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.ssyh.mydemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).transparentBar().init();
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.btShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DialogTest.show(MainActivity.this);
                Uri packageUri = Uri.parse("package:"+MainActivity.this.getPackageName());

                Intent intent = new Intent(Intent.ACTION_DELETE,packageUri);
                startActivity(intent);

            }
        });

        List<Integer> imgResList = new ArrayList<>();

        imgResList.add(R.drawable.icon_caomei);
        imgResList.add(R.drawable.icon_ximei);
        imgResList.add(R.drawable.icon_xiangjiao);
        imgResList.add(R.drawable.icon_pingguo);
        imgResList.add(R.drawable.icon_niuyouguo);
        imgResList.add(R.drawable.icon_mangguo);
        imgResList.add(R.drawable.icon_qingning);
        imgResList.add(R.drawable.icon_yingtao);
        imgResList.add(R.drawable.icon_cengzi);
        imgResList.add(R.drawable.icon_lanmei);

        List<Integer> hongBaoResList = new ArrayList<>();

        hongBaoResList.add(R.drawable.icon_hongbao1);
        hongBaoResList.add(R.drawable.icon_hongbao2);
        hongBaoResList.add(R.drawable.icon_hongbao3);
        hongBaoResList.add(R.drawable.icon_hongbao4);
        hongBaoResList.add(R.drawable.icon_hongbao5);


        binding.randomIvLy.setImgResList(imgResList,4,hongBaoResList);
    }
}