package com.telusko.demo6.Controller;

import com.telusko.demo6.Model.Data;
import com.telusko.demo6.Model.dataResponse;
import com.telusko.demo6.Service.DataService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DataController {
    @Autowired
    private DataService dataService;

    @PostMapping("/add")
    public ResponseEntity<dataResponse<Data>> addData(@RequestBody Data data) {
        dataResponse<Data> dataResponse = dataService.addData(data);
        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("/get")
    public ResponseEntity<dataResponse<List<Data>>> getDatas() {
        dataResponse<List<Data>> dataResponse = dataService.getData();
        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<dataResponse<Data>> getDataByIds(@PathVariable String id) {
        dataResponse<Data> dataResponse = dataService.getDataById(id);
        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("get/name/{name}")
    public ResponseEntity<dataResponse<Data>> getDataByName(@PathVariable String name) {
        dataResponse<Data> dataResponse = dataService.findByName(name);
        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("get/email/{email}")
    public ResponseEntity<dataResponse<Data>> getDataByEmail(@PathVariable String email) {
        dataResponse<Data> dataResponse = dataService.findByEmail(email);
        return ResponseEntity.ok(dataResponse);
    }

    @DeleteMapping("delete/id/{id}")
    public ResponseEntity<dataResponse<Void>> deleteDataById(@PathVariable String id) {
        dataResponse<Void> data = dataService.deleteData(id);
        return ResponseEntity.ok(data);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<dataResponse<Data>> updateDataById(@PathVariable String id, @RequestBody Data data) {
        // Call the service to update data
        dataResponse<Data> updated = dataService.updateById(id, data);
        // Return the response from the service
        return ResponseEntity.ok(updated);
    }

}