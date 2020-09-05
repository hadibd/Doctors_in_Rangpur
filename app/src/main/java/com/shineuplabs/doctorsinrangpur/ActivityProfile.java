package com.shineuplabs.doctorsinrangpur;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ActivityProfile extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    TextView NameTv, SpTv, DayTv, TimeTv, EduTv, WorkTv, PhoneTv, ChamberTv;
    Button SerialBtn, ExpandBtn;
    ImageView CollapseBtn, VisitingCard;
    private BottomSheetBehavior mBottomSheetBehavior;
    String CardUrl;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setUpMenu();

        NameTv = findViewById(R.id.NameTv);
        SpTv = findViewById(R.id.SpTv);
        DayTv = findViewById(R.id.DayTv);
        TimeTv = findViewById(R.id.TimeTv);
        EduTv = findViewById(R.id.EduTv);
        WorkTv = findViewById(R.id.WorkTv);
        PhoneTv = findViewById(R.id.PhoneTv);
        ChamberTv = findViewById(R.id.ChamberTv);
        SerialBtn = findViewById(R.id.SerialBtnId);
        VisitingCard = findViewById(R.id.VisitingCardImageId);
        ExpandBtn = findViewById(R.id.ExpandBtn);
        CollapseBtn = findViewById(R.id.CollapseBtn);
        View bottomSheet = findViewById(R.id.bottom_sheet);
        progressBar = findViewById(R.id.ProgressBarId);

        String Path = getIntent().getStringExtra("PATH");

        assert Path != null;
        db.document(Path)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        final ModelDoctorProfile profile = documentSnapshot.toObject(ModelDoctorProfile.class);
                        NameTv.setText(profile.getName());
                        SpTv.setText(profile.getSp());
                        DayTv.setText(profile.getDay());
                        TimeTv.setText(profile.getTime());
                        EduTv.setText(profile.getEdu());
                        WorkTv.setText(profile.getJob());
                        if (profile.getContact().isEmpty()){
                            PhoneTv.setText(R.string.personal_number_not_found);
                        }else {
                            PhoneTv.setText(profile.getContact());
                        }
                        ChamberTv.setText(profile.getChamber());
                        SerialBtn.setText(profile.getSerial());
                        SerialBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", profile.getSerial(), null));
                                startActivity(intent);
                            }
                        });
                        CardUrl = profile.getCard();
                    }
                });

        // Bottom Sheet

        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        ExpandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                if (CardUrl.isEmpty()){
                    CardUrl = "wwww";
                }
                Picasso.get().load(CardUrl).into(VisitingCard, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }


                    @Override
                    public void onError(Exception e) {
                        progressBar.setVisibility(View.GONE);
                        VisitingCard.setImageResource(R.drawable.oops_card);
                    }
                });
            }
        });
        CollapseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }

    private void setUpMenu() {
        // Home Button
        findViewById(R.id.homeBtnId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActivityMain.class));
            }
        });
        // Menu Button
        findViewById(R.id.popUpMenuId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(ActivityProfile.this, view);
                popup.getMenuInflater().inflate(R.menu.pop_up_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.about_us:
                                startActivity(new Intent(getApplicationContext(), ActivityAboutUs.class));
                                return true;
                            case R.id.doctor_request:
                                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(getString(R.string.form_link_doctor_request))));
                                return true;
                            case R.id.problem_report:
                                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(getString(R.string.form_link_problem_report))));
                                return true;
                            case R.id.add_doctor:
                                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(getString(R.string.form_link_add_doctor))));
                                return true;
                            case R.id.privacy_policy:
                                Dialog dialog = new Dialog(ActivityProfile.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                                dialog.setContentView(R.layout.tos_layout);
                                dialog.show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                popup.show();

            }
        });

    }   // setUpMenu method end here

}