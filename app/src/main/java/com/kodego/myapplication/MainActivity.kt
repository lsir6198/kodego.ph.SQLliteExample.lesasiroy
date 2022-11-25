package com.kodego.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodego.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var databaseHelper = DatabaseHelper(applicationContext)
        var everyEmployee : MutableList<EmployeeModel> = databaseHelper.getAllData()
        var adapter = EmployeeAdapter(everyEmployee)

        binding.ryclrView.adapter = adapter
        binding.ryclrView.layoutManager = LinearLayoutManager(this)

        binding.btnSave.setOnClickListener(){
            lateinit var employeeModel: EmployeeModel
            try {
                var name = binding.etName.text.toString()
                var salary = binding.etSalary.text.toString().toInt()

                employeeModel = EmployeeModel(-1,name,salary)
                adapter.employeeModel.add(employeeModel)

                var databaseHelper = DatabaseHelper(applicationContext)
                databaseHelper.addOne(employeeModel)
                Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()

            }catch (e:Exception){
                Toast.makeText(applicationContext,"Error Occurred",Toast.LENGTH_LONG).show()
            }

        }

        binding.btnView.setOnClickListener(){
            var databaseHelper = DatabaseHelper(applicationContext)
            var everyEmployee : List<EmployeeModel> = databaseHelper.getAllData()
            Toast.makeText(applicationContext,everyEmployee.toString(),Toast.LENGTH_SHORT).show()
        }
    }
}