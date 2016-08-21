package br.com.gustavo.projetoesqueleto.activity;

import android.os.Bundle;
import android.provider.Settings;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.io.IOException;

import br.com.gustavo.projetoesqueleto.R;
import br.com.gustavo.projetoesqueleto.domain.Pais;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Pais pais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        startTask("taskPegarEmpresas", taskGetEmpresas());
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private TaskListener taskGetEmpresas() {
        return new TaskListener() {
            @Override
            public Object execute() throws Exception {

                return null;
            }

            @Override
            public void updateView(Object response) throws IOException, Settings.SettingNotFoundException {

            }

            @Override
            public void onError(Exception exception) {

            }

            @Override
            public void onCancelled(String cod) {

            }
        };
    }
}
