//package com.example.geofence;
//
//import android.Manifest;
//import android.app.AlertDialog;
//import android.app.Service;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.os.IBinder;
//import android.provider.Settings;
//import android.util.Log;
//
//import java.util.logging.Logger;
//
//public class GpsTracker extends Service implements LocationListener {
//
//    //Instance of context
//    private final Context mContext;
//
//    //Flag for GPS status
//    boolean isGPSEnabled = false;
//
//    //Flag for network status
//    boolean isNetworkEnabled = false;
//
//    //Flag for GPS status
//    boolean canGetLocation = false;
//
//    //Location
//    Location location;
//
//    //Latitude
//    double latitude;
//
//    //Longitude
//    double longitude;
//
//    // The minimum distance to change Updates in meters
//    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
//
//    // The minimum time between updates in milliseconds
//    private static final long MIN_TIME_BW_UPDATES = 100000 * 60 * 1; // 100 minute
//
//    // Declaring a Location Manager
//    protected LocationManager locationManager;
//
//    private PermissionHandler permissionHandler;
//
//    //Constructor of the class
//    public GpsTracker(Context context) {
//        this.mContext = context;
//        permissionHandler = new PermissionHandler();
//        getLocation();
//    }
//
//    /**
//     * Method used to get current location
//     *
//     * @return Current location.
//     */
//    public Location getLocation() {
//        try {
//            if (permissionHandler.isPermissionAllowed(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) &&
//                    permissionHandler.isPermissionAllowed(mContext, Manifest.permission.ACCESS_FINE_LOCATION)) {
//                locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
//                // getting GPS status
//                isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//                // getting network status
//                isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//
//                if (!isGPSEnabled && !isNetworkEnabled) {
//                    //showSettingsAlert();
//                } else {
//                    this.canGetLocation = true;
//                    if (isNetworkEnabled) {
//
//                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES,
//                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
//
//                        Log.d("Network", "Network");
//                        if (locationManager != null) {
//                            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                            if (location != null) {
//                                latitude = location.getLatitude();
//                                longitude = location.getLongitude();
//                            }
//                        }
//                    }
//                    // if GPS Enabled get lat/long using GPS Services
//                    if (isGPSEnabled) {
//                        if (location == null) {
//                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,
//                                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
//                            Log.d("GPS Enabled", "GPS Enabled");
//                            if (locationManager != null) {
//                                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                                if (location != null) {
//                                    latitude = location.getLatitude();
//                                    longitude = location.getLongitude();
//                                }
//                            }
//                        }
//                    }
//                }
//            } else {
//                Logger.toastMessage(mContext, mContext.getString(R.string.msg_enable_location_permission));
//            }
//        } catch (Exception e) {
//            Logger.exceptionHandler(e);
//        }
//        return location;
//    }
//
//    /**
//     * Function to get latitude
//     */
//    public double getLatitude() {
//        if (location != null)
//            latitude = location.getLatitude();
//        return latitude;
//    }
//
//    /**
//     * Function to get longitude
//     */
//    public double getLongitude() {
//        if (location != null)
//            longitude = location.getLongitude();
//        return longitude;
//    }
//
//    /**
//     * Function to check GPS/wifi enabled
//     *
//     * @return boolean
//     */
//    public boolean canGetLocation() {
//        return this.canGetLocation;
//    }
//
//    /**
//     * Function to show settings alert dialog On pressing Settings button will lauch Settings Options
//     */
//    public void showSettingsAlert() {
//
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
//        // Setting Dialog Title
//        alertDialog.setTitle(mContext.getResources().getString(R.string.gps_settings_alert));
//        // Setting Dialog Message
//        alertDialog.setMessage(mContext.getResources().getString(R.string.enable_gps));
//
//        // On pressing Settings button
//        alertDialog.setPositiveButton(mContext.getResources().getString(R.string.gps_settings), new DialogInterface
//                .OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                mContext.startActivity(intent);
//            }
//        });
//
//        // on pressing cancel button
//        alertDialog.setNegativeButton(mContext.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//
//        // Showing Alert Message
//        alertDialog.show();
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//    }
//
//    @Override
//    public IBinder onBind(Intent arg0) {
//        return null;
//    }
//
//}
//
