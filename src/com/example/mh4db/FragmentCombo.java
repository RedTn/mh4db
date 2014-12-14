package com.example.mh4db;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link FragmentCombo.OnFragmentInteractionListener} interface
 * to handle interaction events. Use the {@link FragmentCombo#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class FragmentCombo extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private static final int whitebox_id = -2;
	public final static String ID_EXTRA="com.example.mh4db._ID";
	ArrayList<Comboset> comboArry = new ArrayList<Comboset>();
	ArrayList<Comboset> comboArry2 = new ArrayList<Comboset>();

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment FragmentB.
	 */
	// TODO: Rename and change types and number of parameters
	public static FragmentCombo newInstance(String param1, String param2) {
		FragmentCombo fragment = new FragmentCombo();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public FragmentCombo() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		Context context = getActivity().getApplicationContext();
		DBAdapter myDb = new DBAdapter(context);
		myDb.open();

		int passedVal = getArguments().getInt("passed");

		Itemset itemset = myDb.getItemset(passedVal);

		View myInflatedView = inflater.inflate(R.layout.fragment_b, container, false);
		TextView makes = (TextView) myInflatedView.findViewById(R.id.makes);
		TextView tomake = (TextView) myInflatedView.findViewById(R.id.tomake);

		List<Comboset> combosets = myDb.getCombosetByIid(itemset.get_id());
		if(!combosets.isEmpty()){
			comboArry.add(fillHeader(myDb.getExtra(whitebox_id)));
			for(Comboset cs: combosets) {
				Itemset itemset2 = new Itemset();
				if(cs.get_iid() == passedVal) itemset2 = myDb.getItemset(cs.get_iid2());
				else {
					itemset2 = myDb.getItemset(cs.get_iid());
					cs.set_iid2(cs.get_iid());
				}
				cs.set_namea(itemset2.get_name());
				cs.set_imagea(itemset2.get_image());
				Itemset itemset3 = myDb.getItemset(cs.get_result());
				cs.set_nameb(itemset3.get_name());
				cs.set_imageb(itemset3.get_image());
				cs.set_make(true);
				comboArry.add(cs);

			}
		}
		else {
			makes.setText("This item does not combine into anything");
		}

		CombosetAdapter adapter = new CombosetAdapter(context, R.layout.fragment_b_make_layout,
				comboArry);

		ListView dataList = (ListView) myInflatedView.findViewById(R.id.makelist);
		dataList.setAdapter(adapter);


		combosets.clear();

		combosets = myDb.getCombosetByResult(itemset.get_id());
		if(!combosets.isEmpty()){
			comboArry2.add(fillHeaderB(myDb.getExtra(whitebox_id)));
			for(Comboset cs: combosets) {
				Itemset itemset2 = new Itemset();
				itemset2 = myDb.getItemset(cs.get_iid());
				cs.set_namea(itemset2.get_name());
				cs.set_imagea(itemset2.get_image());
				Itemset itemset3 = myDb.getItemset(cs.get_iid2());
				cs.set_nameb(itemset3.get_name());
				cs.set_imageb(itemset3.get_image());
				cs.set_make(false);
				comboArry2.add(cs);

			}
		}
		else {
			tomake.setText("This item is not combined from anything");
		}

		CombosetAdapter adapter2 = new CombosetAdapter(context, R.layout.fragment_b_make_layout,
				comboArry2);

		ListView dataList2 = (ListView) myInflatedView.findViewById(R.id.tomakelist);
		dataList2.setAdapter(adapter2);

		return myInflatedView;
	}


	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFragmentInteraction(Uri uri);
	}

	public Comboset fillHeader(byte[] whitebox) {
		Comboset comboset = new Comboset();
		comboset.set_qty("Amt");
		comboset.set_prob("Prob");
		comboset.set_imagea(whitebox);
		comboset.set_namea("Item B");
		comboset.set_imageb(whitebox);
		comboset.set_nameb("Result");
		comboset.set_header(true);
		return comboset;
	}

	public Comboset fillHeaderB(byte[] whitebox) {
		Comboset comboset = new Comboset();
		comboset.set_qty("Amt");
		comboset.set_prob("Prob");
		comboset.set_imagea(whitebox);
		comboset.set_namea("Item A");
		comboset.set_imageb(whitebox);
		comboset.set_nameb("Item B");
		comboset.set_header(true);
		return comboset;
	}

}
