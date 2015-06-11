package com.github.ismaeltoe.movies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.ismaeltoe.movies.model.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    static final String DETAIL_MOVIE = "DETAIL_MOVIE";

    private Movie mMovie;

    private ImageView mImageView;
    private TextView mTitleView;
    private TextView mOverviewView;
    private TextView mDateView;
    private TextView mVoteAverageView;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        if (arguments != null) {
            mMovie = arguments.getParcelable(DetailActivityFragment.DETAIL_MOVIE);
        }

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        mImageView = (ImageView) rootView.findViewById(R.id.detail_image);
        mTitleView = (TextView) rootView.findViewById(R.id.detail_title);
        mOverviewView = (TextView) rootView.findViewById(R.id.detail_overview);
        mDateView = (TextView) rootView.findViewById(R.id.detail_date);
        mVoteAverageView = (TextView) rootView.findViewById(R.id.detail_vote_average);

        String image_url = "http://image.tmdb.org/t/p/w342" + mMovie.getImage2();
        Glide.with(this).load(image_url).into(mImageView);

        mTitleView.setText(mMovie.getTitle());
        mOverviewView.setText(mMovie.getOverview());

        String movie_date = mMovie.getDate();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String date = DateUtils.formatDateTime(getActivity(),
                    formatter.parse(movie_date).getTime(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR);
            mDateView.setText(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mVoteAverageView.setText(Integer.toString(mMovie.getRating()));

        return rootView;
    }
}
