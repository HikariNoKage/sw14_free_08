package app;


import android.content.Context;
import android.view.View;
import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
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
		this.dPaint.setStrokeWidth(15);
		this.dPaint.setStyle(Paint.Style.STROKE);
		this.dPaint.setStrokeJoin(Paint.Join.ROUND);
		this.dPaint.setStrokeCap(Paint.Cap.ROUND);
		this.cPaint = new Paint(Paint.DITHER_FLAG);
	}

	public void setColor(String newColor) {
		this.pColor = Color.parseColor(newColor);
		this.dPaint.setColor(this.pColor);
	}

	public void setStrokeWidth(int width) {
		this.dPaint.setStrokeWidth(width);
	}

	public void setDrawAble(boolean drawAble) {
		this.drawAble = drawAble;
	}

	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
				matrix, false);
		return resizedBitmap;
	}

	public void newDrawing(Bitmap bitmap) {

		this.dCanvas.drawColor(Color.WHITE);

		this.dCanvas.drawBitmap(
				getResizedBitmap(bitmap, this.getHeight(), this.getWidth()),
				getMatrix(), null);

		this.dPath.reset();
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
