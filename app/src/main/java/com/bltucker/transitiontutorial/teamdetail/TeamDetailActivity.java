package com.bltucker.transitiontutorial.teamdetail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bltucker.transitiontutorial.R;
import com.bltucker.transitiontutorial.data.TeamsItem;

public class TeamDetailActivity extends AppCompatActivity {

    private static final String TEAM_ITEM_ARG = "teamItemArg";

    public static Intent getLaunchIntent(Context context, TeamsItem item) {
        Intent intent = new Intent(context, TeamDetailActivity.class);
        intent.putExtra(TEAM_ITEM_ARG, item);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_team_detail);
        postponeEnterTransition();

        if (null == savedInstanceState) {
            getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, TeamDetailFragment.newInstance(getIntent().<TeamsItem>getParcelableExtra(TEAM_ITEM_ARG)), TeamDetailFragment.TAG)
                .commit();
        }
    }

}
