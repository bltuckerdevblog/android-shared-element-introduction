package com.bltucker.transitiontutorial.teamlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bltucker.transitiontutorial.R;
import com.bltucker.transitiontutorial.databinding.ActivityTeamListBinding;

public class TeamListActivity extends AppCompatActivity {

    private ActivityTeamListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team_list);

        if (null == savedInstanceState) {
            getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_container, TeamListFragment.newInstance(), TeamListFragment.TAG)
                .commit();
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        TeamListFragment fragmentByTag = (TeamListFragment) getSupportFragmentManager().findFragmentByTag(TeamListFragment.TAG);

        if (fragmentByTag != null) {
            return fragmentByTag.getTeamListModel();
        } else {
            return super.onRetainCustomNonConfigurationInstance();
        }
    }
}
