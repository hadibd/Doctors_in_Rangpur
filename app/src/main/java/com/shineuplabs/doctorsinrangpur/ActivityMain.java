package com.shineuplabs.doctorsinrangpur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class ActivityMain extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpMenu();
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

                PopupMenu popup = new PopupMenu(ActivityMain.this, view);
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
                                Dialog dialog = new Dialog(ActivityMain.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
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

    public void Neurologist(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityDoctorListBySp.class);
        intent.putExtra("TAG", "Neurologist");
        startActivity(intent);
    }

    public void Oncologist(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityDoctorListBySp.class);
        intent.putExtra("TAG", "Oncologist");
        startActivity(intent);
    }

    public void Medicine(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityDoctorListBySp.class);
        intent.putExtra("TAG", "Medicine");
        startActivity(intent);
    }

    public void Gynecologist(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityDoctorListBySp.class);
        intent.putExtra("TAG", "Gynecologist");
        startActivity(intent);
    }

    public void Surgeon(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityDoctorListBySp.class);
        intent.putExtra("TAG", "Surgeon");
        startActivity(intent);
    }

    public void Otolaryngologist(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityDoctorListBySp.class);
        intent.putExtra("TAG", "Otolaryngologist");
        startActivity(intent);
    }

    public void Orthopedist(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityDoctorListBySp.class);
        intent.putExtra("TAG", "Orthopedic");
        startActivity(intent);
    }

    public void Pediatrician(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityDoctorListBySp.class);
        intent.putExtra("TAG", "Pediatrician");
        startActivity(intent);
    }

    public void Dermatologist(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityDoctorListBySp.class);
        intent.putExtra("TAG", "Dermatologist");
        startActivity(intent);
    }

    public void Endocrinologist(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityDoctorListBySp.class);
        intent.putExtra("TAG", "Endocrinologist");
        startActivity(intent);
    }

    public void Gastroenterologist(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityDoctorListBySp.class);
        intent.putExtra("TAG", "Gastroenterologist");
        startActivity(intent);
    }

    public void Physiotherapist(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityDoctorListBySp.class);
        intent.putExtra("TAG", "DoctorsChamber");
        startActivity(intent);
    }

    public void Nephrologist(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityDoctorListBySp.class);
        intent.putExtra("TAG", "Nephrologist");
        startActivity(intent);
    }

    public void Pulmonologist(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityDoctorListBySp.class);
        intent.putExtra("TAG", "Pulmonologist");
        startActivity(intent);
    }

    public void Psychiatrist(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityDoctorListBySp.class);
        intent.putExtra("TAG", "Psychiatrist");
        startActivity(intent);
    }

    public void DentalSurgeon(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityDoctorListBySp.class);
        intent.putExtra("TAG", "Dentist");
        startActivity(intent);
    }

    public void Ophthalmologist(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityDoctorListBySp.class);
        intent.putExtra("TAG", "Ophthalmologist");
        startActivity(intent);
    }

    public void Cardiologist(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityDoctorListBySp.class);
        intent.putExtra("TAG", "Cardiologist");
        startActivity(intent);
    }

    public void DoctorsChamber(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityChamberList.class);
        startActivity(intent);
    }


}