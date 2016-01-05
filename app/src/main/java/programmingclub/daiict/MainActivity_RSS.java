package programmingclub.daiict;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omkar13 on 12/17/2015.
 */
public class MainActivity_RSS extends Activity implements AdapterView.OnItemClickListener{

    private TextView stickyView;
    private ListView listView;
    private View heroImageView;
    private View stickyViewSpacer;

    private int MAX_ROWS = 20;

    public void onCreate(Bundle savedInstanceState){


        super.onCreate(savedInstanceState);
        setContentView(R.layout.various_rss_list);

        listView = (ListView) findViewById(R.id.rssListView);
        heroImageView = findViewById(R.id.heroImageView); //containing the photo
        stickyView = (TextView) findViewById(R.id.stickyView);


        /* Inflate list header layout */
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listHeader = inflater.inflate(R.layout.list_header_layout, null); //this just contains spaces, inflator method generates a view object from the layout xml file
        stickyViewSpacer = listHeader.findViewById(R.id.stickyViewPlaceholder); //findViewById can also be uesd to find views within inflated layouts contained in a view like above
                                                                                //stickyViewSpacer is a just space of 50dp.
         /* Add list view header */
        listView.addHeaderView(listHeader); //this gives the listview a header which currently only contains spaces



        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                /* Check if the first item is already reached to top.*/
                if (listView.getFirstVisiblePosition() == 0) { //if the first list item seen on screen is indeed the first item of the list(this might not happen always)
                    View firstChild = listView.getChildAt(0);
                    int topY = 0;
                    if (firstChild != null) {
                        topY = firstChild.getTop(); //getTop gives the Y coordinate of the view
                    }

                    int heroTopY = stickyViewSpacer.getTop();
                    stickyView.setY(Math.max(0,heroTopY+topY));

                    /* Set the image to scroll half of the amount that of ListView */
                    heroImageView.setY(topY * 0.5f);
                }
            }
        }); //defines how the layout will change when a scroll happens



        listView.setOnItemClickListener(this); //this is the use of the inbult listener

        List<String> l =new ArrayList<String>();

        l.add("PCWorld news");
        l.add("Technology news");
        l.add("android news");
        l.add("startUp news");
        l.add("gaming news");
        l.add("open Source news");
        l.add("internet news");
        l.add("software news");
        //LinearLayout ll=(LinearLayout ) findViewById(R.id.container_layout);
/*
        StikkyHeaderBuilder.stickTo(listView)
                .setHeader(R.id.header, ll)
                .minHeightHeader(250)
                .build();
*/
        RssListAdapter a=new RssListAdapter(this,l); //the list adapter has a method which gives you a view containing an embedded text view or a tag
        listView.setAdapter(a); // set adapter to populate the listView, why not use a arrayadapter?
/*
        TextView textView1=(TextView) findViewById(R.id.tv1);
        TextView textView2=(TextView) findViewById(R.id.tv2);
        TextView textView3=(TextView) findViewById(R.id.tv3);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MainAc", "click on tv1 detected");
                //startActivity(new Intent(MainActivity.this, PCWorldActivity.class));
                //addPCWorldFragment();
                Intent i=new Intent(MainActivity.this, com.example.omkar13.programmingclubapp.FragmentActivity.class);
                i.putExtra("feedNo",1);
                startActivity(i);
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("MainAc", "click on tv2 detected");
                //startActivity(new Intent(MainActivity.this,TechnologyAppActivity.class));
                //addTechFragment();
                Intent i=new Intent(MainActivity.this, com.example.omkar13.programmingclubapp.FragmentActivity.class);
                i.putExtra("feedNo",2);
                startActivity(i);
            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("MainAc", "click on tv3 detected");
                //startActivity(new Intent(MainActivity.this,TechnologyAppActivity.class));
                //addTechFragment();
                Intent i=new Intent(MainActivity.this, com.example.omkar13.programmingclubapp.FragmentActivity.class);
                i.putExtra("feedNo",3);
                startActivity(i);
            }
        });
*/
    }


    @Override //call back method for the inbult listView listerer
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    /*
        RssAdapter adapter = (RssAdapter) parent.getAdapter();
        RssItem item = (RssItem) adapter.getItem(position);
        Uri uri = Uri.parse(item.getLink());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    */
        Intent i=new Intent(MainActivity_RSS.this, programmingclub.daiict.FragmentActivity.class);////////////
        boolean listItemClick=false;

        switch (position){

            case 1:  i.putExtra("feedNo", 1);
                    listItemClick=true;
                    break;
            case 2:  i.putExtra("feedNo", 2);
                    listItemClick=true;
                    break;
            case 3: i.putExtra("feedNo", 3);
                     listItemClick=true;
                    break;
            case 4: i.putExtra("feedNo", 4);
                    listItemClick=true;
                    break;
            case 5: i.putExtra("feedNo", 5);
                     listItemClick=true;
                     break;
            case 6: i.putExtra("feedNo", 6);
                listItemClick=true;
                break;
            case 7: i.putExtra("feedNo", 7);
                listItemClick=true;
                break;
            case 8: i.putExtra("feedNo", 8);
                listItemClick=true;
                break;

        }

        if(listItemClick)
            startActivity(i);

    }
}
