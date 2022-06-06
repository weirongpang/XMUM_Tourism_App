package com.example.finalprojectmobile;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class RewardFragment extends Fragment {
    public TextView totalPoint;
    public TextView redemptionCount1, redemptionCount2, redemptionCount3, redemptionCount4, redemptionCount5;
    public Button btnRedeem1, btnRedeem2, btnRedeem3, btnRedeem4, btnRedeem5;
    public ScrollView scrollView;

    public static int  points = 1000;
    public int quizPoint = 0;
    public static int redeemPoint1 = 200, redeemPoint2 = 300, redeemPoint3 = 400, redeemPoint4 = 500, redeemPoint5 = 200;
    public static int redeemCount1 = 2, redeemCount2 = 3, redeemCount3 = 2, redeemCount4 = 3, redeemCount5 = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_reward, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        totalPoint = root.findViewById(R.id.tv_totalPoint);
        scrollView = root.findViewById(R.id.scrollView1);

        scrollView.fullScroll(View.FOCUS_DOWN);
        scrollView.setSmoothScrollingEnabled(true);

        redemptionCount1 = root.findViewById(R.id.redemption_count1);
        redemptionCount2 = root.findViewById(R.id.redemption_count2);
        redemptionCount3 = root.findViewById(R.id.redemption_count3);
        redemptionCount4 = root.findViewById(R.id.redemption_count4);
        redemptionCount5 = root.findViewById(R.id.redemption_count5);

        btnRedeem1 = root.findViewById(R.id.btn_redeem1);
        btnRedeem2 = root.findViewById(R.id.btn_redeem2);
        btnRedeem3 = root.findViewById(R.id.btn_redeem3);
        btnRedeem4 = root.findViewById(R.id.btn_redeem4);
        btnRedeem5 = root.findViewById(R.id.btn_redeem5);

        totalPoint.setText(String.valueOf(points)+(" pts"));

        redemptionCount1.setText(("Redemption(s) Left: ") + String.valueOf(redeemCount1));
        redemptionCount2.setText(("Redemption(s) Left: ") + String.valueOf(redeemCount2));
        redemptionCount3.setText(("Redemption(s) Left: ") + String.valueOf(redeemCount3));
        redemptionCount4.setText(("Redemption(s) Left: ") + String.valueOf(redeemCount4));
        redemptionCount5.setText(("Redemption(s) Left: ") + String.valueOf(redeemCount5));

        btnRedeem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean result = checkRedemption(points, redeemPoint1,redeemCount1);
                if(result)
                {
                    redeemCount1--;
                    redemptionCount1.setText(("Redemption(s) Left: ") + String.valueOf(redeemCount1));
                    successRedeem();
                    if(redeemCount1 <= 0)
                    {
                        btnRedeem1.setEnabled(false);
                    }
                }
            }
        });

        btnRedeem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean result = checkRedemption(points, redeemPoint2,redeemCount2);
                if(result)
                {
                    redeemCount2--;
                    redemptionCount2.setText((" Redemption(s) Left: ") + String.valueOf(redeemCount2));
                    successRedeem();
                    if(redeemCount2 <= 0)
                    {
                        btnRedeem2.setEnabled(false);
                    }
                }
            }
        });

        btnRedeem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean result = checkRedemption(points, redeemPoint3,redeemCount3);
                if(result)
                {
                    redeemCount3--;
                    redemptionCount3.setText(("Redemption(s) Left: ") + String.valueOf(redeemCount3));
                    successRedeem();
                    if(redeemCount3 <= 0)
                    {
                        btnRedeem3.setEnabled(false);
                    }
                }
            }
        });

        btnRedeem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean result = checkRedemption(points, redeemPoint4,redeemCount4);
                if(result)
                {
                    redeemCount4--;
                    redemptionCount4.setText(("Redemption(s) Left: ") + String.valueOf(redeemCount4) );
                    successRedeem();
                    if(redeemCount4 <= 0)
                    {
                        btnRedeem2.setEnabled(false);
                    }
                }
            }
        });

        btnRedeem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean result = checkRedemption(points, redeemPoint5,redeemCount5);
                if(result)
                {
                    redeemCount5--;
                    redemptionCount5.setText(("Redemption(s) Left: ") + String.valueOf(redeemCount5));
                    successRedeem();
                    if(redeemCount5 <= 0)
                    {
                        btnRedeem5.setEnabled(false);
                    }
                }
            }
        });
        return root;
    }
    public Boolean checkRedemption(int currentPoints, int rewardPoints, int redeemCountLeft)
    {
        if (redeemCountLeft > 0 && currentPoints >= rewardPoints)
        {
            points = currentPoints - rewardPoints;
            totalPoint.setText(String.valueOf(points)+(" pts"));
            return true;
        }
        else
        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setMessage("Your points is not enough to choose this rewards")
                    .setCancelable(true)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return false;
        }
    }

    public void successRedeem()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Successful")
                .setCancelable(true)
                .setMessage("Your voucher has successfully claimed. It will send to your mail box.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}

