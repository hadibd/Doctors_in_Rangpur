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

public class ActivityChamberList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamber_list);

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

                PopupMenu popup = new PopupMenu(ActivityChamberList.this, view);
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
                                Dialog dialog = new Dialog(ActivityChamberList.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
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

    public void PopularOneCvId(View view) {
        Intent intent = new Intent(ActivityChamberList.this, ActivityDoctorListByChamber.class);
        intent.putExtra("Chamber", "dc_popular_one");
        startActivity(intent);
    }

    public void PopularTwoCv(View view) {
        Intent intent = new Intent(ActivityChamberList.this, ActivityDoctorListByChamber.class);
        intent.putExtra("Chamber", "dc_popular_two");
        startActivity(intent);
    }

    public void LabAidCv(View view) {
        Intent intent = new Intent(ActivityChamberList.this, ActivityDoctorListByChamber.class);
        intent.putExtra("Chamber", "dc_labaid");
        startActivity(intent);
    }

    public void UpdateCv(View view) {
        Intent intent = new Intent(ActivityChamberList.this, ActivityDoctorListByChamber.class);
        intent.putExtra("Chamber", "dc_update");
        startActivity(intent);
    }

    public void DoctorsCv(View view) {
        Intent intent = new Intent(ActivityChamberList.this, ActivityDoctorListByChamber.class);
        intent.putExtra("Chamber", "dc_doctors");
        startActivity(intent);
    }

    public void HyperCv(View view) {
        Intent intent = new Intent(ActivityChamberList.this, ActivityDoctorListByChamber.class);
        intent.putExtra("Chamber", "dc_hyper_tension");
        startActivity(intent);
    }

    public void KasirCv(View view) {
        Intent intent = new Intent(ActivityChamberList.this, ActivityDoctorListByChamber.class);
        intent.putExtra("Chamber", "dc_kasir");
        startActivity(intent);
    }

    public void IslamiCv(View view) {
        Intent intent = new Intent(ActivityChamberList.this, ActivityDoctorListByChamber.class);
        intent.putExtra("Chamber", "dc_islami_bank");
        startActivity(intent);
    }

    public void SebaCv(View view) {
        Intent intent = new Intent(ActivityChamberList.this, ActivityDoctorListByChamber.class);
        intent.putExtra("Chamber", "dc_seba");
        startActivity(intent);
    }

    public void AnnexCv(View view) {
        Intent intent = new Intent(ActivityChamberList.this, ActivityDoctorListByChamber.class);
        intent.putExtra("Chamber", "dc_annex");
        startActivity(intent);
    }

    public void ApolloCv(View view) {
        Intent intent = new Intent(ActivityChamberList.this, ActivityDoctorListByChamber.class);
        intent.putExtra("Chamber", "dc_apollo");
        startActivity(intent);
    }

    public void RddcCv(View view) {
        Intent intent = new Intent(ActivityChamberList.this, ActivityDoctorListByChamber.class);
        intent.putExtra("Chamber", "dc_rangpur_digital");
        startActivity(intent);
    }

    public void PpCv(View view) {
        Intent intent = new Intent(ActivityChamberList.this, ActivityDoctorListByChamber.class);
        intent.putExtra("Chamber", "dc_prescription_point");
        startActivity(intent);
    }

    public void SunCv(View view) {
        Intent intent = new Intent(ActivityChamberList.this, ActivityDoctorListByChamber.class);
        intent.putExtra("Chamber", "dc_sun");
        startActivity(intent);
    }

    public void RangpurCityScanCv(View view) {
        Intent intent = new Intent(ActivityChamberList.this, ActivityDoctorListByChamber.class);
        intent.putExtra("Chamber", "dc_city_scan");
        startActivity(intent);
    }

    public void CentralCv(View view) {
        Intent intent = new Intent(ActivityChamberList.this, ActivityDoctorListByChamber.class);
        intent.putExtra("Chamber", "dc_central_lab");
        startActivity(intent);
    }

    public void MetroLabCv(View view) {
        Intent intent = new Intent(ActivityChamberList.this, ActivityDoctorListByChamber.class);
        intent.putExtra("Chamber", "dc_metro_lab");
        startActivity(intent);
    }

    public void GlobalEyeHospitalCv(View view) {
        Intent intent = new Intent(ActivityChamberList.this, ActivityDoctorListByChamber.class);
        intent.putExtra("Chamber", "dc_global_eye_hospital");
        startActivity(intent);
    }
}