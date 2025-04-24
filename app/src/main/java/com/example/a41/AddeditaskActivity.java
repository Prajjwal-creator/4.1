package com.example.a41;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.a41.database.TaskDatabase;
import com.example.a41.model.Task;

public class AddeditaskActivity extends AppCompatActivity {

    EditText titleInput, descInput, dateInput;
    Button saveBtn;
    TaskDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit); // Make sure this XML exists!

        titleInput = findViewById(R.id.inputTitle);
        descInput = findViewById(R.id.inputDesc);
        dateInput = findViewById(R.id.inputDate);
        saveBtn = findViewById(R.id.saveButton);

        db = TaskDatabase.getInstance(this);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleInput.getText().toString().trim();
                String desc = descInput.getText().toString().trim();
                String date = dateInput.getText().toString().trim();

                if (title.isEmpty() || date.isEmpty()) {
                    Toast.makeText(AddeditaskActivity.this, "Title and Date are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                Task task = new Task();
                task.title = title;
                task.description = desc;
                task.dueDate = date;
                db.taskDao().insert(task);

                Toast.makeText(AddeditaskActivity.this, "Task saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
