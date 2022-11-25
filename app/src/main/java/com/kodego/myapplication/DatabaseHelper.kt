package com.kodego.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context : Context): SQLiteOpenHelper(context, "Company.db", null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var createTable: String = "CREATE TABLE EMPLOYEE_TABLE (id integer primary key autoincrement, name varchar(30), salary int)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //no function
    }

    fun addOne(employeeModel: EmployeeModel){
        var db = this.writableDatabase
        var cv = ContentValues()

        cv.put("name",employeeModel.name)
        cv.put("salary", employeeModel.salary)

        db.insert("EMPLOYEE_TABLE", null,cv)
    }

    fun getAllData():MutableList<EmployeeModel>{
        var returnlist = ArrayList<EmployeeModel>()
        var queryString = "SELECT * FROM EMPLOYEE_TABLE"
        var db = this.readableDatabase

        var cursor : Cursor = db.rawQuery(queryString,null)

        if (cursor.moveToFirst()){
            do {
                var id = cursor.getInt(0)
                var name = cursor.getString(1)
                var salary = cursor.getInt(2)

                var newEmployeeModel: EmployeeModel = EmployeeModel(id,name,salary)
                returnlist.add(newEmployeeModel)

            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return returnlist
    }
}