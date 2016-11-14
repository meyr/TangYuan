package net.mshome.fyon_linux.tangyuan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by fyon on 11/11/16.
 */

public class ToDoFragment
        extends Fragment
        implements OrderFragment.OrderListener{

    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private ArrayList<Actor> actors = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_todo, container, false);

        // 拿到RecyclerView
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list);
        // 设置LinearLayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        // 设置ItemAnimator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        mRecyclerView.setHasFixedSize(true);
        // 初始化自定义的适配器
        //myAdapter = new MyAdapter(this, actors);
        myAdapter = new MyAdapter(this.getContext(), actors);
        // 为mRecyclerView设置适配器
        mRecyclerView.setAdapter(myAdapter);

        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(mRecyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipeLeft(int position) {
                                return true;
                            }

                            @Override
                            public boolean canSwipeRight(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions){
                                    actors.remove(position);
                                    //actors.remove(position);
                                    myAdapter.notifyItemRemoved(position);
                                }

                                myAdapter.notifyDataSetChanged();

                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions){
                                    actors.remove(position);
                                    //actors.remove(position);
                                    myAdapter.notifyItemRemoved(position);
                                }

                                myAdapter.notifyDataSetChanged();
                            }
                        });

        mRecyclerView.addOnItemTouchListener(swipeTouchListener);

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // restore arraylist
        if(savedInstanceState != null){
            actors = savedInstanceState.getParcelableArrayList("ACTORS");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("ACTORS", actors);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void addOrder(Actor actor){
            actors.add(actor);
            //actors.add(new Actor(names[myAdapter.getItemCount()], pics[myAdapter.getItemCount()]));
            //mRecyclerView.scrollToPosition(myAdapter.getItemCount() - 1);
            myAdapter.notifyDataSetChanged();
    }
}