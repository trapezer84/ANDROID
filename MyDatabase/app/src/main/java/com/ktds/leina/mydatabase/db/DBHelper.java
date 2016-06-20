package com.ktds.leina.mydatabase.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.ktds.leina.mydatabase.vo.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 206-025 on 2016-06-20.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 2;
    private Context context;

    // context, db명, factory, version 버전
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    /**
     * Datebase가 존재하지 않을 때 한번 실행된다.
     * DB를 만드는 역할을 한다.
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE TEST_TABLE ( ");
        sb.append("_ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" NAME TEXT,");
        sb.append(" AGE INTEGER, ");
        sb.append(" PHONE TEXT, ");
        sb.append(" ADDRESS TEXT); ");

        // sql 실행
        db.execSQL(sb.toString());
        Toast.makeText(context, "Table 생성됨....", Toast.LENGTH_SHORT).show();
    }

    /**
     * Apppplication의 버전이 올라가 Table의 구조가 변경되었을 때
     * 실행된다.
     *
     * 예를 들어 컬럼이 추가되었을 경우 onUpgrage에서 구현한다...
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if( oldVersion == 1 && newVersion == 2) {

            StringBuffer sb = new StringBuffer();
            sb.append(" ALTER TABLE TEST_TABLE ADD ADDRESS TEXT ");

            db.execSQL(sb.toString());
        }

        Toast.makeText(context, "Version 올라감...", Toast.LENGTH_SHORT).show();
    }

    public void testDB() {
        SQLiteDatabase db = getReadableDatabase();
    }

    public void addPerson(Person person) {

        // 1. 쓰기 가능한 DB 객체를 가져온다.
        SQLiteDatabase db = getWritableDatabase();
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO TEST_TABLE ( ");
        sb.append(" NAME, AGE, PHONE, ADDRESS ) ");
        sb.append(" VALUES ( ?, ?, ?, ? ) ");


        db.execSQL(sb.toString(), new Object[]{
                person.getName(),
                Integer.parseInt(person.getAge()),
                person.getPhone(),
                person.getAddress()
        });

        Toast.makeText(context, "insert 완료...", Toast.LENGTH_SHORT).show();

    }

    public List<Person> getAllPersons() {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT _ID, NAME, AGE, PHONE, ADDRESS FROM TEST_TABLE");

        // 읽기 전용 DB 객체를 가져온다.
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(sb.toString(), null);
        List<Person> persons = new ArrayList<Person>();

        Person person = null;
        while ( cursor.moveToNext() ) {
            person = new Person();
            person.set_id(cursor.getInt(0));
            person.setName(cursor.getString(1));
            persons.add(person);
        }

        return  persons;
    }
}
