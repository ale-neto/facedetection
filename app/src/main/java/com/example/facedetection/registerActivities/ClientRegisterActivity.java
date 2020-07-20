package com.example.facedetection.registerActivities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;


import com.example.facedetection.R;

import com.example.facedetection.model.register.Documents;
import com.example.facedetection.model.register.PayLoadR;
import com.example.facedetection.model.register.Register;
import com.example.facedetection.services.RecognitionServices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ClientRegisterActivity extends AppCompatActivity {

    EditText etName, etMotherName, etBirthDate;
    Button btnRegister;
    private Register register;
    private String returnRegister;
    private String reposta;
    Date dataHoraAtual = new Date();
    String data = new SimpleDateFormat("MM/dd/yyyy"). format(dataHoraAtual);
    RecognitionServices recognitionS;
    String token;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_register);

        Bundle resultB = getIntent().getExtras();
        token = resultB.getString("token");

        register = (Register) getIntent().getSerializableExtra("register");

        etName = findViewById(R.id.et_Name);
        etMotherName = findViewById(R.id.et_MotherName);
        btnRegister = findViewById(R.id.btnRegister);

        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl("https://cluster.tercepta.com.br")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        recognitionS = retrofit.create(RecognitionServices.class);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(PayLoadR payLoadR : getRegister().getPayload()){
                    payLoadR.setName(etName.getText().toString());
                    payLoadR.setBirthDate("12/22/1993");
                    payLoadR.setScopedId(1001112233);
                    payLoadR.setGender(0);
                    payLoadR.setCustomerId(3);
                    payLoadR.setMotherName(etMotherName.getText().toString());
                    Documents documents = new Documents();
                    List<Documents> docs  = new ArrayList<>();
                    documents.setDocumentTypeId(1);
                    documents.setValue(001112233);
                    docs.add(documents);
                    payLoadR.setDocuments(docs);
                }

                getRegister().setEventDateTime(data);
                reposta = registerClient(register);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String teste = reposta;
                        Intent it = new Intent(ClientRegisterActivity.this, ReturnRegisterActivity.class);
                        Bundle result =  new Bundle();
                        result.putString("result", teste);
                        it.putExtras(result);
                        startActivity(it);
                    }
                },9000);

            }
        });

    }

    private String registerClient(Register register) {

        final Register registerP = new Register(0, register.getPayload(), 3, 3,null, register.getEventDateTime(), 999);

        Call<Register> call = recognitionS.registerFace("Bearer "+ token ,registerP);
        call.enqueue(new Callback<Register>() {

            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {

                int code = response.code();
                Register postResponse = response.body();
                Log.i("ENVIO", "code" + registerP);
                Log.i("POST", "POST" + registerP);
                Log.i("HTTP", "code" + code);
                Log.i("RETORNO", "deu certo" + postResponse);
                
                String name = null;
                for(PayLoadR payLoadR : postResponse.getPayload()){
                    name = payLoadR.getName();
                }
                
                if (postResponse.isSuccess() == true){
                    setReposta("Seja bem-vindo " + name);
                }else{
                    setReposta("Não deu certo. -");
                }


            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Log.i("error",t.getMessage());

            }
        });

    return reposta;
    }


    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public String getReturnRegister() {
        return returnRegister;
    }

    public void setReturnRegister(String returnRegister) {
        this.returnRegister = returnRegister;
    }

    public String getReposta() {
        return reposta;
    }

    public void setReposta(String reposta) {
        this.reposta = reposta;
    }
}
