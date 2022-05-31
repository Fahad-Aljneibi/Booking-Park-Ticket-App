package com.fahadcoder.p11.View.ForAdmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fahadcoder.p11.DAO.AdminMember;
import com.fahadcoder.p11.R;
import com.fahadcoder.p11.model.Member;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllMember extends AppCompatActivity {




    //Reference for the UI elements
    private TextView tvData;

    //Reference to the Firebase realtime database
    private FirebaseDatabase database;

    //Reference to a specific node in the database
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_member);





        tvData = findViewById(R.id.tvData);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("members");


        //receive from admin member
        Intent intent = getIntent();
        String showall = intent.getStringExtra("showall"); // click button show all member
        String showid = intent.getStringExtra("findid"); // click button find by id

        if(showall != null)
        {
            displayAll();
        }
        else if(!showid.equals("0"))
        {
            find(showid);
        }






    }

                                /*********************** FUNCTION ***********************/
                                /**
                                 * displayAll()
                                 * updateUI(List<Member>)
                                 * Back()
                                 **/



    public void displayAll()
    {



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Member> members = new ArrayList<>();
                for(DataSnapshot node:dataSnapshot.getChildren())
                {
                    Member member = node.getValue(Member.class);
                    members.add(member);
                }

                updateUI(members);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void find(String ID)
    {

        //show member by id
        reference.child(ID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Member member = dataSnapshot.getValue(Member.class);

                String details =
                        "id: " + member.getId() + "\n" +
                        "username: " + member.getUsername() + '\n' +
                        "email: " + member.getEmail()+ '\n' +
                        "password='" + member.getPassword() ;

                //tvData.setText(String.valueOf(member.getId()+1));
                tvData.setText(details);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void updateUI (List<Member> members)
    {
        String membersDetails = "";
        for(Member member : members)
        {
            membersDetails += "id: " + member.getId() + "\n" +
                    "username: " + member.getUsername() + "\n" +
                    "email: " + member.getEmail() + "\n" +
                    "password: " + member.getPassword() + "\n\n" ;

        }

        tvData.setText(membersDetails);
    }



    //back to home
    public void Back(View view)
    {
        Intent intent = new Intent(this, AdminMember.class);
        startActivity(intent);
    }
}