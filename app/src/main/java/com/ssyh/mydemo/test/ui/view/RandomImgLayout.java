package com.ssyh.mydemo.test.ui.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ssyh.mydemo.R;
import com.ssyh.mydemo.test.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomImgLayout extends RelativeLayout implements View.OnClickListener{
    private String TAG = "RandomImgLayout";
    private Context mContext;
    private int mWidth;
    private int mHeight;
    private int ivViewWidth = DensityUtil.dp2px(50);
    private int ivViewHeight = DensityUtil.dp2px(50);

    private int indicaViewWidth = DensityUtil.dp2px(40);


    private List<Integer> imgResList = new ArrayList<>();
    private List<Integer> effectClickResList = new ArrayList<>();
    private int showCount = 3;
//    private int effectClickRes;

    private String HONGBAO_TAG = "hongbao";
    private String INDICA = "indica";
    private boolean isAddImgs = false;
    private ImageView bgImageView;
    private int alreadyAddChildCount;
    private RelativeLayout rlImgContanier;
    private ObjectAnimator bgIvAnim;
    private AnimatorSet imgAnim;

    private final int minMarginleft = indicaViewWidth;
    private final int minMarginTop = indicaViewWidth;
    private ImageView indicaIv;
    private AnimatorSet indicaAnim;

    private OnEffectClickListener onEffectClickListener;


    public void setOnEffectClickListener(OnEffectClickListener onEffectClickListener) {
        this.onEffectClickListener = onEffectClickListener;
    }

    public RandomImgLayout(Context context) {
        this(context,null);
    }

    public RandomImgLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RandomImgLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        setOnClickListener(this);
        init();
    }

    private void init() {

        imgResList.add(R.drawable.icon_caomei);
        imgResList.add(R.drawable.icon_ximei);
        imgResList.add(R.drawable.icon_xiangjiao);
        imgResList.add(R.drawable.icon_pingguo);
        imgResList.add(R.drawable.icon_niuyouguo);
        imgResList.add(R.drawable.icon_mangguo);
        imgResList.add(R.drawable.icon_qingning);
        imgResList.add(R.drawable.icon_yingtao);
        imgResList.add(R.drawable.icon_cengzi);
        imgResList.add(R.drawable.icon_lanmei);


        effectClickResList.add(R.drawable.icon_hongbao1);
        effectClickResList.add(R.drawable.icon_hongbao2);
        effectClickResList.add(R.drawable.icon_hongbao3);
        effectClickResList.add(R.drawable.icon_hongbao4);
        effectClickResList.add(R.drawable.icon_hongbao5);

    }

    public void setImgResList(List<Integer> imgResList,int showCount,List<Integer> effectClickResList) {
        if (null == imgResList || null == effectClickResList)
            return;
        this.imgResList = imgResList;
        this.effectClickResList = effectClickResList;
        this.showCount = showCount;
        if (this.showCount > imgResList.size()){
            this.showCount = imgResList.size();
        }
        show();

    }
    public void show(boolean showImgAnim){
        /*if (VISIBLE != getVisibility() || (null != getParent() && VISIBLE != ((ViewGroup)getParent()).getVisibility()))
            return;*/
        if (!isShown())
            return;

        //背景层
        if (null == bgImageView){
            bgImageView = new ImageView(mContext);
            bgImageView.setImageResource(R.drawable.bg_circle_reward_anim);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            bgImageView.setLayoutParams(layoutParams);
            addView(bgImageView,0);
        }
        //图片层
        if (null == rlImgContanier){
            rlImgContanier = new RelativeLayout(mContext);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            rlImgContanier.setLayoutParams(layoutParams);
            addView(rlImgContanier,1);
        }

        Random random = new Random();
        LayoutParams layoutParams;
        int maxLeft = mWidth- ivViewWidth - minMarginleft;
        int maxTop = mHeight- ivViewHeight - minMarginTop;
        ImageView imageView;
        int showResId;

        if (maxLeft <= 0){
            maxLeft = 1;
        }

        if (maxTop <= 0){
            maxTop = 1;
        }
        int hongbaoMarginLeft = 0;
        int hongbaoMarginTop = 0;
        for (int i = 0; i < showCount; i++) {

            int marginLeft = random.nextInt(maxLeft);
            int marginTop = random.nextInt(maxTop);
            if (marginLeft < minMarginleft){
                marginLeft = minMarginleft;
            }
            if (marginTop < minMarginTop){
                marginTop = minMarginTop;
            }
            if (i == showCount-1){
                showResId = effectClickResList.get(random.nextInt(effectClickResList.size()));
                hongbaoMarginLeft = marginLeft;
                hongbaoMarginTop = marginTop;
            }else {
                showResId = imgResList.get(random.nextInt(imgResList.size()));
            }

            if (!isAddImgs){
                imageView = new ImageView(mContext);
                imageView.setOnClickListener(this);
                if (i == showCount-1){
                    imageView.setTag(HONGBAO_TAG);
                }
                imageView.setImageResource(showResId);
                layoutParams = new LayoutParams(ivViewWidth, ivViewHeight);
                layoutParams.leftMargin = marginLeft;
                layoutParams.topMargin = marginTop;
                imageView.setLayoutParams(layoutParams);
                //背景图片是第一个子View，这里是i+1
                rlImgContanier.addView(imageView,i);
            }else {
                //背景图片是第一个子View，这里是i+1
                ImageView childView = (ImageView) rlImgContanier.getChildAt(i);
                childView.setImageResource(showResId);
                ((LayoutParams)childView.getLayoutParams()).leftMargin = marginLeft;
                ((LayoutParams)childView.getLayoutParams()).topMargin = marginTop;
                childView.setLayoutParams(childView.getLayoutParams());
            }
        }
        //图片层添加引导小手
        if (null == rlImgContanier.getChildAt(showCount)){
            indicaIv = new ImageView(mContext);
            indicaIv.setOnClickListener(this);
            indicaIv.setImageResource(R.drawable.icon_indica_small_hand);
            layoutParams = new LayoutParams(indicaViewWidth, indicaViewWidth);
            layoutParams.leftMargin = hongbaoMarginLeft + ivViewWidth;
            layoutParams.topMargin = hongbaoMarginTop + ivViewHeight;
            indicaIv.setLayoutParams(layoutParams);
            rlImgContanier.addView(indicaIv,showCount);
        }else {
            ImageView childView = (ImageView) rlImgContanier.getChildAt(showCount);
            ((LayoutParams)childView.getLayoutParams()).leftMargin = hongbaoMarginLeft + ivViewWidth;
            ((LayoutParams)childView.getLayoutParams()).topMargin = hongbaoMarginTop + ivViewHeight;
            childView.setLayoutParams(childView.getLayoutParams());
        }
        indicaIv.setVisibility(INVISIBLE);

        if (rlImgContanier.getChildCount() > 0){
            isAddImgs = true;
        }


        //旋转动画
        bgIvAnim = ObjectAnimator.ofFloat(bgImageView, "rotation", 0f, 360f);
        bgIvAnim.setRepeatCount(ObjectAnimator.INFINITE);
        bgIvAnim.setInterpolator(new LinearInterpolator());
        bgIvAnim.setDuration(3000);
        bgIvAnim.start();


        //闪一闪动画
        if (showImgAnim){
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(rlImgContanier, "scaleX", 1f, 1.1f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(rlImgContanier, "scaleY", 1f, 1.1f);

            scaleX.setRepeatCount(5);
            scaleX.setRepeatMode(ValueAnimator.REVERSE);
            scaleX.setDuration(200);

            scaleY.setRepeatCount(5);
            scaleY.setRepeatMode(ValueAnimator.REVERSE);
            scaleY.setDuration(200);

            imgAnim = new AnimatorSet();
            imgAnim.play(scaleX).with(scaleY);
            imgAnim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    startIndicaAnim();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            imgAnim.start();
        }else {
            startIndicaAnim();
        }


    }
    private void startIndicaAnim(){
        if (null == indicaIv)
            return;
        indicaIv.setVisibility(VISIBLE);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(indicaIv, "translationX", 0, -30f);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(indicaIv, "translationY", 0, -30f);

        translationX.setRepeatCount(ObjectAnimator.INFINITE);
        translationX.setInterpolator(new LinearInterpolator());
        translationX.setRepeatMode(ValueAnimator.REVERSE);
        translationX.setDuration(300);

        translationY.setRepeatCount(ObjectAnimator.INFINITE);
        translationY.setInterpolator(new LinearInterpolator());
        translationY.setRepeatMode(ValueAnimator.REVERSE);
        translationY.setDuration(300);

        indicaAnim = new AnimatorSet();
        indicaAnim.play(translationX).with(translationY);
        indicaAnim.start();
    }

    public void show(){
         show(false);
    }

    public void stopAnim(){
        if (null != bgIvAnim){
            bgIvAnim.cancel();
        }

        if (null != imgAnim){
            imgAnim.cancel();
        }
        if (null != indicaAnim){
            indicaAnim.cancel();
        }
    }





    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getWidth();
        mHeight = getHeight();
        Log.d(TAG,"width: "+getWidth()+"\nheight: "+getHeight()+"\nmeasuredWidth: "+getMeasuredWidth()+"\nmeasuredHeight: "+getMeasuredHeight()+"\nonSizeChanged");

        post(new Runnable() {
            @Override
            public void run() {
                show();
            }
        });
    }



    @Override
    public void onClick(View v) {

        if (HONGBAO_TAG == v.getTag()){
            Toast.makeText(mContext,"点中红包",Toast.LENGTH_SHORT).show();
            if (null != onEffectClickListener){
                onEffectClickListener.onEffectClick(v);
            }
        }else if (v == this){
            show();
        }else {
            Toast.makeText(mContext,"请点击红包",Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * 有效点击 ，（红包等）
     */
    public interface OnEffectClickListener{
        void onEffectClick(View view);
    }


}
