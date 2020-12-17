package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.bean.UserInfo;
import com.example.myapplication.database.UserDBHelper;
import com.example.myapplication.util.DateUtil;
import com.example.myapplication.util.ViewUtil;


public class RegisterWriteActivity extends AppCompatActivity implements View.OnClickListener{

    private UserDBHelper mHelper; // 声明一个用户数据库帮助器的对象
    private EditText ct_name;
    private EditText ct_phonenumber;
    private EditText ct_password;

//    private EditText et_weight;
//    private boolean bMarried = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_write);

        ct_name = findViewById(R.id.ct_name);
        ct_phonenumber = findViewById(R.id.ct_phonenumber);
        ct_password = findViewById(R.id.ct_password);

//        et_weight = findViewById(R.id.et_weight);
        findViewById(R.id.btn_save).setOnClickListener(this);
        ct_phonenumber.addTextChangedListener(new HideTextWatcher(ct_phonenumber));
        ct_password.addTextChangedListener(new HideTextWatcher(ct_password));


//
//        Spinner sp_married = findViewById(R.id.sp_married);
//        sp_married.setOnItemSelectedListener(new TypeSelectedListener());
    }


    //    class TypeSelectedListener implements AdapterView.OnItemSelectedListener {
//        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//            bMarried = (arg2 == 0) ? false : true;
//        }
//
//        public void onNothingSelected(AdapterView<?> arg0) {
//        }
//    }
// 定义编辑框的文本变化监听器
    private class HideTextWatcher implements TextWatcher {
        private EditText myView;// 声明一个编辑框对象
        private int myMaxLength;// 声明一个最大长度变量
        private CharSequence myStr; // 声明一个文本串

        HideTextWatcher(EditText v) {
            super();
            myView = v;
            myMaxLength = ViewUtil.getMaxLength(v);
        }

        // 在编辑框的输入文本变化前触发
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        // 在编辑框的输入文本变化时触发
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            myStr = s;
        }

        // 在编辑框的输入文本变化后触发
        public void afterTextChanged(Editable s) {
            if (myStr == null || myStr.length() == 0)
                return;
            // 输入文本达到11位（如手机号码）时关闭输入法
            if (myStr.length() == 11 && myMaxLength == 11) {
                ViewUtil.hideAllInputMethod(com.example.myapplication.RegisterWriteActivity.this);
            }
            // 输入文本达到6位（如登录密码）时关闭输入法
            if (myStr.length() == 8 && myMaxLength == 8) {
                ViewUtil.hideOneInputMethod(com.example.myapplication.RegisterWriteActivity.this, myView);
            }
            if (myStr.length() == 6 && myMaxLength == 6) {
                ViewUtil.hideOneInputMethod(com.example.myapplication.RegisterWriteActivity.this, myView);
            }
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) {
            String name = ct_name.getText().toString();
            String phone = ct_phonenumber.getText().toString();
            String pwd = ct_password.getText().toString();
//            String weight = et_weight.getText().toString();
            if (TextUtils.isEmpty(name)) {
                showToast("请输入姓名");
                return;
            } else if (TextUtils.isEmpty(phone)) {
                showToast("请输入电话号码");
                return;
            } else if (TextUtils.isEmpty(pwd)) {
                showToast("请输入密码");
                return;
//            } else if (TextUtils.isEmpty(weight)) {
//                showToast("请先填写体重");
//                return;
            }


            // 以下声明一个用户信息对象，并填写它的各字段值
            UserInfo info = new UserInfo();
            info.name = name;
            info.phone = phone;
            info.pwd = pwd;
//            info.weight = Float.parseFloat(weight);
//            info.married = bMarried;
            info.update_time = DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss");

            // 执行数据库帮助器的插入操作
            mHelper.insert(info);
            showToast("注册成功");

            Intent intent = new Intent(this, com.example.myapplication.MainActivity.class);
            startActivity(intent);
        }
    }


    // 定义编辑框的文本变化监听器

    private void showToast(String desc) {
        Toast.makeText(this, desc, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 获得数据库帮助器的实例
        mHelper = UserDBHelper.getInstance(this, 2);
        // 打开数据库帮助器的写连接
        mHelper.openWriteLink();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 关闭数据库连接
        mHelper.closeLink();
    }
}
