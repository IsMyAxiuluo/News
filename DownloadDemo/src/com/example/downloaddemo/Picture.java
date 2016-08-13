package com.example.downloaddemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class Picture extends FrameLayout{
	
	Context mcontext;
	
	public Picture(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public Picture(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.mcontext = context;
		into();
	}

	@TargetApi(23)
	private void into() {
		// TODO Auto-generated method stub
		ImageView mimage = new ImageView(mcontext);
		mimage.setBackgroundResource(R.drawable.ic_launcher);
		mimage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(mcontext, "обть", Toast.LENGTH_LONG).show();
			}
		});
	}

	public Picture(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.mcontext = context;
		into();
	}

}
