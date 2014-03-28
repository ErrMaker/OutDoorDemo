package com.outdoor.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.outdoordemo2.R;
import com.outdoor.adapter.ChatMsgViewAdapter;
import com.outdoor.model.ChatMsgEntity;

/**
 * 
 * @author geniuseoe2012 ���ྫ�ʣ����ע�ҵ�CSDN����http://blog.csdn.net/geniuseoe2012
 *         android��������Ⱥ��200102476
 */
public class ChatActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	private Button mBtnSend;
	private Button mBtnBack;
	private EditText mEditTextContent;
	// ��Ϣ�б���ͼ
	private ListView mListView;
	// ��Ϣ�б�����������
	private ChatMsgViewAdapter mAdapter;
	// ������Ϣ����б�
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		// ����activityʱ���Զ����������
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		// ��ʼ������ؼ�
		initView();
		// ��ʼ����������
		initData();
	}

	public void initView() {
		mListView = (ListView) findViewById(R.id.listview);
		mBtnSend = (Button) findViewById(R.id.btn_send);
		mBtnSend.setOnClickListener(this);
		mBtnBack = (Button) findViewById(R.id.btn_back);
		mBtnBack.setOnClickListener(this);

		mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
	}

	// ----------------------------------------ģ������----------------------------------------
	private String[] msgArray = new String[] { "���Ϻ�", "��", "ȥʲô�ط��棿", "������ȥ����",
			"һ��ȥ�ﳵ��", "��Ƚ����¿���һ����", "����һ��ȥǱˮ", "<>���յ�<>"};

	private String[] dataArray = new String[] { "2013-9-1 08:00",
			"2013-9-3 12:10", "2013-12-4 18:11", "2013-12-25 00:20",
			"2014-1-3 13:30", "2014-1-14 19:35", "2014-3-1 02:42",
			"2014-3-21 18:54" };
	private final static int COUNT = 8;

	// ----------------------------------------ģ������----------------------------------------

	public void initData() {
		for (int i = 0; i < COUNT; i++) {
			ChatMsgEntity entity = new ChatMsgEntity();
			entity.setDate(dataArray[i]);
			if (i % 2 == 0) {
				entity.setName("С��");
				entity.setMsgType(true);
			} else {
				entity.setName("��");
				entity.setMsgType(false);
			}

			entity.setText(msgArray[i]);
			mDataArrays.add(entity);
		}

		mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
		mListView.setAdapter(mAdapter);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_send:
			send();
			break;
		case R.id.btn_back:
			finish();
			break;
		}
	}

	private void send() {
		String contString = mEditTextContent.getText().toString();
		if (contString.length() > 0) {
			// ��Ϣ����
			ChatMsgEntity entity = new ChatMsgEntity();
			// ���÷�������
			entity.setDate(getDate());
			// �����û�����
			entity.setName("��");
			// ������Ϣ����Ϊ ��������
			entity.setMsgType(false);
			// ������Ϣ�ı�����
			entity.setText(contString);

			mDataArrays.add(entity);
			// ���ݸ���֪ͨ�� ˢ��UI
			mAdapter.notifyDataSetChanged();

			// ����������ÿ�
			mEditTextContent.setText("");
			// ??? ɶ����?
			mListView.setSelection(mListView.getCount() - 1);
		}
	}

	private String getDate() {
		Calendar c = Calendar.getInstance();

		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = String.valueOf(c.get(Calendar.MONTH));
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
		String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String mins = String.valueOf(c.get(Calendar.MINUTE));

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":"
				+ mins);

		return sbBuffer.toString();
	}
	
	/*
	public void head_xiaohei(View v) { // ������ ���ذ�ť
		Intent intent = new Intent(ChatActivity.this, InfoXiaohei.class);
		startActivity(intent);
	} */
	
}