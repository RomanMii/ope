package com.ti38b.ourpeopleeverywhere.ui.convertor;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;



import com.ti38b.ourpeopleeverywhere.R;
import com.ti38b.ourpeopleeverywhere.databinding.FragmentConvertorBinding;

public class ConvertorFragment extends Fragment {

    private ConvertorViewModel convertorViewModel;
    private FragmentConvertorBinding binding;

    private Spinner firstSpinner;
    private Spinner secondSpinner;

    private EditText from;
    private TextView to;

    private ImageButton length;
    private Boolean lengthIsPressed = true;

    private ImageButton area;
    private Boolean areaIsPressed = false;

    private ImageButton weight;
    private Boolean weightIsPressed = false;



    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        convertorViewModel =
                new ViewModelProvider(this).get(ConvertorViewModel.class);


        binding = FragmentConvertorBinding.inflate(inflater, container, false);
        //binding.setViewModel(new ConvertorViewModel());
        //binding.setLifecycleOwner(this);

        View root = binding.getRoot();
        context = root.getContext();
        firstSpinner = binding.spinnerFirstConversion;
        secondSpinner = binding.spinnerSecondConversion;

        from = binding.ucFrom;
        to = binding.ucTo;
        length = binding.lengthButton;
        weight = binding.weightButton;
        area = binding.areaButton;

        from.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculate();
            }
        });

        firstSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        secondSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!areaIsPressed){
                    areaIsPressed = true;
                    lengthIsPressed = false;
                    weightIsPressed = false;
                    area.setBackgroundColor(getResources().getColor(R.color.gray));
                    length.setBackgroundColor(getResources().getColor(R.color.white));
                    weight.setBackgroundColor(getResources().getColor(R.color.white));

                    String[] arraySpinner = getResources().getStringArray(R.array.measuresOfArea);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_item, arraySpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    firstSpinner.setAdapter(adapter);
                    secondSpinner.setAdapter(adapter);
                }
            }
        });

        length.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!lengthIsPressed){
                    areaIsPressed = false;
                    lengthIsPressed = true;
                    weightIsPressed = false;
                    area.setBackgroundColor(getResources().getColor(R.color.white));
                    length.setBackgroundColor(getResources().getColor(R.color.gray));
                    weight.setBackgroundColor(getResources().getColor(R.color.white));

                    String[] arraySpinner = getResources().getStringArray(R.array.measuresOfLength);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_item, arraySpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    firstSpinner.setAdapter(adapter);
                    secondSpinner.setAdapter(adapter);
                }
            }
        });

        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!weightIsPressed){
                    areaIsPressed = false;
                    lengthIsPressed = false;
                    weightIsPressed = true;
                    area.setBackgroundColor(getResources().getColor(R.color.white));
                    length.setBackgroundColor(getResources().getColor(R.color.white));
                    weight.setBackgroundColor(getResources().getColor(R.color.gray));

                    String[] arraySpinner = getResources().getStringArray(R.array.measuresOfWeight);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_item, arraySpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    firstSpinner.setAdapter(adapter);
                    secondSpinner.setAdapter(adapter);
                }
            }
        });

        return root;
    }

    private void calculate(){
        String fromUnit = firstSpinner.getSelectedItem().toString();
        String toUnit = secondSpinner.getSelectedItem().toString();
        String amount = from.getText().toString();
        if(!amount.isEmpty()){
            if(lengthIsPressed){
                to.setText(convertorViewModel.calculateLength(amount,fromUnit,toUnit).toString());
            }else if(weightIsPressed){
                to.setText(convertorViewModel.calculateWeight(amount,fromUnit,toUnit).toString());
            }else if(areaIsPressed){
                to.setText(convertorViewModel.calculateArea(amount,fromUnit,toUnit).toString());
            }
        }else{
            to.setText("0");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}