package org.example.username.mytodo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import org.example.username.mytodo.model.Category;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private OnFragmentInteractionListener mListener;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

//        String[] list = {"買い物リスト", "時間ができたらやること", "すぐやるリスト", "仕事",
//                "プライベート", "緊急", "調査・分析", "資料作成", "その他"};
//        ArrayAdapter adapter = new ArrayAdapter(
//                getActivity(), R.layout.grid_cell, R.id.category, list);
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Category> categories = realm.where(Category.class).findAll();
        CategoryAdapter adapter = new CategoryAdapter(getActivity(), categories);


        GridView gridView = (GridView) view.findViewById(R.id.categories);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // AdapterViewにはGridVieが渡されています。getAdapterビューでアダプターを取得出来ます。
                CategoryAdapter adapter = (CategoryAdapter)parent.getAdapter();
                Category category = adapter.getItem(position);
                long categoryId = category.getId();
                String categoryName = category.getCategory();
                if (mListener != null) {
                    mListener.onCategorySelected(categoryId,categoryName);
                }
            }
        });

        realm.close();

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle(R.string.app_name);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onCategorySelected(long categoryId, String categoryName);
    }
}
