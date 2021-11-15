package com.codepath.doggydatingdeluxe.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.doggydatingdeluxe.DogBlogAdapter;
import com.codepath.doggydatingdeluxe.Post;
import com.codepath.doggydatingdeluxe.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView rvPosts;
    public static final String TAG = "HomeFragment";
    protected DogBlogAdapter adapter;
    protected List<Post> allPosts;

    public HomeFragment(){


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        rvPosts = view.findViewById(R.id.rvPosts);
        allPosts = new ArrayList<>();
        adapter = new DogBlogAdapter(getContext(),allPosts);

        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();
    }
    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null){
                    Log.e(TAG,"Issue with getting posts", e);
                    return;
                }

                for(Post post : posts){
                    Log.i(TAG, "Post: " + post.getDescription() + " username: " + post.getUser().getUsername());
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }


}