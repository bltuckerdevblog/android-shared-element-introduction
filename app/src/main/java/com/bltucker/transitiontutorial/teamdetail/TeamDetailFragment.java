package com.bltucker.transitiontutorial.teamdetail;


import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bltucker.transitiontutorial.DaggerInjector;
import com.bltucker.transitiontutorial.data.TeamsItem;
import com.bltucker.transitiontutorial.databinding.FragmentTeamDetailBinding;
import com.bltucker.transitiontutorial.glide.GlideRequestProvider;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TeamDetailFragment extends Fragment implements TeamDetailView {

    public static final String TAG = "TeamDetailFragment";

    private static final String TEAM_ITEM_ARG = "teamItem";

    private FragmentTeamDetailBinding binding;

    @Inject
    TeamDetailViewPresenter viewPresenter;

    @Inject
    GlideRequestProvider glideRequestProvider;

    public TeamDetailFragment() {
        // Required empty public constructor
    }

    public static TeamDetailFragment newInstance(TeamsItem teamsItem) {
        TeamDetailFragment fragment = new TeamDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(TEAM_ITEM_ARG, teamsItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TeamsItem teamsItem = getArguments().getParcelable(TEAM_ITEM_ARG);
        DaggerInjector.getApplicationComponent()
            .teamDetailFragmentComponentBuilder()
            .bindTeam(teamsItem)
            .build()
            .inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPresenter.onViewCreated(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTeamDetailBinding.inflate(inflater, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.fragmentToolbar);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewPresenter.onViewResumed(this);
    }

    public void onPause() {
        viewPresenter.onViewPaused();
        super.onPause();
    }

    @Override
    public void displayTeamDetails(TeamsItem teamsItem) {
        binding.setTeamItem(teamsItem);
        if (teamsItem.getCrestUrl().contains(".svg")) {
            loadSvgCrest(teamsItem);
        } else {
            loadPngCrest(teamsItem);
        }
    }


    private void loadPngCrest(TeamsItem team) {
        Glide.with(binding.getRoot().getContext())
            .load(team.getCrestUrl())
            .dontAnimate()
            .skipMemoryCache(true)
            .listener(new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    getActivity().startPostponedEnterTransition();
                    return false;
                }
            })
            .into(binding.teamCrestImageView);
    }


    private void loadSvgCrest(final TeamsItem team) {

        Uri uri = Uri.parse(team.getCrestUrl());
        FutureTarget<PictureDrawable> into = glideRequestProvider.getGenericRequest(getContext())
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .dontAnimate()
            .skipMemoryCache(true)
            .load(uri)
            .into(250, 250);


        Observable.fromFuture(into)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<PictureDrawable>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(@NonNull PictureDrawable pictureDrawable) {
                    binding.teamCrestImageView.setImageDrawable(pictureDrawable);
                    getActivity().startPostponedEnterTransition();
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
    }
}
