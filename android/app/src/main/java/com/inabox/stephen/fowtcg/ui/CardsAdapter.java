package com.inabox.stephen.fowtcg.ui;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.ViewGroup;
import com.codesmyth.droidcook.common.content.Query;
import com.codesmyth.droidcook.common.widget.RecyclerCursorAdapter;
import com.codesmyth.droidcook.common.widget.ViewBinder;
import com.google.common.base.Strings;
import com.inabox.stephen.fowtcg.AppContent;

public class CardsAdapter extends RecyclerCursorAdapter<CardsAdapter> {

  public CardsAdapter(Context context) {
    super(context);
  }

  @Override
  public ViewBinder<CardsAdapter> onCreateViewHolder(ViewGroup parent, int viewType) {
    return new CardsItem(parent);
  }

  @Override
  public Loader<Cursor> onCreateLoader(int id, Bundle args) {

    String filter = args.getString("filter", "");

    Query.Builder q = Query.builder()
        .select("_id", "name", "description")
        .from(AppContent.CARDS_URI)
        .orderBy("name");

    if (!Strings.isNullOrEmpty(filter)) {
      q.where("name like ?").args("%" + filter + "%");
    }

    return q.cursorLoader(getContext());
  }
}