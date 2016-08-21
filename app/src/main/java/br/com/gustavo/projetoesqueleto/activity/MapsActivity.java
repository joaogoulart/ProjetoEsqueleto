package br.com.gustavo.projetoesqueleto.activity;

import android.os.Bundle;
import android.provider.Settings;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import br.com.gustavo.projetoesqueleto.R;
import br.com.gustavo.projetoesqueleto.domain.Empresas;
import br.com.gustavo.projetoesqueleto.domain.Pais;
import br.com.gustavo.projetoesqueleto.domain.Service;

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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pais = (Pais) extras.getSerializable("pais");
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        startTask("taskPegarEmpresas", taskGetEmpresas(), R.id.progress);
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private TaskListener taskGetEmpresas() {
        return new TaskListener<List<Empresas>>() {

            List<Empresas> list;

            @Override
            public List<Empresas> execute() throws Exception {
                list = Service.getEmpresas(pais);
                return list;
            }

            @Override
            public void updateView(List<Empresas> response) throws IOException, Settings.SettingNotFoundException {
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (Empresas e : response) {
                    LatLng sydney = new LatLng(e.getLat(), e.getLng());
                    MarkerOptions title = new MarkerOptions().position(sydney).title(e.getNome());
                    mMap.addMarker(title);
                    builder.include(sydney);
                }

                LatLngBounds build = builder.build();
                mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(build, 0));
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(build, 0));
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
