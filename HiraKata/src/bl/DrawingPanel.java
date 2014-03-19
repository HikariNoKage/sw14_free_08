package bl;

import android.content.Context;
import android.view.View;
import android.util.AttributeSet;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.view.MotionEvent;

public class DrawingPanel extends View {

	private Path dPath;
	private Paint dPaint, cPaint;
	private int pColor = 0xFF660000;
	private Canvas dCanvas;
	private Bitmap cBitmap;
	private boolean drawAble = false;

	public DrawingPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		setupDrawing();
	}

	private void setupDrawing() {
		this.dPath = new Path();
		this.dPaint = new Paint();

		this.dPaint.setColor(this.pColor);
		this.dPaint.setAntiAlias(true);
		this.dPaint.setStrokeWidth(20);
		this.dPaint.setStyle(Paint.Style.STROKE);
		this.dPaint.setStrokeJoin(Paint.Join.ROUND);
		this.dPaint.setStrokeCap(Paint.Cap.ROUND);

		this.cPaint = new Paint(Paint.DITHER_FLAG);
		
		

	}

	public void setColor(String newColor) {
		// this.invalidate();
		this.pColor = Color.parseColor(newColor);
		this.dPaint.setColor(this.pColor);
	}

	public void setStrokeWidth(int width) {
		this.dPaint.setStrokeWidth(width);
	}

	public void setDrawAble(boolean drawAble) {
		this.drawAble = drawAble;
	}

	public void newDrawing() {
		this.dCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
		this.invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (this.drawAble) {
			float xCoordinate = event.getX();
			float yCoordinate = event.getY();

			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				this.dPath.moveTo(xCoordinate, yCoordinate);
			} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
				this.dPath.lineTo(xCoordinate, yCoordinate);
			} else if (event.getAction() == MotionEvent.ACTION_POINTER_UP) {
				this.dCanvas.drawPath(this.dPath, this.dPaint);
				this.dPath.reset();
			} else {
				return false;
			}
			this.invalidate();
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(this.cBitmap, 0, 0, this.cPaint);
		canvas.drawPath(this.dPath, this.dPaint);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		this.cBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		this.dCanvas = new Canvas(this.cBitmap);
	}

}
