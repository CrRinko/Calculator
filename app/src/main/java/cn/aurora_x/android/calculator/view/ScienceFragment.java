package cn.aurora_x.android.calculator.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import cn.aurora_x.android.calculator.R;
import cn.aurora_x.android.calculator.presenter.ScienceFragmentPresenter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScienceFragment extends Fragment {

    //    private OnFragmentInteractionListener mListener;
    private EditText editTextScreen;
    private Button btnCE;
    private Button btnC;
    private Button btnDel;
    private Button btnEqual;
    private String screen;
    private WebView webView;
    private ScienceFragmentPresenter presenter;
    boolean toOverRide=false;

    public ScienceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screen = new String();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_science, container, false);
        presenter = ScienceFragmentPresenter.getInstance();
        presenter.setFragment(this);
        editTextScreen = (EditText) fragmentView.findViewById(R.id.editText_science_screen);
        btnC = (Button) fragmentView.findViewById(R.id.btn_science_c);
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen = new String();
                editTextScreen.setText(screen);
            }
        });
        btnCE = (Button) fragmentView.findViewById(R.id.btn_science_ce);
        btnCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen = new String();
                editTextScreen.setText(screen);
            }
        });
        btnDel = (Button) fragmentView.findViewById(R.id.btn_science_del);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toOverRide){
                    setScreen("");
                    toOverRide=false;
                }else {
                    presenter.onButtonDelClick();
                }
            }
        });
        btnEqual = (Button) fragmentView.findViewById(R.id.btn_science_equals);
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButtonEqualsClick();
            }
        });
        return fragmentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    public void onButtonClick(View view) {
        Button button = (Button) view;
        if(toOverRide){
            setScreen("");
            toOverRide=false;
        }
        switch (button.getId()) {
            case R.id.btn_science_cos:
                screen += button.getText().toString() + "(";
                break;
            case R.id.btn_science_sqrt:
                screen += "sqrt(";
                break;
            case R.id.btn_science_multiply:
                screen += "*";
                break;
            case R.id.btn_science_sin:
                screen += button.getText().toString() + "(";
                break;
            case R.id.btn_science_tan:
                screen += button.getText().toString() + "(";
                break;
            default:
                screen += button.getText().toString();
        }
        editTextScreen.setText(screen);
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
        editTextScreen.setText(screen);
    }
    public void throwException(String msg){
        this.screen=screen;
        editTextScreen.setText(msg);
        toOverRide=true;
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
