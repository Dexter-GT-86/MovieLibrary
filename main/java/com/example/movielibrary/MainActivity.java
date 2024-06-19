package com.example.movielibrary;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Database;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movielibrary.provider.MovieDB;
import com.example.movielibrary.provider.MovieViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity  {

    EditText inputTextTitle,inputTextYear,inputTextCountry,inputTextGenre,inputTextCost,inputTextKeyword,inputTextRating;
    TextView movieNum;
    //public int countNum;
    //public String countNumString;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView nav;

    ListView listView;

    ArrayList<String> dataSource;
    ArrayAdapter<String> adapter;

    ArrayList<Movie> movie_dataSource = new ArrayList<>(); //dataset for recycler with customised objects

    //week8 firebase
    DatabaseReference mRef;
    DatabaseReference mRef2;

    FirebaseDatabase database;

    //datasource week7
    private MovieViewModel movieViewModel;


    public void addToFirebase(){
        mRef = database.getInstance().getReference("/movies");
        mRef2 = database.getInstance().getReference("/bigBudget");

        String mTitle = inputTextTitle.getText().toString();
        String mYear = inputTextYear.getText().toString();
        String mCountry = inputTextCountry.getText().toString();
        String mGenre = inputTextGenre.getText().toString();
        String mCost = inputTextCost.getText().toString();
        String mKeywords = inputTextKeyword.getText().toString();
        String mRating = inputTextRating.getText().toString();

        if(!(mTitle.matches("")||
                mYear.matches("")||
                mCountry.matches("")||
                mGenre.matches("")||
                mCost.matches("")||
                mKeywords.matches("")||
                mRating.matches("")
        )){

            MovieDB moviedb = new MovieDB(mTitle,  Integer.parseInt(mYear), mCountry, mGenre, Integer.parseInt(mCost), mKeywords, Integer.parseInt(mRating));
           if (Integer.parseInt(mCost)>40){
               mRef2.push().setValue(moviedb);
           }else{
            mRef.push().setValue(moviedb);}

        }

    }

    public void clearAllFirebase(){
        mRef = database.getInstance().getReference("/movies");
        mRef2 = database.getInstance().getReference("/bigBudget");
        mRef.setValue(null);
        mRef2.setValue(null);



    }


    public void addDataToDatabase(){


            String mTitle = inputTextTitle.getText().toString();
            String mYear = inputTextYear.getText().toString();
            String mCountry = inputTextCountry.getText().toString();
            String mGenre = inputTextGenre.getText().toString();
            String mCost = inputTextCost.getText().toString();
            String mKeywords = inputTextKeyword.getText().toString();
            String mRating = inputTextRating.getText().toString();

        if(!(mTitle.matches("")||
                mYear.matches("")||
                mCountry.matches("")||
                mGenre.matches("")||
                mCost.matches("")||
                mKeywords.matches("")||
                mRating.matches("")
        )){

            MovieDB moviedb = new MovieDB(mTitle,  Integer.parseInt(mYear), mCountry, mGenre, Integer.parseInt(mCost), mKeywords, Integer.parseInt(mRating));

            movieViewModel= new ViewModelProvider(this).get(MovieViewModel.class);
            movieViewModel.insert(moviedb);
        }

    }

    public void toDatabase() {
        Intent intent = new Intent(this, DatabaseActivity.class);
        startActivity(intent);
    }

    public void clearDatabase(){
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.deleteAll();

    }

    //week6
    public void listAllMovies(){
        Intent intent = new Intent(this, RecyclerActivity.class);
        intent.putExtra("All_Movies", new Gson().toJson(movie_dataSource));
        startActivity(intent);
    }


    public void addMovieToDataSource(){
        String mTitle = inputTextTitle.getText().toString();
        String mYear = inputTextYear.getText().toString();
        String mCountry = inputTextCountry.getText().toString();
        String mGenre = inputTextGenre.getText().toString();
        String mCost = inputTextCost.getText().toString();
        String mKeywords = inputTextKeyword.getText().toString();
        String mRating = inputTextRating.getText().toString();
        Movie movie = new Movie(mTitle,  mYear, mCountry, mGenre, mCost, mKeywords, mRating);
        movie_dataSource.add(movie);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_list,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.options_menu_clear_all_id){
            clearAll();
        }
        if(id==R.id.options_lower_case_title_id){
            String tempString;
            tempString = inputTextTitle.getText().toString();
            tempString = tempString.toLowerCase(Locale.ROOT);
            inputTextTitle.setText(tempString);
        }
        if(id==R.id.options_delete_before2010_id){
            movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
            movieViewModel.deleteMovieOlderThan2010();
        }
        if(id==R.id.options_delet_cost_morethan20_id){
            movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
            movieViewModel.deleteMovieCostAbove20();
        }
        return true;
    }




    public class NavHandler implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id=item.getItemId();

            switch (id){
                case R.id.drawer_layout_add_item_id:
                    addItemToList();
                    addMovieToDataSource();
                    addDataToDatabase();
                    addDataToDatabase();
                    break;
                case R.id.drawer_layout_remove_last_item_id:
                    removeLastItem();
                    break;
                case R.id.drawer_layout_remove_all_item_id:
                    removeAllItem();
                    clearAllFirebase();
                    break;
                case R.id.drawer_double_cost_id:
                    doubleCost();
                    break;
                case R.id.drawer_list_all_movies_id:
//                    Intent i = new Intent(getApplicationContext(), RecyclerActivity.class);
//                    startActivity(i);
                    listAllMovies();
                    break;
                case R.id.databaseNav:
                    toDatabase();
                    break;
                case R.id.databaseClear:
                    clearDatabase();
                    clearAllFirebase();
                    break;
                default:
            }

            drawerLayout.closeDrawer(GravityCompat.START);

            return true;
        }
    }

    private void addItemToList(){
        if(!inputTextTitle.getText().toString().matches("") && !inputTextYear.getText().toString().matches("")){
            String Title = inputTextTitle.getText().toString();
            String Year = inputTextYear.getText().toString();
            String inputVal = Title+ "|" + Year;
            dataSource.add(inputVal);
            adapter.notifyDataSetChanged();
        }else {
            showSnackbar();
        }
    }

    private void removeLastItem(){
        if(dataSource.size()>0){
            dataSource.remove(dataSource.size()-1);
            adapter.notifyDataSetChanged();
        }
    }

    private void removeAllItem(){
        dataSource.clear();
        adapter.notifyDataSetChanged();
    }

    private void doubleCost(){
        int temStore;
        temStore = Integer.parseInt( inputTextCost.getText().toString());
        temStore*=2;
        inputTextCost.setText(temStore+"");

    }

    private void clearAll(){
        inputTextTitle.setText("");
        inputTextYear.setText("");
        inputTextCountry.setText("");
        inputTextGenre.setText("");
        inputTextCost.setText("");
        inputTextKeyword.setText("");
        inputTextRating.setText("");
    }

    private void showSnackbar(){

        Snackbar.make(drawerLayout,"Defaults",Snackbar.LENGTH_LONG).setAction("Yes", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Do some logic here
                inputTextTitle.setText("batman");
                inputTextYear.setText("2022");
                inputTextCountry.setText("us");
                inputTextGenre.setText("dsafds");
                inputTextCost.setText("50");
                inputTextKeyword.setText("sdafsa");
                inputTextRating.setText("1");
            }
        }).show();
    }

    float xDown, xUp, yDown, yUp;
    int diffX, diffY;


    public void registerGestureListener(){
        View view = findViewById(R.id.frame_id);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getActionMasked();
                xUp = motionEvent.getX();
                yUp = motionEvent.getY();

                diffX = (int)(xUp - xDown);
                diffY = (int)(yUp - yDown);


                switch (action){
                    case(MotionEvent.ACTION_DOWN):
                        xDown = motionEvent.getX();
                        yDown = motionEvent.getY();
                        return true;
                    case(MotionEvent.ACTION_MOVE):

                        return true;
                    case(MotionEvent.ACTION_UP):
                        if (Math.abs(diffY)<150){
                            //L-R
                            if(diffX>0){
                                Toast.makeText(getApplicationContext(),"L-R",Toast.LENGTH_SHORT).show();
                                addDataToDatabase();
                            }
                            else if(diffX<0){
                                Toast.makeText(getApplicationContext(),"R-L",Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            //U-B
                            if (diffY > 0) {
                                Toast.makeText(getApplicationContext(), "U-B", Toast.LENGTH_SHORT).show();
                                clearAll();
                            } else if (diffY < 0) {
                                Toast.makeText(getApplicationContext(), "B-U", Toast.LENGTH_SHORT).show();
                            }
                        }
                    default:
                        return false;
                    }


                }




        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.drawer_layout);

        //Log.d(TAG, "onCreate");


        //week10
        registerGestureListener();





        //SharedPreferences sp = getSharedPreferences("movieInfo", MODE_PRIVATE);
        Log.i("myLifeCycleMethod", "This is onCreate");


        toolbar=findViewById(R.id.toolbar_id);
        drawerLayout=findViewById(R.id.drawer_layout_id);
        nav=findViewById(R.id.nav_id);
        listView=findViewById(R.id.listview_id);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavHandler());


        dataSource=new ArrayList<>();
