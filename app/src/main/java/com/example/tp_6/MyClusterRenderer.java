package com.example.tp_6;

import android.content.Context;

import com.example.tp_6.model.ClusterItemClass;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

public class MyClusterRenderer extends DefaultClusterRenderer<ClusterItemClass> {
    public MyClusterRenderer(Context context, GoogleMap map, ClusterManager<ClusterItemClass> clusterManager) {
        super(context, map, clusterManager);
    }

    @Override
    protected void onBeforeClusterItemRendered(ClusterItemClass item, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);
        MarkerOptions itemMarkerOptions = item.getMarkerOptions();
        if (itemMarkerOptions != null) {
            markerOptions.icon(itemMarkerOptions.getIcon());
        }
    }
}

