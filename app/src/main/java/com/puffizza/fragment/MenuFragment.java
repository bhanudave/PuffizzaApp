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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.puffizza.R;

import java.util.ArrayList;
import java.util.List;


public class MenuFragment extends Fragment {

    private RecyclerView recyclerView;
//    private List<Menu> itemsList;
    private MenuAdapter mAdapter;
    private TextView menu_name;

    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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

        menu_name = view.findViewById(R.id.menu_name);
        menu_name.setText("Puffizza Menu");

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
            public RelativeLayout menu_view;

            public MyViewHolder(View view) {
                super(view);
                name = view.findViewById(R.id.title);
                decription = view.findViewById(R.id.decription);
                price = view.findViewById(R.id.price);
                thumbnail = view.findViewById(R.id.thumbnail);
                menu_view = view.findViewById(R.id.menu_view);
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

            array_image.add(R.drawable.bread);
            array_image.add(R.drawable.pasta);
            array_image.add(R.drawable.chunky_bocconcini);
            array_image.add(R.drawable.dessert);
            array_image.add(R.drawable.dips);
            array_image.add(R.drawable.beverages);

            title.add(R.string.bread);
            title.add(R.string.pasta);
            title.add(R.string.pizza);
            title.add(R.string.desert);
            title.add(R.string.dips);
            title.add(R.string.beverages);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
//            final Menu movie = movieList.get(position);
            holder.name.setText(title.get(position));
            holder.decription.setVisibility(View.GONE);
            holder.price.setVisibility(View.GONE);

            animate(holder);

            Glide.with(context)
                    .load(array_image.get(position))
                    .into(holder.thumbnail);

            holder.menu_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle=new Bundle();
                    bundle.putString("TITLE", holder.name.getText().toString());
                    Fragment fragment = new MenuSubFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }

        @Override
        public int getItemCount() {
            return 6;
        }

        public void animate(RecyclerView.ViewHolder viewHolder) {
            final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.anticipate_overshoot_interpolator);
            viewHolder.itemView.setAnimation(animAnticipateOvershoot);
        }
    }
}
