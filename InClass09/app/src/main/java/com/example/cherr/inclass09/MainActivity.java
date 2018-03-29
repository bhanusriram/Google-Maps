package com.example.cherr.inclass09;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mMap;
    LatLng start_point,end_point;
    Polyline pl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MainActivity.this);

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String json="";
        Gson gson=new Gson();
        InputStream is=this.getResources().openRawResource(R.raw.trip);
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        int size = 0;
        byte[] buffer = new byte[1024];
        try {
            while((size=is.read(buffer,0,1024))>=0){
                outputStream.write(buffer,0,size);
            }
            is.close();
            json = outputStream.toString();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("demo", "onMapReady: "+json.toString());
        Trip trip = gson.fromJson(json,Trip.class);
        Log.d("demo", "onMapReady: "+trip.toString());
        PolylineOptions polylineOptions=new PolylineOptions();
        LatLngBounds.Builder builder = LatLngBounds.builder();
        List<points> pointsList=trip.getPointsList();
        Log.d("demo", "onMapReady: "+pointsList.size());
        for(int i=0;i<pointsList.size();i++) {
            points p = pointsList.get(i);
            if (i == 0) {
                start_point = new LatLng(p.getLatitude(), p.getLongitude());
                builder.include(start_point);
                mMap.addMarker(new MarkerOptions().position(start_point).title("Start Location"));
                polylineOptions.add(new LatLng(p.getLatitude(), p.getLongitude()));
            } else if (i == pointsList.size() - 1) {
                end_point = new LatLng(p.getLatitude(), p.getLongitude());
                builder.include(end_point);
                mMap.addMarker(new MarkerOptions().position(end_point).title("End Location"));
                polylineOptions.add(new LatLng(p.getLatitude(), p.getLongitude()));
            } else {

                builder.include(new LatLng(p.getLatitude(), p.getLongitude()));
                polylineOptions.add(new LatLng(p.getLatitude(), p.getLongitude()));
            }
        }
        mMap.addPolyline(polylineOptions.width(5).color(Color.BLUE));
        final LatLngBounds bounds = builder.build();
        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
            }
        });
    }
}
