package ir.elegam.doctor.Activity;

import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ir.elegam.doctor.Adapter.ExpandableListAdapter;
import ir.elegam.doctor.Classes.Variables;
import ir.elegam.doctor.Database.database;
import ir.elegam.doctor.R;

public class QuestionActivity extends AppCompatActivity {

    private Typeface San;
    private Toolbar toolbar;
    private TextView txtToolbar;
    private FloatingActionButton fab;
    private database db;
    private ExpandableListView elv;
    private List<String> listDataHeader = new ArrayList<>();
    private HashMap<String, List<String>> listDataChild;
    private ExpandableListAdapter listAdapter;
    private View snack_view;
    private String faction = Variables.getFaq;
    private String QuestionCategory="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        define();
        getWhat();

    }// end onCreate()

    private void define(){
        db = new database(this);

        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar_question);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar);
        txtToolbar.setTypeface(San);
        txtToolbar.setText("سوالات متداول");

        fab = (FloatingActionButton) findViewById(R.id.fab_question);
        elv = (ExpandableListView) findViewById(R.id.elv_question);
        registerForContextMenu(elv);

    }// end define()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch(id){
            case android.R.id.home:
                finish();
                break;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void getWhat() {
        QuestionCategory = getIntent().getStringExtra("faq");
        txtToolbar.setText(QuestionCategory);

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        db.open();
        int count = db.CountAllService("Faction",faction,"Category",QuestionCategory);
        Log.i(Variables.Tag,"count: "+count);
        db.close();

        db.open();
        for(int i=0;i<count;i++){
            listDataHeader.add(db.DisplayAll(i,3,"Faction",faction,"Category",QuestionCategory));
            List<String> comingSoon = new ArrayList<>();
            comingSoon.add(db.DisplayAll(i,4,"Faction",faction,"Category",QuestionCategory));
            listDataChild.put(listDataHeader.get(i), comingSoon);
        }
        db.close();
        Refresh();

    }// end getWhat()

    private void Refresh() {
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        elv.setAdapter(listAdapter);
    }

}// end class
