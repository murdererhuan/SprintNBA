package com.yuyh.cavaliers.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.yuyh.cavaliers.R;
import com.yuyh.cavaliers.base.BaseSwipeBackCompatActivity;
import com.yuyh.cavaliers.base.BaseWebActivity;
import com.yuyh.cavaliers.http.bean.player.Teams;
import com.yuyh.cavaliers.presenter.Presenter;
import com.yuyh.cavaliers.presenter.impl.AllTeamsPresenterImpl;
import com.yuyh.cavaliers.recycleview.OnListItemClickListener;
import com.yuyh.cavaliers.ui.adapter.TeamsListAdapter;
import com.yuyh.cavaliers.ui.view.AllTeamsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * @author yuyh.
 * @date 16/6/11.
 */
public class AllTeamActivity extends BaseSwipeBackCompatActivity implements AllTeamsView, OnListItemClickListener<Teams.TeamsBean.Team> {

    @InjectView(R.id.lvAllTeam)
    ListView lvAllTeam;

    private TeamsListAdapter adapter;
    private List<Teams.TeamsBean.Team> list = new ArrayList<>();
    private Presenter presenter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_all_team;
    }

    @Override
    protected void initViewsAndEvents() {
        setTitle("球队列表");
        adapter = new TeamsListAdapter(list, this, R.layout.list_item_teams);
        adapter.setOnListItemClickListener(this);
        lvAllTeam.setAdapter(adapter);
        presenter = new AllTeamsPresenterImpl(this, this);
        presenter.initialized();
    }

    @Override
    public void showAllTeams(Teams.TeamsBean bean) {
        list.clear();
        list.addAll(bean.east);
        list.addAll(bean.west);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position, Teams.TeamsBean.Team data) {
        Intent intent = new Intent(this, BaseWebActivity.class);
        intent.putExtra(BaseWebActivity.BUNDLE_KEY_TITLE, data.fullCnName);
        intent.putExtra(BaseWebActivity.BUNDLE_KEY_URL, data.detailUrl);
        startActivity(intent);
    }
}
