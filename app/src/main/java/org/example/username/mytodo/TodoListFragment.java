package org.example.username.mytodo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.example.username.mytodo.model.Todo;

import io.realm.Realm;
import io.realm.RealmResults;



public class TodoListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CATEGORY_ID = "CATEGORY_ID";
    private static final String CATEGORY_NAME = "CATEGORY_NAME";

    // TODO: Rename and change types of parameters
    private long mCategoryId;
    private String mCategoryName;

//    private OnFragmentInteractionListener mListener;




    public TodoListFragment() {
        // Required empty public constructor
    }

    /**
     * 提供されたパラメータを用いて、このフラグメントの新しいインスタンスを
     * 作成するには、このファクトリメソッドを使用します。
     *
     * @param categoryId カテゴリID.
     * @return TodoListFragmentの新しいインスタンス
     */
    // TODO: Rename and change types and number of parameters
    public static TodoListFragment newInstance(long categoryId, String categoryName) {
        TodoListFragment fragment = new TodoListFragment();
        Bundle args = new Bundle();
        args.putLong(CATEGORY_ID, categoryId);
        args.putString(CATEGORY_NAME, categoryName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategoryId = getArguments().getLong(CATEGORY_ID);
            mCategoryName = getArguments().getString(CATEGORY_NAME);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Realm realm = Realm.getDefaultInstance();
        getActivity().setTitle(mCategoryName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // このフラグメントのレイアウトを生成します
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);

        // Realmインスタンスを取得します
        Realm realm = Realm.getDefaultInstance();
        // Realmにクエリを発行しカテゴリIDに紐づくTodoリストを取り出します。
        RealmResults<Todo> todoList = realm.where(Todo.class)
                .equalTo("categoryId",mCategoryId)
                .findAllSorted("done");
        // クエリはfindAllSortedでソートすることができます。
        TodoAdapter adapter = new TodoAdapter(getActivity(), todoList);

        ListView listView = (ListView) view.findViewById(R.id.todoList);
        listView.setAdapter(adapter);

        return view;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    public long getCategoryId() {
        return mCategoryId;
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
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }

}