//        adapter=new ArrayAdapter<String>(this, R.layout.list_view_item_layout,dataSource);//
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,dataSource);//
        listView.setAdapter(adapter);

        FloatingActionButton fab=findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemToList();
                addMovieToDataSource();
                addDataToDatabase();
                addToFirebase();
            }
        });




//button
        Button addMovie = findViewById(R.id.buttonAddmovie); //find button addmovie
        Button movieInfo = findViewById(R.id.buttonmovieinfo); //find button movieInfo
        Button clearMovie = findViewById(R.id.buttonclear); //find button clearMovie
        Button previewMovie = findViewById(R.id.buttonPreview);
        //Button doubleCost = findViewById(R.id.buttonDoubleCost);

//find input text
        inputTextTitle = findViewById(R.id.inputTitle);
        inputTextYear = findViewById(R.id.inputYear);
        inputTextCountry = findViewById(R.id.inputCountry);
        inputTextGenre = findViewById(R.id.inputGenre);
        inputTextCost = findViewById(R.id.inputCost);
        inputTextKeyword = findViewById(R.id.inputKeyword);
        inputTextRating = findViewById(R.id.inputRating);

       // movieNum = findViewById(R.id.textView2);

        /* Request permissions to access SMS */
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0);
        /* Create and instantiate the local broadcast receiver
           This class listens to messages come from class SMSReceiver
         */
        MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver();

        /*
         * Register the broadcast handler with the intent filter that is declared in
         * class SMSReceiver @line 11
         * */
        registerReceiver(myBroadCastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER));


