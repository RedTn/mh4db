package com.example.mh4db;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link FragmentA.OnFragmentInteractionListener} interface
 * to handle interaction events. Use the {@link FragmentA#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class FragmentA extends ListFragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private static final int tableLow = 1;
	private static final int tableHigh = 2;
	

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
	 * @return A new instance of fragment FragmentA.
	 */
	// TODO: Rename and change types and number of parameters
	public static FragmentA newInstance(String param1, String param2) {
		FragmentA fragment = new FragmentA();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public FragmentA() {
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
		
		Itemset itemset = myDb.getItemset((int) passedVal);
		byte[] byteArray = itemset.get_image();
		Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);
		
		View myInflatedView = inflater.inflate(R.layout.fragment_a, container, false);
		TextView rare = (TextView) myInflatedView.findViewById(R.id.rare);
		TextView qty = (TextView) myInflatedView.findViewById(R.id.qty);
		ImageView imagepic = (ImageView) myInflatedView.findViewById(R.id.itempic);
		rare.setText(itemset.get_rare());
		qty.setText(itemset.get_qty());
		imagepic.setImageBitmap(bm);
		
		ArrayList<Rankset> rankArry = new ArrayList<Rankset>();

		List<Rankset> ranksets = myDb.getAllRanksetsByIid(itemset.get_id(), tableLow);
		if (!ranksets.isEmpty()) {
		for (Rankset rs : ranksets) {
			Imgset imgset = myDb.getImgset(rs.get_mid());
			rs.set_name(imgset.getName());
			rs.set_low(true);
			rankArry.add(rs);
		}
		List<Rankset> ranksets_h = myDb.getAllRanksetsByIid(itemset.get_id(), tableHigh);
		if (!ranksets_h.isEmpty()) {
			for (Rankset rs : ranksets_h) {
				Imgset imgset = myDb.getImgset(rs.get_mid());
				rs.set_name(imgset.getName());
				rs.set_low(false);
				rankArry.add(rs);
			}
		}
		FragA_Adapter adapter = new FragA_Adapter(context, R.layout.fragment_a_layout,
				rankArry);

		ListView dataList = (ListView) myInflatedView.findViewById(android.R.id.list);
		dataList.setAdapter(adapter);
		}
		myDb.close();
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
	
	public void setText(String _qty, String _rare) {
		TextView qty = (TextView) getView().findViewById(R.id.qty);
		TextView rare = (TextView) getView().findViewById(R.id.rare);
		qty.setText(_qty);
		rare.setText(_rare);
	}

}
