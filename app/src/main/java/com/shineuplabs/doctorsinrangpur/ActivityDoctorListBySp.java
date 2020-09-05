package com.shineuplabs.doctorsinrangpur;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class ActivityDoctorListBySp extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String spTag;
    int size = 0;
    TextView HeaderTag, TotalTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list_by_sp);

        setUpMenu();

        HeaderTag = findViewById(R.id.header_tag_Tv_id);
        TotalTv = findViewById(R.id.Total_tv_id);


        spTag = getIntent().getStringExtra("TAG");

        setUpSpHeader();

        popularOne();
        popularTwo();
        labAidDiagnostic();
        updateDiagnostic();
        doctorsDiagnostic();
        hyperTensionCenter();
        kasirUddinHospital();
        islamiBankHospital();
        sebaPathology();
        annexDiagnostic();
        apolloDiagnostic();
        rangpurDigital();
        prescriptionPoint();
        sunDiagnostic();
        ragnpurCityScan();
        centralLab();
        metroLab();
        globalEye();

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

                PopupMenu popup = new PopupMenu(ActivityDoctorListBySp.this, view);
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
                                Dialog dialog = new Dialog(ActivityDoctorListBySp.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
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

    private void setUpSpHeader() {

        if (spTag.contains("Neurologist")){
            HeaderTag.setText(R.string.Neurologist_sp);
        }
        if (spTag.contains("Medicine")){
            HeaderTag.setText(R.string.Medicine_sp);
        }
        if (spTag.contains("Gynecologist")){
            HeaderTag.setText(R.string.Gynecologists_sp);
        }
        if (spTag.contains("Oncologist")){
            HeaderTag.setText(R.string.Oncologists_sp);
        }
        if (spTag.contains("Anesthesiologist")){
            HeaderTag.setText(R.string.Anesthesiologists);
        }
        if (spTag.contains("Surgeon")){
            HeaderTag.setText(R.string.Surgery_sp);
        }
        if (spTag.contains("Otolaryngologist")){
            HeaderTag.setText(R.string.ent_sp);
        }
        if (spTag.contains("Orthopedic")){
            HeaderTag.setText(R.string.Orthopedist);
        }
        if (spTag.contains("Pediatrician")){
            HeaderTag.setText(R.string.Pediatricians_sp);
        }
        if (spTag.contains("Dermatologist")){
            HeaderTag.setText(R.string.Dermatologists_sp);
        }
        if (spTag.contains("Endocrinologist")){
            HeaderTag.setText(R.string.Diabetics_sp);
        }
        if (spTag.contains("Gastroenterologist")){
            HeaderTag.setText(R.string.Liver_sp);
        }
        if (spTag.contains("Physiatrist")){
            HeaderTag.setText(R.string.Psychiatrists_sp);
        }
        if (spTag.contains("Nephrologist")){
            HeaderTag.setText(R.string.Nephrologists_sp);
        }
        if (spTag.contains("Pulmonologist")){
            HeaderTag.setText(R.string.Pulmonologists_sp);
        }
        if (spTag.contains("Psychiatrist")){
            HeaderTag.setText(R.string.Psychiatrists_sp);
        }
        if (spTag.contains("Dentist")){
            HeaderTag.setText(R.string.Dental);
        }
        if (spTag.contains("Ophthalmologist")){
            HeaderTag.setText(R.string.Eye_sp);
        }
        if (spTag.contains("Cardiologist")){
            HeaderTag.setText(R.string.Cardiologists_sp);
        }
    }

    private void popularOne() {

        Query query = db.collection("dc_popular_one").whereArrayContains("tag", spTag);

        FirestoreRecyclerOptions<ModelDoctorList> options = new FirestoreRecyclerOptions.Builder<ModelDoctorList>()
                .setQuery(query, ModelDoctorList.class)
                .build();

        AdapterBySp adapter = new AdapterBySp(options);
        RecyclerView recyclerView = findViewById(R.id.popular_one_Rv_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        // Change The Activity
        adapter.setOnItemClickListener(new AdapterBySp.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(ActivityDoctorListBySp.this, ActivityProfile.class);
                intent.putExtra("PATH", path);
                startActivity(intent);
            }
        });

        // Hiding The Empty Layout
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot querySnapshot = task.getResult();
                size = size + querySnapshot.size();
                if (querySnapshot.isEmpty()){
                    findViewById(R.id.popular_one_layout_id).setVisibility(View.GONE);
                }
            }
        });


    }

    private void popularTwo() {
        Query query = db.collection("dc_popular_two").whereEqualTo("tag", spTag);

        FirestoreRecyclerOptions<ModelDoctorList> options = new FirestoreRecyclerOptions.Builder<ModelDoctorList>()
                .setQuery(query, ModelDoctorList.class)
                .build();

        AdapterBySp adapter = new AdapterBySp(options);
        RecyclerView recyclerView = findViewById(R.id.popular_two_Rv_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        // Change The Activity
        adapter.setOnItemClickListener(new AdapterBySp.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(ActivityDoctorListBySp.this, ActivityProfile.class);
                intent.putExtra("PATH", path);
                startActivity(intent);
            }
        });

        // Hiding The Empty Layout
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot querySnapshot = task.getResult();
                size = size + querySnapshot.size();
                if (querySnapshot.isEmpty()){
                    findViewById(R.id.popular_two_layout_id).setVisibility(View.GONE);
                }
            }
        });
    }

    private void labAidDiagnostic() {
        Query query = db.collection("dc_labaid").whereEqualTo("tag", spTag);

        FirestoreRecyclerOptions<ModelDoctorList> options = new FirestoreRecyclerOptions.Builder<ModelDoctorList>()
                .setQuery(query, ModelDoctorList.class)
                .build();

        AdapterBySp adapter = new AdapterBySp(options);
        RecyclerView recyclerView = findViewById(R.id.labAid_Rv_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        // Change The Activity
        adapter.setOnItemClickListener(new AdapterBySp.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(ActivityDoctorListBySp.this, ActivityProfile.class);
                intent.putExtra("PATH", path);
                startActivity(intent);
            }
        });

        // Hiding The Empty Layout
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot querySnapshot = task.getResult();
                size = size + querySnapshot.size();
                if (querySnapshot.isEmpty()){
                    findViewById(R.id.labAid_layout_id).setVisibility(View.GONE);
                }
            }
        });
    }

    private void updateDiagnostic() {

        Query query = db.collection("dc_update").whereEqualTo("tag", spTag);

        FirestoreRecyclerOptions<ModelDoctorList> options = new FirestoreRecyclerOptions.Builder<ModelDoctorList>()
                .setQuery(query, ModelDoctorList.class)
                .build();

        AdapterBySp adapter = new AdapterBySp(options);
        RecyclerView recyclerView = findViewById(R.id.update_Rv_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        // Change The Activity
        adapter.setOnItemClickListener(new AdapterBySp.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(ActivityDoctorListBySp.this, ActivityProfile.class);
                intent.putExtra("PATH", path);
                startActivity(intent);
            }
        });

        // Hiding The Empty Layout
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot querySnapshot = task.getResult();
                size = size + querySnapshot.size();
                if (querySnapshot.isEmpty()){
                    findViewById(R.id.update_layout_id).setVisibility(View.GONE);
                }
            }
        });
    }

    private void doctorsDiagnostic() {

        Query query = db.collection("dc_doctors").whereEqualTo("tag", spTag);

        FirestoreRecyclerOptions<ModelDoctorList> options = new FirestoreRecyclerOptions.Builder<ModelDoctorList>()
                .setQuery(query, ModelDoctorList.class)
                .build();

        AdapterBySp adapter = new AdapterBySp(options);
        RecyclerView recyclerView = findViewById(R.id.doctors_Rv_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        // Change The Activity
        adapter.setOnItemClickListener(new AdapterBySp.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(ActivityDoctorListBySp.this, ActivityProfile.class);
                intent.putExtra("PATH", path);
                startActivity(intent);
            }
        });

        // Hiding The Empty Layout
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot querySnapshot = task.getResult();
                size = size + querySnapshot.size();
                if (querySnapshot.isEmpty()){
                    findViewById(R.id.doctors_layout_id).setVisibility(View.GONE);
                }
            }
        });

    }

    private void hyperTensionCenter() {

        Query query = db.collection("dc_hyper_tension").whereEqualTo("tag", spTag);

        FirestoreRecyclerOptions<ModelDoctorList> options = new FirestoreRecyclerOptions.Builder<ModelDoctorList>()
                .setQuery(query, ModelDoctorList.class)
                .build();

        AdapterBySp adapter = new AdapterBySp(options);
        RecyclerView recyclerView = findViewById(R.id.hyper_tension_Rv_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        // Change The Activity
        adapter.setOnItemClickListener(new AdapterBySp.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(ActivityDoctorListBySp.this, ActivityProfile.class);
                intent.putExtra("PATH", path);
                startActivity(intent);
            }
        });

        // Hiding The Empty Layout
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot querySnapshot = task.getResult();
                size = size + querySnapshot.size();
                if (querySnapshot.isEmpty()){
                    findViewById(R.id.hyper_tension_layout_id).setVisibility(View.GONE);
                }
            }
        });

    }

    private void kasirUddinHospital() {

        Query query = db.collection("dc_kasir").whereEqualTo("tag", spTag);

        FirestoreRecyclerOptions<ModelDoctorList> options = new FirestoreRecyclerOptions.Builder<ModelDoctorList>()
                .setQuery(query, ModelDoctorList.class)
                .build();

        AdapterBySp adapter = new AdapterBySp(options);
        RecyclerView recyclerView = findViewById(R.id.kasir_uddin_Rv_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        // Change The Activity
        adapter.setOnItemClickListener(new AdapterBySp.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(ActivityDoctorListBySp.this, ActivityProfile.class);
                intent.putExtra("PATH", path);
                startActivity(intent);
            }
        });

        // Hiding The Empty Layout
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot querySnapshot = task.getResult();
                size = size + querySnapshot.size();
                if (querySnapshot.isEmpty()){
                    findViewById(R.id.kasir_uddin_layout_id).setVisibility(View.GONE);
                }
            }
        });

    }

    private void islamiBankHospital() {

        Query query = db.collection("dc_islami_bank").whereEqualTo("tag", spTag);

        FirestoreRecyclerOptions<ModelDoctorList> options = new FirestoreRecyclerOptions.Builder<ModelDoctorList>()
                .setQuery(query, ModelDoctorList.class)
                .build();

        AdapterBySp adapter = new AdapterBySp(options);
        RecyclerView recyclerView = findViewById(R.id.islami_bank_Rv_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        // Change The Activity
        adapter.setOnItemClickListener(new AdapterBySp.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(ActivityDoctorListBySp.this, ActivityProfile.class);
                intent.putExtra("PATH", path);
                startActivity(intent);
            }
        });

        // Hiding The Empty Layout
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot querySnapshot = task.getResult();
                size = size + querySnapshot.size();
                if (querySnapshot.isEmpty()){
                    findViewById(R.id.islami_bank_layout_id).setVisibility(View.GONE);
                }
            }
        });

    }

    private void sebaPathology() {

        Query query = db.collection("dc_seba").whereEqualTo("tag", spTag);

        FirestoreRecyclerOptions<ModelDoctorList> options = new FirestoreRecyclerOptions.Builder<ModelDoctorList>()
                .setQuery(query, ModelDoctorList.class)
                .build();

        AdapterBySp adapter = new AdapterBySp(options);
        RecyclerView recyclerView = findViewById(R.id.seba_Rv_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        // Change The Activity
        adapter.setOnItemClickListener(new AdapterBySp.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(ActivityDoctorListBySp.this, ActivityProfile.class);
                intent.putExtra("PATH", path);
                startActivity(intent);
            }
        });

        // Hiding The Empty Layout
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot querySnapshot = task.getResult();
                size = size + querySnapshot.size();
                if (querySnapshot.isEmpty()){
                    findViewById(R.id.seba_layout_id).setVisibility(View.GONE);
                }
            }
        });

    }

    private void annexDiagnostic() {

        Query query = db.collection("dc_annex").whereEqualTo("tag", spTag);

        FirestoreRecyclerOptions<ModelDoctorList> options = new FirestoreRecyclerOptions.Builder<ModelDoctorList>()
                .setQuery(query, ModelDoctorList.class)
                .build();

        AdapterBySp adapter = new AdapterBySp(options);
        RecyclerView recyclerView = findViewById(R.id.annex_Rv_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        // Change The Activity
        adapter.setOnItemClickListener(new AdapterBySp.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(ActivityDoctorListBySp.this, ActivityProfile.class);
                intent.putExtra("PATH", path);
                startActivity(intent);
            }
        });

        // Hiding The Empty Layout
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot querySnapshot = task.getResult();
                size = size + querySnapshot.size();
                if (querySnapshot.isEmpty()){
                    findViewById(R.id.annex_layout_id).setVisibility(View.GONE);
                }
            }
        });

    }

    private void apolloDiagnostic() {

        Query query = db.collection("dc_apollo").whereEqualTo("tag", spTag);

        FirestoreRecyclerOptions<ModelDoctorList> options = new FirestoreRecyclerOptions.Builder<ModelDoctorList>()
                .setQuery(query, ModelDoctorList.class)
                .build();

        AdapterBySp adapter = new AdapterBySp(options);
        RecyclerView recyclerView = findViewById(R.id.apollo_Rv_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        // Change The Activity
        adapter.setOnItemClickListener(new AdapterBySp.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(ActivityDoctorListBySp.this, ActivityProfile.class);
                intent.putExtra("PATH", path);
                startActivity(intent);
            }
        });

        // Hiding The Empty Layout
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot querySnapshot = task.getResult();
                size = size + querySnapshot.size();
                if (querySnapshot.isEmpty()){
                    findViewById(R.id.apollo_layout_id).setVisibility(View.GONE);
                }
            }
        });
    }

    private void rangpurDigital() {

        Query query = db.collection("dc_rangpur_digital").whereEqualTo("tag", spTag);

        FirestoreRecyclerOptions<ModelDoctorList> options = new FirestoreRecyclerOptions.Builder<ModelDoctorList>()
                .setQuery(query, ModelDoctorList.class)
                .build();

        AdapterBySp adapter = new AdapterBySp(options);
        RecyclerView recyclerView = findViewById(R.id.rangpur_digital_Rv_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        // Change The Activity
        adapter.setOnItemClickListener(new AdapterBySp.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(ActivityDoctorListBySp.this, ActivityProfile.class);
                intent.putExtra("PATH", path);
                startActivity(intent);
            }
        });

        // Hiding The Empty Layout
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot querySnapshot = task.getResult();
                size = size + querySnapshot.size();
                if (querySnapshot.isEmpty()){
                    findViewById(R.id.rangpur_digital_layout_id).setVisibility(View.GONE);
                }
            }
        });

    }

    private void prescriptionPoint() {

        Query query = db.collection("dc_prescription_point").whereEqualTo("tag", spTag);

        FirestoreRecyclerOptions<ModelDoctorList> options = new FirestoreRecyclerOptions.Builder<ModelDoctorList>()
                .setQuery(query, ModelDoctorList.class)
                .build();

        AdapterBySp adapter = new AdapterBySp(options);
        RecyclerView recyclerView = findViewById(R.id.prescription_point_Rv_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        // Change The Activity
        adapter.setOnItemClickListener(new AdapterBySp.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(ActivityDoctorListBySp.this, ActivityProfile.class);
                intent.putExtra("PATH", path);
                startActivity(intent);
            }
        });

        // Hiding The Empty Layout
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot querySnapshot = task.getResult();
                size = size + querySnapshot.size();
                if (querySnapshot.isEmpty()){
                    findViewById(R.id.prescription_point_layout_id).setVisibility(View.GONE);
                }
            }
        });

    }

    private void sunDiagnostic() {

        Query query = db.collection("dc_sun").whereEqualTo("tag", spTag);

        FirestoreRecyclerOptions<ModelDoctorList> options = new FirestoreRecyclerOptions.Builder<ModelDoctorList>()
                .setQuery(query, ModelDoctorList.class)
                .build();

        AdapterBySp adapter = new AdapterBySp(options);
        RecyclerView recyclerView = findViewById(R.id.sun_Rv_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        // Change The Activity
        adapter.setOnItemClickListener(new AdapterBySp.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(ActivityDoctorListBySp.this, ActivityProfile.class);
                intent.putExtra("PATH", path);
                startActivity(intent);
            }
        });

        // Hiding The Empty Layout
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot querySnapshot = task.getResult();
                size = size + querySnapshot.size();
                if (querySnapshot.isEmpty()){
                    findViewById(R.id.sun_layout_id).setVisibility(View.GONE);
                }
            }
        });

    }

    private void ragnpurCityScan() {

        Query query = db.collection("dc_city_scan").whereEqualTo("tag", spTag);

        FirestoreRecyclerOptions<ModelDoctorList> options = new FirestoreRecyclerOptions.Builder<ModelDoctorList>()
                .setQuery(query, ModelDoctorList.class)
                .build();

        AdapterBySp adapter = new AdapterBySp(options);
        RecyclerView recyclerView = findViewById(R.id.rangpur_city_scan_Rv_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        // Change The Activity
        adapter.setOnItemClickListener(new AdapterBySp.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(ActivityDoctorListBySp.this, ActivityProfile.class);
                intent.putExtra("PATH", path);
                startActivity(intent);
            }
        });

        // Hiding The Empty Layout
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot querySnapshot = task.getResult();
                size = size + querySnapshot.size();
                if (querySnapshot.isEmpty()){
                    findViewById(R.id.rangpur_city_scan_layout_id).setVisibility(View.GONE);
                }
            }
        });
    }

    private void centralLab() {

        Query query = db.collection("dc_central_lab").whereEqualTo("tag", spTag);

        FirestoreRecyclerOptions<ModelDoctorList> options = new FirestoreRecyclerOptions.Builder<ModelDoctorList>()
                .setQuery(query, ModelDoctorList.class)
                .build();

        AdapterBySp adapter = new AdapterBySp(options);
        RecyclerView recyclerView = findViewById(R.id.central_lab_Rv_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        // Change The Activity
        adapter.setOnItemClickListener(new AdapterBySp.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(ActivityDoctorListBySp.this, ActivityProfile.class);
                intent.putExtra("PATH", path);
                startActivity(intent);
            }
        });

        // Hiding The Empty Layout
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot querySnapshot = task.getResult();
                size = size + querySnapshot.size();
                if (querySnapshot.isEmpty()){
                    findViewById(R.id.central_lab_layout_id).setVisibility(View.GONE);
                }
            }
        });

    }

    private void metroLab() {

        Query query = db.collection("dc_metro_lab").whereEqualTo("tag", spTag);

        FirestoreRecyclerOptions<ModelDoctorList> options = new FirestoreRecyclerOptions.Builder<ModelDoctorList>()
                .setQuery(query, ModelDoctorList.class)
                .build();

        AdapterBySp adapter = new AdapterBySp(options);
        RecyclerView recyclerView = findViewById(R.id.metro_lab_Rv_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        // Change The Activity
        adapter.setOnItemClickListener(new AdapterBySp.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(ActivityDoctorListBySp.this, ActivityProfile.class);
                intent.putExtra("PATH", path);
                startActivity(intent);
            }
        });

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot querySnapshot = task.getResult();
                size = size + querySnapshot.size();
                if (querySnapshot.isEmpty()){
                    findViewById(R.id.metro_lab_layout_id).setVisibility(View.GONE);
                }
            }
        });

    }

    private void globalEye() {

        Query query = db.collection("dc_global_eye_hospital").whereEqualTo("tag", spTag);

        FirestoreRecyclerOptions<ModelDoctorList> options = new FirestoreRecyclerOptions.Builder<ModelDoctorList>()
                .setQuery(query, ModelDoctorList.class)
                .build();

        AdapterBySp adapter = new AdapterBySp(options);
        RecyclerView recyclerView = findViewById(R.id.global_eye_Rv_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        // Change The Activity
        adapter.setOnItemClickListener(new AdapterBySp.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(ActivityDoctorListBySp.this, ActivityProfile.class);
                intent.putExtra("PATH", path);
                startActivity(intent);
            }
        });

        // Hiding The Empty Layout
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot querySnapshot = task.getResult();
                size = size + querySnapshot.size();
                TotalTv.setText(String.valueOf(size));
                if (querySnapshot.isEmpty()){
                    findViewById(R.id.global_eye_layout_id).setVisibility(View.GONE);
                }

                TotalTv.setText(String.valueOf(size));
            }
        });


    }


}