//addmovie button
        addMovie.setOnClickListener(new View.OnClickListener(){ //make a listener service run in the background
            @Override
            public void onClick(View view) {
                String movieTitle = inputTextTitle.getText().toString(); //store the input text in string
                if (movieTitle.matches("")){          //if empty
                    Toast.makeText(MainActivity.this,
                            "please enter the movie title",
                            Toast.LENGTH_LONG).show() ;
                }else{                                      //otherwise show the movie title
                Toast.makeText(MainActivity.this,
                        movieTitle+" is added",
                    Toast.LENGTH_LONG).show() ;
                    addItemToList();
                    addMovieToDataSource();
                    addDataToDatabase();

                }
            }
        }
        );
//movie info button
        movieInfo.setOnClickListener(new View.OnClickListener(){ //make a listener service run in the background
            @Override
            public void onClick(View view) {
                String movieTitle = inputTextTitle.getText().toString(); //store the input text in string
                String movieYear = inputTextYear.getText().toString();
                String movieRating = inputTextRating.getText().toString();
                if (movieTitle.matches("")|| movieYear.matches("") ||movieRating.matches("")){          //if empty
                    Toast.makeText(MainActivity.this, "please fill all of the title, year, rating", Toast.LENGTH_LONG).show() ;
                }else{                                      //otherwise show the movie title
                    Toast.makeText(MainActivity.this, "Movie Title: " + movieTitle+"\nMovie Year: "+ movieYear + "\nMovie Rate: "+movieRating, Toast.LENGTH_LONG).show() ;
                }
            }
        }
        );
//clear movie button
        clearMovie.setOnClickListener(new View.OnClickListener(){ //make a listener service run in the background
            @Override
            public void onClick(View view) {
                inputTextTitle.setText("");
                inputTextYear.setText("");
                inputTextCountry.setText("");
                inputTextGenre.setText("");
                inputTextCost.setText("");
                inputTextKeyword.setText("");
                inputTextRating.setText("");
                //movieNum.setText("0");
            }
        }
        );

