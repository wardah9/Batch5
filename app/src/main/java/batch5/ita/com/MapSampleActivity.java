package batch5.ita.com;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapSampleActivity extends FragmentActivity implements OnMapReadyCallback , LocationListener{

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_sample);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


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

        // Add a marker in Sydney and move the camera

        LatLng sydney = new LatLng(25, 56);

        mMap.addMarker(new MarkerOptions()
                .position(sydney)
               // .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker))
                .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_big_on))
                .title("Marker in Sydney"));

        LatLng TutorialsPoint = new LatLng(21, 57);

        mMap.addMarker(new MarkerOptions()
                .position(TutorialsPoint)
                .snippet("Thinking of finding some thing...")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .title("Tutorialspoint.com")
        );

        mMap.moveCamera(CameraUpdateFactory.newLatLng(TutorialsPoint));
    }

    @Override
    public void onLocationChanged(Location location) {

        mMap.clear();
        LatLng myLoc = new LatLng (location.getLatitude(), location.getLongitude());

        mMap.addMarker(new MarkerOptions().position(myLoc).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
