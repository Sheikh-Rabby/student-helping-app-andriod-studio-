package com.example.signup;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.signup.inter.onDialogCloseListner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Addnewtask extends BottomSheetDialogFragment {
    public static final String TAG = "Addnewtask";
    private TextView setDueData;
    EditText taskEdit;
    private Button SaveBtn;
    FirebaseFirestore firestore;
    Context context;
    String dueDate="";

    public static Addnewtask newInsance() {


        return new Addnewtask();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.addnewtask,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setDueData = view.findViewById(R.id.setduedata);
        taskEdit = view .findViewById(R.id.edittext);
        SaveBtn = view.findViewById(R.id.saveBtn);
        firestore = FirebaseFirestore.getInstance();
        taskEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
               if (s.toString().equals("")){
                   SaveBtn.setEnabled(false);


               }else{
                   SaveBtn.setEnabled(true);


               }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        setDueData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Calendar calendar = Calendar.getInstance();
              int Month = calendar.get(Calendar.MONTH);
                int Year = calendar.get(Calendar.YEAR);
                int Day = calendar.get(Calendar.DATE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        setDueData.setText(dayOfMonth+"/"+month+"/"+year);
                        dueDate =dayOfMonth+"/"+month+"/"+year;

                    }
                },Year,Month,Day);
                datePickerDialog.show();
            }
        });
        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = taskEdit.getText().toString();
                if (task.isEmpty()) {
                    Toast.makeText(context, "Empty Task not Allowed!", Toast.LENGTH_SHORT).show();
                }else {
                    Map<String,Object>taskMap = new HashMap<>();
                    taskMap.put("task",task);
                    taskMap.put("due",dueDate);
                    taskMap.put("status",0);
                    firestore.collection("task").add(taskMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(context, "Task Saved", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context,e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                dismiss();


            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context= context;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if (activity instanceof onDialogCloseListner){
            ((onDialogCloseListner)activity).onDialogClose(dialog);
        }
    }
}
