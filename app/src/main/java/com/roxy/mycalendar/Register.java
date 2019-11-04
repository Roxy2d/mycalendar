package com.roxy.mycalendar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    //用户名，密码，再次输入的密码的控件
    private EditText et_user_name,et_psw,et_psw_again;
    //用户名，密码，再次输入的密码的控件的获取值
    private String userName,psw,pswAgain;
    private RadioGroup Sex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面布局 ,注册界面
        setContentView(R.layout.activity_register);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();

    }

    private void init() {
        //从register.xml 页面中获取对应的UI控件
        Button register_btn = (Button) findViewById(R.id.btn_register);
        Button register_return=(Button)findViewById(R.id.register_return);
        et_user_name= (EditText) findViewById(R.id.et_user_name);
        et_psw= (EditText) findViewById(R.id.et_psw);
        et_psw_again= (EditText) findViewById(R.id.et_psw_again);
        Sex= (RadioGroup) findViewById(R.id.SexRadio);

        //注册按钮
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取输入在相应控件中的字符串
                getEditString();
                //判断输入框内容
                int sex;
                int sexChoseId = Sex.getCheckedRadioButtonId();
                switch (sexChoseId) {
                    case R.id.mainRegisterRdBtnFemale:
                        sex = 0;
                        break;
                    case R.id.mainRegisterRdBtnMale:
                        sex = 1;
                        break;
                    default:
                        sex = -1;
                        break;
                }

                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(Register.this, "Please input ID", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(Register.this, "Please input password", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(pswAgain)) {
                    Toast.makeText(Register.this, "Please input password again", Toast.LENGTH_SHORT).show();
                } else if (sex<0){
                    Toast.makeText(Register.this, "Please choose your sex", Toast.LENGTH_SHORT).show();
                }else if(!psw.equals(pswAgain)){
                    Toast.makeText(Register.this, "The passwords are different", Toast.LENGTH_SHORT).show();

                    /**
                     *从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名
                     */

                }else if(isExistUserName(userName)){
                    Toast.makeText(Register.this, "The ID has already exsited", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(Register.this, "Register successfully", Toast.LENGTH_SHORT).show();

                    //把账号、密码和账号标识保存到sp里面
                    /**
                     * 保存账号和密码到SharedPreferences中
                     */
                    saveRegisterInfo(userName, psw);
                    //注册成功后把账号传递到LoginActivity.java中
                    // 返回值到loginActivity显示
                    Intent data = new Intent();
                    data.putExtra("userName", userName);
                    setResult(RESULT_OK, data);
                    //RESULT_OK为Activity系统常量，状态码为-1，
                    // 表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值
                    Register.this.finish();
                }
            }
        });

//       处理返回主界面按钮

        register_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Register.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 获取控件中的字符串
     */
    private void getEditString(){
        userName=et_user_name.getText().toString().trim();
        psw=et_psw.getText().toString().trim();
        pswAgain=et_psw_again.getText().toString().trim();
    }
    /**
     * 从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名
     */
    private boolean isExistUserName(String userName){
        boolean has_userName=false;
        //mode_private SharedPreferences sp = getSharedPreferences( );
        // "loginInfo", MODE_PRIVATE
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取密码
        String spPsw=sp.getString(userName, "");//传入用户名获取密码
        //如果密码不为空则确实保存过这个用户名
        if(!TextUtils.isEmpty(spPsw)) {
            has_userName=true;
        }
        return has_userName;
    }

    /**
     * 保存账号和密码到SharedPreferences中SharedPreferences
     */

    private void saveRegisterInfo(String userName,String psw){
        String md5Psw = null;//把密码用MD5加密
        try {
            md5Psw = MD5.encrypt(psw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //loginInfo表示文件名, mode_private SharedPreferences sp = getSharedPreferences( );
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);

        //获取编辑器， SharedPreferences.Editor  editor -> sp.edit();
        SharedPreferences.Editor editor=sp.edit();
        //以用户名为key，密码为value保存在SharedPreferences中
        //key,value,如键值对，editor.putString(用户名，密码）;
        editor.putString(userName, md5Psw);
        //提交修改 editor.commit();
        editor.apply();
    }
}
