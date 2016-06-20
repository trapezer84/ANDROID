package com.ktds.leina.mydatabase;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ktds.leina.mydatabase.db.DBHelper;
import com.ktds.leina.mydatabase.vo.Person;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnCreateDatabase;
    private Button btnInsertData;
    private Button btnSelectAllData;
    private ListView lvPersons;

    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateDatabase = (Button) findViewById(R.id.btnCreateDatabase);
        btnInsertData = (Button) findViewById(R.id.btnInsertData);
        btnSelectAllData = (Button) findViewById(R.id.btnSelectAllData);
        lvPersons = (ListView) findViewById(R.id.lvPersons);

        btnCreateDatabase.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                lvPersons.setVisibility(View.INVISIBLE);

                //Doalof 로 database의 이름을 받아옴

                final EditText etDBName = new EditText(MainActivity.this);
                etDBName.setHint("DB명을 입력하세요.");
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog  .setTitle("Database 이름을 입력하세요")
                        .setMessage("Database 이름을 입력하세요")
                        .setView(etDBName)
                        .setPositiveButton("생성", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if( etDBName.getText().toString().length() > 0) {
                                    dbHelper = new DBHelper(MainActivity.this, etDBName.getText().toString(), null, DBHelper.DB_VERSION);
                                    dbHelper.testDB();
                                }


//                                Toast.makeText(MainActivity.this, etDBName.getText(), Toast.LENGTH_SHORT).show();
                            }
                        }).
                        setNeutralButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create().show();

            }
        });

        btnInsertData = (Button) findViewById(R.id.btnInsertData);
        btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lvPersons.setVisibility(View.INVISIBLE);

                LinearLayout layout = new LinearLayout(MainActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText etName = new EditText(MainActivity.this);
                etName.setHint("이름을 입력하세요");

                final EditText etAge = new EditText(MainActivity.this);
                etAge.setHint("나이를 입력하세요");

                final EditText etPhone = new EditText(MainActivity.this);
                etPhone.setHint("전화번호를 입력하세요");

                final EditText etAddress = new EditText(MainActivity.this);
                etAddress.setHint("주소를 입력하세요");

                layout.addView(etName);
                layout.addView(etAge);
                layout.addView(etPhone);
                layout.addView(etAddress);

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog  .setTitle("정보를 입력하세요")
                        .setView(layout)
                        .setPositiveButton("등록", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String name = etName.getText().toString();
                                String age = etAge.getText().toString();
                                String phone = etPhone.getText().toString();
                                String address = etAddress.getText().toString();

                                if(dbHelper == null) {
                                    dbHelper = new DBHelper(MainActivity.this, "TEST", null, DBHelper.DB_VERSION);
                                }

                                Person person = new Person();
                                person.setName(name);
                                person.setAge(age);
                                person.setPhone(phone);
                                person.setAddress(address);

                                dbHelper.addPerson(person);

                                Toast.makeText(MainActivity.this, name + " / " + age + " / " + phone + "/" + address, Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNeutralButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create().show();
            }
        });

        btnSelectAllData = (Button) findViewById(R.id.btnSelectAllData);
        btnSelectAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // List View를 보여준다.
                lvPersons.setVisibility(View.VISIBLE);

                // 1. Person 데이터를 가져온다.
                if (dbHelper == null) {
                    dbHelper = new DBHelper(MainActivity.this, "TEST", null, DBHelper.DB_VERSION);

                }

                List<Person> persons = dbHelper.getAllPersons();

                // 2. ListView에 Person 데이터를 모두 보여준다.
                lvPersons.setAdapter(new PersonListAdaptor(persons, MainActivity.this));

            }
        });

    }

    private class PersonListAdaptor extends BaseAdapter {

        private List<Person> persons;
        private Context context;

        public PersonListAdaptor(List<Person> persons, Context context) {
            this.persons = persons;
            this.context = context;
        }

        @Override
        public int getCount() {
            return this.persons.size();
        }

        @Override
        public Object getItem(int position) {
            return this.persons.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Holder holder = null;

            // 아이템을 만들지 않고 바로 여기에서 구현시킨다.
            if (convertView == null) {
                convertView = new LinearLayout(context);
                ((LinearLayout) convertView).setOrientation(LinearLayout.HORIZONTAL);

                TextView tvId = new TextView(context);
                TextView tvName = new TextView(context);

                ((LinearLayout) convertView).addView(tvId);
                ((LinearLayout) convertView).addView(tvName);

                holder = new Holder();
                holder.tvId = tvId;
                holder.tvName = tvName;

                convertView.setTag(holder);
            }
            else {
                holder = (Holder) convertView.getTag();
            }

            Person person = (Person) getItem(position);
            holder.tvId.setText(person.get_id() + "");
            holder.tvName.setText(person.getName());

            return convertView;
        }
    }

    private class Holder {

        public  TextView tvId;
        public  TextView tvName;

    }
}
