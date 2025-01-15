package com.example.magnolia;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private EditText searchEditText;
    private List<String> sampleData;
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        searchEditText = findViewById(R.id.searchEditText);
        Button searchButton = findViewById(R.id.searchButton);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Create sample data
        sampleData = new ArrayList<>();
        sampleData.add("Apple");
        sampleData.add("Banana");
        sampleData.add("Cherry");
        sampleData.add("Date");
        sampleData.add("Elderberry");

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchAdapter();
        recyclerView.setAdapter(adapter);

        // Set up search button click listener
        searchButton.setOnClickListener(v -> performSearch());
    }

    private void performSearch() {
        String searchTerm = searchEditText.getText().toString().toLowerCase();

        List<String> filteredList = sampleData.stream()
                .filter(item -> item.toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());

        adapter.updateItems(filteredList);

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show();
        }
    }
}