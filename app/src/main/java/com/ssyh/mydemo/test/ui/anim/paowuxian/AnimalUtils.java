package com.ssyh.mydemo.test.ui.anim.paowuxian;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.PointF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AnimalUtils {
    /**
     * 二阶贝塞尔曲线实现抛物线动画
     * @param startView 起始地 view
     * @param endView 目的地 View
     * @param animViewContanier 动画View的容器
     * @param animRes 动画View的ImageRes
     * @param context
     * @param listener
     */
    public static void add(View startView, final View endView, final ViewGroup animViewContanier, int animRes, final Context context, final Animator.AnimatorListener listener) {
        int[] startCoordinate = new int[2];
        int[] contanierCoordinate = new int[2];
        int[] endCoordinate = new int[2];
        //1.分别获取被点击View、父布局、购物车在屏幕上的坐标xy。
        startView.getLocationInWindow(startCoordinate);
        animViewContanier.getLocationInWindow(contanierCoordinate);
        endView.getLocationInWindow(endCoordinate);

        //2.自定义ImageView 继承ImageView
        MoveImageView img = new MoveImageView(context);
        int ivMoveWidth = 100;
        int ivMoveHeight = 100;
        int startViewWidth = startView.getWidth();
        int startViewHeight = startView.getHeight();
        int endViewWidth = endView.getWidth();
        int endViewHeight = endView.getHeight();


        int startDiffX = startViewWidth/2 - ivMoveWidth/2;
        int startDiffY = startViewHeight/2 - ivMoveHeight/2;


        int endDiffX = endViewWidth/2 - ivMoveWidth/2;
        int endDiffY = endViewHeight/2 - ivMoveHeight/2;

        img.setLayoutParams(new ViewGroup.LayoutParams(ivMoveWidth, ivMoveHeight));
        img.setImageResource(animRes);
        //3.设置img在父布局中的坐标位置
        img.setX(startCoordinate[0] - contanierCoordinate[0] + startDiffX);
        img.setY(startCoordinate[1] - contanierCoordinate[1] + startDiffY);
        //4.父布局添加该Img
        animViewContanier.addView(img);

        //5.利用 二次贝塞尔曲线 需首先计算出 MoveImageView的2个数据点和一个控制点
        PointF startP = new PointF();
        PointF endP = new PointF();
        PointF controlP = new PointF();
        //开始的数据点坐标就是 addV的坐标
        startP.x = startCoordinate[0] - contanierCoordinate[0] + startDiffX;
        startP.y = startCoordinate[1] - contanierCoordinate[1] + startDiffY;
        //结束的数据点坐标就是 shopImg的坐标
        endP.x = endCoordinate[0] - contanierCoordinate[0] + endDiffX;
        endP.y = endCoordinate[1] - contanierCoordinate[1] + endDiffY;
        //控制点坐标 x等于 购物车x；y等于 addV的y
        controlP.x = endP.x;
        controlP.y = startP.y;

        //启动属性动画
        ObjectAnimator animator = ObjectAnimator.ofObject(img, "mPointF",
                new PointFTypeEvaluator(controlP), startP, endP);
        animator.setDuration(1000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (null != listener){
                    listener.onAnimationStart(animation);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (null != listener){
                    listener.onAnimationEnd(animation);
                }
                //动画结束后 父布局移除 img
                Object target = ((ObjectAnimator) animation).getTarget();
                animViewContanier.removeView((View) target);


            }

            @Override
            public void onAnimationCancel(Animator animation) {
                if (null != listener){
                    listener.onAnimationCancel(animation);
                }
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                if (null != listener){
                    listener.onAnimationRepeat(animation);
                }
            }
        });
        animator.start();
    }

    public static abstract class AnimatorListener implements Animator.AnimatorListener{
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public abstract void onAnimationEnd(Animator animation);

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }



    public static class PointFTypeEvaluator implements TypeEvaluator<PointF> {
        /**
         * 每个估值器对应一个属性动画，每个属性动画仅对应唯一一个控制点
         */
        PointF control;
        /**
         * 估值器返回值
         */
        PointF mPointF = new PointF();

        public PointFTypeEvaluator(PointF control) {
            this.control = control;
        }

        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            return getBezierPoint(startValue, endValue, control, fraction);
        }

        /**
         * 二次贝塞尔曲线公式
         *
         * @param start   开始的数据点
         * @param end     结束的数据点
         * @param control 控制点
         * @param t       float 0-1
         * @return 不同t对应的PointF
         */
        private PointF getBezierPoint(PointF start, PointF end, PointF control, float t) {
            mPointF.x = (1 - t) * (1 - t) * start.x + 2 * t * (1 - t) * control.x + t * t * end.x;
            mPointF.y = (1 - t) * (1 - t) * start.y + 2 * t * (1 - t) * control.y + t * t * end.y;
            return mPointF;
        }
    }


    public static class MoveImageView extends ImageView {

        public MoveImageView(Context context) {
            super(context);
        }

        public void setMPointF(PointF pointF) {
            setX(pointF.x);
            setY(pointF.y);
        }
    }

}
