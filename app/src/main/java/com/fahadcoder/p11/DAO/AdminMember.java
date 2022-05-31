package com.fahadcoder.p11.DAO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fahadcoder.p11.View.ForAdmin.AdminHome;
import com.fahadcoder.p11.View.ForAdmin.AdminUpdateMember;
import com.fahadcoder.p11.model.Alter;
import com.fahadcoder.p11.R;
import com.fahadcoder.p11.View.ForAdmin.AllMember;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminMember extends AppCompatActivity {

    //Reference for the UI elements
    EditText edtid;
    Alter alert = new Alter();

    //Reference to the Firebase realtime database
    private FirebaseDatabase database;

    //Reference to a specific node in the database
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_member);

        edtid = (EditText)findViewById(R.id.edt_bknum);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("members");
    }



                            /************************* ON CLICK *************************/
                            /**
                             * getAllMembers
                             * findMemberByID
                             * deleteMemberByID
                             * updateMemberByID
                             * displayToast(String message)
                             * Back
                             **/

    // get all members
    public void getAllMembers(View view)
    {

        //send
        Intent intent = new Intent(AdminMember.this, AllMember.class);
        intent.putExtra("showall","showall");
        startActivity(intent);
    }

    // find member by id
    public void findMemberByID(View view)
    {
        String edid = edtid.getText().toString();

        //validation input
        if(edid.isEmpty())
        {
            alert.sendMsg("Error","Fix the errors on the screen",AdminMember.this);
            edtid.setError("Field cannot be empty");
        }
        else
        {
            //send
            Intent intent = new Intent(AdminMember.this,AllMember.class);
            String id = edtid.getText().toString();
            intent.putExtra("findid",id);
            startActivity(intent);
        }

    }


    //delete member
    public void deleteMemberByID(View view)
    {
        String edid = edtid.getText().toString();

        //validation input
        if(edid.isEmpty())
        {
            alert.sendMsg("Error","Fix the errors on the screen",AdminMember.this);
            edtid.setError("Field cannot be empty");
        }
        else
        {


            // Retrieve the member by its id number
            // 1 - get input
            long id = Long.parseLong(edtid.getText().toString());
            reference.child(String.valueOf(id)).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    if(dataSnapshot.exists())
                    {
                        reference.child(String.valueOf(id)).setValue(null); //Delete the Member
                        String message = "Member Deleted";
                        displayToast(message);
                    }
                    else
                    {
                        String message = "No Member Found";
                        displayToast(message);


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }

    }

    //update member
    public void updateMemberByID(View view)
    {
        String edid = edtid.getText().toString();

        //validation input
        if(edid.isEmpty())
        {
            alert.sendMsg("Error","Fix the errors on the screen",AdminMember.this);
            edtid.setError("Field cannot be empty");
        }
        else
        {
            //send
            long id = Integer.parseInt(edtid.getText().toString());
            Intent intent = new Intent(AdminMember.this, AdminUpdateMember.class);
            intent.putExtra("idMemeber",id);
            startActivity(intent);
        }


    }

    private void displayToast(String message)
    {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(AdminMember.this,message,duration);
        toast.setGravity(Gravity.TOP,100,500);
        toast.show();
    }

    //back to home
    public void Back(View view)
    {
        Intent intent = new Intent(this, AdminHome.class);
        startActivity(intent);
    }
}