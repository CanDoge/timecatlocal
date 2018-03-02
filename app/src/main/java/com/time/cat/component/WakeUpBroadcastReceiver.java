package com.time.cat.component;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.time.cat.component.service.ListenClipboardService;
import com.time.cat.util.LogUtil;

/**
 * Created by wangyan-pd on 2016/11/23.
 */

public class WakeUpBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.d("shang", "xposed wake");
        try {
            context.startService(new Intent(context, ListenClipboardService.class));
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
