package com.example.finalprojectmobile;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class FeedbackFragment extends Fragment {

    private Button btn_feedback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_feedback, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        btn_feedback = (Button) root.findViewById(R.id.btn_feedbackform);

        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeedbackFormActivity.class);
                startActivity(intent);
            }
        });

        return root;

    }
}
