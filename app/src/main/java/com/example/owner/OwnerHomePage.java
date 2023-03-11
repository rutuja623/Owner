package com.example.owner;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.acl.Owner;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class OwnerHomePage extends AppCompatActivity {

//
//    private Button logout;
//    private String userID;
//    private Button btexp , btcare;
//    VideoView videoView;
//
//    SingletonEdit b =  SingletonEdit.getInstance();
//
//
//    private FirebaseUser user;
//    private DatabaseReference reference;
//    private TextView header_userprofile;
//
//    DrawerLayout drawerLayout;
//    NavigationView navigationView;
//    Toolbar toolbar;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_owner_home_page);
//
////        logout = (Button) findViewById(R.id.signout);
//        System.out.println("Hi this is mandira");
//
//        drawerLayout = findViewById(R.id.drawerlayout_new);
//        navigationView = findViewById(R.id.navigationview2);
//
//        toolbar = findViewById(R.id.toolbar);
//
//
//        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
////        Toolbar toolbar  = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        btexp = findViewById(R.id.btexplore);
//        btcare = findViewById(R.id.btcare);
//
////
////        toolbar.setTitleTextColor(getResources().getColor(R.color.grey2));
//
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
//        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.grey2));
//        toggle.getDrawerArrowDrawable().setBarLength(70);
//        toggle.getDrawerArrowDrawable().setArrowShaftLength(30);
//        toggle.getDrawerArrowDrawable().setBarThickness(7);
//        toggle.getDrawerArrowDrawable().setGapSize(15);
//
//        drawerLayout.addDrawerListener(toggle);
//
//
//        toggle.syncState();
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                int id = item.getItemId();
//                item.setChecked(true);
//                drawerLayout.closeDrawer(GravityCompat.START);
//                switch (id) {
//                    case R.id.nav_home:
//                        Intent i = new Intent(OwnerHomePage.this, OwnerHomePage.class);
//                        startActivity(i);
//                        break;
////                        Toast.makeText(UserHomePage.this, "Home is clicked", Toast.LENGTH_SHORT).show();break;
//                    case R.id.nav_profile:
//                        replaceFragment(new ProfileFragment());
//                        break;
//                    case R.id.nav_about:
//                        replaceFragment(new AboutFragment());
//                        break;
//                    case R.id.nav_contact:
//                        replaceFragment(new ContactFragment());
//                        break;
//                    case R.id.nav_logout:
//                        new AlertDialog.Builder(OwnerHomePage.this)
//                                .setIcon(R.drawable.ic_baseline_logout_24)
//                                .setTitle("Logout")
//                                .setMessage("Are you sure you want to logout?")
//                                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
//                                {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        FirebaseAuth.getInstance().signOut();
//                                        startActivity(new Intent(OwnerHomePage.this, Login_page.class));
//                                    }
//
//                                })
//                                .setNegativeButton("No", null)
//                                .show();
//
//                        break;
//
//                    case R.id.nav_feedback:
//                        replaceFragment(new FeedbackFragment());
//                        break;
//                    case R.id.nav_share:
//                        Intent intentx = new Intent(Intent.ACTION_SEND);
//                        intentx.setType("text/plain");
//                        intentx.putExtra(Intent.EXTRA_SUBJECT , "Check out this cool application");
//                        intentx.putExtra(Intent.EXTRA_TEXT , "Link");
//                        startActivity(Intent.createChooser(intentx , "Share Via"));
//
////                        replaceFragment(new ShareFragment());
//                        break;
//                    default:
//                        return true;
//                }
//                return true;
//            }
//        });
//
//
////        logout.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                FirebaseAuth.getInstance().signOut();
////                startActivity(new Intent(UserHomePage.this, Login_page.class));
////            }
////        });
//
//
//        user = FirebaseAuth.getInstance().getCurrentUser();
//        reference = FirebaseDatabase.getInstance().getReference("Users");
//        userID = user.getUid();
////        userID = "e5OOUd5tMbdyO8dFOPxkqZZA2GJ2";
//
//        final TextView greetingTextview = (TextView) findViewById(R.id.greeting);
//        //        final TextView fullnameTextview = (TextView) findViewById(R.id.fullName);
////        final TextView emailTextview = (TextView) findViewById(R.id.emailid);
////        final TextView mobileTextview = (TextView) findViewById(R.id.mobilenumber);
//        final TextView firstname = (TextView) findViewById(R.id.Name);
//        header_userprofile = (TextView) findViewById(R.id.ownerprofilename);
//
//        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Owner userprofile = snapshot.getValue(Owner.class);
//                if (userprofile != null) {
//
////
////                    String email = userprofile.email;
////                    String fullname = userprofile.fullname;
////                    String mobileno = userprofile.mobileno;
////
////                    b.setFullname(fullname);
////                    b.setEmail(email);
////                    b.setPhone_no(mobileno);
////                    String[] fullnamestr = fullname.split(" ");
////                    String fname = fullnamestr[0];
////
//////                    fullnameTextview.setText(fullname);
//////                    emailTextview.setText(email);
//////                    mobileTextview.setText(mobileno);
////                    firstname.setText(fname + "!");
////                    header_userprofile.setText(fullname);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(OwnerHomePage.this, "something wrong happened!", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//
//    }
//
//    private void replaceFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.drawerlayout_new, fragment);
//        fragmentTransaction.commit();
//
//    }
//
//    @Override
//    public void onBackPressed() {
//
//        int count = getSupportFragmentManager().getBackStackEntryCount();
//
//        if (count == 0) {
//            Intent i = new Intent(OwnerHomePage.this, OwnerHomePage.class);
//            startActivity(i);
////            super.onBackPressed();
//            //additional code
//        } else {
//            Intent i = new Intent(OwnerHomePage.this, OwnerHomePage.class);
//            startActivity(i);
//        }
//
//    }
//

}