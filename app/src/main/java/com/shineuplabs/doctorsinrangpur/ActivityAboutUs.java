package com.shineuplabs.doctorsinrangpur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class ActivityAboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
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

                PopupMenu popup = new PopupMenu(ActivityAboutUs.this, view);
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
                                Dialog dialog = new Dialog(ActivityAboutUs.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
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