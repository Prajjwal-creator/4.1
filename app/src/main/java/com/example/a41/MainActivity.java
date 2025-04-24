package com.example.a41;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a41.Adapter.TaskAdapter;
import com.example.a41.database.TaskDatabase;
import com.example.a41.model.Task;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TaskAdapter adapter;
    TaskDatabase db;
    Button addTaskBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = TaskDatabase.getInstance(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addTaskBtn = findViewById(R.id.addTaskBtn);
        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddeditaskActivity.class));
            }
        });

        loadTasks(); // initial load
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTasks(); // reload every time MainActivity comes to foreground
    }

    private void loadTasks() {
        List<Task> tasks = db.taskDao().getAllTasks();
        adapter = new TaskAdapter(this, tasks);
        recyclerView.setAdapter(adapter);
    }
}
