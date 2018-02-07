package br.ufrpe.beela.lugar.gui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;

public class GoogleMapsActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener  {
    private Bundle bundle;
    private LocationManager locationManager;
    private static final int REQUEST_FINE_LOCATION = 1;

    private GoogleMap mMap;
    private ArrayList<Lugar> recuperado;
    private View popup = null;
    String TYPE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        bundle = getIntent().getExtras();
        recuperado = (ArrayList<Lugar>) bundle.getSerializable("lugarmaps");


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        updateLocation();

        infoWindowPerso();
        createMarkers();

    }

    //-----------------------------------
    private void checkPermission() {
        boolean permissionFineLocation = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED;
        boolean permissionCoarseLocation = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED;

        if (permissionFineLocation && permissionCoarseLocation) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_FINE_LOCATION: {
                updateLocation();
            }
        }
    }

    public void updateLocation() {
        checkPermission();
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED /*&& ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED*/) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            goToCurrentLocation(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            mMap.setMyLocationEnabled(true);
            goToCurrentLocation(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }
    }




    public void goToCurrentLocation(Location location){
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.0f));}





    //----------------------------------






    public void infoWindowPerso() {
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                if (popup == null) {
                    popup = getLayoutInflater().inflate(R.layout.activity_info_window, null);
                }

                TextView textView = popup.findViewById(R.id.titleInfoWindow);
                ImageView imageView = popup.findViewById(R.id.iconRestaurant);
                TextView snippet = popup.findViewById(R.id.snippet);

                imageView.setImageResource(R.mipmap.ic_launcher);
                textView.setText(marker.getTitle());
                snippet.setText(TYPE + marker.getSnippet());

                return popup;
            }
        });
    }



    public void createMarkers() {
        ArrayList<Lugar> listaLugares = recuperado;
        for (int i = 0; i < listaLugares.size(); i++) {
            Lugar lugar = listaLugares.get(i);
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(lugar.getLatLng())
                    .title(lugar.getNome())
                    .snippet(lugar.getDescricao()));
            // separationMarkers(marker,lugar);
            marker.setTag(lugar);


        }
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return true;
    }

    @Override
    public void onInfoWindowClick (Marker marker){
        Lugar lugar = (Lugar) marker.getTag();
        //restaurantBusiness.selectRestaurant(restaurant);
        Intent intent = new Intent(this, LugarDetalhes.class);
        intent.putExtra("lugar", (Serializable) lugar);

        startActivity(intent);
        finish();

        }






    private void separationMarkers(Marker marker, Lugar lugar) {
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}