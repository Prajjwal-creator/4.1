package com.example.a41;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a41.database.TaskDatabase;
import com.example.a41.model.Task;
import com.example.a41.R;

import java.util.List;

public class TaskDetailActivity extends AppCompatActivity {

    TextView titleView, descView, dateView;
    Button deleteBtn;
    TaskDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        // Bind views
        titleView = findViewById(R.id.detailTitle);
        descView = findViewById(R.id.detailDesc);
        dateView = findViewById(R.id.detailDate);
        deleteBtn = findViewById(R.id.deleteButton);

        // Initialize DB
        db = TaskDatabase.getInstance(this);
        int taskId = getIntent().getIntExtra("taskId", -1);

        // Fetch and match task
        final Task task = findTaskById(taskId);

        if (task != null) {
            titleView.setText(task.title);
            descView.setText(task.description);
            dateView.setText(task.dueDate);

            deleteBtn.setOnClickListener(v -> {
                db.taskDao().delete(task);
                Toast.makeText(TaskDetailActivity.this, "Task deleted", Toast.LENGTH_SHORT).show();
                finish();
            });
        } else {
            Toast.makeText(this, "Task not found", Toast.LENGTH_SHORT).show();
        }
    }

    private Task findTaskById(int taskId) {
        List<Task> allTasks = db.taskDao().getAllTasks();
        for (Task t : allTasks) {
            if (t.id == taskId) {
                return t;
            }
        }
        return null;
    }
}
