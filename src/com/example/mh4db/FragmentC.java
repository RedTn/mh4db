package com.example.mh4db;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link FragmentC.OnFragmentInteractionListener} interface
 * to handle interaction events. Use the {@link FragmentC#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class FragmentC extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private static final int whitebox_id = -2;
	public final static String ID_EXTRA="com.example.mh4db._ID";
	ArrayList<MapRankset> mapArry = new ArrayList<MapRankset>();

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
	 * @return A new instance of fragment FragmentC.
	 */
	// TODO: Rename and change types and number of parameters
	public static FragmentC newInstance(String param1, String param2) {
		FragmentC fragment = new FragmentC();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public FragmentC() {
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
				
				View myInflatedView = inflater.inflate(R.layout.fragment_c, container, false);
				TextView location = (TextView) myInflatedView.findViewById(R.id.location);
				
				List<MapRankset> mapsets = myDb.getAllRankMapsetByIid(passedVal, 1);
				
				if(!mapsets.isEmpty()){
					fillHeader();
					for(MapRankset ms: mapsets) {
						ms.set_location(myDb.getMapset(ms.get_mapid()).get_name());
						ms.set_lvl("LowRank");
						switch(ms.get_tid()) {
						case 0: ms.set_typeimage(getResources().getDrawable(R.drawable.ic_gather));
						break;
						case 1: ms.set_typeimage(getResources().getDrawable(R.drawable.ic_fish));
						break;
						case 2: ms.set_typeimage(getResources().getDrawable(R.drawable.ic_bug));
						break;
						case 3: ms.set_typeimage(getResources().getDrawable(R.drawable.ic_mine));
						break;
						default: ms.set_typeimage(getResources().getDrawable(R.drawable.ic_gather));
						break;
						}
						mapArry.add(ms);
					}
				}
				else {
					location.setText("This item is not located on a map");
				}
				
				LocationAdapter adapter = new LocationAdapter(context, R.layout.fragment_c_layout,
						mapArry);

				ListView dataList = (ListView) myInflatedView.findViewById(R.id.locationlist);
				dataList.setAdapter(adapter);
				
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
	
	private void fillHeader() {
		MapRankset mapset = new MapRankset();
		mapset.set_lvl("Lv.");
		mapset.set_area("Area");
		mapset.set_location("Location");
		mapset.set_header(true);
		mapArry.add(mapset);
	}

}
