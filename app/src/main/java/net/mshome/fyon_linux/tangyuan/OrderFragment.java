package net.mshome.fyon_linux.tangyuan;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by fyon on 11/11/16.
 */
public class OrderFragment extends Fragment {
    OrderListener activityCallback;

    public interface OrderListener{
        public void addOrder(String name, String pic);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order, container, false);
        Button addOrderBtn = (Button)rootView.findViewById(R.id.addOrder);
        addOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityCallback.addOrder("test","test");
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            activityCallback = (OrderListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement OrderFragment interface");
        }
    }
}
