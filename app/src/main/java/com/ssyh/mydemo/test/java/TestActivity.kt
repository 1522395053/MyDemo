package com.ssyh.mydemo.test.java

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ssyh.mydemo.R
import kotlinx.android.synthetic.main.activity_test.*


class TestActivity : AppCompatActivity() {
    var number = 2f;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        var defaultComponent = ComponentName(this,"com.ssyh.mydemo.qingning");
        var caomeiComponent = ComponentName(this,"com.ssyh.mydemo.caomei");
        var pingguoComponent = ComponentName(this,"com.ssyh.mydemo.pingguo");


        btn_default.setOnClickListener {
            enableComponent(defaultComponent)
            disEnableComponent(caomeiComponent)
            disEnableComponent(pingguoComponent)
        }

        btn_caomei.setOnClickListener {
            enableComponent(caomeiComponent)
            disEnableComponent(defaultComponent)
            disEnableComponent(pingguoComponent)
        }


        btn_pingguo.setOnClickListener {
            enableComponent(pingguoComponent)
            disEnableComponent(caomeiComponent)
            disEnableComponent(defaultComponent)
        }

        //var newInstance = Class.forName("Student类全限定名").newInstance()
        //或者
        //Student.class.newInstance();


    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
        var defaultComponent = ComponentName(this,"com.ssyh.mydemo.qingning");
        var caomeiComponent = ComponentName(this,"com.ssyh.mydemo.caomei");
        var pingguoComponent = ComponentName(this,"com.ssyh.mydemo.pingguo");
        enableComponent(pingguoComponent)
        disEnableComponent(caomeiComponent)
        disEnableComponent(defaultComponent)
    }




    fun enableComponent(componentName: ComponentName){
        packageManager?.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
        PackageManager.DONT_KILL_APP)
    }

    fun disEnableComponent(componentName: ComponentName){
        packageManager?.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP)
    }
}