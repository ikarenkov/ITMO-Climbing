package ru.climbing.itmo.itmoclimbing.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import ru.climbing.itmo.itmoclimbing.R;
import ru.climbing.itmo.itmoclimbing.cache.routes_and_athletes_cache.RoutesAndAthletesCache;
import ru.climbing.itmo.itmoclimbing.model.AthleteRouteResult;

/**
 * Created by Игорь on 27.12.2016.
 */

public class RouteDetailsFragment extends Fragment {
    public static final String TAG = RouteDetailsFragment.class.getSimpleName();
    public static final String Route_ID_TAG = "routeID";

    private int routeID;
    private ArrayList<AthleteRouteResult> athletesSolvedRoute;

    RoutesAndAthletesCache cache;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            routeID = getArguments().getInt(Route_ID_TAG);
        } else {
            // TODO: 27.12.2016
        }
        cache = new RoutesAndAthletesCache(getContext());
        try {
            athletesSolvedRoute = cache.getAthletesForRoute(routeID);
            Log.d(TAG, "onCreate: athletes  loaded from cashe");
        } catch (FileNotFoundException e) {
            athletesSolvedRoute = new ArrayList<>();
            Log.e(TAG, "onCreate: can not load athletes from cache", e);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_route_details, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public static RouteDetailsFragment newInstance(int routeID) {
        RouteDetailsFragment fragment = new RouteDetailsFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(Route_ID_TAG, routeID);
        fragment.setArguments(arguments);
        return fragment;
    }
}