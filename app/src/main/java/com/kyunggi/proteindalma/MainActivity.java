package com.kyunggi.proteindalma;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener
{
	public enum NameMode{
		FULL,
		SHORT,
		KOR
	}
	NameMode nm;
	@Override
	public void onCheckedChanged(CompoundButton p1, boolean p2)
	{
		// TODO: Implement this method
		if(p2==false)
			return;
	    int id=	p1.getId();
		switch(id)
		{
			case R.id.mainRadioButtonFull:
				nm=NameMode.FULL;
				break;
			case R.id.mainRadioButtonKor:
				nm=NameMode.KOR;
				break;
			case R.id.mainRadioButtonShort:
				nm=NameMode.SHORT;
		}
		pref=getSharedPreferences("save", 0);
		edit=pref.edit();
		edit.putString("NameMode",nm.name());
		edit.commit();
		return ;
	}
	
	SharedPreferences pref;
	SharedPreferences.Editor edit; 
	@Override
	public void onClick(View p1)
	{
		// TODO: Implement this method
		int id=p1.getId();
		switch(id)
		{
			case R.id.mainButtonStart:
				start();
				break;
			case R.id.mainButtonHighScore:
				showHighScore();
				break;
		}
		return ;
	}

	private void start()
	{
		// TODO: Implement this method
		Intent intent=new Intent(this,GameActivity.class);
		startActivity(intent);
		return ;
	}

	private void showHighScore()
	{
		// TODO: Implement this method
		pref=getSharedPreferences("save", 0);       //save 라는 그룹이름으로 객체를 가져온다 
		int high=pref.getInt("HighScore",0);
		Toast.makeText(this,"High Score: "+high,2).show();
		/*//없으면 자동생성
			edit=pref.edit();                                        //데이터를 저장하기 위한 edit 을 생성한다.
		edit.putInt("HighScore", highScore); **/           //savename에 name값을을 저장한다.           //savehungry에 hungry값을 저장한다.
		return ;
	}
	
	Button btStart;
	Button btHigh;
	Button btExit;
	RadioButton rbShort,rbFull,rbKor;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		btStart=(Button) findViewById(R.id.mainButtonStart);
		btHigh=(Button) findViewById(R.id.mainButtonHighScore);
		btExit=(Button) findViewById(R.id.mainButtonExit);
		btStart.setOnClickListener(this);
		btHigh.setOnClickListener(this);
		btExit.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					finish();
					System.runFinalizersOnExit(true);
					System.exit(0);	
					return ;
				}	
		});
		rbShort=(RadioButton) findViewById(R.id.mainRadioButtonShort);	
		rbKor=(RadioButton) findViewById(R.id.mainRadioButtonKor);
		rbFull=(RadioButton) findViewById(R.id.mainRadioButtonFull);
		nm=NameMode.SHORT;
		rbShort.setChecked(true);
		rbShort.setOnCheckedChangeListener(this);
		rbKor.setOnCheckedChangeListener(this);
		rbFull.setOnCheckedChangeListener(this);
		return;
    }

	@Override
	protected void onDestroy()
	{
		// TODO: Implement this method
		super.onDestroy();
		//edit=pref.edit(); 
		//int highScore=0;
		//데이터를 저장하기 위한 edit 을 생성한다.
		//edit.putInt("HighScore", highScore); 
		//edit.commit();
	}
	
}