//double cost button
//        doubleCost.setOnClickListener(new View.OnClickListener(){ //make a listener service run in the background
//              @Override
//              public void onClick(View view) {
//                  int temStore;
//                  SharedPreferences sp = getSharedPreferences("movieInfo", MODE_PRIVATE);
//                  SharedPreferences.Editor editor = sp.edit();
//
//                  temStore = Integer.parseInt(sp.getString("movieCost",""));
//                  temStore*=2;
//                  editor.putString("movieCost", temStore +"");
//                  editor.apply();
//                //  inputTextCost.setText(sp.getString("movieCost",""));
//              }
//          }
//        );



    }//onCreate()finish






    @Override
    protected void onStart(){
        super.onStart();
        restore();
        Log.i("myLifeCycleMethod", "This is onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("myLifeCycleMethod", "This is onResume");
    }

//app running
    @Override
    protected void onPause() {
        super.onPause();
        store();
        Log.i("myLifeCycleMethod", "This is onPause");



    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i("myLifeCycleMethod", "This is onStop");



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("myLifeCycleMethod", "This is onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("myLifeCycleMethod", "This is onRestart");
    }



    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState); //Must call super to let Android save view-state
        //Must code to manually save non-view state in Bundle
        String movieTitle = inputTextTitle.getText().toString();
        String movieGenre = inputTextGenre.getText().toString().toLowerCase();

        outState.putString("SavedTitle", movieTitle);

        outState.putString("SavedGenre", movieGenre);


    }

    protected void onRestoreInstanceState(@NonNull Bundle  inState){
        super.onRestoreInstanceState(inState);
        // Log.d(TAG, "onRestoreInstanceState");
        String movieTitle = inState.getString("SavedTitle","").toUpperCase();
        inputTextTitle.setText(movieTitle);

        String movieGenre =  inState.getString("SavedGenre","");
        inputTextGenre.setText(movieGenre);
    }

    public void store(){
        SharedPreferences sp = getSharedPreferences("movieInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();


        editor.putString("movieTitle", inputTextTitle.getText().toString());
        editor.putString("movieYear", inputTextYear.getText().toString());
        editor.putString("movieCountry", inputTextCountry.getText().toString());
        editor.putString("movieGenre", inputTextGenre.getText().toString());
        editor.putString("movieCost", inputTextCost.getText().toString());
        editor.putString("movieKeywords", inputTextKeyword.getText().toString());
        editor.putString("movieRating", inputTextRating.getText().toString());
        //editor.putString("movieNum", movieNum.getText().toString());


        editor.apply();
    }



    public void restore(){
        SharedPreferences sp = getSharedPreferences("movieInfo", MODE_PRIVATE);
        inputTextTitle.setText(sp.getString("movieTitle",""));
        inputTextYear.setText( sp.getString("movieYear","") );
        inputTextCountry.setText(sp.getString("movieCountry",""));
        inputTextGenre.setText(sp.getString("movieGenre",""));
        inputTextCost.setText( sp.getString("movieCost",""));
        inputTextKeyword.setText(sp.getString("movieKeywords",""));
        inputTextRating.setText(sp.getString("movieRating",""));
        //movieNum.setText(sp.getString("movieNum",""));
        //countNum = Integer.parseInt(sp.getString("movieNum",""));

    }

    public void preview(View view){
        restore();
    }

//    You can just query the db and get the data you want and store it in a normal fashion in variables.
//
//    SharedPreferences is mainly used when you want to retain data between different app executions.
//
//    Bundle is used when you want to safely transfer data between activities or between an activity and a fragment.



    class MyBroadCastReceiver extends BroadcastReceiver {

        /*
         * This method 'onReceive' will get executed every time class SMSReceive sends a broadcast
         * */
        @Override
        public void onReceive(Context context, Intent intent) {
            /*
             * Retrieve the message from the intent
             * */
            String msg = intent.getStringExtra(SMSReceiver.SMS_MSG_KEY);
            /*
             * String Tokenizer is used to parse the incoming message
             * The protocol is to have the account holder name and account number separate by a semicolon
             * */

            StringTokenizer sT = new StringTokenizer(msg, ";");
            String Title = sT.nextToken();
            String Year = sT.nextToken();
            String Country = sT.nextToken();
            String Genre = sT.nextToken();
            String Cost = sT.nextToken();
            String Keyword = sT.nextToken();
            String Rating = sT.nextToken();
            /*
             * Now, its time to update the UI
             * */
            inputTextTitle.setText(Title);
            inputTextYear.setText(Year);
            inputTextCountry.setText(Country);
            inputTextGenre.setText(Genre);
            inputTextCost.setText(Cost);
            inputTextKeyword.setText(Keyword);
            inputTextRating.setText(Rating);
        }
    }

}

//    protected void onRestoreInstanceState(@NonNull Bundle  savedInstanceState){
//        super.onRestoreInstanceState(savedInstanceState);
//       // Log.d(TAG, "onRestoreInstanceState");
//        String movieTitle = savedInstanceState.getString("SavedTitle","").toUpperCase();
//        inputTextTitle.setText(movieTitle);
//
//        String movieGenre =  savedInstanceState.getString("SavedGenre","");
//        inputTextGenre.setText(movieGenre);
//    }