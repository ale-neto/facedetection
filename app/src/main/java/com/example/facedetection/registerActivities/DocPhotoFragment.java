package com.example.facedetection.registerActivities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.facedetection.LoadingDialog;
import com.example.facedetection.R;
import com.example.facedetection.doc.ReturnOcrActivity;
import com.example.facedetection.model.ocr.Document;
import com.example.facedetection.model.ocr.Post;
import com.example.facedetection.services.DocServices;
import com.example.facedetection.util.ImageUtil;
import com.example.facedetection.util.ImageUtilDoc;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class DocPhotoFragment extends Fragment implements SurfaceHolder.Callback {

    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    boolean previewing = false;
    Context context;
    DocServices docServices;
    String token;
    Retrofit retrofit;
    final LoadingDialog loadingDialog =  new LoadingDialog(getActivity());



    @BindView(R.id.preview_layout)
    LinearLayout previewLayout;

    @BindView(R.id.border_camera)
    View borderCamera;

    private OnFragmentInteractionListener mListener;

    Camera.Size previewSizeOptimal;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Bitmap bitmap);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
        Bundle data = getArguments();
        token = data.getString("token");



        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

         retrofit  = new Retrofit.Builder()
                .baseUrl("http://vmdev.tercepta.com.br:10002")
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_doc_photo, container, false);
        surfaceView = view.findViewById(R.id.camera_preview_surface);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        ButterKnife.bind(this, view);
        context = getContext();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        camera = Camera.open();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format, int width, int height) {
        if (previewing) {
            camera.stopPreview();
            previewing = false;
        }
        if (camera != null) {
            try {
                Camera.Parameters parameters = camera.getParameters();

                List<Camera.Size> previewSizes = parameters.getSupportedPreviewSizes();

                previewSizeOptimal = getOptimalPreviewSize(previewSizes, parameters.getPictureSize().width,
                        parameters.getPictureSize().height);

                //set parameters
                if (previewSizeOptimal != null) {
                    parameters.setPreviewSize(previewSizeOptimal.width, previewSizeOptimal.height);
                }

                if (camera.getParameters().getFocusMode().contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
                    parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                }
                if (camera.getParameters().getFlashMode().contains(Camera.Parameters.FLASH_MODE_AUTO)) {
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
                }

                camera.setParameters(parameters);

                //rotate screen, because camera sensor usually in landscape mode
                Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                if (display.getRotation() == Surface.ROTATION_0) {
                    camera.setDisplayOrientation(90);
                } else if (display.getRotation() == Surface.ROTATION_270) {
                    camera.setDisplayOrientation(180);
                }

                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
                previewing = true;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio = (double) w / h;
        if (sizes == null) return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        // Try to find an size match aspect ratio and size
        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        // Cannot find the one match the aspect ratio, ignore the requirement
        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        camera.stopPreview();
        camera.release();
        camera = null;
        previewing = false;
    }

    @OnClick(R.id.make_photo_button)
    void makePhoto() {
        if (camera != null) {
            camera.takePicture(myShutterCallback,
                    myPictureCallback_RAW, myPictureCallback_JPG);
        }
        LoadingDialog loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.startLoadingDialog();

    }

    Camera.ShutterCallback myShutterCallback = new Camera.ShutterCallback() {
        @Override
        public void onShutter() {

        }
    };

    Camera.PictureCallback myPictureCallback_RAW = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

        }
    };

    Camera.PictureCallback myPictureCallback_JPG = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmapPicture
                    = BitmapFactory.decodeByteArray(data, 0, data.length);


            Bitmap croppedBitmap = null;

            Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            if (display.getRotation() == Surface.ROTATION_0) {

                //rotate bitmap, because camera sensor usually in landscape mode
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                Bitmap rotatedBitmap = Bitmap.createBitmap(bitmapPicture, 0, 0, bitmapPicture.getWidth(), bitmapPicture.getHeight(), matrix, true);
                //save file
                //createImageFile(rotatedBitmap);


                //calculate aspect ratio
                float koefX = (float) rotatedBitmap.getWidth() / (float) previewLayout.getWidth();
                float koefY = (float) rotatedBitmap.getHeight() / (float) previewLayout.getHeight();

                //get viewfinder border size and position on the screen
                int x1 = borderCamera.getLeft();
                int y1 = borderCamera.getTop();

                int x2 = 1024;
                int y2 = 722;

                //calculate position and size for cropping
                int cropStartX = Math.round(x1 * koefX);
                int cropStartY = Math.round(y1 * koefY);

                int cropWidthX = Math.round(x2 * koefX);
                int cropHeightY = Math.round(y2 * koefY);

                //check limits and make crop
                if (cropStartX + cropWidthX <= rotatedBitmap.getWidth() && cropStartY + cropHeightY <= rotatedBitmap.getHeight()) {
                    croppedBitmap = Bitmap.createBitmap(rotatedBitmap, cropStartX, cropStartY, cropWidthX, cropHeightY);
                } else {
                    croppedBitmap = null;
                }

                //save result
                if (croppedBitmap != null) {
                    String photo = ImageUtilDoc.convert(croppedBitmap);
                    Bitmap bitmap = ImageUtil.convert(photo);
                    recognitionPost(createImageFile(bitmap));
                    //post(createImageFile(bitmap));
                    Log.i("Image", ImageUtilDoc.convert(croppedBitmap));
                }

            } else if (display.getRotation() == Surface.ROTATION_270) {
                // for Landscape mode
            }

            //pass to another fragment
            if (mListener != null) {
                if (croppedBitmap != null)
                    mListener.onFragmentInteraction(croppedBitmap);
            }

            if (camera != null) {
                camera.startPreview();
            }
        }
    };

    public File createImageFile(final Bitmap bitmap) {

        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        String timeStamp = new SimpleDateFormat("MMdd_HHmmssSSS").format(new Date());
        String imageFileName = "hap_" + timeStamp + ".jpg";
        final File file = new File(path, imageFileName);

        try {
            // Make sure the Pictures directory exists.
            if (path.mkdirs()) {
                Toast.makeText(context, "Not exist :" + path.getName(), Toast.LENGTH_SHORT).show();
            }

            OutputStream os = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);

            os.flush();
            os.close();
            Log.i("ExternalStorage", "Writed " + path + file.getName());
            // Tell the media scanner about the new file so that it is
            // immediately available to the user.
            MediaScannerConnection.scanFile(context,
                    new String[]{file.toString()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("ExternalStorage", "Scanned " + path + ":");
                            Log.i("ExternalStorage", "-> uri=" + uri);
                        }
                    });
            Toast.makeText(context, file.getName(), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            // Unable to create file, likely because external storage is
            // not currently mounted.
            Log.w("ExternalStorage", "Error writing " + file, e);
        }
        return file;
    }

    private void recognitionPost(File file) {

        RequestBody request = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("1-1", file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file))
                .build();

        docServices = retrofit.create(DocServices.class);
        Call<Post> call = docServices.post(request);
        call.enqueue(new Callback<Post>() {

            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                Post postResponse = response.body();

                String name =  "erro";
                if(postResponse.getOcrs().getResults() != null  || !postResponse.getOcrs().getResults().isEmpty() ){
                    for(Document document : postResponse.getOcrs().getResults()){
                       if(document.getName() == null || document.getName().isEmpty()){
                           name =  "Sua CNH não foi reconhecida, tente novamente!";
                       }else{
                           name = "Você foi reconhecido " + document.getName();
                       }
                    }
                }

                Intent in =  new Intent(getActivity(), ReturnOcrActivity.class);
                in.putExtra("result", name);
                startActivity(in);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.i("error",t.getMessage());
            }
        });
    }


    private void post(File file){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        MediaType mediaType = MediaType.parse("text/plain");

        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("1", file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file))
                .build();

        Request request = new Request.Builder()
                .url("http://vmdev.tercepta.com.br:10002/")
                .method("POST", body)
                .build();
        try {

            okhttp3.Response response = client.newCall(request).execute();

            Log.i("post",response.body().toString());
            int code = response.code();
            Log.i("Code","code " + code);

            String resStr = response.body().string();
            JSONObject json = new JSONObject(resStr);

            Log.i("Code", json.toString());

            Intent in =  new Intent(getActivity(), ReturnOcrActivity.class);
            in.putExtra("result", json.toString() );
            startActivity(in);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}
