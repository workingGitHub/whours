package com.instance.working.whours.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.instance.working.whours.R;

import java.io.File;

/**
 * Created by Administrator on 2016/4/25 0025.
 */
public class MediaRecorderButton extends Button {
    public enum MediaState {
        INVALID,RECORD,RECORDING,RECORDEND,PLAYING
    }
    MediaState mstate;
    File mSourceFile;
    MediaRecorder mRecorder;
    MediaPlayer mMediaPlayer;

    private void updateText()
    {
        if(mstate.equals(MediaState.INVALID))
        {
            setText(R.string.mediabutton_invaild);
        }else if(mstate.equals(MediaState.RECORD))
        {
            setText(R.string.mediabutton_record);
        }else if(mstate.equals(MediaState.RECORDING))
        {
            setText(R.string.mediabutton_recording);
        }else if(mstate.equals(MediaState.RECORDEND))
        {
            setText(R.string.mediabutton_recordend);
        }else if(mstate.equals(MediaState.PLAYING))
        {
            setText(R.string.mediabutton_playing);
        }else
        {
            setText(R.string.mediabutton_default);
        }
        return;
    }


    public MediaRecorderButton(Context context) {
        super(context);
        mstate = MediaState.INVALID;
        updateText();
    }

    public MediaRecorderButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mstate = MediaState.INVALID;
        updateText();
    }

    public MediaRecorderButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mstate = MediaState.INVALID;
        updateText();
    }

    public void setMediaFileName(String MediaFileName)
    {
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            mstate = MediaState.INVALID;
            //Toast.makeText(getContext(), R.string.err_sdcardnotexist,Toast.LENGTH_LONG).show();
            return;
        }
        try{
            mSourceFile = new File(Environment.getExternalStorageDirectory().getCanonicalFile() + "/" + MediaFileName + ".amr");
            if(mSourceFile.exists())
            {
                mstate = MediaState.RECORDEND;
            }else {
                mstate = MediaState.RECORD;
            }
        }catch(Exception e)
        {
            System.out.println(e.toString());
            System.out.println("--------------------");
            System.out.println(e.getMessage());
            System.out.println("--------------------");
            e.printStackTrace();
            Log.e("working", e.toString());
            Log.e("working", e.getMessage());
            mstate = MediaState.INVALID;
        }

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 设置处理 单击功能
                if (mstate.equals(MediaState.RECORD)) {
                    // TODO: 开始录音
                    StartRecord();
                } else if (mstate.equals(MediaState.RECORDING)) {
                    // TODO: 停止录音
                    EndRecord();
                } else if (mstate.equals(MediaState.RECORDEND)) {
                    // TODO:开始播放
                    StartPlay();
                } else if (mstate.equals(MediaState.PLAYING)) {
                    // TODO:停止播放
                    EndPlay();
                }
                updateText();
            }
        });
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // 设置处理长按功能
                DeleteRecord();
                return true;
            }
        });
        updateText();
    }
    public void StartRecord()
    {
        if(mSourceFile == null)
        {
            mstate = MediaState.INVALID;
            return;
        }

        if(mSourceFile.exists())
        {
            mstate = MediaState.RECORDEND;
            return;
        }

        try{
            mRecorder = new MediaRecorder();
            //设置录音的声音来源
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //设置声音的输出格式
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            //设置声音编码格式
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            mRecorder.setOutputFile(mSourceFile.getAbsolutePath());
            mRecorder.prepare();
            mRecorder.start();
            mstate = MediaState.RECORDING;
        }catch(Exception e)
        {
            System.out.println(e.toString());
            System.out.println("--------------------");
            System.out.println(e.getMessage());
            System.out.println("--------------------");
            e.printStackTrace();
            Log.e("working", e.toString());
            Log.e("working", e.getMessage());
            mstate = MediaState.INVALID;
        }
        return;
    }
    public void EndRecord()
    {
        if((mSourceFile == null)||(mRecorder == null))
        {
            mstate = MediaState.INVALID;
            return;
        }
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        mstate = MediaState.RECORDEND;
        mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource(mSourceFile.getAbsolutePath());
        } catch(Exception e)
        {
            System.out.println(e.toString());
            System.out.println("--------------------");
            System.out.println(e.getMessage());
            System.out.println("--------------------");
            e.printStackTrace();
            Log.e("working", e.toString());
            Log.e("working", e.getMessage());
            mstate = MediaState.INVALID;
        }
        return ;
    }
    public void StartPlay()
    {
        if(mSourceFile == null)
        {
            mstate = MediaState.INVALID;
            return;
        }
        if(!mSourceFile.exists())
        {
            mstate = MediaState.RECORD;
            return;
        }
        try {
            if(mMediaPlayer == null) {
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setDataSource(mSourceFile.getAbsolutePath());
            }
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    EndPlay();
                    updateText();
                }
            });
            mstate = MediaState.PLAYING;
        }catch(Exception e)
        {
            System.out.println(e.toString());
            System.out.println("--------------------");
            System.out.println(e.getMessage());
            System.out.println("--------------------");
            e.printStackTrace();
            Log.e("working", e.toString());
            Log.e("working", e.getMessage());
            mstate = MediaState.INVALID;
        }
    }
    public void EndPlay()
    {
        if(mSourceFile == null)
        {
            mstate = MediaState.INVALID;
            return;
        }
        if(mMediaPlayer == null)
        {
            mstate = MediaState.RECORDEND;
            return;
        }
        mMediaPlayer.stop();
        mMediaPlayer.release();
        mMediaPlayer = null;
        mstate = MediaState.RECORDEND;
        return ;
    }
    public void DeleteRecord()
    {
        if((mstate.equals(MediaState.INVALID))||(mstate.equals(MediaState.RECORD)))
        {
            return ;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle(R.string.mediabutton_dialog_delete_title)
                .setMessage(R.string.mediabutton_dialog_delete_text);
        builder.setPositiveButton(R.string.mediabutton_dialog_btn_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(mstate.equals(MediaState.RECORDING))
                        {
                            EndRecord();
                        }else if(mstate.equals(MediaState.PLAYING))
                        {
                            EndPlay();
                        }

                        if (mSourceFile.isFile() && mSourceFile.exists()) {
                            mSourceFile.delete();
                        }
                        mstate = MediaState.RECORD;
                        updateText();
                        return;
                    }
                });
        builder.create().show();

    }
}
