package zg.org.moments.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import zg.org.moments.R;

/**
 * Created by gzeng on 12/05/2017.
 */

public class DocumentsFragment extends Fragment{

  private ListView documentListView = null;

  public static Fragment getInstance(){
    return new DocumentsFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.public_relation_activity_documents, container, false);
    documentListView = (ListView) view.findViewById(R.id.documentContainer);
    renderDocumentList();
    return view;
  }

  public void renderDocumentList(){

    final List<String> documents = new ArrayList<String>();
    documents.add("aaa");
    documents.add("bbb");
    documents.add("ccc");
    documents.add("ddd");
    documents.add("eee");
    documents.add("eee");
    documents.add("eee");
    documents.add("eee");documents.add("eee");documents.add("eee");documents.add("eee");documents.add("eee");


    documentListView.setAdapter(new BaseAdapter() {

      @Override
      public int getCount() {
        return documents.size();
      }

      @Override
      public Object getItem(int position) {
        return documents.get(position);
      }

      @Override
      public long getItemId(int position) {
        return 0;
      }

      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout view = null;
        if(convertView != null){
          view = (LinearLayout) convertView;
        }else{
          view = (LinearLayout)LayoutInflater.from(getActivity()).inflate(R.layout.document_item, parent, false);
        }
        return view;
      }
    });
  }

}
