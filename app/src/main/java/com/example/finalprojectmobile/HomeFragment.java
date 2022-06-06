package com.example.finalprojectmobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView featuredLocation;
    RecyclerView.Adapter adapter;
    ImageView restaurant, airports, hotels, shops;
    VideoView video;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        featuredLocation = root.findViewById(R.id.featured_location);

        featuredLocation();

        restaurant = root.findViewById(R.id.img_restaurant);
        airports = root.findViewById(R.id.img_airports);
        hotels = root.findViewById(R.id.img_hotels);
        shops = root.findViewById(R.id.img_shops);
        video = root.findViewById(R.id.vid_home);

        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://xmureporters.wordpress.com/2011/05/02/xiamen-food-hunting/"));
                startActivity(intent);
            }
        });

        airports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.travelmath.com/nearest-airport/Xiamen,+China"));
                startActivity(intent);
            }
        });

        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.booking.com/city/cn/xiamen.en-gb.html?aid=356980;label=gog235jc-1DCAMoMUIGeGlhbWVuSDNYA2ihAYgBAZgBCbgBF8gBDNgBA-gBAYgCAagCA7gC-p3ejgbAAgHSAiQ0NTFhZjY0Ni1kMGZiLTQ4NTctOTE1Ni00OTdjZGUwNTViMjfYAgTgAgE;sid=3b2cd265c323c35bbe18c422d7203566;inac=0&keep_landing=1&"));
                startActivity(intent);
            }
        });

        shops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.topchinatravel.com/china-shopping/top-shopping-places-in-xiamen.htm"));
                startActivity(intent);
            }
        });

        MediaController mediaController= new MediaController(getActivity());
        mediaController.setAnchorView(video);

        //specify the location of media file
        String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.home_video;
        Uri uri = Uri.parse(videoPath);

        //Setting MediaController and URI, then starting the videoView
        video.setMediaController(mediaController);
        video.setVideoURI(uri);
        video.requestFocus();
        video.start();

        return root;

    }

    private void featuredLocation() {

        featuredLocation.setHasFixedSize(true);
        featuredLocation.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.gulangyu_island, "Gulanggu Island", "Tiny island located on the estuary of the Chiu-lung River, facing the city of Xiamen"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.xiamen_botanical_garden, "Xiamen Botanical Garden", "Integral part of the Gulangyu Islet"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.xiamen_university, "Xiamen University", "One of China's most beautiful universities"));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredLocation.setAdapter(adapter);
    }

}
