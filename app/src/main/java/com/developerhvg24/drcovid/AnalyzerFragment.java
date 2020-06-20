package com.developerhvg24.drcovid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnalyzerFragment extends Fragment {

    private CheckBox question_1_option_1,question_1_option_2,question_1_option_3,question_1_option_4,question_1_option_5,question_1_option_6,question_1_option_7;
    private CheckBox question_2_option_1,question_2_option_2;
    private CheckBox question_3_option_1,question_3_option_2;
    private CheckBox question_4_option_1,question_4_option_2;
    private CheckBox question_5_option_1,question_5_option_2;
    private CheckBox question_6_option_1,question_6_option_2,question_6_option_3,question_6_option_4,question_6_option_5,question_6_option_6,question_6_option_7,question_6_option_8;
    private Button submitButton;
    private Toast toast;

    public AnalyzerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_analyzer, container, false);

        //Getting reference of Question 1's options' check boxes
        question_1_option_1 = view.findViewById(R.id.question_1_option_1);
        question_1_option_2 = view.findViewById(R.id.question_1_option_2);
        question_1_option_3 = view.findViewById(R.id.question_1_option_3);
        question_1_option_4 = view.findViewById(R.id.question_1_option_4);
        question_1_option_5 = view.findViewById(R.id.question_1_option_5);
        question_1_option_6 = view.findViewById(R.id.question_1_option_6);
        question_1_option_7 = view.findViewById(R.id.question_1_option_7);

        //Getting reference of Question 2's options' check boxes
        question_2_option_1 = view.findViewById(R.id.question_2_option_1);
        question_2_option_2 = view.findViewById(R.id.question_2_option_2);

        //Getting reference of Question 3's options' check boxes
        question_3_option_1 = view.findViewById(R.id.question_3_option_1);
        question_3_option_2 = view.findViewById(R.id.question_3_option_2);

        //Getting reference of Question 4's options' check boxes
        question_4_option_1 = view.findViewById(R.id.question_4_option_1);
        question_4_option_2 = view.findViewById(R.id.question_4_option_2);

        //Getting reference of Question 5's options' check boxes
        question_5_option_1 = view.findViewById(R.id.question_5_option_1);
        question_5_option_2 = view.findViewById(R.id.question_5_option_2);

        //Getting reference of Question 6's options' check boxes
        question_6_option_1 = view.findViewById(R.id.question_6_option_1);
        question_6_option_2 = view.findViewById(R.id.question_6_option_2);
        question_6_option_3 = view.findViewById(R.id.question_6_option_3);
        question_6_option_4 = view.findViewById(R.id.question_6_option_4);
        question_6_option_5 = view.findViewById(R.id.question_6_option_5);
        question_6_option_6 = view.findViewById(R.id.question_6_option_6);
        question_6_option_7 = view.findViewById(R.id.question_6_option_7);
        question_6_option_8 = view.findViewById(R.id.question_6_option_8);

        toast = Toast.makeText(getContext(),"Uncheck first option",Toast.LENGTH_SHORT);
        
        submitButton = view.findViewById(R.id.btn_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeResult(view);
            }
        });
        //Logic Making Functions' for each questions' check boxes
        question_1_options_selection_logic();
        question_2_options_selection_logic();
        question_3_options_selection_logic();
        question_4_options_selection_logic();
        question_5_options_selection_logic();
        question_6_options_selection_logic();
        return view;
    }

    //Question 1's options selection Logic
    private void question_1_options_selection_logic() {

        question_1_option_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    question_1_option_2.setChecked(false);
                    question_1_option_3.setChecked(false);
                    question_1_option_4.setChecked(false);
                    question_1_option_5.setChecked(false);
                    question_1_option_6.setChecked(false);
                    question_1_option_7.setChecked(false);
                    toast.cancel();
                }
            }
        });
        question_1_option_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(question_1_option_1.isChecked())
                {
                    question_1_option_2.setChecked(false);
                    toast.show();
                }
            }
        });

        question_1_option_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(question_1_option_1.isChecked())
                {
                    question_1_option_3.setChecked(false);
                    toast.show();
                }
            }
        });

        question_1_option_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(question_1_option_1.isChecked())
                {
                    question_1_option_4.setChecked(false);
                    toast.show();
                }
            }
        });

        question_1_option_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(question_1_option_1.isChecked())
                {
                    question_1_option_5.setChecked(false);
                    toast.show();
                }
            }
        });

        question_1_option_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(question_1_option_1.isChecked())
                {
                    question_1_option_6.setChecked(false);
                    toast.show();
                }
            }
        });

        question_1_option_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(question_1_option_1.isChecked())
                {
                    question_1_option_7.setChecked(false);
                    toast.show();
                }
            }
        });
    }

    //Question 2's options selection Logic
    private void question_2_options_selection_logic() {

        question_2_option_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    question_2_option_2.setChecked(false);
                }
            }
        });
        question_2_option_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    question_2_option_1.setChecked(false);
                }
            }
        });
    }

    //Question 3's options selection Logic
    private void question_3_options_selection_logic() {

        question_3_option_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    question_3_option_2.setChecked(false);
                }
            }
        });
        question_3_option_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    question_3_option_1.setChecked(false);
                }
            }
        });
    }

    //Question 4's options selection Logic
    private void question_4_options_selection_logic() {

        question_4_option_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    question_4_option_2.setChecked(false);
                }
            }
        });
        question_4_option_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    question_4_option_1.setChecked(false);
                }
            }
        });
    }

    //Question 5's options selection Logic
    private void question_5_options_selection_logic() {

        question_5_option_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    question_5_option_2.setChecked(false);
                }
            }
        });
        question_5_option_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    question_5_option_1.setChecked(false);
                }
            }
        });
    }

    //Question 6's options selection Logic
    private void question_6_options_selection_logic() {

        question_6_option_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    question_6_option_2.setChecked(false);
                    question_6_option_3.setChecked(false);
                    question_6_option_4.setChecked(false);
                    question_6_option_5.setChecked(false);
                    question_6_option_6.setChecked(false);
                    question_6_option_7.setChecked(false);
                    question_6_option_8.setChecked(false);
                    toast.cancel();
                }
            }
        });
        question_6_option_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(question_6_option_1.isChecked())
                {
                    question_6_option_2.setChecked(false);
                    toast.show();
                }
            }
        });

        question_6_option_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(question_6_option_1.isChecked())
                {
                    question_6_option_3.setChecked(false);
                    toast.show();
                }
            }
        });

        question_6_option_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(question_6_option_1.isChecked())
                {
                    question_6_option_4.setChecked(false);
                    toast.show();
                }
            }
        });

        question_6_option_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(question_6_option_1.isChecked())
                {
                    question_6_option_5.setChecked(false);
                    toast.show();
                }
            }
        });

        question_6_option_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(question_6_option_1.isChecked())
                {
                    question_6_option_6.setChecked(false);
                    toast.show();
                }
            }
        });

        question_6_option_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(question_6_option_1.isChecked())
                {
                    question_6_option_7.setChecked(false);
                    toast.show();
                }
            }
        });

        question_6_option_8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(question_6_option_1.isChecked())
                {
                    question_6_option_8.setChecked(false);
                    toast.show();
                }
            }
        });
    }

    public void computeResult(View view)
    {
        ArrayList<CheckBox> checkBoxList  = new ArrayList<>();
        checkBoxList.add(question_1_option_1);checkBoxList.add(question_1_option_2);checkBoxList.add(question_1_option_3);checkBoxList.add(question_1_option_4);checkBoxList.add(question_1_option_5);checkBoxList.add(question_1_option_6);checkBoxList.add(question_1_option_7);
        checkBoxList.add(question_2_option_1);checkBoxList.add(question_2_option_2);
        checkBoxList.add(question_3_option_1);checkBoxList.add(question_3_option_2);
        checkBoxList.add(question_4_option_1);checkBoxList.add(question_4_option_2);
        checkBoxList.add(question_5_option_1);checkBoxList.add(question_5_option_2);
        checkBoxList.add(question_6_option_1);checkBoxList.add(question_6_option_2);checkBoxList.add(question_6_option_3);checkBoxList.add(question_6_option_4);checkBoxList.add(question_6_option_5);checkBoxList.add(question_6_option_6);checkBoxList.add(question_6_option_7);checkBoxList.add(question_6_option_8);
        int c = 0;
        for(CheckBox checkBox:checkBoxList)
        {
            if(checkBox.isChecked())
            {
                c = c + 1;
            }
        }
        if(c >= 6)
        {
            int risk_factor;
            Toast.makeText(getContext(),"Computing Result...",Toast.LENGTH_SHORT).show();
            //TODO: Implement logic for computing the risk of  COVID-19 i.e. the value of factor variable here
            if((question_6_option_2.isChecked()&&question_6_option_3.isChecked()&&question_6_option_4.isChecked())||(question_6_option_2.isChecked()&&(question_6_option_4.isChecked()))||
                    (question_2_option_1.isChecked())||(question_3_option_1.isChecked())||(question_4_option_1.isChecked()))
            {
                risk_factor = 6;
            }
            else if((question_6_option_2.isChecked()||question_6_option_3.isChecked()||question_6_option_4.isChecked()||question_6_option_5.isChecked()||
                    question_6_option_6.isChecked()||question_6_option_7.isChecked()||question_6_option_8.isChecked())&&(question_1_option_2.isChecked()||
                    question_1_option_3.isChecked()||question_1_option_4.isChecked()||question_1_option_5.isChecked()||question_1_option_6.isChecked()||question_1_option_7.isChecked()))
            {
                risk_factor = 5;
            }
            else if(question_1_option_2.isChecked()||question_1_option_3.isChecked()||question_1_option_4.isChecked()||question_1_option_5.isChecked()||question_1_option_6.isChecked()||question_1_option_7.isChecked())
            {
                risk_factor = 4;
            }
            else if(question_6_option_2.isChecked()||question_6_option_3.isChecked()||question_6_option_4.isChecked()||question_6_option_5.isChecked()||question_6_option_6.isChecked()||
            question_6_option_7.isChecked()||question_6_option_8.isChecked())
            {
                risk_factor = 3;
            }
            else if(question_5_option_1.isChecked())
            {
                risk_factor = 2;
            }
            else
            {
                risk_factor = 1;
            }
            showResult(view,risk_factor);
        }
        else
        {
            Toast toast = Toast.makeText(getContext(),"Carefully answer all questions",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
    }

    private void showResult(View view, int factor) {

        View popview = LayoutInflater.from(getContext()).inflate(R.layout.result_pop_up_window,new RelativeLayout(getContext()));
        Button dissmissButton = popview.findViewById(R.id.btn_dismiss);
        ProgressBar progressBar = popview.findViewById(R.id.result_progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        TextView textView = popview.findViewById(R.id.text_view_risk_factor);
        TextView textView1 = popview.findViewById(R.id.recommendation_text_view);
        TextView textView2 = popview.findViewById(R.id.terms_and_conditions_text_view);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                textView2.setText(R.string.terms_and_conditions_text);
                if(factor == 1)
                {
                    textView.setBackgroundResource(R.drawable.recovered_update_text_view_background);
                    textView.setTextColor(getResources().getColor(R.color.recovered_cases_sub_title_text_color));
                    textView.setText(R.string.low_risk_text);
                    textView1.setText(R.string.recommendation_text_low_risk);
                }
                else if(factor == 2 || factor == 3 || factor == 4 || factor == 5)
                {
                    textView.setBackgroundResource(R.drawable.active_update_text_view_background);
                    textView.setTextColor(getResources().getColor(R.color.active_cases_sub_title_text_color));
                    if(factor ==2)
                    {
                        textView.setText(R.string.can_be_at_RISK_text);
                        textView1.setText(R.string.recommendation_text_can_be_at_RISK_2);
                    }
                    else if(factor == 3)
                    {
                        textView.setText(R.string.can_be_at_RISK_text);
                        textView1.setText(R.string.recommendation_text_can_be_at_RISK);
                    }
                    else if(factor == 4)
                    {
                        textView.setText(R.string.can_be_at_HIGH_RISK_text);
                        textView1.setText(R.string.recommendation_text_can_be_at_HIGH_RISK);
                    }
                    else
                    {
                        textView.setText(R.string.can_be_at_HIGH_RISK_text);
                        textView1.setText(R.string.recommendation_text_can_be_at_HIGH_RISK_2);
                    }
                }
                else
                {
                    textView.setBackgroundResource(R.drawable.deceased_update_text_view_background);
                    textView.setTextColor(getResources().getColor(R.color.death_cases_sub_title_text_color));
                    textView.setText(R.string.high_risk_text);
                    textView1.setText(R.string.recommendation_text_high_risk);
                }
            }
        },2000);

        PopupWindow popupWindow = new PopupWindow(popview, RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT,true);
        popupWindow.setAnimationStyle(R.style.Animation_Design_BottomSheetDialog);
        dissmissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        popupWindow.showAtLocation(view, Gravity.CENTER,0,0);
    }
}
