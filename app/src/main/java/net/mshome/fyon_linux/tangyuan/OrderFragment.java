package net.mshome.fyon_linux.tangyuan;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by fyon on 11/11/16.
 */
public class OrderFragment extends Fragment {
    OrderListener activityCallback;
    RadioGroup mRG;
    TextView sian_big_num, sian_small_num;
    Button sian_big_pls,sian_big_mns, sian_small_pls, sian_small_mns;

    public interface OrderListener{
        public void addOrder(Actor actor);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_order, container, false);
        Button addOrderBtn = (Button)rootView.findViewById(R.id.addOrder);
        mRG = (RadioGroup) rootView.findViewById(R.id.inout);
        addOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Actor act = new Actor();
                int id = mRG.getCheckedRadioButtonId();
                if(id == R.id.indoor)
                    act.setInout(true);
                int num_big_sian = Integer.parseInt(sian_big_num.getText().toString());
                int num_small_sian = Integer.parseInt(sian_small_num.getText().toString());
                act.setSian(num_big_sian,num_small_sian);
                sian_big_num.setText("0");
                sian_small_num.setText("0");
                activityCallback.addOrder(act);
            }
        });
        sian_big_num = (TextView) rootView.findViewById(R.id.sian_big_num);
        sian_small_num = (TextView) rootView.findViewById(R.id.sian_small_num);
        sian_big_pls = (Button) rootView.findViewById(R.id.sian_big_pls_btn);
        sian_big_pls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(sian_big_num.getText().toString());
                num++;
                sian_big_num.setText(Integer.toString(num));
            }
        });
        sian_big_mns = (Button) rootView.findViewById(R.id.sian_big_mns_btn);
        sian_big_mns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(sian_big_num.getText().toString());
                num--;
                if(num < 0)
                    num = 0;
                sian_big_num.setText(Integer.toString(num));
            }
        });

        sian_small_pls = (Button) rootView.findViewById(R.id.sian_small_pls_btn);
        sian_small_pls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(sian_small_num.getText().toString());
                num++;
                sian_small_num.setText(Integer.toString(num));
            }
        });
        sian_small_mns = (Button) rootView.findViewById(R.id.sian_small_mns_btn);
        sian_small_mns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(sian_small_num.getText().toString());
                num--;
                if(num < 0)
                    num = 0;
                sian_small_num.setText(Integer.toString(num));
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
