package com.abovis.paginasamarillasapp.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abovis.paginasamarillasapp.R;
import com.abovis.paginasamarillasapp.models.Paginas;
import com.abovis.paginasamarillasapp.repositories.PaginasRepository;

public class DetailPaginaActivity extends AppCompatActivity {
    private Integer id;
    private ImageView pictureImage;
    private TextView nameText;
    private TextView direccionText;
    private TextView telefonoText;
    private TextView detalleText;
    private Button callButton;
    private Button urlButton;
    private Button smsButton;
    private Button emailButton;
    private Paginas paginas;
    private static final int CALL_PERMISSION_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pagina);

        pictureImage = findViewById(R.id.picture_image);
        nameText = findViewById(R.id.name_text);
        direccionText = findViewById(R.id.direccion_text);
        telefonoText = findViewById(R.id.telefono_text);
        detalleText = findViewById(R.id.detalle_text);
        callButton = findViewById(R.id.call_button);
        urlButton = findViewById(R.id.url_button);
        smsButton = findViewById(R.id.sms_button);
        emailButton = findViewById(R.id.email_button);

        this.id = getIntent().getExtras().getInt("id");

        paginas = PaginasRepository.getPaginasById(id);

        if(paginas != null) {
            int resourceid = getResources().getIdentifier(
                    paginas.getLogo(),
                    "drawable",
                    getPackageName()
            );
            pictureImage.setImageResource(resourceid);
            nameText.setText(paginas.getName());
            direccionText.setText(paginas.getAddress());
            telefonoText.setText(paginas.getPhone());
            detalleText.setText(paginas.getInfo());

            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    call();
                }
            });
            urlButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view();
                }
            });
            smsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sms();
                }
            });
            emailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    email();
                }
            });

        }
    }
    private void call(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION_REQUEST);
            return;
        }
        String phonenumber = telefonoText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phonenumber));
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    private void view(){
        String url = paginas.getUrl();
        if(!url.startsWith("http://") || !url.startsWith("https://")) {
            url = "http://" + url;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }
    private void sms(){
            String phonenumber = telefonoText.getText().toString();
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:" + phonenumber));  // This ensures only SMS apps respond
            intent.putExtra("sms_body","");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
    }

    private void email(){
            String email = paginas.getEmail();
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"+ email)); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, "");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Consulta");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CALL_PERMISSION_REQUEST) {
            if(permissions[0].equals(Manifest.permission.CALL_PHONE)) {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permisos otorgados!!!", Toast.LENGTH_SHORT).show();
                    call();
                }
            }
        }
    }

}
