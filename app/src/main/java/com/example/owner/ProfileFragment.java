package com.example.owner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.security.acl.Owner;


public class ProfileFragment extends Fragment {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    ImageView backbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        // Inflate the layout for this fragment
        Button btne;
        TextView name_user = rootView.findViewById(R.id.name_v);
        TextView email_user = rootView.findViewById(R.id.email_v);
        TextView phone_user = rootView.findViewById(R.id.phone_v);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        backbtn = rootView.findViewById(R.id.backpressed_p);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Owner userprofile = snapshot.getValue(Owner.class);
                if (userprofile != null) {

//
//                    String email = userprofile.email;
//                    String fullname = userprofile.fullname;
//                    String mobileno = userprofile.mobileno;
//
//                    name_user.setText(fullname);
//                    email_user.setText(email);
//                    phone_user.setText(mobileno);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "something wrong happened!", Toast.LENGTH_SHORT).show();

            }
        });



        btne = rootView.findViewById(R.id.btn_edit);
        btne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment editFragment = new EditFragment();
                // consider using Java coding conventions (upper first char class names!!!)
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.drawerlayout_new, editFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();

            }
        });

        return rootView;
    }
    public void onBackPressed() {


        Intent i = new Intent(getContext(), OwnerHomePage.class);
        startActivity(i);
//

    }
}