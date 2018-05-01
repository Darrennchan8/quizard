package cmsc355.groupF.quizard;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

public class ViewPagerBuilder {
    private AppCompatActivity mContext;
    private PageChangeHook mPageHook = null;
    private RobustAdapter mAdapter = null;
    private ViewPager mViewPager = null;

    public ViewPagerBuilder(AppCompatActivity context) {
        this.mContext = context;
    }

    public ViewPagerBuilder setPageChangeHook(PageChangeHook pageHook) {
        this.mPageHook = pageHook;
        return this;
    }

    public ViewPager build(final ViewPager viewPager, final RobustAdapter adapter) {
        this.mAdapter = adapter;
        this.mViewPager = viewPager;
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                updateAppBar();
            }

            @Override
            public void onInvalidated() {
                super.onInvalidated();
                updateAppBar();
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int previousPosition = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int newPosition) {
                adapter.getItem(previousPosition).onPause();
                adapter.getItem(newPosition).onResume();
                previousPosition = newPosition;
                updateAppBar();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    viewPager.requestFocus();
                    InputMethodManager Ime = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (Ime != null) {
                        Ime.hideSoftInputFromWindow(viewPager.getWindowToken(), 0);
                    }
                }
            }
        });
        if (!(adapter instanceof PagerAdapter)) {
            throw new IllegalArgumentException("adapter must extend either FragmentPagerAdapter or FragmentStatePagerAdapter");
        }
        viewPager.setAdapter((PagerAdapter) adapter);
        updateAppBar();
        return viewPager;
    }

    private void updateAppBar() {
        int position = this.mViewPager.getCurrentItem();
        ActionBar appBar = mContext.getSupportActionBar();
        if (appBar != null && mPageHook != null) {
            appBar.setTitle(mPageHook.getAppBarText(position + 1, this.mAdapter.getCount()));
        }
    }

    public ViewPager build(int resId, RobustAdapter adapter) {
        return this.build((ViewPager) mContext.findViewById(resId), adapter);
    }

    public interface RobustAdapter {
        Fragment getItem(int pos);

        int getCount();

        void registerDataSetObserver(@NonNull DataSetObserver observer);
    }

    public interface PageChangeHook {
        String getAppBarText(int position, int total);
    }
}
