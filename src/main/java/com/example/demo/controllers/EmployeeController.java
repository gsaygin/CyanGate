package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Api
@RestController()
@RequestMapping("/api/employee")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Could not find employee")
})
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAllEmployee() {
        return ResponseEntity.ok().body(employeeService.findAll());
    }

    @PostMapping("/create")
    @ApiResponses({
            @ApiResponse(code = 201, message = "OK")
    })
    public void createRecord() {
        try
        {
            String filename = "/home/chopchop/Desktop/cyangate/CyanGate_Interview_Assignment/Sample_Employees.xlsx";
            FileInputStream file = new FileInputStream(new File(filename));

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // to pass first column names row
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                List<String> values = new ArrayList<>();

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    Object val;

                    switch (cell.getCellType())
                    {
                        case NUMERIC:
                            val = cell.getNumericCellValue();
                            break;
                        case STRING:
                            val = cell.getStringCellValue();
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + cell.getCellType());
                    }
                    values.add(val.toString());
                }

                Employee e = new Employee(values.get(0),
                        values.get(1),
                        (int) Double.parseDouble(values.get(2)),
                        (int) Double.parseDouble(values.get(3)),
                        (int) Double.parseDouble(values.get(4)),
                        values.get(5));
                employeeService.insert(e);
                values.clear();
            }
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @GetMapping("{id}")
    @ApiResponses({
            @ApiResponse(code = 202, message = "OK")
    })
    public ResponseEntity<Employee> readRecord(@PathVariable long id) {
        return ResponseEntity.ok().body(employeeService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    @ApiResponses({
            @ApiResponse(code = 203, message = "OK")
    })
    public ResponseEntity<Employee> deleteRecord(@PathVariable("id") long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    @ApiResponses({
            @ApiResponse(code = 204, message = "OK")
    })
    public String updateRecord(@RequestBody Employee employee) {
        if(employee != null) {
            employeeService.update(employee);
            return "Updated record.";
        } else {
            return "Request does not contain a body";
        }
    }
}
