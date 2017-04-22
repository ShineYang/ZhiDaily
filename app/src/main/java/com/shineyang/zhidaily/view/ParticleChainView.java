package com.shineyang.zhidaily.view;

/**
 * Created by ShineYang on 2017/1/12.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.shineyang.zhidaily.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * A particle chain view, particle chain changed by touch event.
 *
 * @TODO only a demo view.
 */

public class ParticleChainView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder mHolder;
    private Thread mDrawThread;
    private boolean mDrawThreadRunning;

    private List<RandomParticle> mRandomParticles;
    private Paint mParticlePaint;
    private Bitmap mBgBitmap;

    private Point mTouchPoint;

    public ParticleChainView(Context context) {
        this(context, null);
    }

    public ParticleChainView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParticleChainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        mHolder = this.getHolder();
        mHolder.setFormat(PixelFormat.RGB_565);
        mHolder.addCallback(this);

        mParticlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mParticlePaint.setStrokeJoin(Paint.Join.ROUND);
        mParticlePaint.setStrokeCap(Paint.Cap.ROUND);
        mTouchPoint = new Point();

    }

    @Override
    public void run() {
        Date interval = new Date();
        while (mDrawThreadRunning) {
            synchronized (mHolder) {
                Canvas canvas = null;
                try {
                    canvas = mHolder.lockCanvas();
                    loopDraw(canvas);
                    //计算绘图时间间隔，设定帧数在30帧左右
                    Thread.sleep(Math.max(0, 33 - new Date().getTime() - interval.getTime()));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (mHolder != null && mHolder.getSurface().isValid()) {
                        mHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }

    private void loopDraw(Canvas canvas) {
        canvas.drawColor(getResources().getColor(R.color.grey100));
        List<RandomParticle> lines = new ArrayList<>();
        for (int index = 0; mRandomParticles != null && index < mRandomParticles.size(); index++) {
            RandomParticle particle = mRandomParticles.get(index);
            if (isInnerLineArea(particle)) {
                lines.add(particle);
            }
        }

        for (int index = 0; index < lines.size(); index++) {
            for (int jdex = 0; jdex < lines.size(); jdex++) {
                RandomParticle particle1 = lines.get(index);
                RandomParticle particle2 = lines.get(jdex);
                if (doubleParticleDistance(particle1, particle2) < 300
                        && doubleParticleDistance(particle1, particle2) > 0) {
                    int lineColor = Color.argb(120, 210, 210, 210);
                    mParticlePaint.setStrokeWidth(1);
                    mParticlePaint.setColor(lineColor);
                    canvas.drawLine(particle1.positionX, particle1.positionY,
                            particle2.positionX, particle2.positionY, mParticlePaint);
                }
            }
        }

        for (int index = 0; mRandomParticles != null && index < mRandomParticles.size(); index++) {
            RandomParticle particle = mRandomParticles.get(index);
            mParticlePaint.setColor(particle.color);
            particle.move();
            canvas.drawCircle(particle.positionX, particle.positionY, particle.radius, mParticlePaint);
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mDrawThreadRunning = true;
        mDrawThread = new Thread(this);
        mDrawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mDrawThreadRunning = false;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //TODO default position.
        mTouchPoint.x = getMeasuredWidth() / 2;
        mTouchPoint.y = getMeasuredHeight() / 2;
        mRandomParticles = new ArrayList<>();
        for (int index = 0; index < 15; index++) {
            RandomParticle particle = new RandomParticle(getMeasuredWidth(), getMeasuredHeight());
            mRandomParticles.add(particle);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mTouchPoint.set((int) event.getX(), (int) event.getY());
        return true;
    }

    public boolean isInnerLineArea(RandomParticle particle) {
        int disX = Math.abs(mTouchPoint.x - particle.positionX);
        int disY = Math.abs(mTouchPoint.y - particle.positionY);
        if (Math.sqrt(disX * disX + disY * disY) <= 300 - particle.radius) {
            return true;
        }
        return false;
    }

    public int doubleParticleDistance(RandomParticle particle1, RandomParticle particle2) {
        int disx = Math.abs(particle1.positionX - particle2.positionX);
        int disy = Math.abs(particle1.positionY - particle2.positionY);
        return (int) Math.sqrt(disx * disx + disy * disy);
    }

    public static class RandomParticle {
        public int radius;
        public int color;

        public int positionX;
        public int positionY;

        public int speedDirectionX;
        public int speedDirectionY;

        private Point mMeasurePoint;

        public RandomParticle(int width, int heigh) {
            this.mMeasurePoint = new Point(width, heigh);
            this.radius = randomRange(5, 12);
            int randomColor = randomRange(180, 255);
            this.color = Color.argb(200, randomColor, randomColor, randomColor);
            this.positionX = randomRange(this.radius, this.mMeasurePoint.x - this.radius);
            this.positionY = randomRange(this.radius, this.mMeasurePoint.y - this.radius);
            this.speedDirectionX = randomRange(1, 2) * (new Random().nextBoolean() ? 1 : -1);
            this.speedDirectionY = randomRange(1, 2) * (new Random().nextBoolean() ? 1 : -1);
        }

        public void move() {
            this.positionX += this.speedDirectionX;
            this.positionY += this.speedDirectionY;

            if (this.positionX <= this.radius) {
                this.positionX = this.radius;
                this.speedDirectionX *= -1;
            }
            if (this.positionX >= this.mMeasurePoint.x - this.radius) {
                this.positionX = this.mMeasurePoint.x - this.radius;
                this.speedDirectionX *= -1;
            }
            if (this.positionY <= this.radius) {
                this.positionY = this.radius;
                this.speedDirectionY *= -1;
            }
            if (this.positionY >= this.mMeasurePoint.y - this.radius) {
                this.positionY = this.mMeasurePoint.y - this.radius;
                this.speedDirectionY *= -1;
            }
        }

        private int randomRange(int min, int max) {
            Random random = new Random();
            return random.nextInt(max) % (max - min + 1) + min;
        }
    }
}
