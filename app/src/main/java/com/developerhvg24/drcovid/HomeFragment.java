package com.developerhvg24.drcovid;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private TextView activeCases_text_view,deceasedCases_text_view,recoveredCases_text_view,updateTime_text_view;
    private ProgressBar progressBar;
    private String activeCases,deceasedCases,recoveredCases,updateTime;
    public HomeFragment() {
        // Required empty public constructor
    }

    //THIS onResume() Method will help us to check network state whenever users returns to this fragment
    @Override
    public void onResume() {
        super.onResume();
        //CHECKING NETWORK ACCESS
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(!(networkInfo!=null && networkInfo.isConnected()))
        {
            updateTime_text_view.setText(R.string.no_internet_case_text);
            Toast.makeText(getContext(),"No Internet",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        activeCases_text_view = fragmentView.findViewById(R.id.active_cases_text_view);
        deceasedCases_text_view = fragmentView.findViewById(R.id.deceased_cases_text_view);
        recoveredCases_text_view = fragmentView.findViewById(R.id.recovered_cases_text_view);
        updateTime_text_view = fragmentView.findViewById(R.id.update_time_text_view);
        Button syncButton = fragmentView.findViewById(R.id.btn_sync);
        syncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if(networkInfo!=null && networkInfo.isConnected())
                {
                    Toast.makeText(getContext(),"Syncing",Toast.LENGTH_LONG).show();
                    getJSONData();
                }
                else
                {
                    Toast.makeText(getContext(),"No Internet",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        progressBar = fragmentView.findViewById(R.id.progress_bar);
        ImageView preventive_measures_image_view = fragmentView.findViewById(R.id.preventive_measures_image_view);
        //USED Glide Library TO LOAD IMAGE INTO IMAGE VIEW BECAUSE SOMETIMES APP CRASHES
        //AS IMAGE RESOLUTION IS TOO HIGH AS COMPARED TO A DEVICE(like it crashes in Motorola G(5S) Plus)
        Glide.with(this).load(R.drawable.preventive_measures_1).into(preventive_measures_image_view);
        getJSONData();
        return fragmentView;
    }

    private void getJSONData()
    {
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Interface_COVID_UPDATE_API.BASE_URL_COVID_UPDATE_API)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Interface_COVID_UPDATE_API api = retrofit.create(Interface_COVID_UPDATE_API.class);

        Call<Whole_India_Data> call = api.getData();

        call.enqueue(new Callback<Whole_India_Data>() {
            @Override
            public void onResponse(Call<Whole_India_Data> call, Response<Whole_India_Data> response) {
                progressBar.setVisibility(View.GONE);
                activeCases = response.body().getTotalCases();
                deceasedCases = response.body().getDeaths();
                recoveredCases = response.body().getRecovered();
                String lastUpdatedAtApify = response.body().getLastUpdatedAtApify();

                //FORMATTING UPDATE DATE AND TIME TO REQUIRED FORMAT
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                SimpleDateFormat simpleDateFormat1  = new SimpleDateFormat("EEE, d MMM 'at' hh:mm aaa");
                Date date;
                try {
                    date = simpleDateFormat.parse(lastUpdatedAtApify);
                    updateTime = "Updated At : "+ simpleDateFormat1.format(date);
                } catch (ParseException e) {
                    //Log.d("ERROR_MESSAGE",e.getMessage());
                }
                updateTime_text_view.setText(updateTime);
                activeCases_text_view.setText(activeCases);
                deceasedCases_text_view.setText(deceasedCases);
                recoveredCases_text_view.setText(recoveredCases);
            }

            @Override
            public void onFailure(Call<Whole_India_Data> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                //Toast.makeText(getContext(),"Something Wrong Happened",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
