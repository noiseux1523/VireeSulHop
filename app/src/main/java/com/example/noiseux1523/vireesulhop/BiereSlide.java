package com.example.noiseux1523.vireesulhop;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BiereSlide.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BiereSlide#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BiereSlide extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private int mPosition;
    private static final String POSITION = "pos";

    //Variables
    private TextView nom;
    private TextView version;
    private TextView version_value;
    private TextView abv;
    private TextView abv_value;
    private TextView ibu;
    private TextView ibu_value;
    private TextView description;
    private TextView ingredients;
    private TextView ingredients_value;


    public BiereSlide() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param pos Parameter 1.
     * @return A new instance of fragment BiereSlide.
     */
    // TODO: Rename and change types and number of parameters
    public static BiereSlide newInstance(int pos) {
        BiereSlide fragment = new BiereSlide();
        Bundle args = new Bundle();
        args.putInt(POSITION, pos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.content_biere, container, false);

        // Set Phenomena font to buttons
        Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Phenomena-Bold.otf");

        // Nom
        nom = (TextView)rootView.findViewById(R.id.nom);
        nom.setTypeface(custom_font);

        // Version
        version = (TextView)rootView.findViewById(R.id.version);
        version.setTypeface(custom_font);
        version_value = (TextView)rootView.findViewById(R.id.version_value);
        version_value.setTypeface(custom_font);

        // ABV
        abv = (TextView)rootView.findViewById(R.id.abv);
        abv.setTypeface(custom_font);
        abv_value = (TextView)rootView.findViewById(R.id.abv_value);
        abv_value.setTypeface(custom_font);

        // IBU
        ibu = (TextView)rootView.findViewById(R.id.ibu);
        ibu.setTypeface(custom_font);
        ibu_value = (TextView)rootView.findViewById(R.id.ibu_value);
        ibu_value.setTypeface(custom_font);

        // Description
        description = (TextView)rootView.findViewById(R.id.description);
        description.setTypeface(custom_font);

        // Ingredients
        ingredients = (TextView)rootView.findViewById(R.id.ingredients);
        ingredients.setTypeface(custom_font);
        ingredients_value = (TextView)rootView.findViewById(R.id.ingredients_value);
        ingredients_value.setTypeface(custom_font);

        switch(mPosition){
            case 0:
                nom.setText("MOUSTACHE CARESSE");
                version_value.setText("BETA");
                abv_value.setText("4.6%");
                ibu_value.setText("16");
                description.setText(R.string.biere_description);
                ingredients_value.setText("MALTS, HOUBLONS, LEVURE, EAU, AMOUR...");
                break;
            case 1:
                nom.setText("GRAND GREBICHE");
                version_value.setText("ALPHA");
                abv_value.setText("6.7%");
                ibu_value.setText("62");
                description.setText(R.string.biere_description);
                ingredients_value.setText("MALTS, HOUBLONS, LEVURE, EAU, AMOUR...");
                break;
            default:
                nom.setText(String.valueOf(mPosition));
                version_value.setText("N/A");
                abv_value.setText("N/A");
                ibu_value.setText("N/A");
                description.setText(R.string.biere_description);
                ingredients_value.setText("MALTS, HOUBLONS, LEVURE, EAU, AMOUR...");
                break;
        }
        return rootView;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
