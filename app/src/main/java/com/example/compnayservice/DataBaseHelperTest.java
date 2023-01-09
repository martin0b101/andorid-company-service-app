package com.example.compnayservice;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelperTest extends SQLiteOpenHelper {

    /**
     * Tables of table user
     */
    public static final String USERS_TABLE = "USERS_TABLE";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_EMAIL = "USER_EMAIL";
    public static final String COLUMN_USER_PASS = "USER_PASS";
    public static final String COLUMN_USER_ID = "USER_ID";



    private String CREATE_USERS_TABLE = " CREATE TABLE " + USERS_TABLE
            + " (" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USER_NAME + " TEXT, "
            + COLUMN_USER_EMAIL + " TEXT, "
            + COLUMN_USER_PASS + " TEXT) ";

    private String DROP_TABLE_USER = " DROP TABLE IF EXIST " + USERS_TABLE;

    /**
     * Company table data.
     */
    public static final String COMPANY_TABLE = "COMPANY_TABLE";
    public static final String COLUMN_COMPANY_ID = "COLUMN_COMPANY_ID";
    public static final String COLUMN_COMPANY_NAME = "COLUMN_COMPANY_NAME";
    public static final String COLUMN_COMPANY_EMAIL = "COLUMN_COMPANY_EMAIL";
    public static final String COLUMN_VAT_NUMBER = "COLUMN_VAT_NUMBER ";
    public static final String COLUMN_COUNTRY = "COLUMN_COUNTRY";
    public static final String COLUMN_COMPANY_PASSWORD = "COLUMN_PASSWORD";
    public static final String COLUMN_WORK = "COLUMN_WORK";


    private String CREATE_COMPANY_TABLE = " CREATE TABLE " + COMPANY_TABLE
            + " (" + COLUMN_COMPANY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_COMPANY_NAME + " TEXT, "
            + COLUMN_COMPANY_EMAIL + " TEXT, "
            + COLUMN_VAT_NUMBER + " TEXT, "
            + COLUMN_COUNTRY + " TEXT, "
            + COLUMN_WORK + " TEXT, "
            + COLUMN_COMPANY_PASSWORD + " TEXT) ";

    private String DROP_TABLE_COMPANY = "DROP TABLE IF EXIST "+ COMPANY_TABLE;

    /**
     * Customer table data.
     */
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_ID = "CUSTOMER_ID";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_WORK = "CUSTOMER_WORK";
    public static final String COLUMN_CUSTOMER_TIME = "CUSTOMER_TIME";
    public static final String COLUMN_CUSTOMER_USER_ID = "USER_ID_F"; //foregin key
    public static final String COLUMN_CUSTOMER_EMAIL = "USER_EMAIL_F"; //foregin key
    public static final String COLUMN_CUSTOMER_COMPANY_ID = "COMPANY_ID_F"; //foregin key


    private String CREATE_CUSTOMER_TABLE = " CREATE TABLE " + CUSTOMER_TABLE
            + " (" + COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CUSTOMER_WORK +" TEXT, "
            + COLUMN_CUSTOMER_TIME + " TEXT, "
            + COLUMN_CUSTOMER_NAME + " TEXT, "
            + COLUMN_CUSTOMER_USER_ID + " TEXT, "
            + COLUMN_CUSTOMER_EMAIL + " TEXT, "
            + COLUMN_CUSTOMER_COMPANY_ID + " TEXT, "
            + "FOREIGN KEY (" + COLUMN_CUSTOMER_USER_ID + ") REFERENCES " + USERS_TABLE + "(" + COLUMN_USER_ID + "), "
            + "FOREIGN KEY (" + COLUMN_CUSTOMER_EMAIL + ") REFERENCES " + USERS_TABLE + "(" + COLUMN_USER_EMAIL + "), "
            + "FOREIGN KEY (" + COLUMN_CUSTOMER_COMPANY_ID + ") REFERENCES "+ COMPANY_TABLE + "(" + COLUMN_COMPANY_ID + ")" + ") ";

    private String DROP_TABLE_CUSTOMER  = "DROP TABLE IF EXIST " + CUSTOMER_TABLE;


    public DataBaseHelperTest(Context context) {
        super(context, "UserDatabase.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_COMPANY_TABLE);
        db.execSQL(CREATE_CUSTOMER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Adds new user in database.
     * @param userCustomer
     * @return
     */
    public boolean addUser(UserCustomer userCustomer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME, userCustomer.getName());
        cv.put(COLUMN_USER_EMAIL, userCustomer.getEmail());
        cv.put(COLUMN_USER_PASS, userCustomer.getPassword());

        long insert = db.insert(USERS_TABLE, null, cv);
        if (insert > -1)
            return true;
        return false;
    }

    /**
     * Check if user with email and password exist in database, and returns boolean.
     * @param userEmail
     * @param userPass
     * @return
     */
    public boolean loginUser(String userEmail, String userPass){
        SQLiteDatabase database = this.getReadableDatabase();

        String[] columns = {
                COLUMN_USER_ID
        };

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASS + " = ?";
        // selection arguments
        String[] selectionArgs = {userEmail, userPass};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = database.query(USERS_TABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        database.close();
        if (cursorCount > 0)
            return true;
        return false;
    }

    /**
     * Check if company with email and pass exist in database.
     * @param userEmail
     * @param userPass
     * @return
     */
    public boolean loginCompany(String userEmail, String userPass){
        SQLiteDatabase database = this.getReadableDatabase();

        String[] columns = {
                COLUMN_COMPANY_ID
        };

        // selection criteria
        String selection = COLUMN_COMPANY_EMAIL + " = ?" + " AND " + COLUMN_COMPANY_PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {userEmail, userPass};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = database.query(COMPANY_TABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        database.close();
        if (cursorCount > 0)
            return true;
        return false;
    }

    /**
     * get user id from database if you provied email.
     * @param userEmail
     * @return
     */
    public int getUserIdFromEmail(String userEmail){
        SQLiteDatabase database = this.getReadableDatabase();

        String[] columns = {
                COLUMN_USER_ID
        };

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";
        // selection arguments
        String[] selectionArgs = {userEmail};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        UserCustomer user;
        Cursor cursor = database.query(USERS_TABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        int userID = 0;
        if (cursor.moveToFirst() && cursorCount > 0){
            do{
                userID = Integer.parseInt(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return userID;
    }

    public int getCompanyIdFromEmail(String companyEmail){
        SQLiteDatabase database = this.getReadableDatabase();

        String[] columns = {
                COLUMN_COMPANY_ID
        };

        // selection criteria
        String selection = COLUMN_COMPANY_EMAIL + " = ?";
        // selection arguments
        String[] selectionArgs = {companyEmail};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        UserCustomer user;
        Cursor cursor = database.query(COMPANY_TABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        int companyID = 0;
        if (cursor.moveToFirst() && cursorCount > 0){
            do{
                companyID = Integer.parseInt(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return companyID;
    }


    /**
     * Adds new company to database when they register.
     * @param company
     * @return
     */
    public boolean addNewCompany(Company company){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_COMPANY_NAME, company.getCompanyName());
        contentValues.put(COLUMN_COMPANY_EMAIL, company.getEmail());
        contentValues.put(COLUMN_COUNTRY, company.getCountry());
        contentValues.put(COLUMN_VAT_NUMBER, company.getVATnumber());
        contentValues.put(COLUMN_COMPANY_PASSWORD, company.getPassword());
        contentValues.put(COLUMN_WORK, company.getWork());

        long insert = database.insert(COMPANY_TABLE, null, contentValues);
        if (insert > -1)
            return true;
        return false;
    }

    /**
     * Add new termin for company.
     * @param customer
     * @return
     */
    public boolean addNewCustomerToCompany(Customer customer){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_CUSTOMER_WORK, customer.getTypeOfWork());
        contentValues.put(COLUMN_CUSTOMER_NAME, customer.getCustomerName());
        contentValues.put(COLUMN_CUSTOMER_TIME, customer.getTime());
        //foreign keys
        contentValues.put(COLUMN_CUSTOMER_EMAIL, customer.getCustomerEmail());
        contentValues.put(COLUMN_CUSTOMER_COMPANY_ID, customer.getCompanyId());
        contentValues.put(COLUMN_CUSTOMER_USER_ID, customer.getUserCustomerId());

        long insert = database.insert(CUSTOMER_TABLE, null, contentValues);
        if (insert > -1) {
            return true;
        }
        return false;
    }

    /**
     * Makes list of all registered companies without sensitive data.
     * @return
     */
    public ArrayList<Company> getAllCompaniesForList(){

        ArrayList<Company> listOfAllCompanies = new ArrayList<>();
        String selection = "SELECT * FROM " + COMPANY_TABLE;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery(selection, null);

        if (cursor.moveToFirst()){
            do{
                int companyID = cursor.getInt(0);
                String comp_name = cursor.getString(1);
                String comp_email = cursor.getString(2);
                String comp_country = cursor.getString(3);
                String comp_work = cursor.getString(5);

                listOfAllCompanies.add(new Company(companyID, comp_name, "", comp_country, "", comp_email, comp_work));

            }while(cursor.moveToNext());
        }
        cursor.close();
        return listOfAllCompanies;
    }

    public ArrayList<Customer> getAllCustomers(int companyId){
        ArrayList<Customer> allCustomers = new ArrayList<>();
        String selection = "SELECT * FROM " + CUSTOMER_TABLE + " WHERE "+ COLUMN_CUSTOMER_COMPANY_ID +" = "+Integer.toString(companyId);
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery(selection, null);

        if (cursor.moveToFirst()){
            do{
                int user_id = cursor.getInt(4);
                String customer_work = cursor.getString(1);
                String customer_time = cursor.getString(2);
                String customer_email = cursor.getString(5);
                String customer_fullname = cursor.getString(3);
                String company_id = cursor.getString(6);

                allCustomers.add(new Customer(user_id, customer_fullname, customer_email, customer_work, customer_time, Integer.parseInt(company_id)));

            }while(cursor.moveToNext());
        }
        cursor.close();

        return allCustomers;
    }



}
