package com.nyw.eMurals.ui.activity;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.bumptech.glide.Glide;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.nyw.eMurals.R;
import com.nyw.eMurals.base.BaseActivity;
import com.nyw.eMurals.event.SkinChangeEvent;
import com.nyw.eMurals.theme.ColorRelativeLayout;
import com.nyw.eMurals.theme.ColorUiUtil;
import com.nyw.eMurals.theme.ColorView;
import com.nyw.eMurals.theme.Theme;
import com.nyw.eMurals.ui.fragment.HomeFragment;
import com.nyw.eMurals.ui.fragment.LikeFragment;
import com.nyw.eMurals.utils.DimenUtils;
import com.nyw.eMurals.utils.PreUtils;
import com.nyw.eMurals.utils.SystemUtils;
import com.nyw.eMurals.utils.ThemeUtils;
import com.nyw.eMurals.widget.ResideLayout;

import org.greenrobot.eventbus.EventBus;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements ColorChooserDialog.ColorCallback {


    @BindView(R.id.avatar)
    ImageView mAvatar;
    @BindView(R.id.desc)
    TextView mDesc;
    @BindView(R.id.all)
    TextView mAll;
    @BindView(R.id.like)
    TextView mLike;
    @BindView(R.id.photo)
    TextView mPhoto;
    @BindView(R.id.setting)
    TextView mSetting;
    @BindView(R.id.exit)
    TextView mExit;
    @BindView(R.id.share)
    TextView mShare;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.about)
    TextView mAbout;
    @BindView(R.id.theme)
    TextView mTheme;
    @BindView(R.id.menu)
    ColorRelativeLayout mMenu;
    @BindView(R.id.status_bar)
    ColorView mStatusBar;
    @BindView(R.id.icon)
    ImageView mIcon;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.container)
    FrameLayout mContainer;
    @BindView(R.id.resideLayout)
    ResideLayout mResideLayout;

    private FragmentManager fragmentManager;
    private String currentFragmentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mStatusBar.setVisibility(View.VISIBLE);
            mStatusBar.getLayoutParams().height = SystemUtils.getStatusHeight(this);
            mStatusBar.setLayoutParams(mStatusBar.getLayoutParams());
        } else {
            mStatusBar.setVisibility(View.GONE);
        }

        setIconDrawable(mAll, MaterialDesignIconic.Icon.gmi_view_comfy);
        setIconDrawable(mLike, MaterialDesignIconic.Icon.gmi_favorite_outline);
        setIconDrawable(mPhoto, MaterialDesignIconic.Icon.gmi_collection_image_o);
        setIconDrawable(mSetting, MaterialDesignIconic.Icon.gmi_settings);
        setIconDrawable(mExit, MaterialDesignIconic.Icon.gmi_power);
        setIconDrawable(mShare, MaterialDesignIconic.Icon.gmi_share);
        setIconDrawable(mAbout, MaterialDesignIconic.Icon.gmi_account);
        setIconDrawable(mTheme, MaterialDesignIconic.Icon.gmi_palette);
        Glide.with(MainActivity.this)
                .load(R.mipmap.arrtr)
                .into(mAvatar);
        mDesc.setText("我们共同努力，只为这个世界更美好");
        mIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_view_comfy).sizeDp(20));
        mTitle.setText("主页");
        switchFragment("主页");
    }

    private void switchFragment(String name) {
        if (currentFragmentTag != null && currentFragmentTag.equals(name))
            return;

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        Fragment currentFragment = fragmentManager.findFragmentByTag(currentFragmentTag);
        if (currentFragment != null) {
            ft.hide(currentFragment);
        }

        Fragment foundFragment = fragmentManager.findFragmentByTag(name);

        if (foundFragment == null) {
            if (name.equals("主页")){
                foundFragment = new HomeFragment();
            }else if (name.equals("收藏")){
                foundFragment = new LikeFragment();
            }
        }

        if (foundFragment == null) {

        } else if (foundFragment.isAdded()) {
            ft.show(foundFragment);
        } else {
            ft.add(R.id.container, foundFragment, name);
        }
        ft.commit();
        currentFragmentTag = name;
    }

    private void setIconDrawable(TextView view, IIcon icon) {
        view.setCompoundDrawablesWithIntrinsicBounds(new IconicsDrawable(this)
                        .icon(icon)
                        .color(Color.WHITE)
                        .sizeDp(16),
                null, null, null);
        view.setCompoundDrawablePadding(DimenUtils.dp2px(this, 10));
    }

    @Override
    public void onBackPressed() {
        if (mResideLayout.isOpen()) {
            mResideLayout.closePane();
        } else {
            super.onBackPressed();
        }
    }

    @OnClick({R.id.avatar, R.id.all, R.id.like, R.id.photo,
            R.id.setting, R.id.share, R.id.exit,
           R.id.about, R.id.theme, R.id.icon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.avatar:
                break;
            case R.id.all:
                mResideLayout.closePane();
                mIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_view_comfy).sizeDp(20));
                mTitle.setText("主页");
                switchFragment("主页");
                break;
            case R.id.like:
                mResideLayout.closePane();
                mIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_favorite_outline).sizeDp(20));
                mTitle.setText("收藏");
                switchFragment("收藏");
                break;
            case R.id.photo:
                Intent intent = new Intent(Intent.ACTION_SET_WALLPAPER);
                startActivity(Intent.createChooser(intent, "选择壁纸"));
                break;
            case R.id.setting:
                mResideLayout.closePane();
                mIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_collection_video).sizeDp(20));
                break;
            case R.id.share:
                /*Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_SEND);
                intent2.putExtra(Intent.EXTRA_TEXT, "http://a.app.qq.com/o/simple.jsp?pkgname=com.dingmouren.wallpager");
                intent2.setType("text/plain");
                startActivity(Intent.createChooser(intent, "分享下载链接到"));*/
                break;
            case R.id.exit:
                new MaterialDialog.Builder(this)
                        .title(R.string.exit)
                        .content(R.string.exit_me, true)
                        .negativeText(R.string.close)
                        .positiveText(R.string.done)
                        .contentColor(Color.BLACK)
                        .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            System.exit(0);
                        } else if (which == DialogAction.NEGATIVE) {
                        }
                    }
                }).show();
                break;
            case R.id.about:
                new MaterialDialog.Builder(this)
                        .title(R.string.about)
                        .icon(new IconicsDrawable(this)
                                .color(ThemeUtils.getThemeColor(this, R.attr.colorPrimary))
                                .icon(MaterialDesignIconic.Icon.gmi_account)
                                .sizeDp(20))
                        .content(R.string.about_me)
                        .positiveText(R.string.close)
                        .contentColor(Color.BLACK)
                        .show();
                break;
            case R.id.theme:
                new ColorChooserDialog.Builder(this, R.string.theme)
                        .customColors(R.array.colors, null)
                        .doneButton(R.string.done)
                        .cancelButton(R.string.cancel)
                        .allowUserColorInput(false)
                        .allowUserColorInputAlpha(false)
                        .show();
                break;
            case R.id.icon:
                mResideLayout.openPane();
                break;
        }
    }

    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, @ColorInt int selectedColor) {
        if (selectedColor == ThemeUtils.getThemeColor(this, R.attr.colorPrimary))
            return;
        EventBus.getDefault().post(new SkinChangeEvent());

        if (selectedColor == getResources().getColor(R.color.colorBluePrimary)) {
            setTheme(R.style.BlueTheme);
            PreUtils.setCurrentTheme(this, Theme.Blue);

        } else if (selectedColor == getResources().getColor(R.color.colorRedPrimary)) {
            setTheme(R.style.RedTheme);
            PreUtils.setCurrentTheme(this, Theme.Red);

        } else if (selectedColor == getResources().getColor(R.color.colorBrownPrimary)) {
            setTheme(R.style.BrownTheme);
            PreUtils.setCurrentTheme(this, Theme.Brown);

        } else if (selectedColor == getResources().getColor(R.color.colorGreenPrimary)) {
            setTheme(R.style.GreenTheme);
            PreUtils.setCurrentTheme(this, Theme.Green);

        } else if (selectedColor == getResources().getColor(R.color.colorPurplePrimary)) {
            setTheme(R.style.PurpleTheme);
            PreUtils.setCurrentTheme(this, Theme.Purple);

        } else if (selectedColor == getResources().getColor(R.color.colorTealPrimary)) {
            setTheme(R.style.TealTheme);
            PreUtils.setCurrentTheme(this, Theme.Teal);

        } else if (selectedColor == getResources().getColor(R.color.colorPinkPrimary)) {
            setTheme(R.style.PinkTheme);
            PreUtils.setCurrentTheme(this, Theme.Pink);

        } else if (selectedColor == getResources().getColor(R.color.colorDeepPurplePrimary)) {
            setTheme(R.style.DeepPurpleTheme);
            PreUtils.setCurrentTheme(this, Theme.DeepPurple);

        } else if (selectedColor == getResources().getColor(R.color.colorOrangePrimary)) {
            setTheme(R.style.OrangeTheme);
            PreUtils.setCurrentTheme(this, Theme.Orange);

        } else if (selectedColor == getResources().getColor(R.color.colorIndigoPrimary)) {
            setTheme(R.style.IndigoTheme);
            PreUtils.setCurrentTheme(this, Theme.Indigo);

        } else if (selectedColor == getResources().getColor(R.color.colorLightGreenPrimary)) {
            setTheme(R.style.LightGreenTheme);
            PreUtils.setCurrentTheme(this, Theme.LightGreen);

        } else if (selectedColor == getResources().getColor(R.color.colorDeepOrangePrimary)) {
            setTheme(R.style.DeepOrangeTheme);
            PreUtils.setCurrentTheme(this, Theme.DeepOrange);

        } else if (selectedColor == getResources().getColor(R.color.colorLimePrimary)) {
            setTheme(R.style.LimeTheme);
            PreUtils.setCurrentTheme(this, Theme.Lime);

        } else if (selectedColor == getResources().getColor(R.color.colorBlueGreyPrimary)) {
            setTheme(R.style.BlueGreyTheme);
            PreUtils.setCurrentTheme(this, Theme.BlueGrey);

        } else if (selectedColor == getResources().getColor(R.color.colorCyanPrimary)) {
            setTheme(R.style.CyanTheme);
            PreUtils.setCurrentTheme(this, Theme.Cyan);

        }
        final View rootView = getWindow().getDecorView();
        rootView.setDrawingCacheEnabled(true);
        rootView.buildDrawingCache(true);

        final Bitmap localBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
        if (null != localBitmap && rootView instanceof ViewGroup) {
            final View tmpView = new View(getApplicationContext());
            tmpView.setBackgroundDrawable(new BitmapDrawable(getResources(), localBitmap));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) rootView).addView(tmpView, params);
            tmpView.animate().alpha(0).setDuration(400).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    ColorUiUtil.changeTheme(rootView, getTheme());
                    System.gc();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    ((ViewGroup) rootView).removeView(tmpView);
                    localBitmap.recycle();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();
        }
    }
}
