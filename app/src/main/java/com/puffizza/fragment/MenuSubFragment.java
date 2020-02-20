package com.puffizza.fragment;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.puffizza.R;

import java.util.ArrayList;
import java.util.List;


public class MenuSubFragment extends Fragment {

    private RecyclerView recyclerView;
//    private List<Menu> itemsList;
    private MenuAdapter mAdapter;
    private TextView menu_name;

    public MenuSubFragment() {
        // Required empty public constructor
    }

    public static MenuSubFragment newInstance(String param1, String param2) {
        MenuSubFragment fragment = new MenuSubFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        /*List<Menu> items = new Gson().fromJson(menuImageList.toString(), new TypeToken<List<Menu>>() {
        }.getType());

        itemsList.add((Menu) items);*/

//        itemsList.clear();
//        itemsList.addAll(items);

        // refreshing recycler view
//        mAdapter.notifyDataSetChanged();

        String Message = getArguments().getString("TITLE");

        menu_name = view.findViewById(R.id.menu_name);
        menu_name.setText(Message);

        recyclerView = view.findViewById(R.id.menu_list);
//        itemsList = new ArrayList<Menu>();
        mAdapter = new MenuAdapter(getActivity());

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        return view;
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    /**
     * RecyclerView adapter class to render items
     * This class can go into another separate class, but for simplicity
     */
    class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
        private Context context;
//        private List<Menu> movieList;

        ArrayList<Integer> array_image = new ArrayList<Integer>();

        List<Integer> title = new ArrayList<Integer>();
        List<Integer> sub_title = new ArrayList<Integer>();
        List<Integer> price = new ArrayList<Integer>();
        List<Integer> description = new ArrayList<Integer>();

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView name, decription,price;
            public ImageView thumbnail;

            public MyViewHolder(View view) {
                super(view);
                name = view.findViewById(R.id.title);
                decription = view.findViewById(R.id.decription);
                price = view.findViewById(R.id.price);
                thumbnail = view.findViewById(R.id.thumbnail);
            }
        }


        public MenuAdapter(Context context) {
            this.context = context;
//            this.movieList = movieList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.home_menu_row, parent, false);

            array_image.add(R.drawable.tomonion);
            array_image.add(R.drawable.peri_peri_mashroom);
            array_image.add(R.drawable.five_cheesy);
            array_image.add(R.drawable.farm_fresh);
            array_image.add(R.drawable.pasta);
            array_image.add(R.drawable.pasta);
            array_image.add(R.drawable.beverages);
            array_image.add(R.drawable.dips);
            array_image.add(R.drawable.bread);
            array_image.add(R.drawable.chunky_bocconcini);

            title.add(R.string.sub1_bread1);

            sub_title.add(R.string.sub_bread1);
            sub_title.add(R.string.sub_bread2);
            sub_title.add(R.string.sub_bread3);
            sub_title.add(R.string.sub_bread4);
            sub_title.add(R.string.sub_bread5);
            sub_title.add(R.string.sub_bread6);
            sub_title.add(R.string.sub_bread7);
            sub_title.add(R.string.sub_bread8);
            sub_title.add(R.string.sub_bread9);
            sub_title.add(R.string.sub_bread10);

            price.add(R.string.price19);
            price.add(R.string.price35);
            price.add(R.string.price40);
            price.add(R.string.price60);
            price.add(R.string.price70);
            price.add(R.string.price80);
            price.add(R.string.price90);
            price.add(R.string.price110);
            price.add(R.string.price119);
            price.add(R.string.price130);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
//            final Menu movie = movieList.get(position);
            holder.name.setText(sub_title.get(position));

            animate(holder);

//            holder.decription.setText(description.get(position));
            holder.price.setText(price.get(position));
//            holder.thumbnail.setImageResource(R.drawable.peri_peri_mashroom);

            Glide.with(context)
                    .load(array_image.get(position))
                    .into(holder.thumbnail);
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public void animate(RecyclerView.ViewHolder viewHolder) {
            final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.anticipate_overshoot);
            viewHolder.itemView.setAnimation(animAnticipateOvershoot);
        }
    }
}
