package com.example.facedetection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.facedetection.model.PayLoad;
import com.example.facedetection.model.Picture;
import com.example.facedetection.model.Post;
import com.example.facedetection.services.RecognitionServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Result extends AppCompatActivity {

    private TextView tvResult;
    RecognitionServices recognitionS;
    Bundle resultB;
    private String result ;
    private List<PayLoad> payload;
    private List<Picture> picture;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent it = getIntent();
        resultB = it.getExtras();
        setContentView(R.layout.result);
        tvResult = findViewById(R.id.textView);

        /*String result = resultB.getString("result");*/

        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl("https://cluster.tercepta.com.br")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        recognitionS = retrofit.create(RecognitionServices.class);

        recognitionPost();

    }

    private void recognitionPost() {

        payload = new ArrayList<>();
        PayLoad payLoadAux = new PayLoad();
        picture = new ArrayList<>();
        Picture pictureAux =  new Picture();
        pictureAux.setContent("/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDABQODxIPDRQSEBIXFRQYHjIhHhwcHj0sLiQySUBMS0dARkVQWnNiUFVtVkVGZIhlbXd7gYKBTmCNl4x9lnN+gXz/2wBDARUXFx4aHjshITt8U0ZTfHx8fHx8fHx8fHx8fHx8fHx8fHx8fHx8fHx8fHx8fHx8fHx8fHx8fHx8fHx8fHx8fHz/wAARCAEsALcDASIAAhEBAxEB/8QAGwAAAQUBAQAAAAAAAAAAAAAAAAECAwQFBgf/xAA8EAABBAECBAMFBgMHBQAAAAABAAIDEQQhMQUSQVEiYXEGEzKBkRQjobHB0UJS8BY0U3KS4fEzYoOj0v/EABgBAAMBAQAAAAAAAAAAAAAAAAABAgME/8QAHhEBAQACAwEBAQEAAAAAAAAAAAECERIhMQNBE1H/2gAMAwEAAhEDEQA/AOsQhCzaFCVIlQQSpEqZFCEIQRUqRMmnix2c8zwxvn1TCRCyZ+PYzNIWukP0CpSe0cxd93DG1vnZQNV0aFzbfaHIDhzxRlvWrBWth8VxssUHcj/5XHf0TGl9CEJkEIQgBCEIAWJlG3OPmVtrByDus82vzUn7oQbc+huhQ0dIhIlVsioQhAKlSICZHBCRUOLcRbg4xoj3zhTB+qCLxHisOA3U88p2YD+fZcrn58udMXyHyAGw9FXJknkJ1c46kqxDia3J9Et6XMVYWdgU8RyEgcrj8lpRxRg7BWouUEaBLkrixXRTAfC7bsoQ57HAgnRdU3lI1ASScIx8ppLQGO7hVKmxW4Tx8giHLNt6P7eq6Vrg5oc02DqCuIz+FTYb7+Jh2cFtez/EnSVizusgeAnf0TiLG8hCFSQhCEAhWDkbLedsVgZGyzza/NWiHNO0ev5IRAQMht/1ohTGldEhIhUzKlSJUEVCRKmRskgijc92zRZXEZuVJnZTpH6knQdh2XWcYeWcLyCN+WvquRw2XKT2SqsYtwwiGP8A7julO6kTm8o3CzaoQSFYiJKlDIyPhH0UjGMGyYIwu6K7jzPbo7qo4g21cbGwgbKoztR5AE0ZY7UH8Fzhjdi5VsPK5htp9F0knhNVQWTxYAFrq6bpz0r46HDyG5WMyVvUajsVOsT2cmts0V6CnAfn+i21bOhCEJka80xx8lgZB0W9Kfun/wCUrAyFlm1+atEfvwhJCfvkJRpXSISJVTMqVIhBFQhCCVOLtc/hs7Wi3Fug+a5OEOha+x4r+i7DOB+w5FXYjcRR12XJtAdCCerQUqvFWdNKT8ZHoENdLdslB8naH8UrrN8rRTd02namtt04VqePKma7ldbT2IVn7WW6qGBzXtHOwPI0F9FekxKhbI+FpDjy20kUUtK30pO4rI19BqsQcYyGkF0LuU9QFSceRx92K8xuVBzAmyDd7qk7dSzOjlAa+mvWdxYm2jvaz4Huvwuu97VzPfz47eZpBBoEbeYS/R+LfsvZnmP8IZX4/wCy6RYXsvHUU8nQkN+n/K3VbO+hCEIIyc1BJ/lK5/JK3so1jv8ARYGTvqs823z8V4jUvyQo2u5Xk+SEo0dQEqRCbIqVIlTIJUiVAUuLZTsTE5mta7mPLTttlhMj5sYN05g0Gu+mq3eLRtk4fLz0A2nAnyKw3EUKU5LxnSjJGQdEjYnO6ClZcPMfNNFkgXr5JSq47Ox2gvY0CqNlbOVG8YDXMJGuoO3qsyEtirutyGaKbFMbiNRVFOXYs1HO5GM5khIbQJ/h6JkOFJI+2DXutZzTCadq6tR38wozPRuNtPPQJ7RxMbwmPHiEkshDydrUWZrgua1riSQbrTfur2PZfzzansdb9U7MPNEaFkuGnzCD00OE44xsCNgIJOrq7lXVncGMr4ZnzOt3vC30paKqXcZZTVCEJE0ocz+7P+X5rByd1uZxrHruQsHI3Kzy9b4eKTjRKFFM6nCtEJNHYJUiVUyKEqalTIqEIQStxOMS8PyGG9WH6jULnGm421uAAure0PY5jvhcCCuTew4+XLBdhriLU5LwNeaUXOWG6SZEvJsN1Xc57278o8lMi9po8p3NTq128lKc2RlCLU+qptgGwfR7kKzjNZG8EyajqW6KtFur7pZcmKM0bF7KWGIs8R3RDlxQOaX6abt2crAkjkFxnRGi2T3nlSgzpvd4+xJJAFJ0jg0G03CH2jMYNSGagBEFbvD2FmIzmYWOdbiDvqVZSDQJVbAISIQFXP8A+iP8ywsg6rb4gfumjzWHkHUqMvW2HjOnNOCEmQachEW7JKkSpsypU1LaAVKmpUEVYXGcVrXvyGAh1tc8nYgivw5R9VuqrnRCSLUWCCx3of8AcD5WiidVyMzeYprIqNuOigM72SGKQU5po+qnsvZYP0S8aSyn+6/lk+oU8eE1zSXZDWn0tZ5klboAD8lJG+QnVoBQrcpz2vY4tvmb3CuYL3Dfbsq5213SNyAzQXaPUXpelkt1dVucHxvcQOe6+Z5+lf72sfgsLsiR+Q4aA8sfW3d/luuna1rGhrRTQKA8lUiMqchIhNARaEiApcR+FnzWLPuVs8R2Z81iZG5WeXrbHxny6yV5ISu8eS1pAqkJxW3YWgEqu/MgjFSSta4dL1+izcnjhjk+6jHJdW/crkwwzy8RbI3AQUtrmpOOzknle1ldm/uoJONZLm0ZnC+wAK7ccMtdp5R1lpVxJ4nMQWmeVzb2Lyo5s+aUjnfI4dnm6VcS27kva0gOcATsCd1XmzcPke2SeItqnDmuwVw/v3EHxlM98SC4nW9EcS2tcYDX5JyI3BzXUHOAq3VRNeZVaDJ5fCTorENPh5X+IHXVVJsRzDceo81G55WmrO4utewmyQrTMmFjBdFYJD2mi1wR4+oKfEuVaORmB5LW6DsosWCTLl5W3ROrio8XEkyJQ2qHU9lvwRNxwI4hR2H6lFuvCk360uFyQYsfI94aGGm2Nz1P6fXutcOabAINb6rl86QY8Zd0aygPyVeHNkbiiSWyDqdfotccNzbPLLVdiDaFykfEaArmrl8ZaRXop4uLPZQaXMDtrBcj+Zc3SJFgO4tOQQJBR0BqiVLBmyROLi9zgejjoj+dHOLfEd2+hWJOdStPIyBkVpykClm5LHNFkad1jljY3xyl6UWOAy2k9L/JCrzOIlPKSEKVdJ8h7Xvc8/ESST5qs6Rzm8jjdbIc61E/WiNwulzn8/X6pCdUwnW+hTeax6aIIrjRJCbJq2+yRxSE2CEGa3r1Q01omglJfiSC9jO+7afUfip7tVIfFAW+qkicSyjuNFz2OiVPQ6hK1tprXWE8OSNex+WJljcqXHN+M9dvRUA7mpt7q4JWtaSdh2QVQ8Wk+4c0bkAfiszNIYY4214WgeasTSCfJY01vzO8v60VNh+0ZvMdrtdkmppyW7u10D3OO1hPiOpUkktNDR4SR9Aq/vRJKXH4Ga+qjbLzutx+N30AVbTpoNd941o0a1tn9lP74EWFnOlDSQTru79krHueQzyt2uyomjFNdkmm9FOyTTXUHoVnNk1AA0CnY9BGZnDhKTJj6OO7CdPkhWmPNalCzvzlaz65RgE667JjjacaKjtIyc2mqS9Smu/iHlaRpsIBxKQIOyRANqikPxBOG5SO0opBLjvpxB2OinrllurDtFVboSVbieCAb2WOU122wu5pLSW6Ti29U3k5nVenVQs9p5RzIL/CXPNAC/RMLmlzWt1F6Vsoc2SyIWafzLo+eHHusM899QwSH3Msx+KQ8o9FHC7lY89XafJLkmi2MbMFfNMa4BrR1Vs4me7lxgOr3fgE2JwErL2tMldq0DYBMJqijZpHS24uPU2rEMnJGST4iqV6gFSc5RKLF6N/f6K0x/4LLZJrqdPzWjYYGq5WdidrnO30CEkZ0QqJku3TCVZmhLTY1BVXrSys012jcfHXkmx/AlPxJG/CAkDklmwBqSlTCadY3GyVOFcCx9O0PqnP1anxuE7yXgX6K7BhwzQSmiHhpIIKW18d+M6KveNDtiaKuz4joqfBZHVZ");
        picture.add(pictureAux);
        payLoadAux.setPictures(picture);
        payLoadAux.setBirthDate("05/06/1997");
        payload.add(payLoadAux);

        final Post post = new Post(2, payload, 3,3, null, "05/06/1997", "05/05/2020");

        Call<Post> call = recognitionS.recognitionPost(post);
        call.enqueue(new Callback<Post>() {

            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                int code = response.code();
                Post postResponse = response.body();
                Log.i("POST", "POST" + post);
                Log.i("HTTP", "code" + code);
                Log.i("RETORNO", "deu certo" + postResponse);



            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
               Log.i("error",t.getMessage());
            }
        });
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
