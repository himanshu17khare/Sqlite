package com.example.himanshu.pets;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.himanshu.pets.data.PetContract;
import com.example.himanshu.pets.data.PetDbHelper;
import com.example.himanshu.pets.data.PetProvider;
public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    PetDbHelper mDbHelper;
    Uri newUri;
    static final String[] PROJECTION = new String[] {PetContract.PetEntry._id,PetContract.PetEntry.name,
           PetContract.PetEntry.breed};
    private static final int PET_LOADER=0;
    PetCursorAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent.setData(null));
            }
        });
        ListView listView=findViewById(R.id.listView);
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);
        mAdapter=new PetCursorAdapter(this,null);
        listView.setAdapter(mAdapter);
        getLoaderManager().initLoader(PET_LOADER, null, this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(CatalogActivity.this,EditorActivity.class).setData(ContentUris.withAppendedId(PetContract.PetEntry.CONTENT_URI,id)));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertPet() {
            // Create a ContentValues object where column names are the keys,
            // and Toto's pet attributes are the values.
            ContentValues values = new ContentValues();
            values.put(PetContract.PetEntry.name, "Toto");
            values.put(PetContract.PetEntry.breed, "Terrier");
            values.put(PetContract.PetEntry.gender, PetContract.PetEntry.MALE);
            values.put(PetContract.PetEntry.weight, 7);
            // Insert a new row for Toto into the provider using the ContentResolver.
            // Use the {@link PetEntry#CONTENT_URI} to indicate that we want to insert
            // into the pets database table.
            // Receive the new content URI that will allow us to access Toto's data in the future.
             newUri = getContentResolver().insert(PetContract.PetEntry.CONTENT_URI, values);

    }
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i,  Bundle bundle) {
        // Now create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.
        return new CursorLoader(this, PetContract.PetEntry.CONTENT_URI,
                PROJECTION, null, null, null);
    }
    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }
    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
       mAdapter.swapCursor(null);
    }
}
