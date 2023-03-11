package com.example.owner;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.acl.Owner;

public class FeedbackFragment extends Fragment {
    Button submit_feedback;
    EditText feedback;
    TextView msg_feedback;
    RatingBar rtb;
    ImageView backbtn;
    private EditText editTextFullname, editTextmobileno, editTextemail, editTextpassword;
    private DatabaseReference reference,references, reference2 , reference3 , reference4;
    private String userID;
    private FirebaseUser user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_feedback, container, false);
        submit_feedback = rootView.findViewById(R.id.btn_fdb);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        feedback = rootView.findViewById(R.id.editTextTextMultiLine);
        msg_feedback = rootView.findViewById(R.id.msg_fdb);
        rtb = rootView.findViewById(R.id.ratingBar);

        rtb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating == 0){
                    msg_feedback.setText("Very Dissatisfied");
                }
                else if(rating >= 1 && rating < 2){
                    msg_feedback.setText("Dissatisfied");

                }
                else if(rating >= 2 && rating < 3){
                    msg_feedback.setText("Ok");

                }
                else if(rating >= 3 && rating < 4){
                    msg_feedback.setText("Good");

                }
                else if(rating >= 4 && rating < 5){
                    msg_feedback.setText("Satsfied");
                }
                else{
                    msg_feedback.setText("Very Satisfied");
                }
            }
        });
        submit_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = feedback.getText().toString().trim();
                user = FirebaseAuth.getInstance().getCurrentUser();
                references = FirebaseDatabase.getInstance().getReference("Users");
                userID = user.getUid();
                references.child(userID).child("Feedback").setValue(message);

                Toast.makeText(getContext(), "Feedback Submitted", Toast.LENGTH_SHORT).show();


            }
        });

        backbtn = rootView.findViewById(R.id.backpressed_f);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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