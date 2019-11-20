package com.example.envirometrics;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.heinrichreimersoftware.materialintro.app.NavigationPolicy;
import com.heinrichreimersoftware.materialintro.app.OnNavigationBlockedListener;
import com.heinrichreimersoftware.materialintro.slide.ButtonCtaSlide;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;


public class IntroActivity extends com.heinrichreimersoftware.materialintro.app.IntroActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checking for first time launch - before calling setContentView()

        addSlide(new SimpleSlide.Builder()
                .title("¿Qué es Envirometrics?")
                .description("Envirometrics te permite viajar en taxi por un trayecto con menos contaminación.")
                .image(R.drawable.taxi_env)
                .background(R.color.colorPrimaryDark)
                .backgroundDark(R.color.colorPrimary)
                .scrollable(false)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Mapa")
                .description("Un mapa con toda la información necesaria sobre la contaminación de tu ciudad.")
                .image(R.drawable.mapa_intro)
                .background(R.color.colorPrimaryDark)
                .backgroundDark(R.color.colorPrimary)
                .scrollable(false)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Análisis de contaminación")
                .description("Con simplemente hacer una foto a tu entorno envirometrics te dirá cuanta contaminación hay.")
                .image(R.drawable.foto_cont)
                .background(R.color.colorPrimaryDark)
                .backgroundDark(R.color.colorPrimary)
                .scrollable(false)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Resumen del día")
                .description("¿Quieres saber cuanta contaminación has respirado en un día? Envirometrics te muestra un resumen de tu día con todo tipo de detalles.")
                .image(R.drawable.grafico)
                .background(R.color.colorPrimaryDark)
                .backgroundDark(R.color.colorPrimary)
                .scrollable(false)
                .build());

        /* Show/hide button */
        setButtonBackVisible(false);

        setNavigationPolicy(new NavigationPolicy() {
            @Override public boolean canGoForward(int position) {
                if(position==1){
                    pedirPermisoGPS();

                    if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED) {
                        return true;
                    }
                }
                return true;
            }

            @Override public boolean canGoBackward(int position) {
                return true;
            }
        });


    }

    //Funcion para comprobar y pedir los permisos de GPS y en caso de tenerlos, pedir los del BT
    public void pedirPermisoGPS(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED //&&
            /*ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED*/) {
            ActivityCompat.requestPermissions(this, new  String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 3);
        }
    }

}