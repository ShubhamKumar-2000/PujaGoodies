package com.example.pujagoodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pujagoodies.databinding.ActivityLandingBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.jetbrains.annotations.NotNull;
import android.content.res.Resources;


public class LandingActivity extends AppCompatActivity {
    TextView userName;
    Button logout;
    GoogleSignInClient gClient;
    GoogleSignInOptions gOptions;
    ActivityLandingBinding binding;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] name= { "Aastha"
                , "Aniruddhacharya ji"
                , "Satsang"
                , "Bhakthi TV"
                , "Shri Devkinandan Thakur Ji"
                , "Pandit Pradeep Ji Mishra Sehore Wale"
                , "Bhakti Sangeet HDN"
                , "JayaKishori Motivation"
                , "Iamjayakishori"
                , "SanskarTV"
                , "SADHNA GOLD"
                , "Saregama Bhakti"
                , "Gauri Gopal Tv"
                , "Swami Raghvacharya"
                , "BhaktiSagar Tv"
                , "Shemaroo Bhakti"
                , "T-Series Bhakti Sagar"
                , "Pen Bhakti"};

        String [] str={"dnI_7TiD7CY","dnI_7TiD7CY","dnI_7TiD7CY","dnI_7TiD7CY","dnI_7TiD7CY","dnI_7TiD7CY","dnI_7TiD7CY","dnI_7TiD7CY","dnI_7TiD7CY",
                "dnI_7TiD7CY","dnI_7TiD7CY","dnI_7TiD7CY","dnI_7TiD7CY","dnI_7TiD7CY","dnI_7TiD7CY","dnI_7TiD7CY","dnI_7TiD7CY","dnI_7TiD7CY"};
        int[] image = {R.drawable.aastha, R.drawable.aniruddhacharyaji, R.drawable.satsang, R.drawable.bhakthitv, R.drawable.shridevkinandan,
                R.drawable.panditpradeepji, R.drawable.bhaktisangeet, R.drawable.jayakishori, R.drawable.iamjayakishori, R.drawable.sanskartv,
                R.drawable.sadhnagold, R.drawable.saregamabhakti, R.drawable.gaurigopaltv, R.drawable.swamiraghvacharya, R.drawable.bhaktisagartv,
                R.drawable.shemaroobhakti, R.drawable.tseriesbhaktisagar, R.drawable.penbhakti};

        // this makes the grid items circular
        //ends here

        GridAdapter gridAdapter = new GridAdapter(LandingActivity.this, name,image);
        binding.gridView.setAdapter(gridAdapter);

        binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //define your Logic and Intent here
                Toast.makeText(LandingActivity.this, "You Clikced"+name[position], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LandingActivity.this, YoutubePlayerActivity.class);
                intent.putExtra("id", str[position]);
                startActivity(intent);

            }
        });

        getSupportActionBar().hide();
        logout = findViewById(R.id.logout);
//        userName = findViewById(R.id.userName);

        gOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gClient = GoogleSignIn.getClient(this, gOptions);

        GoogleSignInAccount gAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(gAccount != null){
            String gName = gAccount.getDisplayName();
//            userName.setText(gName);
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                        startActivity(new Intent(LandingActivity.this, HomeActivity.class));
                    }
                });
            }
        });
    }
}