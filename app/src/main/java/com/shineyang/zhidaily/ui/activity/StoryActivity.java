package com.shineyang.zhidaily.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.shineyang.zhidaily.R;
import com.shineyang.zhidaily.ui.fragment.StoryFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StoryActivity extends AppCompatActivity {
    @BindView(R.id.bottom_toolbar)
    Toolbar bottom_toolbar;

    private static final String SHARE_URL = "http://daily.zhihu.com/story/";
    private String storyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        ButterKnife.bind(this);
        initBottomToolbar();

        if (savedInstanceState == null) {
            storyId = getIntent().getStringExtra("story_id");
            StoryFragment storyFragment = StoryFragment.newInstance(storyId);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, storyFragment, StoryFragment.TAG)
                    .commit();
        }

        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);

    }

    public void initBottomToolbar() {
        bottom_toolbar.setTitle("");
        setSupportActionBar(bottom_toolbar);
        bottom_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bottom_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_comment:
                        Intent intent = new Intent(StoryActivity.this, CommentActivity.class);
                        intent.putExtra("id", storyId);
                        startActivity(intent);
                        break;
                    case R.id.action_share:
                        shareUrl();
                        break;
                }
                return true;
            }
        });


    }

    private void shareUrl() {
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);
        share_intent.setType("text/plain");
        share_intent.putExtra(Intent.EXTRA_TEXT, SHARE_URL + storyId);
        share_intent = Intent.createChooser(share_intent, getResources().getString(R.string.text_share_story_url));
        startActivity(share_intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_bottom_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
