package com.telusko.demo6.Service;

import com.telusko.demo6.Exception.ErrorHandler;
import com.telusko.demo6.Model.Data;
import com.telusko.demo6.Model.dataResponse;
import com.telusko.demo6.Repository.DataRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataService {
    @Autowired
    private DataRepository dataRepository;

    // Validate and normalize data
    private String validateAndNormalize(String input, String type) {
        if (input == null || input.isEmpty()) {
            throw new ErrorHandler(type + " is null, please enter values");
        }
        String trimmedInput = input.trim();
        if (type.equals("name")) {
            if (!trimmedInput.matches("^[a-zA-Z]+( [a-zA-Z]+)*$")) {
                throw new ErrorHandler("Name contains illegal characters or has more than one space between letters");
            }
        } else if (type.equals("email")) {
            if (!trimmedInput.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$")) {
                throw new ErrorHandler("Invalid email format");
            }
        }
        return trimmedInput.toLowerCase();
    }

    public dataResponse<Data> addData(Data addedingdata) {
        try {
            String normalizedName = validateAndNormalize(addedingdata.getName(), "name");
            String normalizedEmail = validateAndNormalize(addedingdata.getEmail(), "email");
            if (dataRepository.findByName(normalizedName).isPresent()) {
                throw new ErrorHandler("Name already exists");
            } else if (dataRepository.findByEmail(normalizedEmail).isPresent()) {
                throw new ErrorHandler("Email already exists");

            } else {// Save the data
                addedingdata.setName(normalizedName);
                addedingdata.setEmail(normalizedEmail);
                dataRepository.save(addedingdata);
                return new dataResponse<>(addedingdata, "Data added successfully", "success");
            }
        } catch (Exception e) {
            return new dataResponse<>(null, e.getMessage(), "error");
        }
    }//get data

    public dataResponse<List<Data>> getData() {
        try {
            List<Data> dataList = dataRepository.findAll();
            return new dataResponse<>(dataList, "Data retrieved successfully", "success");
        } catch (Exception e) {
            return new dataResponse<>(null, e.getMessage(), "error");
        }
    }//get by id

    public dataResponse<Data> getDataById(String id) {
        try {
            // Attempt to find the data by ID
            Data data = dataRepository.findById(id).orElseThrow(() -> new ErrorHandler("by this id , please check id  "));
            // Return success response if data is found
            return new dataResponse<>(data, "Data retrieved successfully", "success");
        } catch (Exception e) {
            // Catch any other exceptions and return an error response
            return new dataResponse<>(null, "data not found " + e.getMessage(), "error");
        }


    }//get by name

    public dataResponse<Data> findByName(String name) {
        try {
            Data data = dataRepository.findByName(name).orElseThrow(() -> new ErrorHandler("By this name  ,please check and enter name "));
            return new dataResponse<>(data, "Data retrieved successfully", "success");
        } catch (Exception e) {
            return new dataResponse<>(null, "Data not found " + e.getMessage(), "error");
        }
    }

    //get by email
    public dataResponse<Data> findByEmail(String email) {
        try {
            Data data = dataRepository.findByEmail(email).orElseThrow(() -> new ErrorHandler("By this email ,please check and enter email "));
            return new dataResponse<>(data, "Data retrieved successfully", "success");
        } catch (Exception e) {
            return new dataResponse<>(null, "Data not found " + e.getMessage(), "error");
        }
    }

    //delete by id
    public dataResponse<Void> deleteData(String id) {
        try {
            if (dataRepository.findById(id).isPresent()) {
                dataRepository.deleteById(id);
                return new dataResponse<>(null, "Data deleted successfully", "success");
            } else {
                return new dataResponse<>(null, "Data not found " + id, "error");
            }
        } catch (Exception e) {
            return new dataResponse<>(null, "Data not found ", "error");
        }


    }

    public dataResponse<Data> updateById(String id, Data updatedData) {
        try {
            // Find existing data by ID
            Data existingData = dataRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Data with ID " + id + " not found"));

            // Update existing data with new values
            existingData.setName(updatedData.getName());
            existingData.setEmail(updatedData.getEmail());

            // Save the updated data
            Data savedData = dataRepository.save(existingData);

            // Return success response
            return new dataResponse<>(savedData, "Data updated successfully", "success");
        } catch (EntityNotFoundException e) {
            // Handle specific case where data is not found
            return new dataResponse<>(null, e.getMessage(), "error");
        } catch (Exception e) {
            // Handle any other exceptions
            return new dataResponse<>(null, "An error occurred: " + e.getMessage(), "error");
        }
    }

}





