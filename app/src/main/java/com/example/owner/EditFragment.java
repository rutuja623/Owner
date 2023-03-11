package com.example.owner;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditFragment extends Fragment {
    ImageView backbtn;
    private EditText editTextFullname, editTextmobileno, editTextemail, editTextpassword;
    Button editinfo;
    private DatabaseReference reference,references, reference2 , reference3 , reference4;
    private String userID;
    private FirebaseUser user;
    SingletonEdit b =  SingletonEdit.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_edit, container, false);
        backbtn = rootView.findViewById(R.id.backpressed_p2);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new fragment and transaction
                Fragment newFragment = new ProfileFragment();
                // consider using Java coding conventions (upper first char class names!!!)
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.drawerlayout_new, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }
        });
        editTextFullname = (EditText) rootView.findViewById(R.id.fullname2);
        editTextmobileno = (EditText) rootView.findViewById(R.id.mobileno2);
        editTextemail = (EditText) rootView.findViewById(R.id.email2);
        editinfo = rootView.findViewById(R.id.Register_btn2);

        editTextemail.setText(b.getEmail());
        editTextFullname.setText(b.getFullname());
        editTextmobileno.setText(b.getPhone_no());

        editinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = editTextFullname.getText().toString().trim();
                String email = editTextemail.getText().toString().trim();
                String mobileno = editTextmobileno.getText().toString().trim();
                if(fullname.isEmpty()){
                    editTextFullname.setError("Full Name is required");
                    editTextFullname.requestFocus();
                    return;
                }

                if(mobileno.isEmpty()){
                    editTextmobileno.setError("Mobile Number is required");
                    editTextmobileno.requestFocus();
                    return;
                }
                if(email.isEmpty()){
                    editTextemail.setError("email is required");
                    editTextemail.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editTextemail.setError("Please provide valid email");
                    editTextemail.requestFocus();
                    return;

                }

                user = FirebaseAuth.getInstance().getCurrentUser();
                references = FirebaseDatabase.getInstance().getReference("Users");
                userID = user.getUid();
                references.child(userID).child("fullname").setValue(fullname);
                references.child(userID).child("email").setValue(email);
                references.child(userID).child("mobileno").setValue(mobileno);


                Toast.makeText(getContext(), "Profile Edited", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();


            }
        });



        return rootView;

    }

}
